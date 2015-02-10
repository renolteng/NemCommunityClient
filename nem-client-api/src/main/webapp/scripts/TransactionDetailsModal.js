"use strict";

define(['NccModal', 'Utils'], function(NccModal, Utils) {
	return NccModal.extend({
		onrender: function() {
		    this.set('privateLabels', ncc.get('privateLabels'));

            this.viewAccount = ncc.viewAccount;
		}
	});
});