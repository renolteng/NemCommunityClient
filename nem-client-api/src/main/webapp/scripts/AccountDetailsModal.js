"use strict";

define(['NccModal', 'Utils'], function(NccModal, Utils) {
	return NccModal.extend({
		computed: {
			formattedAddress: function() {
				return Utils.format.address.format(this.get('account.address'));
			},
			formattedBalance: function() {
				return Utils.format.nem.formatNemAmount(this.get('account.balance'));
			},
			formattedVestedBalance: function() {
                return Utils.format.nem.formatNemAmount(this.get('account.vestedBalance'));
            }
		},
		addContact: function(address) {
			ncc.addContact(address);
		},
		removeContact: function(address) {
			ncc.removeContact(address);
		}
	});
});