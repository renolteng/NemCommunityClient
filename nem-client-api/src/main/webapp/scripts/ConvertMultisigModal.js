"use strict";

define(['NccModal', 'Utils'], function(NccModal, Utils) {
	return NccModal.extend({
        computed: {
            multisigAccount: function() {
                return ncc.get('activeAccount.address');
            },
            formattedMultisigAccount: {
                get: function() {
                    return ncc.get('activeAccount.formattedAddress');
                },
                set: function() {}
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
            this.set('useMinimumFee', true);
        },
        onrender: function() {
            this.on('modalOpened', function() {
                this.resetDefaultData();
            });
        }
	});
});