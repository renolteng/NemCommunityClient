define({
	id: 'nl',
	name: 'Nederlands',
	texts: {
		preferences: {
			thousandSeparator: '\u2009',
			decimalSeparator: '.'
		},
		faults: {
			101: 'The wallet file does not exist.',
			102: 'Wallet is niet aangemaakt.',
			103: 'Wallet file is corrupt. Please recover your wallet from a backup.',
			104: 'The provided password for the wallet is not correct.',
			105: 'No password was provided for the wallet.',
			106: 'Voordat je met je wallet kan beginnen, moet hij worden geopend. Om er zeker van te zijn dat jij de rechtmatige eigenaar bent, moet je het wachtwoord opgeven.',
			107: 'Rekeningnummer komt niet voor in de wallet.',
			108: 'De rekening kan niet worden verwijderd. Waarschijnlijk komt dit omdat er nog XEM in zit. Het kan ook zijn dat de rekening de primaire rekening is van de wallet.',
			109: 'Een andere wallet met dezelfde naam bestaat al, kies een andere wallet-naam.',
			110: 'Rekening komt al voor in de wallet.',
			111: 'The wallet name is a directory.',
			112: 'The extension of the wallet file is incorrect.',
			113: 'The wallet could not be deleted.',
			121: 'The address book file does not exist.',
			122: 'Address book has not been created.',
			123: 'Address book file is corrupt. Please recover your address book from a backup.',
			124: 'The provided password for the address book is not correct.',
			125: 'No password was provided for the address book.',
			127: 'Address book does not contain this address.',
			128: 'The address provided is not valid.',
			129: 'Another address book with the same name exists already. Please choose an other address book name.',
			130: 'Address book already contains this address.',
			131: 'The address book name is a directory.',
			132: 'The extension of the address book file is incorrect.',
			133: 'The address book could not be deleted.',
			202: 'Een beveiligd bericht kan niet worden verstuurd naar de ontvanger omdat hij of zij nog nooit een transactie heeft gemaakt met dit rekeningnummer.',
			305: 'NEM Infrastructure Server is niet beschikbaar.',
			306: 'Een fout is opgetreden wat het ontwikkelteam niet heeft voorzien. Onze verontschuldiging hiervoor, misschien helpt het om nog een keer te proberen. Als dat ook niet lukt is het wijs om een ticket te openen binnen de NEM NIS/NCC community.',
			400: 'Een parameter is missend of niet goed.',
			401: 'Deze actie is niet toegestaan, omdat er een privé sleutel naar een remote NIS kan worden verstuurd.',
			404: 'De opgevraagde bron kom niet worden gevonden..',
			500: 'Een fout is opgetreden wat het ontwikkelteam niet heeft voorzien. Onze verontschuldiging hiervoor, misschien helpt het om nog een keer te proberen. Als dat ook niet lukt is het wijs om een ticket te openen binnen de NEM NIS/NCC community.',
			600: 'Voor de NCC (Nem Community Client) is het vereist om de NIS (Network Infrastructure Server) het starten voor het verzenden en ontvangen van transacties van de NEM cloud. Gebruik de NCC menu optie om lokaal te starten.',
			601: 'De nis node is al gestart. Een tweede poging om te starten is niet mogelijk.',
			602: 'Cannot perform any operations until db is fully loaded.',
			699: 'Maximum number of harvesters allowed on server has been reached.',
			700: 'De opgegeven rekening voldoet niet aan de basis criteria om te harvesten/oogsten. Waarschijnlijk heeft dat te maken met het aantal XEM wat zich in de rekening bevindt. Harvesten begint bij tenminste 1000 XEM.',
			701: 'De termijn ligt in het verleden en kan alleen geldig zijn binnen een periode van 1 dag.',
			702: 'De termijn ligt te ver in de toekomst en kan alleen geldig zijn binnen een periode van 1 dag.',
			703: 'De rekening heeft niet genoeg saldo om het aangegeven aantal XEM te verzenden.',
			704: 'De tekst is te groot om het te verzenden via NEM. Reduceer de tekst en probeer opnieuw te verzenden.',
			705: 'De hash van de transactie bestaat al in de database van onbevestigde transacties.',
			706: 'De handtekening van de transactie kon niet worden geverifieerd.',
			707: 'De timestamp van de transactie ligt te ver in het verleden.',
			708: 'De timestamp van de transactie ligt te ver in de toekomst.',
			709: 'Het rekeningnummer is onbekend.  Een rekening wordt pas bekend op het netwerk als deze deel heeft uitgemaakt van een transactie als verzender of ontvanger.',
			710: 'The transaction was rejected because the transaction cache is too full. A higher fee improves the chance that the transaction gets accepted.',
			730: 'Importance transfer transaction (secure harvesting) conflicts with existing transaction.',
			731: 'Secure harvesting account has non zero balance and cannot be used.',
			732: 'Importance transfer rejected. There is already pending importance transfer operation.',
			733: 'Secure harvesting is already active.',
			734: 'Secure harvesting is NOT active. Cannot deactivate.',
			740: 'Transaction is not allowed for multisig account.',
			741: 'Multisig signature transaction rejected. Current account is not a cosignatory of a multisig account.',
			742: 'Multisig signature transaction rejected. Associated multisig transaction is not known to NEM network',
			743: 'Multisig account modification rejected. One of added accounts is already a cosignatory.',
			901: 'Er is een fout opgetreden bij het instellen van de offline modus.',
			1000: "The privé sleutel en publieke sleutel komen niet overeen.",
			1001: 'The publieke sleutel en het adres komen niet overeen.',
			1002: 'Het adres klopt niet en hoort niet bij het netwerk.'
		},
		common: {
			success: 'Gelukt!',
			appStatus: {
				nccUnknown: 'NCC status is onbekend',
				nccUnavailable: 'NCC is niet beschikbaar',
				nccStarting: 'NCC is aan het starten...',
				nisUnknown: 'NIS status is onbekend',
				nisUnavailable: 'NIS is niet beschikbaar',
				nisStarting: 'NIS is aan het starten...',
				notBooted: 'Het is vereist om NIS te (her)starten. Open een wallet en start een lokale bij het popup dialoog.',
				loading: 'Loading blocks from db, at block: ',
				booting: 'Starten van de NIS...',
				nisInfoNotAvailable: 'NIS info is not avaiable yet. Trying to retrieve NIS info...',
				synchronizing: 'NIS is aan het synchronizeren. Bij block {{1}}, ong. {{2}} achter.',
				daysBehind: {
					0: 'minder dan 1 dag',
					1: '1 dag',
					many: '{{1}} dagen'
				},
				synchronized: 'NIS is gesynchroniseerd!',
				noRemoteNisAvailable: 'No remote NIS found in the network, disconnected from internet?'
			},
			addressBook: 'Address book',
			password: 'Wachtwoord',
			passwordValidation: 'Password must not be blank',
			address: 'Address',
			privateLabel: 'Privé label',
			publicLabel: 'Public label',
			noCharge: 'Current account will <b>NOT</b> be charged any fees, multisig account covers them',
			justUse: 'Just use'
		},
		transactionTypes: [
			'TRANSFER TRANSACTION',
			'IMPORTANCE TRANSFER',
			'MODIFICATION OF MULTISIG ACCOUNT',
			'MULTISIG TRANSACTION'
		],
		transactionDirections: {
			pending: 'Wachtende transactie',
			outgoing: 'Uitgaande transactie',
			incoming: 'Inkomende transactie',
			self: 'Eigen transactie',
			importance: 'Importance transaction',
			modification: 'Aggregate Modification of Multisig'
		},
		modals: {
			error: {
				title: 'Oeps!',
				caption: 'ERROR {{1}}'
			},
			confirmDefault: {
				yes: 'Ja',
				no: 'Nee'
			},
			settings: {
				title: 'Instellingen',
				language: {
					label: 'Taal'
				},
				remoteServer: {
					tabTitle: 'Remote Server',
					protocol: 'Protocol',
					protocolOptions: {
						http: 'HTTP'
					},
					host: 'Host',
					port: 'Port'
				},
				autoBoot: {
					tabTitle: 'Auto-start',
					name: 'Node naam',
					account: 'Rekening',
					primaryAccount: 'Primaire rekening',
					auto: 'Auto starten als een wallet is geopend'
				},
				save: 'Opslaan',
				saveSuccess: 'Instelling zijn met succes opgeslagen'
			},
			multisig: {
				title: 'Convert account to multisig',
				multisigAccount: 'Multisig account',
				cosignatories: "Cosignatories' addresses",
				labelDesc: 'Deze rekening is als {{1}} gelabeld',
				nullLabelDesc: "Deze rekening heeft geen label",
				addCosignatory: '+ Add Cosignatory',
				cancel: 'Annuleer',
				convert: 'Convert',
				fee: 'Toeslag',
				feeValidation: 'Toeslag mag niet lager zijn dan het minimum',
				dueBy: 'Verzenden voor',
				useMinimumFee: 'Gebruik minimale toeslag',
				hours: 'uur',
				txConfirm: {
					title: 'Confirm Conversion to Multisig Account',
					total: 'Totaal',

				},
				warning: 'Multisig account is on the list of cosignatories. This will result in locking down the account cutting off access to the fund. most likely you <b>DO NOT</b> want to do that.'
			},
			signMultisig: {
				title: 'Sign multisig transaction',
				original: {
					from: 'Multisig account',
					to: 'Ontvanger',
					amount: 'Bedrag',
					fee: 'Inner Fee',
					deadline: 'Deadline'
				},
				multisigFees: 'Multisig Fees',
				multisigTotal: 'Totaal',
				sender: 'Cosignatory',
				fee: 'Toeslag',
				feeValidation: 'Toeslag mag niet lager zijn dan het minimum',
				dueBy: 'Verzenden voor',
				useMinimumFee: 'Gebruik minimale toeslag',
				hours: 'uur',
				password: 'Wachtwoord',
				passwordValidation: 'Password must not be blank',
				send: 'Verstuur',
				cancel: 'Annuleer',
				sending: 'Verzenden...',
				successMessage: 'Transactie is verzonden!',
				txConfirm: {
					title: 'Confirm Multisig Transaction',
					message: 'Bericht',
					encrypted: 'Bericht is versleuteld',
					noMessage: 'Geen bericht',

				}
			},
			sendNem: {
				title: 'XEM versturen',
				sender: 'Afzender',
				thisAccount: 'This account',
				labelDesc: 'Deze rekening is als {{1}} gelabeld',
				nullLabelDesc: "Deze rekening heeft geen label",
				amount: 'Bedrag',
				recipient: "Rekening van ontvanger",
				recipientValidation: 'Rekeningen moeten een lengte hebben van 40 karakters en dashes',
				message: 'Bericht',
				encrypt: 'Beveilig bericht',
				fee: 'Toeslag',
				multisigFee: 'Multisig fee',
				feeValidation: 'Toeslag mag niet lager zijn dan het minimum',
				dueBy: 'Overmaken voor',
				useMinimumFee: 'Gebruik minimale toeslag',
				hours: 'Uur',
				password: 'Wachtwoord',
				passwordValidation: 'Password must not be blank',
				send: 'Verstuur',
				cancel: 'Afbreken',
				sending: 'Versturen...',
				successMessage: 'Transactie is verzonden!',
				txConfirm: {
					title: 'Bevestig transactie',
					amount: 'Hoeveelheid',
					to: 'Naar',
					dueBy: 'Verzenden voor',
					hours: 'uur',
					total: 'Totaal',
					message: 'Bericht',
					encrypted: 'Bericht is geencrypt',
					noMessage: 'Geen bericht',
					cancel: 'Annuleer',
					confirm: 'Bevestig',
					sending: 'Verzenden...'
				},
				notBootedWarning: {
					title: 'Node is niet gestart!',
					message: 'Een lokale node moet eerst gestart worden om XEM te versturen!'
				},
				bootingWarning: {
					title: 'Node wordt opgestart',
					message: 'Even wachten a.u.b. tot het opstartproces klaar is om je transactie te verzenden.'
				},
				loadingWarning: {
					title: 'Loading db'
				}
			},
			clientInfo: {
				title: 'Client informatie',
				ncc: 'NEM Community Client - NCC',
				signer: 'Signer',
				remoteServer: 'Remote Server',
				local: 'Lokale Server',
				nis: 'NEM Infrastructure Server - NIS',
				sync: 'Gesynchroniseerd',
				notSync: 'Niet gesynchroniseerd',
				notConnected: 'Kan geen verbinding maken met de NEM Cloud',
				loading: 'Laden...'
			},
			transactionDetails: {
				title: 'Transactie details',
				id: 'ID',
				hash: 'Hash',
				type: 'Type transactie',
				direction: 'Transaction Direction',
				pending: 'Wachten',
				outgoing: 'Uitgaand',
				incoming: 'Inkomend',
				self: 'Zelf',
				sender: 'Afzender',
				multisigAccount: 'Multisig Account',
				issuer: 'Issuer',
				recipient: 'Ontvanger',
				remote: 'Remote',
				multisigMessage: 'Signatures present',
				message: 'Bericht',
				noMessage: 'Geen bericht',
				encrypted: 'Bericht is beveiligd',
				time: 'Timestamp',
				confirmations: 'Confirmaties',
				confirmationsUnknown: 'Onbekend',
				amount: 'Bedrag',
				fee: 'Toeslag',
				innerFee: 'Inner Fee',
				multisigFees: 'Multisig Fees',
				cosignatory: 'Cosignatory'
			},
			accountDetails: {
				title: "Account details",
				address: "Address",
				label: "Label",
				noLabel: "No label",
				add: "Add to address book",
				remove: "Remove from address book",
				balance: "Balance",
				vested: "vested",
				importance: "Importance",
				publicKey: "Public key",
				noPublicKey: "No public key",
				harvestedBlocks: "Harvested blocks",
				close: "Close"
			},
			bootLocalNode: {
				title: 'Start lokale node',
				account: 'Rekening voor het starten van de lokale node',
				noLabel: '<span class="null">&lt;Geen label&gt;</span>',
				wallet: 'Wallet',
				node: 'Node naam',
				boot: 'Start',
				booting: 'Starten...'
			},
			closeWallet: {
				title: 'Wallet sluiten',
				message: 'Weet je het zeker om terug te keren naar de openingspagina?'
			},
			createAccount: {
				title: 'Nieuwe rekening aanmaken',
				label: 'Privé label',
				wallet: 'Wallet',
				password: "Wallet wachtwoord",
				successMessage: 'Rekening {{1}} {{#2}}({{2}}){{/2}} is aangemaakt.!',
				create: 'Aanmaken'
			},
			createRealAccountData: {
				title: 'Aanmaken ECHTE account',
				message: 'De onderstaande gegevens is bedoeld voor je echte account, nadat NEM live-gang is geweest. Bewaard het adres, publieke sleutel en het belangrijkste: de privé sleutel op een veilige plaats. Als de privé sleutel verloren raakt is het niet meer mogelijk om toegang tot je XEM te krijgen!',
				address: 'Adres',
				publicKey: 'Publiek sleutel key',
				privateKey: 'Privé sleutel',
				confirm: {
					title: 'Bewaar de privé sleutel',
					message: 'Weet je zeker dat de privé sleutel op een veilige plaats is opgeslagen?'
				},
				recheck: {
					title: 'Controleer je opgeslagen privé sleutel',
					message: "Voer de privé sleutel nogmaals voor controle. Als dit niet lukt is het misschien beter om een nieuwe te maken.",
					correct: {
						title: 'Gelukt!',
						message: 'Je hebt de correcte privé sleutel met succes opgeslagen. Vergeet niet om de sleutel altijd veilig te bewaren!'
					},
					incorrect: {
						title: 'Hmm...',
						message: "Helaas, de privé sleutel die je hebt ingevoerd is niet correct! Controleer dit goed en voer het nogmaals in.",
						tryAgain: 'Try to enter again',
						seeOriginal: 'See the original data'
					},
					recheck: 'Controle'
				},
				ok: 'OK'
			},
			verifyRealAccountData: {
				title: 'Verify real account data',
				message: 'Re-enter your saved address, public key and private key below to check if they match',
				address: 'Address',
				publicKey: 'Public key',
				privateKey: 'Private key',
				dataMatched: 'Everything seems good, your entered address, public key, and private key match.',
				verify: 'Verify'
			},
			addAccount: {
				title: 'Voeg een bestaande rekening toe',
				privateKey: "Privésleutel van rekening",
				wallet: 'Wallet',
				password: "Wallet wachtwoord",
				successMessage: 'Rekening {{1}} {{#2}}({{2}}){{/2}} is toegevoegd aan wallet!',
				add: 'Voeg toe',
				label: 'Label'
			},
			setPrimary: {
				title: 'Primaire rekening instellen',
				account: 'Rekening die primair wordt ingesteld.',
				noLabel: '<span class="null">&lt;Geen label&gt;</span>',
				wallet: 'Wallet',
				password: "Wallet wachtwoord",
				successMessage: 'Rekening {{1}} {{#2}}({{2}}){{/2}} als primair ingesteld!',
				set: 'Stel primaire rekening in'
			},
			changeWalletName: {
				title: 'Verander naam van wallet',
				wallet: 'Huidige wallet naam',
				newName: 'Nieuwe wallet naam',
				password: "Wallet wachtwoord",
				successMessage: 'Naam van wallet is gewijzigd van <em>{{1}}</em> naar <em>{{2}}</em>',
				change: 'Change'
			},
			changeWalletPassword: {
				title: 'Verander het Wallet wachtwoord ',
				wallet: 'Wallet',
				password: 'Huidig wachtwoord',
				newPassword: 'Nieuw wachtwoord',
				confirmPassword: 'Bevestig nieuw wachtwoord',
				successMessage: 'Wallet wachtwoord is aangepast.',
				change: 'Aanpassen',
				passwordNotMatchTitle: 'Oeps!',
				passwordNotMatchMessage: 'Het wachtwoord komt niet overeen.'
			},
			changeAccountLabel: {
				title: 'Verander het label van de rekening',
				label: 'Rekening label',
				wallet: 'Wallet',
				password: "Wallet wachtwoord",
				successMessage: 'Rekening {{1}} is gelabeld als {{2}}',
				change: 'Aanpassen'
			},
			removeAccount: {
				title: 'Rekening verwijderen',
				wallet: 'Wallet',
				password: "Wallet wachtwoord",
				warning: 'Let op! Wees er zeker van dat er geen XEM saldo opstaat. Eventueel saldo wordt voor altijd verwijderd.',
				successMessage: 'Rekening {{1}} {{#2}}({{2}}){{/2}} is verwijderd!',
				remove: 'Verwijderen'
			},
			nisUnavailable: {
				title: 'NIS niet beschikbaar',
				message: 'NIS connectie onderbroken, wachten op verbinding'
			},
			shutdown: {
				title: 'Afsluiten',
				message: 'Weet je zeker dat je de NEM Community Client wil afsluiten?'
			},
			activateRemote: {
				title: 'Activeer Remote harvesten',
				wallet: 'Wallet',
				account: 'Account',
				hoursDue: 'Binnen (uren)',
				password: "Wallet wachtwoord",
				activate: 'Activeren'
			},
			deactivateRemote: {
				title: 'Deactiveer Remote harvesten',
				wallet: 'Wallet',
				account: 'Account',
				hoursDue: 'Binnen (uren)',
				password: "Wallet wachtwoord",
				deactivate: 'Deactiveer'
			},
			startRemote: {
				title: 'Start Remote harvesten',
				wallet: 'Wallet',
				account: 'Account',
				password: "Wallet wachtwoord",
				start: 'Start'
			},
			stopRemote: {
				title: 'Stop Remote harvesting',
				wallet: 'Wallet',
				account: 'Account',
				password: "Wallet's password",
				stop: 'Stop'
			},
			logoutWarning: {
				leavePage: "Je gaat de wallet verlaten. Onthoud dat op deze manier andere gebruikers op deze computer hier toegang toe kunnen krijgen.\n\nLog uit met het \"Sluit Wallet\" menu item in het dropdown menu rechts-boven voordat je de browser sluit of naar een andere pagina navigeert."
			},
			addContact: {
				title: 'Add contact',
				add: 'Voeg toe'
			},
			editContact: {
				title: 'Edit contact',
				saveChanges: 'Opslaan'
			},
			removeContact: {
				title: 'Remove contact',
				remove: 'Verwijderen'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'Wallet is met succes geimporteerd!',
			nav: {
				start: 'Aan de slag',
				about: 'Over NEM',
				settings: 'Instellingen'
			},
			main: {
				leftTitle: 'Nieuw bij <em>NEM</em>?',
				leftButton: 'Maak een nieuwe wallet',
				walletNamePlh: 'Naam van de wallet',
				passwordPlh: 'Wachtwoord',
				confirmPasswordPlh: 'Confirm password',
				create: 'Aanmaken',
				creating: 'Creating...',
				rightTitle: 'Al een <em>NEM</em>ber?',
				rightButton: 'Open je wallet',
				openButton: 'Open',
				walletsFound: '<strong>{{1}}</strong> <em>wallets gevonden</em>',
				copyright: 'Fotografie door <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC beveiligt je wallet',
						description: '<em>Veiligheid</em> is heel belangrijk voor NEM om diefstal van XEM&amp; assets te voorkomen.'
					},
					{
						title: 'NCC beveiligt je wallet',
						description: '<em>Veiligheid</em> is heel belangrijk voor NEM om diefstal van XEM&amp; assets te voorkomen.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'Hoe werkt NCC?',
						paragraphs: [
							'<strong>NCC</strong> zorgt voor toegang tot je Assets en XEM zoals een traditionele Wallet, of portomonnee, dat doet.',
							'<strong>NCC</strong> heeft toegang to de <strong>NIS</strong> server nodig om te kunnen werken. Standaard is een lokale service actief. Deze wordt geinstalleerd met de community client (NCC)',
							'Je kan NCC ook configureren om toegang te krijgen tot een remote <strong>NIS</strong>.'
						],
						listItems: [
							'Meerdere rekeningen',
							'Definieer meerdere rekeningingen in een wallet'
						]
					},
					{
						title: 'Wat is &#42;NIS?',
						paragraphs: [
							'Dit component is verantwoordelijk om de <strong>NEM</strong> cloud in de lucht te houden.',
							'Hoe meer <strong>NIS</strong> servers er zijn, des te veiliger het netwerk is.',
							'<strong>NIS</strong> is het toegangspunt voor de <strong>NEM</strong> cloud.'
						],
						legend: '<strong>&#42;NIS</strong> staat voor <strong>NEM Infrastructure Server</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2015. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: 'Ongeveer {{1}} dagen geleden',
			lastAccessJustNow: 'Zojuist',
			lastAccessTooltip: 'Laatste toegang was {{1}}',
			primary: 'Primaire',
			primaryShort: 'P',
			noLabel: '<Geen label>',
			copiedToClipboard: 'Rekening is gekopieerd naar het clipboard!',
			actions: {
				refreshInfo: 'Ververs informatie',
				bootLocalNode: 'Start Lokale Node',
				changeWalletName: 'Verander naam  van Wallet',
				changeWalletPassword: 'Verander Wallet Wachtwoord',
				mergeWallets: 'Wallets samenvoegen',
				exportWallet: 'Exporteer Wallet',
				createAccount: 'Maak een nieuwe Rekening',
				createRealAccountData: 'Maak echte Rekening (wanneer NEM live is!)',
				verifyRealAccountData: 'Verify real account data',
				addAccount: 'Voor een Bestaande Rekening toe',
				changeAccountLabel: 'Verander het label van de Rekening',
				setPrimary: 'Stel in als Primaire Rekening',
				removeAccount: 'Verwijder rekening',
				clientInfo: 'Client Informatie',
				closeWallet: 'Sluit Wallet',
				closeProgram: 'Programma Afsluiten',
				copyClipboard: 'Kopieer rekeningnummer naar het clipboard.',
				convertMultisig: 'Convert to multisig'
			},
			nav: [
				'Dashboard',
				'Berichten',
				'Address Book',
				'Transacties',
				'Geharveste Blocks',
				'Asset Exchange',
				'Nieuws',
				'Applicaties',
				'Rekeningen',
				'Instellingen',
				'Afsluiten'
			],
			bootNodeWarning: "Een lokale node moet gestart zijn om alle functionaliteiten van NCC te gebruiken."
		},
		dashboard: {
			assets: {
				title: 'Je Assets'
			},
			importance: {
				title: 'Score',
				unknown: 'Onbekende status',
				start: 'Start harvesten',
				harvesting: 'Harvesten',
				stop: 'Stop harvesten',
				description: 'Status-score van deze rekening op  to the NEM cloud',
				remoteHarvest: {
					activate: 'Activeer remote harvesten',
					activating: 'Activeren van remote harvesten...',
					active: 'Remote harvesten is actief',
					deactivate: 'Deactiveer remote harvesten',
					deactivating: 'Deactiveren van remote harvesten...',
					startRemoteHarvesting: 'Start remote harvesten',
					remotelyHarvesting: 'Harvesten op afstand',
					stopRemoteHarvesting: 'Stop met remote harvesten'
				}
			},
			transactions: {
				title: 'Recente Transacties',
				sendNem: 'Verstuur XEM',
				signMultisig: 'SIGN',
				balance: 'Huidige balans',
				vestedBalance: 'Vested Balance',
				syncStatus: '(bij block {{1}}{{#2}} : ong. {{3}} dagen achter{{/2}})',
				unknown: 'onbekend',
				columns: [
					'',
					'Tijd',
					'Afzender/Ontvanger',
					'Bericht',
					'',
					'Details',
					'Confirmaties',
					'Toeslag',
					'Bedrag'
				],
				noMessage: 'Geen bericht',
				encrypted: 'Bericht is beveiligd',
				view: 'Bekijk',
				confirmationsUnknown: 'Onbekend',
				pending: 'Wachtend',
				seeAll: 'Bekijk alle transacties',
				noTransactions: 'Nog geen transacties zijn uitgevoerd'
			},
			nemValue: {
				title: 'XEM statistieken'
			},
			messages: {
				titleTooltip: 'Berichten'
			},
			news: {
				titleTooltip: 'Nieuws'
			},
			notAvailable: 'Nog niet beschikbaar in deze release'
		},
		transactions: {
			title: 'Transacties',
			sendNem: 'Verstuur XEM',
			balance: 'Huidige Balans',
			filters: {
				confirmed: 'Bevestigd',
				unconfirmed: 'Onbevestigd',
				incoming: 'Inkomend',
				outgoing: 'Uitgaand'
			},
			table: {
				columns: [
					'',
					'Tijd',
					'Afzender/Ontvanger',
					'Bericht',
					'',
					'Details',
					'Confirmaties',
					'Toeslag',
					'Bedrag'
				],
				noMessage: 'Geen bericht',
				encrypted: 'Bericht is versleuteld',
				view: 'Bekijk',
				confirmationsUnknown: 'Onbekend',
				pending: 'Wachtend',
				noTransactions: 'Nog geen transacties zijn uitgevoerd',
				loading: 'Meer transacties laden...'
			}
		},
		harvestedBlocks: {
			title: 'Geharveste Blocks',
			feeEarned: 'Toeslagen verdiend van de laatste 25 geharveste blocks',
			unknown: 'Unknown',
			table: {
				columns: [
					'Blockhoogte',
					'Tijd',
					'Block hash',
					'Toeslag'
				],
				noBlocks: 'Geen geharveste blocks ',
				loading: 'Bekijk oudere blocks die geharvest zijn'
			},
			harvesting: {
				unknown: 'Onbekende status',
				start: 'Start harvesten',
				harvesting: 'Harvesten',
				stop: 'Stop harvesten',
				remoteHarvest: {
					startRemoteHarvesting: 'Start remote harvesting',
					stopRemoteHarvesting: 'Stop remote harvesting'
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
			sendNem: 'Verstuur XEM',
			edit: 'Edit',
			remove: 'Verwijderen'
		},
		settings: {
			title: 'Instellingen',
			settings: [
				{
					name: 'Taal'
				}
			],
			save: 'Opslaan',
			saveSuccess: 'Instellingen zijn opgeslagen'
		}
	}
});
