"use strict";

 define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
 	return $.extend(true, {}, NccLayout, {
 		name: 'landing',
 		url: 'index.html',
		template: 'rv!layout/landing',
		local: {
			initialWaitTime: 0,
			closingGateTime: 1000,
			scrollHeader: 'landingPage.scrollHeader',
			nisCheckingInterval: 3000
		},
		initOnce: function() {
            ncc.openWallet = function(walletData) {
            	ncc.set('wallet', ncc.processWallet(walletData));
            	ncc.set('activeAccount', ncc.processAccount(walletData.primaryAccount));
                ncc.loadPage('dashboard', {
                	wallet: walletData.name,
                	account: walletData.primaryAccount.address
                });
	        };
		},
		initEverytime: function() {
			ncc.set('landingPage.gateClosed', false);
			ncc.set('landingPage.overlayIn', false);
			ncc.set('landingPage.hexagonIn', false);
			ncc.set('landingPage.activeSection', '');
			ncc.set(this.local.scrollHeader, false);
		},
		setupEverytime: function() {
			var local = this.local;
			var global = ncc.global;

			global.$window.on('scroll.landing', (function() {
				var $staticHeader = $('.staticHeader');
				var headerBottom = $staticHeader.offset().top + $staticHeader.outerHeight();

				var $landingBackground = $('.landing-background');
				var landingBgTop = $landingBackground.offset().top;

				return function(event) {
					var scrollTop = global.$document.scrollTop();

					// Scroll header
					if (!ncc.get(local.scrollHeader) && scrollTop >= headerBottom) {
						ncc.set(local.scrollHeader, true);
					} else if (ncc.get(local.scrollHeader) && scrollTop < headerBottom) {
						ncc.set(local.scrollHeader, false);
					}

					// Floating background
					var shifted = (scrollTop - landingBgTop) * .8;
					$landingBackground.css('background-position', '50% calc(50% + ' + shifted + 'px)');
				}
			})());

			// Intro
			$(function() {
				setTimeout(function() {
					ncc.set('landingPage.gateClosed', true);

					setTimeout(function() {
						ncc.set('landingPage.overlayIn', true);
						ncc.set('landingPage.hexagonIn', true);
					}, local.closingGateTime);
				}, local.initialWaitTime);
			});

			// Retrieve wallet list
			ncc.set('landingPage.listingWallets', true);
			ncc.getRequest('wallet/list', 
				function(data) {
					ncc.set('wallets', data.wallets);
				},
				{
					complete: function() {
						ncc.set('landingPage.listingWallets', false);
					}
				}
			);

			require(['tinycarousel'], function() {
				$('.tipsCarousel-container').tinycarousel({
			        interval: true,
			        intervalTime: 5000,
			        animationTime: 1000
			    });
			});

			require(['expand'], function() {
				var $leftForm = $('.createWalletForm');
				var $rightForm = $('.openWalletForm');
				var $createWalletNameInput = $('.js-textbox-createWallet-name');

				local.listeners.push(ncc.on({
					'switchLeft': function() {
						if (ncc.get('landingPage.activeSection') === 'right') {
							$rightForm.collapse();
						}
						ncc.set('landingPage.activeSection', 'left');
						ncc.set('landingPage.hexagonIn', false);
						$leftForm.expand();
						$createWalletNameInput.focus();
					},
					'switchRight': function() {
						if (ncc.get('landingPage.activeSection') === 'left') {
							$leftForm.collapse();
						}
						ncc.set('landingPage.activeSection', 'right');
						ncc.set('landingPage.hexagonIn', false);
						$rightForm.expand();
					}
				}));
			});

			local.listeners.push(ncc.on({
				openWallet: function(e, wallet) {
			    	var requestData = {
						wallet: wallet,
						password: ncc.get('landingPage.openWalletPasswords')[wallet]
					};
					ncc.set('landingPage.openingWallet', true);
			    	ncc.postRequest('wallet/open', requestData,
			    		function(data) {
			        		ncc.openWallet(data);
			        	},
						{
							complete: function() {
								ncc.set('landingPage.openingWallet', false);
							}
						}
			    	);
			    },
				createWallet: function() {
					ncc.set('landingPage.creatingWallet', true);
					var requestData = ncc.get('landingPage.createWalletForm');
			    	ncc.postRequest('wallet/create', requestData, function(data) {
		    			ncc.postRequest('wallet/open', requestData,
				    		function(data) {
				        		ncc.openWallet(data);
				        	}
				    	);
		    		},
		    		{
		    			complete: function() {
		    				ncc.set('landingPage.creatingWallet', false);
		    			}
		    		});
			    },
			    scrollTo: function(e, selector) {
			    	var $el = $(selector);
			    	if ($el[0]) {
			    		var offsetTop = $el.offset().top;
				        global.$html.add(global.$body).stop().animate({ // Chrome scrolls body, Firefox and IE scrolls html
				            scrollTop: offsetTop
				        }, 400);
			    	}
			    },
			    createWalletKeypress: function(e) {
			    	if (e.original.keyCode === 13) {
			    		ncc.fire('createWallet');
			    	}
			    },
			    passwordKeypress: function(e, wallet) {
			    	if (e.original.keyCode === 13) {
			    		ncc.fire('openWallet', {}, wallet);
			    	}
			    },
			    fileToImportSelected: function(e) {
			    	ncc.set('fileToImport.import_path', e.node.value);
			    },
			    importWallet: function() {
			    	var requestData = ncc.get('fileToImport');
			    	ncc.postRequest('wallet/import', requestData, function(data) {
			    		ncc.showMessage(ncc.get('texts.modals.common.success'), ncc.get('texts.landing.importSuccess'));
			    		ncc.set('fileToImport', {});
			    	});
			    },
			    importKeypress: function(e) {
			    	if (e.original.keyCode === 13) {
			    		ncc.fire('importWallet');
			    	}
			    },
			    walletSelected: function(e) {
			    	$(e.node).siblings('.openWalletList-passwordPrompt').find('.openWalletList-password').focus();
			    },
			    switchLanguage: function(e, id) {
			    	ncc.set('settings.language', id);
			    	ncc.postRequest('configuration/update', ncc.get('settings'), null, null, true);
			    }
			}));

			global.$html.addClass('landing');
		},
		leave: [function() {
			ncc.global.$window.off('scroll.landing');
			ncc.set('landingPage', null);
			ncc.global.$html.removeClass('landing');
		}]
 	});
});