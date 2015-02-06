"use strict";

define(['jquery', 'ncc', 'NccLayout', 'Utils', 'ractive-events-tap'], function($, ncc, NccLayout, Utils) {
    return $.extend(true, {}, NccLayout, {
        name: 'address-book',
        url: 'address-book.html',
        template: 'rv!layout/address-book',
        parent: 'wallet',
        local: {
            scrollBottomTolerance: 100
        },
        initOnce: function() {
            ncc.on( 'sort', function (event, column) {
                this.set('contactsSortColumn', column);
                var name = 'contactsSort_'+column;
                this.set(name, this.get(name) ^ 1);
            });

            ncc.set('contactsSortColumn', 'privateLabel');
            ncc.set('contactsSort_privateLabel', 1);
            ncc.set('sortContacts',
                function (array, sortColumn) {
                    array = array.slice();

                    var order = this.get('contactsSort_'+sortColumn) === 1 ? 1 : -1;
                    return array.sort(function(a, b) {
                        return order*(a[sortColumn].toLowerCase().localeCompare(b[sortColumn].toLowerCase()));
                    });
                }
            );
         },
        setupEverytime: function() {
        },
        leave: [function() {}]
    });
});
