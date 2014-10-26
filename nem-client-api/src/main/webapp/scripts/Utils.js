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
        }
    };
});