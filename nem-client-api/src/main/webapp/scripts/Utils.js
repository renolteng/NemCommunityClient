"use strict";

define(function() {
	return {
        restoreAddress: function(address) {
            return address.replace(/\-/g, '');
        },
        formatAddress: function(address) {
            if (address && typeof address === 'string') {
                return address.match(/.{1,6}/g).join('-').toUpperCase();
            } else {
                return address;
            }
        },
        toggleOn: function(instance, id) {
            var keypath = 'active.' + id;
            if (!instance.get(keypath)) {
                instance.set(keypath, true);
                var firstTime = true;
                var self = this;
                $(document).on('click.' + id, function(ev) {
                    if (firstTime) {
                        firstTime = false;
                        return;
                    }
                    self.toggleOff(instance, id);
                });
            }
        },
        toggleOff: function(instance, id) {
            instance.set('active.' + id, false);
            $(document).off('click.' + id);
        }
    };
});