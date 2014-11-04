"use strict";

define(['NccModal', 'Utils'], function(NccModal, Utils) {
	return NccModal.extend({
        confirm: function(action) {
            var callbacks = this.get('callbacks');

            var dontClose = false;
            if (callbacks && callbacks[action]) {
                dontClose = callbacks[action].call(this);
            }
            if (!dontClose) {
                this.closeModal();
            }
        }
    });
});