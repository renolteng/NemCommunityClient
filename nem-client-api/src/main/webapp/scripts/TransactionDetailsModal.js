"use strict";

define(['NccModal', 'Utils'], function(NccModal, Utils) {
	return NccModal.extend({
		onrender: function() {
            this.viewAccount = ncc.viewAccount;
		}
	});
});