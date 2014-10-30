"use strict";

define(['jquery', 'ractive', 'Utils'], function($, Ractive, Utils) {
	return Ractive.extend({
        template: '#modal-template',
        isolated: true,
        open: function() {
            this.set('isActive', true);
            $('.active.modal-container').focus().find('.modal').css({
                left: 'auto',
                top: 'auto'
            });
            this.fire('modalOpened');
        },
        closeModal: function() {
            this.set('isActive', false);
            this.fire('modalClosed');
        },
        toggleOn: function(id) {
            Utils.toggleOn(this, id);
        },
        toggleOff: function(id) {
            Utils.toggleOff(this, id);
        },
        oncomplete: function() {
            this.on({
                modalContainerClick: function(e) {
                    if (e.node === e.original.target) { //clicked outside modal
                        this.closeModal();
                    }
                },
                modalContainerKeyup: function(e) {
                    if (e.original.keyCode === 27 || (this.get('closeOnEnter') && e.original.keyCode === 13)) {
                        this.closeModal();
                    }
                }
            });
        }
    });
});