define({
	id: 'fi',
	name: 'Suomi',
	texts: {
		preferences: {
			thousandSeparator: ' ',
			decimalSeparator: '.'
		},
		faults: {
			101: 'Lompakkotiedostoa ei löydy.',
			102: 'Lompakkoa ei ole luotu.',
			103: 'Lompakkotiedosto on vaurioitunut.Palauta lompakkotiedosto varmuuskopiosta.',
			104: 'Syöttämäsi salasana ei ole oikea. ',
			105: 'Et syöttänyt salasanaa.',
			106: 'Jos haluat käyttää lompakkoa, se pitää avata. Avataksesi lompakon, on sinulla oltava salasana.',
			107: 'Lompakko ei sisällä tätä tiliä.',
			108: 'Tiliä ei voida poistaa.Tämä johtuu todenäköisesti siitä, että tilin saldo on enemmän, kuin 0 XEM, tai yrität poistaa ensisijaista tiliä.',
			109: 'Saman niminen lompakko on jo olemassa. Valitse toinen lompakon nimi.',
			110: 'Lompakko sisältää jo tämän tilin.',
			111: 'Lompakon nimi on hakemisto.',
			112: 'Lompakkotiedoston laajennus on väärä.',
			113: 'Lompakkoa ei voitu poistaa.',
			121: 'Osoitekijatiedostoa ei ole.',
			122: 'Osoitekijaa ei ole luotu.',
			123: 'Osoitekirjatiedosto on viallinen, palauta se varmuuskopiosta.',
			124: 'Syöttämäsi osoitekirjan salasana on väärä.',
			125: 'Et syöttänyt osoitekirjan salasanaa.',
			127: 'Osoitekirja ei sisällä tätä osoitetta.',
			128: 'Osoite ei ole oikea.',
			129: 'Samalla nimellä oleva osoitekija on jo olemassa. Valitse toinen osoitekirjan nimi.',
			130: 'Osoitekirja sisältää jo antamasi osoitteen.',
			131: 'Osoitekirjan nimi on hakemisto.',
			132: 'Osoitekirjan laajennustiedosto on väärä.',
			133: 'Osoitekirjaa ei voida poistaa.',
			202: 'Salattua viestiä ei voida lähettää, koska vastaanottaja ei ole tehnyt koskaan tilisiirtoa.',
			203: 'The account cannot be converted because not all cosignatories are known. They either need to be in the same wallet or have made at least one transaction.',
			305: 'NIS ei ole käytettävissä. Yritä käynnistää NEM sovellus uudelleen. Mikäli yrität käyttää NIS palvelua etänä, tarkista kirjoitusvirheet (host), tai käytä toista etä NIS palvelua.',
			306: 'Esiintyi ongelma, jota kehitystiimi ei ole tavannut aikaisemmin. Pahoittelemme tilannetta, yritä uudelleen. Muussa tapauksessa avaa uusi keskustelu NEM NIS/NCC foorumissa.',
			400: 'Jotkut parametrit puuttuvat tai ovat viallisia.',
			401: 'Tätä toimintoa ei voida saattaa loppuun, koska muutoin on mahdollista että \'private key\' näkyy ulkopuolisille, mikäli se lähetetään etä NIS palveluun.',
			404: 'Lähdettä ei löydy.',
			500: 'Esiintyi ongelma, jota kehitystiimi ei ole tavannut aikaisemmin. Pahoittelemme tilannetta, yritä uudelleen. Muussa tapauksessa avaa uusi keskustelu NEM NIS/NCC foorumissa.',
			600: 'NCC vaatii NIS palvelimen uudelleenkäynnistyksen, jotta siirtoja voidaan suorittaa NEM cloud palvelusta. Käytä NCC menua näynnistääksesi uudelleen Local node.',
			601: 'NIS on jo käynnistetty uudelleen.',
			602: 'Melkein valmista. NIS on lataamassa lohkoja. Lompakko on käytettävissä, kun tietokanta on täysin ladattu.',
			699: 'Louhijoiden maksimimäärä palvelimella on saavutettu.',
			700: 'Louhintaa ei voida aloittaa. Toiminto on mahdollista, kun tilisi saldo on vähintään 1000 XEM.',
			701: 'Aikaraja on ylitetty. Aikaraja on oltava yhden päivän sisällä.',
			702: 'Valittu aikaraja on liian kaukana tulevaisuudessa. Aikaraja täytyy olla yhden päivän sisällä.',
			703: 'Your account does not have the right balance to make this transaction.',
			704: 'Teksti on liian pitkä lähetettäväksi siirron mukana. Yritä lyhentää viestiä.',
			705: 'Hash on jo tietokannassa tai siirtoja on vahvistamatta.',
			706: 'Siirron allekirjoitusta ei voida varmistaa.',
			707: 'Siirron aikamerkintä on liian kaukana menneisyydessä.',
			708: 'Siirron aikamerkintä on liian kaukana tulevaisuudessa.',
			709: 'Tili on tuntematon. Tilillä on oltava yksi lähetys tai vastaanotto, jotta se voidaan tunnistaa verkossa.',
			710: 'Siirtoa ei hyväksytty, koska siirtojen välimuisti on täynnä. Korkeampi palkkio parantaaa siirron hyväksymistä.',
			730: 'Importance transfer transaction (delegated harvesting) conflicts with existing transaction.',
			731: 'Delegated harvesting account has non zero balance and cannot be used.',
			732: 'Siirtoa ei hyväksytty, viivästynyt siirto on vielä käynnissä.',
			733: 'Delegated harvesting is already active.',
			734: 'Delegated harvesting is NOT active. Cannot deactivate.',
			740: 'Siirtoa ei ole mahdollinen multisig tilille.',
			741: 'Multisig allekirjoitettu siirto hylättiin. Nykyinen tili ei ole allekirjoittaja multisig tilillä.',
			742: 'Multisig allekirjoitettu siirto hylättiin. Allekirjoittajakumppania ei tunneta NEM-verkossa.',
			743: 'Multisig-tilin muutos hylätty.Yksi lisätty tili on jo allekirjoittaja.',
			901: 'Tapahtui virhe määritettäessä offline node.',
			1000: 'Private key ja public key, eivät vastaa toisiaan.',
			1001: 'Public key ja osoite eivät vastaa toisiaan.',
			1002: 'Osoite ei kuulu varsinaiseen verkkoon.'
		},
		common: {
			success: 'Onnistui',
			unknown: 'Tuntematon tila',
			unknownMessage: 'Ncc did not get response in a timely manner. Transaction might have been sent to the network.<br /><br />Please, check transactions before attempting to make it again.',
			appStatus: {
				nccUnknown: 'NCC tila tuntematon.',
				nccUnavailable: 'NCC ei ole käytettävissä.',
				nccStarting: 'NCC on käynnistymässä...',
				nisUnknown: 'NIS tila on tuntematon',
				nisUnavailable: 'NIS ei ole käytettävissä',
				nisStarting: 'NIS on käynnistymässä...',
				notBooted: 'NIS on käynnistettävä uudelleen. Avaa lompakkosi ja käynnistä local node uudelleen popup valikosta, jotta voit muuttaa auto-boot asetuksia. ',
				loading: 'Ladataan lohkoja tietokannasta, lohkossa:',
				booting: 'NIS on käynnistymässä uudelleen...',
				nisInfoNotAvailable: 'NIS info ei ole vielä käytettävissä. Yritetään hakea NIS infoa...',
				synchronizing: 'NIS synkronointi on käynnissä. Lohko {{1}}, {{2}} takana.',
				daysBehind: {
					0: 'Vähemmän, kuin yksi päivä.',
					1: 'Yksi päivä',
					many: '{{1}} päivää'
				},
				synchronized: 'NIS on sykronoitu !',
				noRemoteNisAvailable: 'NIS ei löydy verkosta, tarkista internet-yhteys?'
			},
			addressBook: 'Osoitekirja',
			password: 'Salasana',
			passwordValidation: 'Salasana ei voi olla tyhjä',
			address: 'Osoite',
			privateLabel: 'Yksityinen merkintä',
			publicLabel: 'Julkinen merkintä',
			noCharge: 'Olemassa olevaa tiliä <b>ei</b> veliteta, se katetaan multisig-tililtä.',
			fee: 'Palkkio',
			justUse: 'Käytä',
			dueBy: 'Maksettava',
			hours: 'Tunnit',
			hoursDue: 'Maksettavaksi (tunnit)',
			hoursDueExplanation: 'If the transaction isn\'t included by the deadline, it is rejected.',
			closeButton: 'Close'
		},
		transactionTypes: [
			'SIIRTO LÄHETYS',
			'IMPORTANCE LÄHETYS',
			'MULTISIG TILIN MUOKKAUS',
			'MULTISIG SIIRTO'
		],
		transactionDirections: {
			pending: 'Viivästynyt siirto ',
			outgoing: 'Lähtevä siirto',
			incoming: 'Tuleva siirto',
			self: 'Siirto itselle',
			importance: 'Importance siirto',
			modification: 'Multisig tilin muokkaus'
		},
		modals: {
			error: {
				title: 'Hups!',
				caption: 'Virhe {{1}}'
			},
			yikes: {
				title: 'Ou nou!',
				caption: 'info koodi {{1}}'
			},
			confirmDefault: {
				yes: 'Kyllä',
				no: 'Ei'
			},
			settings: {
				title: 'Asetukset',
				language: {
					label: 'Kieli'
				},
				remoteServer: {
					tabTitle: 'Etäpalvelin',
					protocol: 'Protokolla',
					protocolOptions: {
						http: 'HTTP'
					},
					host: 'Host',
					port: 'Port',
					defaultPort: 'Käytä oletusporttia.'
				},
				autoBoot: {
					tabTitle: 'Auto-boot',
					name: 'Node nimi',
					account: 'Tili',
					primaryAccount: 'Ensisijainen tili',
					auto: 'Auto boot, kun lompakko on avattu'
				},
				save: 'Tallenna',
				saveSuccess: 'Asetukset on tallennettu onnistuneesti'
			},
			multisig: {
				title: 'Muunna tili multisig tiliksi',
				multisigAccount: 'Multisig tili',
				cosignatories: 'Allekijoittajien osoitteet',
				labelDesc: 'Tämä tili on nimetty {{1}}',
				nullLabelDesc: 'Tällä tilillä ei ole nimeä',
				addCosignatory: '+ Lisää allekirjoittaja',
				cancel: 'Peruuta',
				convert: 'Muunna',
				fee: 'Palkkio',
				feeValidation: 'Palkkio ei voi olla vähempää, kuin minimiveloitus',
				useMinimumFee: 'Käytä minimipalkkiota',
				txConfirm: {
					title: 'Vahvista multiig tilin muunto',
					total: 'Yhteensä',

				},
				warning: 'Multisig-tili on allekirjoittajien listalla. Tämä aiheuttaa tilin lukittumisen ja estää pääsyn varoihin.Todennäköisesti sinä <b>ET</b> halua tehdä sitä!'
			},
			signMultisig: {
				title: 'Allekirjoita multisig siirto',
				original: {
					from: 'Multisig tili',
					to: 'Vastaanottaja',
					amount: 'Summa',
					fee: 'Sisäinen veloitus',
					deadline: 'Määräaika'
				},
				multisigFees: 'Multisig palkkiot',
				multisigTotal: 'Yhteensä',
				sender: 'Allekirjoittaja',
				fee: 'Palkkio',
				feeValidation: 'Palkkio ei voi olla vähempää, kuin minimipalkkio',
				useMinimumFee: 'Käytä minimipalkkiota',
				password: 'Salasana',
				passwordValidation: 'Salasanakenttä ei voi olla tyhjä',
				send: 'Lähetä',
				cancel: 'Peruuta',
				sending: 'Lähetetään...',
				successMessage: 'Siirto on suoritettu onnistuneesti!',
				txConfirm: {
					title: 'Vahvista multisig siirto',
					message: 'Viesti',
					encrypted: 'Viesti on salattu',
					noMessage: 'Ei viestiä',

				}
			},
			sendNem: {
				title: 'Lähetä XEM',
				sender: 'Lähettäjä',
				thisAccount: 'Tämä tili',
				labelDesc: 'Tämä tili on nimetty {{1}}',
				nullLabelDesc: 'Tällä tilillä ei ole nimeä',
				amount: 'Summa',
				recipient: 'Vastaanottajan tili',
				recipientValidation: 'Tilin osoite on oltava 40 merkkiä pitkä, ilman viivoja',
				message: 'Viesti',
				encrypt: 'Salaa viesti',
				fee: 'Palkkio',
				multisigFee: 'Multisig palkkio',
				feeValidation: 'Palkkio ei voi olla vähempää, kuin minimipalkkio.',
				useMinimumFee: 'Käytä minimipalkkiota',
				password: 'Salasana',
				passwordValidation: 'Salasanakenttä ei voi olla tyhjä',
				send: 'Lähetä',
				cancel: 'Peruuta',
				sending: 'Lähetetään...',
				successMessage: 'Siirto on lähetetty onnistuneesti!',
				txConfirm: {
					title: 'Vahvista siirto',
					amount: 'Summa',
					to: 'Minne',
					total: 'Kokonaissumma',
					message: 'Viesti',
					encrypted: 'Viesti on salattu',
					noMessage: 'Ei viestiä',
					cancel: 'Peruuta',
					confirm: 'Vahvista',
					sending: 'Lähetetään...'
				},
				notBootedWarning: {
					title: 'Nodea ei ole käynnistetty uudelleen!',
					message: 'Local node on käynnistettävä uudelleen, jotta XEM voidaan lähettää!'
				},
				bootingWarning: {
					title: 'Node on käynnistymässä uudelleen',
					message: 'Odota, että uudelleenkäynnistys on valmis, ennen kuin teet siirtoja.'
				},
				loadingWarning: {
					title: 'Ladataan tietokantaa'
				}
			},
			clientInfo: {
				title: 'Client info',
				ncc: 'NEM Community Client - NCC',
				signer: 'Allekirjoittaja',
				remoteServer: 'Etäpalvelin',
				local: 'Paikallinen',
				nis: 'NEM Infrastructure Server - NIS',
				sync: 'Synkronoitu',
				notSync: 'Ei sykronoitu',
				notConnected: 'Ei yhteydessä NEM Cloud palveluun',
				loading: 'Ladataan...'
			},
			transactionDetails: {
				title: 'Siirron tiedot',
				id: 'ID',
				hash: 'Hash',
				type: 'Siiron tyyppi',
				direction: 'Siirron suunta',
				pending: 'Viivästynyt',
				outgoing: 'Lähtevä',
				incoming: 'Saapuva',
				self: 'Itse',
				sender: 'Lähettäjä',
				multisigAccount: 'Multisig tili',
				issuer: 'Myöntäjä',
				recipient: 'Vastaanottaja',
				remote: 'Etä',
				multisigMessage: 'Olemassaolevat allekirjoitukset',
				message: 'Viesti',
				noMessage: 'Ei viestiä',
				encrypted: 'Viesti on salattu',
				time: 'Aikamerkintä',
				confirmations: 'Vahvistuksia',
				confirmationsUnknown: 'Tuntemaon',
				amount: 'Summa',
				fee: 'Palkkio',
				innerFee: 'Sisäinen palkkio',
				multisigFees: 'Multisig palkkio',
				cosignatory: 'Allekirjoittaja'
			},
			accountDetails: {
				title: 'Tilin tiedot',
				address: 'Osoite',
				label: 'Merkki',
				noLabel: 'Ei merkkiä',
				add: 'Lisää osoitekirjaan',
				remove: 'Poista osoitekirjasta',
				balance: 'Saldo',
				vested: 'Louhittu',
				importance: 'Merkitys',
				publicKey: 'Julkinen avain',
				noPublicKey: 'Ei julkista avainta',
				harvestedBlocks: 'Louhitut lohkot',
				close: 'Sulje'
			},
			bootLocalNode: {
				title: 'Käynnistä local node uudelleen',
				account: 'Tili, jolta local node käynnistetään uudelleen',
				noLabel: '<span class=\'null\'>&lt;Ei nimeä&gt;</span>',
				wallet: 'Lompakko',
				node: 'Noden nimi',
				boot: 'Käynnistä uudelleen',
				booting: 'Käynnistymässä uudelleen...',
				warning: 'Boot node varoitus',
				warningText: 'You\'re trying to boot a node <u>{{2}}</u><br/><br/>Booting remote node is currently impossible from within NCC.',
				warningStatement: 'You have auto-boot set to true and you\'re using remote node {{3}}.<br/><br/>Booting remote node is currently impossible from within NCC',
				warningQuestion: 'Oletko varma, että haluat käynnistää uudelleen noden <u>{{3}}</u> käyttämällä private keytä tlille {{1}} ({{2}} XEM)?<br><br>tämä paljastaa<span class=\"varoitus\">private keyn</span>  nodelle: <u>{{3}}</u>.'
			},
			closeWallet: {
				title: 'Sulje lompakko',
				message: 'Oletko varma, että haluat sulkea lompakon ja palata aloitussivulle?'
			},
			createAccount: {
				title: 'Luo uusi lompakko',
				label: 'Yksityinen merkintä',
				wallet: 'Lompakko',
				password: 'Lompakon salasana',
				successMessage: 'Tili {{1}} {{#2}}({{2}}){{/2}} on luotu!',
				create: 'Luo'
			},
			showPrivateKey: {
				title: 'Show Account\'s PRIVATE Key',
				message: 'This will display account\'s private key on the screen, as a text. In case of any malware present in the system, this might be hazardous operation. Are you sure you want to do that?',
				publicKey: 'Public key',
				privateKey: 'Private key',
				show: 'Show the key'
			},
			showRemotePrivateKey: {
				title: 'Show Remote Account\'s PRIVATE Key',
				message: 'This will display remote account\'s private key on the screen, as a text. In case of any malware present in the system, this might be hazardous operation. Are you sure you want to do that?',

			},
			addAccount: {
				title: 'Lisää olemassaoleva tili',
				privateKey: 'Tilin Private Key',
				wallet: 'Lompakko',
				password: 'Lompakon salasana',
				successMessage: 'Tili {{1}} {{#2}}({{2}}){{/2}} on lisätty lompakkoon!',
				add: 'Lisää',
				label: 'Merkki'
			},
			setPrimary: {
				title: 'Aseta ensisijainen tili',
				account: 'Tili on asetettu ensisijaiseksi',
				noLabel: '<span class=\'null\'>&lt;Ei merkkiä&gt;</span>',
				wallet: 'Lompakko',
				password: 'Lompakon salasana',
				successMessage: 'Tili {{1}} {{#2}}({{2}}){{/2}} on asetettu ensisijaiseksi!',
				set: 'Aseta ensisijaiseksi'
			},
			changeWalletName: {
				title: 'Vaihda lompakon nimi',
				wallet: 'Nykyisen lompakon nimi',
				newName: 'Uusi lompakon nimi',
				password: 'Lompakon salasana',
				successMessage: 'Lompakon nimi on vaihdetttu onnistuneesti<em>{{1}}</em> - <em>{{2}}</em>',
				change: 'Vaihda'
			},
			changeWalletPassword: {
				title: 'Vaihda lompakon salasana',
				wallet: 'Lompakko',
				password: 'Nykyinen salasana',
				newPassword: 'Uusi salasana',
				confirmPassword: 'Vahvista uusi salasana',
				successMessage: 'Lompakon salasana on vaihdettu onnistuneesti',
				change: 'Vaihda',
				passwordNotMatchTitle: 'Hups!',
				passwordNotMatchMessage: 'Syöttämäsi salasana ja salasanan vahvistus eivät vastaa toisiaan. Varmista, että syötät uuden salasanasi oikein.'
			},
			changeAccountLabel: {
				title: 'Vaihda tilin nimi',
				label: 'Tilin nimi',
				wallet: 'Lompakko',
				password: 'Lompakon salasana',
				successMessage: 'Tili {{1}} on nimetty {{2}}',
				change: 'Vaihda'
			},
			removeAccount: {
				title: 'Poista tili',
				account: 'Tili',
				label: 'Tilin nimi',
				wallet: 'Lompakko',
				password: 'Lompakon salasana',
				warning: 'Varmista, että tilisi on tyhjä ennen kuin poistat sen, tai kaikki XEMit poistuvat tililtäsi myös.',
				successMessage: 'Tili {{1}} {{#2}}({{2}}){{/2}} on poistettu',
				remove: 'Poista'
			},
			nisUnavailable: {
				title: 'NIS ei käytettävissä',
				message: 'NIS yhteys katkaistu, odotetaan yhdistämistä'
			},
			shutdown: {
				title: 'Sulje ohjelma',
				message: 'Haluatko sulkea NEM Community Client sovelluksen?'
			},
			activateRemote: {
				title: 'Activate Delegated Harvesting',
				wallet: 'Lompakko',
				account: 'Tili',
				password: 'Lompakon salasana',
				activate: 'Aktivoi',
				warning: 'Warning',
				warningText: 'Activation will take 6 hours (360 blocks). Activation will NOT start harvesting automatically.'
			},
			deactivateRemote: {
				title: 'Deactivate Delegated Harvesting',
				wallet: 'Lompakko',
				account: 'Tili',
				password: 'Lompakon salasana',
				deactivate: 'Katkaise yhteys',
				warning: 'Warning',
				warningText: 'Deactivation will take 6 hours (360 blocks).'
			},
			startRemote: {
				title: 'Start Delegated Harvesting',
				wallet: 'Lompakko',
				account: 'Tili',
				password: 'Lompakon salasana',
				start: 'Käynnistä'
			},
			stopRemote: {
				title: 'Stop Delegated Harvesting',
				wallet: 'Lompakko',
				account: 'Tili',
				password: 'Lompakon salasana',
				stop: 'Pysäytä'
			},
			logoutWarning: {
				leavePage: "You're leaving your wallet. Remember that if you leave your wallet this way, some others may still be able to access your wallet from this computer. To prevent that from happening, please log out using the \"Close wallet\" menu item in the top-right dropdown menu before you close the browser tab or navigate away."
			},
			addContact: {
				title: 'Lisää yhteystieto',
				add: 'Lisää'
			},
			editContact: {
				title: 'Muokkaa yhteystieto',
				saveChanges: 'Tallenna muutokset'
			},
			removeContact: {
				title: 'Poista yhteystieto',
				remove: 'Poista'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'Lompakko on tuotu onnistuneesti!',
			nav: {
				start: 'Aloitussivu',
				about: 'NEM ',
				settings: 'Asetukset'
			},
			main: {
				leftTitle: 'Uusi <em>NEM käyttäjä</em>?',
				leftButton: 'Luo uusi lompakko',
				walletNamePlh: 'Lompakkosi nimi',
				passwordPlh: 'Salasana',
				confirmPasswordPlh: 'Vahvista salasana',
				create: 'Luo',
				creating: 'Luodaan...',
				rightTitle: 'Oletko jo <em>NEM</em>tilin käyttäjä?',
				rightButton: 'Avaa lompakkosi',
				openButton: 'Avaaa',
				walletsFound: 'Löydetty <strong>{{1}}</strong> <em>Lompakko(a)</em>',
				copyright: 'Valokuvaaja <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC salaa lompakkosi',
						description: '<em>Lompakon turvallisuus</em> on erittäin tärkeää varkauksien välttämiseksi.'
					},
					{
						title: 'NCC salaa lompakkosi',
						description: '<em>Lompakon turvallisuus</em> on erittäin tärkeää varkauksien välttämiseksi.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'Miten NCC toimii?',
						paragraphs: [
							'<strong>NCC</strong> luo yhteyden assetteihisi ja XEM tilillesi, kuin perinteinen lompakko:',
							'<strong>NCC</strong> vaatii yhteyden <strong>NIS</strong> palvelimelle, ollakseen toimintavalmis. Normaalisti paikallinen palvelin on aktiivinen, kun se on asennettu <strong>NCC</strong>palvelun kanssa.',
							'Voit myös konfiguroida pääsyn etäpalvelimelle <strong>NIS</strong>.'
						],
						listItems: [
							'Sinulla voi olla useita lompakoita',
							'Voit määrittää useita tilejä lompakkoosi'
						]
					},
					{
						title: 'Mikä on &#42;NIS?',
						paragraphs: [
							'Tämä komponentti pitää <strong>NEM</strong> cloud-palvelun käynnissä.',
							'The more <strong>NIS</strong> there are in the network, the better the security.,',
							'<strong>NIS</strong> on yhdyspiste <strong>NEM</strong> cloudiin.'
						],
						legend: '<strong>&#42;NIS</strong> tarkoittaa <strong>NEM Infrastructure Server</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2015. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: 'Noin {{1}} päivää sitten',
			lastAccessJustNow: 'Juuri nyt',
			lastAccessTooltip: 'Viimeinen kirjautuminen oli {{1}}',
			primary: 'Ensisijainen',
			primaryShort: 'E',
			noLabel: '<Ei merkkiä>',
			copiedToClipboard: 'Osoite on kopioitu työpöydälle!',
			actions: {
				refreshInfo: 'Päivitä Info',
				bootLocalNode: 'Käynnistä Local Node uudelleen',
				changeWalletName: 'Vaihda lompakon nimi',
				changeWalletPassword: 'Vaihda lompakon salasana',
				mergeWallets: 'Yhdistä lompakot',
				exportWallet: 'Vie lompakko',
				createAccount: 'Luo uusi lompakko',
				createRealAccountData: 'Luo real account data',
				verifyRealAccountData: 'Tarkista real account data',
				showPrivateKey: 'Show Account\'s PRIVATE key',
				showRemotePrivateKey: 'Show Remote Account\'s PRIVATE key',
				viewDetails: 'View Account Details',
				addAccount: 'Lisää olemassaoleva tili',
				changeAccountLabel: 'Vahda tilin nimi',
				setPrimary: 'Aseta ensisijainen tili',
				removeAccount: 'Poista tili',
				clientInfo: 'Client Info',
				closeWallet: 'Sulje lomakko',
				closeProgram: 'Sulje ohjelma',
				copyClipboard: 'Kopioi osoite työpöydälle',
				copyDisabled: 'Copying an address requires flash',
				convertMultisig: 'Muunna toinen tili multisig tiliksi'
			},
			nav: [
				'Valikko',
				'Viesti',
				'Osoitekirja',
				'Siirrot',
				'Louhitut lohkot',
				'Asset kaupankäynti',
				'Uutiset',
				'Sovellukset',
				'Tilit',
				'Asetukset',
				'Sulje ohjelma'
			],
			bootNodeWarning: 'Local node täytyy käynnistää uudelleen, että saat kaikki NCC:n ominaisuudet käyttöön.'
		},
		dashboard: {
			assets: {
				title: 'Assettisi'
			},
			importance: {
				title: 'Merkitys',
				unknown: 'Tuntematon tila',
				start: 'Aloita paikallinen lohinta ',
				harvesting: 'Louhinta ',
				stop: 'Lopeta paikallinen louhinta ',
				description: 'Tilin merkitys NEM cloud palvelussa',
				remoteHarvest: {
					activate: 'Activate delegated harvesting',
					activating: 'Activating delegated harvesting...',
					active: 'Delegated harvesting is active',
					deactivate: 'Deactivate delegated harvesting',
					deactivating: 'Deactivating delegated harvesting...',
					startRemoteHarvesting: 'Start delegated harvesting',
					remotelyHarvesting: 'Etälouhinta ',
					stopRemoteHarvesting: 'Stop delegated harvesting'
				}
			},
			transactions: {
				title: 'Viimeisimmät siirrot',
				sendNem: 'Lähetä XEM',
				signMultisig: 'ALLEKIRJOITA',
				balance: 'Saldo',
				loading: 'Loading balance',
				accountCosignatories: 'Multisig tili',
				accountCosignatoriesView: 'view cosignatories',
				vestedBalance: 'Louhittu saldo',
				syncStatus: '(lohko {{1}}{{#2}} : {{3}} päivää takana{{/2}})',
				notSynced: 'might be inaccurate, NIS not synchronized yet',
				unknown: 'Tuntematon',
				columns: [
					'',
					'Aika',
					'Lähettäjä/Vastaanottaja',
					'Viesti',
					'',
					'Tiedot',
					'Vahvistukset',
					'Palkkio',
					'Summa'
				],
				noMessage: 'Ei viestiä',
				encrypted: 'Viesti on salattu',
				view: 'Näytä',
				confirmationsUnknown: 'Tuntematon',
				pending: 'Viivästynyt',
				seeAll: 'Näytä kaikki siirrot',
				noTransactions: 'Siirtoja ei ole vielä tehty'
			},
			nemValue: {
				title: 'XEM arvon statistiikka'
			},
			messages: {
				titleTooltip: 'Viesti'
			},
			news: {
				titleTooltip: 'Uutiset'
			},
			notAvailable: 'Ei vielä saatavilla beta versiossa'
		},
		transactions: {
			title: 'Siirrot',
			sendNem: 'Lähetä XEM',
			balance: 'Saldo',
			filters: {
				confirmed: 'Vahvistettu',
				unconfirmed: 'Vahvistamaton',
				incoming: 'Saapuva',
				outgoing: 'Lähtevä'
			},
			table: {
				columns: [
					'',
					'Aika',
					'Lähettäjä/Vastaanottaja',
					'Viesti',
					'',
					'Tiedot',
					'Vahvistuksia',
					'Palkkio',
					'Summa'
				],
				noMessage: 'Ei viestiä',
				encrypted: 'Viesti on salattu',
				view: 'Näytä',
				confirmationsUnknown: 'Tuntematon',
				pending: 'Viivästynyt',
				noTransactions: 'Siirtoja ei ole vielä tehty',
				loading: 'Lataa lisää siirtoja...'
			}
		},
		harvestedBlocks: {
			title: 'Louhitut lohkot ',
			feeEarned: 'Ansaittuja palkkioita 25 viimeisestä louhitusta lohkosta',
			unknown: 'Tuntematon',
			table: {
				columns: [
					'Korkeus',
					'Aika',
					'Lohkon vaikeus',
					'Palkkio'
				],
				noBlocks: 'Yhtään lohkoa ei ole vielä louhittu',
				loading: 'Ladataan lisää louhittuja lohkoja '
			},
			harvesting: {
				unknown: 'Tuntematon tila',
				start: 'Aloita paikallinen louhinta',
				harvesting: 'Louhinta ',
				stop: 'Lopeta paikallinen louhinta ',
				remoteHarvest: {
					startRemoteHarvesting: 'Start delegated harvesting',
					stopRemoteHarvesting: 'Stop delegated harvesting'
				}
			}
		},
		addressBook: {
			title: 'Osoitekirja',
			addContact: 'Lisää yhteystieto',
			table: {
				columns: [
					'Tilin osoite',
					'Yksityinen merkintä',
					'Julkinen merkintä'
				],
				noContacts: 'Osoitekirjassasi ei ole yhteystietoja'
			},
			noLabel: 'Ei merkkiä',
			sendNem: 'Lähetä XEM',
			edit: 'Muokkaa',
			remove: 'Poista'
		},
		settings: {
			title: 'Asetukset',
			settings: [
				{
					name: 'Kieli'
				}
			],
			save: 'Tallenna muutokset',
			saveSuccess: 'Asetukset on tallennettu onnistuneesti'
		}
	}
});
