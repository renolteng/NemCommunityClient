"use strict";

define(function(require) {
    var $ = require('jquery');
    var Ractive = require('ractive');
    var Mustache = require('mustache');
    var tooltipster = require('tooltipster');

    var Utils = {
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
    }

    var NccModal = Ractive.extend({
        template: '#modal-template',
        isolated: true,
        open: function() {
            this.set('isActive', true);
            $('.active.modal-container').focus().find('.modal').css({
                left: 'auto',
                top: 'auto'
            });
        },
        close: function() {
            this.set('isActive', false);
            if (typeof this.afterClose === 'function') {
                this.afterClose();
            }
        },
        lockAction: function() {
            this.set('processing', true);
        },
        unlockAction: function() {
            this.set('processing', false);
        },
        init: function() {
            this.on({
                closeModal: function() {
                    this.close();
                },
                modalContainerClick: function(e) {
                    if (e.node === e.original.target) { //clicked outside modal
                        this.fire('closeModal');
                    }
                },
                modalContainerKeyup: function(e) {
                    if (e.original.keyCode === 27 || (this.get('closeOnEnter') && e.original.keyCode === 13)) {
                        this.fire('closeModal');
                    }
                },
                confirm: function(e, action) {
                    var callbacks = this.get('callbacks');
                    var result;
                    if (callbacks && callbacks[action]) {
                        result = callbacks[action].call(this);
                    }
                    if (result !== false) {
                        this.close();
                    }
                },
                submit: function() {
                    var submit = this.get('submit');
                    var values = this.get('values');
                    var result;
                    if (submit) {
                        result = submit.call(this, values, this.close.bind(this));
                    }
                    if (result !== false) {
                        this.close();
                    }
                },
                inputKeyup: function(e) {
                    if (e.original.keyCode === 13) {
                        this.fire('submit');
                    }
                }
            });
        }
    });



    var SendNemModal = NccModal.extend({
        data: {
            isFeeAutofilled: true,
            dueBy: 12
        },
        computed: {
            amount: function() {
                return ncc.toMNem(ncc.convertCurrencyToStandard(this.get('formattedAmount')));
            },
            inputtedRecipient: function() {
                return Utils.restoreAddress(this.get('formattedRecipient'));
            },
            recipient: function() {
                return this.get('inputtedRecipient') || ncc.get('activeAccount.address');
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
                    return ncc.toMNem(ncc.convertCurrencyToStandard(this.get('formattedFee')));
                },
                set: function(fee) {
                    this.set('formattedFee', ncc.formatCurrency(fee));
                }
            }
        },
        resetFee: function(forceReset, silent) {
            var requestData = {
                wallet: ncc.get('wallet.name'),
                account: ncc.get('activeAccount.address'),
                amount: this.get('amount'),
                message: this.get('message'),
                encrypt: this.get('encrypt'),
                recipient: this.get('recipient'),
                hours_due: this.get('hours_due')
            };
            var self = this;
            
            ncc.postRequest('wallet/account/transaction/validate', requestData, 
                function(data) {
                    var currentFee = self.get('fee');
                    var newFee = data.fee;
                    if (newFee || newFee === 0) {
                        if (forceReset || !currentFee || currentFee < newFee) {
                            self.set('fee', newFee);
                            self.set('isFeeAutofilled', true);
                        }
                    }

                    self.set('encryptionPossible', data.encryptionPossible);
                }, 
                null, silent
            );
        },
        init: function() {
            (new NccModal()).init.call(this);

            var self = this;

            this.observe('fee', function(newValue, oldValue, keypath) {
                this.set('isFeeAutofilled', false);
            });

            this.observe('amount recipient message encrypt', (function() {
                var t;
                return function() {
                    clearTimeout(t);
                    t = setTimeout(function() {
                        self.resetFee(self.get('isFeeAutofilled'), true);
                    }, 500);
                }
            })());

            this.observe('inputtedRecipient', (function() {
                var t;
                return function(recipient) {
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
            })(), {
                init: false
            });

            this.on({
                resetFee: function() {
                    this.resetFee(true, false );
                },
                sendTransaction: function() {
                    var activeAccount = ncc.get('activeAccount.address');
                    var requestData = {
                        wallet: ncc.get('wallet.name'),
                        account: activeAccount,
                        password: this.get('password'),
                        amount: this.get('amount'),
                        recipient: this.get('recipient'),
                        message: this.get('message'),
                        fee: this.get('fee'),
                        encrypt: this.get('encrypt'),
                        hours_due: this.get('hours_due')
                    };

                    var txConfirm = ncc.getModal('transactionConfirmation');
                    var parent = this;
                    txConfirm.set('parentData', this.get());
                    txConfirm.set('callbacks', {
                        confirm: function() {
                            var self = this;
                            self.lockAction();
                            ncc.postRequest('wallet/account/transaction/send', requestData, function(data) {
                                self.close();
                                parent.close();
                                ncc.showMessage(ncc.get('texts.modals.common.success'), ncc.get('texts.modals.sendNem.successMessage'));
                                ncc.refreshInfo();
                            },
                            {
                                complete: function() {
                                    parent.set('password', '');
                                    self.unlockAction();
                                }
                            });
                            return false;
                        }
                    });
                    txConfirm.open();
                },
                sendFormKeypress: function(e) {
                    if (e.original.keyCode === 13) {
                        this.fire('sendTransaction');
                    }
                }
            });
        }
    });




    var SettingsModal = NccModal.extend({
        computed: {
            chkAutoBoot: {
                get: function() {
                    return !!this.get('settings.nisBootInfo.bootNis');
                },
                set: function(autoBoot) {
                    this.set('settings.nisBootInfo.bootNis', autoBoot? 1 : 0);
                }
            },
            displayedAccount: {
                get: function() {
                    if (!this.get('settings.nisBootInfo.account')) {
                        return this.get('texts.modals.settings.autoBoot.primaryAccount');
                    } else {
                        return Utils.formatAddress(this.get('settings.nisBootInfo.account'));
                    }
                },
                set: function(address) {
                    if (address === this.get('texts.modals.settings.autoBoot.primaryAccount')) {
                        this.set('settings.nisBootInfo.account', '');
                    } else {
                        this.set('settings.nisBootInfo.account', Utils.restoreAddress(address));
                    }
                }
            },
            portStr: {
                get: function() {
                    return this.get('settings.remoteServer.port');
                },
                set: function(port) {
                    if (port && typeof port === 'string') {
                        this.set('settings.remoteServer.port', parseInt(port, 10));
                    } else {
                        this.set('settings.remoteServer.port', port);
                    }
                }
            }
        },
        close: function() {
            (new NccModal()).close.call(this);

            // Refresh to the current settings
            ncc.getRequest('configuration/get', function(data) {
                ncc.set('settings', data);
            });
        },
        init: function() {
            (new NccModal()).init.call(this);

            this.on({
                saveSettings: function() {
                    var self = this;
                    ncc.postRequest('configuration/update', ncc.get('settings'), function(data) {
                        if (data.ok) {
                            ncc.showMessage(ncc.get('texts.common.success'), ncc.get('texts.modals.settings.saveSuccess'));
                            self.close();
                        }
                    });
                },
                setDisplayedAccount: function(e, val) {
                    this.set('displayedAccount', val);
                }
            });

            require(['maskedinput'], function() {
                $('.js-settingsModal-account-textbox').mask('AAAAAA-AAAAAA-AAAAAA-AAAAAA-AAAAAA-AAAAAA-AAAA');
                $('.js-settingsModal-port-textbox').mask('00000');
            });

            this.set('active.settingsModalTab', 'remoteServer');
        }
    });



    var NccRactive = Ractive.extend({
        el: document.body,
        template: '#template',
        consts: {
            fractionalDigits: 2,
            txesPerPage: 25,
            blocksPerPage: 25,
            defaultLanguage: 'en',
            STATUS_UNKNOWN: 0,
            STATUS_STOPPED: 1,
            STATUS_STARTING: 2,
            STATUS_RUNNING: 3,
            STATUS_BOOTED: 4,
            STATUS_SYNCHRONIZED: 5
        },
        getUrlParam: function(name) {
            var qStr = location.search.substring(1, location.search.length);
            var queries = qStr.split('&');
            var temp, key, value;
            for (var i = 0; i < queries.length; i++) {
                temp = queries[i].split('=');
                if (temp[0] && temp[1]) {
                    key = decodeURIComponent(temp[0]);
                    value = decodeURIComponent(temp[1]);
                    if (key === name) {
                        return value;
                    }
                }
            }
        },
        toQueryString: function(params) {
            var queryString = [];
            for (var key in params) {
                if (params.hasOwnProperty(key)) {
                    queryString.push(encodeURIComponent(key) + '=' + encodeURIComponent(params[key]));
                }
            }
            if (queryString.length === 0) {
                return '';
            } else {
                return '?' + queryString.join('&');
            }
        },
        queryStringToJson: function (qStr) {
            if (qStr === '')
                return '';
            var pairs = (qStr || location.search).slice(1).split('&');
            var result = {};
            for (var i = 0; i < pairs.length; i++) {
                var pair = pairs[i].split('=');
                if (pair[0]) {
                    result[decodeURIComponent(pair[0])] = decodeURIComponent(pair[1] || null);
                }
            }
            return result;
        },
        escapeRegExp: function(str) {
            return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, '\\$&');
        },
        components: {
            errorModal: NccModal,
            messageModal: NccModal,
            confirmModal: NccModal,
            inputModal: NccModal,
            settingsModal: SettingsModal,
            sendNemModal: SendNemModal,
            clientInfoModal: NccModal,
            transactionDetailsModal: NccModal,
            unclosableMessageModal: NccModal,
            transactionConfirmationModal: NccModal
        },
        computed: {
            allAccounts: function() {
                return this.prepend([this.get('wallet.primaryAccount')], this.get('wallet.otherAccounts'));
            },
            appStatus: function() {
                switch (this.get('nccStatus.code')) {
                    case null:
                    case undefined:
                    case this.consts.STATUS_UNKNOWN:
                        return {
                            type: 'critical',
                            message: this.get('texts.common.appStatus.nccUnknown')
                        };
                    case  this.consts.STATUS_STOPPED:
                        return {
                            type: 'critical',
                            message: this.get('texts.common.appStatus.nccUnavailable')
                        };
                    case  this.consts.STATUS_STARTING:
                        return {
                            type: 'warning',
                            message: this.get('texts.common.appStatus.nccStarting')
                        };
                    default: // probably RUNNING
                        switch (this.get('nisStatus.code')) {
                            case null:
                            case undefined:
                            case this.consts.STATUS_UNKNOWN:
                                return {
                                    type: 'critical',
                                    message: this.get('texts.common.appStatus.nisUnknown')
                                };
                            case this.consts.STATUS_STOPPED:
                                return {
                                    type: 'critical',
                                    message: this.get('texts.common.appStatus.nisUnavailable')
                                };
                            case this.consts.STATUS_STARTING:
                                return {
                                    type: 'warning',
                                    message: this.get('texts.common.appStatus.nisStarting')
                                };
                            case this.consts.STATUS_RUNNING:
                                if (this.get('status.booting')) {
                                    return {
                                        type: 'warning',
                                        message: this.get('texts.common.appStatus.booting')
                                    };
                                } else {
                                    return {
                                        type: 'warning',
                                        message: this.get('texts.common.appStatus.notBooted')
                                    };
                                }
                            case this.consts.STATUS_BOOTED:
                                var lastBlockBehind = this.get('nis.nodeMetaData.lastBlockBehind');
                                if (lastBlockBehind !== 0) {
                                    if (!lastBlockBehind) {
                                        return {
                                            type: 'warning',
                                            message: this.get('texts.common.appStatus.nisInfoNotAvailable')
                                        };
                                    } else {
                                        var daysBehind = Math.floor(this.get('nis.nodeMetaData.lastBlockBehind') / (60 * 1440));
                                        var daysBehindText;
                                        switch (daysBehind) {
                                            case 0:
                                                daysBehindText = this.get('texts.common.appStatus.daysBehind.0');
                                                break;
                                            case 1:
                                                daysBehindText = this.get('texts.common.appStatus.daysBehind.1');
                                                break;
                                            default:
                                                daysBehindText = this.fill(this.get('texts.common.appStatus.daysBehind.many'), daysBehind);
                                                break;
                                        }

                                        return {
                                            type: 'warning',
                                            message: this.fill(this.get('texts.common.appStatus.synchronizing'), this.get('nis.nodeMetaData.nodeBlockChainHeight'), daysBehindText)
                                        };
                                    }
                                } else {
                                    return {
                                        type: 'message',
                                        message: this.get('texts.common.appStatus.synchronized')
                                    };
                                }

                                return {
                                    type: 'warning',
                                    message: this.get('texts.common.appStatus.synchronizing')
                                };
                            case this.consts.STATUS_SYNCHRONIZED:
                                return {
                                    type: 'message',
                                    message: this.get('texts.common.appStatus.synchronized')
                                };
                        }
                }
            },
            nodeBooted: function() {
                var nisStatus = this.get('nisStatus.code');
                return !!(nisStatus === this.consts.STATUS_BOOTED || nisStatus === this.consts.STATUS_SYNCHRONIZED);
            },
            nisUnavailable: function() {
                return !!(this.get('nisStatus.code') === this.consts.STATUS_STOPPED);
            },
            nisSynchronized: function() {
                // 5 status code is not implemented yet
                // so we cannot completely rely on NIS status code
                return this.get('nisStatus.code') === this.consts.STATUS_SYNCHRONIZED || this.get('nis.nodeMetaData.lastBlockBehind') === 0;
            }
        },
        ajaxError: function(jqXHR, textStatus, errorThrown) {
            this.showError(textStatus, errorThrown);
        },
        apiPath: function(api) {
            return '../api/' + api;
        },
        checkSuccess: function(data, silent, settings) {
            var self = this;
            function showError(faultId, error) {
                if (!silent) {
                    self.showError(faultId);
                }
                if (settings && settings.altFailCb) {
                    settings.altFailCb(faultId, error);
                }
                return false;
            }

            if (undefined !== data.error) {
                switch (data.status) {
                    case 200:
                        break;

                    case 601: // NODE_ALREADY_BOOTED
                        silent = true;

                    default:
                        return showError(data.status);
                }
            }

            return true;
        },
        _ajaxRequest: function(type, api, requestData, successCb, settings, silent) {
            var self = this;
            successCb = successCb || function() {};

            // set the dataType to 'text' instead of 'json' because  NCC will return "" (empty string)
            // from void functions, but JQuery treats this as invalid JSON
            var s = {
                contentType: 'application/json',
                dataType: 'text',
                type: type,
                data: requestData ? JSON.stringify(requestData) : undefined,
                error: function (jqXHR, textStatus, errorThrown) {
                    // first check the success (in case this is a new API)
                    var data = jqXHR.responseText ? $.parseJSON(jqXHR.responseText) : {};
                    if (!self.checkSuccess(data, silent, settings))
                        return;

                    // since we are in an error callback, handle the error as an unknown ajax error
                    return silent ? [] : self.ajaxError(jqXHR, textStatus, errorThrown);
                },
                success: function(data) {
                    // if the data is an empty string, emulate original NCC behavior by returning { ok: 1 }
                    if (!data) {
                        successCb({ ok: 1 });
                        return;
                    }

                    // otherwise, parse the json check the success (for legacy API handling)
                    var parsedData = $.parseJSON(data);
                    if (self.checkSuccess(parsedData, silent, settings)) {
                        successCb(parsedData);
                    }
                }
            };
            $.extend(s, settings);
            $.ajax(this.apiPath(api), s);
        },
        getRequest: function(api, successCb, settings, silent) {
            return this._ajaxRequest('get', api, undefined, successCb, settings, silent);
        },
        postRequest: function(api, requestData, successCb, settings, silent) {
            return this._ajaxRequest('post', api, requestData, successCb, settings, silent);
        },
        syncRequest: function(url) {
            return $.ajax(url, {
                async: false,
                type: 'GET',
                error: this.ajaxError
            }).responseText;
        },
        formatAddress: function(address) {
            if (address && typeof address === 'string') {
                return address.match(/.{1,6}/g).join('-').toUpperCase();
            } else {
                return address;
            }
        },
        addThousandSeparators: function(num) {
            return num.toString(10).replace(/\B(?=(\d{3})+(?!\d))/g, ncc.get('texts.preferences.thousandSeparator'));
        },
        minDigits: function(num, digits) {
            num = num.toString(10);
            while (num.length < digits) {
                num = '0' + num;
            }
            return num;
        },
        toNem: function(mNem) {
            return mNem / 1000000;
        },
        toMNem: function(nem) {
            // clever workaround to deal with JavaScript loss of precision bugs
            // by using strings for multiplication
            // the largest integer that can be stored in a java numeric type (64-bit floating point)
            // is Math.pow(2, 53) > 8 * Math.pow(10, 15)
            // [ Math.pow(2, 53) - 1 < Math.pow(2, 53) == Math.pow(2, 53) + 1 ]
            // NOTE: this only works because nem is assumed to be a string

            // determine the number of trailing zeros to add based on the index of the decimal
            var decimalIndex = nem.indexOf('.');
            var power = 6 - (-1 === decimalIndex ? 0 : nem.length - decimalIndex - 1);
            var trailingZeros = new Array(power + 1).join('0');
            return parseInt(nem.replace('.', '') + trailingZeros);
        },
        formatCurrency: function(amount, dimTrailings, noLimitFractionalPart) {
            var nem = this.addThousandSeparators(Math.floor(this.toNem(amount)));
            var mNem = this.minDigits(amount % 1000000, 6);
            if (!noLimitFractionalPart) {
                mNem = mNem.substring(0, this.consts.fractionalDigits);
            }

            if (dimTrailings) {
                var cutPos = mNem.length - 1;
                while (mNem.charAt(cutPos) === '0') {
                    cutPos--;
                }
                cutPos++;

                var clearPart = mNem.substring(0, cutPos);
                var dimmedPart = mNem.substring(cutPos, mNem.length);
                if (dimmedPart) {
                    if (clearPart) {
                        return nem + this.get('texts.preferences.decimalSeparator') + clearPart + '<span class="dimmed">' + dimmedPart + '</span>';
                    } else {
                        return nem + '<span class="dimmed">' + this.get('texts.preferences.decimalSeparator') + dimmedPart + '</span>';
                    }
                }
            }

            return nem + this.get('texts.preferences.decimalSeparator') + mNem;
        },
        convertCurrencyToStandard: function(amount) {
            var thousandSeparator = new RegExp(ncc.escapeRegExp(ncc.get('texts.preferences.thousandSeparator')), 'g');
            var decimalSeparator = new RegExp(ncc.escapeRegExp(ncc.get('texts.preferences.decimalSeparator')), 'g');
            return amount.replace(thousandSeparator, '').replace(decimalSeparator, '.');
        },
        toDate: function(ms) {
            return new Date(ms);
        },
        formatDate: (function() {
            var shortMonths = {
                1: 'Jan',
                2: 'Feb',
                3: 'Mar',
                4: 'Apr',
                5: 'May',
                6: 'Jun',
                7: 'Jul',
                8: 'Aug',
                9: 'Sep',
                10: 'Oct',
                11: 'Nov',
                12: 'Dec',
            };
            return function(date, format) {
                if (typeof date === 'number') {
                    date = this.toDate(date);

                    var month = date.getMonth() + 1;
                    var day = date.getDate();
                    var year = date.getFullYear();
                    var hour = date.getHours();
                    var min = date.getMinutes();
                    var sec = date.getSeconds();

                    switch (format) {
                    case 'MM/dd/yy hh:mm:ss':
                        month = this.minDigits(month, 2);
                        day = this.minDigits(day, 2);
                        year = year.toString(10);
                        year = year.substring(year.length - 2, year.length);
                        hour = this.minDigits(hour, 2);
                        min = this.minDigits(min, 2);
                        sec = this.minDigits(sec, 2);
                        return month + '/' + day + '/' + year + ' ' + hour + ':' + min + ':' + sec;
                    case 'M dd, yyyy':
                        month = shortMonths[month];
                        day = this.minDigits(day, 2);
                        return month + ' ' + day + ', ' + year;
                    case 'M dd, yyyy hh:mm:ss':
                        month = shortMonths[month];
                        day = this.minDigits(day, 2);
                        hour = this.minDigits(hour, 2);
                        min = this.minDigits(min, 2);
                        sec = this.minDigits(sec, 2);
                        return month + ' ' + day + ', ' + year + ' ' + hour + ':' + min + ':' + sec;
                    }
                } else {
                    return date;
                }
            };
        })(),
        daysPassed: function(begin) {
            var now = new Date().getTime();
            var timespan = now - begin;
            var day = 1000*60*60*24;
            var days = timespan / day;
            var roundedDays = Math.round(days);
            return {
                days: days,
                roundedDays: roundedDays
            };
        },
        sortByTimeNewest: function(a, b) {
            if (a && b && a.timeStamp && b.timeStamp) {
                return b.timeStamp - a.timeStamp;
            } else {
                return 0;
            }
        },
        getModal: function(modalName) {
            return this.findComponent(modalName + 'Modal');
        },
        showModal: function(modalName) {
            this.getModal(modalName).open();
        },
        showError: function(errorId, message) {
            var modal = this.getModal('error');
            if (!message && typeof errorId === 'number') {
                message = this.get('texts.faults.' + errorId);
            }
            modal.set({
                errorId: errorId,
                message: message || 'Unknown error'
            });
            modal.open();
        },
        showMessage: function(title, message, closeCallback) {
            var modal = this.getModal('message');
            modal.set({
                modalTitle: title,
                message: message
            });
            modal.open();
            if (closeCallback) {
                var ob = modal.observe('isActive', function(newValue, oldValue) {
                    if (newValue === false) {
                        closeCallback();
                        ob.cancel();
                    }
                });
            }
        },
        showUnclosableMessage: function(title, message, runningEllipsis) {
            var modal = this.getModal('unclosableMessage');
            modal.set({
                modalTitle: title,
                message: message
            });
            if (runningEllipsis) {
                var ell = '';
                modal.runningEllipsis = setInterval(function() {
                    modal.set('runningEllipsis', ell);
                    if (ell.length < 3) {
                        ell += '.';
                    } else {
                        ell = '';
                    }
                }, 600);
            }
            modal.open();
        },
        closeUnclosableMessage: function() {
            var modal = this.getModal('unclosableMessage');
            if (modal.runningEllipsis) {
                clearInterval(modal.runningEllipsis);
                modal.runningEllipsis = null;
            }
            modal.close();

        },
        showConfirmation: function(title, message, callbacks, actions) {
            var modal = this.getModal('confirm');
            modal.set({
                modalTitle: title,
                message: message,
                callbacks: callbacks,
                actions: actions || [
                    {
                        action: 'no',
                        label: ncc.get('texts.modals.confirmDefault.no'),
                        actionType: 'secondary'
                    },
                    {
                        action: 'yes',
                        label: ncc.get('texts.modals.confirmDefault.yes'),
                        actionType: 'primary'
                    }
                ]
            });
            modal.open();
        },
        showInputForm: function(title, message, fields, initValues, submit, submitLabel, processingLabel) {
            var modal = this.getModal('input');
            modal.set({
                fields: null,
                values: null
            });

            modal.set({
                modalTitle: title,
                message: message,
                fields: fields,
                values: initValues,
                submit: submit,
                submitLabel: submitLabel,
                processingLabel: processingLabel
            });
            modal.open();
        },
        toggleOn: function(id) {
            var keypath = 'active.' + id;
            if (!this.get(keypath)) {
                this.set(keypath, true);
                var firstTime = true;
                var self = this;
                $(document).on('click.' + id, function(ev) {
                    if (firstTime) {
                        firstTime = false;
                        return;
                    }
                    //if (self.nodes[id] !== ev.target && !$.contains(self.nodes[id], ev.target)) {
                    self.fire('toggleOff', null, id);
                    //}
                });
            }
        },
        toggleOff: function(id) {
            this.set('active.' + id, false);
            $(document).off('click.' + id);
        },
        prepend: function(args, array) {
            var a = array.slice(0);
            a.unshift.apply(a, args);
            return a;
        },
        updateNewer: function(updatedArr, currentArr, comparedProp) {
            if (currentArr && currentArr[0]) {
                var comparedValue = updatedArr[updatedArr.length - 1][comparedProp];
                
                for (var i = currentArr.length - 1; i >= 0; i--) {
                    if (currentArr[i][comparedProp] === comparedValue) {
                        break;
                    }
                }

                var nonDup = currentArr.slice(i + 1);
                var result = updatedArr.concat(nonDup);
                return result;
            } else {
                return updatedArr;
            }
        },
        processTransaction: function(tx, activeAccount) {
            if (!activeAccount) activeAccount = this.get('activeAccount.address');
            tx.isIncoming = tx.direction === 1 || tx.direction === 0;
            tx.isOutgoing = tx.direction === 2;
            tx.isSelf = tx.direction === 3;
            tx.formattedSender = this.formatAddress(tx.sender);
            tx.formattedRecipient = this.formatAddress(tx.recipient);
            tx.formattedFee = this.formatCurrency(tx.fee, true);
            tx.formattedAmount = this.formatCurrency(tx.amount, true);
            tx.formattedDate = this.formatDate(tx.timeStamp, 'M dd, yyyy hh:mm:ss');
            return tx;
        },
        processTransactions: function(transactions, activeAccount) {
            if (transactions) {
                if (!activeAccount) activeAccount = this.get('activeAccount.address');
                for (var i = 0; i < transactions.length; i++) {
                    this.processTransaction(transactions[i], activeAccount);
                }
            }
            return transactions;
        },
        processHarvestedBlock: function(block) {
            if (!block.message) block.message = 'Block #' + block.height;
            if (!block.hash) block.hash = block.blockHash.data;
            if (!block.timeStamp && block.fee !== 0) block.timeStamp = block.timeStamp;
            if (!block.fee && block.fee !== 0) block.fee = block.totalFee;

            block.formattedTime = this.formatDate(block.timeStamp, 'M dd, yyyy hh:mm:ss');
            block.formattedFee = this.formatCurrency(block.fee, true);
            return block;
        },
        processHarvestedBlocks: function(blocks) {
            for (var i = 0; i < blocks.length; i++) {
                this.processHarvestedBlock(blocks[i]);
            }
            return blocks;
        },
        processAccount: function(account) {
            account.formattedAddress = this.formatAddress(account.address);
            var currentAccount = this.get('activeAccount.address');

            if (account.transactions) {
                account.transactions = this.processTransactions(account.transactions);
            }

            return account;
        },
        processWallet: function(wallet) {
            wallet.lastRefreshDate = this.toDate(wallet.lastRefresh).toString();
            wallet.daysPassed = this.daysPassed(wallet.lastRefresh);

            this.processAccount(wallet.primaryAccount);
            for (var i = 0; i < wallet.otherAccounts.length; i++) {
                this.processAccount(wallet.otherAccounts[i]);
            }

            return wallet;
        },
        globalSetup: function() {
            require(['draggable'], function() {
                $('.modal').draggable({
                    handle: '.modal-head'
                });
            });
        },
        loadPage: function(page, params, isBack, isInit) {
            var self = this;

            // We use require(string) instead of require(array, function) to make page loading process synchronous
            // require(string) needs dependencies to be loaded before being used
            // So we have to load all layout files and templates first
            var layouts = [];
            var loadLayout = function(layoutName) {
                require([layoutName], function(layout) {
                    layouts.unshift(layout);
                    require([layout.template], function() {
                        if (layout.parent) {
                            loadLayout(layout.parent);
                        } else {
                            replaceLayouts();
                        }
                    });
                });
            };

            var replaceLayouts = function() {
                var oldParams = ncc.get('params');
                var paramsChanged = JSON.stringify(oldParams) !== JSON.stringify(params);

                for (var i = 0; i < layouts.length; i++) {
                    var layout = layouts[i];
                    var keypath = 'layout.' + i;
                    var currentLayout = self.get(keypath);

                    if (paramsChanged || !currentLayout || (currentLayout.name !== layout.name)) {
                        var template = require(layout.template);
                        if (currentLayout && currentLayout.leave) {
                            $.each(currentLayout.leave, function() {
                                this.apply(currentLayout);
                            });
                        }

                        // Init
                        if (!layout.alreadyInit && layout.initOnce) {
                            layout.initOnce();
                            layout.alreadyInit = true;
                        }
                        if (layout.initEverytime) {
                            var abort = layout.initEverytime(params);
                            if (abort) return;
                        }

                        self.set(keypath, null);
                        self.partials[i] = template;
                        self.set(keypath, layout);

                        // Setup
                        if (!layout.alreadySetup && layout.setupOnce) {
                            layout.setupOnce();
                            layout.alreadySetup = true;
                        }
                        if (layout.setupEverytime) {
                            var abort = layout.setupEverytime(params);
                            if (abort) return;
                        }
                    }
                }

                self.globalSetup();

                // Clear the old layouts
                var currLayouts = self.get('layout');
                if (currLayouts && currLayouts.length) {
                    for (i = i + 1; i < currLayouts.length; i++) {
                        self.set('layout.' + i, null);
                    }
                }

                if (!isBack) {
                    var queryString = '';
                    if (params) {
                        queryString = self.toQueryString(params);
                    } else if (isInit) {
                        queryString = location.search;
                        params = ncc.queryStringToJson(location.search);
                    }
                    var url = layouts[layouts.length - 1].url + queryString;
                    
                    if (isInit) {
                        history.replaceState({ page: page, params: params }, 'entry', url);
                    } else {
                        history.pushState({ page: page, params: params }, null, url);
                    }
                }
                ncc.set('params', params);
            };

            loadLayout(page);
        },
        fill: (function() {
            var htmlDecode = function(str) {
                var txt = document.createElement('textarea');
                txt.innerHTML = str;
                return txt.value;
            };

            return function(template) {
                // The first argument could be whether it should return the HTML decoded version
                if (typeof arguments[0] === 'boolean') {
                    var decode = arguments[0];
                    Array.prototype.splice.call(arguments, 0, 1);
                    template = arguments[0];

                    if (decode) {
                        return htmlDecode(Mustache.render(template, arguments));
                    } else {
                        return Mustache.render(template, arguments);
                    }
                } else {
                    return Mustache.render(template, arguments);
                }
            };
        })(),
        init: function(options) {
            var self = this;

            require(['languages'], function(languages) {
                self.set('languages', languages);
                self.observe('settings.language', function(newValue) {
                    newValue = newValue || self.consts.defaultLanguage;
                    for (var i = 0; i < languages.length; i++) {
                        if (languages[i].id.toLowerCase() === newValue.toLowerCase()) {
                            self.set('texts', languages[i].texts);
                            if (undefined === self.get('nis.nodeMetaData.maxBlockChainHeight')) {
                                self.set('nis.nodeMetaData.nodeBlockChainHeight', self.get('texts.dashboard.transactions.unknown')) // after opening the wallet the node meta data is not available yet
                            }
                            return;
                        }
                    }
                });
            });

            this.on({
                redirect: function(e, page, params) {
                    this.loadPage(page, params);
                },
                toggleOn: function(e, id) {
                    this.toggleOn(id);
                },
                toggleOff: function(e, id) {
                    this.toggleOff(id);
                },
                openModal: function(e, id) {
                    this.showModal(id);
                },
                shutdown: function() {
                    var self = this;
                    this.showConfirmation(ncc.get('texts.modals.shutdown.title'), ncc.get('texts.modals.shutdown.message'), {
                        yes: function() {
                            self.loadPage('start');
                        }
                    });
                },
                selectFile: function(e, id) {
                    var input = this.nodes[id];
                    var evt = document.createEvent("MouseEvents");
                    evt.initEvent('click', true, false);
                    input.dispatchEvent(evt);
                },
                registerToolTip: function(e) {
                    if (!$(e.node).data('tooltipster-ns')) {
                        var $node = $(e.node);
                        $node.tooltipster({
                            position: 'bottom',
                            delay: 50,
                            functionBefore: function($origin, continueTooltip) {
                                var title = $origin.attr('title');
                                if (title) {
                                    $origin.tooltipster('content', title);
                                    $origin.removeAttr('title');
                                }
                                continueTooltip();
                            }
                        });
                        $node.tooltipster('show');
                    }
                },
                set: function(e, keypath, val) {
                    this.set(keypath, val);
                }
            });

            this.set('fill', this.fill);
            this.set('formatAddress', this.formatAddress);
            this.set('formatCurrency', this.formatCurrency);
            this.set('formatDate', this.formatDate);
            this.set('toDate', this.toDate);
            this.set('daysPassed', this.daysPassed);
            this.set('toNem', this.toNem);

            this.global = {
                $window: $(window),
                $document: $(document),
                $html: $('html'),
                $body: $('body')
            };
        }
    });

    window.ncc = new NccRactive();
    return ncc;
});
