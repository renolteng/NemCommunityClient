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
			ncc.loadTransactions = function(reload) {
				var api = ncc.get('transactions.filter');
				var currAccount = ncc.get('activeAccount.address');
				var requestData = { account: currAccount };
				var currTxes;
				if (!reload) {
					currTxes = ncc.get('transactions.filtered');
					var lastHash = (currTxes && currTxes.length)? currTxes[currTxes.length - 1] : null;
					if (lastHash) requestData.hash = lastHash;
				}

				ncc.postRequest(api, requestData, function(data) {
					var updatedTxes = ncc.processTransactions(data.transactions);
					var concat = (!reload && currTxes && currTxes.concat)? currTxes.concat(updatedTxes) : updatedTxes;
                    ncc.set('transactions.filtered', concat);
                	ncc.set('transactions.gotAll', updatedTxes.length < ncc.consts.txesPerPage);
                }, null, true);
			};
		},
    	setupEverytime: function() {
    		var local = this.local;

			ncc.loadTransactions(false);

			local.listeners.push(ncc.observe('activeAccount.address transactions.filter', function() {
				ncc.loadTransactions(true);
			}));

			local.txesUpdate = setInterval(function() {
				var api = ncc.get('transactions.filter');
				var currAccount = ncc.get('activeAccount.address');
				var currTxes = ncc.get('transactions.filtered');

				ncc.postRequest(api, { account: currAccount }, function(data) {
					var updatedTxes = ncc.processTransactions(data.transactions);
                    ncc.set('transactions.filtered', ncc.updateNewer(updatedTxes, currTxes, function(obj) {
                        return obj.hash;
                    }));
                }, null, true);
			}, local.autoRefreshInterval);

			var $win = $(window);
			var $doc = $(document);
			$win.on('scroll.txesInfiniteScrolling', function(event) {
				if (!ncc.get('status.loadingOlderTransactions') && $win.scrollTop() + $win.height() >= $doc.height() - local.scrollBottomTolerance) {
					ncc.loadTransactions(false);
				}
			});
    	},
    	leave: [function() {
    		$(window).off('scroll.txesInfiniteScrolling');
    		clearInterval(this.local.txesUpdate);
    	}]
    });
});