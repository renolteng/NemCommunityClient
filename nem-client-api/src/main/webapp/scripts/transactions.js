"use strict";

 define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
    return $.extend(true, {}, NccLayout, {
        name: 'transactions',
        url: 'transactions.html',
        template: 'rv!layout/transactions',
        parent: 'wallet-pages',
        local: {
        	scrollBottomTolerance: 100
        },
        setupOnce: function() {
        	ncc.loadOlderTransactions = function() {
				var currTxes = ncc.get('activeAccount.transactions');
				if (currTxes && currTxes[0] && !ncc.get('transactions.gotAll')) {
					ncc.set('status.loadingOlderTransactions', true);
					var requestData = {
						account: ncc.get('activeAccount.address'),
						timeStamp: currTxes[currTxes.length - 1].timeStamp
					};
					ncc.postRequest('account/transactions', requestData, function(data) {
						var oldTxes = data.transactions;

	                    if (oldTxes) {
	                    	oldTxes = ncc.processTransactions(oldTxes);
	                    	
	                    	if (oldTxes.length > 0) {
		                        var topTxHash = oldTxes[0].hash;
		                        
		                        for (var i = 0; i < currTxes.length; i++) {
		                            if (currTxes[i].hash === topTxHash) {
		                                break;
		                            }
		                        }

		                        var nonDuplicatedTxes = currTxes.slice(0, i);
		                        var txes = nonDuplicatedTxes.concat(oldTxes);
		                        ncc.set('activeAccount.transactions', txes);
		                    }

		                    if (oldTxes.length < ncc.consts.txesPerPage) {
		                    	ncc.set('transactions.gotAll', true);
		                    }
		                }
					}, {
						complete: function() {
							ncc.set('status.loadingOlderTransactions', false);
						}
					});
				}
			};
		},
    	setupEverytime: function() {
    		var local = this.local;

			local.listeners.push(ncc.observe('activeAccount.transactions transactions.filter', function() {
				var filter = ncc.get('transactions.filter');
				var transactions = ncc.get('activeAccount.transactions');

				if (filter === 'all') {
					ncc.set('transactions.filtered', transactions);
				} else {
					var filtered = [];

					switch (filter) {
						case 'pending':
							for (var i = 0; i < transactions.length; i++) {
								var t = transactions[i];
								if (!t.confirmed) filtered.push(t);
							}
							break;
						case 'incoming':
							for (var i = 0; i < transactions.length; i++) {
								var t = transactions[i];
								if (!t.isPending && (t.isIncoming || t.isSelf)) filtered.push(t);
							}
							break;
						case 'outgoing':
							for (var i = 0; i < transactions.length; i++) {
								var t = transactions[i];
								if (!t.isPending && (t.isOutgoing || t.isSelf)) filtered.push(t);
							}
							break;
					}

					ncc.set('transactions.filtered', filtered);
				}
			}));
			
			var $win = $(window);
			var $doc = $(document);
			$win.on('scroll.txesInfiniteScrolling', function(event) {
				if (!ncc.get('status.loadingOlderTransactions') && $win.scrollTop() + $win.height() >= $doc.height() - local.scrollBottomTolerance) {
					ncc.loadOlderTransactions();
				}
			});
    	},
    	leave: [function() {
    		$(window).off('scroll.txesInfiniteScrolling');
    	}]
    });
});