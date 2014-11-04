"use strict";

define({
    config: {
        fractionalDigits: 2,
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
            var self = this;
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
    restoreAddress: function(address) {
        return address.replace(/\-/g, '');
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
    formatCurrency: function(amount, dimTrailings, noLimitFractionalPart, noFixedDecimalPlaces) { // amount is in mNEM
        var nem = this.addThousandSeparators(Math.floor(this.toNem(amount)));
        var mNem = Math.floor(amount % 1000000);
        if (!noFixedDecimalPlaces) {
            mNem = this.minDigits(mNem, 6);
        }
        if (!noLimitFractionalPart) {
            mNem = mNem.substring(0, this.config.fractionalDigits);
        }

        if (dimTrailings) {
            mNem = mNem.toString(10);
            var cutPos = mNem.length - 1;
            while (mNem.charAt(cutPos) === '0') {
                cutPos--;
            }
            cutPos++;

            var clearPart = mNem.substring(0, cutPos);
            var dimmedPart = mNem.substring(cutPos, mNem.length);
            if (dimmedPart) {
                if (clearPart) {
                    return nem + ncc.get('texts.preferences.decimalSeparator') + clearPart + '<span class="dimmed">' + dimmedPart + '</span>';
                } else {
                    return nem + '<span class="dimmed">' + ncc.get('texts.preferences.decimalSeparator') + dimmedPart + '</span>';
                }
            }
        }

        return nem + ((noFixedDecimalPlaces && !mNem) ? '' : (ncc.get('texts.preferences.decimalSeparator') + mNem));
    },
    // @param {string} amount
    convertCurrencyFormat: function(amount, oldThousandSeparator, newThousandSeparator, oldDecimalSeparator, newDecimalSeparator) {
        if (oldThousandSeparator) {
            var thousandSeparatorRegex = new RegExp(this.escapeRegExp(oldThousandSeparator), 'g');
            amount = amount.replace(thousandSeparatorRegex, newThousandSeparator);
        }

        if (oldDecimalSeparator) {
            var decimalSeparatorRegex = new RegExp(this.escapeRegExp(oldDecimalSeparator), 'g');
            amount = amount.replace(decimalSeparatorRegex, newDecimalSeparator);
        }

        return amount;
    },
    convertCurrencyToStandard: function(amount) {
        return this.convertCurrencyFormat(amount, ncc.get('texts.preferences.thousandSeparator'), '', ncc.get('texts.preferences.decimalSeparator'), '.');
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

    // PROCESSING
    processTransaction: function(tx) {
        tx.isIncoming = tx.direction === 1 || tx.direction === 0;
        tx.isOutgoing = tx.direction === 2;
        tx.isSelf = tx.direction === 3;
        tx.formattedSender = this.formatAddress(tx.sender);
        tx.formattedRecipient = this.formatAddress(tx.recipient);
        tx.formattedFee = this.formatCurrency(tx.fee, true);
        tx.formattedAmount = this.formatCurrency(tx.amount, true);
        tx.formattedFullFee = this.formatCurrency(tx.fee, false, true, false);
        tx.formattedFullAmount = this.formatCurrency(tx.amount, false, true, false);
        tx.formattedDate = this.formatDate(tx.timeStamp, 'M dd, yyyy hh:mm:ss');
        return tx;
    },
    processTransactions: function(transactions) {
        if (transactions) {
            for (var i = 0; i < transactions.length; i++) {
                this.processTransaction(transactions[i]);
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
    }
});