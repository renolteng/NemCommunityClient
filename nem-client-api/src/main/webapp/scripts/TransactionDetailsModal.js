"use strict";

define(['jquery', 'NccModal', 'Utils'], function($, NccModal, Utils) {
	return NccModal.extend({
	    onrender: function() {
            this.set('privateLabels', ncc.get('privateLabels'));

            this.viewAccount = ncc.viewAccount;

            // rather ugly way to make partials visible
            this.partials['transferTransactionPartial'] = $('transferTransactionPartial').html();
            this.partials['importanceTransactionPartial'] = $('importanceTransactionPartial').html();
            this.partials['aggregateModificationPartial'] = $('aggregateModificationPartial').html();

		}
	});
});