"use strict";

define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
    return $.extend(true, {}, NccLayout, {
        name: 'address-book',
        url: 'address-book.html',
        template: 'rv!layout/address-book',
        parent: 'wallet',
        local: {
            scrollBottomTolerance: 100
        },
        initOnce: function() {
            ncc.addContact = function() {
                ncc.showInputForm(ncc.get('texts.modals.startRemote.title'), '',
                    [
                        {
                            name: '',
                            type: 'text',
                            readonly: true,
                            unimportant: true,
                            label: {
                                content: ncc.get('texts.modals.startRemote.wallet')
                            }
                        },
                    ]
                );
            }
        },
        setupEverytime: function() {},
        leave: [function() {}]
    });
});
