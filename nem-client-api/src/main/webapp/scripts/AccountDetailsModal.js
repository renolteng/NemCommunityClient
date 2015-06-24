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
		},
		signToken: function(address) {
		    ncc.signToken(address);
		},
        showPrivateKey: function(address) {
            ncc.showKey(address, 'texts.modals.showPrivateKey.title', 'texts.modals.showPrivateKey.message', 'wallet/account/reveal');
        },
        showRemotePrivateKey: function(address) {
            ncc.showKey(address, 'texts.modals.showRemotePrivateKey.title', 'texts.modals.showRemotePrivateKey.message', 'wallet/account/remote/reveal');
        },
		onrender: function() {
		    // not sure why, but new ractive started complaining about it,
		    // also seems now it's not needed o0
            //this.set('privateLabels', ncc.get('privateLabels'));
            this.viewAccount = ncc.viewAccount;
        }
	});
});