define({
	id: "DE",
	name: "Deutsch",
	texts: {
		preferences: {
			thousandSeparator: " .",
			decimalSeparator: ","
		},
		faults: {
			101: 'Die Brieftasche existiert nicht.',
			102: "Die Brieftasche konnte nicht erstellt werden.",
			103: 'Die Brieftasche ist beschädigt. Bitte probiere sie aus einer Sicherungsdatei wiederherzustellen.',
			104: 'Das Passwort für die Brieftasche ist nicht korrekt.',
			105: 'Es wurde kein Passwort für die Brieftasche eingegeben.',
			106: "Bevor Du eine Brieftasche benutzen kannst, muss sie geöffnet werden. Um sicher zu gehen, dass Du berechtigt bist, die Brieftasche zu verwenden, musst Du das Passwort für die Brieftasche eingeben.",
			107: "Die Brieftasche enthält dieses Konto nicht.",
			108: "Das Konto kann nicht entfernt werden. Der Grund ist meistens, dass der Kontostand größer als 0 NEM ist oder, dass Du versuchst, ein Hauptkonto zu entfernen.",
			109: "Es gibt bereits eine Brieftasche mit demselben Namen. Bitte wähle einen anderen Namen für die Brieftasche.",
			110: "Die Brieftasche enthält dieses Konto schon.",
			111: 'Der Name der Brieftasche darf kein Dateierzeichnis sein.',
			112: 'Die Dateierweiterung für die Brieftasche ist nicht korrekt.',
			113: 'Die Brieftasche konnte nicht gelöscht werden.',
			121: 'Das Adressbuch existiert nicht.',
			122: 'Das Adressbuch konnte nicht erstellt werden.',
			123: 'Das Adressbuch ist beschädigt. Bitte probiere es aus einer Sicherungsdatei wiederherzustellen.',
			124: 'Das Passwort für die das Adressbuch ist nicht korrekt.',
			125: 'Es wurde kein Passwort für das Adressbuch eingegeben.',
			127: 'Das Adressbuch enthält die angegebene Adresse nicht.',
			129: 'Es gibt bereits ein Adressbuch mit demselben Namen. Bitte wähle einen anderen Namen für das Adressbuch.',
			130: 'Das Adressbuch enthält die Adresse bereits.',
			131: 'Der Name des Adressbuches darf kein Dateierzeichnis sein.',
			132: 'Die Dateierweiterung für das Adressbuch ist nicht korrekt.',
			133: 'Das Adressbuch konnte nicht gelöscht werden.',
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
			710: 'Die Transaktion wurde zurückgewiesen weil der Cache bereits zu voll ist. Eine höhere Gebühr erhöht die Chance dass die Transaktion akzeptiert wird.',
			730: 'Die Transaktion zur Übertragung der Wichtigkeit (für die sichere Ernte) steht im Konfikt zu einer anderen Transaktion.',
			731: 'Das Konto für die sichere Ernte ist nicht leer und kann daher nicht benutzt werden.',
			732: 'Transaktion zur Übertragung der Wichtigkeit eines Kontos wurde gescheitert. Eine andere solche Transaktion ist nocht nicht abgeschlossen..',
			733: 'Sichere Ernte ist bereits aktiviert.',
			734: 'Sichere Ernte ist nicht aktiviert und kann daher nicht deaktiviert werden.',
			740: 'Transaktion ist für eine Multisig-Konto nicht erlaubt.',
			741: 'Unterschriftstransaktion unzulässig da das Konto kein Mitunterzeichner des Multisig Kontos ist.',
			742: 'Unterschriftstransaktion unzulässig. Die zugehörige Multisig-Transaktion wurde nicht gefunden.',
			743: 'Änderung für das Multisig Konto unzulässig. Hinzufügen eines bereits vorhandenen Mitunterzeichners ist nicht möglich.',
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
				synchronized: "NIS ist synchron!",
				noRemoteNisAvailable: 'Kein remote NIS im Netzwerk verfügbar, Internetverbindung unterbrochen?'
			},
			addressBook: 'Adressbuch',
			password: "Passwort",
			passwordValidation: "Password must not be blank",
			address: "Adresse",
			privateLabel: "Privates Label",
			publicLabel: 'Public label',

		},
		transactionTypes: [
			'NEM Transfer',
			'Transfer der Wichtigkeit',
			'Änderung eines Multisig Kontos',
			'Multisig Transaktion',
			
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
				title: 'Konto in eine Multisig Konto umwandeln',
				multisigAccount: 'Multisig Konto',
				cosignatories: "Adressen der Mitunterzeichner",
				labelDesc: "Das Label des Kontos ist {{1}}",
				nullLabelDesc: "Dieses Konto hat kein Label",
				addCosignatory: '+ Mitunterzeichner hinzufügen',
				cancel: "Abbrechen",
				convert: 'Umwandeln',
				fee: "Gebühr",
				feeValidation: "Die Gebühr darf die Minimalgebühr nicht unterschreiten",
				dueBy: "Verfällt in",
				useMinimumFee: "Benutze minimale Gebühr",
				hours: "Stunde(n)",
				txConfirm: {
					title: 'Confirm Conversion to Multisig Account',
					total: "Insgesamt",

				},

			},
			signMultisig: {
				title: 'Multisig Transaktion unterzeichnen',
				sender: 'Mitunterzeichner',
				fee: "Gebühr",
				feeValidation: "Die Gebühr darf die Minimalgebühr nicht unterschreiten",
				dueBy: "Verfällt in",
				useMinimumFee: "Benutze minimale Gebühr",
				hours: "Stunde(n)",
				password: "Passwort",
				passwordValidation: "Das Passwort darf nicht leer sein",
				send: "Senden",
				cancel: "Abbrechen",
				sending: "Sende...",
				successMessage: "Die Transaktion wurde erfolgreich gesendet!",
				txConfirm: {
					title: 'Multisig Transaktion bestätigen',
					amount: "Betrag",
					from: 'Multisig Konto',
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
				fee: "Gebühr",
				cosignatory: 'Mitunterzeichner'
			},
			accountDetails: {
				title: "Kontodetails",
				address: "Adresse",
				label: "Label",
				noLabel: "Kein Label",
				add: "Zum Adressbuch hinzufügen",
				remove: "Vom Adressbuch entfernen",
				balance: "Kontostand",
				importance: "Wichtigkeit",
				publicKey: "Öffentlicher Schlüssel",
				noPublicKey: "öffentlicher Schlüssel unbekannt",
				harvestedBlocks: "Geerntete Böcke",
				close: "Schließen"
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
				signMultisig: 'Unterzeichnen',
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
			title: 'Adressbuch',
			addContact: 'Kontakt hinzufügen',
			table: {
				columns: [
					'Adresse des Kontakts',
					'Privater Label',
					'Öffentlicher Label'
				],
				noContacts: 'Keine Kontakte im Adressbuch vorhanden'
			},
			noLabel: 'Kein Label',
			sendNem: "NEM senden",
			edit: 'Bearbeiten',
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
