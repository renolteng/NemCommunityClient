"use strict";

define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
    return $.extend(true, {}, NccLayout, {
        name: 'wallet',
        template: 'rv!layout/wallet',
        initOnce: function() {
            ncc.refreshWallet = function(wallet, silent) {
                if (!wallet) wallet = ncc.get('wallet.name');

                ncc.postRequest('wallet/info', { wallet: wallet }, function(data) {
                    ncc.set('wallet', ncc.processWallet(data));
                }, null, silent);
            };

            ncc.refreshAccount = function(wallet, account, silent) {
                if (!wallet) wallet = ncc.get('wallet.name');
                if (!account) account = ncc.get('activeAccount.address');

                var success = false;
                ncc.postRequest('account/transactions/all', { wallet: wallet, account: account }, function(data) {
                    success = true;
                    ncc.set('activeAccount', ncc.processAccount(data));
                    ncc.set('status.lostConnection', false);
                }, {
                    complete: function() {
                        if (!success) {
                            ncc.set('status.lostConnection', true);
                        }
                    }
                }, silent);
            };

            ncc.refreshInfo = function(wallet, account, silent) {
                ncc.refreshWallet(wallet, silent);
                ncc.refreshAccount(wallet, account, silent);
            };

            ncc.showTempMessage = function(message, duration) {
                if (!duration) {
                    duration = 2000;
                }
                this.set('tempMessage.message', message);
                this.set('tempMessage.visible', true);
                var self = this;
                window.setTimeout(function() {
                    self.set('tempMessage.visible', false);
                }, duration);
            };

            ncc.showBootModal = function(message) {
                var account = ncc.get('activeAccount.address');
                var accountLabel = ncc.get('activeAccount.label');
                var wallet = ncc.get('wallet.name');
                ncc.showInputForm(ncc.get('texts.modals.bootLocalNode.title'), message,
                    [
                        {
                            name: 'account',
                            type: 'text',
                            readonly: true,
                            label: {
                                content: ncc.get('texts.modals.bootLocalNode.account')
                            },
                            sublabel: accountLabel?
                                {
                                    content: accountLabel
                                } :
                                {
                                    content: ncc.get('texts.modals.bootLocalNode.noLabel'),
                                    nullContent: true
                                }
                        }, 
                        {
                            name: 'wallet',
                            type: 'text',
                            readonly: true,
                            label: {
                                content: ncc.get('texts.modals.bootLocalNode.wallet')
                            }
                        }, 
                        {
                            name: 'node_name', 
                            type: 'text',
                            label: {
                                content: ncc.get('texts.modals.bootLocalNode.node')
                            }
                        }
                    ],
                    {
                        account: ncc.formatAddress(account),
                        wallet: wallet
                    },
                    function(values, closeModal) {
                        var self = this;
                        values.account = account;

                        this.lockAction();
                        ncc.postRequest('node/boot', values, 
                            function(data) {
                                closeModal();
                                ncc.set('status.nodeBooted', true);
                                ncc.refreshNisInfo();
                            },
                            {
                                altFailCb: function(faultId) {
                                    if (601 === faultId) {
                                        ncc.set('status.nodeBooted', true);
                                        closeModal();
                                    }
                                },
                                complete: function() {
                                    self.unlockAction();
                                }
                            }
                        );
                        return false;
                    }, 
                    ncc.get('texts.modals.bootLocalNode.boot'), 
                    ncc.get('texts.modals.bootLocalNode.booting')
                );
            };

            ncc.on('registerScrollableSidebar', function(e) {
                var $sidebarNav = $(e.node);
                var navBottom = $sidebarNav.offset().top + $sidebarNav.outerHeight();
                var decideSidebarScrollability = function() {
                    if (navBottom > global.$window.height()) {
                        ncc.set('walletPage.sidebarScrollable', true);
                    } else {
                        ncc.set('walletPage.sidebarScrollable', false);
                    }
                };

                global.$window.on('resize.scrollableSidebar', decideSidebarScrollability);
                decideSidebarScrollability();
            });
        },
        initEverytime: function(params) {
            var wallet = (params && params.wallet) || ncc.getUrlParam('wallet');
            if (!wallet) {
                ncc.loadPage('landing');
                return true;
            } else {
                if (!ncc.get('wallet') || ncc.get('wallet.name') != wallet) {
                    ncc.set('wallet.name', wallet);
                    ncc.refreshWallet(wallet);
                }
            }

            var account = (params && params.account) || ncc.getUrlParam('account');
            if (!account) {
                ncc.loadPage('landing');
                return true;
            } else {
                if (!ncc.get('activeAccount') || ncc.get('activeAccount.address') != account) {
                    ncc.set('activeAccount.address', account);
                    ncc.refreshAccount(wallet, account);
                }
            }
        },
        setupEverytime: function() {
            var local = this.local;
            var global = ncc.global;

            require(['zeroClipboard'], function(ZeroClipboard) {
                local.client = new ZeroClipboard($('#addressClipboard'));

                local.client.on('ready', function() {
                    local.client.on('aftercopy', function() {
                        ncc.showTempMessage(ncc.get('texts.wallet.copiedToClipboard'));
                    });
                });
            });

            local.listeners.push(ncc.on({
                toggleSidebar: function() {
                    ncc.set('walletPage.miniSidebar', !ncc.get('walletPage.miniSidebar'));
                },
                openSendNem: function() {
                    if (ncc.get('status.nodeBooted')) {
                        ncc.showModal('sendNem');
                    } else {
                        ncc.showMessage(ncc.get('texts.modals.notBootedWarning.title'), ncc.get('texts.modals.notBootedWarning.message'), function() {
                            ncc.showBootModal();
                        });
                    }
                },
                showClientInfo: function() {
                    var modal = ncc.getModal('clientInfo');
                    modal.open();

                    modal.set('loadingNcc', true);
                    ncc.getRequest('info/ncc',
                        function(data) {
                            ncc.set('ncc', data);
                        },
                        {
                            complete: function() {
                                modal.set('loadingNcc', false);
                            }
                        }
                    );

                    modal.set('loadingNis', true);
                    ncc.getRequest('info/nis',
                        function(data) {
                        	var lastBlockBehind = (data.nodeMetaData.maxBlockChainHeight - data.nodeMetaData.nodeBlockChainHeight) * 60;
                            ncc.set('nis', data);
                            ncc.set('nis.nodeMetaData.lastBlockBehind', lastBlockBehind < 0? 0 : lastBlockBehind);
                        },
                        {
                            complete: function() {
                                modal.set('loadingNis', false);
                            }
                        }
                    );
                },
                closeWallet: function() {
                    ncc.showConfirmation(ncc.get('texts.modals.closeWallet.title'), ncc.get('texts.modals.closeWallet.message'), {
                        yes: function() {
                            ncc.postRequest('wallet/close', { wallet: ncc.get('wallet.name') }, function(data) {
                                if (data.ok) {
                                    ncc.loadPage('landing');
                                } else {
                                    ncc.showError();
                                }
                            });
                        }
                    });
                },
                switchAccount: function(e, newAccount) {
                    var currentAccount = ncc.get('activeAccount.address');
                    if (currentAccount !== newAccount) {
                        var layouts = ncc.get('layout');
                        var currentPage = layouts[layouts.length - 1].name;
                        var wallet = ncc.get('wallet.name');
                        ncc.loadPage(currentPage, { wallet: wallet, account: newAccount });
                    }
                },
                refreshInfo: function() {
                    this.refreshInfo();
                },
                createNewAccount: function() {
                    var wallet = ncc.get('wallet.name');
                    ncc.showInputForm(ncc.get('texts.modals.createAccount.title'), '',
                        [
                            {
                                name: 'label',
                                type: 'text',
                                label: {
                                    content: ncc.get('texts.modals.createAccount.label')
                                }
                            },
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: ncc.get('texts.modals.createAccount.wallet')
                                }
                            },
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: ncc.get('texts.modals.createAccount.password')
                                }
                            }
                        ],
                        {
                            wallet: wallet
                        },
                        function(values, closeModal) {
                            ncc.postRequest('wallet/account/new', values, function(data) {
                                if (data.address) {
                                    var label = data.label;
                                    ncc.showMessage(ncc.get('texts.modals.common.success'), ncc.fill(ncc.get('texts.modals.createAccount.successMessage'), ncc.formatAddress(data.address), label));
                                    ncc.refreshAccount(null, data.address, true);
                                    ncc.refreshWallet();
                                    closeModal();
                                } else {
                                    ncc.showError();
                                }
                            });
                            return false;
                        },
                        ncc.get('texts.modals.createAccount.create')
                    );
                },
                addAccount: function() {
                    var wallet = ncc.get('wallet.name');
                    ncc.showInputForm(ncc.get('texts.modals.addAccount.title'), '',
                        [
                            {
                                name: 'account_key',
                                type: 'text',
                                label: {
                                    content: ncc.get('texts.modals.addAccount.privateKey')
                                }
                            },
                            {
                                name: 'label',
                                type: 'text',
                                label: {
                                    content: ncc.get('texts.modals.addAccount.label')
                                }
                            },
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: ncc.get('texts.modals.addAccount.wallet')
                                }
                            },
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: ncc.get('texts.modals.addAccount.password')
                                }
                            }
                        ],
                        {
                            wallet: wallet
                        },
                        function(values, closeModal) {
                            ncc.postRequest('wallet/account/add', values, function(data) {
                                if (data.address) {
                                    var label = data.label;
                                    ncc.showMessage(ncc.get('texts.modals.common.success'), ncc.fill(ncc.get('texts.modals.addAccount.successMessage'), ncc.formatAddress(data.address), label));
                                    ncc.refreshAccount(null, data.address, true);
                                    ncc.refreshWallet();
                                    closeModal();
                                } else {
                                    ncc.showError();
                                }
                            });
                            return false;
                        },
                        ncc.get('texts.modals.addAccount.add')
                    );
                },
                setCurrentAccountAsPrimary: function() {
                    var account = ncc.get('activeAccount.address');
                    var accountLabel = ncc.get('activeAccount.label');
                    var wallet = ncc.get('wallet.name');
                    ncc.showInputForm(ncc.get('texts.modals.setPrimary.title'), '',
                        [
                            {
                                name: 'account',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: ncc.get('texts.modals.setPrimary.account')
                                },
                                sublabel: accountLabel?
                                    {
                                        content: accountLabel
                                    } :
                                    {
                                        content: ncc.get('texts.modals.setPrimary.noLabel'),
                                        nullContent: true
                                    }
                            }, 
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: ncc.get('texts.modals.setPrimary.wallet')
                                }
                            }, 
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: ncc.get('texts.modals.setPrimary.password')
                                }
                            }
                        ],
                        {
                            account: ncc.formatAddress(account),
                            wallet: wallet
                        },
                        function(values, closeModal) {
                            values.account = account;
                            ncc.postRequest('wallet/account/primary', values, function(data) {
                                ncc.showMessage(ncc.get('texts.modals.common.success'), ncc.fill(ncc.get('texts.modals.setPrimary.successMessage'), ncc.formatAddress(account), accountLabel));
                                ncc.set('wallet', ncc.processWallet(data));
                                closeModal();
                            });
                            return false;
                        },
                        ncc.get('texts.modals.setPrimary.set')
                    );
                },
                bootLocalNode: function(e, message) {
                    ncc.showBootModal(message);
                },
                viewTransaction: (function() {
                    var modal = ncc.getModal('transactionDetails');
                    modal.set('formatCurrency', ncc.formatCurrency.bind(ncc));
                    return function(e, transaction) {
                        modal.set('transaction', transaction);
                        modal.open();
                    };
                })(),
                changeWalletName: function() {
                    var wallet = ncc.get('wallet.name');
                    ncc.showInputForm(ncc.get('texts.modals.changeWalletName.title'), '',
                        [
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: ncc.get('texts.modals.changeWalletName.wallet')
                                }
                            },
                            {
                                name: 'new_name',
                                type: 'text',
                                label: {
                                    content: ncc.get('texts.modals.changeWalletName.newName')
                                }
                            },
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: ncc.get('texts.modals.changeWalletName.password')
                                }
                            }
                        ],
                        {
                            wallet: wallet
                        },
                        function(values, closeModal) {
                            ncc.postRequest('wallet/name/change', values, function(data) {
                                var newWalletName = values['new_name'];
                                ncc.showMessage(ncc.get('texts.modals.common.success'), ncc.fill(ncc.get('texts.modals.changeWalletName.successMessage'), wallet, newWalletName));
                                ncc.set('wallet', ncc.processWallet(data));
                                closeModal();
                            });
                            return false;
                        },
                        ncc.get('texts.modals.changeWalletName.change')
                    );
                },
                changeWalletPassword: function() {
                    var wallet = ncc.get('wallet.name');
                    ncc.showInputForm(ncc.get('texts.modals.changeWalletPassword.title'), '',
                        [
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: ncc.get('texts.modals.changeWalletPassword.wallet')
                                }
                            },
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: ncc.get('texts.modals.changeWalletPassword.password')
                                }
                            },
                            {
                                name: 'new_password',
                                type: 'password',
                                label: {
                                    content: ncc.get('texts.modals.changeWalletPassword.newPassword')
                                }
                            },
                            {
                                name: 'confirmPassword',
                                type: 'password',
                                label: {
                                    content: ncc.get('texts.modals.changeWalletPassword.confirmPassword')
                                }
                            }
                        ],
                        {
                            wallet: wallet
                        },
                        function(values, closeModal) {
                            if (values['new_password'] === values.confirmPassword) {
                                values.confirmPassword = undefined;
                                ncc.postRequest('wallet/password/change', values, function(data) {
                                    ncc.showMessage(ncc.get('texts.modals.common.success'), ncc.get('texts.modals.changeWalletPassword.successMessage'));
                                    closeModal();
                                });
                            } else {
                                ncc.showMessage(ncc.get('texts.modals.changeWalletPassword.passwordNotMatchTitle'), ncc.get('texts.modals.changeWalletPassword.passwordNotMatchMessage'));
                            }
                            return false;
                        },
                        ncc.get('texts.modals.changeWalletPassword.change')
                    );
                },
                changeAccountLabel: function() {
                    var wallet = ncc.get('wallet.name');
                    var account = ncc.get('activeAccount.address');
                    var accountLabel = ncc.get('activeAccount.label');
                    ncc.showInputForm(ncc.get('texts.modals.changeAccountLabel.title'), '',
                        [
                            {
                                name: 'label',
                                type: 'text',
                                label: {
                                    content: ncc.get('texts.modals.changeAccountLabel.label')
                                }
                            },
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: ncc.get('texts.modals.changeAccountLabel.wallet')
                                }
                            },
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: ncc.get('texts.modals.changeAccountLabel.password')
                                }
                            }
                        ],
                        {
                            wallet: wallet,
                            account: account
                        },
                        function(values, closeModal) {
                            ncc.postRequest('wallet/account/label', values, function(data) {
                                var label = values.label;
                                ncc.showMessage(
                                    ncc.get('texts.modals.common.success'), 
                                    ncc.fill(ncc.get('texts.modals.changeAccountLabel.successMessage'), ncc.formatAddress(account), label)
                                );
                                ncc.set('activeAccount', ncc.processAccount(data));
                                ncc.refreshWallet();
                                closeModal();
                            });
                            return false;
                        }, 
                        ncc.get('texts.modals.changeAccountLabel.change')
                    );
                },
                removeAccount: function() {
                    var wallet = ncc.get('wallet.name');
                    var account = ncc.get('activeAccount.address');
                    var accountLabel = ncc.get('activeAccount.label');
                    ncc.showInputForm(ncc.get('texts.modals.removeAccount.title'), 
                        ncc.get('texts.modals.removeAccount.warning'),
                        [
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                label: {
                                    content: ncc.get('texts.modals.removeAccount.wallet')
                                }
                            },
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: ncc.get('texts.modals.removeAccount.password')
                                }
                            }
                        ],
                        {
                            wallet: wallet,
                            account: account
                        },
                        function(values, closeModal) {
                            ncc.postRequest('wallet/account/remove', values, function(data) {
                                ncc.showMessage(
                                    ncc.get('texts.modals.common.success'), 
                                    ncc.fill(ncc.get('texts.modals.removeAccount.successMessage'), ncc.formatAddress(account), accountLabel)
                                );
                                ncc.set('wallet', ncc.processWallet(data));
                                ncc.fire('switchAccount', null, data.primaryAccount.address);
                                closeModal();
                            });
                            return false;
                        }, 
                        ncc.get('texts.modals.removeAccount.remove')
                    );
                },
                startHarvesting: function() {
                    var account = ncc.get('activeAccount.address');
                    var wallet = ncc.get('wallet.name');
                    ncc.postRequest('wallet/account/unlock', 
                    {
                        wallet: wallet,
                        account: account
                    }, function(data) {
                        if (!data.ok) {
                            ncc.showError();
                        }
                    }, 
                    {
                        complete: function() {
                            ncc.refreshAccount(null, null, true);
                        }
                    });
                },
                stopHarvesting: function() {
                    var account = ncc.get('activeAccount.address');
                    var wallet = ncc.get('wallet.name');
                    ncc.postRequest('wallet/account/lock', 
                    {
                        wallet: wallet,
                        account: account
                    }, function(data) {
                        if (!data.ok) {
                            ncc.showError();
                        }
                    }, 
                    {
                        complete: function() {
                            ncc.refreshAccount(null, null, true);
                        }
                    });
                }
            }));

            local.intervalJobs.push(setInterval(ncc.refreshAccount.bind(null, null, null, true), local.autoRefreshInterval));

            if (!ncc.get('status.nodeBooted')) {
                ncc.checkNodeStatus(null, function() {
                    if (ncc.get('settings.nisBootInfo.bootNis')) {
                        var bootData = {
                            node_name: ncc.get('settings.nisBootInfo.nodeName'),
                            wallet: ncc.get('wallet.name'),
                            account: ncc.get('settings.nisBootInfo.account') || ncc.get('wallet.primaryAccount.address')
                        };

                        ncc.set('status.booting', true);
                        ncc.postRequest('node/boot', bootData, 
                            function(data) {
                                ncc.set('status.nodeBooted', true);
                                ncc.refreshNisInfo();
                            },
                            {
                                altFailCb: function(faultId) {
                                    if (601 === faultId) {
                                        ncc.set('status.nodeBooted', true);
                                    }
                                },
                                complete: function() {
                                    ncc.set('status.booting', false);

                                    // If booting fails
                                    if (!ncc.get('status.nodeBooted')) {
                                        ncc.showBootModal(ncc.get('texts.wallet.bootNodeWarning'));
                                    }
                                }
                            },
                            true
                        );
                    } else {
                        ncc.showBootModal(ncc.get('texts.wallet.bootNodeWarning'));
                    }
                });
            }

            require(['maskedinput'], function() {
                var pattern1 = function() {
                    return '#' + ncc.get('texts.preferences.thousandSeparator') + '##0d';
                };
                var pattern2 = function() {
                    return '#' + ncc.get('texts.preferences.thousandSeparator') + '##0' +
                        ncc.get('texts.preferences.decimalSeparator') + '999999';
                };

                var dPatternRecalc = function(options) {
                    options.translation = {
                        'd': {
                            pattern: new RegExp(ncc.escapeRegExp(ncc.get('texts.preferences.decimalSeparator'))),
                            optional: true
                        }
                    };
                };
                var options = {
                    onKeyPress: function(amount, e, currentField, options) {
                        dPatternRecalc(options);
                        if (!onPattern1) {
                            if (amount.indexOf(ncc.get('texts.preferences.decimalSeparator')) === -1) {
                                $amount.mask(pattern1(), options);
                                $fee.mask(pattern1(), options);
                                onPattern1 = true;
                            }
                        } else {
                            if (amount.indexOf(ncc.get('texts.preferences.decimalSeparator')) !== -1) {
                                $amount.mask(pattern2(), options);
                                $fee.mask(pattern2(), options);
                                onPattern1 = false;
                            }
                        }
                    },
                    maxlength: false,
                    reverse: true
                };
                
                var $recipient = $('.js-sendNem-recipient-textbox');
                $recipient.mask('AAAAAA-AAAAAA-AAAAAA-AAAAAA-AAAAAA-AAAAAA-AAAA');

                var onPattern1 = true;
                var $amount = $('.js-sendNem-amount-textbox');
                var $fee = $('.js-sendNem-fee-textbox');
                local.listeners.push(ncc.observe('texts.preferences', function(preferences) {
                    dPatternRecalc(options);
                    if (onPattern1) {
                        $amount.mask(pattern1(), options);
                        $fee.mask(pattern1(), options);
                    } else {
                        $amount.mask(pattern2(), options);
                        $fee.mask(pattern2(), options);
                    }
                }));
            });
        },
        leave: [function() {
            $(window).off('resize.scrollableSidebar');
        }]
    });
});