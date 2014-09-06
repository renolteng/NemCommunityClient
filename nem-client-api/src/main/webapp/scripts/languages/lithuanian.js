define({
	id: 'lt',
	name: 'Lietuviškai',
	texts: {
		faults: {
            101: 'Byla nerasta.',
            102: 'Piniginė nesukurta.',
            103: 'Piniginės byla yra sugadinta. Prašome atstatyti Jūsų piniginę iš atsarginės kopijos, kurią Jūs turėjote susikurti, kai kurėte naują piniginę arba pridėjote naują saskaitą į ją.',
            104: 'Pateiktas slaptažodis netinka. Tikėkimes Jūs galite atsiminti teisingą slaptažodį. Jei jį praradote, jis negali būti atkurtas!',
            106: 'Prieš pradedant darbą su pinigine, ji turi būti atidaryta. Norint atidaryti piniginę, Jūs turite įvesti piniginės slaptažodį.',
            107: 'Pinigineje nėra šios sąskaitos',
            108: 'Sąskaita negali būti pašalinta. Panašu, kad sąskaitoje yra daugiau kaip 0 NEM arba ši sąskaita yra Jūsų pagrindinė sąskaita.',
            109: 'Jau egzistuoja piniginė su tokiu pavadinimu. Prašome pasirinkti kitą piniginės pavadinimą.',
            110: 'Piniginėje jau yra ši sąskaita.',
            202: 'Nėra viešojo rakto',
            305: 'NEM Infrastruktūros serveris negalimas',
            306: 'Įvyko klaida, kurios kūrėjai nenumatė. Atsiprašome, bet gal paleidimas iš naujo padės. Kitu atveju, praneškite apie tai NEM NIS/NCC bendruomenei',
            400: 'Trūksta kai kurių parametrų',
            404: 'Netinkama Boot strategy reikšmė',
            500: 'Nepavyko užsaugoti konfigūracijos bylos',
            600: 'NCC reikia, kad NIS serveris būtų paleistas, norint atlikti siuntimo ir gavimo transakcijas. Prašome naudoti NCC meniu ir paleisti vietinį prieigos tašką.',
            601: 'NIS prieigos taškas jau paleistas. Antrą kartą jį paleisti neįmanoma.',
            700: 'Pateikta sąskaita neatitinka pagrindinių kriterijų, norint "kasti". Pagrinde, tai susiję su NEM kiekiu Jūsų sąskaitoje. "Kasimas" prasideda tik turint ne mažiau 1,000 NEM.',
            701: 'Pateiktas galutinis terminas yra praeityje. Galutinis terminas turi būti 1 dienos bėgyje.',
            702: 'Pateiktas galutinis terminas yra per toli ateityje. Galutinis terminas turi būti ne daugiau kaip viena diena.',
            703: 'Jūsų sąskaitoje neužtenka lėšų, kad išsiųsti nurodytą NEM sumą.',
            704: 'Pateikta žinutė yra per didelė, kad ja išsiųsti. Prašome sutrumpinti žinutę, kurią Jūs norite išsiųsti.',
            705: 'Transakcijos "hash" jau yra duomenų bazėje arba nepatvirtintų transakcijų sąaraše.',
            706: 'Transakcijos parašas negali būti patikrintas.',
            707: 'Transakcijos laiko žymė yra per toli praeityje.',
            708: 'Transakcijos laiko žymė yra per toli ateityje.',
            709: 'Sąskaita nežinoma. Sąskaita turi turėti bent vieną transakciją (siuntimo ar gavimo), kad butu žinoma tinkle.',
            901: 'Įvyko klaida nustatant offline režimą.'
        },
        common: {
        	success: 'Pavyko', //title of the Success message modals
        	nisStatus: {
        		unavailable: 'NIS nėra paleistas',
        		notBooted: 'Reikia, kad NIS būtų paleistas. Prašome atidaryti piniginę ir paleisti vietinį prieigos tašką, pasinaudodami atsidariusiu dialogo langu.',
        		synchronizing: 'NIS sinchronizuojasi. Dabartinis blokas yra {{1}}, liko dar {{2}} .',
                daysBehind: {
                    0: 'mažiau kaip 1 diena',
                    1: '1 diena',
                    many: '{{1}} dienos'
                }
        	}
        },
		modals: {
			error: {
				title: 'Oops!',
				caption: 'KLAIDA {{1}}'
			},
			confirmDefault: {
				yes: 'Taip',
				no: 'Ne'
			},
			sendNem: {
				title: 'Siųsti NEM',
				labelDesc: 'Ši sąskaita pavadinta kaip <strong>{{1}}</strong>',
				nullLabelDesc: "Ši sąskaita neturi pavadinimo",
				amount: 'Suma',
				recipient: "Gavėjo sąskaita",
				message: 'Žinute',
				encrypt: 'Užšifruoti žinutę',
				fee: 'Mokestis',
				dueBy: 'Galiojimo trukmė',
				resetFee: 'Nustatyti iki minimalaus mokesčio',
				hours: 'valandos',
				password: 'Slaptažodis',
				send: 'Siųsti',
				sending: 'Siunčiama...',
				successMessage: 'Transakcija išsiųsta sėkmingai!'
			},
			clientInfo: {
				title: 'Kliento informacija',
				ncc: 'NEM Community Client - NCC',
				signer: 'Parašo turėtojas',
				remoteServer: 'Nuotolinis serveris',
				local: 'Vietinis',
				nis: 'NEM Infrastructure Server - NIS',
				sync: 'Susisinchronizavęs',
				notSync: 'Nesusisinchronizavęs',
				notConnected: 'Neprisijungta prie NEM Cloud',
				loading: 'Kraunasi...'
			},
			transactionDetails: {
				title: 'Transakcijos duomenys',
				// this might be block or transaction ID
				id: 'ID',
				// this might be block or transaction Hash
				hash: 'Hash',
				type: 'Transakcijos tipas',
				pending: 'Kol kas vyksta',
				outgoing: 'Išeinanti',
				incoming: 'Įeinanti',
				self: 'Sau',
				sender: 'Siuntėjas',
				recipient: 'Gavėjas',
				message: 'Žinutė',
				noMessage: 'Nėra žinutės',
				encrypted: 'Žinute yra užšifruota',
				time: 'Laiko žymė',
				confirmations: 'Patvirtinimai',
				amount: 'Suma',
				fee: 'Mokestis'
			},
			bootLocalNode: {
				title: 'Užkrauti vietini prieigos tašką',
				account: 'Vietinio prieigos taško sąskaita',
				noLabel: '<span class="null">&lt;Nėra pavadinimo&gt;</span>',
				wallet: 'Piniginė',
				node: 'Prieigos taško pavadinimas',
				boot: 'Užkrauti',
				booting: 'Kraunasi...'
			},
			notBootedWarning: {
				title: 'Prieigos taškas nepaleistas!',
				message: 'Norint siųsti NEM, vietinis prieigos taškas turi būti paleistas!'
			},
			closeWallet: {
				title: 'Uždaryti piniginę',
				message: 'Ar tikrai norite uždaryti piniginę ir grįžti į pagrindinį puslapį?'
			},
			createAccount: {
				title: 'Sukurti naują sąskaitą',
				label: 'Nuosavas pavadinimas',
				wallet: 'Piniginė',
				password: "Piniginės slaptažodis",
				successMessage: 'Sąskaita {{1}} {{#2}}({{2}}){{/2}} sukurta!',
				create: 'Sukurti'
			},
			addAccount: {
				title: 'Pridėti egzistuojančią saskaitą',
				privateKey: "Sąskaitos privatus raktas",
				wallet: 'Piniginė',
				password: "Piniginės slaptažodis",
				successMessage: 'Sąskaita {{1}} {{#2}}({{2}}){{/2}} įtraukta į piniginę!',
				add: 'Pridėti',
				label: 'Pavadinimas'
			},
			setPrimary: {
				title: 'Nustatyti pagrindinę sąskaitą',
				account: 'Sąskaita, kuri turi būti nustatyta kaip pagrindinė',
				noLabel: '<span class="null">&lt;Nėra pavadinimo&gt;</span>',
				wallet: 'Piniginė',
				password: "Piniginės slaptažodis",
				successMessage: 'Sąskaita {{1}} {{#2}}({{2}}){{/2}} nustatyta kaip pagrindinė!',
				set: 'Nustatyta kaip pagrindinė',
			},
			changeWalletName: {
				title: 'Pakeisti piniginės pavadinimą',
				wallet: 'Dabartinis piniginės pavadinimas',
				newName: 'Naujas piniginės pavadinimas',
				password: "Piniginės slaptažodis",
				successMessage: 'Piniginės pavadinimas pakeistas sėkmingai iš <em>{{1}}</em> į <em>{{2}}</em>',
				change: 'Pakeisti'
			},
			changeWalletPassword: {
				title: 'Pakeisti piniginės slaptažodį',
				wallet: 'Piniginė',
				password: 'Dabartinis slaptažodis',
				newPassword: 'Naujas slaptažodis',
				confirmPassword: 'Patvirtinti naują slaptažodį',
				successMessage: 'Piniginės slaptažodis pakeistas sėkmingai',
				change: 'Pakeisti',
				passwordNotMatchTitle: 'Oops!',
				passwordNotMatchMessage: 'Jūsų įvestas naujas slaptažodis ir jo patvirtinimas neatitinka. Prašome įsitikinti, kad naują slaptažodį įvedėte teisingai.'
			},
			changeAccountLabel: {
				title: 'Pakeisti sąskaitos pavadinimą',
				label: 'Sąskaitos pavadinimas',
				wallet: 'Piniginė',
				password: "Piniginės slaptažodis",
				successMessage: 'Sąskaita {{1}} dabar pavadinta kaip {{2}}',
				change: 'Pakeisti'
			},
			removeAccount: {
				title: 'Panaikinti sąskaitą',
				wallet: 'Piniginė',
				password: "Piniginės slaptažodis",
				warning: 'Prašome įsitikinti, kad Jūsų sąskaitoje nėra NEM prieš ją panaikinant. Panaikinus sąskaita, Jūs prarasite joje turėtus NEM visam laikui.',
				successMessage: 'Sąskaita {{1}} {{#2}}({{2}}){{/2}} panaikinta!',
				remove: 'Panaikinti'
			},
			nisUnavailable: {
				title: 'NIS nepaleistas',
				message: 'Atsijungta nuo NIS, laukiama prisijungimo'
			},
			shutdown: {
				title: 'Uždaryti programą',
				message: 'Ar tikrai norite uždaryti NEM Community Client?'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'Piniginė importuota sėkmingai!',
			nav: {
				start: '<strong>Pradžiamokslis</strong>',
				about: 'Apie <strong>NEM</strong>',
				help: '<strong>Pagalba</strong>'
			},
			main: {
				leftTitle: 'Esate pirmą kartą <em>NEM</em>?',
				leftButton: 'Sukurti naują piniginę',
				walletNamePlh: 'Sukurkite piniginės pavadinimą',
				passwordPlh: 'Slaptažodis',
				create: 'Sukurti',
				rightTitle: 'Esate <em>NEM</em>eris?',
				rightButton: 'Atidaryti savo piniginę',
				openButton: 'Atidaryti',
				walletsFound: 'Rastos <strong>{{1}}</strong> <em>piniginės</em>',
				copyright: 'Fotografas <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC šifruoja Jūsų piniginę',
						description: '<em>Saugumas</em> yra labai svarbus, tam kad išvengti NEM vagysčių &amp; assets.'
					},
					{
						title: 'NCC šifruoja Jusu piniginę',
						description: '<em>Saugumas</em> yra labai svarbus, tam kad išvengti NEM vagysčių &amp; assets.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'Kaip veikia NCC?',
						paragraphs: [
							'<strong>NCC</strong> leidžia Jums prieiti prie savo NEM ir aktyvų. Jūs galite',
							'<strong>NCC</strong> reikia prieigos prie <strong>NIS</strong> serverio, kad veiktų. Paprastai reikia, kad vietinis serveris būtų aktyvus (jis instaliuojamas kartu su <strong>NCC</strong>)',
							'Jūs taip pat galite susikonfiguruoti prieigą prie nuotolinio <strong>NIS</strong> serverio.'
						],
						listItems: [
							'Turėti keletą piniginių',
							'Turėti keletą saskaitų piniginėje'
						]
					},
					{
						title: 'Kas yra &#42;NIS?',
						paragraphs: [
							'Šis komponentas reikalingas, kad veiktų <strong>NEM</strong> "debesis".',
							'Kuo daugiau yra veikiancių <strong>NIS</strong>, tuo geresnis saugumas.',
							'<strong>NIS</strong> yra prieigos taškas į <strong>NEM</strong> "debesį".'
						],
						legend: '<strong>&#42;NIS</strong> iššifruojamas kaip <strong>NEM Infrastruktūros Serveris</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2014. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: '{{1}} dienos prieš tai',
			lastAccessJustNow: 'Dabar',
			lastAccessTooltip: 'Paskutinį kartą buvo prisijungta {{1}}',
			primary: 'Pagrindinė',
			primaryShort: 'P',
			noLabel: '<Nėra pavadinimo>',
			copyToClipboard: 'Paspauskite, norėdami nukopijuoti adresą į atmintį',
			copiedToClipboard: 'Adresas nukopijuotas į atmintį!',
			actions: {
				refreshInfo: 'Atnaujinti informaciją',
				bootLocalNode: 'Paleisti vietinį prieigos tašką',
				changeWalletName: 'Pakeisti piniginės pavadinimą',
				changeWalletPassword: 'Pakeisti piniginės slaptažodį',
				mergeWallets: 'Apjungti pinigines',
				exportWallet: 'Eksportuoti piniginę',
				createAccount: 'Sukurti naują saskaitą',
				addAccount: 'Pridėti egzistuojančią sąskaitą',
				changeAccountLabel: 'Pakeisti sąskaitos pavadinimą',
				setPrimary: 'Nustatyti kaip pagrindinę sąskaitą',
				removeAccount: 'Pašalinti sąskaitą',
				clientInfo: 'Programos informacija',
				closeWallet: 'Uždaryti piniginę',
				closeProgram: 'Uždaryti programą'
			},
			nav: [
				'Skydelis',
				'Žinutes',
				'Kontaktai',
				'Transakcijos',
				'"Iškasti" blokai',
				'Aktyvų birža',
				'Naujienos',
				'Aplikacijos',
				'Sąskaitos',
				'Nustatymai',
				'Uždaryti programą'
			],
			bootNodeWarning: "Norint naudotis NCC pilnai, vietinis prieigos taškas turi būti paleistas."
		},
		dashboard: {
			assets: {
				title: 'Jūsų aktyvai'
			},
			importance: {
				title: 'Svarbumo tinkle procentas',
				unknown: 'Statusas nežinomas',
				start: 'Pradėti "kasti"',
				harvesting: '"Kasimas" vyksta',
				stop: 'Nutraukti "kasimą"',
				description: 'Sąskaitos svarbumas NEM "debesyje"'
			},
			transactions: {
				title: 'Paskutinės transakcijos',
				sendNem: 'Siųsti NEM',
				balance: 'Turimas balansas',
				syncStatus: '(blokas {{1}}{{#2}} : po {{3}} dienu{{/2}})',
				unknown: 'nežinomas',
				columns: [
					'',
					'Laikas',
					'Siuntėjas/Gavėjas',
					'Žinute',
					'',
					'Informacija',
					'Patvirtinimai',
					'Mokestis',
					'Suma'
				],
				types: {
					pending: 'Atliekama transakcija',
					outgoing: 'Išeinanti transakcija',
					incoming: 'Įeinanti transakcija',
					self: 'Transakcija sau',
				},
				noMessage: 'Žinutes nera',
				encrypted: 'Žinutė šifruota',
				view: 'Peržiūrėti',
				pending: 'Atliekama',
				seeAll: 'Peržiūrėti visas transakcijas',
				noTransactions: 'Nėra dar atlikta jokių transakcijų'
			},
			nemValue: {
				title: 'NEM vertės statistika'
			},
			messages: {
				titleTooltip: 'Žinutes'
			},
			news: {
				titleTooltip: 'Naujienos'
			},
			notAvailable: 'Dar nera šioje alpha versijoje'
		},
		transactions: {
			title: 'Transakcijos',
			sendNem: 'Siųsti NEM',
			balance: 'Turimas balansas',
			filters: {
				confirmed: 'Patvirtintos',
				unconfirmed: 'Nepatvirtintos',
				incoming: 'Įeinančios',
				outgoing: 'Išeinančios',
			},
			table: {
				columns: [
					'',
					'Laikas',
					'Siuntėjas/Gavėjas',
					'Žinute',
					'',
					'Informacija',
					'Patvirtinimai',
					'Mokestis',
					'Suma'
				],
				types: {
					pending: 'Vykdoma transakcija',
					outgoing: 'Išeinanti transakcija',
					incoming: 'Įeinanti transakcija',
					self: 'Transakcija sau',
				},
				noMessage: 'Nėra žinutes',
				encrypted: 'Žinutė šifruota',
				view: 'Peržiūrėti',
				pending: 'Vykdoma',
				noTransactions: 'Nėra atliktų transakcijų',
				loading: 'Krauna daugiau transakcijų...'
			}
		},
		harvestedBlocks: {
			title: '"Iškasti" blokai',
			feeEarned: 'Iš paskutinių 25 "iškastų" blokų gauti mokesčiai',
			table: {
				columns: [
					'Bloko numeris',
					'Laikas',
					'Bloko "hash"',
					'Mokestis'
				],
				noBlocks: 'Nėra "iškastų" blokų ',
				loadMore: 'Peržiūrėti senesnius "iškastus" blokus'
			},
			harvesting: {
				unknown: 'Statusas nežinomas',
				start: 'Pradėti "kasti"',
				harvesting: '"Kasimas" vyksta',
				stop: 'Stabdyti "kasimą"'
			}
		},
		settings: {
			title: 'Nustatymai',
			settings: [
				{
					name: 'Kalba'
				}
			],
			save: 'Išsaugoti pakeitimus',
			saveSuccess: 'Nustatymai išsaugoti sėkmingai'
		}
	}
});