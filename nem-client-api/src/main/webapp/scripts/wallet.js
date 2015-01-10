"use strict";

define(['jquery', 'ncc', 'NccLayout', 'Utils'], function($, ncc, NccLayout, Utils) {
    return $.extend(true, {}, NccLayout, {
        name: 'wallet',
        template: 'rv!layout/wallet',
        initOnce: function() {
            ncc.refreshAddressBook = function(addressBook, silent) {
                ncc.postRequest('addressbook/info', { addressBook: addressBook }, function(data) {
                    ncc.set('contacts', Utils.processContacts(data.accountLabels));
                }, null, silent);
            }

            ncc.refreshWallet = function(wallet, silent) {
                if (!wallet) wallet = ncc.get('wallet.wallet');

                ncc.postRequest('wallet/info', { wallet: wallet }, function(data) {
                    ncc.set('wallet', Utils.processWallet(data));
                }, null, silent);

                ncc.refreshAddressBook(wallet, silent);
            };

            ncc.refreshRemoteHarvestingStatus = function(wallet, account, silent) {
                if (!wallet) wallet = ncc.get('wallet.wallet');
                if (!account) account = ncc.get('activeAccount.address');

                ncc.postRequest('wallet/account/remote/status', 
                    { 
                        wallet: wallet,
                        account: account
                    },
                    function(data) {
                        if (data.status === 'UNLOCKED') {
                            ncc.set('walletPage.remoteUnlocked', true);
                        } else {
                            ncc.set('walletPage.remoteUnlocked', false);
                        }
                    },
                    null,
                    silent
                );
            };

            ncc.refreshAccount = function(wallet, account, silent) {
                if (!wallet) wallet = ncc.get('wallet.wallet');
                if (!account) account = ncc.get('activeAccount.address');

                var success = false;
                ncc.postRequest('account/transactions/all', 
                    { 
                        wallet: wallet, 
                        account: account 
                    }, 
                    function(data) {
                        success = true;
                        ncc.set('activeAccount', Utils.processAccount(data));
                        ncc.set('status.lostConnection', false);
                    }, 
                    {
                        complete: function() {
                            if (!success) {
                                ncc.set('status.lostConnection', true);
                            }
                        }
                    }, 
                    silent
                );

                ncc.refreshRemoteHarvestingStatus(wallet, account, silent);

                ncc.fire('refreshAccount');
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
                var wallet = ncc.get('wallet.wallet');
                ncc.showInputForm(ncc.get('texts.modals.bootLocalNode.title'), message,
                    [
                        {
                            name: 'account',
                            type: 'text',
                            readonly: true,
                            unimportant: true,
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
                            unimportant: true,
                            label: {
                                content: ncc.get('texts.modals.bootLocalNode.wallet')
                            }
                        }, 
                        {
                            name: 'nodeName',
                            type: 'text',
                            label: {
                                content: ncc.get('texts.modals.bootLocalNode.node')
                            }
                        }
                    ],
                    {
                        account: Utils.format.address.format(account),
                        wallet: wallet
                    },
                    function(values, closeModal) {
                        var self = this;
                        values.account = account;

                        this.lockAction();
                        ncc.set('status.booting', true);
                        ncc.postRequest('node/boot', values, 
                            function(data) {
                                closeModal();
                                // NIS info will be automatically retrieved when NIS status becomes BOOTED
                                // so no need to manually call refreshNisInfo()
                                ncc.refreshAppStatus();
                            },
                            {
                                altFailCb: function(faultId) {
                                    if (601 === faultId) {
                                        ncc.refreshAppStatus();
                                        closeModal();
                                    }
                                },
                                complete: function() {
                                    self.unlockAction();
                                    ncc.set('status.booting', false);
                                }
                            }
                        );
                    }, 
                    ncc.get('texts.modals.bootLocalNode.boot'), 
                    ncc.get('texts.modals.bootLocalNode.booting')
                );
            };

            ncc.openSendNem = function(recipient) {
                if (ncc.get('nodeBooted')) {
                    var m = ncc.getModal('sendNem');
                    m.open();
                    if (recipient) {
                        m.set('formattedRecipient', Utils.format.address.format(recipient));
                    }
                } else if (ncc.get('nodeBooting')) {
                    ncc.showMessage(ncc.get('texts.modals.sendNem.bootingWarning.title'), ncc.get('texts.modals.sendNem.bootingWarning.message'));
                } else {
                    ncc.showMessage(ncc.get('texts.modals.sendNem.notBootedWarning.title'), ncc.get('texts.modals.sendNem.notBootedWarning.message'), function() {
                        ncc.showBootModal();
                    });
                }
            },

            ncc.viewTransaction = function(transaction) {
                var m = ncc.getModal('transactionDetails');
                m.set('transaction', transaction);
                m.open();
            };

            ncc.viewAccount = function(address) {
                ncc.postRequest(
                    'account/find', 
                    {account: Utils.format.address.restore(address)},
                    function(data) {
                        var m = ncc.getModal('accountDetails');
                        m.set('account', data);
                        m.open();
                    });
            };

            ncc.addContact = function(address) {
                ncc.showInputForm(ncc.get('texts.modals.addContact.title'), '',
                    [
                        {
                            name: 'addressBook',
                            type: 'text',
                            readonly: true,
                            unimportant: true,
                            label: {
                                content: ncc.get('texts.common.addressBook')
                            }
                        },
                        {
                            name: 'password',
                            type: 'password',
                            label: {
                                content: ncc.get('texts.common.password')
                            }
                        },
                        {
                            name: 'address',
                            type: 'text',
                            label: {
                                content: ncc.get('texts.common.address')
                            }
                        },
                        {
                            name: 'privateLabel',
                            type: 'text',
                            label: {
                                content: ncc.get('texts.common.privateLabel')
                            }
                        },
                        {
                            name: 'publicLabel',
                            type: 'text',
                            label: {
                                content: ncc.get('texts.common.publicLabel')
                            }
                        },
                    ],
                    {
                        addressBook: ncc.get('wallet.wallet'),
                        address: Utils.format.address.format(address)
                    },
                    function(values, closeModal) {
                        values.address = Utils.format.address.restore(values.address);
                        ncc.postRequest('addressbook/accountlabel/add', values, function(data) {
                            closeModal();
                            ncc.refreshAddressBook();
                        });
                    },
                    ncc.get('texts.modals.addContact.add')
                );
            };

            ncc.editContact = function(address, privateLabel, publicLabel) {
                ncc.showInputForm(ncc.get('texts.modals.editContact.title'), '',
                    [
                        {
                            name: 'addressBook',
                            type: 'text',
                            readonly: true,
                            unimportant: true,
                            label: {
                                content: ncc.get('texts.common.addressBook')
                            }
                        },
                        {
                            name: 'password',
                            type: 'password',
                            label: {
                                content: ncc.get('texts.common.password')
                            }
                        },
                        {
                            name: 'address',
                            type: 'text',
                            label: {
                                content: ncc.get('texts.common.address')
                            }
                        },
                        {
                            name: 'privateLabel',
                            type: 'text',
                            label: {
                                content: ncc.get('texts.common.privateLabel')
                            }
                        },
                        {
                            name: 'publicLabel',
                            type: 'text',
                            label: {
                                content: ncc.get('texts.common.publicLabel')
                            }
                        },
                    ],
                    {
                        addressBook: ncc.get('wallet.wallet'),
                        address: Utils.format.address.format(address),
                        privateLabel: privateLabel,
                        publicLabel: publicLabel
                    },
                    function(values, closeModal) {
                        values.address = Utils.format.address.restore(values.address);
                        ncc.postRequest('addressbook/accountlabel/change', values, function(data) {
                            closeModal();
                            ncc.refreshAddressBook();
                        });
                    },
                    ncc.get('texts.modals.editContact.saveChanges')
                );
            };

            ncc.removeContact = function(address) {
                ncc.showInputForm(ncc.get('texts.modals.removeContact.title'), '',
                    [
                        {
                            name: 'addressBook',
                            type: 'text',
                            readonly: true,
                            unimportant: true,
                            label: {
                                content: ncc.get('texts.common.addressBook')
                            }
                        },
                        {
                            name: 'password',
                            type: 'password',
                            label: {
                                content: ncc.get('texts.common.password')
                            }
                        },
                        {
                            name: 'address',
                            type: 'text',
                            label: {
                                content: ncc.get('texts.common.address')
                            }
                        }
                    ],
                    {
                        addressBook: ncc.get('wallet.wallet'),
                        address: Utils.format.address.format(address)
                    },
                    function(values, closeModal) {
                        values.address = Utils.format.address.restore(values.address);
                        ncc.postRequest('addressbook/accountlabel/remove', values, function(data) {
                            closeModal();
                            ncc.refreshAddressBook();
                        });
                    },
                    ncc.get('texts.modals.removeContact.remove')
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
        paramsChanged: function(params) {
            var wallet = (params && params.wallet) || Utils.getUrlParam('wallet');
            if (!wallet) {
                ncc.loadPage('landing');
                return true;
            } else {
                if (!ncc.get('wallet') || ncc.get('wallet.wallet') != wallet) {
                    ncc.set('wallet.wallet', wallet);
                    ncc.refreshWallet(wallet);
                }
            }

            var account = (params && params.account) || Utils.getUrlParam('account');
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
                            var blockchainHeight = ncc.get('blockchainHeight') || data.nodeMetaData.nodeBlockChainHeight;
                        	var lastBlockBehind = (data.nodeMetaData.maxBlockChainHeight - blockchainHeight) * 60;
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
                            var requestData = {
                                wallet: ncc.get('wallet.wallet') 
                            };
                            ncc.postRequest('wallet/close', requestData, function(data) {
                                if (data.ok) {
                                    ncc.loadPage('landing');
                                } else {
                                    ncc.showError();
                                }
                            });

                            requestData.addressBook = requestData.wallet;
                            delete requestData.wallet;
                            ncc.postRequest('addressbook/close', requestData);
                        }
                    });
                },
                switchAccount: function(e, newAccount) {
                    var currentAccount = ncc.get('activeAccount.address');
                    if (currentAccount !== newAccount) {
                        var layouts = ncc.get('layout');
                        var currentPage = layouts[layouts.length - 1].name;
                        var wallet = ncc.get('wallet.wallet');
                        ncc.loadPage(currentPage, { wallet: wallet, account: newAccount });
                    }
                },
                refreshInfo: function() {
                    this.refreshInfo();
                    this.refreshAppStatus();
                    this.refreshNisInfo();
                },
                createNewAccount: function() {
                    var wallet = ncc.get('wallet.wallet');
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
                                unimportant: true,
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
                                    ncc.showMessage(ncc.get('texts.common.success'), ncc.fill(ncc.get('texts.modals.createAccount.successMessage'), Utils.format.address.format(data.address), label));

                                    var layout = ncc.get('layout');
                                    var wallet = ncc.get('wallet.wallet');
                                    ncc.loadPage(layout[layout.length - 1].name, 
                                        {
                                            wallet: wallet,
                                            account: data.address
                                        }
                                    );
                                    ncc.refreshWallet();

                                    closeModal();
                                } else {
                                    ncc.showError();
                                }
                            });
                        },
                        ncc.get('texts.modals.createAccount.create')
                    );
                },
                createRealAccountData: (function() {
                    var showAccountData = function(accountData) {
                        var formattedAddress = Utils.format.address.format(accountData.address);

                        // Open the 1st modal: generating account data
                        ncc.showInputForm(
                            ncc.get('texts.modals.createRealAccountData.title'), 
                            ncc.get('texts.modals.createRealAccountData.message'),
                            [
                                {
                                    name: 'address',
                                    type: 'text',
                                    readonly: true,
                                    label: {
                                        content: ncc.get('texts.modals.createRealAccountData.address')
                                    }
                                },
                                {
                                    name: 'publicKey',
                                    type: 'textarea',
                                    readonly: true,
                                    label: {
                                        content: ncc.get('texts.modals.createRealAccountData.publicKey')
                                    }
                                },
                                {
                                    name: 'privateKey',
                                    type: 'textarea',
                                    readonly: true,
                                    label: {
                                        content: ncc.get('texts.modals.createRealAccountData.privateKey')
                                    }
                                }
                            ],
                            {
                                address: formattedAddress,
                                publicKey: accountData.publicKey,
                                privateKey: accountData.privateKey
                            },
                            function(values, closeModal) {
                                ncc.showConfirmation(
                                    ncc.get('texts.modals.createRealAccountData.confirm.title'),
                                    ncc.get('texts.modals.createRealAccountData.confirm.message'),
                                    {
                                        yes: function() {
                                            // Close the 1st modal
                                            closeModal();

                                            // Open the 2nd modal: recheck private key
                                            ncc.showInputForm(ncc.get('texts.modals.createRealAccountData.recheck.title'), 
                                                ncc.get('texts.modals.createRealAccountData.recheck.message'),
                                                [
                                                    {
                                                        name: 'address',
                                                        type: 'text',
                                                        readonly: true,
                                                        label: {
                                                            content: ncc.get('texts.modals.createRealAccountData.address')
                                                        }
                                                    },
                                                    {
                                                        name: 'publicKey',
                                                        type: 'textarea',
                                                        readonly: true,
                                                        label: {
                                                            content: ncc.get('texts.modals.createRealAccountData.publicKey')
                                                        }
                                                    },
                                                    {
                                                        name: 'privateKey',
                                                        type: 'textarea',
                                                        label: {
                                                            content: ncc.get('texts.modals.createRealAccountData.privateKey')
                                                        }
                                                    }
                                                ],
                                                {
                                                    address: formattedAddress,
                                                    publicKey: accountData.publicKey
                                                },
                                                function(values, closeModal) {
                                                    if (values.privateKey === accountData.privateKey) {
                                                        closeModal(); // close the 2nd modal
                                                        ncc.showMessage(
                                                            ncc.get('texts.modals.createRealAccountData.recheck.correct.title'),
                                                            ncc.get('texts.modals.createRealAccountData.recheck.correct.message')
                                                        );
                                                    } else {
                                                        ncc.showConfirmation(
                                                            ncc.get('texts.modals.createRealAccountData.recheck.incorrect.title'),
                                                            ncc.get('texts.modals.createRealAccountData.recheck.incorrect.message'),
                                                            {
                                                                seeOriginal: function() {
                                                                    closeModal();
                                                                    showAccountData(accountData);
                                                                    return true;
                                                                }
                                                            },
                                                            [
                                                                {
                                                                    action: 'tryAgain',
                                                                    label: ncc.get('texts.modals.createRealAccountData.recheck.incorrect.tryAgain'),
                                                                    actionType: 'secondary'
                                                                },
                                                                {
                                                                    action: 'seeOriginal',
                                                                    label: ncc.get('texts.modals.createRealAccountData.recheck.incorrect.seeOriginal'),
                                                                    actionType: 'primary'
                                                                }
                                                            ]
                                                        );
                                                    }
                                                },
                                                ncc.get('texts.modals.createRealAccountData.recheck.recheck')
                                            );
                                        }
                                    }
                                );
                            },
                            ncc.get('texts.modals.createRealAccountData.ok')
                        );
                    };

                    return function() {
                        ncc.getRequest('/account/create-real-account-data',
                            function(data) {
                                showAccountData(data);
                            }
                        );
                    }
                })(),
                verifyRealAccountData: function() {
                    ncc.showInputForm(
                        ncc.get('texts.modals.verifyRealAccountData.title'), 
                        ncc.get('texts.modals.verifyRealAccountData.message'), 
                        [
                            {
                                name: 'address',
                                type: 'text',
                                mask: 'address',
                                label: {
                                    content: ncc.get('texts.modals.verifyRealAccountData.address')
                                }
                            },
                            {
                                name: 'publicKey',
                                type: 'textarea',
                                label: {
                                    content: ncc.get('texts.modals.verifyRealAccountData.publicKey')
                                }
                            },
                            {
                                name: 'privateKey',
                                type: 'textarea',
                                label: {
                                    content: ncc.get('texts.modals.verifyRealAccountData.privateKey')
                                }
                            }
                        ],
                        {},
                        function(values, closeModal) {
                            values.address = Utils.format.address.restore(values.address);
                            ncc.postRequest('account/verify-real-account-data', values, function(data) {
                                ncc.showMessage(ncc.get('texts.common.success'),
                                    ncc.get('texts.modals.verifyRealAccountData.dataMatched'));
                                closeModal();
                            });
                        },
                        ncc.get('texts.modals.verifyRealAccountData.verify')
                    );
                },
                addAccount: function() {
                    var wallet = ncc.get('wallet.wallet');
                    ncc.showInputForm(ncc.get('texts.modals.addAccount.title'), '',
                        [
                            {
                                name: 'accountKey',
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
                                unimportant: true,
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
                                    ncc.showMessage(ncc.get('texts.common.success'), ncc.fill(ncc.get('texts.modals.addAccount.successMessage'), Utils.format.address.format(data.address), label));

                                    var layout = ncc.get('layout');
                                    var wallet = ncc.get('wallet.wallet');
                                    ncc.loadPage(layout[layout.length - 1].name, 
                                        {
                                            wallet: wallet,
                                            account: data.address
                                        }
                                    );
                                    ncc.refreshWallet();
                                    
                                    closeModal();
                                } else {
                                    ncc.showError();
                                }
                            });
                        },
                        ncc.get('texts.modals.addAccount.add')
                    );
                },
                setCurrentAccountAsPrimary: function() {
                    var account = ncc.get('activeAccount.address');
                    var accountLabel = ncc.get('activeAccount.label');
                    var wallet = ncc.get('wallet.wallet');
                    ncc.showInputForm(ncc.get('texts.modals.setPrimary.title'), '',
                        [
                            {
                                name: 'account',
                                type: 'text',
                                readonly: true,
                                unimportant: true,
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
                                unimportant: true,
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
                            account: Utils.format.address.format(account),
                            wallet: wallet
                        },
                        function(values, closeModal) {
                            values.account = account;
                            ncc.postRequest('wallet/account/primary', values, function(data) {
                                ncc.showMessage(ncc.get('texts.common.success'), ncc.fill(ncc.get('texts.modals.setPrimary.successMessage'), Utils.format.address.format(account), accountLabel));
                                ncc.set('wallet', Utils.processWallet(data));
                                closeModal();
                            });
                        },
                        ncc.get('texts.modals.setPrimary.set')
                    );
                },
                bootLocalNode: function(e, message) {
                    ncc.showBootModal(message);
                },
                changeWalletName: function() {
                    var wallet = ncc.get('wallet.wallet');
                    ncc.showInputForm(ncc.get('texts.modals.changeWalletName.title'), '',
                        [
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                unimportant: true,
                                label: {
                                    content: ncc.get('texts.modals.changeWalletName.wallet')
                                }
                            },
                            {
                                name: 'newName',
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
                            var oneCompleted = false;
                            var newWalletName = values['newName'];

                            ncc.postRequest('wallet/name/change', values, function() {
                                if (oneCompleted) {
                                    ncc.showMessage(ncc.get('texts.common.success'), ncc.fill(ncc.get('texts.modals.changeWalletName.successMessage'), wallet, newWalletName));
                                    closeModal();
                                } else {
                                    oneCompleted = true;
                                }
                            });

                            values.addressBook = values.wallet;
                            delete values.wallet;
                            ncc.postRequest('addressbook/name/change', values, function() {
                                if (oneCompleted) {
                                    ncc.showMessage(ncc.get('texts.common.success'), ncc.fill(ncc.get('texts.modals.changeWalletName.successMessage'), wallet, newWalletName));
                                    closeModal();
                                } else {
                                    oneCompleted = true;
                                }
                            });
                        },
                        ncc.get('texts.modals.changeWalletName.change')
                    );
                },
                changeWalletPassword: function() {
                    var wallet = ncc.get('wallet.wallet');
                    ncc.showInputForm(ncc.get('texts.modals.changeWalletPassword.title'), '',
                        [
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                unimportant: true,
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
                                name: 'newPassword',
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
                            if (values['newPassword'] === values.confirmPassword) {
                                var oneCompleted = false;
                                delete values.confirmPassword;

                                ncc.postRequest('wallet/password/change', values, function() {
                                    if (oneCompleted) {
                                        ncc.showMessage(ncc.get('texts.common.success'), ncc.get('texts.modals.changeWalletPassword.successMessage'));
                                        closeModal();
                                    } else {
                                        oneCompleted = true;
                                    }
                                });

                                values.addressBook = values.wallet;
                                delete values.wallet;
                                ncc.postRequest('addressbook/password/change', values, function() {
                                    if (oneCompleted) {
                                        ncc.showMessage(ncc.get('texts.common.success'), ncc.get('texts.modals.changeWalletPassword.successMessage'));
                                        closeModal();
                                    } else {
                                        oneCompleted = true;
                                    }
                                });
                            } else {
                                ncc.showMessage(ncc.get('texts.modals.changeWalletPassword.passwordNotMatchTitle'), ncc.get('texts.modals.changeWalletPassword.passwordNotMatchMessage'));
                            }
                        },
                        ncc.get('texts.modals.changeWalletPassword.change')
                    );
                },
                changeAccountLabel: function() {
                    var wallet = ncc.get('wallet.wallet');
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
                                unimportant: true,
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
                            account: account,
                            label: accountLabel
                        },
                        function(values, closeModal) {
                            ncc.postRequest('wallet/account/label', values, function(data) {
                                var label = values.label;
                                ncc.showMessage(
                                    ncc.get('texts.common.success'), 
                                    ncc.fill(ncc.get('texts.modals.changeAccountLabel.successMessage'), Utils.format.address.format(account), label)
                                );
                                ncc.refreshInfo();
                                closeModal();
                            });
                        }, 
                        ncc.get('texts.modals.changeAccountLabel.change')
                    );
                },
                removeAccount: function() {
                    var wallet = ncc.get('wallet.wallet');
                    var account = ncc.get('activeAccount.address');
                    var accountLabel = ncc.get('activeAccount.label');
                    ncc.showInputForm(ncc.get('texts.modals.removeAccount.title'), 
                        ncc.get('texts.modals.removeAccount.warning'),
                        [
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                unimportant: true,
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
                                    ncc.get('texts.common.success'), 
                                    ncc.fill(ncc.get('texts.modals.removeAccount.successMessage'), Utils.format.address.format(account), accountLabel)
                                );
                                ncc.set('wallet', Utils.processWallet(data));
                                ncc.fire('switchAccount', null, data.primaryAccount.address);
                                closeModal();
                            });
                        }, 
                        ncc.get('texts.modals.removeAccount.remove')
                    );
                },
                startHarvesting: function() {
                    var account = ncc.get('activeAccount.address');
                    var wallet = ncc.get('wallet.wallet');
                    ncc.set('walletPage.harvestButtonProcessing', true);
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
                            ncc.set('walletPage.harvestButtonProcessing', false);
                        }
                    });
                },
                stopHarvesting: function() {
                    var account = ncc.get('activeAccount.address');
                    var wallet = ncc.get('wallet.wallet');
                    ncc.set('walletPage.harvestButtonProcessing', true);
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
                            ncc.set('walletPage.harvestButtonProcessing', false);
                        }
                    });
                },
                startRemoteHarvesting: function() {
                    ncc.showInputForm(ncc.get('texts.modals.startRemote.title'), '',
                        [   
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                unimportant: true,
                                label: {
                                    content: ncc.get('texts.modals.startRemote.wallet')
                                }
                            },
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: ncc.get('texts.modals.startRemote.password')
                                }
                            },
                            {
                                name: 'account',
                                type: 'text',
                                readonly: true,
                                unimportant: true,
                                label: {
                                    content: ncc.get('texts.modals.startRemote.account')
                                }
                            }
                        ],
                        {
                            wallet: ncc.get('wallet.wallet'),
                            account: ncc.get('activeAccount.address')
                        },
                        function(values, closeModal) {
                            ncc.set('walletPage.harvestButtonProcessing', true);
                            ncc.postRequest('wallet/account/remote/unlock', values, function(data) {
                                closeModal();
                            }, {
                                complete: function() {
                                    ncc.refreshAccount(null, null, true);
                                    ncc.set('walletPage.harvestButtonProcessing', false);
                                }
                            });
                        },
                        ncc.get('texts.modals.startRemote.start')
                    );
                },
                stopRemoteHarvesting: function() {
                    ncc.showInputForm(ncc.get('texts.modals.stopRemote.title'), '',
                        [   
                            {
                                name: 'wallet',
                                type: 'text',
                                readonly: true,
                                unimportant: true,
                                label: {
                                    content: ncc.get('texts.modals.stopRemote.wallet')
                                }
                            },
                            {
                                name: 'password', 
                                type: 'password',
                                label: {
                                    content: ncc.get('texts.modals.stopRemote.password')
                                }
                            },
                            {
                                name: 'account',
                                type: 'text',
                                readonly: true,
                                unimportant: true,
                                label: {
                                    content: ncc.get('texts.modals.stopRemote.account')
                                }
                            },
                            {
                                name: 'host',
                                type: 'text',
                                label: {
                                    content: ncc.get('texts.modals.stopRemote.host')
                                }
                            }
                        ],
                        {
                            wallet: ncc.get('wallet.wallet'),
                            account: ncc.get('activeAccount.address')
                        },
                        function(values, closeModal) {
                            ncc.set('walletPage.harvestButtonProcessing', true);
                            ncc.postRequest('wallet/account/remote/lock', values, function(data) {
                                closeModal();
                            }, {
                                complete: function() {
                                    ncc.refreshAccount(null, null, true);
                                    ncc.set('walletPage.harvestButtonProcessing', false);
                                }
                            });
                        },
                        ncc.get('texts.modals.stopRemote.stop')
                    );
                }
            }));

            local.intervalJobs.push(setInterval(ncc.refreshAccount.bind(null, null, null, true), local.autoRefreshInterval));

            ncc.refreshAppStatus(function() {
                if (!ncc.get('nodeBooted')) {
                    if (ncc.get('settings.nisBootInfo.bootNis')) {
                        // default the node name to a substring of the account name so that auto-boot works out-of-box
                        var accountName = ncc.get('settings.nisBootInfo.account') || ncc.get('wallet.primaryAccount.address');
                        var bootData = {
                            nodeName: ncc.get('settings.nisBootInfo.nodeName') || accountName.substring(0, 10),
                            wallet: ncc.get('wallet.wallet'),
                            account: accountName
                        };

                        ncc.set('status.booting', true);
                        ncc.postRequest('node/boot', bootData, 
                            function(data) {
                                // NIS info will be automatically retrieved when NIS status becomes BOOTED
                                // so no need to manually call refreshNisInfo()
                                ncc.refreshAppStatus();
                            },
                            {
                                altFailCb: function(faultId) {
                                    if (601 === faultId) {
                                        ncc.refreshAppStatus();
                                    }
                                },
                                complete: function() {
                                    ncc.set('status.booting', false);
                                }
                            },
                            true
                        );
                    } else {
                        ncc.showBootModal(ncc.get('texts.wallet.bootNodeWarning'));
                    }
                }
            });

            global.$window.on('beforeunload', function() {
                return ncc.get('texts.modals.logoutWarning.leavePage');
            });
        },
        leave: [function() {            
            ncc.global.$window.off('resize.scrollableSidebar');
            ncc.global.$window.off('beforeunload');
            ncc.set('contacts', {});
        }]
    });
});