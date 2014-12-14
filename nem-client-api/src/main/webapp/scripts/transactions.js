"use strict";

 define(['jquery', 'ncc', 'NccLayout', 'Utils'], function($, ncc, NccLayout, Utils) {
    return $.extend(true, {}, NccLayout, {
        name: 'transactions',
        url: 'transactions.html',
        template: 'rv!layout/transactions',
        parent: 'wallet',
        local: {
        	scrollBottomTolerance: 100
        },
        initOnce: function() {
        	/**
        	 * @param {string} type load type: 'reload' | 'update' | 'append', default is 'reload'
        	 */
			ncc.loadTransactions = function(type) {
				var api = ncc.get('transactions.filter');
				var currAccount = ncc.get('activeAccount.address');
				var requestData = { account: currAccount };
				var currTxes = ncc.get('transactions.filtered');
				if (type === 'append') {
					requestData.id = (currTxes && currTxes.length) ? currTxes[currTxes.length - 1].id : undefined;
					ncc.set('status.loadingOlderTransactions', true);
				} else if (type !== 'update') {
					ncc.set('transactions.filtered', null);
				}

				ncc.postRequest(api, requestData, function(data) {
					var updatedTxes = Utils.processTransactions(data.transactions);
					var all;
					if (type === 'append' && currTxes && currTxes.concat) {
						all = currTxes.concat(updatedTxes);
					} else if (type === 'update') {
						var result = Utils.updateNewer(updatedTxes, currTxes, 'id');
						all = result.updatedArray;
						if (result.noConnection) {
							ncc.set('transactions.gotAll', false);
						}
					} else {
						all = updatedTxes;
					}

                    ncc.set('transactions.filtered', all);
                    if (type !== 'update') {
                    	var gotAll = updatedTxes.length < Utils.config.txesPerPage;
                		ncc.set('transactions.gotAll', gotAll);
                	}
                }, {
                	complete: function() {
                		ncc.set('status.loadingOlderTransactions', false);
                	}
                }, type === 'update');
			};
		},
    	setupEverytime: function() {
    		var local = this.local;

			local.listeners.push(ncc.observe('activeAccount.address transactions.filter', function() {
				ncc.loadTransactions('reload');
			}, {
				init: false
			}));

			local.listeners.push(ncc.on({
				refreshAccount: function() {
					if (ncc.get('transactions.filter') === 'account/transactions/unconfirmed') {
						ncc.loadTransactions('reload');
					} else {
						ncc.loadTransactions('update');
					}
				}
			}));

			var $win = ncc.global.$window;
			var $doc = ncc.global.$document;
			local.listeners.push(ncc.observe('transactions.gotAll', function(gotAll) {
				if (gotAll) {
					ncc.global.$window.off('scroll.txesInfiniteScrolling');
				} else {
					$win.on('scroll.txesInfiniteScrolling', function(event) {
						if (ncc.get('transactions.filter') !== 'account/transactions/unconfirmed' 
							&& !ncc.get('status.loadingOlderTransactions')
							&& $win.scrollTop() + $win.height() >= $doc.height() - local.scrollBottomTolerance) {
							ncc.loadTransactions('append');
						}
					});
				}
			}));

			ncc.loadTransactions('reload');
    	},
    	leave: [function() {
    		ncc.global.$window.off('scroll.txesInfiniteScrolling');
    	}]
    });
});