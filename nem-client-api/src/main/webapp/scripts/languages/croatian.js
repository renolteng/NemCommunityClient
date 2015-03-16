define({
	id: 'hr',
	name: 'hrvatski',
	texts: {
		preferences: {
			thousandSeparator: ' ',
			decimalSeparator: '.'
		},
		faults: {
			101: 'Datoteka ne postoji.',
			102: 'Novčanik nije stvoren.',
			103: 'Datoteka novčanika je oštećena. Molim Vas povratite novčanik iz sigurnosne kopije.',
			104: 'Unešena lozinka je netočna. Nadam se da se možete sjetiti točne lozinke jer izgubljenu lozinku je nemoguće povratiti!',
			105: 'Lozinka novčanika nije unešena.',
			106: 'Novčanik mora biti otvoren da biste ga mogli koristiti. Morate unijeti lozinku za novčanik da biste bili sigurni da ste ovlašteni za korištenje istog.',
			107: 'Novčanik ne sadrži taj račun.',
			108: 'Nemoguće je ukloniti račun jer je iznos na njemu veći od 0 XEM-a ili jer pokušavate ukloniti primarni račun.',
			109: 'Drugi novčanik sa istim nazivom već postoji. Molim Vas izaberite drugi naziv za novčanik.',
			110: 'Novčanik već sadrži taj račun.',
			111: 'Naziv novčanika je direktorij',
			112: 'Nastavak datoteke novčanika je netočan.  ',
			113: 'Novčanik se ne može ukloniti.',
			121: 'Datoteka adresara ne postoji.',
			122: 'Adresar nije bio stvoren',
			123: 'Datoteka adresara je oštećena. Molimo vas povratite datoteku adresara iz sigurnosne kopije.',
			124: 'Unesena lozinka je neidpravna.',
			125: 'Nije unesena lozinka adresara',
			127: 'Adresa u adresaru nije pronađena.',
			128: 'Unešena adresa nije ispravna.',
			129: 'Adresar s takvim nazivom već postoji. Molim vas izaberite drugi naziv adresara.',
			130: 'Adresar već sadrži takvu adresu.',
			131: 'Naziv adresara je datoteka',
			132: 'Nastavak naziva datoteke adresara je netočan.',
			133: 'Adresar nije mogao biti izbrisan.',
			202: 'Šifrirana se poruka nije mogla poslati jer primatelj nije nikad napravio niti jednu transakciju.',
			305: 'NEM infrastrukturni poslužitelj (NIS) nije dostupan.\n\nPokušajte ponovo pokrenuti NEM program.\n\nAko koristite udaljeni NIS, provjerite postavke poslužitelja za ispis grešaka ili koristite drugi udaljeni NIS.',
			306: 'Došlo je do pogreške koju razvojni tim nije predvidio. Ispričavamo se zbog toga. Novi pokušaj bi mogao pomoći, u suprotnom molim Vas pošaljite upit NEM NIS/NCC zajednici.',
			400: 'Neki parametar nedostaje ili je nevažeći.',
			401: 'Ova operacije nije mogla biti sprovedena zato što bi moglo doći do otkrivanja privatnog kluča slanjem na NIS.',
			404: 'Zatraženi resurs nije mogao biti pronađen.',
			500: 'Došlo je do pogreške koju razvojni tim nije predvidio. Ispričavamo se zbog toga. Ponovno pokretanje programa bi moglo pomoći u suprotnom molimo vas da grešku prijavite NEM NIS/NCC zajednicom.',
			600: 'Prije slanja i primanje transakcija sa NEM oblaka, NIS poslužitelj mora biti pokrenut. Molim Vas da u NCC izborniku koristite stavku za pokretanje lokalnog čvora.',
			601: 'NIS čvor je već pokrenut. Sljedeći pokušaj za pokretanje NIS čvora nije moguć.',
			602: 'Skoro spreman. NEM infrastrukturni poslužitelj trenutno učitava blokove. Novčanik će biti funkcionalan nakon što se baza u potpunosti učita.',
			699: 'Dostignut je najveći dopušteni broj žetveoca na poslužitelju.',
			700: 'Navedeni račun ne ispunjava osnovne kriterije za ubiranje blokova. Za ubiranje blokova račun mora sadržavati najmanje iznos od 1000 XEM-a.',
			701: 'Datum isteka je u prošlosti. Datum isteka mora biti u roku od jednog dana.',
			702: 'Datum isteka je predaleko u budućnosti. Datum isteka mora biti u roku od jednog dana.',
			703: 'Vaš račun nema dovoljan iznos XEM-a za slanje.',
			704: 'Tekst u poruci je predugačak. Molim Vas, probajte smanjiti dužinu teksta u Vašoj poruci ako ju želite slati.',
			705: 'Šifra transakcije već postoji u bazi podataka ili u listi nepotvrđenih transakcija.',
			706: 'Potpis transakcije nije mogao biti provjeren.',
			707: 'Vremenska oznaka transakcije je predaleko u prošlosti.',
			708: 'Vremenska oznaka transakcije je predaleko u budućnosti.',
			709: 'Račun je nepoznat. Račun se mora pojaviti barem u jednoj transakciji (pošiljatelja ili primatelja) da bi bio prepoznat u mreži.',
			710: 'Transakcija je odbačena. Veća naknada povećava šansu za prihvaćanje transakcije.',
			730: 'Transakcija prijenos važnosti (sigurno ubiranje) je u kofliktu sa postojećom transakcijom.',
			731: 'Saldo na računu sigurnog ubiranja je veći od nule i ne može se koristiti',
			732: 'Prijenos važnosti odbijen. Operacija prijenosa važnosti se već nalazi na čekanju.',
			733: 'Sigurno ubiranje je već aktivno.',
			734: 'Sigurno ubiranje NIJE aktivno. Nemoguce deaktivirati.',
			740: 'Transakcija nije dozvoljena u višepotpisničkom računu.',
			741: 'Transakcija potpisa je odbačena. Ovaj račun nije potpisnik višepotpisničkog računa.',
			742: 'Višepotpisnička transkacija je odbačena. Transakcija nije prepoznata u NEM mreži',
			743: 'Promjena višepotpisničkog računa je odbačena. Jedan od dodanih računa je već potpisnik.',
			901: 'Došlo je do pogreške kod postavljanja u izvanmrežni način rada.',
			1000: 'Privatni i javni ključ koji ste unijeli se ne poklapaju.',
			1001: 'Javni ključ i adresa koju ste unijeli se ne poklapaju.',
			1002: 'Adresa ne pripada glavnoj mreži.'
		},
		common: {
			success: 'Uspjeh',
			appStatus: {
				nccUnknown: 'NCC status je nepoznat',
				nccUnavailable: 'NCC je nedostupan',
				nccStarting: 'NCC se pokreće...',
				nisUnknown: 'NIS status je nepoznat',
				nisUnavailable: 'NIS je nedostupan',
				nisStarting: 'NIS se pokreće...',
				notBooted: 'NIS mora biti pokrenut. Molim vas otvorite vaš novčanik i pokrenite lokalni čvor preko skočnog prozora ili podesite postavke automatsko pokretanje.',
				loading: 'Učitavanje blokova iz baze, na bloku: ',
				booting: 'Pokrećem NIS...',
				nisInfoNotAvailable: 'NIS informacije nisu još dostupne. Pokušavam prikupiti informacije NIS-a...',
				synchronizing: 'NIS sinkronizacija. Na bloku {{1}}, otprilike {{2}} u zaostatku.',
				daysBehind: {
					0: 'manje od 1 dan',
					1: '1 dan',
					many: '{{1}} dana'
				},
				synchronized: 'NIS je sinkroniziran!',
				noRemoteNisAvailable: 'Nije pronađen niti jedan udaljeni NIS (server), odspojen od interneta?'
			},
			addressBook: 'Adresar',
			password: 'Lozinka',
			passwordValidation: 'Lozinka ne smije biti prazna',
			address: 'Adresa',
			privateLabel: 'Privatna oznaka',
			publicLabel: 'Javna oznaka',
			noCharge: 'Naknada <b>neće</b> biti naplaćena sa trenutnog računa. Višepotpisnički račun to pokriva.',
			justUse: 'Samo u koristi'
		},
		transactionTypes: [
			'TRANSAKCIJA PRIJENOSA',
			'PRIJENOS VAŽNOSTI',
			'PROMJENA MULTISIG RAČUNA',
			'VIŠEPOTPISNIČKA TRANSAKCIJA'
		],
		transactionDirections: {
			pending: 'Transakcija na čekanju',
			outgoing: 'Odlazna transakcija',
			incoming: 'Dolazna transakcija',
			self: 'Vlastita transakcija',
			importance: 'Transakcija važnosti',
			modification: 'Skupne izmjene više potpisa'
		},
		modals: {
			error: {
				title: 'Oops!',
				caption: 'GREŠKA {{1}}'
			},
			yikes: {
				title: 'Greška!',
				caption: 'info kod {{1}}'
			},
			confirmDefault: {
				yes: 'Da',
				no: 'Ne'
			},
			settings: {
				title: 'Postavke',
				language: {
					label: 'Jezik'
				},
				remoteServer: {
					tabTitle: 'Udaljeni poslužitelj',
					protocol: 'Protokol',
					protocolOptions: {
						http: 'HTTP'
					},
					host: 'Host',
					port: 'Port',
					defaultPort: 'Koristi zadani port'
				},
				autoBoot: {
					tabTitle: 'Automatsko pokretanje',
					name: 'Naziv čvora',
					account: 'Račun',
					primaryAccount: 'Primarni račun',
					auto: 'Samostalno pokretanje prilikom otvaranja novčanika'
				},
				save: 'Spremi',
				saveSuccess: 'Postavke su uspješno spremljene'
			},
			multisig: {
				title: 'Pretvori račun u višepotpisnički',
				multisigAccount: 'Višepotpisnički račun',
				cosignatories: 'Adrese supotpisnika',
				labelDesc: 'Ovaj račun je označen kao {{1}}',
				nullLabelDesc: 'Ovaj račun nema oznake',
				addCosignatory: '+ Dodaj potpisnika',
				cancel: 'Poništi',
				convert: 'Pretvori',
				fee: 'Naknada',
				feeValidation: 'Naknade ne može biti manja od minimalne naknade',
				dueBy: 'Vremenski rok',
				useMinimumFee: 'Koristi minimalnu naknadu',
				hours: 'sati',
				txConfirm: {
					title: 'Potvrdi pretvaranje u Multisig račun',
					total: 'Ukupno'
				},
				warning: 'Višepotpisnički račun se nalazi u listi supotpisnika što može imati za posljedicu zaključavanje računa i zabranu pristupa sredstvima na istom. Vjerojatno <b>NE</b> želite to učiniti.'
			},
			signMultisig: {
				title: 'Potpiši višepotpisničku transakciju',
				original: {
					from: 'Višepotpisnički račun',
					to: 'Primatelj',
					amount: 'Količina',
					fee: 'Interna naknada',
					deadline: 'Rok'
				},
				multisigFees: 'Višepotpisničke naknade',
				multisigTotal: 'Ukupno',
				sender: 'Potpisnik',
				fee: 'Naknada',
				feeValidation: 'Naknade ne može biti manja od minimalne naknade',
				dueBy: 'Vremenski rok',
				useMinimumFee: 'Koristi najmanju naknadu',
				hours: 'sati',
				password: 'Lozinka',
				passwordValidation: 'Lozinka ne smije biti prazna',
				send: 'Šalji',
				cancel: 'Poništi',
				sending: 'Slanje...',
				successMessage: 'Transakcije je uspiješno provedena',
				txConfirm: {
					title: 'Potvrdi Multisig transakciju',
					message: 'Poruka',
					encrypted: 'Poruak je šifrirana',
					noMessage: 'Nema poruke'
				}
			},
			sendNem: {
				title: 'Slanje XEM-a',
				sender: 'Pošiljatelj',
				thisAccount: 'Ovaj račun',
				labelDesc: 'Ovaj račun je označen kao {{1}}',
				nullLabelDesc: 'Ovaj račun nema oznake',
				amount: 'Iznos',
				recipient: 'Račun primatelja',
				recipientValidation: 'Adresa računa mora sadržavati 40 karaktera bez crtica',
				message: 'Poruka',
				encrypt: 'Šifriraj poruku',
				fee: 'Naknada',
				multisigFee: 'Višepotpisnička naknada',
				feeValidation: 'Naknada nemože biti manja od najniže naknade',
				dueBy: 'zbog',
				useMinimumFee: 'Koristi najmanju naknadu',
				hours: 'sati',
				password: 'Lozinka',
				passwordValidation: 'Lozinka mora biti unešena',
				send: 'Šalji',
				cancel: 'Poništi',
				sending: 'Slanje...',
				successMessage: 'Transakcija je uspješno provedena!',
				txConfirm: {
					title: 'Potvrdi transakciju',
					amount: 'Iznos',
					to: 'Na',
					dueBy: 'Trajanje',
					hours: 'sat(i)',
					total: 'Ukupno',
					message: 'Poruka',
					encrypted: 'Poruka je šifrirana',
					noMessage: 'Nema poruke',
					cancel: 'Odustani',
					confirm: 'Potvrdi',
					sending: 'Šaljem...'
				},
				notBootedWarning: {
					title: 'Čvor nije pokrenut',
					message: 'Za slanje XEM-a lokalni čvor mora biti pokrenut. '
				},
				bootingWarning: {
					title: 'Čvor se pokreće',
					message: 'Molimo vas pričekajte prije slanja transakcije da se proces pokretanja čvora završi.'
				},
				loadingWarning: {
					title: 'Učitavanje baze'
				}
			},
			clientInfo: {
				title: 'Informacije klijenta',
				ncc: 'NEM Community Client - NCC',
				signer: 'Potpisnik',
				remoteServer: 'Udaljeni poslužitelj',
				local: 'Lokalno',
				nis: 'NEM Infrastructure Server - NIS',
				sync: 'Sinkroniziran',
				notSync: 'Nije sinkroniziran',
				notConnected: 'Nije spojen na NEM oblak',
				loading: 'Učitavanje...'
			},
			transactionDetails: {
				title: 'Detalji transakcije',
				id: 'ID',
				hash: 'Hash',
				type: 'Tip transakcije',
				direction: 'Smjer transakcije',
				pending: 'U tijeku',
				outgoing: 'Odlazno',
				incoming: 'Dolazno',
				self: 'Vlastito',
				sender: 'Pošiljatelj',
				multisigAccount: 'Višepotpisnički račun',
				issuer: 'Izdavatelj',
				recipient: 'Primatelj',
				remote: 'Udaljeno',
				multisigMessage: 'Potpisi prisutni',
				message: 'Poruka',
				noMessage: 'Nema poruka',
				encrypted: 'Poruka je šifrirana',
				time: 'Vremenski zapis',
				confirmations: 'Potvrde',
				confirmationsUnknown: 'Nepoznato',
				amount: 'Iznos',
				fee: 'Naknada',
				innerFee: 'Interna naknada',
				multisigFees: 'Višepotpisničke naknade',
				cosignatory: 'Potpisnik'
			},
			accountDetails: {
				title: 'Detalji računa',
				address: 'Adresa',
				label: 'Oznaka',
				noLabel: 'Nema oznake',
				add: 'Dodaj u adresar',
				remove: 'Ukloni iz adresara',
				balance: 'Stanje',
				vested: 'osiguran',
				importance: 'Važnost',
				publicKey: 'Javni ključ',
				noPublicKey: 'Nema javnog ključa',
				harvestedBlocks: 'Harvested blocks',
				close: 'Zatvori'
			},
			bootLocalNode: {
				title: 'Pokreni lokalni čvor',
				account: 'Račun za pokretanje lokalnog čvora',
				noLabel: '<span class=\'null\'>&lt;Nema oznake&gt;</span>',
				wallet: 'Novčanik',
				node: 'Naziv čvora',
				boot: 'Pokreni',
				booting: 'Pokretanje...',
				warning: 'Upozorenje o pokretanju čvora',
				warningText: 'Pokušavate pokrenuti čvor računom koji ima saldo od: ({{{1}}} XEM). To će otkriti privatni ključ tog računa čvoru: <u>{{2}}</u>',
				warningQuestion: 'Jeste li sigurni da želite pokrenuti čvor <u>{{3}}</u> korištenjem privatnog ključa računa {{1}} ({{2}} XEM)?<br><br>To će otkriti <span class=\"sublabelWarning\">privatni ključ</span> tog računa čvoru: <u>{{3}}</u>.'
			},
			closeWallet: {
				title: 'Zatvori novčanik',
				message: 'Jeste li sigurni da želite zatvoriti novčanik i vratiti se na početnu stranicu?'
			},
			createAccount: {
				title: 'Stvori novi račun',
				label: 'Privatna oznaka',
				wallet: 'Novčanik',
				password: 'Lozinka novčanika',
				successMessage: 'Račun {{1}} {{#2}}({{2}}){{/2}} je uspješno stvoren!',
				create: 'Stvori'
			},
			createRealAccountData: {
				title: 'Stvori stvarni račun',
				message: 'Dolje navedeni podaci su za vaš stvarni račun nakon što se NEM pokrene. Spremite adresu javni i privatni ključ negdje na sigurno. Ako izgubite privatni ključ vaš račun i svi stvarni XEM-ovi biti će izgubljeni ZAUVIJEK!',
				address: 'Adresa',
				publicKey: 'Javni ključ',
				privateKey: 'Privatni ključ',
				confirm: {
					title: 'Spremi privatni ključ',
					message: 'Jeste li sigurni da je vaš privatni ključ spremljen na sigurno mjesto?'
				},
				recheck: {
					title: 'Ponovno provjerite vaš spremljeni privatni ključ',
					message: 'Molim vas unesite privatni ključ koji vam je nedavno dodijeljen kako bi provjerili da ste ga ispravno premili. Ako ste već izgubili privatni ključ, možda želite stvoriti novi?',
					correct: {
						title: 'Lijepo!',
						message: 'Čini se da imate spremljen ispravan privatni ključ. Molimo zapamtite da privatni ključ uvijek čuvate na sigurnom.'
					},
					incorrect: {
						title: 'Hmm...',
						message: 'Privatni ključ koji ste unijeli nije ispravan. Da li želite ponovo pokušati unijeti privatni ključ ili pregledati originalne podatke računa?',
						tryAgain: 'Pokušajte ponoviti unos',
						seeOriginal: 'Pogledaj originalne podatke'
					},
					recheck: 'Provjeri'
				},
				ok: 'OK'
			},
			verifyRealAccountData: {
				title: 'Provjeri stvarne podatke računa',
				message: 'Ponovno unesite spremljenu adresu, javni i privatnji ključ radi provjere ispravnosti',
				address: 'Adresa',
				publicKey: 'Javni ključ',
				privateKey: 'Privatni ključ',
				dataMatched: 'Sve izgleda uredu, adresa, javni i privatni ključ koji ste unijeli se poklapaju.',
				verify: 'Provjeri'
			},
			addAccount: {
				title: 'Dodaj postojeći račun',
				privateKey: 'Privatni ključ računa',
				wallet: 'Novčanik',
				password: 'Lozinka novčanika',
				successMessage: 'Račun {{1}} {{#2}}({{2}}){{/2}} je uspješno dodan u novčanik!',
				add: 'Dodaj',
				label: 'Oznaka'
			},
			setPrimary: {
				title: 'Postavi primarni račun',
				account: 'Račun koji će biti postavljen kao primarni',
				noLabel: '<span class=\'null\'>&lt;Nema oznake&gt;</span>',
				wallet: 'Novčanik',
				password: 'Lozinka novčanika',
				successMessage: 'Račun {{1}} {{#2}}({{2}}){{/2}} je postavljen kao primarni!',
				set: 'Postavi kao primarni'
			},
			changeWalletName: {
				title: 'Izmijeni naziv novčanika',
				wallet: 'Trenutni naziv novčanika',
				newName: 'Novi naziv novčanika',
				password: 'Lozinka novčanika',
				successMessage: 'Naziv novčanika je uspješno izmijenjen iz <em>{{1}}</em> u <em>{{2}}</em>',
				change: 'Izmijeni'
			},
			changeWalletPassword: {
				title: 'Izmijeni lozinku novčanika',
				wallet: 'Novčanik',
				password: 'Trenutna lozinka',
				newPassword: 'Nova lozinka',
				confirmPassword: 'Potvrdi novu lozinku',
				successMessage: 'Lozinka novčanika je uspješno izmijenjena',
				change: 'Izmijeni',
				passwordNotMatchTitle: 'Oops!',
				passwordNotMatchMessage: 'Unesena lozinka i potvrda lozinke se ne podudaraju. Molimo Vas da upišete novu lozinku ispravno.'
			},
			changeAccountLabel: {
				title: 'Izmijeni oznaku računa',
				label: 'Oznaka računa',
				wallet: 'Novčanik',
				password: 'Lozinka novčanika',
				successMessage: 'Račun {{1}} je sad označen kao {{2}}',
				change: 'Izmijeni'
			},
			removeAccount: {
				title: 'Ukloni račun',
				wallet: 'Pridruženi novčanik',
				password: 'Lozinka novčanika',
				warning: 'Molim Vas da prije uklanjanja računa provjerite da isti ne sadrži XEM ili će iznos biti izgubljen zauvijek nakon uklanjanja računa.',
				successMessage: 'Račun {{1}} {{#2}}({{2}}){{/2}} je uspješno uklonjen!',
				remove: 'Ukloni'
			},
			nisUnavailable: {
				title: 'NIS nedostupan',
				message: 'Odspojen od NIS-a, čekanje na vezu'
			},
			shutdown: {
				title: 'Zatvori program',
				message: 'Jeste li sigurni za želite zatvoriti NEM Community Client?'
			},
			activateRemote: {
				title: 'Aktiviraj udaljeno ubiranje',
				wallet: 'Novčanik',
				account: 'Račun',
				hoursDue: 'Vrijedi (sati)',
				password: 'Lozinka novčanika',
				activate: 'Pokreni'
			},
			deactivateRemote: {
				title: 'Deaktiviraj udaljeno ubiranje',
				wallet: 'Novčanik',
				account: 'Račun',
				hoursDue: 'Vremensko razdoblje (sati)',
				password: 'Lozinka novčanika',
				deactivate: 'Deaktiviraj'
			},
			startRemote: {
				title: 'Pokreni udaljeno ubiranje',
				wallet: 'Novčanik',
				account: 'Račun',
				password: 'Lozinka novčanika',
				start: 'Start'
			},
			stopRemote: {
				title: 'Prekini udaljeno ubiranje',
				wallet: 'Novčanik',
				account: 'Račun',
				password: 'Lozinka novčanika',
				stop: 'Stop'
			},
			logoutWarning: {
				leavePage: 'Napuštate vaš novčanik. Ako napustite vaš novčanik na takav način netko drugi će još uvijek moći pristupiti vašem novčaniku s tog računala.\n\nMolimo vas da se prije zatvaranje kartice ili nastavka surfanje odjavite koristeći stavku \'Zatvori novčanik\' u padajućem izborniku gore desno.\n'
			},
			addContact: {
				title: 'Dodaj kontakt',
				add: 'Dodaj'
			},
			editContact: {
				title: 'Uredi kontakt',
				saveChanges: 'Spremi promjene'
			},
			removeContact: {
				title: 'Ukloni kontakt',
				remove: 'Ukloni'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'Novčanik je uspješno uvezen!',
			nav: {
				start: 'Početak',
				about: 'O NEM',
				settings: 'Postavke'
			},
			main: {
				leftTitle: 'Novi <em>NEM</em> korisnik?',
				leftButton: 'Stvori novi novčanik',
				walletNamePlh: 'Naziv Vašeg novčanika',
				passwordPlh: 'Lozinka',
				confirmPasswordPlh: 'Potvrdi lozinku',
				create: 'Stvori',
				creating: 'Stvaranje...',
				rightTitle: 'Već si <em>NEM</em>ber?',
				rightButton: 'Otvori novčanik',
				openButton: 'Otvori',
				walletsFound: 'Pronađeno <strong>{{1}}</strong> <em>novčanik(a)</em>',
				copyright: 'Fotografiju ustupio  <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC šifrira vaš novčanik',
						description: '<em>Sigurnost</em> je za NEM  jako važna za sprječavanje krađe XEM novčića &amp; udjela.'
					},
					{
						title: 'NCC šifrira vaš novčanik',
						description: '<em>Sigurnost</em> je za NEM jako važna za sprječavanje krađe XEM novčića &amp; udjela.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'Kako radi NCC?',
						paragraphs: [
							'<strong>NCC</strong> pruža pristup Vašim udjelima i XEM novčićima kao što to čini običan novčanik. Možete',
							'<strong>NCC</strong> zahtijeva pristup na <strong>NIS</strong> poslužitelj kako bi mogao funkcionirati. Standardno je imati aktivan lokalni poslužitelj (instalira se zajedno sa <strong>NCC</strong>-om)',
							'Moguće je također podesiti pristup na udaljeni <strong>NIS</strong> poslužitelj.'
						],
						listItems: [
							'Koristiti više novčanika',
							'Definirati više računa koji će biti uključeni u novčanik'
						]
					},
					{
						title: 'Što je to &#42;NIS?',
						paragraphs: [
							'Ova komponenta je odgovorna za održavanje <strong>NEM</strong> oblaka.',
							'Što je više <strong>NIS</strong> poslužitelja to je bolja sigurnost.',
							'<strong>NIS</strong> je pristupna točka <strong>NEM</strong> oblaku.'
						],
						legend: '<strong>&#42;NIS</strong> je oznaka za <strong>NEM Infrastructure Server</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2015. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: 'Otprilike prije {{1}} dan(a)',
			lastAccessJustNow: 'Upravo sad',
			lastAccessTooltip: 'Zadnji pristup na {{1}}',
			primary: 'primarni',
			primaryShort: 'P',
			noLabel: '<Nema oznake>',
			copiedToClipboard: 'Adresa je kopirana u međuspremnik!',
			actions: {
				refreshInfo: 'Osvježi informacije',
				bootLocalNode: 'Pokreni lokalni čvor',
				changeWalletName: 'Izmijeni naziv novčanika',
				changeWalletPassword: 'Izmijeni lozinku novčanika',
				mergeWallets: 'Spoji novčanike',
				exportWallet: 'Izvezi novčanike',
				createAccount: 'Stvori novi račun',
				createRealAccountData: 'Stvori podatke za stvarni račun',
				verifyRealAccountData: 'Provjeri podatke za stvarnog računa',
				addAccount: 'Dodaj postojeći račun',
				changeAccountLabel: 'Izmijeni oznaku računa',
				setPrimary: 'Postavi kao primarni račun',
				removeAccount: 'Ukloni račun',
				clientInfo: 'Informacije klijenta',
				closeWallet: 'Zatvori novčanik',
				closeProgram: 'Zatvori  program',
				copyClipboard: 'Kopiraj adresu u međuspremnik',
				convertMultisig: 'Convert other account to multisig'
			},
			nav: [
				'Kontrolna ploča',
				'Poruke',
				'Adresar',
				'Transakcije',
				'Ubrani blokovi',
				'Razmjena udjela',
				'Novosti',
				'Aplikacije',
				'Računi',
				'Postavke',
				'Zatvori program'
			],
			bootNodeWarning: 'Lokalni čvor mora biti pokrenut prije nego što u potpunosti  možete iskoristiti sva NCC svojstva.'
		},
		dashboard: {
			assets: {
				title: 'Vaši udjeli'
			},
			importance: {
				title: 'Razina važnosti',
				unknown: 'Nepoznat status',
				start: 'Pokreni ubiranje',
				harvesting: 'Ubiranje',
				stop: 'Prekini ubiranje',
				description: 'Važnost računa u NEM oblaku',
				remoteHarvest: {
					activate: 'Aktiviraj udaljeno ubiranje',
					activating: 'Aktivacija udaljenog ubiranja...',
					active: 'Udaljeno ubiranje je aktivno',
					deactivate: 'Deaktiviraj udaljeno ubiranje',
					deactivating: 'Deaktivacija udaljenog biranja...',
					startRemoteHarvesting: 'Započni udaljeno ubiranje',
					remotelyHarvesting: 'Udaljeno ubiranje',
					stopRemoteHarvesting: 'Prekini udaljeno ubiranje'
				}
			},
			transactions: {
				title: 'Nedavne transakcije',
				sendNem: 'Šalji XEM',
				signMultisig: 'POTPIŠI',
				balance: 'Trenutno stanje',
				vestedBalance: 'Osigurani iznos',
				syncStatus: '(na bloku {{1}}{{#2}} : otprilike {{3}} dan(a) u zaostatku {{/2}})',
				unknown: 'nepoznato',
				columns: [
					'',
					'Vrijeme',
					'Pošiljatelj/Primatelj',
					'Poruka',
					'',
					'Detalji',
					'Potvrde',
					'Naknada',
					'Iznos'
				],
				noMessage: 'Nema poruke',
				encrypted: 'Poruka je šifrirana',
				view: 'Pregled',
				confirmationsUnknown: 'Nepoznato',
				pending: 'U tijeku',
				seeAll: 'Pogledaj sve transakcije',
				noTransactions: 'Niti jedna transakcija nije još izvršena'
			},
			nemValue: {
				title: 'XEM statistika vrijednosti'
			},
			messages: {
				titleTooltip: 'Poruke'
			},
			news: {
				titleTooltip: 'Novosti'
			},
			notAvailable: 'Nije još dostupno u beta izdanju'
		},
		transactions: {
			title: 'Transakcije',
			sendNem: 'Šalji XEM',
			balance: 'Trenutno stanje',
			filters: {
				confirmed: 'Potvrđene',
				unconfirmed: 'Nepotvrđene',
				incoming: 'Dolazne',
				outgoing: 'Odlazne'
			},
			table: {
				columns: [
					'',
					'Vrijeme',
					'Pošiljatelj/Primatelj',
					'Poruka',
					'',
					'Detalji',
					'Potvrde',
					'Naknada',
					'Iznos'
				],
				noMessage: 'Nema poruke',
				encrypted: 'Poruka je šifrirana',
				view: 'Pregled',
				confirmationsUnknown: 'Nepoznato',
				pending: 'U tijeku',
				noTransactions: 'Još nema izvršenih transakcija',
				loading: 'Učitavanje više transakcija...'
			}
		},
		harvestedBlocks: {
			title: 'Ubrani blokovi',
			feeEarned: 'Zarađene naknade od zadnjih 25 ubranih blokova',
			unknown: 'Nepoznato',
			table: {
				columns: [
					'Visina',
					'Vrijeme',
					'Težina bloka',
					'Naknada'
				],
				noBlocks: 'Nema ubranih blokova',
				loading: 'Učitavam više ubranih blokova'
			},
			harvesting: {
				unknown: 'Nepoznat status',
				start: 'Pokreni ubiranje',
				harvesting: 'Ubiranje',
				stop: 'Prekini ubiranje',
				remoteHarvest: {
					startRemoteHarvesting: 'Pokreni udaljeno ubiranje',
					stopRemoteHarvesting: 'Prekini udaljeno ubiranje'
				}
			}
		},
		addressBook: {
			title: 'Adresar',
			addContact: 'Dodaj kontak',
			table: {
				columns: [
					'Adresa računa',
					'Privatna oznaka',
					'Javna oznaka'
				],
				noContacts: 'Nema unešenih kontakata u vašem adresaru'
			},
			noLabel: 'Bez oznake',
			sendNem: 'Šalji XEM',
			edit: 'Uredi',
			remove: 'Ukloni'
		},
		settings: {
			title: 'Postavke',
			settings: [
				{
					name: 'Jezik'
				}
			],
			save: 'Spremi izmjene',
			saveSuccess: 'Postavke su uspješno spremljene'
		}
	}
});
