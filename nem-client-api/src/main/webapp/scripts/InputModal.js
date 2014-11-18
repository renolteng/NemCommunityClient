"use strict";

define(['NccModal', 'Utils'], function(NccModal, Utils) {
	return NccModal.extend({
        submit: function() {
            var submit = this.get('submitCb');
            var values = this.get('values');

            var closeNow = true;
            if (typeof submit === 'function') {
                closeNow = submit.call(this, values, this.closeModal.bind(this));
            }
            if (closeNow) {
                this.closeModal();
            }
        },
        onrender: function() {
            this._super();

            this.on({
                inputKeyup: function(e) {
                    if (e.original.keyCode === 13) {
                        this.submit();
                    }
                }
            });
        }
    });
});