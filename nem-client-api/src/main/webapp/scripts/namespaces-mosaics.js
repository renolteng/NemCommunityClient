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

            //namespace root
            var url = remoteserver + '/namespace/root/page?';
            var outputurls = [];
            ncc.set('namespaces.all', null);
            ncc.set('namespaces.urls', null);
            outputurls.push({
                "url":url
            });
            $.getJSON(url, function(data) {
                var output = [];
                var ownerslist = [];
                var mosaicOutputs = [];
                for (var i in data.data) {
                    //namespaces owned by root owners
                    // http://127.0.0.1:7890/namespace/mosaic/definition/page?namespace=jabo38_ltd
                    //http://127.0.0.1:7890/account/namespace/page?address=TBGIMRE4SBFRUJXMH7DVF2IBY36L2EDWZ37GVSC4

                    // do we already know this owner account?
                    var foundit = $.inArray(data.data[i].namespace.owner, ownerslist);
                    if (foundit < 0) {
                        var url2 = remoteserver + '/account/namespace/page?address=' + data.data[i].namespace.owner;
                        outputurls.push({
                            "url":url2
                        });

                        $.getJSON(url2, function(data2) {
                            for (var i2 in data2.data) {
                                //alert(data2.data[ii].mosaic.id.name);
                                //{"owner":"TBGIMRE4SBFRUJXMH7DVF2IBY36L2EDWZ37GVSC4","fqn":"a","height":189256}
                                //"fqn":data2.data[i2].fqn,

                                //mosaics lookup
                                var url3 = remoteserver + '/namespace/mosaic/definition/page?namespace=' + data2.data[i2].fqn;
                                //alert(url3);
                                outputurls.push({
                                    "url":url3
                                });
                                $.getJSON(url3, function(data3) {
                                    //console.log(data2.data[i2].fqn, data3);
                                    for (var i3 in data3.data) {
                                        //alert(data2.data[ii].mosaic.id.name);

                                        mosaicOutputs.push({
                                            "namespaceId":data3.data[i3].mosaic.id.namespaceId,
                                            "name":data3.data[i3].mosaic.id.name,
                                            "description":data3.data[i3].mosaic.description
                                        });
                                    } //end mosaics lookup for
                                }); //end mosaics lookup

                                //alert("fqn" + data2.data[i2].fqn +
                                //    "owner" + data2.data[i2].owner +
                                //    "height" +data2.data[i2].height);

                                output.push({
                                    "fqn":data2.data[i2].fqn,
                                    "owner":data2.data[i2].owner,
                                    "height":data2.data[i2].height,
                                });
                            } //end namespaces owned by root owners for
                        }); //end namespaces owned by root owners
                    } //end check if we already know this owner.
                    ownerslist.push(data.data[i].namespace.owner);
                } //end namespace root for

                ncc.set('namespaces.all', output);
                ncc.set('mosaics.all', mosaicOutputs);
                ncc.set('namespaces.urls', outputurls);
            }); //end namespace root


            //owned mosaics
            //http://127.0.0.1:7890/account/mosaic/owned?address=TD3RXTHBLK6J3UD2BH2PXSOFLPWZOTR34WCG4HXH
            var currAccount = ncc.get('activeAccount.address');
            var url4 = remoteserver + '/account/mosaic/owned?address=' + currAccount;
            //alert(url3);
            var mosaicOwnedOutputs = [];
            $.getJSON(url4, function(data4) {
                for (var i4 in data4.data) {
                    //alert(JSON.stringify(data4));
                    //alert(data2.data[ii].mosaic.id.name);
                    mosaicOwnedOutputs.push({
                        "quantity":data4.data[i4].quantity,
                        "namespaceId":data4.data[i4].mosaicId.namespaceId,
                        "name":data4.data[i4].mosaicId.name,
                    });
                    //{"data":[{"quantity":1099976000000,"mosaicId":{"namespaceId":"nem","name":"xem"}},{"quantity":226,"mosaicId":{"namespaceId":"gimre.games.pong","name":"paddles"}}]}
                } //end owned mosaics for
            }); //end owned mosaics

            ncc.set('mosaics.owned', mosaicOwnedOutputs);
        },
    leave: [function() {}]
    });
});