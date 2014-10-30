"use strict";

 define(['jquery', 'ncc', 'NccLayout', 'Utils'], function($, ncc, NccLayout, Utils) {
 	return $.extend(true, {}, NccLayout, {
 		name: 'start',
 		url: 'install.html',
		template: 'rv!layout/start',
		setupEverytime: function() {
 			ncc.getRequest('shutdown', null, {
				error: function(jqXHR, textStatus, errorThrown) {
					if (jqXHR.status !== 200) {
						ncc.showError(textStatus, errorThrown);
					}
		        }
			});
		}
 	});
});