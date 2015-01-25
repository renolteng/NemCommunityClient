define({
	id: "DE",
	name: "Deutsch",
	texts: {
		preferences: {
			thousandSeparator: " .",
			decimalSeparator: ","
		},
		faults: {
			101: 'The wallet file does not exist.',
			102: "Die Brieftasche wurde nicht erstellt.",
			103: 'Wallet file is corrupt. Please recover your wallet from a backup.',
			104: 'The provided password for the wallet is not correct.',
			105: 'No password was provided for the wallet.',
			106: "Bevor Du eine Brieftasche benutzen kannst, muss sie geöffnet werden. Um sicher zu gehen, dass Du berechtigt bist, die Brieftasche zu verwenden, musst Du das Passwort für die Brieftasche eingeben.",
			107: "Die Brieftasche enthält dieses Konto nicht.",
			108: "Das Konto kann nicht entfernt werden. Der Grund ist meistens, dass der Kontostand größer als 0 NEM ist oder, dass Du versuchst, ein Hauptkonto zu entfernen.",
			109: "Es gibt bereits eine Brieftasche mit demselben Namen. Bitte wähle einen anderen Namen für die Brieftasche.",
			110: "Die Brieftasche enthält dieses Konto schon.",
			111: 'The wallet name is a directory.',
			112: 'The extension of the wallet file is incorrect.',
			113: 'The wallet could not be deleted.',
			121: 'The address book file does not exist.',
			122: 'Address book has not been created.',
			123: 'Address book file is corrupt. Please recover your address book from a backup.',
			124: 'The provided password for the address book is not correct.',
			125: 'No password was provided for the address book.',
			127: 'Address book does not contain this address.',
			129: 'Another address book with the same name exists already. Please choose an other address book name.',
			130: 'Address book already contains this address.',
			131: 'The address book name is a directory.',
			132: 'The extension of the address book file is incorrect.',
			133: 'The address book could not be deleted.',
			202: "Die verschlüsselte Nachricht kann nicht gesendet werden, da der Empfänger bisher noch keine Transaktion gemacht hat und deswegen der öffentliche Schlüssel des Empfängers unbekannt ist.",
			305: "Der NEM Infrastructure Server (NIS) ist nicht verfügbar.",
			306: "Entschuldigung! Es ist ein unvorhergesehener Fehler aufgetreten.\n\nMöglicherweise hilft ein Neustart. Falls, eröffne bitte einen Thread in der NEM NIS/NCC Community.",
			400: "Einer der Parameter fehlt oder ist ungültig.",
			401: "Dieser Vorgang kann nicht durchgeführt werden, da der private Schlüssel gestohlen werden könnte, wenn er an das remote NIS gesendet wird.",
			404: "Die angeforderte Ressource wurde nicht gefunden.",
			500: "Entschuldigung! Es ist ein unvorhergesehener Fehler aufgetreten.\n\nMöglicherweise hilft ein Neustart. Falls, eröffne bitte einen Thread in der NEM NIS/NCC Community.",
			600: "Der NEM Infrastructure Server (NIS) muss gebootet sein, damit Transaktionen gesendet und empfangen werden können. Bitte boote Deinen lokalen Knotenpunkt mit Hilfe des NCC Boot-Menüpunkts.",
			601: "Der NEM Infrastructure Server (NIS) ist bereits gebootet. Es ist nicht nötig, NIS ein weiteres Mal zu booten.",
			699: 'Maximum number of harvesters allowed on server has been reached.',
			700: "Das angegebene Konto erfüllt nicht die Grundkriterien, um Blöcke zu erzeugen. Um Blöcke erzeugen zu können, wird ein Kontostand von mindestens 1.000 NEM benötigt.",
			701: "Das angegebene Verfallsdatum liegt in der Vergangenheit. Das Verfallsdatum muss in einem Zeitraum von einem Tag liegen.",
			702: "Das angegebene Verfallsdatum liegt zu weit in der Zukunft. Das Verfallsdatum muss in einem Zeitraum von einem Tag liegen.",
			703: "Dein Kontoguthaben reicht nicht aus, um den angegebenen Betrag an NEM zu senden.",
			704: "Der angegebene Nachrichtentext ist zu lang. Bitte reduziere die Länge der Nachricht, um sie versenden zu können.",
			705: "Der Transaktionshash existiert bereits in der Datenbank oder in der Liste der unbestätigten Transaktionen.",
			706: "Die Signatur der Transaktion konnte nicht verifiziert werden.",
			707: "Der Zeitstempel der Transaktion liegt zu weit in der Vergangenheit.",
			708: "Der Zeitstempel der Transaktion liegt zu weit in der Zukunft.",
			709: "Das Konto ist unbekannt. Ein Konto muss mindestens einmal als Sender oder Empfänger in einer Transaktion auftreten, um dem Netzwerk bekannt zu sein.",
			730: 'Importance transfer transaction (secure harvesting) conflicts with existing transaction.',
			731: 'Secure harvesting account has non zero balance and cannot be used.',
			732: 'Importance transfer rejected. There is already pending importance transfer operation.',
			733: 'Secure harvesting is already active.',
			734: 'Secure harvesting is NOT active. Cannot deactivate.',
			740: 'Transaction is not allowed for multisig account.',
			741: 'Multisig signature transaction rejected. Current account is not a cosignatory of a multisig account.',
			742: 'Multisig signature transaction rejected. Associated multisig transaction is not known to NEM network',
			743: 'Multisig account modification rejected. One of added accounts is already a cosignatory.',
			901: "Es ist ein Fehler beim Übergang zum Offlinemodus aufgetreten.",
			1000: "Der eingegebene private Schlüssel passt nicht zum eingegebenen öffentlichen Schlüssel.",
			1001: "Der eingegebene öffentliche Schlüssel passt nicht zur eingegebenen Adresse.",
			1002: "Die Adresse gehört nicht zum Hauptnetzwerk."
		},
		common: {
			success: "Erfolg",
			appStatus: {
				nccUnknown: "NCC-Status ist unbekannt",
				nccUnavailable: "NCC ist nicht verfügbar",
				nccStarting: "NCC wird gestartet...",
				nisUnknown: "NIS-Status ist unbekannt",
				nisUnavailable: "NIS ist nicht verfügbar",
				nisStarting: "NIS wird gestartet...",
				notBooted: "NIS muss gebootet werden. Bitte öffne eine Brieftasche und boote den lokalen Knotenpunkt mit Hilfe des Popup-Dialoges.",
				booting: "NIS wird gebootet...",
				nisInfoNotAvailable: "NIS-Status wird abgefragt...",
				synchronizing: "NIS synchronisiert gerade. Block {{1}}, {{2}} im Rückstand.",
				daysBehind: {
					0: "weniger als 1 Tag",
					1: "etwa 1 Tag",
					many: "etwa {{1}} Tage"
				},
				synchronized: "NIS ist synchron!"
			},
			addressBook: 'Address book',
			password: "Passwort",
			address: "Adresse",
			privateLabel: "Privates Label",
			publicLabel: 'Public label',

		},
		transactionTypes: [
			'TRANSFER TRANSACTION',
			'IMPORTANCE TRANSFER',
			'MODIFICATION OF MULTISIG ACCOUNT',
			'MULTISIG TRANSACTION',
			
		],
		transactionDirections: {
			pending: "Unbestätigte Transaktion",
			outgoing: "Ausgehende Transaktion",
			incoming: "Eingehende Transaktion",
			self: "Transaktion zu sich selbst",

		},
		modals: {
			error: {
				title: "Oops!",
				caption: "FEHLER {{1}}"
			},
			confirmDefault: {
				yes: "Ja",
				no: "Nein"
			},
			settings: {
				title: "Einstellungen",
				language: {
					label: "Sprache"
				},
				remoteServer: {
					tabTitle: "Remote Server",
					protocol: "Protokoll",
					protocolOptions: {
						http: "HTTP"
					},
					host: "Host",
					port: "Port"
				},
				autoBoot: {
					tabTitle: "Auto-boot",
					name: "Name des Knotenpunkts",
					account: "Konto",
					primaryAccount: "Primäres Konto",
					auto: "Automatisch booten beim Öffnen der Brieftasche"
				},
				save: "Speichern",
				saveSuccess: "Die Einstellungen wurden erfolgreich gespeichert"
			},
			multisig: {
				title: 'Convert account to multisig',
				multisigAccount: 'Multisig account',
				cosignatories: "Cosignatories' addresses",
				labelDesc: "Das Label des Kontos ist {{1}}",
				nullLabelDesc: "Dieses Konto hat kein Label",
				addCosignatory: '+ Add Cosignatory',
				cancel: "Abbrechen",
				convert: 'Convert',
				fee: "Gebühr",
				feeValidation: "Die Gebühr darf die Minimalgebühr nicht unterschreiten",
				dueBy: "Verfällt in",
				useMinimumFee: "Benutze minimale Gebühr",
				hours: "Stunde(n)",

			},
			signMultisig: {
				title: 'Sign multisig transaction',
				sender: 'Cosignatory',
				fee: "Gebühr",
				feeValidation: "Die Gebühr darf die Minimalgebühr nicht unterschreiten",
				dueBy: "Verfällt in",
				useMinimumFee: "Benutze minimale Gebühr",
				hours: "Stunde(n)",
				password: "Passwort",
				passwordValidation: "Password must not be blank",
				send: "Senden",
				cancel: "Abbrechen",
				sending: "Sende...",
				successMessage: "Die Transaktion wurde erfolgreich gesendet!",
				txConfirm: {
					title: 'Confirm Multisig Transaction',
					amount: "Betrag",
					from: 'Multisig account',
					to: "An",
					fee: "Gebühr",
					dueBy: "Verfällt in",
					hours: "Stunde(n)",
					total: "Insgesamt",
					message: "Nachricht",
					encrypted: "Nachricht ist verschlüsselt",
					noMessage: "Keine Nachricht",

				},

			},
			sendNem: {
				title: "NEM senden",
				sender: "Sender",
				thisAccount: 'This account',
				labelDesc: "Das Label des Kontos ist {{1}}",
				nullLabelDesc: "Dieses Konto hat kein Label",
				amount: "Betrag",
				recipient: "Konto des Empfängers",
				recipientValidation: "Adressen müssen eine Länge von 40 Zeichen ohne die Bindestriche haben",
				message: "Nachricht",
				encrypt: "Nachricht verschlüsseln",
				fee: "Gebühr",
				feeValidation: "Die Gebühr darf die Minimalgebühr nicht unterschreiten",
				dueBy: "Verfällt in",
				useMinimumFee: "Benutze minimale Gebühr",
				hours: "Stunden",
				password: "Passwort",
				passwordValidation: "Password must not be blank",
				send: "Senden",
				cancel: "Abbrechen",
				sending: "Sende...",
				successMessage: "Die Transaktion wurde erfolgreich gesendet!",
				txConfirm: {
					title: "Transaktion bestätigen",
					amount: "Betrag",
					to: "An",
					fee: "Gebühr",
					dueBy: "Verfällt in",
					hours: "Stunde(n)",
					total: "Insgesamt",
					message: "Nachricht",
					encrypted: "Nachricht ist verschlüsselt",
					noMessage: "Keine Nachricht",
					cancel: "Abbrechen",
					confirm: "Bestätigen",
					sending: "Sende..."
				},
				notBootedWarning: {
					title: "Der Knotenpunkt wurde noch nicht gebootet!",
					message: "Der lokale Knotenpunkt muss gebootet werden, bevor Transaktionen gesendet werden können!"
				},
				bootingWarning: {
					title: "Der Knotenpunkt wird gebootet",
					message: "Bitte warte bis der Bootprozess abgeschlossen ist, bevor Du eine Transaktion sendest."
				}
			},
			clientInfo: {
				title: "Software Informationen",
				ncc: "NEM Community Client - NCC",
				signer: "Unterzeichner",
				remoteServer: "Remote Server",
				local: "Lokal",
				nis: "NEM Infrastructure Server - NIS",
				sync: "Synchron",
				notSync: "Nicht synchron",
				notConnected: "Nicht verbunden mit der NEM Cloud",
				loading: "Lade..."
			},
			transactionDetails: {
				title: "Transaktionsdetails",
				id: "ID",
				hash: "Hash",
				type: "Transaktionstyp",
				direction: 'Transaction Direction',
				pending: "Unbestätigt",
				outgoing: "Ausgehend",
				incoming: "Eingehend",
				self: "Selbst",
				sender: "Sender",
				recipient: "Empfänger",
				remote: 'Remote',
				multisigMessage: 'Signatures present',
				message: "Nachricht",
				noMessage: "Keine Nachricht",
				encrypted: "Nachricht ist verschlüsselt",
				time: "Zeitpunkt",
				confirmations: "Bestätigungen",
				confirmationsUnknown: "Unbekannt",
				amount: "Betrag",
				fee: "Gebühr"
			},
			accountDetails: {
				title: "Account details",
				address: "Address",
				label: "Label",
				noLabel: "No label",
				add: "Add to address book",
				remove: "Remove from address book",
				balance: "Balance",
				importance: "Importance",
				publicKey: "Public key",
				noPublicKey: "No public key",
				harvestedBlocks: "Harvested blocks",
				close: "Close"
			},
			bootLocalNode: {
				title: "Lokalen Knotenpunkt booten",
				account: "Konto, um den lokalen Knotenpunkt zu booten",
				noLabel: "<span class=\"null\"><Kein label></span>",
				wallet: "Brieftasche",
				node: "Name des Knotenpunkts",
				boot: "Booten",
				booting: "Wird gebootet..."
			},
			closeWallet: {
				title: "Brieftasche schließen",
				message: "Bist Du sicher, dass Du die Brieftasche schließen und zur Startseite wechseln möchtest?"
			},
			createAccount: {
				title: "Neues Konto anlegen",
				label: "Privates Label",
				wallet: "Brieftasche",
				password: "Passwort der Brieftasche",
				successMessage: "Das Konto {{1}} {{#2}}({{2}}){{/2}} wurde angelegt!",
				create: "Anlegen"
			},
			createRealAccountData: {
				title: "Daten für ein echtes Konto erzeugen",
				message: "Die nachstehend angezeigten Daten sind für ein echtes Konto nach dem offiziellen Start von NEM. Bitte speichere den privaten Schlüssel, den öffentlichen Schlüssel und die Adresse an einem sicheren Ort. Wenn Du den privaten Schlüssel verlierst, sind Deine Kontodaten und damit all Deine NEM für IMMER verloren.",
				address: "Adresse",
				publicKey: "Öffentlicher Schlüssel",
				privateKey: "Privater Schlüssel",
				confirm: {
					title: "Sicherung des privaten Schlüssels",
					message: "Bist Du sicher, dass Du den privaten Schlüssel an einem sicheren Ort gespeichert hast?"
				},
				recheck: {
					title: "Überprüfung des privaten Schlüssels",
					message: "Bitte gib den privaten Schlüssel erneut ein, um zu überprüfen, ob er korrekt ist. Wenn Du vergessen hast den Schlüsel zu speichern, musst Du einen Neuen erzeugen.",
					correct: {
						title: "Sehr gut!",
						message: "Du hast den richtigen Schlüssel gespeichert. Bewahre ihn an einem sicheren Ort auf!"
					},
					incorrect: {
						title: "Fehler",
						message: "Der private Schlüssel, den Du eingegeben hast, ist nicht korrekt. Bitte gebe ihn erneut ein.",
						tryAgain: "Bitte erneut eingeben.",
						seeOriginal: "Originaldaten anschauen."
					},
					recheck: "Überprüfung"
				},
				ok: "OK"
			},
			verifyRealAccountData: {
				title: "Reale Kontodaten verifizieren",
				message: "Bitte gib die gespeicherte Addresse, den öffentlichen und privaten Schlüssel ein, um zu überprüfen, ob sie zusammen passen.",
				address: "Adresse",
				publicKey: "Öffentlicher Schlüssel",
				privateKey: "Privater Schlüssel",
				dataMatched: "Es ist alles in Ordnung! Die Addresse, der öffentliche und private Schlüssel passen zusammen.",
				verify: "Überprüfen"
			},
			addAccount: {
				title: "Ein vorhandenes Konto hinzufügen",
				privateKey: "Privater Schlüssel des Kontos",
				wallet: "Brieftasche",
				password: "Passwort der Brieftasche",
				successMessage: "Das Konto {{1}} {{#2}}({{2}}){{/2}} wurde zur Brieftasche hinzugefügt!",
				add: "Hinzufügen",
				label: "Label"
			},
			setPrimary: {
				title: "Hauptkonto festlegen",
				account: "Konto, welches als Hauptkonto dient",
				noLabel: "<span class=\"null\"><Kein Label></span>",
				wallet: "Brieftasche",
				password: "Passwort der Brieftasche",
				successMessage: "Das Konto {{1}} {{#2}}({{2}}){{/2}} wurde als Hauptkonto festgelegt!",
				set: "Als Hauptkonto festlegen",

			},
			changeWalletName: {
				title: "Namen der Brieftasche ändern",
				wallet: "Derzeitiger Name der Brieftasche",
				newName: "Neuer Name der Brieftasche",
				password: "Passwort der Brieftasche",
				successMessage: "Der Name der Brieftasche wurde erfolgreich von <em>{{1}}</em> in <em>{{2}}</em> geändert",
				change: "Ändern"
			},
			changeWalletPassword: {
				title: "Passwort der Brieftasche ändern",
				wallet: "Name der Brieftasche",
				password: "Derzeitiges Passwort der Brieftasche",
				newPassword: "Neues Passwort",
				confirmPassword: "Neues Passwort bestätigen",
				successMessage: "Das Passwort der Brieftasche wurde erfolgreich geändert",
				change: "Ändern",
				passwordNotMatchTitle: "Oops!",
				passwordNotMatchMessage: "Passwort und Passwortbestätigung sind verschieden. Bitte gib das neue Passwort beide Male richtig ein."
			},
			changeAccountLabel: {
				title: "Label des Kontos ändern",
				label: "Label des Kontos",
				wallet: "Name der Brieftasche",
				password: "Passwort der Brieftasche",
				successMessage: "Das Konto {{1}} hat jetzt das Label {{2}}",
				change: "Ändern"
			},
			removeAccount: {
				title: "Konto entfernen",
				wallet: "Name der Brieftasche",
				password: "Passwort der Brieftasche",
				warning: "Stelle bitte sicher, dass auf Deinem Konto keine NEM sind, bevor Du das Konto entfernst, da diese verloren gehen.",
				successMessage: "Das Konto {{1}} {{#2}}({{2}}){{/2}} wurde entfernt!",
				remove: "Entfernen"
			},
			nisUnavailable: {
				title: "NIS nicht verfügbar",
				message: "Nicht verbunden mit NEM Infrastructure Server (NIS), warte auf die Verbindung"
			},
			shutdown: {
				title: "Programm schließen",
				message: "Bist Du sicher, dass Du den NEM Community Client schließen möchtest?"
			},
			activateRemote: {
				title: "Sichere Ernte aktivieren",
				wallet: "Brieftasche",
				account: "Konto",
				hoursDue: "Transaktion verfällt in (Stunden)",
				password: "Passwort der Bieftasche",
				activate: "Aktivieren"
			},
			deactivateRemote: {
				title: "Sichere Ernte deaktivieren",
				wallet: "Brieftasche",
				account: "Konto",
				hoursDue: "Transaktion verfällt in (Stunden)",
				password: "Passwort der Bieftasche",
				deactivate: "Deaktivieren"
			},
			startRemote: {
				title: "Sichere Ernte starten",
				wallet: "Brieftasche",
				account: "Konto",
				password: "Passwort der Brieftasche",
				start: "Starten"
			},
			stopRemote: {
				title: "Sichere Ernte beenden",
				wallet: "Brieftasche",
				account: "Konto",
				password: "Passwort der Brieftasche",
				stop: "Beenden"
			},
			logoutWarning: {
				leavePage: "Du bist dabei die Seite zu verlassen ohne Deine Brieftasche zu schließen. Dadurch könnten andere Zugang zu Deiner Brieftasche erhalten.\n\nUm dies zu verhindern solltest Du den Menüpunkt \"Brieftasche schließen\" im Aufklappmenü anwählen bevor Du den Browser schließt oder zu einer anderen Seite navigierst.\n",

			},
			addContact: {
				title: 'Add contact',
				add: "Hinzufügen"
			},
			editContact: {
				title: 'Edit contact',
				saveChanges: "Änderungen speichern",

			},
			removeContact: {
				title: 'Remove contact',
				remove: "Entfernen",

			}
		},
		landing: {
			logo: "images/nem_logo.png",
			importSuccess: "Die Brieftasche wurde erfolgreich importiert!",
			nav: {
				start: "Erste Schritte",
				about: "Über NEM",
				settings: "Einstellungen"
			},
			main: {
				leftTitle: "Neu bei <em>NEM</em>?",
				leftButton: "Brieftasche anlegen",
				walletNamePlh: "Name Deiner Brieftasche",
				passwordPlh: "Passwort",
				confirmPasswordPlh: "Passwort bestätigen",
				create: "Anlegen",
				creating: "Wird angelegt...",
				rightTitle: "Bist Du schon ein <em>NEM</em>ber?",
				rightButton: "Öffne Deine Brieftasche",
				openButton: "Öffnen",
				walletsFound: "<strong>{{1}}</strong> <em>Brieftasche(n)</em> gefunden",
				copyright: "Photografie von <em>Cas Cornelissen</em>"
			},
			carousel: {
				items: [
					{
						title: "NCC verschlüsselt Deine Brieftasche",
						description: "<em>Sicherheit</em> ist sehr wichtig, um den Raub von Münzen &amp; Anlagen zu verhindern."
					},
					{
						title: "NCC verschlüsselt Deine Brieftasche",
						description: "<em>Sicherheit</em> ist sehr wichtig, um den Raub von Münzen &amp; Anlagen zu verhindern."
					}
				]
			},
			about: {
				sections: [
					{
						title: "Wie funktioniert NCC?",
						paragraphs: [
							"<strong>NCC</strong> ermöglicht Dir den Zugriff auf Anlagen und NEM genauso wie eine herkömmliche Brieftasche. Du kannst",
							"<strong>NCC</strong> braucht Zugang zu einem <strong>NIS</strong> Server, um zu funktionieren. Normalerweise wird der Server lokal betrieben (wird zusammen mit dem <strong>NCC</strong> installiert)",
							"Du kannst auch den Zugang zu einem remote <strong>NIS</strong> konfigurieren."
						],
						listItems: [
							"mehrere Brieftaschen haben",
							"mehrere Konten in jeder Brieftasche haben"
						]
					},
					{
						title: "Was ist &#42;NIS?",
						paragraphs: [
							"Diese Komponente ist dafür zuständig, die <strong>NEM</strong> Cloud am Leben zu halten.",
							"Je mehr <strong>NIS</strong> Server, desto höher die Sicherheit.",
							"<strong>NIS</strong> ist der Zugangspunkt zur <strong>NEM</strong> Cloud."
						],
						legend: "<strong>&#42;NIS</strong> steht für <strong>NEM Infrastructure Server</strong>"
					}
				]
			},
			footer: {
				copyright: "© Copyright 2014. NEM Community Client."
			}
		},
		wallet: {
			logo: "images/nem_logo.png",
			lastAccess: "Ungefähr vor {{1}} Tagen",
			lastAccessJustNow: "Gerade eben",
			lastAccessTooltip: "Letzter Zugriff war {{1}}",
			primary: "Hauptkonto",
			primaryShort: "H",
			noLabel: "<Kein Label>",
			copiedToClipboard: "Die Adresse wurde in die Zwischenablage kopiert!",
			actions: {
				refreshInfo: "Informationen aktualisieren",
				bootLocalNode: "Lokalen Knotenpunkt booten",
				changeWalletName: "Namen der Brieftasche ändern",
				changeWalletPassword: "Passwort der Brieftasche ändern",
				mergeWallets: "Brieftaschen zusammenführen",
				exportWallet: "Brieftasche exportieren",
				createAccount: "Neues Konto anlegen",
				createRealAccountData: "Daten für ein echtes Konto erzeugen",
				verifyRealAccountData: "Daten eines echten Kontos verifizieren",
				addAccount: "Vorhandenes Konto hinzufügen",
				changeAccountLabel: "Label des Kontos ändern",
				setPrimary: "Hauptkonto festlegen",
				removeAccount: "Konto entfernen",
				clientInfo: "Software Informationen",
				closeWallet: "Brieftasche schließen",
				closeProgram: "Programm beenden",
				copyClipboard: "Addresse in die Zwischenablage kopieren",
				convertMultisig: 'Convert to multisig'
			},
			nav: [
				"Dashboard",
				"Nachrichten",
				'Address Book',
				"Transaktionen",
				"Geerntete Blöcke",
				"Börse",
				"Neuigkeiten",
				"Anwendungen",
				"Konten",
				"Einstellungen",
				"Programm beenden"
			],
			bootNodeWarning: "Der lokaler Knotenpunkt muss gebootet werden, bevor Du alle NCC Features verwenden kannst."
		},
		dashboard: {
			assets: {
				title: "Deine Anlagen"
			},
			importance: {
				title: "Wichtigkeit",
				unknown: "Unbekannter Status",
				start: "Ernte beginnen",
				harvesting: "Es wird geerntet",
				stop: "Ernte beenden",
				description: "Wichtigkeit des Kontos für die NEM Cloud",
				remoteHarvest: {
					activate: "Sichere Ernte aktivieren",
					activating: "Aktiviere sichere Ernte...",
					active: "Sichere Ernte ist aktiv",
					deactivate: "Sichere Ernte deaktivieren",
					deactivating: "Deaktiviere sichere Ernte...",
					startRemoteHarvesting: "Sichere Ernte beginnen",
					remotelyHarvesting: "Sichere Ernte",
					stopRemoteHarvesting: "Sichere Ernte beenden"
				}
			},
			transactions: {
				title: "Letzte Transaktionen",
				sendNem: "NEM senden",
				signMultisig: 'SIGN',
				balance: "Kontostand",
				syncStatus: "(Block {{1}}{{#2}} : etwa {{3}} Tage im Rückstand{{/2}})",
				unknown: "unbekannt",
				columns: [
					"",
					"Zeitpunkt",
					"Sender/Empfänger",
					"Nachricht",
					"",
					"Details",
					"Bestätigungen",
					"Gebühr",
					"Betrag"
				],
				noMessage: "Keine Nachricht",
				encrypted: "Nachricht ist verschlüsselt",
				view: "Ansehen",
				confirmationsUnknown: "Unbekannt",
				pending: "Unbestätigt",
				seeAll: "Alle Transaktionen ansehen",
				noTransactions: "Es wurden noch keine Transaktionen ausgeführt"
			},
			nemValue: {
				title: "NEM Statistiken"
			},
			messages: {
				titleTooltip: "Nachrichten"
			},
			news: {
				titleTooltip: "Neuigkeiten"
			},
			notAvailable: "Noch nicht verfügbar im Beta Release"
		},
		transactions: {
			title: "Transaktionen",
			sendNem: "NEM senden",
			balance: "Kontostand",
			filters: {
				confirmed: "Bestätigt",
				unconfirmed: "Unbestätigt",
				incoming: "Eingehend",
				outgoing: "Ausgehend",

			},
			table: {
				columns: [
					"",
					"Zeitpunkt",
					"Sender/Empfänger",
					"Nachricht",
					"",
					"Details",
					"Bestätigungen",
					"Gebühr",
					"Betrag"
				],
				noMessage: "Keine Nachricht",
				encrypted: "Nachricht ist verschlüsselt",
				view: "Ansehen",
				confirmationsUnknown: "Unbekannt",
				pending: "Unbestätigt",
				noTransactions: "Es wurden noch keine Transaktionen ausgeführt",
				loading: "Lade weitere Transaktionen..."
			}
		},
		harvestedBlocks: {
			title: "Geerntete Blöcke",
			feeEarned: "Aus den letzten 25 geernteten Blöcken erhaltene Gebühren",
			unknown: "Unbekannt",
			table: {
				columns: [
					"Block",
					"Zeitpunkt",
					"Blockhash",
					"Gebühr"
				],
				noBlocks: "Keine Blöcke geerntet",
				loading: "Lade weitere geerntete Blöcke..."
			},
			harvesting: {
				unknown: "Unbekannter Status",
				start: "Ernte beginnen",
				harvesting: "Ernte",
				stop: "Ernte beenden",
				remoteHarvest: {
					startRemoteHarvesting: "Sichere Ernte beginnen",
					stopRemoteHarvesting: "Sichere Ernte beenden"
				}
			}
		},
		addressBook: {
			title: 'Address book',
			addContact: 'Add contact',
			table: {
				columns: [
					'Account address',
					'Private Label',
					'Public Label'
				],
				noContacts: 'There is no contacts in your address book'
			},
			noLabel: 'No label',
			sendNem: "NEM senden",
			edit: 'Edit',
			remove: "Entfernen"
		},
		settings: {
			title: "Einstellungen",
			settings: [
				{
					name: "Sprache"
				}
			],
			save: "Änderungen speichern",
			saveSuccess: "Die Änderungen wurden erfolgreich gespeichert"
		}
	}
});
