"use strict";

define(['jquery', 'ncc', 'NccLayout'], function($, ncc, NccLayout) {
    return $.extend(true, {}, NccLayout, {
        name: 'wallet',
        template: 'rv!layout/wallet-pages',
        setupOnce: function() {
            var local = this.local;

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
                ncc.postRequest('account/transactions', { wallet: wallet, account: account }, function(data) {
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

            ncc.bootLocalNode = function(message) {
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
                                    isHtml: true,
                                    content: ncc.get('texts.modals.bootLocalNode.noLabel')
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
                        values.account = account;
                        var self = this;
                        self.lockAction();
                        ncc.postRequest('node/boot', values, function(data) {
                                closeModal();
                                ncc.set('status.nodeBooted', true);
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
                        });
                        return false;
                    }, 
                    ncc.get('texts.modals.bootLocalNode.boot'), 
                    ncc.get('texts.modals.bootLocalNode.booting')
                );
            };
        },
        setupEverytime: function() {
            var wallet = ncc.getUrlParam('wallet');
            if (!wallet) {
                ncc.loadPage('landing');
                return;
            } else {
                if (!ncc.get('wallet') || ncc.get('wallet.name') != wallet) {
                    ncc.refreshWallet(wallet);
                }
            }

            var account = ncc.getUrlParam('account');
            if (!account) {
                ncc.loadPage('landing');
                return;
            } else {
                if (!ncc.get('activeAccount') || ncc.get('activeAccount.address') != account) {
                    ncc.refreshWallet(account);
                }
            }

            ncc.set('params', {
                wallet: wallet,
                account: account
            });

            var local = this.local;

            require(['zeroClipboard'], function(ZeroClipboard) {
                local.client = new ZeroClipboard($('.copyClipboard'));

                local.client.on('ready', function() {
                    local.client.on('aftercopy', function() {
                        ncc.showTempMessage(ncc.get('texts.wallet.copiedToClipboard'));
                    });
                });

                require(['tooltipster'], function() {
                    $('#global-zeroclipboard-flash-bridge').tooltipster({
                        content: ncc.get('texts.wallet.copyToClipboard'),
                        position: 'bottom',
                        delay: 50
                    });
                });
            });

            local.listeners.push(ncc.on({
                toggleSidebar: function() {
                    ncc.set('active.fullSidebar', !ncc.get('active.fullSidebar'));
                },
                openSendNem: function() {
                    if (ncc.get('status.nodeBooted')) {
                        ncc.showModal('sendNem');
                    } else {
                        ncc.showMessage(ncc.get('texts.modals.notBootedWarning.title'), ncc.get('texts.modals.notBootedWarning.message'), function() {
                            ncc.bootLocalNode();
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
                            ncc.set('nis', data);
                            ncc.set('nis.nodeMetaData.lastBlockBehind', (data.nodeMetaData.maxBlockChainHeight - data.nodeMetaData.nodeBlockChainHeight) * 60);
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
                        var wallet = ncc.get('wallet.name');
                        ncc.postRequest('account/transactions', { wallet: wallet, account: newAccount }, function(data) {
                            ncc.set('activeAccount', ncc.processAccount(data));
                            ncc.set('transactions.gotAll', false);
                            require([history.state], function(layout) {
                                history.pushState(history.state, null, layout.url + ncc.toQueryString({
                                    wallet: wallet,
                                    account: newAccount
                                }));
                            });
                        });
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
                                        isHtml: true,
                                        content: ncc.get('texts.modals.setPrimary.noLabel')
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
                    ncc.bootLocalNode(message);
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
                                delete values.confirmPassword;
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

            var modal = ncc.getModal('sendNem');

            var resetFee = (function() {
                var requestData = {
                    wallet: ncc.get('wallet.name'),
                    account: ncc.get('activeAccount.address')
                };
                return function(forceReset, silent) {
                    requestData.amount = ncc.toMNem(modal.get('amount'));
                    requestData.message = modal.get('message') && modal.get('message').toString();
                    requestData.encrypt = modal.get('encrypted') ? 1 : 0;
                    requestData.recipient = ncc.restoreAddress($('.recipient.form-control').val()) || ncc.get('activeAccount.address');
                    requestData.hours_due = modal.get('dueBy') | 0,
                    
                    ncc.postRequest('wallet/account/transaction/fee', requestData, function(data) {
                            var currentFee = modal.get('fee');
                            var newFee = data.fee / 1000000;
                            if (newFee || newFee === 0) {
                                if (forceReset || !currentFee || currentFee < newFee) {
                                    modal.set('fee', newFee);
                                    modal.set('isFeeAutofilled', true);
                                }
                            }
                        }, null, silent
                    );
                };
            })();

            modal.observe('fee', function(newValue, oldValue, keypath) {
                modal.set('isFeeAutofilled', false);
            });

            modal.observe('amount message encrypted', (function() {
                var t;
                return function() {
                    if (t) clearTimeout(t);
                    t = setTimeout(function() {
                        resetFee(modal.get('isFeeAutofilled'), true);
                    }, 500);
                }
            })(), {
                init: false
            });

            modal.on({
                resetFee: function() {
                    resetFee(true, false );
                },
                sendTransaction: function() {
                    var activeAccount = ncc.get('activeAccount.address');
                    var requestData = {
                        wallet: ncc.get('wallet.name'),
                        password: modal.get('password'),
                        account: activeAccount,
                        recipient: ncc.restoreAddress($('.recipient.form-control').val()),
                        amount: ncc.toMNem(modal.get('amount')),
                        message: modal.get('message') && modal.get('message').toString(),
                        fee: ncc.toMNem(modal.get('fee')),
                        hours_due: modal.get('dueBy') | 0,
                        encrypt: modal.get('encrypted')? 1 : 0
                    };
                    modal.lockAction();
                    ncc.postRequest('wallet/account/transaction/send', requestData, function(data) {
                        ncc.showMessage(ncc.get('texts.modals.common.success'), ncc.get('texts.modals.sendNem.successMessage'), function() {
                            ncc.refreshInfo();
                        });

                        modal.close();

                        ncc.refreshAccount();
                    },
                    {
                        complete: function() {
                            modal.set('password', '');
                            modal.unlockAction();
                        }
                    });
                },
                sendFormKeypress: function(e) {
                    if (e.original.keyCode === 13) {
                        this.fire('sendTransaction');
                    }
                },
                queryRecipient: function() {
                    var address = ncc.restoreAddress($('.recipient.form-control').val());
                    ncc.postRequest('account/find', { account: address }, function(data) {
                            if (data.address) {
                                modal.set('recipientLabel', data.label || '');
                            } else {
                                modal.set('recipientLabel', null);
                            }
                        }, 
                        {
                            error: function() {
                                modal.set('recipientLabel', null);
                            },
                            altFailCb: function() {
                                modal.set('recipientLabel', null);
                            }
                        },
                    true);
                }
            });

            require(['maskedinput'], function() {
                var $recipient = $('.form-control.recipient');
                // on-change doesn't work with maskedInput
                $recipient.on('change', function() {
                    modal.fire('queryRecipient');
                });
                $recipient.mask('******-******-******-******-******-******-****');
            });

            /*require(['stepper'], function() {
                $('.form-control.due-by input').stepper({
                    labels: {
                        up: '',
                        down: ''
                    }
                });
            });*/

            modal.set({
                isFeeAutofilled: true,
                dueBy: 12
            });

            local.accountRefresh = setInterval(ncc.refreshAccount.bind(null, null, null, true), local.autoRefreshInterval);

            ncc.postRequest('account/transactions', { wallet: ncc.get('wallet.name'), account: ncc.get('activeAccount.address') }, function(data) {
                ncc.set('activeAccount', ncc.processAccount(data));
            });

            if (!ncc.get('status.nodeBooted')) {
                var success = false;
                ncc.getRequest('node/status', function(data) {
                    if (data.ok) {
                        ncc.set('status.nodeBooted', true);
                        success = true;
                    }
                }, {
                    complete: function() {
                        if (!success) {
                            ncc.set('status.nodeBooted', false);
                        }

                        if (!ncc.get('status.nodeBooted')) {
                            ncc.bootLocalNode(ncc.get('texts.wallet.bootNodeWarning'));
                        }
                    }
                }, true);
            }
        },
        leave: [function() {
            clearInterval(this.local.accountRefresh);
            clearTimeout(this.local.nisInfoRefresh);
            ncc.set({
                wallet: null,
                activeAccount: null,
                transactions: null,
                params: null
            });
            ncc.set('active.fullSidebar', undefined);
        }]
    });
});