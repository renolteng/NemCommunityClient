"use strict";

define(['NccModal', 'Utils',
    'rv!layout/partialTransferTransaction',
    'rv!layout/partialImportanceTransaction',
    'rv!layout/partialAggregateModification',
    'rv!layout/partialProvisionNamespace',
    'rv!layout/partialMosaicCreation',
    'rv!layout/partialMosaicSupply'],
    function(NccModal, Utils,
        partialTransferTransaction,
        partialImportanceTransaction,
        partialAggregateModification,
        partialProvisionNamespace,
        partialMosaicCreation,
        partialMosaicSupply)
{
	return NccModal.extend({
	    partials: {
            transferTransactionPartial: partialTransferTransaction,
            importanceTransactionPartial: partialImportanceTransaction,
            aggregateModificationPartial: partialAggregateModification,
            provisionNamespacePartial: partialProvisionNamespace,
            mosaicCreationPartial: partialMosaicCreation,
            mosaicSupplyPartial: partialMosaicSupply
        },
	    onrender: function() {
            this.set('fqn', ncc.get('fqn'));
            var mosaicOutputs = [];
            //alert(fqn);
            //alert(this.fqn);
             //mosaics lookup 
               var remoteserver = ncc.get('settings.remoteServer.protocol') + "://" + ncc.get('settings.remoteServer.host') + ":" + ncc.get('settings.remoteServer.port');
                        //alert(remoteserver);


            var url3 = remoteserver + '/namespace/mosaic/definition/page?namespace=' + this.fqn;
                        //alert(url3);
                       
                         $.getJSON(url3, function(data3) {
        for (var i3 in data3.data) {
            //alert(data2.data[ii].mosaic.id.name);
             
           mosaicOutputs.push({
    "namespaceId":data3.data[i3].mosaic.id.namespaceId,
    "name":data3.data[i3].mosaic.id.name,
    "description":data3.data[i3].mosaic.description
                            });
                                            } //end mosaics lookup for
                                             }); //end mosaics lookup
                          ncc.set('mosaics.all', mosaicOutputs);

            this.viewAccount = ncc.viewAccount;
		}
	});
});