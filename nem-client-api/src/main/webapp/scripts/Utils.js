"use strict";

define(['TransactionType'], function(TransactionType) {
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
            STATUS_SYNCHRONIZED: 6,
            STATUS_NO_REMOTE_NIS_AVAILABLE: 7,
            STATUS_LOADING: 8
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
        getClipboardText: function(ev) {
            var e = (ev && ev.clipboardData) ? ev : ev.originalEvent;
            return (e && e.clipboardData && e.clipboardData.getData('text/plain')) || (window.clipboardData && window.clipboardData.getData('Text'));
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

        // ADDRESS BOOK
        addressBook: {
            query: function(query) {
                var matches = [];
                var addressBook = ncc.get('contacts');
                if (addressBook) {
                    var regex = new RegExp(query, 'i');
                    $.each(addressBook, function(i, item) {
                        if (regex.test(item.address) || regex.test(item.privateLabel)) {
                            matches.push(item);
                        }
                    });
                }

                return matches;
            },
            findByAddress: function(address) {
                var label = undefined;
                var addressBook = ncc.get('contacts');
                if (addressBook) {
                    $.each(addressBook, function(i, item) {
                        if (item.address === address) {
                            label = item.privateLabel;
                            return false;
                        }
                    });
                }

                return label;
            }
        },

        // FORMAT & CONVERSON
        format: {
            minDigits: function(num, digits) {
                num = num.toString();
                if (num.length < digits) {
                    num = new Array(digits - num.length + 1).join('0') + num;
                }
                return num;
            },
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
                    // clever workaround to deal with JavaScript loss of precision bugs
                    // by using strings for multiplication
                    // the largest integer that can be stored in a java numeric type (64-bit floating point)
                    // is Math.pow(2, 53) > 8 * Math.pow(10, 15)
                    // [ Math.pow(2, 53) - 1 < Math.pow(2, 53) == Math.pow(2, 53) + 1 ]
                    // NOTE: this only works because nem is assumed to be a string

                    // determine the number of trailing zeros to add based on the index of the decimal
                    // (note that the nem amount object decimalPart MUST contain leading zeros)
                    var power = Utils.config.maxDecimalPlaces - nem.decimalPart.length;
                    if (power < 0) power = 0;
                    var trailingZeros = new Array(power + 1).join('0');
                    return (parseInt(nem.intPart, 10) || 0) * Utils.config.nemDivisibility + (parseInt(nem.decimalPart + trailingZeros, 10) || 0);
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
                },
            },
            address: {
                format: function(address) {
                    if (!address) return address;
                    var segments = address.substring(0, Utils.config.addressCharacters).match(/.{1,6}/g) || [];
                    return segments.join('-').toUpperCase();
                },
                restore: function(formattedAddress) {
                    if (!formattedAddress) return formattedAddress;
                    var address = formattedAddress.replace(/\-/g, '');
                    var separatorIndex = address.indexOf(' ');
                    return address.substr(separatorIndex + 1);
                }
            },
            date: {
                shortMonths: {
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
                },
                format: function(date, format) {
                    if (typeof date === 'number') {
                        date = new Date(date);

                        var month = date.getMonth() + 1;
                        var day = date.getDate();
                        var year = date.getFullYear();
                        var hour = date.getHours();
                        var min = date.getMinutes();
                        var sec = date.getSeconds();

                        switch (format) {
                            case 'MM/dd/yy hh:mm:ss':
                                month = Utils.format.minDigits(month, 2);
                                day = Utils.format.minDigits(day, 2);
                                year = year.toString(10);
                                year = year.substring(year.length - 2, year.length);
                                hour = Utils.format.minDigits(hour, 2);
                                min = Utils.format.minDigits(min, 2);
                                sec = Utils.format.minDigits(sec, 2);
                                return month + '/' + day + '/' + year + ' ' + hour + ':' + min + ':' + sec;
                            case 'M dd, yyyy':
                                month = this.shortMonths[month];
                                day = Utils.format.minDigits(day, 2);
                                return month + ' ' + day + ', ' + year;
                            case 'M dd, yyyy hh:mm:ss':
                                month = this.shortMonths[month];
                                day = Utils.format.minDigits(day, 2);
                                hour = Utils.format.minDigits(hour, 2);
                                min = Utils.format.minDigits(min, 2);
                                sec = Utils.format.minDigits(sec, 2);
                                return month + ' ' + day + ', ' + year + ' ' + hour + ':' + min + ':' + sec;
                        }
                    } else {
                        return date;
                    }
                }
            },
        },
        mask: {
            charsAllowed: {
                nem: function() {
                    return new RegExp('^[0-9' + Utils.escapeRegExp(ncc.get('texts.preferences.decimalSeparator')) + ']$');
                },
                address: /^[2-7a-zA-Z]$/,
                number: /^[0-9]$/,
                privateKey: /^[0-9a-fA-F]$/,
            },
            transform: {
                address: function(char) {
                    return char.toUpperCase();
                }
            },
            reformat: {
                nem: function(val) {
                    return Utils.format.nem.formatNem(Utils.format.nem.stringToNem(val), {keepTrailingZeroes: true});
                },
                address: function(val) {
                    return Utils.format.address.format(val.replace(/[^0-9a-zA-Z]/g, ''));
                }
            },
            caretStickToRight: {
                'default': function(oldCaret, oldText, newText) {
                    return oldCaret + newText.length - oldText.length;
                },
                nem: function(oldCaret, oldText, newText) {
                    var decimalSeparator = ncc.get('texts.preferences.decimalSeparator');
                    var tsLength = ncc.get('texts.preferences.thousandSeparator').length;
                    var chunkLength = 3 + tsLength;

                    var oldDsPos = oldText.indexOf(decimalSeparator);
                    var oldIntPartLength = oldDsPos !== -1 ? oldDsPos : oldText.length;
                    if (oldCaret <= oldIntPartLength) { // the change is in the integer part, not affecting the decimal separator
                        var newCaret = newText.length - oldText.length + oldCaret;
                    } else {
                        var oldCaretToRight = oldText.length - oldCaret;
                        var decPartLength = oldText.length - oldIntPartLength;
                        oldCaretToRight = oldCaretToRight - decPartLength;
                        var wholeChunks = Math.floor(oldCaretToRight / chunkLength);
                        var realCaretToRight = (decPartLength ? decPartLength - decimalSeparator.length : 0) + (oldCaretToRight - wholeChunks * tsLength);

                        var newDsPos = newText.indexOf(decimalSeparator);
                        var newIntPartLength = newDsPos !== -1 ? newDsPos : newText.length;
                        var newRealCaret = newText.length - realCaretToRight;
                        if (newRealCaret >= newIntPartLength + decimalSeparator.length) {
                            var newCaret = newRealCaret;
                        } else {
                            var newDecPartLength = newText.length - newIntPartLength;
                            realCaretToRight = realCaretToRight - newDecPartLength;
                            var newWholeChunks = Math.floor(realCaretToRight / chunkLength);
                            var newCaretToRight = (newDecPartLength - decimalSeparator.length) + (realCaretToRight + newWholeChunks * tsLength);
                        }
                    }

                    return newCaret;
                },
                address: function(oldCaret, oldText, newText) {
                    var oldCaretToRight = oldText.length - oldCaret;

                    var lastChunk = oldText.length % 7; // 6 characters and 1 dash
                    if (oldCaretToRight <= lastChunk) {
                        var realCaretToRight = oldCaretToRight;
                    } else {
                        oldCaretToRight = oldCaretToRight - (lastChunk? lastChunk + 1 : 0);
                        var wholeChunks = Math.floor(oldCaretToRight / 7);
                        var realCaretToRight = lastChunk + (oldCaretToRight - wholeChunks * 1);
                    }

                    var newLastChunk = newText.length % 7;
                    if (realCaretToRight <= newLastChunk) {
                        var newCaret = newText.length - realCaretToRight;
                    } else {
                        realCaretToRight = realCaretToRight - newLastChunk;
                        var newWholeChunks = Math.floor(realCaretToRight / 6);
                        var newCaretToRight = (newLastChunk? newLastChunk + 1 : 0) + (realCaretToRight + newWholeChunks * 1);
                        var newCaret = newText.length - newCaretToRight;
                    }

                    return newCaret;
                }
            },
            caretStickToLeft: {
                'default': function(oldCaret) {
                    return oldCaret;
                },
                nem: function(oldCaret, oldText, newText) {
                    var decimalSeparator = ncc.get('texts.preferences.decimalSeparator');
                    var tsLength = ncc.get('texts.preferences.thousandSeparator').length;
                    var chunkLength = 3 + tsLength;

                    var oldDsPos = oldText.indexOf(decimalSeparator);
                    var oldIntPartLength = oldDsPos !== -1 ? oldDsPos : oldText.length;
                    var lastChunk = oldIntPartLength % chunkLength;
                    if (oldCaret <= lastChunk) {
                        var realCaret = oldCaret;
                    } else {
                        if (oldCaret > oldIntPartLength) {
                            var afterDs = oldCaret - oldIntPartLength - decimalSeparator.length;
                            oldCaret = oldIntPartLength;
                        }
                        oldCaret = oldCaret - (lastChunk? lastChunk + tsLength : 0);
                        var wholeChunks = Math.floor(oldCaret / chunkLength);
                        var realCaret = lastChunk + (oldCaret - wholeChunks * tsLength) + (afterDs || 0);
                    }

                    var newDsPos = newText.indexOf(decimalSeparator);
                    var newIntPartLength = newDsPos !== -1 ? newDsPos : newText.length;
                    var newLastChunk = newIntPartLength % chunkLength;
                    if (realCaret <= newLastChunk) {
                        var newCaret = realCaret;
                    } else {
                        var newRealIntDigits = newIntPartLength - Math.floor(newIntPartLength / chunkLength) * tsLength;
                        if (realCaret > newRealIntDigits) {
                            var newCaret = newIntPartLength + decimalSeparator.length + (realCaret - newRealIntDigits);
                        } else {
                            realCaret = realCaret - newLastChunk;
                            var newWholeChunks = Math.floor(realCaret / 3);
                            var newCaret = (newLastChunk? newLastChunk + tsLength : 0) + (realCaret + newWholeChunks * tsLength);
                        }
                    }

                    return newCaret;
                },
                address: function(oldCaret, oldText, newText) {
                    return oldCaret;
                }
            },
            skippedToken: {
                nem: function() {
                    return ncc.get('texts.preferences.thousandSeparator');
                },
                address: '-'
            },
            reformatTextbox: function(target, type, text, ractive, dontReformat) {
                if (! dontReformat) {
                    var reformat = this.reformat[type];
                    if (typeof reformat === 'function') {
                        text = reformat(text);
                    }
                }
                target.value = text;
                if (ractive) ractive.updateModel();
                return text;
            },
            insertInput: function(input, target, type, ractive) {
                var caretStart = target.selectionStart;
                var caretEnd = target.selectionEnd;
                var oldText = target.value;
                var newText = oldText.substring(0, caretStart) + input + oldText.substring(caretEnd, oldText.length);

                newText = this.reformatTextbox(target, type, newText, ractive);

                var caretStickToRight = this.caretStickToRight[type] || this.caretStickToRight['default'];
                if (typeof caretStickToRight === 'function') {
                    var newCaret = caretStickToRight(caretEnd, oldText, newText);
                    target.setSelectionRange(newCaret, newCaret);
                }
            },
            keypress: function(e, type, ractive) {
                // Pasting in  Firefox also fires keypress event (stupid Firefox!)
                // so we have to detect if Alt or Ctrl key is on
                var char = String.fromCharCode(e.which);
                var nonPrintables = /[\x00-\x1F]/;
                if (e.altKey || e.ctrlKey || nonPrintables.test(char)) return;

                e.preventDefault();
                var chars = this.charsAllowed[type];
                if (typeof chars === 'function') {
                    chars = chars();
                }

                // Test if char is allowed
                if (!chars || !char || !chars.test(char)) return;

                // Perform transformations (if any)
                var target = e.target;
                var transform = this.transform[type];
                if (typeof transform === 'function') {
                    char = transform(char);
                }

                // Apply inputted char
                this.insertInput(char, target, type, ractive);
            },
            paste: function(e, type, ractive) {
                e.preventDefault();
                var pastedText = Utils.getClipboardText(e);
                this.insertInput(pastedText, e.target, type, ractive);
            },
            keydown: function(e, type, ractive) {
                var target = e.target;
                var oldText = target.value;
                var caret = target.selectionStart;
                var token = this.skippedToken[type];
                if (typeof token === 'function') {
                    token = token();
                }
                if (!token || typeof token !== 'string') return;

                if (e.keyCode === 8) { // Backspace key
                    e.preventDefault();
                    if (target.selectionStart !== target.selectionEnd) {
                        this.insertInput('', target, type, ractive);
                    } else {
                        var tokenStart = caret - token.length; // starting index of the token (if any)
                        var deletedPos = oldText.substring(tokenStart, caret) === token ? tokenStart : caret;
                        var newText = oldText.substring(0, deletedPos - 1) + oldText.substring(deletedPos, oldText.length); // remove the character before thousand separator instead of the thousand separator

                        // TODO this is hack for removal of first digit, this is not a proper solution,
                        // this might cause some weird problems
                        var dontReformat = false;
                        if (type === 'nem' && deletedPos == 1 && oldText.length != 1) {
                            dontReformat = true;
                        }
                        newText = this.reformatTextbox(target, type, newText, ractive, dontReformat);

                        var caretStickToRight = this.caretStickToRight[type] || this.caretStickToRight['default'];
                        if (typeof caretStickToRight === 'function') {
                            var newCaret = caretStickToRight(caret, oldText, newText);
                            target.setSelectionRange(newCaret, newCaret);
                        }
                    }
                }
                if (e.keyCode === 46) { // Delete key
                    e.preventDefault();
                    if (target.selectionStart !== target.selectionEnd) {
                        this.insertInput('', target, type, ractive);
                    } else {
                        var tokenEnd = caret + token.length; // ending index of the token (if any)
                        var deletedPos = oldText.substring(caret, tokenEnd) === token ? tokenEnd : caret;
                        var newText = oldText.substring(0, deletedPos) + oldText.substring(deletedPos + 1, oldText.length); // remove the character after thousand separator instead of the thousand separator

                        // TODO this is hack for removal of first digit, this is not a proper solution,
                        // this might cause some weird problems
                        var dontReformat = false;
                        if (type === 'nem' && deletedPos === 0 && oldText.length != 1) {
                            dontReformat = true;
                        }
                        newText = this.reformatTextbox(target, type, newText, ractive, dontReformat);

                        var caretStickToLeft = this.caretStickToLeft[type] || this.caretStickToLeft['default'];
                        if (typeof caretStickToLeft === 'function') {
                            var newCaret = caretStickToLeft(caret, oldText, newText);
                            target.setSelectionRange(newCaret, newCaret);
                        }
                    }
                }
            },
        },
        typeahead: {
            addressBookMatcher: function(query, cb) {
                var matches = Utils.addressBook.query(query);
                cb(matches);
            },
            justFormatAddress: function(query, cb) {
                var suggestion = {
                    text: ncc.get('texts.common.justUse'),
                    formattedAddress: Utils.format.address.format(query)
                };
                cb([suggestion]);
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
            var transferTransaction = tx;
            var currentFee = 0;

            // there are three types now, to make ui templates simpler:
            // tx.type - this is type of transaction
            // tx.maintype - this is either type of transaction or in case of multisig type of inner transaction
            // tx.innerType - only avail for multisig transactions, this is shortcut for tx.inner.type
            tx.mainType = tx.type;

            // multisig
            if (tx.type === TransactionType.Multisig_Transfer || tx.type === TransactionType.Multisig_Aggregate_Modification) {
                tx.isMultisig = true;
                tx.cosignatoriesCount = "#cosigs " + tx.signatures.length;
                tx.innerType = tx.inner.type;
                tx.mainType = tx.inner.type;

                tx.multisig={};
                tx.multisig.formattedFrom = Utils.format.address.format(tx.inner.sender);
                tx.multisig.formattedInnerFee = Utils.format.nem.formatNemAmount(tx.inner.fee, {dimUnimportantTrailing: true, fixedDecimalPlaces: true});

                if (tx.type === TransactionType.Multisig_Transfer) {
                    tx.multisig.formattedTo = Utils.format.address.format(tx.inner.recipient);
                    tx.multisig.formattedAmount = Utils.format.nem.formatNemAmount(tx.inner.amount, {dimUnimportantTrailing: true, fixedDecimalPlaces: true});

                    transferTransaction = tx.inner;
                    tx.recipient = transferTransaction.recipient
                    tx.message = tx.inner.message;
                }

                var mutltisigFees = tx.fee + tx.signatures
                    .map(function(d){return d.fee})
                    .reduce(function(p,c){return p+c}, 0);
                var totalFees = mutltisigFees + tx.inner.fee;

                tx.multisig.formattedTotalMultisigFees = Utils.format.nem.formatNemAmount(mutltisigFees, {dimUnimportantTrailing: true, fixedDecimalPlaces: true});
                tx.multisig.formattedTotalFees = Utils.format.nem.formatNemAmount(totalFees, {dimUnimportantTrailing: true, fixedDecimalPlaces: true});

                var totalCost = totalFees + tx.inner.amount;
                tx.multisig.formattedTotal = Utils.format.nem.formatNemAmount(totalCost, {dimUnimportantTrailing: true, fixedDecimalPlaces: true});

                tx.multisig.formattedDeadline = Utils.format.date.format(tx.deadline, 'M dd, yyyy hh:mm:ss');
                tx.multisig.validMinutes = (tx.inner.deadline - tx.inner.timeStamp) / 1000 / 60;

                currentFee = 0;
            }
            else
            {
                tx.isMultisig = false;
                currentFee = tx.fee;
            }

            // importance transfer
            if (tx.type == TransactionType.Importance_Transfer) {
                tx.recipient = tx.remote;
                tx.isIncoming = false;
                tx.isOutgoing = false;
                tx.isSelf = true;
            }

            if (transferTransaction.type == TransactionType.Transfer)
            {
                tx.isIncoming = transferTransaction.direction === 1; //  || transferTransaction.direction === 0;
                tx.isOutgoing = transferTransaction.direction === 2;
                tx.isSelf = transferTransaction.direction === 3;
            }

            tx.formattedSender = Utils.format.address.format(tx.sender);
            tx.formattedFee = Utils.format.nem.formatNemAmount(currentFee, {dimUnimportantTrailing: true, fixedDecimalPlaces: true});
            tx.formattedFullFee = Utils.format.nem.formatNemAmount(currentFee);
            tx.formattedDate = Utils.format.date.format(tx.timeStamp, 'M dd, yyyy hh:mm:ss');

            tx.formattedRecipient = Utils.format.address.format(transferTransaction.recipient);
            tx.formattedAmount = Utils.format.nem.formatNemAmount(transferTransaction.amount, {dimUnimportantTrailing: true, fixedDecimalPlaces: true});
            tx.formattedFullAmount = Utils.format.nem.formatNemAmount(transferTransaction.amount);
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
            if (!block.timeStamp && block.fee !== 0) block.timeStamp = block.timeStamp;
            if (!block.fee && block.fee !== 0) block.fee = block.totalFee;

            block.formattedTime = Utils.format.date.format(block.timeStamp, 'M dd, yyyy hh:mm:ss');
            block.formattedFee = Utils.format.nem.formatNemAmount(block.fee, {dimUnimportantTrailing: true, fixedDecimalPlaces: true});
            block.formattedDifficulty = ((100 * block.difficulty) / 100000000000000).toFixed(2) + '%';
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
            account.formattedVestedBalance = Utils.format.nem.formatNem(Utils.format.nem.uNemToNem(account.vestedBalance), {fixedDecimalPlaces: true});
            account.balanceInt = parseInt(balanceObj.intPart, 10);
            account.balanceDec = parseInt(balanceObj.decimalPart, 10);
            account.isMultisig = account.cosignatories.length > 0;

            account.formattedBalanceInt = Utils.format.nem.formatNem({intPart: balanceObj.intPart, decimalPart: ''}, {fixedDecimalPlaces: false});
            if (account.transactions) {
                account.transactions = Utils.processTransactions(account.transactions);
            }

            return account;
        },
        processWallet: function(wallet) {
            wallet.lastRefreshDate = new Date(wallet.lastRefresh).toString();
            wallet.daysPassed = Utils.daysPassed(wallet.lastRefresh);

            Utils.processAccount(wallet.primaryAccount);
            for (var i = 0; i < wallet.otherAccounts.length; i++) {
                Utils.processAccount(wallet.otherAccounts[i]);
            }

            return wallet;
        },
        processContacts: function(ab) {
            for (var i = 0; i < ab.length; i++) {
                ab[i].formattedAddress = Utils.format.address.format(ab[i].address);
            }

            return ab;
        },
        removeContact: function(ab, address) {
            for (var i = 0; i < ab.length; i++) {
                if (ab[i].address === address) {
                    ab.splice(i, 1);
                }
            }

            return ab;
        }
    };
    return Utils;
});