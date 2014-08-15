"use strict";

 define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
    return $.extend(true, {}, NccLayout, {
        name: 'harvested-blocks',
        url: 'harvested-blocks.html',
        template: 'rv!layout/harvested-blocks',
        parent: 'wallet-pages',
        local: {
        	scrollBottomTolerance: 100
        },
        setupOnce: function() {
        	ncc.refreshHarvestedBlocks = function(account) {
        		var activeAccount = account || ncc.get('activeAccount.address');

				ncc.postRequest('account/harvests', { account: activeAccount }, function(data) {
					var updatedBlocks = ncc.processHarvestedBlocks(data.data);

					if (account) {
						ncc.set('harvestedBlocks.list', updatedBlocks);
						ncc.set('harvestedBlocks.gotAll', updatedBlocks.length < ncc.consts.blocksPerPage);
					} else {
	                    ncc.set('harvestedBlocks.list', ncc.updateNewer(updatedBlocks, ncc.get('harvestedBlocks.list'), function(obj) {
	                        return obj.hash;
	                    }));
					}
				});
			};

			ncc.loadOlderHarvestedBlocks = function() {
				var currBlocks = ncc.get('harvestedBlocks.list');
				if (currBlocks && currBlocks[0] && !ncc.get('harvestedBlocks.gotAll')) {
					ncc.set('status.loadingOlderBlocks', true);
					var requestData = {
						account: ncc.get('activeAccount.address'),
						timeStamp: currBlocks[currBlocks.length - 1].timeStamp
					};
					ncc.postRequest('account/harvests', requestData, function(data) {
						var oldBlocks = data.data;

	                    if (oldBlocks) {
	                    	oldBlocks = ncc.processHarvestedBlocks(oldBlocks);
	                    	
	                    	if (oldBlocks.length > 0) {
		                        var topBlockHash = oldBlocks[0].hash;
		                        
		                        for (var i = 0; i < currBlocks.length; i++) {
		                            if (currBlocks[i].hash === topBlockHash) {
		                                break;
		                            }
		                        }

		                        var nonDup = currBlocks.slice(0, i);
		                        var blocks = nonDup.concat(oldBlocks);
		                        ncc.set('harvestedBlocks.list', blocks);
		                    }

		                    if (oldBlocks.length < ncc.consts.blocksPerPage) {
		                    	ncc.set('harvestedBlocks.gotAll', true);
		                    }
		                }
					}, {
						complete: function() {
							ncc.set('status.loadingOlderBlocks', false);
						}
					});
				}
			};
        },
    	setupEverytime: function() {
    		var local = this.local;

    		local.listeners.push(ncc.observe({
    			'harvestedBlocks.list': function(harvestedBlocks) {
    				var sum = 0;
    				if (harvestedBlocks) {
	    				for (var i = 0; i < Math.min(harvestedBlocks.length, ncc.consts.blocksPerPage); i++) {
	    					sum += harvestedBlocks[i].fee;
	    				}
	    			}

	    			ncc.set('harvestedBlocks.feeEarned', sum);
    			},
    			'activeAccount.address': function(account) {
    				ncc.refreshHarvestedBlocks(account);
    			}
    		}));

    		var $win = $(window);
			var $doc = $(document);
			$win.on('scroll.harvestedBlocksInfiniteScrolling', function(event) {
				if (!ncc.get('status.loadingOlderBlocks') && $win.scrollTop() + $win.height() >= $doc.height() - local.scrollBottomTolerance) {
					ncc.loadOlderHarvestedBlocks();
				}
			});

			ncc.refreshHarvestedBlocks();
			local.autoRefresh = setInterval(ncc.refreshHarvestedBlocks, local.autoRefreshInterval);
    	},
    	leave: [function() {
    		clearInterval(this.local.autoRefresh);
    		$(window).off('scroll.harvestedBlocksInfiniteScrolling');
    	}]
    });
});
