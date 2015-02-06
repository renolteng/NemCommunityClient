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
                console.log( 'Will sort by ' + column );
            });

            ncc.set('contactsSortOrder', { 'formattedAddress': -1, 'privateLabel': -1 });
            ncc.set('sortContacts',
                function (array, sortColumn) {
                    array = array.slice(); // clone, so we don't modify the underlying data
                    ncc.get('contactsSortOrder')[sortColumn] = -ncc.get('contactsSortOrder')[sortColumn];

                    return array.sort(function(a, b) {
                        return ncc.get('contactsSortOrder')[sortColumn] * (a[sortColumn] < b[sortColumn] ? -1 : 1);
                    });
                }
            );
         },
        setupEverytime: function() {
        },
        leave: [function() {}]
    });
});
