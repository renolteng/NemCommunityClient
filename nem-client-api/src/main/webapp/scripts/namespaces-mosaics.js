"use strict";

 define(['jquery', 'ncc', 'NccLayout', 'Utils'], function($, ncc, NccLayout, Utils) {
    return $.extend(true, {}, NccLayout, {
        name: 'namespacesmosaics',
        url: 'namespaces-mosaics.html',
        template: 'rv!layout/namespaces-mosaics',
        parent: 'wallet',
        local: {
        	scrollBottomTolerance: 100
        },
        initOnce: function() {
        	/**
        	 * @param {string} type load type: 'reload' | 'update' | 'append', default is 'reload'
        	 */
			
		},
    	setupEverytime: function() {
            
            
    		var remoteserver = ncc.get('settings.remoteServer.protocol') + "://" + ncc.get('settings.remoteServer.host') + ":" + ncc.get('settings.remoteServer.port');
                        //alert(remoteserver);
            var url = remoteserver + '/namespace/root/page?';
                        //alert(url);
            ncc.set('namespaces.all', null);
            $.getJSON(url, function(data) {
                var output = [];
                for (var i in data.data) {
        	
         
            
       

// http://127.0.0.1:7890/namespace/mosaic/definition/page?namespace=jabo38_ltd
            
                        var url2 = remoteserver + '/namespace/mosaic/definition/page?namespace=' + data.data[i].namespace.fqn;
                        //alert(url2);
                        var mosaicOutputs = [];
                         $.getJSON(url2, function(data2) {
        for (var ii in data2.data) {
        	//alert(data2.data[ii].mosaic.id.name);
        	 
           mosaicOutputs.push({
    "namespaceId":data2.data[ii].mosaic.id.namespaceId,
    "name":data2.data[ii].mosaic.id.name,
    "description":data2.data[ii].mosaic.description
  							});
        									}
        									 });
        

   output.push({
    "fqn":data.data[i].namespace.fqn,
    "owner":data.data[i].namespace.owner,
    "height":data.data[i].namespace.height,
  });
         }


ncc.set('mosaics.all', mosaicOutputs);
        ncc.set('namespaces.all', output);
        //alert(JSON.stringify(mosaicOutputs));
        //alert(JSON.stringify(output));
  });
    	},
    	leave: [function() {}]
    });
});