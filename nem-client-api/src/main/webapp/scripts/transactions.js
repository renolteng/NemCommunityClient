"use strict";

 define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
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
        	 * @param {string} type load type: 'reload' | 'update' | 'append'
        	 */
			ncc.loadTransactions = function(type) {
				var api = ncc.get('transactions.filter');
				var currAccount = ncc.get('activeAccount.address');
				var requestData = { account: currAccount };
				var currTxes = ncc.get('transactions.filtered');
				if (type === 'append') {
					requestData.hash = (currTxes && currTxes.length) ? currTxes[currTxes.length - 1].hash : undefined;
					ncc.set('status.loadingOlderTransactions', true);
				}

				ncc.postRequest(api, requestData, function(data) {
					var updatedTxes = ncc.processTransactions(data.transactions);
					var all;
					if (type === 'append' && currTxes && currTxes.concat) {
						all = currTxes.concat(updatedTxes);
					} else if (type === 'update') {
						all = ncc.updateNewer(updatedTxes, currTxes, 'hash');
					} else {
						all = updatedTxes;
					}

                    ncc.set('transactions.filtered', all);
                    if (type !== 'update') {
                    	var gotAll = updatedTxes.length < ncc.consts.txesPerPage;
                		ncc.set('transactions.gotAll', gotAll);
                		if (gotAll) {
                			ncc.global.$window.off('scroll.txesInfiniteScrolling');
                		}
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
			}));

			local.intervalJobs.push(setInterval(function() {
				ncc.loadTransactions('update');
			}, local.autoRefreshInterval));

			var $win = ncc.global.$window;
			var $doc = ncc.global.$document;
			$win.on('scroll.txesInfiniteScrolling', function(event) {
				if (!ncc.get('status.loadingOlderTransactions') && $win.scrollTop() + $win.height() >= $doc.height() - local.scrollBottomTolerance) {
					ncc.loadTransactions('append');
				}
			});
    	},
    	leave: [function() {
    		ncc.global.$window.off('scroll.txesInfiniteScrolling');
    	}]
    });
});