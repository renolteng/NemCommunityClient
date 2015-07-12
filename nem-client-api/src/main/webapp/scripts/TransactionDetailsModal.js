"use strict";

define(['NccModal', 'Utils',
    'rv!layout/partialTransferTransaction',
    'rv!layout/partialImportanceTransaction',
    'rv!layout/partialAggregateModification',
    'rv!layout/partialProvisionNamespace',
    'rv!layout/partialMosaicCreation'],
    function(NccModal, Utils,
        partialTransferTransaction,
        partialImportanceTransaction,
        partialAggregateModification,
        partialProvisionNamespace,
        partialMosaicCreation)
{
	return NccModal.extend({
	    partials: {
            transferTransactionPartial: partialTransferTransaction,
            importanceTransactionPartial: partialImportanceTransaction,
            aggregateModificationPartial: partialAggregateModification,
            provisionNamespacePartial: partialProvisionNamespace,
            mosaicCreationPartial: partialMosaicCreation
        },
	    onrender: function() {
            this.set('privateLabels', ncc.get('privateLabels'));

            this.viewAccount = ncc.viewAccount;
		}
	});
});