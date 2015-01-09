"use strict";

define(['jquery', 'ncc', 'NccLayout', 'Utils'], function($, ncc, NccLayout, Utils) {
    return $.extend(true, {}, NccLayout, {
        name: 'address-book',
        url: 'address-book.html',
        template: 'rv!layout/address-book',
        parent: 'wallet',
        local: {
            scrollBottomTolerance: 100
        },
        initOnce: function() {
            ncc.addContact = function(address) {
                ncc.showInputForm(ncc.get('texts.modals.addContact.title'), '',
                    [
                        {
                            name: 'addressBook',
                            type: 'text',
                            readonly: true,
                            unimportant: true,
                            label: {
                                content: ncc.get('texts.modals.addContact.addressBook')
                            }
                        },
                        {
                            name: 'password',
                            type: 'password',
                            label: {
                                content: ncc.get('texts.modals.addContact.password')
                            }
                        },
                        {
                            name: 'address',
                            type: 'text',
                            label: {
                                content: ncc.get('texts.modals.addContact.address')
                            }
                        },
                        {
                            name: 'privateLabel',
                            type: 'text',
                            label: {
                                content: ncc.get('texts.modals.addContact.privateLabel')
                            }
                        },
                        {
                            name: 'publicLabel',
                            type: 'text',
                            label: {
                                content: ncc.get('texts.modals.addContact.publicLabel')
                            }
                        },
                    ],
                    {
                        addressBook: ncc.get('wallet.wallet'),
                        address: address
                    },
                    function(values, closeModal) {
                        values.address = Utils.format.address.restore(values.address);
                        ncc.postRequest('addressbook/accountlabel/add', values, function(data) {
                            closeModal();
                            ncc.refreshAddressBook();
                        });
                    },
                    ncc.get('texts.modals.addContact.add')
                );
            }
        },
        setupEverytime: function() {},
        leave: [function() {}]
    });
});
