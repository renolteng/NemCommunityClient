"use strict";

define(function() {
    var TransactionType = {
        Transfer: 1
        , Importance_Transfer: 2
        , Aggregate_Modification: 3
        , Multisig_Transfer: 4
        , Multisig_Signature: 5
    };

    return TransactionType;
});
