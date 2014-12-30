"use strict";

define(['NccModal', 'Utils'], function(NccModal, Utils) {
	return NccModal.extend({
        computed: {
            formattedMultisigAccount: {
                get: function() {
                    return Utils.format.address.format(this.get('multisigAccount'));
                },
                set: function(address) {
                    this.set('multisigAccount', Utils.format.address.restore(address));
                }
            }
        },
		addCosignatory: function() {
            $('.js-cosignatory').last().focus();
            this.get('cosignatories').push({});
            $('.js-cosignatory').last().focus();
        },
        removeCosignatory: function(index) {
            this.get('cosignatories').splice(index, 1);
        },
        resetDefaultData: function() {
        	this.set('cosignatories', [{}]);
            this.set('multisigAccount', ncc.get('activeAccount.address'));
            this.set('useMinimumFee', true);
        },
        onrender: function() {
            this.on('modalOpened', function() {
                this.resetDefaultData();
            });
        }
	});
});