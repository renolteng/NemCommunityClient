"use strict";

define(['jquery', 'ractive', 'NccModal', 'Utils',
    'rv!layout/partialTransferTransaction', 'rv!layout/partialImportanceTransaction', 'rv!layout/partialAggregateModification'],
    function($, Ractive, NccModal, Utils,
        partialTransferTransaction, partialImportanceTransaction, partialAggregateModification )
{
	return NccModal.extend({
	    partials: {
            transferTransactionPartial: partialTransferTransaction,
            importanceTransactionPartial: partialImportanceTransaction,
            aggregateModificationPartial: partialAggregateModification
        },
	    onrender: function() {
            this.set('privateLabels', ncc.get('privateLabels'));

            this.viewAccount = ncc.viewAccount;
		}
	});
});