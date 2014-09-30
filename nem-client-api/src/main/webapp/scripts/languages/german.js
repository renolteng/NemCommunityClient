define({
    id: 'DE',
    name: 'Deutsch',
    texts: {
        preferences: {
            thousandSeparator: '\u2009',
            decimalSeparator: '.'
        },
        faults: {
            101: 'Die Datei wurde nicht gefunden.',
            102: 'Die Brieftasche wurde nicht erstellt.',
            103: 'Die Brieftaschendatei ist beschädigt. Bitte stelle die Brieftasche aus einem Backup wieder her.',
            104: 'Das eingegebene Passwort ist nicht korrekt. Hoffentlich kannst Du Dich an das richtige Passwort erinnern. Ein verlorenes Passwort kann nicht wiederhergestellt werden!',
            106: 'Bevor Du eine Brieftasche benutzen kannst, muss sie geöffnet werden. Um sicher zu gehen, dass Du berechtigt bist, die Brieftasche zu verwenden, musst Du das Passwort für die Brieftasche eingeben.',
            107: 'Die Brieftasche enthält dieses Konto nicht.',
            108: 'Das Konto kann nicht entfernt werden. Der Grund ist meistens, dass der Kontostand größer als 0 NEM ist oder dass Du versuchst ein Hauptkonto zu entfernen.',
            109: 'Es gibt bereits eine Brieftasche mit demselben Namen. Bitte wähle einen anderen Namen für die Brieftasche.',
            110: 'Die Brieftasche enthält dieses Konto schon.',
            202: 'Die verschlüsselte Nachricht kann nicht gesendet werden, da der Empfänger bisher noch keine Transaktion gemacht hat und deswegen der öffentliche Schlüssel des Empfängers unbekannt ist.',
            305: 'Der NEM Infrastructure Server ist nicht verfügbar.',
            306: 'Es ist ein Fehler aufgetreten, den das Entwicklerteam nicht vorhergesehen hat. Wir entschuldigen uns hierfür, vielleicht hilft ein Neustart. Falls nicht, eröffne bitte einen Thread in der NEM NIS/NCC Community.',
            400: 'Einer der Parameter fehlt oder ist ungültig.',
            404: 'Die angeforderte Ressource wurde nicht gefunden.',
            500: 'Es ist ein Fehler aufgetreten, den das Entwicklerteam nicht vorhergesehen hat. Wir entschuldigen uns hierfür, vielleicht hilft ein Neustart. Falls nicht, eröffne bitte einen Thread in der NEM NIS/NCC Community.',
            600: 'Der NIS Server muss gebooted sein, damit NCC Transaktionen senden und empfangen kann. Bitte boote Deinen lokalen Knotenpunkt mit Hilfe des NCC Boot-Menüpunkts.',
            601: 'Der NIS Knotenpunkt ist bereits gebooted. Es ist nicht nötig, NIS ein weiteres Mal zu booten.',
            700: 'Das angegebene Konto erfüllt nicht die Grundkriterien, um Blöcke zu erzeugen. Um Blöcke erzeugen zu können, braucht man mindestens 1.000 NEMs auf dem Konto.',
            701: 'Das angegebene Verfallsdatum liegt in der Vergangenheit. Das Verfallsdatum muss in einem Zeitraum von einem Tag liegen.',
            702: 'Das angegebene Verfallsdatum liegt zu weit in der Zukunft. Das Verfallsdatum muss in einem Zeitraum von einem Tag liegen.',
            703: 'Dein Kontoguthaben reicht nicht aus, um den angegebenen Betrag an NEMs zu senden.',
            704: 'Der angegebene Nachrichtentext ist zu lang, um ihn zu versenden. Bitte reduziere die Länge der Nachricht, um sie versenden zu können.',
            705: 'Der Transaktionshash existiert bereits in der Datenbank oder in der Liste der unbestätigten Transaktionen.',
            706: 'Die Signatur der Transaktion konnte nicht verifiziert werden.',
            707: 'Der Zeitstempel der Transaktion liegt zu weit in der Vergangenheit.',
            708: 'Der Zeitstempel der Transaktion liegt zu weit in der Zukunft.',
            709: 'Das Konto ist unbekannt. Ein Konto muss mindestens einmal als Sender oder Empfänger in einer Transaktion auftreten, um dem Netzwerk bekannt zu sein.',
            901: 'Es ist ein Fehler beim Übergang zum Offlinemodus aufgetreten.'
        },
        common: {
                success: 'Erfolg', //title of the Success message modals
                appStatus: {
        		nccUnknown: 'NCC status ist unbekannt',
        		nccUnavailable: 'NCC is nicht verfügbar',
        		nccStarting: 'NCC started...',
        		nisUnknown: 'NIS status ist unbekannt',
        		nisUnavailable: 'NIS is nicht verfügbar',
        		nisStarting: 'NIS started...',
        		notBooted: 'NIS muss gebootet werden. Bitte öffne eine Brieftasche und boote den lokalen Knotenpunkt mit Hilfe des Popup-Dialoges.',
        		booting: 'NIS wird gebootet...',
        		nisInfoNotAvailable: 'NIS status wird abgefragt...',
        		synchronizing: 'NIS synchronisiert. Block {{1}}, {{2}} im Rückstand.',
                    daysBehind: {
                        0: 'weniger als 1 Tag',
                        1: 'etwa 1 Tag',
                        many: 'etwa {{1}} Tage'
                    },
                    synchronized: 'NIS ist synchronisiert!'
                }
        },
        modals: {
                error: {
                        title: 'Oops!',
                        caption: 'FEHLER {{1}}'
                },
                confirmDefault: {
                        yes: 'Ja',
                        no: 'Nein'
                },
                settings: {
					title: 'Einstellungen',
					language: {
						label: 'Sprache'
					},
					remoteServer: {
						tabTitle: 'Remote Server',
						protocol: 'Protokoll',
						protocolOptions: {
							http: 'HTTP'
						},
						host: 'Host',
						port: 'Port'
					},
					autoBoot: {
						tabTitle: 'Auto-boot',
						name: 'Name des Knotenpunkts',
						account: 'Konto',
						primaryAccount: 'Primäres Konto',
						auto: 'Automatisch booten beim Öffnen der Brieftasche'
					},
					save: 'Speichern',
					saveSuccess: 'Die Einstellungen wurden erfolgreich gespeichert'
				},
                sendNem: {
                        title: 'NEM senden',
                        labelDesc: 'Das Label des Kontos ist {{1}}',
                        nullLabelDesc: "Dieses Konto hat keinen Label",
						amount: 'Betrag',
						recipient: "Konto des Empfängers",
						message: 'Nachricht',
                        encrypt: 'Nachricht verschlüsseln',
                        fee: 'Gebühr',
                        dueBy: 'Verfällt in',
                        resetFee: 'Auf Minimalgebühr zurücksetzen',
                        hours: 'Stunden',
						password: 'Passwort',
                        send: 'Senden',
                        sending: 'Sende...',
                        successMessage: 'Die Transaktion wurde erfolgreich gesendet!',
				txConfirm: {
					title: 'Confirm Transaction',
					sendLabel: "You're going to send",
					to: 'To',
					message: 'Message',
					encrypted: 'Message is encrypted',
					noMessage: 'No message',
					cancel: 'Cancel',
					send: 'Send',
					sending: 'Sending...'
				}
                },
                clientInfo: {
                        title: 'Client info',
                        ncc: 'NEM Community Client - NCC',
                        signer: 'Unterzeichner',
                        remoteServer: 'Remote Server',
                        local: 'Lokal',
                        nis: 'NEM Infrastructure Server - NIS',
                        sync: 'Synchronisiert',
                        notSync: 'Nicht synchronisiert',
                        notConnected: 'Nicht verbunden mit der NEM Cloud',
                        loading: 'Lade...'
                },
                transactionDetails: {
                        title: 'Transaktionsdetails',
                        // this might be block or transaction ID
                        id: 'ID',
                        // this might be block or transaction Hash
                        hash: 'Hash',
                        type: 'Transaktionstyp',
                        pending: 'Unbestätigt',
                        outgoing: 'Abgehend',
                        incoming: 'Eingehend',
                        self: 'Selbst',
                        sender: 'Sender',
                        recipient: 'Empfänger',
                        message: 'Nachricht',
                        noMessage: 'Keine Nachricht',
                        encrypted: 'Nachricht ist verschlüsselt',
                        time: 'Zeitpunkt',
                        confirmations: 'Bestätigungen',
                        amount: 'Betrag',
                        fee: 'Gebühr'
                },
                bootLocalNode: {
                        title: 'Lokalen Knotenpunkt booten',
                        account: 'Konto, um den lokalen Knotenpunkt zu booten',
                        noLabel: '<span class="null"><Kein label></span>',
                        wallet: 'Brieftasche',
                        node: 'Name des Knotenpunkts',
                        boot: 'Boot',
                        booting: 'Booting...'
                },
                notBootedWarning: {
                        title: 'Der Knotenpunkt wurde noch nicht gebooted!',
                        message: 'Der lokale Knotenpunkt muss gebootet werden, bevor Du NEM senden kannst!'
                },
                closeWallet: {
                        title: 'Brieftasche schließen',
                        message: 'Bist Du sicher, dass Du die Brieftasche schließen und zur Startseite wechseln möchtest?'
                },
                createAccount: {
                        title: 'Neues Konto anlegen',
                        label: 'Privater Label',
                        wallet: 'Brieftasche',
                        password: "Passwort der Brieftasche",
                        successMessage: 'Das Konto {{1}} {{#2}}({{2}}){{/2}} wurde angelegt!',
                        create: 'Anlegen'
                },
                addAccount: {
                        title: 'Ein vorhandenes Konto hinzufügen',
                        privateKey: "Privater Schlüssel des Kontos",
                        wallet: 'Brieftasche',
                        password: "Passwort der Brieftasche",
                        successMessage: 'Das Konto {{1}} {{#2}}({{2}}){{/2}} wurde zur Brieftasche hinzugefügt!',
                        add: 'Hinzufügen',
						label: 'Label'
                },
                setPrimary: {
                        title: 'Hauptkonto festlegen',
                        account: 'Konto, welches als Hauptkonto dient',
                        noLabel: '<span class="null"><Kein Label></span>',
                        wallet: 'Brieftasche',
                        password: "Passwort der Brieftasche",
                        successMessage: 'Das Konto {{1}} {{#2}}({{2}}){{/2}} wurde als Hauptkonto festgelegt!',
                        set: 'Als Hauptkonto festlegen',
                },
                changeWalletName: {
                        title: 'Namen der Brieftasche ändern',
                        wallet: 'Derzeitiger Name der Brieftasche',
                        newName: 'Neuer Name der Brieftasche',
                        password: "Passwort der Brieftasche",
                        successMessage: 'Der Name der Brieftasche wurde erfolgreich von <em>{{1}}</em> in <em>{{2}}</em> geändert',
                        change: 'Ändern'
                },
                changeWalletPassword: {
                        title: 'Passwort der Brieftasche ändern',
                        wallet: 'Name der Brieftasche',
                        password: 'Derzeitiges Passwort der Brieftasche',
                        newPassword: 'Neues Passwort',
                        confirmPassword: 'Neues Passwort bestätigen',
                        successMessage: 'Das Passwort der Brieftasche wurde erfolgreich geändert',
                        change: 'Ändern',
                        passwordNotMatchTitle: 'Oops!',
                        passwordNotMatchMessage: 'Passwort und Passwortbestätigung sind verschieden. Bitte gib das neue Passwort beide Male richtig ein.'
                },
                changeAccountLabel: {
                        title: 'Label des Kontos ändern',
                        label: 'Label des Kontos',
                        wallet: 'Name der Brieftasche',
                        password: "Passwort der Brieftasche",
                        successMessage: 'Das Konto {{1}} hat jetzt das Label {{2}}',
                        change: 'Ändern'
                },
                removeAccount: {
                        title: 'Konto entfernen',
                        wallet: 'Name der Brieftasche',
                        password: "Passwort der Brieftasche",
						warning: 'Stelle bitte sicher, dass auf Deinem Konto keine NEMs sind, bevor Du das Konto entfernst, da diese verloren gehen.',
                        successMessage: 'Das Konto {{1}} {{#2}}({{2}}){{/2}} wurde entfernt!',
                        remove: 'Entfernen'
                },
                nisUnavailable: {
                        title: 'NIS nicht verfügbar',
                        message: 'Nicht verbunden mit NIS, warte auf die Verbindung'
                },
				shutdown: {
					title: 'Programm schließen',
					message: 'Bist Du sicher, dass Du den NEM Community Client schließen möchtest?'
				}
        },
        landing: {
                logo: 'images/nem_logo.png',
                importSuccess: 'Die Brieftasche wurde erfolgreich importiert!',
                nav: {
                        start: 'Erste Schritte',
                        about: 'Info über NEM',
                        settings: 'Einstellungen'
                },
                main: {
                        leftTitle: 'Neu bei <em>NEM</em>?',
                        leftButton: 'Brieftasche anlegen',
                        walletNamePlh: 'Name Deiner Brieftasche',
                        passwordPlh: 'Passwort',
                        create: 'Anlegen',
                        rightTitle: 'Bist Du schon ein <em>NEM</em>ber?',
                        rightButton: 'Öffne Deine Brieftasche',
                        openButton: 'Öffnen',
                        walletsFound: '<strong>{{1}}</strong> <em>Brieftaschen</em> wurden gefunden',
                        copyright: 'Photografie von <em>Cas Cornelissen</em>'
                },
                carousel: {
                        items: [
                                {
                                        title: 'NCC verschlüsselt Deine Brieftasche',
                                        description: '<em>Sicherheit</em> ist sehr wichtig, um den Raub von Münzen &amp; Anlagen zu verhindern.'
                               },
                                {
                                        title: 'NCC verschlüsselt Deine Brieftasche',
                                        description: '<em>Sicherheit</em> ist sehr wichtig, um den Raub von Münzen &amp; Anlagen zu verhindern.'
                                }
                        ]
                },
                about: {
                        sections: [
                                {
                                        title: 'Wie funktioniert NCC?',
                                        paragraphs: [
                                                '<strong>NCC</strong> ermöglicht Dir den Zugriff auf Anlagen und NEMs genauso wie eine herkömmliche Brieftasche. Du kannst',
                                                '<strong>NCC</strong> braucht Zugang zu einem <strong>NIS</strong> Server, um zu funktionieren. Normalerweise wird der Server lokal betrieben (wird zusammen mit dem <strong>NCC</strong> installiert)',
                                                'Du kannst auch den Zugang zu einem remote <strong>NIS</strong> konfigurieren.'
                                        ],
                                        listItems: [
                                                'mehrere Brieftaschen haben',
                                                'mehrere Konten in jeder Brieftasche haben'
                                        ]
                                },
                                {
                                        title: 'Was ist &#42;NIS?',
                                        paragraphs: [
                                                'Diese Komponente ist dafür zuständig, die <strong>NEM</strong> Cloud am Leben zu halten.',
                                                'Je mehr <strong>NIS</strong> Server, desto besser die Sicherheit.',
                                                '<strong>NIS</strong> ist der Zugangspunkt zur <strong>NEM</strong> Cloud.'
                                        ],
                                        legend: '<strong>&#42;NIS</strong> steht für <strong>NEM Infrastructure Server</strong>'
                                }
                        ]
                },
                footer: {
                        copyright: '© Copyright 2014. NEM Community Client.'
                }
        },
        wallet: {
                logo: 'images/nem_logo.png',
                lastAccess: 'Ungefähr vor {{1}} Tagen',
                lastAccessJustNow: 'Gerade eben',
	 			lastAccessTooltip: 'Letzter Zugriff war {{1}}',
                primary: 'Hauptkonto',
                primaryShort: 'H',
                noLabel: '<Kein Label>',
                copiedToClipboard: 'Die Adresse wurde in die Zwischenablage kopiert!',
                actions: {
                        refreshInfo: 'Info aktualisieren',
                        bootLocalNode: 'Lokalen Knotenpunkt booten',
                        changeWalletName: 'Namen der Brieftasche ändern',
                        changeWalletPassword: 'Passwort der Brieftasche ändern',
                        mergeWallets: 'Brieftaschen zusammenführen',
                        exportWallet: 'Brieftasche exportieren',
                        createAccount: 'Neues Konto anlegen',
                        addAccount: 'Vorhandenes Konto hinzufügen',
                        changeAccountLabel: 'Label des Kontos ändern',
                        setPrimary: 'Hauptkonto festlegen',
                        removeAccount: 'Konto entfernen',
                        clientInfo: 'Client Info',
                        closeWallet: 'Brieftasche schließen',
                        closeProgram: 'Programm beenden',
                        copyClipboard: 'Addresse in die Zwischenablage kopieren'
                },
                nav: [
                        'Dashboard',
                        'Nachrichten',
                        'Kontakte',
                        'Transaktionen',
                        'Geerntete Blöcke',
                        'Börse',
                        'Neuigkeiten',
                        'Anwendungen',
                        'Konten',
                        'Einstellungen',
                        'Programm beenden'
                ],
                bootNodeWarning: "Der lokaler Knotenpunkt muss gebootet werden, bevor Du alle NCC Features verwenden kannst."
        },
        dashboard: {
                assets: {
                        title: 'Deine Anlagen'
                },
                importance: {
                        title: 'Wichtigkeit',
                        unknown: 'Unbekannter Status',
                        start: 'Ernte beginnen',
                        harvesting: 'Es wird geerntet',
                        stop: 'Ernte beenden',
                        description: 'Wichtigkeit des Kontos für die NEM Cloud'
                },
                transactions: {
                        title: 'Letzte Transaktionen',
						sendNem: 'NEM senden',
                        balance: 'Kontostand',
						syncStatus: '(Block {{1}}{{#2}} : etwa {{3}} Tage im Rückstand{{/2}})',
 						unknown: 'unbekannt',
                        columns: [
                                '',
                                'Zeitpunkt',
                                'Sender/Empfänger',
                                'Nachricht',
                                '',
                                'Details',
                                'Bestätigungen',
                                'Gebühr',
                                'Betrag'
                        ],
						types: {
							pending: 'Unbestätigte Transaktion',
							outgoing: 'Abgehende Transaktion',
							incoming: 'Eingehende Transaktion',
							self: 'Transaktion zu sich selbst'
						},
                        noMessage: 'Keine Nachricht',
                        encrypted: 'Nachricht ist verschlüsselt',
                        view: 'Ansehen',
                        pending: 'Unbestätigt',
                        seeAll: 'Alle Transaktionen ansehen',
                        noTransactions: 'Es wurden noch keine Transaktionen ausgeführt'
                },
                nemValue: {
                        title: 'NEM Statistiken'
                },
                messages: {
                        titleTooltip: 'Nachrichten'
                },
                news: {
                        titleTooltip: 'Neuigkeiten'
                },
                notAvailable: 'Noch nicht verfügbar im Alpha Release'
        },
        transactions: {
                title: 'Transaktionen',
                sendNem: 'NEM senden',
                balance: 'Kontostand',
                filters: {
                        confirmed: 'Bestätigt',
                        unconfirmed: 'Unbestätigt',
                        incoming: 'Eingehend',
                        outgoing: 'Abgehend',
                },
                table: {
                        columns: [
                                '',
                                'Zeitpunkt',
                                'Sender/Empfänger',
                                'Nachricht',
                                '',
                                'Details',
                                'Bestätigungen',
                                'Gebühr',
                                'Betrag'
                        ],
						types: {
							pending: 'Unbestätigte Transaktion',
							outgoing: 'Abgehende Transaktion',
							incoming: 'Eingehende Transaktion',
							self: 'Transaktion zu sich selbst'
						},
                        noMessage: 'Keine Nachricht',
                        encrypted: 'Nachricht ist verschlüsselt',
                        view: 'Ansehen',
                        pending: 'Unbestätigt',
                        noTransactions: 'Es wurden noch keine Transaktionen ausgeführt',
                        loadMore: 'Ältere Transaktionen ansehen'
                }
        },
        harvestedBlocks: {
                title: 'Geerntete Blöcke',
                feeEarned: 'Aus den letzten 25 geernteten Blöcken erhaltene Gebühren',
                table: {
                        columns: [
                                'Block',
                                'Zeitpunkt',
                                'Blockhash',
                                'Gebühr'
                        ],
                        noBlocks: 'Keine Blöcke geerntet',
                        loadMore: 'Ältere geerntete Blöcke ansehen'
                },
                harvesting: {
                        unknown: 'Unbekannter Status',
                        start: 'Ernte beginnen',
                        harvesting: 'Ernte',
                        stop: 'Ernte beenden'
                }
        },
        settings: {
                title: 'Einstellungen',
                settings: [
                        {
                                name: 'Sprache'
                        }
                ],
                save: 'Änderungen speichern',
				saveSuccess: 'Die Änderungen wurden erfolgreich gespeichert'
        }
    }
});
