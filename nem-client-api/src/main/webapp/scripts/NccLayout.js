"use strict";

 define(['jquery', 'ncc'], function($, ncc) {
 	return {
 		local: {
            listeners: [],
            intervalJobs: [],
            timeoutJobs: [],
            autoRefreshInterval: 30000
        },
        leave: [
	        function() {},
	        function() {
	        	$.each(this.local.listeners, function() {
					this.cancel();
				});
				this.local.listeners = [];

				$.each(this.local.intervalJobs, function() {
					clearInterval(this);
				});
				this.local.intervalJobs = [];

				$.each(this.local.timeoutJobs, function() {
					clearInterval(this);
				});
				this.local.timeoutJobs = [];
        	}
        ]
 	};
 });