"use strict";

define(['NccModal', 'Utils',
    'rv!layout/partialTransferTransaction',
    'rv!layout/partialImportanceTransaction',
    'rv!layout/partialAggregateModification',
    'rv!layout/partialProvisionNamespace'],
    function(NccModal, Utils,
        partialTransferTransaction,
        partialImportanceTransaction,
        partialAggregateModification,
        partialProvisionNamespace)
{
	return NccModal.extend({
	    partials: {
            transferTransactionPartial: partialTransferTransaction,
            importanceTransactionPartial: partialImportanceTransaction,
            aggregateModificationPartial: partialAggregateModification,
            provisionNamespacePartial: partialProvisionNamespace,
        },
	    onrender: function() {
            this.set('privateLabels', ncc.get('privateLabels'));

            this.viewAccount = ncc.viewAccount;
		}
	});
});