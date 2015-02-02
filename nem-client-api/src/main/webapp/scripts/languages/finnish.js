define({
	id: "fi",
	name: "Suomi",
	texts: {
		preferences: {
			thousandSeparator: " ",
			decimalSeparator: "."
		},
		faults: {
			101: 'The wallet file does not exist.',
			102: "Lompakkoa ei ole luotu.",
			103: 'Wallet file is corrupt. Please recover your wallet from a backup.',
			104: 'The provided password for the wallet is not correct.',
			105: 'No password was provided for the wallet.',
			106: "Ennenkuin voit käyttää lompakkoa, se pitää avata. Avataksesi lompakon, on sinulla oltava salasana.",
			107: "Lompakko ei sisällä tätä tiliä.",
			108: "Tiliä ei voida poistaa.Tämä johtuu todenäköisesti siitä, että tilin saldo on enemmän, kuin 0 XEM, tai yrität poistaa ensisijaista tiliä.",
			109: "Saman niminen lompakko on jo olemassa. Valitse toinen lompakon nimi.",
			110: "Lompakko sisältää jo tämän tilin.",
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
			202: "Salattua viestiä ei voida lähettää, koska vastaanottaja ei ole tehnyt koskaan tilisiirtoa.",
			305: "NEM Infrastructure Server ei ole käytettävissä.",
			306: "Esiintyi ongelma, jota kehitystiimi ei ole tavannut aikaisemmin. Pahoittelemme tilannetta, yritä uudelleen. Muussa tapauksessa avaa uusi keskustelu NEM NIS/NCC foorumissa.",
			400: "Jotkut parametrit puuttuvat tai ovat viallisia.",
			401: "Tätä toimintoa ei voida saattaa loppuun, koska muutoin on mahdollista että \"private key\" näkyy ulkopuolisille, mikäli se lähetetään NIS-palveluun.\n",
			404: "Lähdettä ei löydy.",
			500: "Esiintyi ongelma, jota kehitystiimi ei ole tavannut aikaisemmin. Pahoittelemme tilannetta, yritä uudelleen. Muussa tapauksessa avaa uusi keskustelu NEM NIS/NCC foorumissa.",
			600: "NCC vaatii NIS palvelimen uudelleenkäynnistyksen, jotta siirtoja voidaan suorittaa NEM cloud palvelusta. Käytä NCC menua näynnistääksesi uudelleen Local node.",
			601: "NIS node on jo käynnistetty uudelleen, toinen yritys ei ole mahdollinen.",
			699: 'Maximum number of harvesters allowed on server has been reached.',
			700: "Louhintaa (Harvesting) ei voida aloittaa. Toiminto on mahdollista, kun tilisi saldo on vähintään 1000 XEM.",
			701: "Aikaraja on ylitetty. Aikaraja on oltava yhden päivän sisällä.",
			702: "Valittu aikaraja on liian kaukana tulevaisuudessa. Aikaraja täytyy olla yhden päivän sisällä.",
			703: "Tililläsi ei ole siirrettävää summaa.",
			704: "Teksti on liian pitkä lähetettäväksi siirron mukana. Yritä lyhentää viestiä.",
			705: "Hash-koodi on jo tietokannassa tai siirtoja on vahvistamatta.",
			706: "Siirron allekirjoitusta ei voida varmistaa.",
			707: "Siirron aikamerkintä on liian kaukana menneisyydessä.",
			708: "Siirron aikamerkintä on liian kaukana tulevaisuudessa.",
			709: "Tili on tuntematon. Tilillä on oltava yksi siirto tai vastaanotto, jotta se voidaan tunnistaa verkossa.",
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
			901: "Tapahtui virhe määritettäessä offline node.",
			1000: "Private key ja public key, eivät vastaa toisiaan.",
			1001: "Public key ja osoite eivät vastaa toisiaan.",
			1002: "Osoite ei kuulu varsinaiseen verkkoon."
		},
		common: {
			success: "Onnistui",
			appStatus: {
				nccUnknown: "NCC tila tuntematon.",
				nccUnavailable: "NCC ei ole käytettävissä.",
				nccStarting: "NCC on käynnistymässä...",
				nisUnknown: "NIS tila on tuntematon",
				nisUnavailable: "NIS ei ole käytetttävissä",
				nisStarting: "NIS on käynnistymässä...",
				notBooted: "NIS on käynnistettävä uudelleen. Avaa lompakkosi ja käynnistä local node uudelleen popup valikosta, jotta\nvoit muuttaa auto-boot asetuksia. ",
				booting: "NIS on käynnistymässä uudelleen...",
				nisInfoNotAvailable: "NIS info ei ole vielä käytettävissä. Yritetään hakea\nNIS infoa...",
				synchronizing: "NIS synkronointi on käynnissä. Block {{1}}, est. {{2}} takana.",
				daysBehind: {
					0: "Vähemmän, kuin yksi päivä.",
					1: "Yksi päivä",
					many: "{{1}} päivää"
				},
				synchronized: "NIS on sykronoitu !",
				noRemoteNisAvailable: 'No remote NIS found in the network, disconnected from internet?'
			},
			addressBook: 'Address book',
			password: "Salasana",
			passwordValidation: "Salasanakenttä ei voi olla tyhjä",
			address: "Osoite",
			privateLabel: "Yksityinen merkintä",
			publicLabel: 'Public label',
			noCharge: 'Current account will <b>NOT</b> be charged any fees, multisig account covers them',

		},
		transactionTypes: [
			'TRANSFER TRANSACTION',
			'IMPORTANCE TRANSFER',
			'MODIFICATION OF MULTISIG ACCOUNT',
			'MULTISIG TRANSACTION',
			
		],
		transactionDirections: {
			pending: "Viivästyneet siirrot",
			outgoing: "Lähtevät siirrot",
			incoming: "Saapuvat siirrot",
			self: "Automaattiset siirrot",
			importance: 'Importance transaction',
			modification: 'Aggregate Modification of Multisig'
		},
		modals: {
			error: {
				title: "Hups!",
				caption: "Virhe {{1}}"
			},
			confirmDefault: {
				yes: "Kyllä",
				no: "Ei"
			},
			settings: {
				title: "Asetukset",
				language: {
					label: "Kieli"
				},
				remoteServer: {
					tabTitle: "Remote server",
					protocol: "Protokolla",
					protocolOptions: {
						http: "HTTP"
					},
					host: "Host",
					port: "Port"
				},
				autoBoot: {
					tabTitle: "Auto-boot",
					name: "Node nimi",
					account: "Tili",
					primaryAccount: "Ensisijainen tili",
					auto: "Auto boot, kun lompakko on avattu"
				},
				save: "Tallenna",
				saveSuccess: "Asetukset on tallennettu onnistuneesti"
			},
			multisig: {
				title: 'Convert account to multisig',
				multisigAccount: 'Multisig account',
				cosignatories: "Cosignatories' addresses",
				labelDesc: "Tämä tili on nimetty {{1}}",
				nullLabelDesc: "Tällä tilillä ei ole nimeä",
				addCosignatory: '+ Add Cosignatory',
				cancel: "Peruuta",
				convert: 'Convert',
				fee: "Palkkio",
				feeValidation: "Palkkio ei voi olla vähempää, kuin minimipalkkio.",
				dueBy: "Maksettava",
				useMinimumFee: "Käytä minimipalkkiota",
				hours: "Tunnit",
				txConfirm: {
					title: 'Confirm Conversion to Multisig Account',
					total: "Kokonaissumma",

				},

			},
			signMultisig: {
				title: 'Sign multisig transaction',
				original: {
					from: 'Multisig account',
					to: "Vastaanottaja",
					amount: "Summa",
					fee: 'Inner Fee',
					deadline: 'Deadline'
				},
				multisigFees: 'Multisig Fees',
				multisigTotal: "Kokonaissumma",
				sender: 'Cosignatory',
				fee: "Palkkio",
				feeValidation: "Palkkio ei voi olla vähempää, kuin minimipalkkio.",
				dueBy: "Maksettava",
				useMinimumFee: "Käytä minimipalkkiota",
				hours: "Tunnit",
				password: "Salasana",
				passwordValidation: "Salasanakenttä ei voi olla tyhjä",
				send: "Lähetys",
				cancel: "Peruuta",
				sending: "Lähetetään...",
				successMessage: "Siirto on lähetetty onnistuneesti!",
				txConfirm: {
					title: 'Confirm Multisig Transaction',
					message: "Viesti",
					encrypted: "Viesti on salattu",
					noMessage: "Ei viestiä",

				},

			},
			sendNem: {
				title: "Lähetä XEM",
				sender: "Lähettäjä",
				thisAccount: 'This account',
				labelDesc: "Tämä tili on nimetty {{1}}",
				nullLabelDesc: "Tällä tilillä ei ole nimeä",
				amount: "Summa",
				recipient: "Vastaanottajan tili",
				recipientValidation: "Tilin osoite on oltava 40 merkkiä pitkä, ilman\npilkkuja",
				message: "Viesti",
				encrypt: "Salaa viesti",
				fee: "Palkkio",
				multisigFee: 'Multisig fee',
				feeValidation: "Palkkio ei voi olla vähempää, kuin minimipalkkio.",
				dueBy: "Maksettava",
				useMinimumFee: "Käytä minimipalkkiota",
				hours: "Tunnit",
				password: "Salasana",
				passwordValidation: "Salasanakenttä ei voi olla tyhjä",
				send: "Lähetys",
				cancel: "Peruuta",
				sending: "Lähetetään...",
				successMessage: "Siirto on lähetetty onnistuneesti!",
				txConfirm: {
					title: "Vahvista siirto",
					amount: "Summa",
					to: "Minne",
					dueBy: "Maksettava",
					hours: "Tunnit",
					total: "Kokonaissumma",
					message: "Viesti",
					encrypted: "Viesti on salattu",
					noMessage: "Ei viestiä",
					cancel: "Peruuta",
					confirm: "Vahvista",
					sending: "Lähetetään..."
				},
				notBootedWarning: {
					title: "Nodea ei ole käynnistetty uudelleen!",
					message: "Local node on käynnistettävä uudelleen, jotta NEM\nvoidaan lähettää!"
				},
				bootingWarning: {
					title: "Node on käynnistymässä uudelleen",
					message: "Odota, että uudelleenkäynnistys on valmis, ennenkuin teet siirtoja."
				}
			},
			clientInfo: {
				title: "Client info",
				ncc: "NEM Community Client - NCC",
				signer: "Allekirjoittaja",
				remoteServer: "Remote server",
				local: "Local",
				nis: "NEM Infrastructure Server - NIS",
				sync: "Synkronoitu",
				notSync: "Ei sykronoitu",
				notConnected: "Ei yhteydessä NEM Cloud palveluun.",
				loading: "Ladataan..."
			},
			transactionDetails: {
				title: "Siirron tiedot",
				id: "ID",
				hash: "Hash",
				type: "Siiron tyyppi",
				direction: 'Transaction Direction',
				pending: "Odotetaan",
				outgoing: "Lähtevä",
				incoming: "Saapuva",
				self: "Itse",
				sender: "Lähettäjä",
				multisigAccount: 'Multisig Account',
				issuer: 'Issuer',
				recipient: "Vastaanottaja",
				remote: 'Remote',
				multisigMessage: 'Signatures present',
				message: "Viesti",
				noMessage: "Ei viestiä",
				encrypted: "Viesti on salattu",
				time: "Aikamerkintä",
				confirmations: "Vahvistuksia",
				confirmationsUnknown: "Tuntemaon",
				amount: "Summa",
				fee: "Palkkio",
				innerFee: 'Inner Fee',
				multisigFees: 'Multisig Fees',
				issuer: 'Issuer',
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
				importance: "Importance",
				publicKey: "Public key",
				noPublicKey: "No public key",
				harvestedBlocks: "Harvested blocks",
				close: "Close"
			},
			bootLocalNode: {
				title: "Käynnistä local node uudelleen",
				account: "Tili, jolta local node käynnistetään uudelleen",
				noLabel: "<span class=\"null\">&lt;No label&gt;</span>",
				wallet: "Lompakko",
				node: "Node nimi",
				boot: "Käynnistä uudelleen",
				booting: "Käynnistymässä uudelleen..."
			},
			closeWallet: {
				title: "Sulje lompakko",
				message: "Oletko varma, että haluat sulkea lompakon ja palata\naloitussivulle?"
			},
			createAccount: {
				title: "Luo uusi lompakko",
				label: "Yksityinen merkintä",
				wallet: "Lompakko",
				password: "Lompakon salasana",
				successMessage: "Tili {{1}} {{#2}}({{2}}){{/2}} on luotu!",
				create: "Luo"
			},
			createRealAccountData: {
				title: "Luo real account data",
				message: "Alla olevat tiedot ovat oikean lompakkosi tiedot (real account data), kun NEM on julkaistu. Tallenna osoite, public key ja tärkeimpänä private key, turvalliseen paikkaan. Jos private key hukkuu hävität samalla kaikki NEM kolikkosi!",
				address: "Osoite",
				publicKey: "Public key",
				privateKey: "Private key",
				confirm: {
					title: "Tallenna private key",
					message: "Oletko varma, että private key on tallennettu turvalliseen paikkaan?"
				},
				recheck: {
					title: "Tarkista tallennettu private key uudellen",
					message: "Syötä juuri luomasi private key uudelleen tarkistaaksesi, että sinulla on oikea private key.\nJos sinulla ei ole sitä, niin haluat ehkä luoda uuden.",
					correct: {
						title: "Hienoa!",
						message: "Näyttäisi siltä, että oikea private key on tallennettu. Mista säilyttää se aina varmassa ja turvallisessa paikassa."
					},
					incorrect: {
						title: "Hmm...",
						message: "Syöttämäsi private key ei ole oikea! Haluatko syöttää sen uudelleen, vai palata tilisi alkuperäisiin tilitietoihin?",
						tryAgain: "Yritä syöttää uudelleen",
						seeOriginal: "Katso alkuperäiset tiedot"
					},
					recheck: "Tarkista"
				},
				ok: "OK"
			},
			verifyRealAccountData: {
				title: "Varmista real account data",
				message: "Syötä tallennettu osoitteesi, public key ja private key\nuudelleen, jotta voita tarkistaa niiden yhteensopivuuden.",
				address: "Osoite",
				publicKey: "Public key",
				privateKey: "Private key",
				dataMatched: "Kaikki näyttää hyvältä, syöttämäsi osoite, public key ja private key sopivat yhteen.",
				verify: "Varmista"
			},
			addAccount: {
				title: "Lisää olemassaoleva tili",
				privateKey: "Tilin Private Key",
				wallet: "Lompakko",
				password: "Lompakon salasana",
				successMessage: "Tili {{1}} {{#2}}({{2}}){{/2}} on lisätty lompakkoon!",
				add: "Lisää",
				label: "Merkki"
			},
			setPrimary: {
				title: "Aseta ensisijainen tili",
				account: "Tili on asetettu ensisijaiseksi",
				noLabel: "<span class=\"null\">&lt;No label&gt;</span>",
				wallet: "Lompakko",
				password: "Lompakon salasana",
				successMessage: "Tili {{1}} {{#2}}({{2}}){{/2}} on asetettu ensisijaiseksi!",
				set: "Aseta ensisijaiseksi",

			},
			changeWalletName: {
				title: "Vaihda lompakon nimi",
				wallet: "Nykyisen lompakon nimi",
				newName: "Uusi lompakon nimi",
				password: "Lompakon salasana",
				successMessage: "Lompakon nimi on vaihdetttu onnistuneesti<em>{{1}}</em> - <em>{{2}}</em>",
				change: "Vaihda"
			},
			changeWalletPassword: {
				title: "Vaihda lompakon salasana",
				wallet: "Lompakko",
				password: "Nykyinen salasana",
				newPassword: "Uusi salasana",
				confirmPassword: "Vahvista uusi salasana",
				successMessage: "Lompakon salasana on vaihdettu onnistuneesti",
				change: "Vaihda",
				passwordNotMatchTitle: "Hups!",
				passwordNotMatchMessage: "Syöttämäsi salasana ja salasanan vahvistus eivät vastaa toisiaan. Varmista, että syötät uuden salasanasi oikein."
			},
			changeAccountLabel: {
				title: "Vaihda tilin nimi",
				label: "Tilin nimi",
				wallet: "Lompakko",
				password: "Lompakon salasana",
				successMessage: "Tili {{1}} on nyt merkitty {{2}}",
				change: "Vaihda"
			},
			removeAccount: {
				title: "Poista tili",
				wallet: "Lompakko",
				password: "Lompakon salasana",
				warning: "Varmista, että tilisi on tyhjä ennen kuin positat sen, tai kaikki NEMit poistuvat tililtäsi myös.\n\n",
				successMessage: "Tili {{1}} {{#2}}({{2}}){{/2}} on poistettu",
				remove: "Poistettu"
			},
			nisUnavailable: {
				title: "NIS ei käytettävissä",
				message: "NIS yhteys katkaistu, odotetaan yhdistämistä"
			},
			shutdown: {
				title: "Sulje ohjelma",
				message: "Haluatko sulkea NEM Community Client sovelluksen?"
			},
			activateRemote: {
				title: "Aktivoi Remote harvesting",
				wallet: "Lompakko",
				account: "Tili",
				hoursDue: "Maksettavaksi (tunnit)",
				password: "Lompakon salasana",
				activate: "Aktivoi"
			},
			deactivateRemote: {
				title: "Lopeta Remote harvesting",
				wallet: "Lompakko",
				account: "Tili",
				hoursDue: "Maksettavaksi (tunnit)",
				password: "Lompakon salasana",
				deactivate: "Katkaise yhteys"
			},
			startRemote: {
				title: "Aloita Remote harvesting",
				wallet: "Lompakko",
				account: "Tili",
				password: "Lompakon salasana",
				start: "Käynnistä"
			},
			stopRemote: {
				title: "Lopeta Remote harvesting",
				wallet: "Lompakko",
				account: "Tili",
				password: "Lompakon salasana",
				stop: "Pysäytä"
			},
			logoutWarning: {
				leavePage: "Olet poistumassa lompakostasi. Jos poistut näin, on toisilla tämän tietokoneen käyttäjillä mahdollisuus käyttää lompakkoasi. Poistu lompakosta \"close wallet\"\ntoiminnolla menu pudotusvalikosta, joka sijaitsee ruudun oikeassa yläkulmassa, ennen kuin suljet selaimen.",

			},
			addContact: {
				title: 'Add contact',
				add: "Lisää"
			},
			editContact: {
				title: 'Edit contact',
				saveChanges: "Tallenna muutokset",

			},
			removeContact: {
				title: 'Remove contact',
				remove: "Poistettu",

			}
		},
		landing: {
			logo: "images/nem_logo.png",
			importSuccess: "Lompakko on tuotu onnistuneesti!",
			nav: {
				start: "Aloitussivu",
				about: "NEM tietoja",
				settings: "Asetukset"
			},
			main: {
				leftTitle: "Uusi <em>NEM käyttäjä</em>?",
				leftButton: "Luo uusi lompakko",
				walletNamePlh: "Lompakkosi nimi",
				passwordPlh: "Salasana",
				confirmPasswordPlh: "Vahvista salasana",
				create: "Luo",
				creating: "Luodaan...",
				rightTitle: "Oletko jo <em>NEM</em>tilin käyttäjä?",
				rightButton: "Avaa lompakkosi",
				openButton: "Avaaa",
				walletsFound: "Löydetty <strong>{{1}}</strong> <em>Lompakko(s)</em>",
				copyright: "Valokuvaaja <em>Cas Cornelissen</em>"
			},
			carousel: {
				items: [
					{
						title: "NCC salaa lompakkosi",
						description: "<em>Turvallisuus</em> on erittäin tärkeää, että kolikkoja ja assetteja ei varasteta."
					},
					{
						title: "NCC salaa lompakkosi",
						description: "<em>Turvallisuus</em> on erittäin tärkeää, että kolikkoja ja assetteja ei varasteta."
					}
				]
			},
			about: {
				sections: [
					{
						title: "Miten NCC toimii?",
						paragraphs: [
							"<strong>NCC</strong> luo yhteyden assetteihisi ja NEM tilillesi, kuin perinteinen lompakko. Voit:",
							"<strong>NCC</strong> vaatii yhteyden <strong>NIS</strong> palvelimelle, ollakseen toimintavalmis. Normaalisti paikallinen palvelin on aktiivinen, se on asennettu <strong>NCC</strong>)palvelun kanssa.",
							"Voit myös konfiguroida pääsyn etäpalvelimelle <strong>NIS</strong>."
						],
						listItems: [
							"Useita lompakoita",
							"Määritä useita tilejä, jotka voivat olla lompakossasi"
						]
					},
					{
						title: "Mikä on &#42;NIS?",
						paragraphs: [
							"Tämä komponentti pitää cloud-palvelun <strong>NEM</strong> käynnissä.",
							"Suurempi <strong>NIS</strong> turvallisuus on parempi.",
							"<strong>NIS</strong> on yhdyspiste <strong>NEM</strong> cloudiin."
						],
						legend: "<strong>&#42;NIS</strong> tarkoittaa <strong>NEM Infrastructure Server</strong>"
					}
				]
			},
			footer: {
				copyright: "&copy; Copyright 2015. NEM Community Client."
			}
		},
		wallet: {
			logo: "images/nem_logo.png",
			lastAccess: "Noin {{1}} päivää sitten",
			lastAccessJustNow: "Juuri nyt",
			lastAccessTooltip: "Viimeinen kirjautuminen oli {{1}}",
			primary: "Ensisijainen",
			primaryShort: "P",
			noLabel: "<No label>",
			copiedToClipboard: "Osoite on kopioitu työpöydälle",
			actions: {
				refreshInfo: "Päivitä Info",
				bootLocalNode: "Käynnistä Local Node uudelleen",
				changeWalletName: "Vaihda lompakon nimi",
				changeWalletPassword: "Vaihda lompakon salasana",
				mergeWallets: "Yhdistä lompakot",
				exportWallet: "Vie lompakko",
				createAccount: "Luo uusi lompakko",
				createRealAccountData: "Luo real account data",
				verifyRealAccountData: "Tarkista real account data",
				addAccount: "Lisää olemassaoleva tili",
				changeAccountLabel: "Vahda tilin nimi",
				setPrimary: "Aseta ensisijainen tili",
				removeAccount: "Poista tili",
				clientInfo: "Client Info",
				closeWallet: "Sulje lomakko",
				closeProgram: "Sulje ohjelma",
				copyClipboard: "Kopioi osoite työpöydälle",
				convertMultisig: 'Convert to multisig'
			},
			nav: [
				"Valikko",
				"Viesti",
				'Address Book',
				"Siirrot",
				"Harvested blocks",
				"Asset Exchange",
				"Uutiset",
				"Sovellukset",
				"Tilit",
				"Asetukset",
				"Sulje ohjelma"
			],
			bootNodeWarning: "Local node täytyy käynnistää uudelleen, että saat kaikki\nominaisuudet käyttöön kohteessa NCC."
		},
		dashboard: {
			assets: {
				title: "Assettisi"
			},
			importance: {
				title: "Merkittävä määrä",
				unknown: "Tuntematon tila",
				start: "Aloita local harvesting",
				harvesting: "Harvesting",
				stop: "Lopeta local harvesting",
				description: "Tilin merkitys NEM cloud palvelussa",
				remoteHarvest: {
					activate: "Aktovoi remote harvesting",
					activating: "Aktivoidaan remote harvesting...",
					active: "Remote harvesting on aktiivinen",
					deactivate: "Lopeta remote harvesting",
					deactivating: "Lopetetaan remote harvesting...",
					startRemoteHarvesting: "Käynnistä remote harvesting",
					remotelyHarvesting: "Remotely harvesting",
					stopRemoteHarvesting: "Pysäytä remote harvesting"
				}
			},
			transactions: {
				title: "Viimeisimmät siirrot",
				sendNem: "Lähetä XEM",
				signMultisig: 'SIGN',
				balance: "Tämänhetkinen saldo",
				syncStatus: "(at block {{1}}{{#2}} : est. {{3}} days behind{{/2}})",
				unknown: "Tuntematon",
				columns: [
					"",
					"Aika",
					"Lähettäjä/Vastaanottaja",
					"Viesti",
					"",
					"Tiedot",
					"Vahvistukset",
					"Palkkio",
					"Summa"
				],
				noMessage: "Ei viestiä",
				encrypted: "Viesti on salattu",
				view: "Näytä",
				confirmationsUnknown: "Tuntematon",
				pending: "Viivästynyt",
				seeAll: "Näytä kaikki siirrot",
				noTransactions: "Siirtoja ei ole vielä tehty"
			},
			nemValue: {
				title: "XEM arvon staistiiikka"
			},
			messages: {
				titleTooltip: "Viesti"
			},
			news: {
				titleTooltip: "Uutiset"
			},
			notAvailable: "Ei ole saatavilla beta versiossa"
		},
		transactions: {
			title: "Siirrot",
			sendNem: "Lähetä XEM",
			balance: "Tämänhetkinen saldo",
			filters: {
				confirmed: "Vahvistettu",
				unconfirmed: "Vahvistamaton",
				incoming: "Saapuva",
				outgoing: "Lähtevä",

			},
			table: {
				columns: [
					"",
					"Aika",
					"Lähettäjä/Vastaanottaja",
					"Viesti",
					"",
					"Tiedot",
					"Vahvistuksia",
					"Palkkio",
					"Summa"
				],
				noMessage: "Ei viestiä",
				encrypted: "Viesti on salattu",
				view: "Näytä",
				confirmationsUnknown: "Tuntematon",
				pending: "Viivästynyt",
				noTransactions: "Siirtoja ei ole vielä tehty",
				loading: "Lataa lisää siirtoja..."
			}
		},
		harvestedBlocks: {
			title: "Harvested blocks",
			feeEarned: "Ansaittuja palkkioita 25 viimeisestä louhitusta lohkosta\n(harvested blocks)",
			unknown: "Tuntematon",
			table: {
				columns: [
					"Korkeus",
					"Aika",
					"Block hash",
					"Palkkio"
				],
				noBlocks: "Yhtään Blockia ei ole vielä louhittu",
				loading: "Ladataan lisää harvested blocks"
			},
			harvesting: {
				unknown: "Tuntematon tila",
				start: "Aloita local harvesting",
				harvesting: "Harvesting",
				stop: "Lopeta local harvesting",
				remoteHarvest: {
					startRemoteHarvesting: "Käynnistä remote harvesting",
					stopRemoteHarvesting: "Lopeta remote harvesting"
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
			sendNem: "Lähetä XEM",
			edit: 'Edit',
			remove: "Poistettu"
		},
		settings: {
			title: "Asetukset",
			settings: [
				{
					name: "Kieli"
				}
			],
			save: "Tallenna muutokset",
			saveSuccess: "Asetukset on tallennettu onnistuneesti"
		}
	}
});
