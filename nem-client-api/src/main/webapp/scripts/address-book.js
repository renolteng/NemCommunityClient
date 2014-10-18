"use strict";

 define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
    return $.extend(true, {}, NccLayout, {
        name: 'address-book',
        url: 'address-book.html',
        template: 'rv!layout/address-book',
        parent: 'wallet',
        local: {
        	scrollBottomTolerance: 100
        },
        initOnce: function() {
            /**
             * @param {string} type load type: 'reload' | 'update' | 'append'
             */
   //      	ncc.loadHarvestedBlocks = function(type) {
   //      		var currAccount = ncc.get('activeAccount.address');
   //      		var requestData = { account: currAccount };
   //      		var currBlocks = ncc.get('harvestedBlocks.list');
   //      		if (type === 'append') {
			// 		requestData.hash = (currBlocks && currBlocks.length) ? currBlocks[currBlocks.length - 1].hash : undefined;
   //                  ncc.set('status.loadingOlderBlocks', true);
			// 	}

			// 	ncc.postRequest('account/harvests', requestData, function(data) {
			// 		var updatedBlocks = ncc.processHarvestedBlocks(data.data);
			// 		var all;
			// 		if (type === 'append' && currBlocks && currBlocks.concat) {
			// 			all = currBlocks.concat(updatedBlocks);
			// 		} else if (type === 'update') {
			// 			all = ncc.updateNewer(updatedBlocks, currBlocks, 'hash');
			// 		} else {
			// 			all = updatedBlocks;
			// 		}

			// 		ncc.set('harvestedBlocks.list', all);
   //              	if (type !== 'update') {
   //                      var gotAll = updatedBlocks.length < ncc.consts.blocksPerPage;
   //              		ncc.set('harvestedBlocks.gotAll', gotAll);
   //                      if (gotAll) {
   //                          ncc.global.$window.off('scroll.harvestedBlocksInfiniteScrolling');
   //                      }
   //              	}
			// 	}, {
   //                  complete: function() {
   //                      ncc.set('status.loadingOlderBlocks', false);
   //                  }
   //              }, type === 'update');
			// };
        },
    	setupEverytime: function() {
   //  		var local = this.local;

   //  		local.listeners.push(ncc.observe({
   //  			'harvestedBlocks.list': function(harvestedBlocks) {
   //  				var sum = 0;
   //  				if (harvestedBlocks) {
	  //   				for (var i = 0; i < Math.min(harvestedBlocks.length, ncc.consts.blocksPerPage); i++) {
	  //   					sum += harvestedBlocks[i].fee;
	  //   				}
	  //   			}

	  //   			ncc.set('harvestedBlocks.feeEarned', sum);
   //  			},
   //  			'activeAccount.address': function() {
   //  				ncc.loadHarvestedBlocks('reload');
   //  			}
   //  		}));

   //  		local.intervalJobs.push(setInterval(function() {
			// 	ncc.loadHarvestedBlocks('update');
			// }, local.autoRefreshInterval));

   //  		var $win = ncc.global.$window;
			// var $doc = ncc.global.$document;
			// $win.on('scroll.harvestedBlocksInfiniteScrolling', function(event) {
			// 	if (!ncc.get('status.loadingOlderBlocks') && $win.scrollTop() + $win.height() >= $doc.height() - local.scrollBottomTolerance) {
			// 		ncc.loadHarvestedBlocks('append');
			// 	}
			// });
    	},
    	leave: [function() {
    		// ncc.global.$window.off('scroll.harvestedBlocksInfiniteScrolling');
    	}]
    });
});
