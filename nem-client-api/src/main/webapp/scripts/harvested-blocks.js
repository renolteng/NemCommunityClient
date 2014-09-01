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
					var lastTimeStamp = (currBlocks && currBlocks.length)? currBlocks[currBlocks.length - 1].timeStamp : null;
					if (lastTimeStamp) requestData.hash = lastTimeStamp;
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
                		ncc.set('harvestedBlocks.gotAll', updatedBlocks.length < ncc.consts.blocksPerPage);
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

    		var $win = $(window);
			var $doc = $(document);
			$win.on('scroll.harvestedBlocksInfiniteScrolling', function(event) {
				if (!ncc.get('status.loadingOlderBlocks') && $win.scrollTop() + $win.height() >= $doc.height() - local.scrollBottomTolerance) {
					ncc.loadHarvestedBlocks(false);
				}
			});
    	},
    	leave: [function() {
    		$(window).off('scroll.harvestedBlocksInfiniteScrolling');
    	}]
    });
});
