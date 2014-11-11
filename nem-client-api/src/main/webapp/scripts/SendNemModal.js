"use strict";

define(['NccModal', 'Utils'], function(NccModal, Utils) {
	return NccModal.extend({
        data: {
            isFeeAutofilled: true,
            dueBy: 12
        },
        computed: {
            amount: function() {
                return Utils.toMNem(Utils.convertCurrencyToStandard(this.get('formattedAmount')));
            },
            recipient: function() {
                return Utils.restoreAddress(this.get('formattedRecipient'));
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
            hours_due: function() {
                return this.get('dueBy') | 0;
            },
            fee: {
                get: function() {
                    return Utils.toMNem(Utils.convertCurrencyToStandard(this.get('formattedFee')));
                },
                set: function(fee) {
                    this.set('formattedFee', Utils.formatCurrency(fee, false, true, true));
                    this.update('fee'); // if not, stupid Ractive would not trigger fee observer
                }
            },
            feeValid: function() {
                return this.get('fee') >= this.get('minimumFee');
            },
            feeError: function() {
                return !this.get('feeValid') && this.get('feeChanged');
            },
            formattedMinimumFee: function() {
                return Utils.formatCurrency(this.get('minimumFee'), false, true, true);
            },
            formValid: function() {
                return this.get('recipientValid') && this.get('feeValid');
            }
        },
        /**
         * @param {boolean} options.silent
         */
        resetFee: function(options) {
            var requestData = {
                wallet: ncc.get('wallet.name'),
                account: ncc.get('activeAccount.address'),
                amount: this.get('amount'),
                message: this.get('message'),
                encrypt: this.get('encrypt'),
                recipient: this.get('recipientValid') ? this.get('recipient') : ncc.get('activeAccount.address'),
                hours_due: this.get('hours_due')
            };
            var self = this;
            
            ncc.postRequest('wallet/account/transaction/validate', requestData, 
                function(data) {
                    self.set('minimumFee', data.fee);
                    self.set('encryptionPossible', data.encryptionSupported && self.get('recipientValid'));1                    
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
            this.set('minimumFee', 0);
            this.set('dueBy', '12');
            this.set('password', '');
            this.set('useMinimumFee', true);

            this.set('recipientChanged', false);
            this.set('feeChanged', false);
        },
        sendTransaction: function() {
            var requestData = {
                wallet: ncc.get('wallet.name'),
                account: ncc.get('activeAccount.address'),
                password: this.get('password'),
                amount: this.get('amount'),
                recipient: this.get('recipient'),
                message: this.get('message'),
                fee: this.get('fee'),
                encrypt: this.get('encrypt'),
                hours_due: this.get('hours_due')
            };

            var txConfirm = ncc.getModal('transactionConfirm');
            txConfirm.set('txData', this.get());
            txConfirm.set('requestData', requestData);
            txConfirm.open();
        },
        oncomplete: function() {
            this._super();
            var self = this;

            this.resetDefaultData();
            
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
                recipient: (function() {
                    var t;
                    return function(recipient) {
                        this.set('recipientChanged', true);

                        clearTimeout(t);
                        t = setTimeout(function() {
                            ncc.postRequest('account/find', { account: recipient }, 
                                function(data) {
                                    if (data.address) {
                                        self.set('recipientLabel', data.label || '');
                                    } else {
                                        self.set('recipientLabel', null);
                                    }
                                }, 
                                {
                                    error: function() {
                                        self.set('recipientLabel', null);
                                    },
                                    altFailCb: function() {
                                        self.set('recipientLabel', null);
                                    }
                                },
                                true
                            );
                        }, 500);
                    };
                })(),
                fee: function() {
                    this.set('feeChanged', true);
                }
            }, 
            {
                init: false
            });

            this.on({
                sendFormKeypress: function(e) {
                    if (e.original.keyCode === 13) {
                        this.sendTransaction();
                    }
                },
                modalClosed: function() {
                    this.resetDefaultData();
                }
            });


            require(['maskedinput'], function() {
                var $dueBy = $('.js-sendNem-dueBy-textbox');
                $dueBy.mask('00');
            });

            // Mask NEM amount textboxes
            var self = this;
            (function(){
                var generateAccountAddressMask = function() {
                    var oldVal;
                    return function(e) {
                        var target = e.target;
                        var currentVal = target.value;
                        // If the keypress doesn't change the textbox value then i don't give a sh!t.
                        if (currentVal === oldVal) { 
                            return;
                        }

                        var caretToEnd = currentVal.length - target.selectionEnd;
                        // Remove all illegal characters
                        var rawAddress = currentVal.replace(/[^0-9a-zA-Z]/g, '');
                        var newVal = Utils.formatAddress(rawAddress)

                        target.value = oldVal = newVal;
                        self.updateModel();
                        var caret = newVal.length - caretToEnd;
                        target.setSelectionRange(caret, caret);
                    };
                };

                var $recipient = $('.js-sendNem-recipient-textbox');
                $recipient.on('keyup', generateAccountAddressMask());

                var generateNemTextboxMask = function() {
                    var oldVal;
                    return function(e) {
                        var target = e.target;
                        var currentVal = target.value;
                        // If the keypress doesn't change the textbox value then i don't give a sh!t.
                        if (currentVal === oldVal) { 
                            return;
                        }

                        var caretToEnd = currentVal.length - target.selectionEnd;
                        var decimalSeparator = ncc.get('texts.preferences.decimalSeparator');

                        // Remove illegal characters
                        var dsRegex = new RegExp('[^0-9' + Utils.escapeRegExp(decimalSeparator) + ']', 'g');
                        currentVal = currentVal.replace(dsRegex, '');
                        // Remove leading zeroes
                        while (currentVal.length > 1 && currentVal[0] === '0' && currentVal[1] !== decimalSeparator) {
                            currentVal = currentVal.substring(1, currentVal.length);
                        }
                        // Remove redundant decimal separators
                        var matchedOnce = false;
                        var i = 0;
                        while (i < currentVal.length) {
                            if (currentVal[i] === decimalSeparator) {
                                if (!matchedOnce) {
                                    matchedOnce = true;
                                } else {
                                    currentVal = currentVal.substring(0, i) + currentVal.substring(i + 1);
                                    i--; // not going forward
                                }
                            }
                            i++;
                        }

                        var dotPos = currentVal.indexOf(decimalSeparator);
                        if (dotPos === -1) {
                            dotPos = currentVal.length;
                        }
                        var intPart = currentVal.substring(0, dotPos);
                        var decimalPart = currentVal.substring(dotPos, currentVal.length);

                        intPart = Utils.addThousandSeparators(intPart);
                        // Limit to maximum 6 decimal digits
                        decimalPart = decimalPart.substring(0, decimalSeparator.length + 6); 
                        var newVal = intPart + decimalPart;

                        target.value = oldVal = newVal;
                        self.updateModel();
                        var caret = newVal.length - caretToEnd;
                        target.setSelectionRange(caret, caret);
                    };
                };
                
                var $amount = $('.js-sendNem-amount-textbox');
                var amountTxb = $amount[0];
                $amount.on('keyup', generateNemTextboxMask());

                var $fee = $('.js-sendNem-fee-textbox');
                var feeTxb = $fee[0];
                $fee.on('keyup', generateNemTextboxMask());

                ncc.observe('texts.preferences.thousandSeparator', function(newProp, oldProp) {
                    amountTxb.value = Utils.convertCurrencyFormat(amountTxb.value, oldProp, newProp);
                    feeTxb.value = Utils.convertCurrencyFormat(feeTxb.value, oldProp, newProp);
                });

                ncc.observe('texts.preferences.decimalSeparator', function(newProp, oldProp) {
                    amountTxb.value = Utils.convertCurrencyFormat(amountTxb.value, null, null, oldProp, newProp);
                    feeTxb.value = Utils.convertCurrencyFormat(feeTxb.value, null, null, oldProp, newProp);
                });
            })();
        }
    });
});