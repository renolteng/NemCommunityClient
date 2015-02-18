"use strict";

define(['NccModal', 'Utils'], function(NccModal, Utils) {
	return NccModal.extend({
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
                        return Utils.format.address.format(this.get('settings.nisBootInfo.account'));
                    }
                },
                set: function(address) {
                    if (address !== this.get('texts.modals.settings.autoBoot.primaryAccount')) {
                        this.set('settings.nisBootInfo.account', Utils.format.address.restore(address));
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
        saveSettings: function() {
            var self = this;
            ncc.postRequest('configuration/update', ncc.get('settings'), function(data) {
                if (data.ok) {
                    ncc.showMessage(ncc.get('texts.common.success'), ncc.get('texts.modals.settings.saveSuccess'));
                    self.closeModal();
                    ncc.refreshAccount();
                }
            });
        },
        data: {
            activeTab: 'remoteServer'
        },
        onrender: function() {
            this._super();

            this.observe({
                chkDefaultPort: function(chkDefaultPort) {
                    if (chkDefaultPort) {
                        this.set('portStr', '7890');
                    }
                },
            },
            {
                init: false
            });
            this.on({
                modalOpened: function() {
                    this.set('chkDefaultPort', (this.get('settings.remoteServer.port') == 7890));
                },
                modalClosed: function() {
                    // Refresh to the current settings
                    ncc.getRequest('configuration/get', function(data) {
                        ncc.set('settings', data);
                    });
                }
            });

            require(['maskedinput'], function() {
                $('.js-settingsModal-account-textbox').mask('AAAAAA-AAAAAA-AAAAAA-AAAAAA-AAAAAA-AAAAAA-AAAA');
                $('.js-settingsModal-port-textbox').mask('00000');
            });
        }
    });
});