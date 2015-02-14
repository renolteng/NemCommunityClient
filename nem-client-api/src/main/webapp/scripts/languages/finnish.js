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
			106: 'Ennenkuin voit käyttää lompakkoa, se pitää avata. Avataksesi lompakon, on sinulla oltava salasana.',
			107: 'Lompakko ei sisällä tätä tiliä.',
			108: 'Tiliä ei voida poistaa.Tämä johtuu todenäköisesti siitä, että tilin saldo on enemmän, kuin 0 XEM, tai yrität poistaa ensisijaista tiliä.',
			109: 'Saman niminen lompakko on jo olemassa. Valitse toinen lompakon nimi.',
			110: 'Lompakko sisältää jo tämän tilin.',
			111: 'Lomakon nimi on hakemisto.',
			112: 'Lompakkotiedoston laajennus on väärä.',
			113: 'Lompakkoa ei voitu poistaa.',
			121: 'Osoitekijatiedostoa ei ole.',
			122: 'Osoitekijaa ei ole luotu.',
			123: 'Osoitekirjatiedosto on viallinen, palauta se varmuuskopiosta.',
			124: 'Syöttämäsi osoitekirjan salasana on väärä.',
			125: 'Et syöttänyt osoitekirjan salasanaa.',
			127: 'Osoitekirja ei sisällä tätä osoitetta.',
			128: 'The address provided is not valid.',
			129: 'Samalla nimellä oleva osoitekija on jo olemassa. Valitse toinen osoitekirjan nimi.',
			130: 'Osoitekirja sisältää jo antamasi osoitteen.',
			131: 'Osoitekirjan nimi on hakemisto.',
			132: 'Osoitekirjan laajennustiedosto on väärä.',
			133: 'Osoitekirjaa ei voida poistaa.',
			202: 'Salattua viestiä ei voida lähettää, koska vastaanottaja ei ole tehnyt koskaan tilisiirtoa.',
			305: 'NEM Infrastructure Server ei ole käytettävissä.',
			306: 'Esiintyi ongelma, jota kehitystiimi ei ole tavannut aikaisemmin. Pahoittelemme tilannetta, yritä uudelleen. Muussa tapauksessa avaa uusi keskustelu NEM NIS/NCC foorumissa.',
			400: 'Jotkut parametrit puuttuvat tai ovat viallisia.',
			401: 'Tätä toimintoa ei voida saattaa loppuun, koska muutoin on mahdollista että \'private key\' näkyy ulkopuolisille, mikäli se lähetetään NIS-palveluun.\n',
			404: 'Lähdettä ei löydy.',
			500: 'Esiintyi ongelma, jota kehitystiimi ei ole tavannut aikaisemmin. Pahoittelemme tilannetta, yritä uudelleen. Muussa tapauksessa avaa uusi keskustelu NEM NIS/NCC foorumissa.',
			600: 'NCC vaatii NIS palvelimen uudelleenkäynnistyksen, jotta siirtoja voidaan suorittaa NEM cloud palvelusta. Käytä NCC menua näynnistääksesi uudelleen Local node.',
			601: 'NIS on jo käynnistetty uudelleen, toinen yritys ei ole mahdollinen.',
			602: 'Cannot perform any operations until db is fully loaded.',
			699: 'Louhijoiden (harvesters) maksimimäärä palvelimella on saavutettu.',
			700: 'Louhintaa (harvesting) ei voida aloittaa. Toiminto on mahdollista, kun tilisi saldo on vähintään 1000 XEM.',
			701: 'Aikaraja on ylitetty. Aikaraja on oltava yhden päivän sisällä.',
			702: 'Valittu aikaraja on liian kaukana tulevaisuudessa. Aikaraja täytyy olla yhden päivän sisällä.',
			703: 'Tililläsi ei ole siirrettävää summaa.',
			704: 'Teksti on liian pitkä lähetettäväksi siirron mukana. Yritä lyhentää viestiä.',
			705: 'Hash-koodi on jo tietokannassa tai siirtoja on vahvistamatta.',
			706: 'Siirron allekirjoitusta ei voida varmistaa.',
			707: 'Siirron aikamerkintä on liian kaukana menneisyydessä.',
			708: 'Siirron aikamerkintä on liian kaukana tulevaisuudessa.',
			709: 'Tili on tuntematon. Tilillä on oltava yksi siirto tai vastaanotto, jotta se voidaan tunnistaa verkossa.',
			710: 'Siirtoa ei hyväksytty, koska siirtojen välimuisti on täynnä. Korkeampi palkkio parantaaa siirron hyväksymistä.',
			730: 'Turvatun louhinnan siirto (secure harvesting) on ristiriidassa olemassaolevan siirron kanssa.',
			731: 'Turvatun louhinnan (Secure harvesting)tilin saldo on nolla, eikä sitä voida käyttää.',
			732: 'Siirtoa ei hyväksytty, viivästynyt siirto on vielä käynnissä.',
			733: 'Turvattu louhinta (Secure harvesting)on jo aktiivinen.',
			734: 'Turvattu louhinta (secure harvesting) ei ole aktiivinen,\njoten sitä ei voida pysäyttää.',
			740: 'Siirtoa ei ole mahdollinen multisig-tilille.',
			741: 'Multisig-allekirjoitus hylättiin.Käytössä oleva tili ei ole multisig tili.',
			742: 'Multisig-allekirjoitus hylättiin. Multisig-siirtoa ei tunneta NEM verkossa.',
			743: 'Multisig-tilin muutos hylätty.Yksi lisätty tili on jo allekirjoittaja.',
			901: 'Tapahtui virhe määritettäessä offline node.',
			1000: 'Private key ja public key, eivät vastaa toisiaan.',
			1001: 'Public key ja osoite eivät vastaa toisiaan.',
			1002: 'Osoite ei kuulu varsinaiseen verkkoon.'
		},
		common: {
			success: 'Onnistui',
			appStatus: {
				nccUnknown: 'NCC tila tuntematon.',
				nccUnavailable: 'NCC ei ole käytettävissä.',
				nccStarting: 'NCC on käynnistymässä...',
				nisUnknown: 'NIS tila on tuntematon',
				nisUnavailable: 'NIS ei ole käytettävissä',
				nisStarting: 'NIS on käynnistymässä...',
				notBooted: 'NIS on käynnistettävä uudelleen. Avaa lompakkosi ja käynnistä local node uudelleen popup valikosta, jotta\nvoit muuttaa auto-boot asetuksia. ',
				loading: 'Loading blocks from db, at block: ',
				booting: 'NIS on käynnistymässä uudelleen...',
				nisInfoNotAvailable: 'NIS info ei ole vielä käytettävissä. Yritetään hakea\nNIS infoa...',
				synchronizing: 'NIS synkronointi on käynnissä. Lohko {{1}},  {{2}} takana.',
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
			noCharge: 'Olemassaolevaa tiliä <b>ei</b> veliteta, se katetaan\nmultisig-tililtä.',
			justUse: 'Käytä'
		},
		transactionTypes: [
			'SIIRTO LÄHETYS',
			'IMPORTANCE LÄHETYS',
			'MULTISIG-TILIN MUOKKAUS',
			'MULTISIG SIIRTO'
		],
		transactionDirections: {
			pending: 'Viivästynyt siirto ',
			outgoing: 'Lähtevä siirto',
			incoming: 'Tuleva siirto',
			self: 'Itsesiirto',
			importance: 'Importance siirto',
			modification: 'Multisig-tilin muokkaus'
		},
		modals: {
			error: {
				title: 'Hups!',
				caption: 'Virhe {{1}}'
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
					port: 'Port'
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
				title: 'Muunna tili multisig-tiliksi',
				multisigAccount: 'Multisig-tili',
				cosignatories: 'Allekijoittajien sositteet',
				labelDesc: 'Tämä tili on nimetty {{1}}',
				nullLabelDesc: 'Tällä tilillä ei ole nimeä',
				addCosignatory: '+ Lisää allekirjoittaja',
				cancel: 'Peruuta',
				convert: 'Muunna',
				fee: 'Palkkio',
				feeValidation: 'Palkkio ei voi olla vähempää, kuin minimipalkkio',
				dueBy: 'Maksettava',
				useMinimumFee: 'Käytä minimipalkkiota',
				hours: 'Tunti(a)',
				txConfirm: {
					title: 'Vahvista multiig-tilin muunto',
					total: 'Yhteensä'
				},
				warning: 'Multisig account is on the list of cosignatories. This will result in locking down the account cutting off access to the fund. most likely you <b>DO NOT</b> want to do that.'
			},
			signMultisig: {
				title: 'Allekirjoita multisig-siirto',
				original: {
					from: 'Multisig tili',
					to: 'Vastaanottaja',
					amount: 'Summa',
					fee: 'Sisäinen palkkio',
					deadline: 'Määräaika'
				},
				multisigFees: 'Multisig palkkiot',
				multisigTotal: 'Yhteensä',
				sender: 'Allekirjoittaja',
				fee: 'Palkkio',
				feeValidation: 'Palkkio ei voi olla vähempää, kuin minimipalkkio',
				dueBy: 'Maksettava',
				useMinimumFee: 'Käytä minimipalkkiota',
				hours: 'Tunti(a)',
				password: 'Salasana',
				passwordValidation: 'Salasanakenttä ei voi olla tyhjä',
				send: 'Lähetys',
				cancel: 'Peruuta',
				sending: 'Lähetetään...',
				successMessage: 'Siirto on suoritettu onnistuneesti!',
				txConfirm: {
					title: 'Vahvista multisig-siirto',
					message: 'Viesti',
					encrypted: 'Viesti on salattu',
					noMessage: 'Ei viestiä'
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
				recipientValidation: 'Tilin osoite on oltava 40 merkkiä pitkä, ilman\nviivoja',
				message: 'Viesti',
				encrypt: 'Salaa viesti',
				fee: 'Palkkio',
				multisigFee: 'Multisig-palkkio',
				feeValidation: 'Palkkio ei voi olla vähempää, kuin minimipalkkio.',
				dueBy: 'Maksettava',
				useMinimumFee: 'Käytä minimipalkkiota',
				hours: 'Tunnit',
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
					dueBy: 'Maksettava',
					hours: 'Tunnit',
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
					message: 'Local node on käynnistettävä uudelleen, jotta XEM\nvoidaan lähettää!'
				},
				bootingWarning: {
					title: 'Node on käynnistymässä uudelleen',
					message: 'Odota, että uudelleenkäynnistys on valmis, ennenkuin teet siirtoja.'
				},
				loadingWarning: {
					title: 'Loading db'

				}
			},
			clientInfo: {
				title: 'Client info',
				ncc: 'NEM Community Client - NCC',
				signer: 'Allekirjoittaja',
				remoteServer: 'Etäpalvelin',
				local: 'Local',
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
				multisigAccount: 'Multisig-tili',
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
				multisigFees: 'Multisig-palkkio',
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
				vested: "vested",
				importance: 'Merkitys',
				publicKey: 'Julkinen avain',
				noPublicKey: 'Ei julkista avainta',
				harvestedBlocks: 'Louhitut lohkot (Harvested blocks)',
				close: 'Sulje'
			},
			bootLocalNode: {
				title: 'Käynnistä local node uudelleen',
				account: 'Tili, jolta local node käynnistetään uudelleen',
				noLabel: '<span class=\'null\'>&lt;Ei nimeä&gt;</span>',
				wallet: 'Lompakko',
				node: 'Node nimi',
				boot: 'Käynnistä uudelleen',
				booting: 'Käynnistymässä uudelleen...'
			},
			closeWallet: {
				title: 'Sulje lompakko',
				message: 'Oletko varma, että haluat sulkea lompakon ja palata\naloitussivulle?'
			},
			createAccount: {
				title: 'Luo uusi lompakko',
				label: 'Yksityinen merkintä',
				wallet: 'Lompakko',
				password: 'Lompakon salasana',
				successMessage: 'Tili {{1}} {{#2}}({{2}}){{/2}} on luotu!',
				create: 'Luo'
			},
			createRealAccountData: {
				title: 'Luo real account data',
				message: 'Alla olevat tiedot ovat oikean lompakkosi tiedot (real account data), kun NEM on julkaistu. Tallenna osoite, public key ja tärkeimpänä private key, turvalliseen paikkaan. Jos private key hukkuu hävität samalla kaikki XEM kolikkosi!',
				address: 'Osoite',
				publicKey: 'Public key',
				privateKey: 'Private key',
				confirm: {
					title: 'Tallenna private key',
					message: 'Oletko varma, että private key on tallennettu turvalliseen paikkaan?'
				},
				recheck: {
					title: 'Tarkista tallennettu private key uudellen',
					message: 'Syötä juuri luomasi private key uudelleen tarkistaaksesi, että sinulla on oikea private key.\nJos sinulla ei ole sitä, niin haluat ehkä luoda uuden.',
					correct: {
						title: 'Hienoa!',
						message: 'Näyttäisi siltä, että oikea private key on tallennettu. Muista säilyttää se aina varmassa ja turvallisessa paikassa.'
					},
					incorrect: {
						title: 'Hmm...',
						message: 'Syöttämäsi private key ei ole oikea! Haluatko syöttää sen uudelleen, vai palata tilisi alkuperäisiin tilitietoihin?',
						tryAgain: 'Yritä syöttää uudelleen',
						seeOriginal: 'Katso alkuperäiset tiedot'
					},
					recheck: 'Tarkista'
				},
				ok: 'OK'
			},
			verifyRealAccountData: {
				title: 'Varmista real account data',
				message: 'Syötä tallennettu osoitteesi, public key ja private key\nuudelleen, jotta voita tarkistaa niiden yhteensopivuuden.',
				address: 'Osoite',
				publicKey: 'Public key',
				privateKey: 'Private key',
				dataMatched: 'Kaikki näyttää hyvältä, syöttämäsi osoite, public key ja private key sopivat yhteen.',
				verify: 'Varmista'
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
				wallet: 'Lompakko',
				password: 'Lompakon salasana',
				warning: 'Varmista, että tilisi on tyhjä ennen kuin poistat sen, tai kaikki XEMit poistuvat tililtäsi myös.\n\n',
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
				title: 'Aktivoi etälouhinta (Remote harvesting)',
				wallet: 'Lompakko',
				account: 'Tili',
				hoursDue: 'Maksettavaksi (tunnit)',
				password: 'Lompakon salasana',
				activate: 'Aktivoi'
			},
			deactivateRemote: {
				title: 'Lopeta etälouhinta (Remote harvesting)',
				wallet: 'Lompakko',
				account: 'Tili',
				hoursDue: 'Maksettavaksi (tunnit)',
				password: 'Lompakon salasana',
				deactivate: 'Katkaise yhteys'
			},
			startRemote: {
				title: 'Aloita etälouhinta (Remote harvesting)',
				wallet: 'Lompakko',
				account: 'Tili',
				password: 'Lompakon salasana',
				start: 'Käynnistä'
			},
			stopRemote: {
				title: 'Lopeta etälouhinta (Remote harvesting)',
				wallet: 'Lompakko',
				account: 'Tili',
				password: 'Lompakon salasana',
				stop: 'Pysäytä'
			},
			logoutWarning: {
				leavePage: 'Olet poistumassa lompakostasi. Jos poistut näin, on toisilla tämän tietokoneen käyttäjillä mahdollisuus käyttää lompakkoasi. Poistu lompakosta \'close wallet\'\ntoiminnolla menu pudotusvalikosta, joka sijaitsee ruudun oikeassa yläkulmassa, ennen kuin suljet selaimen.'
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
				about: 'NEM tietoja',
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
						description: '<em>Lompakon turvallisuus</em> on erittäin tärkeää,\nettä voit välttää XEM kolikkojen ja assettien varkaudet.'
					},
					{
						title: 'NCC salaa lompakkosi',
						description: '<em>Lompakon turvallisuus</em> on erittäin tärkeää,\nettä voit välttää XEM kolikkojen ja assettien varkaudet.'
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
							'Useita lompakoita',
							'Määritä useita tilejä lompakkoosi'
						]
					},
					{
						title: 'Mikä on &#42;NIS?',
						paragraphs: [
							'Tämä komponentti pitää cloud-palvelun <strong>NEM</strong> käynnissä.',
							' <strong>NIS</strong> lisää turvallisuutta.',
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
			primaryShort: 'P',
			noLabel: '<Ei merkkiä>',
			copiedToClipboard: 'Osoite on kopioitu työpöydälle',
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
				addAccount: 'Lisää olemassaoleva tili',
				changeAccountLabel: 'Vahda tilin nimi',
				setPrimary: 'Aseta ensisijainen tili',
				removeAccount: 'Poista tili',
				clientInfo: 'Client Info',
				closeWallet: 'Sulje lomakko',
				closeProgram: 'Sulje ohjelma',
				copyClipboard: 'Kopioi osoite työpöydälle',
				convertMultisig: 'Muunna multisig-tiliksi'
			},
			nav: [
				'Valikko',
				'Viesti',
				'Osoitekirja',
				'Siirrot',
				'Harvested blocks',
				'Asset Exchange',
				'Uutiset',
				'Sovellukset',
				'Tilit',
				'Asetukset',
				'Sulje ohjelma'
			],
			bootNodeWarning: 'Local node täytyy käynnistää uudelleen, että saat kaikki\nNCC:n ominaisuudet käyttöön.'
		},
		dashboard: {
			assets: {
				title: 'Assettisi'
			},
			importance: {
				title: 'Merkittävä määrä',
				unknown: 'Tuntematon tila',
				start: 'Aloita local harvesting',
				harvesting: 'Harvesting',
				stop: 'Lopeta local harvesting',
				description: 'Tilin merkitys NEM cloud palvelussa',
				remoteHarvest: {
					activate: 'Aktivoi etälouhinta (remote harvesting)',
					activating: 'Aktivoidaan etälouhinta (remote harvesting)...',
					active: 'Etälouhinta (remote harvesting) on aktiivinen',
					deactivate: 'Lopeta etälouhinta (remote harvesting)',
					deactivating: 'Lopetetaan etälouhinta (remote harvesting)...',
					startRemoteHarvesting: 'Käynnistä etälouhinta (remote harvesting)',
					remotelyHarvesting: 'Etälouhinta (Remotely harvesting)',
					stopRemoteHarvesting: 'Pysäytä remote harvesting'
				}
			},
			transactions: {
				title: 'Viimeisimmät siirrot',
				sendNem: 'Lähetä XEM',
				signMultisig: 'ALLEKIRJOITA',
				balance: 'Tämänhetkinen saldo',
				vestedBalance: 'Vested Balance',
				syncStatus: '(lohko {{1}}{{#2}} : {{3}} päivää takana{{/2}})',
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
			notAvailable: 'Ei ole saatavilla beta versiossa'
		},
		transactions: {
			title: 'Siirrot',
			sendNem: 'Lähetä XEM',
			balance: 'Tämänhetkinen saldo',
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
			title: 'Harvested blocks',
			feeEarned: 'Ansaittuja palkkioita 25 viimeisestä louhitusta lohkosta\n(harvested blocks)',
			unknown: 'Tuntematon',
			table: {
				columns: [
					'Korkeus',
					'Aika',
					'Lohkon koodi (hash)',
					'Palkkio'
				],
				noBlocks: 'Yhtään lohkoa ei ole vielä louhittu (harvested)',
				loading: 'Ladataan lisää lohkoja (harvested blocks)'
			},
			harvesting: {
				unknown: 'Tuntematon tila',
				start: 'Aloita paikallinen louhinta (local harvesting)',
				harvesting: 'Louhinta (Harvesting)',
				stop: 'Lopeta paikallinen louhinta (local harvesting)',
				remoteHarvest: {
					startRemoteHarvesting: 'Käynnistä etälouhinta (remote harvesting)',
					stopRemoteHarvesting: 'Lopeta etälouhinta (remote harvesting)'
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
