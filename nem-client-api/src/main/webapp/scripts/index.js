"use strict";

require.config({
    baseUrl: 'scripts',
    paths: {
        'layout': '../layouts',
        'jquery': 'plugins/jquery-2.1.0.min',
        'expand': 'plugins/expand',
        'modernizr': 'plugins/modernizr.custom.00846',
        'ractive': 'plugins/ractive.min',
        'tinycarousel': 'plugins/jquery.tinycarousel.min',
        'scroller': 'plugins/jquery.fs.scroller.min',
        'selecter': 'plugins/jquery.fs.selecter.min',
        'stepper': 'plugins/jquery.fs.stepper.min',
        'gridster': 'plugins/jquery.gridster.min',
        'maskedinput': 'plugins/jquery.mask.min',
        'dropit': 'plugins/dropit.min',
        'zeroClipboard': 'plugins/ZeroClipboard.min',
        'tooltipster': 'plugins/jquery.tooltipster.min',
        'mustache': 'plugins/mustache.min',
        'draggable': 'plugins/jquery-ui.draggable.min'
    },
    shim: {
        'scroller': { deps: ['jquery'] },
        'selecter': { deps: ['jquery'] },
        'stepper': { deps: ['jquery'] },
        'gridster': { deps: ['jquery'] },
        'maskedinput': { deps: ['jquery'] },
        'dropit': { deps: ['jquery'] },
        'tooltipster': { deps: ['jquery'] }
    }
});

define(['ncc'], function(ncc) {
    ncc.getRequest('configuration/get', function(data) {
        ncc.set('settings', data);
        ncc.loadPage(entryPage, null, false, true);
    });

    window.onpopstate = function(event) {
        ncc.loadPage(event.state.page, event.state.params, true, false);
    };

    var checkNisAvailability = function() {
        var success = false;
        ncc.getRequest('info/nis/check', function(data) {
            if (data === 1) {
                ncc.set('status.nisUnavailable', false);
                success = true;
            }
        }, {
            complete: function(jqXHR, textStatus) {
                if (jqXHR.status === 0) {
                    ncc.set('status.nccUnavailable', true);
                }

                if (!success) {
                    ncc.set('status.nisUnavailable', true);
                }
            }
        }, true);
    };
    checkNisAvailability();
    setInterval(checkNisAvailability, 3000);

    var checkNodeStatus = function() {
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
            }
        }, true);
    };
    checkNodeStatus();
    setInterval(checkNodeStatus, 30000);

    (function() {
        var waitTime = 30000;
        var t = 0;

        ncc.refreshNisInfo = function() {
            clearTimeout(t);
            ncc.getRequest('info/nis',
                function(data) {
                    var lastBlockBehind = (data.nodeMetaData.maxBlockChainHeight - data.nodeMetaData.nodeBlockChainHeight) * 60;
                    ncc.set('nis', data);
                    ncc.set('nis.nodeMetaData.lastBlockBehind', lastBlockBehind < 0? 0 : lastBlockBehind);
                    if (data.nodeMetaData.maxBlockChainHeight === data.nodeMetaData.nodeBlockChainHeight) {
                        waitTime = 60000;
                    } else {
                        waitTime = 30000;
                    }
                },
                {
                    complete: function() {
                        t = setTimeout(ncc.refreshNisInfo, waitTime);
                    }
                },
                true
            );
        };

        ncc.refreshNisInfo();
    })();
});