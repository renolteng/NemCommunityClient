"use strict";

 define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
    return $.extend(true, {}, NccLayout, {
        name: 'harvested-blocks',
        url: 'harvested-blocks.html',
        template: 'rv!layout/harvested-blocks',
        parent: 'wallet',
        local: {
        	scrollBottomTolerance: 100
        },
        initOnce: function() {
        	ncc.loadHarvestedBlocks = function(reload, update) {
        		var currAccount = ncc.get('activeAccount.address');
        		var requestData = { account: currAccount };
        		var currBlocks;
        		if (!reload && !update) {
					currBlocks = ncc.get('harvestedBlocks.list');
					requestData.hash = (currBlocks && currBlocks.length) ? currBlocks[currBlocks.length - 1].hash : undefined;
				}

				ncc.postRequest('account/harvests', requestData, function(data) {
					var updatedBlocks = ncc.processHarvestedBlocks(data.data);
					var all;
					if (!reload && currBlocks && currBlocks.concat) {
						all = currBlocks.concat(updatedBlocks);
					} else if (update) {
						all = ncc.updateNewer(updatedBlocks, currBlocks, 'hash');
					} else {
						all = updatedBlocks;
					}

					ncc.set('harvestedBlocks.list', all);
                	if (!update) {
                        var gotAll = updatedBlocks.length < ncc.consts.blocksPerPage;
                		ncc.set('harvestedBlocks.gotAll', gotAll);
                        if (gotAll) {
                            ncc.global.$window.off('scroll.harvestedBlocksInfiniteScrolling');
                        }
                	}
				}, null, update);
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
    			'activeAccount.address': function() {
    				ncc.loadHarvestedBlocks(true);
    			}
    		}));

    		local.intervalJobs.push(setInterval(function() {
				ncc.loadHarvestedBlocks(false, true);
			}, local.autoRefreshInterval));

    		var $win = ncc.global.$window;
			var $doc = ncc.global.$document;
			$win.on('scroll.harvestedBlocksInfiniteScrolling', function(event) {
				if (!ncc.get('status.loadingOlderBlocks') && $win.scrollTop() + $win.height() >= $doc.height() - local.scrollBottomTolerance) {
					ncc.loadHarvestedBlocks(false);
				}
			});
    	},
    	leave: [function() {
    		ncc.global.$window.off('scroll.harvestedBlocksInfiniteScrolling');
    	}]
    });
});
