"use strict";

 define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
    return $.extend(true, {}, NccLayout, {
        name: 'settings',
        url: 'settings.html',
        template: 'rv!layout/settings',
        parent: 'wallet',
    	setupEverytime: function() {
    		var local = this.local;
    		
    		local.listeners.push(ncc.on({
    			saveSettings: function() {
    				ncc.postRequest('configuration/update', ncc.get('settings'), function(data) {
    					if (data.ok) {
    						ncc.showMessage(ncc.get('texts.common.success'), ncc.get('texts.settings.saveSuccess'));
    					}
    				});
    			}
    		}));
    	}
    });
});
