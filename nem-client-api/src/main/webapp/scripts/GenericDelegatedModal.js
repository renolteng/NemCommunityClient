"use strict";

define(['NccModal', 'Utils', 'TransactionType', 'handlebars', 'typeahead'], function(NccModal, Utils, TransactionType, Handlebars) {
	return NccModal.extend({
        data: {
            isFeeAutofilled: true,
            dueBy: 1
        },
        computed: {
            hoursDue: function() {
                return this.get('dueBy') | 0;
            },
            fee: {
                get: function() {
                    return Utils.format.nem.getNemValue(Utils.format.nem.stringToNem(this.get('formattedFee')));
                },
                set: function(fee) {
                    this.set('formattedFee', Utils.format.nem.formatNemAmount(fee));
                    this.update('fee'); // so that stupid Ractive trigger fee observers
                }
            },
            multisigFee: {
                get: function() {
                    return Utils.format.nem.getNemValue(Utils.format.nem.stringToNem(this.get('formattedMultisigFee')));
                },
                set: function(fee) {
                    this.set('formattedMultisigFee', Utils.format.nem.formatNemAmount(fee));
                    this.update('multisigFee'); // so that stupid Ractive trigger fee observers
                }
            },
            feeValid: function() {
                return this.get('fee') >= this.get('minimumFee');
            },
            feeError: function() {
                return !this.get('feeValid') && this.get('feeChanged');
            },
            formattedMinimumFee: function() {
                return Utils.format.nem.formatNemAmount(this.get('minimumFee'));
            },
            passwordValid: function() {
                return !!this.get('password');
            },
            passwordError: function() {
                return !this.get('passwordValid') && this.get('passwordChanged');
            },
            formValid: function() {
                return this.get('feeValid') && this.get('passwordValid');
            }
        },
        resetDefaultData: function() {
            var activeAddress = ncc.get('activeAccount').address;
            var wallet = ncc.get('wallet');
            var walletAccount = null;
            if (wallet.primaryAccount.address === activeAddress) {
                walletAccount = wallet.primaryAccount;
            } else {
                for (var i = 0; i < wallet.otherAccounts.length; ++i) {
                    if (wallet.otherAccounts[i].address == activeAddress) {
                        walletAccount = wallet.otherAccounts[i];
                        break;
                    }
                }
            }
            this.set('sender', null);
            this.set('remote', {
                publicKey: walletAccount.remotePublicKey,
                address: walletAccount.remoteAddress,
                formattedAddress: walletAccount.formattedRemoteAddress,
            });
            this.set('fee', 0);
            this.set('multisigFee', 0);
            this.set('minimumFee', 0);
            this.set('dueBy', '1');
            this.set('password', '');
            this.set('useMinimumFee', true);
            this.set('signatories', [{}]);

            this.set('privateLabels', ncc.get('privateLabels'));
            this.set('multisigAccounts', ncc.get('activeAccount').multisigAccounts);
            this.set('fullBalance', Utils.format.nem.formatNemAmount(ncc.get('activeAccount').balance, {keepTrailingZeroes:true}));
            this.set('feeChanged', false);
            this.set('passwordChanged', false);
            this.resetFee({ silent: true });
        },
        /**
         * @param {boolean} options.silent
         */
        resetFee: function(options) {
            var requestData = {
                wallet: ncc.get('wallet.wallet'),
                account: ncc.get('activeAccount.address'),
                hoursDue: this.get('hoursDue')
            };
            var self = this;

            ncc.postRequest('wallet/account/remote/validate', requestData,
                function(data) {
                    self.set('minimumFee', data.fee);
                    self.set('multisigFee', data.multisigFee);
                },
                {},
                options.silent
            );
        },
        sendTransaction: function() {
            if (this.get('sender') == null) {
                var requestData = {
                    wallet: ncc.get('wallet.wallet'),
                    type: TransactionType.Importance_Transfer,
                    account: ncc.get('activeAccount.address'),
                    password: this.get('password'),
                    publicKey: this.get('remote.publicKey'),
                    fee: this.get('fee'),
                    multisigFee: 0,
                    hoursDue: this.get('hoursDue')
                };
            } else {
                var requestData = {
                    wallet: ncc.get('wallet.wallet'),
                    type: TransactionType.Multisig_Importance_Transfer,
                    multisigAccount: this.get('sender'),
                    account: ncc.get('activeAccount.address'),
                    password: this.get('password'),
                    publicKey: this.get('remote.publicKey'),
                    multisigFee: this.get('multisigFee'),
                    hoursDue: this.get('hoursDue')
                };
            }
            var txConfirm = ncc.getModal('genericDelegatedConfirm');
            txConfirm.set('TransactionType', TransactionType);
            txConfirm.set('txData', this.get());
            txConfirm.set('requestData', requestData);
            txConfirm.open();
        },
        onrender: function() {
            this._super();
            var self = this;

            this.resetDefaultData();

            this.observe({
                useMinimumFee: function(useMinimumFee) {
                    if (useMinimumFee) {
                        this.set('fee', this.get('minimumFee'));
                    }
                },
                minimumFee: function(minimumFee) {
                    if (this.get('useMinimumFee')) {
                        this.set('fee', minimumFee);
                    }
                }
            });

            this.observe({
                fee: function() {
                    this.set('feeChanged', true);
                },
                password: function() {
                    this.set('passwordChanged', true);
                }
            },
            {
                init: false
            });

            this.on({
                sendFormKeypress: function(e) {
                    if (e.original.keyCode === 13 && this.get('formValid')) {
                        this.sendTransaction();
                    }
                },
                modalOpened: function() {
                    $('.js-sendNem-recipient-textbox').focus();
                    this.resetDefaultData();
                },
                modalClosed: function() {
                    this.resetDefaultData();
                }
            });

            var $dueBy = $('.js-sendNem-dueBy-textbox');
            $dueBy.on('keypress', function(e) { Utils.mask.keypress(e, 'number', self) });

            var $fee = $('.js-sendNem-fee-textbox');
            var feeTxb = $fee[0];
            $fee.on('keypress', function(e) { Utils.mask.keypress(e, 'nem', self); });
            $fee.on('paste', function(e) { Utils.mask.paste(e, 'nem', self); });
            $fee.on('keydown', function(e) { Utils.mask.keydown(e, 'nem', self); });
        }
    });
});