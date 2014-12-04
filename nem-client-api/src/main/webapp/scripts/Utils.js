"use strict";

define(function() {
    var Utils = {
        config: {
            addressCharacters: 40,
            defaultDecimalPlaces: 2,
            maxDecimalPlaces: 6,
            nemDivisibility: 1000000,
            txesPerPage: 25,
            blocksPerPage: 25,
            defaultLanguage: 'en',
            STATUS_UNKNOWN: 0,
            STATUS_STOPPED: 1,
            STATUS_STARTING: 2,
            STATUS_RUNNING: 3,
            STATUS_BOOTING: 4,
            STATUS_BOOTED: 5,
            STATUS_SYNCHRONIZED: 6
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
        htmlDecode: function(str) {
            var txt = document.createElement('textarea');
            txt.innerHTML = str;
            return txt.value;
        },
        updateNewer: function(newArr, currentArr, comparedProp) {
            if (currentArr && currentArr[0] && newArr && newArr[0]) {
                var comparedValue = newArr[newArr.length - 1][comparedProp];
                
                for (var i = currentArr.length - 1; i >= 0; i--) {
                    if (currentArr[i][comparedProp] === comparedValue) {
                        break;
                    }
                }

                if (i === 0) {
                    return {
                        updatedArray: newArr,
                        noConnection: true
                    };
                } else {
                    var nonDup = currentArr.slice(i + 1);
                    var updatedArray = newArr.concat(nonDup);
                    return {
                        updatedArray: updatedArray,
                        noConnection: false
                    };
                }
            } else {
                return {
                    updatedArray: newArr,
                    noConnection: true
                };
            }
        },
        toggleOn: function(instance, id) {
            var keypath = 'active.' + id;
            if (!instance.get(keypath)) {
                instance.set(keypath, true);
                var firstTime = true;
                var self = Utils;
                $(document).on('click.' + id, function(ev) {
                    if (firstTime) {
                        firstTime = false;
                        return;
                    }
                    self.toggleOff(instance, id);
                });
            }
        },
        toggleOff: function(instance, id) {
            instance.set('active.' + id, false);
            $(document).off('click.' + id);
        },

        // FORMAT & CONVERSON
        format: {
            nem: {
                /**
                 * @param {string} str any inputted string
                 * @return {object} NEM Amount object
                 */
                stringToNem: function(str) {
                    var nem = {
                        intPart: '',
                        decimalPart: ''
                    };
                    if (typeof str !== 'string') return nem;

                    var decimalSeparator = ncc.get('texts.preferences.decimalSeparator');

                    // Remove illegal characters
                    var dsRegex = new RegExp('[^0-9' + Utils.escapeRegExp(decimalSeparator) + ']', 'g');
                    str = str.replace(dsRegex, '');

                    // Split integer and decimal parts
                    var dsPos = str.indexOf(decimalSeparator);
                    if (dsPos >= 0) {
                        nem.forceDecimalSeparator = true;
                    } else {
                        dsPos = str.length;
                    }
                    
                    nem.intPart = str.substring(0, dsPos);
                    var dsRegex = new RegExp(Utils.escapeRegExp(decimalSeparator), 'g');
                    nem.decimalPart = str.substring(dsPos, str.length).replace(dsRegex, '');

                    return nem;
                },
                /**
                 * @param {number} uNem amount in uNEM
                 * @return {object} NEM Amount object
                 */
                uNemToNem: function(uNem) {
                    var nem = {
                        intPart: '',
                        decimalPart: ''
                    };
                    if (typeof uNem !== 'number') return nem;

                    nem.intPart = Math.floor(uNem / Utils.config.nemDivisibility).toString();

                    var decimalPart = (Math.floor(uNem) % Utils.config.nemDivisibility).toString();
                    // Add leading zeroes
                    if (decimalPart.length < Utils.config.maxDecimalPlaces) {
                        decimalPart = new Array(Utils.config.maxDecimalPlaces - decimalPart.length + 1).join('0') + decimalPart;
                    }
                    // Remove trailing zeroes
                    // for (var i = decimalPart.length - 1; decimalPart[i] === '0'; i--) {
                    // }
                    // decimalPart = decimalPart.substring(0, i + 1);

                    nem.decimalPart = decimalPart;
                    return nem;
                },
                 /**
                 * @param {object} nem NEM Amount object
                 * @param {boolean} options.fixedDecimalPlaces apply a fixed number of decimal places
                 * @param {number} options.decimalPlaces decimal places to be applied, fallback to default if falsy
                 * @param {boolean} options.dimUnimportantTrailing whether to dim the unimportant trailing part
                 * @param {boolean} options.keepTrailingZeroes whether to keep trailing zeroes (ignored when fixedDecimalPlaces is true)
                 * @return {string} formatted amount
                 */
                formatNem: function(nem, options) {
                    if (!nem) return '';
                    options = options || {};
                    var firstPart = (parseInt(nem.intPart) || 0).toString();
                    firstPart = firstPart.replace(/\B(?=(\d{3})+(?!\d))/g, ncc.get('texts.preferences.thousandSeparator')); // add thousand separators
                    var lastPart = nem.decimalPart;

                    if (options.fixedDecimalPlaces) {
                        var decimalPlaces = options.decimalPlaces || Utils.config.defaultDecimalPlaces;
                        if (lastPart.length > decimalPlaces) {
                            lastPart = lastPart.substring(0, decimalPlaces);
                        }
                        if (lastPart.length < decimalPlaces) {
                            lastPart = lastPart + new Array(decimalPlaces - lastPart.length + 1).join('0');
                        }
                    } else if (!options.keepTrailingZeroes) {
                        for (var i = lastPart.length - 1; lastPart[i] === '0'; i--) {
                        }
                        lastPart = lastPart.substring(0, i + 1);
                    }

                    if (lastPart || nem.forceDecimalSeparator) {
                        var decimalSeparator = ncc.get('texts.preferences.decimalSeparator');
                        lastPart = decimalSeparator + lastPart;

                        if (options.dimUnimportantTrailing) {
                            var cutPos = lastPart.length - 1;
                            while (lastPart[cutPos] === '0') {
                                cutPos--;
                            }
                            cutPos++;

                            if (cutPos < lastPart.length && lastPart.length > 0) {
                                if (lastPart.substring(0, cutPos) === decimalSeparator) {
                                    cutPos = 0;
                                }

                                var clearPart = lastPart.substring(0, cutPos);
                                var dimmedPart = lastPart.substring(cutPos, lastPart.length);
                                lastPart = clearPart + '<span class="dimmed">' + dimmedPart + '</span>';
                            }
                        }
                    }

                    return firstPart + lastPart;
                },
                /**
                 * @param {string} str a formatted NEM amount
                 * @param {string} oldThousandSep
                 * @param {string} newThousandSep
                 * @param {string} oldDecimalSep
                 * @param {string} newDecimalSep
                 * @return {string} the reformatted NEM amount
                 */
                reformat: function(str, oldThousandSep, newThousandSep, oldDecimalSep, newDecimalSep) {
                    if (oldThousandSep) {
                        var tsRegex = new RegExp(Utils.escapeRegExp(oldThousandSep), 'g');
                        str = str.replace(tsRegex, newThousandSep);
                    }

                    if (oldDecimalSep) {
                        var dsRegex = new RegExp(Utils.escapeRegExp(oldDecimalSep), 'g');
                        str = str.replace(dsRegex, newDecimalSep);
                    }

                    return str;
                },
                /**
                 * @param {object} NEM Amount object
                 * @return {number} amount in uNEM
                 */
                getNemValue: function(nem) {
                    return (parseInt(nem.intPart, 10) || 0) * Utils.config.nemDivisibility + (parseInt(nem.decimalPart, 10) || 0);
                },
                /**
                 * Shortcut function for formatNem() and uNemToNem()
                 * @param {number} uNem amount in uNEM
                 * @param {object} options as in the formatNem()
                 * @return {string} formatted amount
                 */
                formatNemAmount: function(uNem, options) {
                    var t = Utils.format.nem;
                    return t.formatNem(t.uNemToNem(uNem), options);
                }
            },
            address: {
                format: function(address) {
                    var segments = address.substring(0, Utils.config.addressCharacters).match(/.{1,6}/g) || [];
                    return segments.join('-').toUpperCase();
                },
                restore: function(address) {
                    return address.replace(/\-/g, '');
                }
            }
        },
        minDigits: function(num, digits) {
            num = num.toString(10);
            while (num.length < digits) {
                num = '0' + num;
            }
            return num;
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
                    date = Utils.toDate(date);

                    var month = date.getMonth() + 1;
                    var day = date.getDate();
                    var year = date.getFullYear();
                    var hour = date.getHours();
                    var min = date.getMinutes();
                    var sec = date.getSeconds();

                    switch (format) {
                    case 'MM/dd/yy hh:mm:ss':
                        month = Utils.minDigits(month, 2);
                        day = Utils.minDigits(day, 2);
                        year = year.toString(10);
                        year = year.substring(year.length - 2, year.length);
                        hour = Utils.minDigits(hour, 2);
                        min = Utils.minDigits(min, 2);
                        sec = Utils.minDigits(sec, 2);
                        return month + '/' + day + '/' + year + ' ' + hour + ':' + min + ':' + sec;
                    case 'M dd, yyyy':
                        month = shortMonths[month];
                        day = Utils.minDigits(day, 2);
                        return month + ' ' + day + ', ' + year;
                    case 'M dd, yyyy hh:mm:ss':
                        month = shortMonths[month];
                        day = Utils.minDigits(day, 2);
                        hour = Utils.minDigits(hour, 2);
                        min = Utils.minDigits(min, 2);
                        sec = Utils.minDigits(sec, 2);
                        return month + ' ' + day + ', ' + year + ' ' + hour + ':' + min + ':' + sec;
                    }
                } else {
                    return date;
                }
            };
        })(),
        generateMask: function(type, ractive) {
            var oldVal;
            return function(e) {
                var target = e.target;
                var currentVal = target.value;
                // If the keypress doesn't change the textbox value then i don't give a sh!t.
                if (currentVal === oldVal) { 
                    return;
                }

                var caretToEnd = currentVal.length - target.selectionEnd;
                var newVal = oldVal;

                switch (type) {
                    case 'nem':
                        newVal = Utils.format.nem.formatNem(Utils.format.nem.stringToNem(currentVal), {keepTrailingZeroes: true});
                        break;
                    case 'address':
                        // Remove all illegal characters
                        var rawAddress = currentVal.replace(/[^0-9a-zA-Z]/g, '');
                        var newVal = Utils.format.address.format(rawAddress);
                        break;
                }

                target.value = oldVal = newVal;
                if (ractive) ractive.updateModel();
                var caret = newVal.length - caretToEnd;
                target.setSelectionRange(caret, caret);
            };
        },
        ignoreThousandSeparators: function(e, ractive) {
            var target = e.target;
            if (target.selectionStart === target.selectionEnd) {
                var selection = target.selectionStart;
                var text = target.value;
                var thousandSeparator = ncc.get('texts.preferences.thousandSeparator');
                if (e.keyCode === 8) { // Backspace key
                    var tsStart = selection - thousandSeparator.length; // starting index of thousand separator (if any)
                    if (selection > 0 && text.substring(tsStart, selection) === thousandSeparator) {
                        target.value = text.substring(0, tsStart - 1) + text.substring(tsStart, text.length); // remove the character before thousand separator instead of the thousand separator
                        if (ractive) ractive.updateModel();
                        target.setSelectionRange(selection - 1, selection - 1);
                        e.preventDefault();
                    }
                }
                if (e.keyCode === 46) { // Delete key
                    var tsEnd = selection + thousandSeparator.length; // ending index of thousand separator (if any)
                    if (selection < text.length - 1 && text.substring(selection, tsEnd) === thousandSeparator) {
                        target.value = text.substring(0, tsEnd) + text.substring(tsEnd + 1, text.length); // remove the character after thousand separator instead of the thousand separator
                        if (ractive) ractive.updateModel();
                        target.setSelectionRange(tsEnd, tsEnd);
                        e.preventDefault();
                    }
                }
            }
        },
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

        // PROCESSING
        processTransaction: function(tx) {
            tx.isIncoming = tx.direction === 1 || tx.direction === 0;
            tx.isOutgoing = tx.direction === 2;
            tx.isSelf = tx.direction === 3;
            tx.formattedSender = Utils.format.address.format(tx.sender);
            tx.formattedRecipient = Utils.format.address.format(tx.recipient);
            tx.formattedFee = Utils.format.nem.formatNemAmount(tx.fee, {dimUnimportantTrailing: true, fixedDecimalPlaces: true});
            tx.formattedAmount = Utils.format.nem.formatNemAmount(tx.amount, {dimUnimportantTrailing: true, fixedDecimalPlaces: true});
            tx.formattedFullFee = Utils.format.nem.formatNemAmount(tx.fee);
            tx.formattedFullAmount = Utils.format.nem.formatNemAmount(tx.amount);
            tx.formattedDate = Utils.formatDate(tx.timeStamp, 'M dd, yyyy hh:mm:ss');
            return tx;
        },
        processTransactions: function(transactions) {
            if (transactions) {
                for (var i = 0; i < transactions.length; i++) {
                    Utils.processTransaction(transactions[i]);
                }
            }
            return transactions;
        },
        processHarvestedBlock: function(block) {
            if (!block.message) block.message = 'Block #' + block.height;
            if (!block.hash) block.hash = block.blockHash.data;
            if (!block.timeStamp && block.fee !== 0) block.timeStamp = block.timeStamp;
            if (!block.fee && block.fee !== 0) block.fee = block.totalFee;

            block.formattedTime = Utils.formatDate(block.timeStamp, 'M dd, yyyy hh:mm:ss');
            block.formattedFee = Utils.format.nem.formatNemAmount(block.fee, {dimUnimportantTrailing: true, fixedDecimalPlaces: true});
            return block;
        },
        processHarvestedBlocks: function(blocks) {
            for (var i = 0; i < blocks.length; i++) {
                Utils.processHarvestedBlock(blocks[i]);
            }
            return blocks;
        },
        processAccount: function(account) {
            account.formattedAddress = Utils.format.address.format(account.address);
            var balanceObj = Utils.format.nem.uNemToNem(account.balance);
            account.formattedBalance = Utils.format.nem.formatNem(balanceObj, {fixedDecimalPlaces: true});
            account.balanceInt = parseInt(balanceObj.intPart, 10);
            account.balanceDec = parseInt(balanceObj.decimalPart, 10);

            if (account.transactions) {
                account.transactions = Utils.processTransactions(account.transactions);
            }

            return account;
        },
        processWallet: function(wallet) {
            wallet.lastRefreshDate = Utils.toDate(wallet.lastRefresh).toString();
            wallet.daysPassed = Utils.daysPassed(wallet.lastRefresh);

            Utils.processAccount(wallet.primaryAccount);
            for (var i = 0; i < wallet.otherAccounts.length; i++) {
                Utils.processAccount(wallet.otherAccounts[i]);
            }

            return wallet;
        }
    };
    return Utils;
});