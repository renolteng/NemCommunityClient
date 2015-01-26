"use strict";

define(['NccModal', 'Utils'], function(NccModal, Utils) {
	return NccModal.extend({
	    data: {
            isFeeAutofilled: true,
            dueBy: 12
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
            multisigAccount: function() {
                return ncc.get('activeAccount.address');
            },
            formattedMultisigAccount: {
                get: function() {
                    return ncc.get('activeAccount.formattedAddress');
                },
                set: function() {}
            },
            feeValid: function() {
                console.log(this.get('fee'), this.get('minimumFee'));
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
        resetFee: function(options) {
            var requestData = {
                wallet: ncc.get('wallet.wallet'),
                multisig: ncc.get('activeAccount.address'),
                cosignatories: this.get('cosignatories')
                    .filter(function(e){ return (e.text != null); })
                    .map(function(e){ return {'address':e.text}}),
                hours_due: this.get('hoursDue')
            };
            var self = this;

            console.log(requestData);

            ncc.postRequest('wallet/account/modification/validate', requestData,
                function(data) {
                    console.log(data);
                    self.set('minimumFee', data.fee);
                },
                {
                    altFailCb: function(faultId, error) {
                    }
                }, options.silent
            );
        },
		addCosignatory: function() {
            $('.js-cosignatory').last().focus();
            this.get('cosignatories').push({});
            $('.js-cosignatory').last().focus();
        },
        removeCosignatory: function(index) {
            this.get('cosignatories').splice(index, 1);
        },
        resetDefaultData: function() {
        	this.set('cosignatories', [{}]);

            this.set('fee', 0);
            this.set('minimumFee', 0);
            this.set('dueBy', '12');
            this.set('password', '');
            this.set('useMinimumFee', true);

            this.set('feeChanged', false);
            this.set('passwordChanged', true);
            this.resetFee({ silent: true });
        },
        onrender: function() {
            this._super();
            var self = this;

            this.resetDefaultData();

            this.observe({
                'cosignatories': (function() {
                    var t;
                    return function() {
                        clearTimeout(t);
                        t = setTimeout(function() {
                            self.resetFee({ silent: true });
                        }, 500);
                    }
                })()
            },
            {
                init: false
            });
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
                    $('.js-cosignatory').focus();
                    // TODO G-Krysto: this should be here not in modalClosed, or not?
                    this.resetDefaultData();
                },
                modalClosed: function() {
                    this.resetDefaultData();
                }
            });

            var $dueBy = $('.js-multisig-dueBy-textbox');
            $dueBy.on('keypress', function(e) { Utils.mask.keypress(e, 'number', self) });

            var $fee = $('.js-multisig-fee-textbox');
            var feeTxb = $fee[0];
            $fee.on('keypress', function(e) { Utils.mask.keypress(e, 'nem', self); });
            $fee.on('paste', function(e) { Utils.mask.paste(e, 'nem', self); });
            $fee.on('keydown', function(e) { Utils.mask.keydown(e, 'nem', self); });

            this.listeners.push(ncc.observe({
                'texts.preferences.thousandSeparator': function(newProp, oldProp) {
                    feeTxb.value = Utils.format.nem.reformat(feeTxb.value, oldProp, newProp);
                },
                'texts.preferences.decimalSeparator': function(newProp, oldProp) {
                    feeTxb.value = Utils.format.nem.reformat(feeTxb.value, null, null, oldProp, newProp);
                }
            }));
        }
	});
});