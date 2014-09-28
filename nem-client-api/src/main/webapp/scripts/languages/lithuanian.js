define({
	id: 'lt',
	name: 'Lietuviškai',
	texts: {
		preferences: {
			thousandSeparator: '\u2009',
			decimalSeparator: '.'
		},
		faults: {
            101: 'Byla nerasta.',
            102: 'Pinigine nesukurta.',
            103: 'Pinigines byla yra sugadinta. Prašome atstatyti Jusu pinigine iš atsargines kopijos, kuria Jus turejote susikurti, kai kurete nauja pinigine arba pridejote nauja saskaita i ja.',
            104: 'Pateiktas slaptažodis netinka. Tikekimes Jus galite atsiminti teisinga slaptažodi. Jei ji praradote, jis negali buti atkurtas!',
            106: 'Prieš pradedant darba su pinigine, ji turi buti atidaryta. Norint atidaryti pinigine, Jus turite ivesti pinigines slaptažodi.',
            107: 'Pinigineje nera šios saskaitos',
            108: 'Saskaita negali buti pašalinta. Panašu, kad saskaitoje yra daugiau kaip 0 NEM arba ši saskaita yra Jusu pagrindine saskaita.',
            109: 'Jau egzistuoja pinigine su tokiu pavadinimu. Prašome pasirinkti kita pinigines pavadinima.',
            110: 'Pinigineje jau yra ši saskaita.',
            202: 'Nera viešojo rakto',
            305: 'NEM Infrastrukturos serveris negalimas',
            306: 'Ivyko klaida, kurios kurejai nenumate. Atsiprašome, bet gal paleidimas iš naujo pades. Kitu atveju, praneškite apie tai NEM NIS/NCC bendruomenei',
            400: 'Truksta kai kuriu parametru',
            404: 'Netinkama Boot strategy reikšme',
            500: 'Nepavyko užsaugoti konfiguracijos bylos',
            600: 'NCC reikia, kad NIS serveris butu paleistas, norint atlikti siuntimo ir gavimo transakcijas. Prašome naudoti NCC meniu ir paleisti vietini prieigos taška.',
            601: 'NIS prieigos taškas jau paleistas. Antra karta ji paleisti neimanoma.',
            700: 'Pateikta saskaita neatitinka pagrindiniu kriteriju, norint "kasti". Pagrinde, tai susije su NEM kiekiu Jusu saskaitoje. "Kasimas" prasideda tik turint ne mažiau 1,000 NEM.',
            701: 'Pateiktas galutinis terminas yra praeityje. Galutinis terminas turi buti 1 dienos begyje.',
            702: 'Pateiktas galutinis terminas yra per toli ateityje. Galutinis terminas turi buti ne daugiau kaip viena diena.',
            703: 'Jusu saskaitoje neužtenka lešu, kad išsiusti nurodyta NEM suma.',
            704: 'Pateikta žinute yra per didele, kad ja išsiusti. Prašome sutrumpinti žinute, kuria Jus norite išsiusti.',
            705: 'Transakcijos "hash" jau yra duomenu bazeje arba nepatvirtintu transakciju saaraše.',
            706: 'Transakcijos parašas negali buti patikrintas.',
            707: 'Transakcijos laiko žyme yra per toli praeityje.',
            708: 'Transakcijos laiko žyme yra per toli ateityje.',
            709: 'Saskaita nežinoma. Saskaita turi tureti bent viena transakcija (siuntimo ar gavimo), kad butu žinoma tinkle.',
            901: 'Ivyko klaida nustatant offline režima.'
        },
        common: {
        	success: 'Pavyko', //title of the Success message modals
        	nisStatus: {
        		nccUnavailable: 'NCC is not available',
        		unavailable: 'NIS nera paleistas',
        		booting: 'Booting NIS...',
        		notBooted: 'Reikia, kad NIS butu paleistas. Prašome atidaryti pinigine ir paleisti vietini prieigos taška, pasinaudodami atsidariusiu dialogo langu.',
        		retrievingStatus: 'Retrieving NIS status...',
        		synchronizing: 'NIS sinchronizuojasi. Dabartinis blokas yra {{1}}, liko dar {{2}} .',
                daysBehind: {
                    0: 'mažiau kaip 1 diena',
                    1: '1 diena',
                    many: '{{1}} dienos'
                },
        		synchronized: 'NIS is synchronized!'
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
			settings: {
				title: 'Settings',
				language: {
					label: 'Language'
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
					tabTitle: 'Auto-boot',
					name: 'Node name',
					account: 'Account',
					primaryAccount: 'Primary Account',
					auto: 'Auto boot when a wallet is opened'
				},
				save: 'Save',
				saveSuccess: 'Settings have been saved successfully'
			},
			sendNem: {
				title: 'Siusti NEM',
				labelDesc: 'Ši saskaita pavadinta kaip {{1}}',
				nullLabelDesc: "Ši saskaita neturi pavadinimo",
				amount: 'Suma',
				recipient: "Gavejo saskaita",
				message: 'Žinute',
				encrypt: 'Užšifruoti žinute',
				fee: 'Mokestis',
				dueBy: 'Galiojimo trukme',
				resetFee: 'Nustatyti iki minimalaus mokescio',
				hours: 'valandos',
				password: 'Slaptažodis',
				send: 'Siusti',
				sending: 'Siunciama...',
				successMessage: 'Transakcija išsiusta sekmingai!'
			},
			clientInfo: {
				title: 'Kliento informacija',
				ncc: 'NEM Community Client - NCC',
				signer: 'Parašo turetojas',
				remoteServer: 'Nuotolinis serveris',
				local: 'Vietinis',
				nis: 'NEM Infrastructure Server - NIS',
				sync: 'Susisinchronizaves',
				notSync: 'Nesusisinchronizaves',
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
				incoming: 'Ieinanti',
				self: 'Sau',
				sender: 'Siuntejas',
				recipient: 'Gavejas',
				message: 'Žinute',
				noMessage: 'Nera žinutes',
				encrypted: 'Žinute yra užšifruota',
				time: 'Laiko žyme',
				confirmations: 'Patvirtinimai',
				amount: 'Suma',
				fee: 'Mokestis'
			},
			bootLocalNode: {
				title: 'Užkrauti vietini prieigos taška',
				account: 'Vietinio prieigos taško saskaita',
				noLabel: '<span class="null">&lt;Nera pavadinimo&gt;</span>',
				wallet: 'Pinigine',
				node: 'Prieigos taško pavadinimas',
				boot: 'Užkrauti',
				booting: 'Kraunasi...'
			},
			notBootedWarning: {
				title: 'Prieigos taškas nepaleistas!',
				message: 'Norint siusti NEM, vietinis prieigos taškas turi buti paleistas!'
			},
			closeWallet: {
				title: 'Uždaryti pinigine',
				message: 'Ar tikrai norite uždaryti pinigine ir grižti i pagrindini puslapi?'
			},
			createAccount: {
				title: 'Sukurti nauja saskaita',
				label: 'Nuosavas pavadinimas',
				wallet: 'Pinigine',
				password: "Pinigines slaptažodis",
				successMessage: 'Saskaita {{1}} {{#2}}({{2}}){{/2}} sukurta!',
				create: 'Sukurti'
			},
			addAccount: {
				title: 'Prideti egzistuojancia saskaita',
				privateKey: "Saskaitos privatus raktas",
				wallet: 'Pinigine',
				password: "Pinigines slaptažodis",
				successMessage: 'Saskaita {{1}} {{#2}}({{2}}){{/2}} itraukta i pinigine!',
				add: 'Prideti',
				label: 'Pavadinimas'
			},
			setPrimary: {
				title: 'Nustatyti pagrindine saskaita',
				account: 'Saskaita, kuri turi buti nustatyta kaip pagrindine',
				noLabel: '<span class="null">&lt;Nera pavadinimo&gt;</span>',
				wallet: 'Pinigine',
				password: "Pinigines slaptažodis",
				successMessage: 'Saskaita {{1}} {{#2}}({{2}}){{/2}} nustatyta kaip pagrindine!',
				set: 'Nustatyta kaip pagrindine',
			},
			changeWalletName: {
				title: 'Pakeisti pinigines pavadinima',
				wallet: 'Dabartinis pinigines pavadinimas',
				newName: 'Naujas pinigines pavadinimas',
				password: "Pinigines slaptažodis",
				successMessage: 'Pinigines pavadinimas pakeistas sekmingai iš <em>{{1}}</em> i <em>{{2}}</em>',
				change: 'Pakeisti'
			},
			changeWalletPassword: {
				title: 'Pakeisti pinigines slaptažodi',
				wallet: 'Pinigine',
				password: 'Dabartinis slaptažodis',
				newPassword: 'Naujas slaptažodis',
				confirmPassword: 'Patvirtinti nauja slaptažodi',
				successMessage: 'Pinigines slaptažodis pakeistas sekmingai',
				change: 'Pakeisti',
				passwordNotMatchTitle: 'Oops!',
				passwordNotMatchMessage: 'Jusu ivestas naujas slaptažodis ir jo patvirtinimas neatitinka. Prašome isitikinti, kad nauja slaptažodi ivedete teisingai.'
			},
			changeAccountLabel: {
				title: 'Pakeisti saskaitos pavadinima',
				label: 'Saskaitos pavadinimas',
				wallet: 'Pinigine',
				password: "Pinigines slaptažodis",
				successMessage: 'Saskaita {{1}} dabar pavadinta kaip {{2}}',
				change: 'Pakeisti'
			},
			removeAccount: {
				title: 'Panaikinti saskaita',
				wallet: 'Pinigine',
				password: "Pinigines slaptažodis",
				warning: 'Prašome isitikinti, kad Jusu saskaitoje nera NEM prieš ja panaikinant. Panaikinus saskaita, Jus prarasite joje turetus NEM visam laikui.',
				successMessage: 'Saskaita {{1}} {{#2}}({{2}}){{/2}} panaikinta!',
				remove: 'Panaikinti'
			},
			nisUnavailable: {
				title: 'NIS nepaleistas',
				message: 'Atsijungta nuo NIS, laukiama prisijungimo'
			},
			shutdown: {
				title: 'Uždaryti programa',
				message: 'Ar tikrai norite uždaryti NEM Community Client?'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'Pinigine importuota sekmingai!',
			nav: {
				start: 'Pradžiamokslis',
				about: 'Apie NEM',
				settings: 'Settings'
			},
			main: {
				leftTitle: 'Esate pirma karta <em>NEM</em>?',
				leftButton: 'Sukurti nauja pinigine',
				walletNamePlh: 'Sukurkite pinigines pavadinima',
				passwordPlh: 'Slaptažodis',
				create: 'Sukurti',
				rightTitle: 'Esate <em>NEM</em>eris?',
				rightButton: 'Atidaryti savo pinigine',
				openButton: 'Atidaryti',
				walletsFound: 'Rastos <strong>{{1}}</strong> <em>pinigines</em>',
				copyright: 'Fotografas <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC šifruoja Jusu pinigine',
						description: '<em>Saugumas</em> yra labai svarbus, tam kad išvengti NEM vagysciu &amp; assets.'
					},
					{
						title: 'NCC šifruoja Jusu pinigine',
						description: '<em>Saugumas</em> yra labai svarbus, tam kad išvengti NEM vagysciu &amp; assets.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'Kaip veikia NCC?',
						paragraphs: [
							'<strong>NCC</strong> leidžia Jums prieiti prie savo NEM ir aktyvu. Jus galite',
							'<strong>NCC</strong> reikia prieigos prie <strong>NIS</strong> serverio, kad veiktu. Paprastai reikia, kad vietinis serveris butu aktyvus (jis instaliuojamas kartu su <strong>NCC</strong>)',
							'Jus taip pat galite susikonfiguruoti prieiga prie nuotolinio <strong>NIS</strong> serverio.'
						],
						listItems: [
							'Tureti keleta piniginiu',
							'Tureti keleta saskaitu pinigineje'
						]
					},
					{
						title: 'Kas yra &#42;NIS?',
						paragraphs: [
							'Šis komponentas reikalingas, kad veiktu <strong>NEM</strong> "debesis".',
							'Kuo daugiau yra veikianciu <strong>NIS</strong>, tuo geresnis saugumas.',
							'<strong>NIS</strong> yra prieigos taškas i <strong>NEM</strong> "debesi".'
						],
						legend: '<strong>&#42;NIS</strong> iššifruojamas kaip <strong>NEM Infrastrukturos Serveris</strong>'
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
			lastAccessTooltip: 'Paskutini karta buvo prisijungta {{1}}',
			primary: 'Pagrindine',
			primaryShort: 'P',
			noLabel: '<Nera pavadinimo>',
			copyToClipboard: 'Paspauskite, noredami nukopijuoti adresa i atminti',
			actions: {
				refreshInfo: 'Atnaujinti informacija',
				bootLocalNode: 'Paleisti vietini prieigos taška',
				changeWalletName: 'Pakeisti pinigines pavadinima',
				changeWalletPassword: 'Pakeisti pinigines slaptažodi',
				mergeWallets: 'Apjungti pinigines',
				exportWallet: 'Eksportuoti pinigine',
				createAccount: 'Sukurti nauja saskaita',
				addAccount: 'Prideti egzistuojancia saskaita',
				changeAccountLabel: 'Pakeisti saskaitos pavadinima',
				setPrimary: 'Nustatyti kaip pagrindine saskaita',
				removeAccount: 'Pašalinti saskaita',
				clientInfo: 'Programos informacija',
				closeWallet: 'Uždaryti pinigine',
				closeProgram: 'Uždaryti programa',
				copyClipboard: 'Kopijuoti adresa i laikinaja atminti'
			},
			nav: [
				'Skydelis',
				'Žinutes',
				'Kontaktai',
				'Transakcijos',
				'"Iškasti" blokai',
				'Aktyvu birža',
				'Naujienos',
				'Aplikacijos',
				'Saskaitos',
				'Nustatymai',
				'Uždaryti programa'
			],
			bootNodeWarning: "Norint naudotis NCC pilnai, vietinis prieigos taškas turi buti paleistas."
		},
		dashboard: {
			assets: {
				title: 'Jusu aktyvai'
			},
			importance: {
				title: 'Svarbumo tinkle procentas',
				unknown: 'Statusas nežinomas',
				start: 'Pradeti "kasti"',
				harvesting: '"Kasimas" vyksta',
				stop: 'Nutraukti "kasima"',
				description: 'Saskaitos svarbumas NEM "debesyje"'
			},
			transactions: {
				title: 'Paskutines transakcijos',
				sendNem: 'Siusti NEM',
				balance: 'Turimas balansas',
				syncStatus: '(blokas {{1}}{{#2}} : po {{3}} dienu{{/2}})',
				unknown: 'nežinomas',
				columns: [
					'',
					'Laikas',
					'Siuntejas/Gavejas',
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
					incoming: 'Ieinanti transakcija',
					self: 'Transakcija sau',
				},
				noMessage: 'Žinutes nera',
				encrypted: 'Žinute šifruota',
				view: 'Peržiureti',
				pending: 'Atliekama',
				seeAll: 'Peržiureti visas transakcijas',
				noTransactions: 'Nera dar atlikta jokiu transakciju'
			},
			nemValue: {
				title: 'NEM vertes statistika'
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
			sendNem: 'Siusti NEM',
			balance: 'Turimas balansas',
			filters: {
				confirmed: 'Patvirtintos',
				unconfirmed: 'Nepatvirtintos',
				incoming: 'Ieinancios',
				outgoing: 'Išeinancios',
			},
			table: {
				columns: [
					'',
					'Laikas',
					'Siuntejas/Gavejas',
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
					incoming: 'Ieinanti transakcija',
					self: 'Transakcija sau',
				},
				noMessage: 'Nera žinutes',
				encrypted: 'Žinute šifruota',
				view: 'Peržiureti',
				pending: 'Vykdoma',
				noTransactions: 'Nera atliktu transakciju',
				loading: 'Krauna daugiau transakciju...'
			}
		},
		harvestedBlocks: {
			title: 'Iškasti blokai',
			feeEarned: 'Iš paskutiniu 25 "iškastu" bloku gauti mokesciai',
			table: {
				columns: [
					'Bloko numeris',
					'Laikas',
					'Bloko "hash"',
					'Mokestis'
				],
				noBlocks: 'Nera "iškastu" bloku ',
				loadMore: 'Peržiureti senesnius "iškastus" blokus'
			},
			harvesting: {
				unknown: 'Statusas nežinomas',
				start: 'Pradeti "kasti"',
				harvesting: '"Kasimas" vyksta',
				stop: 'Stabdyti "kasima"'
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
			saveSuccess: 'Nustatymai išsaugoti sekmingai'
		}
	}
});