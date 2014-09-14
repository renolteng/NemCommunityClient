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
			ncc.loadTransactions = function(reload, update) {
				var api = ncc.get('transactions.filter');
				var currAccount = ncc.get('activeAccount.address');
				var requestData = { account: currAccount };
				var currTxes;
				if (!reload && !update) {
					currTxes = ncc.get('transactions.filtered');
					var lastHash = (currTxes && currTxes.length)? currTxes[currTxes.length - 1].hash : null;
					if (lastHash) requestData.hash = lastHash;
				}

				ncc.postRequest(api, requestData, function(data) {
					var updatedTxes = ncc.processTransactions(data.transactions);
					var all;
					if (!reload && currTxes && currTxes.concat) {
						all = currTxes.concat(updatedTxes);
					} else if (update) {
						all = ncc.updateNewer(updatedTxes, currTxes, 'hash');
					} else {
						all = updatedTxes;
					}

                    ncc.set('transactions.filtered', all);
                    if (!update) {
                    	var gotAll = updatedTxes.length < ncc.consts.txesPerPage;
                		ncc.set('transactions.gotAll', gotAll);
                		if (gotAll) {
                			ncc.global.$window.off('scroll.txesInfiniteScrolling');
                		}
                	}
                }, null, update);
			};
		},
    	setupEverytime: function() {
    		var local = this.local;

			local.listeners.push(ncc.observe('activeAccount.address transactions.filter', function() {
				ncc.loadTransactions(true);
			}));

			local.intervalJobs.push(setInterval(function() {
				ncc.loadTransactions(false, true);
			}, local.autoRefreshInterval));

			var $win = ncc.global.$window;
			var $doc = ncc.global.$document;
			$win.on('scroll.txesInfiniteScrolling', function(event) {
				if (!ncc.get('status.loadingOlderTransactions') && $win.scrollTop() + $win.height() >= $doc.height() - local.scrollBottomTolerance) {
					ncc.loadTransactions(false);
				}
			});
    	},
    	leave: [function() {
    		ncc.global.$window.off('scroll.txesInfiniteScrolling');
    	}]
    });
});