"use strict";

define(['NccModal', 'Utils', 'TransactionType'], function(NccModal, Utils, TransactionType) {
	return NccModal.extend({
        data: {
            isFeeAutofilled: true,
            dueBy: 12
        },
        computed: {
            amount: function() {
                return Utils.format.nem.getNemValue(Utils.format.nem.stringToNem(this.get('formattedAmount')));
            },
            recipient: function() {
                return Utils.format.address.restore(this.get('formattedRecipient'));
            },
            recipientValid: function() {
                var recipient = this.get('recipient');
                return !!recipient && recipient.length === 40;
            },
            recipientError: function() {
                return !this.get('recipientValid') && this.get('recipientChanged');
            },
            message: function() {
                return this.get('rawMessage') && this.get('rawMessage').toString();
            },
            encrypt: function() {
                return this.get('encryptionPossible') ? (this.get('encrypted') ? 1 : 0) : 0;
            },
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
                return this.get('recipientValid') && this.get('feeValid') && this.get('passwordValid');
            }
        },
        validateTx: function() {
            if (!this.get('recipientValid')) return false;
            return true;
        },
        resetDefaultData: function() {
            this.set('formattedAmount', '0');
            this.set('formattedRecipient', '');
            this.set('rawMessage', '');
            this.set('encrypted', false);
            this.set('fee', 0);
            this.set('multisigFee', 0);
            this.set('minimumFee', 0);
            this.set('dueBy', '12');
            this.set('password', '');
            this.set('useMinimumFee', true);
            this.set('signatories', [{}]);

            this.set('cosignatories', ncc.get('activeAccount').cosignatoryOf);
            this.set('recipientChanged', false);
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
                amount: this.get('amount'),
                message: this.get('message'),
                encrypt: this.get('encrypt'),
                recipient: this.get('recipientValid') ? this.get('recipient') : ncc.get('activeAccount.address'),
                hoursDue: this.get('hoursDue')
            };
            var self = this;
            
            ncc.postRequest('wallet/account/transaction/validate', requestData, 
                function(data) {
                    self.set('minimumFee', data.fee);
                    self.set('multisigFee', data.multisigFee);
                    if (self.get('sender') == null) {
                        self.set('encryptionPossible', data.encryptionSupported && self.get('recipientValid'));
                    } else {
                        self.set('encryptionPossible', false);
                    }
                },
                {
                    altFailCb: function(faultId, error) {
                        if (faultId === 202) { // request encrypting while recipient unable to encrypt
                            self.set('encrypted', false); // this will automatically trigger resetFee
                            return true;
                        }
                    }
                }, options.silent
            );
        },
        sendTransaction: function() {
            if (this.get('sender') == null) {
                var requestData = {
                    wallet: ncc.get('wallet.wallet'),
                    type: TransactionType.Transfer,
                    account: ncc.get('activeAccount.address'),
                    password: this.get('password'),
                    amount: this.get('amount'),
                    recipient: this.get('recipient'),
                    message: this.get('message'),
                    fee: this.get('fee'),
                    multisigFee: 0,
                    encrypt: this.get('encrypt'),
                    hoursDue: this.get('hoursDue')
                };
            } else {
                var requestData = {
                    wallet: ncc.get('wallet.wallet'),
                    type: TransactionType.Multisig_Transfer,
                    multisigAccount: this.get('sender'),
                    account: ncc.get('activeAccount.address'),
                    password: this.get('password'),
                    amount: this.get('amount'),
                    recipient: this.get('recipient'),
                    message: this.get('message'),
                    fee: this.get('fee'),
                    multisigFee: this.get('multisigFee'),
                    encrypt: this.get('encrypt'),
                    hoursDue: this.get('hoursDue')
                };
            }

            var txConfirm = ncc.getModal('transactionConfirm');
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
                encryptionPossible: function(encryptionPossible) {
                    if (!encryptionPossible) {
                        this.set('encrypted', false);
                    }
                },
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
                'amount recipient message encrypt': (function() {
                    var t;
                    return function() {
                        clearTimeout(t);
                        t = setTimeout(function() {
                            self.resetFee({ silent: true });
                        }, 500);
                    }
                })(),
                recipient: function(recipient) {
                    this.set('recipientChanged', true);
                    self.set('recipientLabel', ncc.get('privateLabels')[recipient]);
                },
                fee: function() {
                    this.set('feeChanged', true);
                },
                password: function() {
                    this.set('passwordChanged', true);
                },
                sender: function(senderData) {
                    if (senderData != null) {
                        this.set('encryptionPossible', false);
                    }
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
                    // TODO G-Krysto: this should be here not in modalClosed, or not?
                    this.resetDefaultData();
                },
                modalClosed: function() {
                    this.resetDefaultData();
                }
            });

            var $dueBy = $('.js-sendNem-dueBy-textbox');
            $dueBy.on('keypress', function(e) { Utils.mask.keypress(e, 'number', self) });

            var $recipient = $('.js-sendNem-recipient-textbox');
            $recipient.on('keypress', function(e) { Utils.mask.keypress(e, 'address', self); });
            $recipient.on('paste', function(e) { Utils.mask.paste(e, 'address', self); });
            $recipient.on('keydown', function(e) { Utils.mask.keydown(e, 'address', self); });

            var $amount = $('.js-sendNem-amount-textbox');
            var amountTxb = $amount[0];
            $amount.on('keypress', function(e) { Utils.mask.keypress(e, 'nem', self); });
            $amount.on('paste', function(e) { Utils.mask.paste(e, 'nem', self); });
            $amount.on('keydown', function(e) { Utils.mask.keydown(e, 'nem', self); });

            var $fee = $('.js-sendNem-fee-textbox');
            var feeTxb = $fee[0];
            $fee.on('keypress', function(e) { Utils.mask.keypress(e, 'nem', self); });
            $fee.on('paste', function(e) { Utils.mask.paste(e, 'nem', self); });
            $fee.on('keydown', function(e) { Utils.mask.keydown(e, 'nem', self); });

            this.listeners.push(ncc.observe({
                'texts.preferences.thousandSeparator': function(newProp, oldProp) {
                    amountTxb.value = Utils.format.nem.reformat(amountTxb.value, oldProp, newProp);
                    feeTxb.value = Utils.format.nem.reformat(feeTxb.value, oldProp, newProp);
                },
                'texts.preferences.decimalSeparator': function(newProp, oldProp) {
                    amountTxb.value = Utils.format.nem.reformat(amountTxb.value, null, null, oldProp, newProp);
                    feeTxb.value = Utils.format.nem.reformat(feeTxb.value, null, null, oldProp, newProp);
                }
            }));
        }
    });
});