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

            // order is as follows:
            // 1. take owners of root namespaces
            // 2. take all namespaces owned by rootOwners
            // 3. take all mosaics from above namespaces
            $.getJSON(url, function(rootNamespaces) {
                var output = [];
                var ownerslist = [];
                var mosaicOutputs = [];
                for (var rootNs of rootNamespaces.data) {
                    //namespaces owned by root owners
                    // http://127.0.0.1:7890/namespace/mosaic/definition/page?namespace=jabo38_ltd
                    //http://127.0.0.1:7890/account/namespace/page?address=TBGIMRE4SBFRUJXMH7DVF2IBY36L2EDWZ37GVSC4

                    // do we already know this owner account?
                    var foundit = $.inArray(rootNs.namespace.owner, ownerslist);
                    if (foundit < 0) {
                        var url2 = remoteserver + '/account/namespace/page?address=' + rootNs.namespace.owner;
                        outputurls.push({
                            "url":url2
                        });

                        $.getJSON(url2, function(ownerNamespaces) {
                            for (var curNamespace of ownerNamespaces.data) {
                                //mosaics lookup
                                var url3 = remoteserver + '/namespace/mosaic/definition/page?namespace=' + curNamespace.fqn;
                                //alert(url3);
                                outputurls.push({
                                    "url":url3
                                });
                                $.getJSON(url3, function(ownerMosaics) {
                                    //console.log(curNamespace.fqn, data3);
                                    for (var curMosaic of ownerMosaics.data) {
                                        //console.log(curMosaic);

                                        /*
                                        creator: "994793ba1c789fa9bdea918afc9b06e2d0309beb1081ac5b6952991e4defd324"
                                        description: "relies on b"
                                        id: Object
                                            name: "d"
                                            namespaceId: "a"
                                        levy: Object
                                            fee: 5
                                            mosaicId: Object
                                                name: "b"
                                                namespaceId: "a"
                                            recipient: "TAOPATMADWFEPME6GHOJL477SI7D3UT6NFJN4LGB"
                                            type: 1
                                        properties: Array[4]
                                        */
                                        mosaicOutputs.push(curMosaic.mosaic);
                                    } //end mosaics lookup for
                                }); //end mosaics lookup

                                /*
                                owner: "TCACZPQQZ2R4SJNAAA4ZAUBEFFZGBTQKPNUDAGI5"
                                fqn: "jetstar"
                                height: 185747
                                */
                                output.push(curNamespace);
                            } //end namespaces owned by root owners for
                        }); //end namespaces owned by root owners
                    } //end check if we already know this owner.
                    ownerslist.push(rootNs.namespace.owner);
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