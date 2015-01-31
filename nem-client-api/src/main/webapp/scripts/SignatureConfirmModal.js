"use strict";

define(['NccModal', 'Utils'], function(NccModal, Utils) {
	return NccModal.extend({
		confirm: function() {
            this.lockAction();

            var self = this;
            ncc.postRequest('wallet/account/signature/send', this.get('requestData'), function(data) {
                self.closeModal();
            	ncc.getModal('signMultisig').closeModal();

                ncc.showMessage(ncc.get('texts.modals.common.success'), ncc.get('texts.modals.sendNem.successMessage'));
                ncc.refreshInfo();
            },
            {
                complete: function() {
                    self.unlockAction();
                }
            });
		}
	});
});