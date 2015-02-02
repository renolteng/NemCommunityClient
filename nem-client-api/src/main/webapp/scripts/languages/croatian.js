define({
	id: "hr",
	name: "hrvatski",
	texts: {
		preferences: {
			thousandSeparator: " ",
			decimalSeparator: "."
		},
		faults: {
			101: 'The wallet file does not exist.',
			102: "Novčanik nije stvoren.",
			103: 'Wallet file is corrupt. Please recover your wallet from a backup.',
			104: 'The provided password for the wallet is not correct.',
			105: 'No password was provided for the wallet.',
			106: "Novčanik mora biti otvoren da biste ga mogli koristiti. Morate unijeti lozinku za novčanik da biste bili sigurni da ste ovlašteni za korištenje istog.",
			107: "Novčanik ne sadrži taj račun.",
			108: "Nemoguće je ukloniti račun jer je iznos na njemu veći od 0 XEM-ova ili jer pokušavate ukloniti primarni račun.",
			109: "Drugi novčanik sa istim nazivom već postoji. Molim Vas izaberite drugi naziv za novčanik.",
			110: "Novčanik već sadrži taj račun.",
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
			202: "Šifrirana se poruka nije mogla poslati jer primatelj nije nikad napravio niti jednu transakciju.",
			305: "NEM infrastrukturni poslužitelj nije dostupan.",
			306: "Došlo je do pogreške koju razvojni tim nije predvidio. Ispričavamo se zbog toga. Novi pokušaj bi mogao pomoći, u suprotnom molim Vas pošaljite upit NEM NIS/NCC zajednici.",
			400: "Neki parametar nedostaje ili je nevažeći.",
			401: "Ova operacije nije mogla biti sprovedena zato što bi moglo doći do otkrivanja privatnog kluča slanjem na NIS.",
			404: "Zatraženi resurs nije mogao biti pronađen.",
			500: "Došlo je do pogreške koju razvojni tim nije predvidio. Ispričavamo se zbog toga. Ponovno pokretanje programa bi moglo pomoći u suprotnom molimo vas da grešku prijavite NEM NIS/NCC zajednicom.",
			600: "Za slanje i primanje transakcija sa NEM oblaka, NCC zahtijeva da NIS poslužitelj bude pokrenut. Molim Vas da u NCC izborniku koristite stavku za pokretanje lokalnog čvora.",
			601: "NIS čvor je već pokrenut. Sljedeći pokušaj za pokretanje NIS čvora nije moguć.",
			699: 'Maximum number of harvesters allowed on server has been reached.',
			700: "Navedeni račun ne ispunjava osnovne kriterije za ubiranje blokova. Za ubiranje blokova račun mora sadržavati najmanje 1000 XEM-ova.",
			701: "Datum isteka je u prošlosti. Datum isteka mora biti u roku od jednog dana.",
			702: "Datum isteka je predaleko u budućnosti. Datum isteka mora biti u roku od jednog dana.",
			703: "Stanje na Vašem računu nije dovoljno za slanje određenog iznosa NEMa.",
			704: "Tekst u poruci je predugačak. Molim Vas, probajte smanjiti dužinu teksta u Vašoj poruci ako ju želite slati.",
			705: "Šifra transakcije već postoji u bazi podataka ili u listi nepotvrđenih transakcija.",
			706: "Potpis transakcije nije mogao biti provjeren.",
			707: "Vremenska oznaka transakcije je predaleko u prošlosti.",
			708: "Vremenska oznaka transakcije je predaleko u budućnosti.",
			709: "Račun je nepoznat. Račun se mora pojaviti barem u jednoj transakciji (pošiljatelja ili primatelja) da bi bio prepoznat u mreži.",
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
			901: "Došlo je do pogreške kod postavljanja u izvanmrežni način rada.",
			1000: "Privatni i javni ključ koji ste unijeli se ne poklapaju.",
			1001: "Javni ključ i adresa koju ste unijeli se ne poklapaju.",
			1002: "Adresa ne pripada glavnoj mreži."
		},
		common: {
			success: "Uspjeh",
			appStatus: {
				nccUnknown: "NCC status je nepoznat",
				nccUnavailable: "NCC je nedostupan",
				nccStarting: "NCC se pokreće...",
				nisUnknown: "NIS status je nepoznat",
				nisUnavailable: "NIS je nedostupan",
				nisStarting: "NIS se pokreće...",
				notBooted: "NIS mora biti pokrenut. Molim vas otvorite vaš novčanik i pokrenite lokalni čvor preko skočnog prozora ili podesite postavke automatsko pokretanje.",
				booting: "Pokrećem NIS...",
				nisInfoNotAvailable: "NIS informacije nisu još dostupne. Pokušavam prikupiti informacije NIS-a...",
				synchronizing: "NIS sinkronizacija. Na bloku {{1}}, otprilike {{2}} u zaostatku.",
				daysBehind: {
					0: "manje od 1 dan",
					1: "1 dan",
					many: "{{1}} dana"
				},
				synchronized: "NIS je sinkroniziran!",
				noRemoteNisAvailable: 'No remote NIS found in the network, disconnected from internet?'
			},
			addressBook: 'Address book',
			password: "Lozinka",
			passwordValidation: 'Password must not be blank',
			address: "Adresa",
			privateLabel: "Privatna oznaka",
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
			pending: "Transakcija u tijeku",
			outgoing: "Odlazna transakcija",
			incoming: "Dolazna transakcija",
			self: "Vlastita transakcija",
			importance: 'Importance transaction',
			modification: 'Aggregate Modification of Multisig'
		},
		modals: {
			error: {
				title: "Oops!",
				caption: "GREŠKA {{1}}"
			},
			confirmDefault: {
				yes: "Da",
				no: "Ne"
			},
			settings: {
				title: "Postavke",
				language: {
					label: "Jezik"
				},
				remoteServer: {
					tabTitle: "Udaljeni poslužitelj",
					protocol: "Protokol",
					protocolOptions: {
						http: "HTTP"
					},
					host: "Host",
					port: "Port"
				},
				autoBoot: {
					tabTitle: "Automatsko pokretanje",
					name: "Naziv čvora",
					account: "Račun",
					primaryAccount: "Primarni račun",
					auto: "Samostalno pokretanje prilikom otvaranja novčanika"
				},
				save: "Spremi",
				saveSuccess: "Postavke su uspješno spremljene"
			},
			multisig: {
				title: 'Convert account to multisig',
				multisigAccount: 'Multisig account',
				cosignatories: "Cosignatories' addresses",
				labelDesc: "Ovaj račun je označen kao {{1}}",
				nullLabelDesc: "Ovaj račun nema oznake",
				addCosignatory: '+ Add Cosignatory',
				cancel: "Odustani",
				convert: 'Convert',
				fee: "Naknada",
				feeValidation: 'Fee must not be less than the minimum fee',
				dueBy: "Zbog",
				useMinimumFee: 'Use minimum fee',
				hours: "sat(i)",
				txConfirm: {
					title: 'Confirm Conversion to Multisig Account',
					total: "Ukupno",

				},

			},
			signMultisig: {
				title: 'Sign multisig transaction',
				original: {
					from: 'Multisig account',
					to: "Primatelj",
					amount: "Iznos",
					fee: 'Inner Fee',
					deadline: 'Deadline'
				},
				multisigFees: 'Multisig Fees',
				multisigTotal: "Ukupno",
				sender: 'Cosignatory',
				fee: "Naknada",
				feeValidation: 'Fee must not be less than the minimum fee',
				dueBy: "Zbog",
				useMinimumFee: 'Use minimum fee',
				hours: "sat(i)",
				password: "Lozinka",
				passwordValidation: 'Password must not be blank',
				send: "Šalji",
				cancel: "Odustani",
				sending: "Šaljem...",
				successMessage: "Transakcija je uspješno provedena!",
				txConfirm: {
					title: 'Confirm Multisig Transaction',
					message: "poruke",
					encrypted: "Poruka je šifrirana",
					noMessage: "Nema poruke",

				},

			},
			sendNem: {
				title: "Slanje XEMa",
				sender: "Pošiljatelj",
				thisAccount: 'This account',
				labelDesc: "Ovaj račun je označen kao {{1}}",
				nullLabelDesc: "Ovaj račun nema oznake",
				amount: "Iznos",
				recipient: "Račun primatelja",
				recipientValidation: 'Account addresses must be 40 character long excluding dashes',
				message: "Poruka",
				encrypt: "Šifriraj poruku",
				fee: "Naknada",
				multisigFee: 'Multisig fee',
				feeValidation: 'Fee must not be less than the minimum fee',
				dueBy: "zbog",
				useMinimumFee: 'Use minimum fee',
				hours: "sati",
				password: "Lozinka",
				passwordValidation: 'Password must not be blank',
				send: "Šalji",
				cancel: 'Cancel',
				sending: "Slanje...",
				successMessage: "Transakcija je uspješno provedena!",
				txConfirm: {
					title: "Potvrdi transakciju",
					amount: "Iznos",
					to: "Na",
					dueBy: "Zbog",
					hours: "sat(i)",
					total: "Ukupno",
					message: "Poruka",
					encrypted: "Poruka je šifrirana",
					noMessage: "Nema poruke",
					cancel: "Odustani",
					confirm: "Potvrdi",
					sending: "Šaljem..."
				},
				notBootedWarning: {
					title: "Čvor nije pokrenut",
					message: "Za slanje NEM-a lokalni čvor mora biti prije pokrenut. "
				},
				bootingWarning: {
					title: "Čvor se pokreće",
					message: "Molimo vas pričekajte prije slanja transakcije da se proces pokretanja čvora završi."
				}
			},
			clientInfo: {
				title: "Informacije klijenta",
				ncc: "NEM Community Client - NCC",
				signer: "Potpisnik",
				remoteServer: "Udaljeni poslužitelj",
				local: "Lokalno",
				nis: "NEM Infrastructure Server - NIS",
				sync: "Sinkroniziran",
				notSync: "Nije sinkroniziran",
				notConnected: "Nije spojen na NEM oblak",
				loading: "Učitavanje..."
			},
			transactionDetails: {
				title: "Detalji transakcije",
				id: "ID",
				hash: "Hash",
				type: "Tip transakcije",
				direction: 'Transaction Direction',
				pending: "U tijeku",
				outgoing: "Odlazno",
				incoming: "Dolazno",
				self: "Self",
				sender: "Pošiljatelj",
				multisigAccount: 'Multisig Account',
				issuer: 'Issuer',
				recipient: "Primatelj",
				remote: 'Remote',
				multisigMessage: 'Signatures present',
				message: "Poruka",
				noMessage: "Nema poruka",
				encrypted: "Poruka je šifrirana",
				time: "Vremenski zapis",
				confirmations: "Potvrde",
				confirmationsUnknown: 'Unknown',
				amount: "Iznos",
				fee: "Naknada",
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
				title: "Pokreni lokalni čvor",
				account: "Račun za pokretanje lokalnog čvora",
				noLabel: "<span class=\"null\">&lt;Nema oznake&gt;</span>",
				wallet: "Novčanik",
				node: "Naziv čvora",
				boot: "Pokreni",
				booting: "Pokretanje..."
			},
			closeWallet: {
				title: "Zatvori novčanik",
				message: "Jeste li sigurni da želite zatvoriti novčanik i vratiti se na početnu stranicu?"
			},
			createAccount: {
				title: "Stvori novi račun",
				label: "Privatna oznaka",
				wallet: "Novčanik",
				password: "Lozinka novčanika",
				successMessage: "Račun {{1}} {{#2}}({{2}}){{/2}} je uspješno stvoren!",
				create: "Stvori"
			},
			createRealAccountData: {
				title: "Stvori stvarni račun",
				message: "Dolje navedeni podaci su za vaš stvarni račun nakon što se NEM pokrene. Spremite adresu javni i privatni ključ negdje na sigurno. Ako izgubite privatni ključ vaš račun i svi stvarni XEM-ovi bit će izgubljeni ZAUVIJEK!",
				address: "Adresa",
				publicKey: "Javni ključ",
				privateKey: "Privatni ključ",
				confirm: {
					title: "Spremi privatni ključ",
					message: "Jeste li sigurni da je vaš privatni ključ spremljen na sigurno mjesto?"
				},
				recheck: {
					title: "Ponovno provjerite vaš spremljeni privatni ključ",
					message: "Molim vas unesite privatni ključ koji vam je nedavno dodijeljen kako bi provjerili da ste ga ispravno premili. Ako ste već izgubili privatni ključ, možda želite stvoriti novi?",
					correct: {
						title: "Lijepo!",
						message: "Čini se da imate spremljen ispravan privatni ključ. Molimo zapamtite da privatni ključ uvijek čuvate na sigurnom."
					},
					incorrect: {
						title: "Hmm...",
						message: "Privatni ključ koji ste unijeli nije ispravan. Da li želite ponovo pokušati unijeti privatni ključ ili pregledati originalne podatke računa?",
						tryAgain: "Pokušajte ponoviti unos",
						seeOriginal: "Pogledaj originalne podatke"
					},
					recheck: "Provjeri"
				},
				ok: "OK"
			},
			verifyRealAccountData: {
				title: "Provjeri stvarne podatke računa",
				message: "Ponovno unesite spremljenu adresu, javni i privatnji ključ radi provjere ispravnosti",
				address: "Adresa",
				publicKey: "Javni ključ",
				privateKey: "Privatni ključ",
				dataMatched: "Sve izgleda uredu, adresa, javni i privatni ključ koji ste unijeli se poklapaju.",
				verify: "Provjeri"
			},
			addAccount: {
				title: "Dodaj postojeći račun",
				privateKey: "Privatni ključ računa",
				wallet: "Novčanik",
				password: "Lozinka novčanika",
				successMessage: "Račun {{1}} {{#2}}({{2}}){{/2}} je uspješno dodan u novčanik!",
				add: "Dodaj",
				label: "Oznaka"
			},
			setPrimary: {
				title: "Postavi primarni račun",
				account: "Račun koji će biti postavljen kao primarni",
				noLabel: "<span class=\"null\">&lt;Nema oznake&gt;</span>",
				wallet: "Novčanik",
				password: "Lozinka novčanika",
				successMessage: "Račun {{1}} {{#2}}({{2}}){{/2}} je postavljen kao primarni!",
				set: "Postavi kao primarni",

			},
			changeWalletName: {
				title: "Izmijeni naziv novčanika",
				wallet: "Trenutni naziv novčanika",
				newName: "Novi naziv novčanika",
				password: "Lozinka novčanika",
				successMessage: "Naziv novčanika je uspješno izmijenjen iz <em>{{1}}</em> u <em>{{2}}</em>",
				change: "Izmijeni"
			},
			changeWalletPassword: {
				title: "Izmijeni lozinku novčanika",
				wallet: "Novčanik",
				password: "Trenutna lozinka",
				newPassword: "Nova lozinka",
				confirmPassword: "Potvrdi novu lozinku",
				successMessage: "Lozinka novčanika je uspješno izmijenjena",
				change: "Izmijeni",
				passwordNotMatchTitle: "Oops!",
				passwordNotMatchMessage: "Unesena lozinka i potvrda lozinke se ne podudaraju. Molimo Vas da upišete novu lozinku ispravno."
			},
			changeAccountLabel: {
				title: "Izmijeni oznaku računa",
				label: "Oznaka računa",
				wallet: "Novčanik",
				password: "Lozinka novčanika",
				successMessage: "Račun {{1}} je sad označen kao {{2}}",
				change: "Izmijeni"
			},
			removeAccount: {
				title: "Ukloni račun",
				wallet: "Novčanik",
				password: "Lozinka novčanika",
				warning: "Molim Vas da prije uklanjanja računa provjerite da isti ne sadrži XEM ili će biti izgubljen zauvijek nakon uklanjanja računa.",
				successMessage: "Račun {{1}} {{#2}}({{2}}){{/2}} je uspješno uklonjen!",
				remove: "Ukloni"
			},
			nisUnavailable: {
				title: "NIS nedostupan",
				message: "Odspojen od NIS-a, čekanje na vezu"
			},
			shutdown: {
				title: "Zatvori program",
				message: "Jeste li sigurni za želite zatvoriti NEM Community Client?"
			},
			activateRemote: {
				title: "Aktiviraj udaljeno ubiranje",
				wallet: "Novčanik",
				account: "Račun",
				hoursDue: "Hours due",
				password: "Lozinka novčanika",
				activate: "Pokreni"
			},
			deactivateRemote: {
				title: "Deaktiviraj udaljeno ubiranje",
				wallet: "Novčanik",
				account: "Račun",
				hoursDue: "Hours due",
				password: "Lozinka novčanika",
				deactivate: "Deaktiviraj"
			},
			startRemote: {
				title: "Započni udaljeno ubiranje",
				wallet: "Novčanik",
				account: "Račun",
				password: "Lozinka novčanika",
				start: "Start"
			},
			stopRemote: {
				title: "Zaustavi udaljeno ubiranje",
				wallet: "Novčanik",
				account: "Račun",
				password: "Lozinka novčanika",
				stop: "Stop"
			},
			logoutWarning: {
				leavePage: "You're leaving your wallet. Remember that if you leave your wallet this way, some others may still be able to access your wallet from this computer.\n\nTo prevent that from happening, please log out using the \"Close wallet\" menu item in the top-right dropdown menu before you close the browser tab or navigate away.",

			},
			addContact: {
				title: 'Add contact',
				add: "Dodaj"
			},
			editContact: {
				title: 'Edit contact',
				saveChanges: "Spremi izmjene",

			},
			removeContact: {
				title: 'Remove contact',
				remove: "Ukloni",

			}
		},
		landing: {
			logo: "images/nem_logo.png",
			importSuccess: "Novčanik je uspješno uvezen!",
			nav: {
				start: "Početak",
				about: "O XEM",
				settings: "Postavke"
			},
			main: {
				leftTitle: "Novi <em>NEM</em> korisnik?",
				leftButton: "Stvori novi novčanik",
				walletNamePlh: "Naziv Vašeg novčanika",
				passwordPlh: "Lozinka",
				confirmPasswordPlh: 'Confirm password',
				create: "Stvori",
				creating: 'Creating...',
				rightTitle: "Već si <em>NEM</em>ber?",
				rightButton: "Otvori novčanik",
				openButton: "Otvori",
				walletsFound: "Pronađeno <strong>{{1}}</strong> <em>novčanik(a)</em>",
				copyright: "Fotografiju ustupio  <em>Cas Cornelissen</em>"
			},
			carousel: {
				items: [
					{
						title: "NCC šifrira vaš novčanik",
						description: "<em>Sigurnost</em> je za NEM  jako važna za sprječavanje krađe XEM novčića &amp; udjela."
					},
					{
						title: "NCC šifrira vaš novčanik",
						description: "<em>Sigurnost</em> je za NEM jako važna za sprječavanje krađe XEM novčića &amp; udjela."
					}
				]
			},
			about: {
				sections: [
					{
						title: "Kako radi NCC?",
						paragraphs: [
							"<strong>NCC</strong> pruža pristup Vašim udjelima i NEM novčićima kao što to čini tradicionalni novčanik. Možete",
							"<strong>NCC</strong> zahtijeva pristup na <strong>NIS</strong> poslužitelj kako bi mogao funkcionirati. Standardno je imati aktivan lokalni poslužitelj (instalira se zajedno sa <strong>NCC</strong>-om)",
							"Moguće je također podesiti pristup na udaljeni <strong>NIS</strong> poslužitelj."
						],
						listItems: [
							"Koristiti više novčanika",
							"Definirati više računa koji će biti uključeni u novčanik"
						]
					},
					{
						title: "Što je to &#42;NIS?",
						paragraphs: [
							"Ova komponenta je odgovorna za održavanje <strong>NEM</strong> oblaka.",
							"Što je više <strong>NIS</strong> poslužitelja to je bolja sigurnost.",
							"<strong>NIS</strong> je pristupna točka <strong>NEM</strong> oblaku."
						],
						legend: "<strong>&#42;NIS</strong> je oznaka za <strong>NEM Infrastructure Server</strong>"
					}
				]
			},
			footer: {
				copyright: "&copy; Copyright 2015. NEM Community Client."
			}
		},
		wallet: {
			logo: "images/nem_logo.png",
			lastAccess: "Otprilike prije {{1}} dan(a)",
			lastAccessJustNow: "Upravo sad",
			lastAccessTooltip: "Zadnji pristup na {{1}}",
			primary: "primarni",
			primaryShort: "P",
			noLabel: "<Nema oznake>",
			copiedToClipboard: "Adresa je kopirana u međuspremnik!",
			actions: {
				refreshInfo: "Osvježi informacije",
				bootLocalNode: "Pokreni lokalni čvor",
				changeWalletName: "Izmijeni naziv novčanika",
				changeWalletPassword: "Izmijeni lozinku novčanika",
				mergeWallets: "Spoji novčanike",
				exportWallet: "Izvezi novčanike",
				createAccount: "Stvori novi račun",
				createRealAccountData: "Stvori podatke za stvarni račun",
				verifyRealAccountData: "Provjeri podatke za stvarnog računa",
				addAccount: "Dodaj postojeći račun",
				changeAccountLabel: "Izmijeni oznaku računa",
				setPrimary: "Postavi kao primarni račun",
				removeAccount: "Ukloni račun",
				clientInfo: "Informacije klijenta",
				closeWallet: "Zatvori novčanik",
				closeProgram: "Zatvori  program",
				copyClipboard: "Kopiraj adresu u međuspremnik",
				convertMultisig: 'Convert to multisig'
			},
			nav: [
				"Kontrolna ploča",
				"Poruke",
				'Address Book',
				"Transakcije",
				"Ubrani blokovi",
				"Razmjena udjela",
				"Novosti",
				"Aplikacije",
				"Računi",
				"Postavke",
				"Zatvori program"
			],
			bootNodeWarning: "Lokalni čvor mora biti pokrenut prije nego što u potpunosti  možete iskoristiti sva NCC svojstva."
		},
		dashboard: {
			assets: {
				title: "Vaši udjeli"
			},
			importance: {
				title: "Razina važnosti",
				unknown: "Nepoznat status",
				start: "Pokreni ubiranje",
				harvesting: "Ubiranje",
				stop: "Prekini ubiranje",
				description: "Važnost računa u NEM oblaku",
				remoteHarvest: {
					activate: "Aktiviraj udaljeno ubiranje",
					activating: "Aktivacija...",
					active: "Udaljeno ubiranje je aktivno",
					deactivate: "Deaktiviraj udaljeno ubiranje",
					deactivating: "Deaktivacija...",
					startRemoteHarvesting: "Započni udaljeno ubiranje",
					remotelyHarvesting: "Udaljeno ubiranje",
					stopRemoteHarvesting: "Prekini udaljeno ubiranje"
				}
			},
			transactions: {
				title: "Nedavne transakcije",
				sendNem: "Šalji XEM",
				signMultisig: 'SIGN',
				balance: "Trenutno stanje",
				syncStatus: "(na bloku {{1}}{{#2}} : otprilike {{3}} dan(a) u zaostatku {{/2}})",
				unknown: "nepoznato",
				columns: [
					"",
					"Vrijeme",
					"Pošiljatelj/Primatelj",
					"Poruka",
					"",
					"Detalji",
					"Potvrde",
					"Naknada",
					"Iznos"
				],
				noMessage: "Nema poruke",
				encrypted: "Poruka je šifrirana",
				view: "Pregled",
				confirmationsUnknown: 'Unknown',
				pending: "U tijeku",
				seeAll: "Pogledaj sve transakcije",
				noTransactions: "Niti jedna transakcija nije još izvršena"
			},
			nemValue: {
				title: "XEM statistika vrijednosti"
			},
			messages: {
				titleTooltip: "Poruke"
			},
			news: {
				titleTooltip: "Novosti"
			},
			notAvailable: "Nije još dostupno u beta izdanju"
		},
		transactions: {
			title: "Transakcije",
			sendNem: "Šalji XEM",
			balance: "Trenutno stanje",
			filters: {
				confirmed: "Potvrđene",
				unconfirmed: "Nepotvrđene",
				incoming: "Dolazne",
				outgoing: "Odlazne",

			},
			table: {
				columns: [
					"",
					"Vrijeme",
					"Pošiljatelj/Primatelj",
					"poruke",
					"",
					"Detalji",
					"Potvrde",
					"Naknada",
					"Iznos"
				],
				noMessage: "Nema poruke",
				encrypted: "Poruka je šifrirana",
				view: "Pregled",
				confirmationsUnknown: 'Unknown',
				pending: "U tijeku",
				noTransactions: "Još nema izvršenih transakcija",
				loading: "Učitavanje više transakcija..."
			}
		},
		harvestedBlocks: {
			title: "Ubrani blokovi",
			feeEarned: "Zarađene naknade od zadnjih 25 ubranih blokova",
			unknown: 'Unknown',
			table: {
				columns: [
					"Visina",
					"Vrijeme",
					"Šifra bloka",
					"Naknada"
				],
				noBlocks: "Nema ubranih blokova",
				loading: "Učitavam više ubranih blokova"
			},
			harvesting: {
				unknown: "Nepoznat status",
				start: "Pokreni ubiranje",
				harvesting: "Ubiranje",
				stop: "Prekini ubiranje",
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
			sendNem: "Šalji XEM",
			edit: 'Edit',
			remove: "Ukloni"
		},
		settings: {
			title: "Postavke",
			settings: [
				{
					name: "Jezik"
				}
			],
			save: "Spremi izmjene",
			saveSuccess: "Postavke su uspješno spremljene"
		}
	}
});
