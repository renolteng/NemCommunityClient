define({
	id: 'lt',
	name: 'Lietuvi�kai',
	texts: {
		faults: {
            101: 'Byla nerasta.',
            102: 'Pinigine nesukurta.',
            103: 'Pinigines byla yra sugadinta. Pra�ome atstatyti Jusu pinigine i� atsargines kopijos, kuria Jus turejote susikurti, kai kurete nauja pinigine arba pridejote nauja saskaita i ja.',
            104: 'Pateiktas slapta�odis netinka. Tikekimes Jus galite atsiminti teisinga slapta�odi. Jei ji praradote, jis negali buti atkurtas!',
            106: 'Prie� pradedant darba su pinigine, ji turi buti atidaryta. Norint atidaryti pinigine, Jus turite ivesti pinigines slapta�odi.',
            107: 'Pinigineje nera �ios saskaitos',
            108: 'Saskaita negali buti pa�alinta. Pana�u, kad saskaitoje yra daugiau kaip 0 NEM arba �i saskaita yra Jusu pagrindine saskaita.',
            109: 'Jau egzistuoja pinigine su tokiu pavadinimu. Pra�ome pasirinkti kita pinigines pavadinima.',
            110: 'Wallet already contains this account.',
            202: 'Nera vie�ojo rakto',
            305: 'NEM Infrastrukturos serveris negalima',
            306: 'Ivyko klaida, kurios kurejai nenumate. Atsipra�ome, gal paleidimas i� naujo pades. Kitu atveju, prane�kite apie tai NEM NIS/NCC bendruomenei',
            400: 'Truksta kai kuriu parametru',
            404: 'Netinkama Boot strategy reik�me',
            500: 'Nepavyko u�saugoti konfiguracijos bylos',
            600: 'NCC reikia, kad NIS serveris butu paleistas, norint atlikti siuntimo ir gavimo transakcijas. Pra�ome naudoti NCC meniu ir paleisti vietini prieigos ta�ka.',
            601: 'NIS prieigos ta�kas jau paleistas. Antra karta ji paleisti neimanoma.',
            700: 'Pateikta saskaita neatitinka pagrindiniu kriteriju, norint "kasti". Pagrinde, tai susije su NEM kiekiu Jusu saskaitoje. "Kasimas" prasideda tik turint ne ma�iau 1,000 NEM.',
            701: 'Pateiktas galutinis terminas yra praeityje. Galutinis terminas turi buti 1 dienos begyje.',
            702: 'Pateiktas galutinis terminas yra per toli ateityje. Galutinis terminas turi buti ne daugiau kaip viena diena.',
            703: 'Jusu saskaitoje neu�tenka le�u, kad i�siusti nurodyta NEM suma.',
            704: 'Pateikta �inute yra per didele, kad ja i�siusti. Pra�ome sutrumpinti �inutu, kuria Jus norite i�siusti.',
            705: 'Transakcijos "hash" jau yra duomenu bazeje arba nepatvirtintu transakciju sara�es.',
            706: 'Transakcijos para�as negali buti patikrintas.',
            707: 'Transakcijos laiko �yme yra per toli praeityje.',
            708: 'Transakcijos laiko �yme yra per toli ateityje.',
            709: 'Saskaita ne�inoma. Saskaita turi buti dalis bent vienos transakcijos (siuntimo ar gavimo), kad butu �inoma tinkle.',
            901: 'There was an error setting up offline mode.'
        },
        common: {
        	success: 'Pavyko', //title of the Success message modals
        	nisStatus: {
        		nccUnavailable: 'NCC is not available',
        		unavailable: 'NIS nera paleistas',
        		notBooted: 'Reikia, kad NIS butu paleistas. Pra�ome atidaryti pinigine ir paleisti vietini prieigos ta�ka, pasinaudodami atsidariusiu dialogo langu.',
        		synchronizing: 'NIS is synchronizing. At block {{1}}, est. {{2}} behind.',
                daysBehind: {
                    0: 'less than 1 day',
                    1: '1 day',
                    many: '{{1}} days'
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
				title: 'Siusti NEM',
				labelDesc: '�i saskaita pavadinta kaip <strong>{{1}}</strong>',
				nullLabelDesc: "�i saskaita neturi pavadinimo",
				amount: 'Suma',
				recipient: "Gavejo saskaita",
				message: '�inute',
				encrypt: 'U��ifruoti �inute',
				fee: 'Mokestis',
				dueBy: 'Galiojimo trukme',
				resetFee: 'Nustatyti iki minimalaus mokescio',
				hours: 'valandos',
				password: 'Slapta�odis',
				send: 'Siusti',
				sending: 'Siunciama...',
				successMessage: 'Transakcija i�siusta sekmingai!'
			},
			clientInfo: {
				title: 'Kliento informacija',
				ncc: 'NEM Community Client - NCC',
				signer: 'Para�o turetojas',
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
				outgoing: 'I�einanti',
				incoming: 'Ieinanti',
				self: 'Sau',
				sender: 'Siuntejas',
				recipient: 'Gavejas',
				message: '�inute',
				noMessage: 'Nera �inutes',
				encrypted: '�inute yra u��ifruota',
				time: 'Laiko �yme',
				confirmations: 'Patvirtinimai',
				amount: 'Suma',
				fee: 'Mokestis'
			},
			bootLocalNode: {
				title: 'U�krauti vietini prieigos ta�ka',
				account: 'Vietinio prieigos ta�ko scskaita',
				noLabel: '&lt;Nera pavadinimo&gt;',
				wallet: 'Pinigine',
				node: 'Prieigos ta�ko pavadinimas',
				boot: 'U�krauti',
				booting: 'Kraunasi...'
			},
			notBootedWarning: {
				title: 'Prieigos ta�kas nepaleistas!',
				message: 'Norint siusti NEM, vietinis prieigos ta�kas turi buti paleistas!'
			},
			closeWallet: {
				title: 'U�daryti pinigine',
				message: 'Ar tikrai norite u�daryti pinigine ir gri�ti i pagrindini puslapi?'
			},
			createAccount: {
				title: 'Sukurti nauja saskaita',
				label: 'Nuosavas pavadinimas',
				wallet: 'Pinigine',
				password: "Pinigines slapta�odis",
				successMessage: 'Saskaita {{1}} {{#2}}({{2}}){{/2}} sukurta!',
				create: 'Sukurti'
			},
			addAccount: {
				title: 'Prideti egzistuojancia saskaita',
				privateKey: "Saskaitos privatus raktas",
				wallet: 'Pinigine',
				password: "Pinigines slapta�odis",
				successMessage: 'Saskaita {{1}} {{#2}}({{2}}){{/2}} itraukta i pinigine!',
				add: 'Prideti',
				label: 'Pavadinimas'
			},
			setPrimary: {
				title: 'Nustatyti pagrindine saskaita',
				account: 'Saskaita, kuri turi buti nustatyta kaip pagrindine',
				noLabel: '&lt;Nera pavadinimo&gt;',
				wallet: 'Pinigine',
				password: "Pinigines slapta�odis",
				successMessage: 'Saskaita {{1}} {{#2}}({{2}}){{/2}} nustatyta kaip pagrindine!',
				set: 'Nustatyta kaip pagrindine',
			},
			changeWalletName: {
				title: 'Pakeisti pinigines pavadinima',
				wallet: 'Dabartinis pinigines pavadinimas',
				newName: 'Naujas pinigines pavadinimas',
				password: "Pinigines slapta�odis",
				successMessage: 'Pinigines pavadinimas pakeistas sekmingai i� <em>{{1}}</em> i <em>{{2}}</em>',
				change: 'Pakeisti'
			},
			changeWalletPassword: {
				title: 'Pakeisti pinigines slapta�odi',
				wallet: 'Pinigine',
				password: 'Dabartinis slapta�odis',
				newPassword: 'Naujas slapta�odis',
				confirmPassword: 'Patvirtinti nauja slapta�odi',
				successMessage: 'Pinigines slapta�odis pakeistas sekmingai',
				change: 'Pakeisti',
				passwordNotMatchTitle: 'Oops!',
				passwordNotMatchMessage: 'Jusu ivestas naujas slapta�odis ir jo patvirtinimas neatitinka. Pra�ome isitikinti, kad nauja slapta�odi ivedete teisingai.'
			},
			changeAccountLabel: {
				title: 'Pakeisti saskaitos pavadinima',
				label: 'Saskaitos pavadinimas',
				wallet: 'Pinigine',
				password: "Pinigines slapta�odis",
				successMessage: 'Saskaita {{1}} dabar pavadinta kaip {{2}}',
				change: 'Pakeisti'
			},
			removeAccount: {
				title: 'Panaikinti saskaita',
				wallet: 'Pinigine',
				password: "Pinigines slapta�odis",
				warning: 'Pra�ome isitikinti, kad Jusu saskaitoje nera NEM prie� ja panaikinant. Panaikinus saskaita, Jus prarasite joje turetus NEM visam laikui.',
				successMessage: 'Saskaita {{1}} {{#2}}({{2}}){{/2}} panaikinta!',
				remove: 'Panaikinti'
			},
			nisUnavailable: {
				title: 'NIS nepaleistase',
				message: 'Atsijungta nuo NIS, laukiama prisijungimo'
			},
			shutdown: {
				title: 'U�daryti programa',
				message: 'Ar tikrai norite u�daryti NEM Community Client?'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'Pinigine importuota sekmingai!',
			nav: {
				start: 'Prad�iamokslis',
				about: 'Apie NEM',
				help: 'Pagalba'
			},
			main: {
				leftTitle: 'Pirma karta <em>NEM</em>?',
				leftButton: 'Sukurti nauja pinigine',
				walletNamePlh: 'Sukurkite pinigines pavadinima',
				passwordPlh: 'Slapta�odis',
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
						title: 'NCC �ifruoja Jusu pinigine',
						description: '<em>Saugumas</em> yra labai svarbus, tam kad i�vengti NEM vagysciu &amp; assets.'
					},
					{
						title: 'NCC �ifruoja Jusu pinigine',
						description: '<em>Saugumas</em> yra labai svarbus, tam kad i�vengti NEM vagysciu &amp; assets.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'Kaip veikia NCC?',
						paragraphs: [
							'<strong>NCC</strong> leid�ia Jums prieiti prie savo NEM ir aktyvu. Jus galite',
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
							'�is komponentas reikalingas, kad veiktu <strong>NEM</strong> "debesis".',
							'Kuo daugiau yra veikianciu <strong>NIS</strong>, tuo geresnis saugumas.',
							'<strong>NIS</strong> yra prieigos ta�kas i <strong>NEM</strong> "debesi".'
						],
						legend: '<strong>&#42;NIS</strong> i��ifruojamas kaip <strong>NEM Infrastrukturos Serveris</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2014. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: '{{1}} dienos prie� tai',
			lastAccessJustNow: 'Dabar',
			lastAccessTooltip: 'Paskutini karta buvo prisijungta {{1}}',
			primary: 'Pagrindine',
			primaryShort: 'P',
			noLabel: '<Nera pavadinimo>',
			copiedToClipboard: 'Adresas nukopijuotas i atminti!',
			actions: {
				refreshInfo: 'Atnaujinti informacija',
				bootLocalNode: 'Paleisti vietini prieigos ta�ka',
				changeWalletName: 'Pakeisti pinigines pavadinima',
				changeWalletPassword: 'Pakeisti pinigines slapta�odi',
				mergeWallets: 'Apjungti pinigines',
				exportWallet: 'Eksportuoti pinigine',
				createAccount: 'Sukurti nauja saskaita',
				addAccount: 'Prideti egzistuojancia saskaita',
				changeAccountLabel: 'Pakeisti saskaitos pavadinima',
				setPrimary: 'Nustatyti kaip pagrindine saskaita',
				removeAccount: 'Pa�alinti saskaita',
				clientInfo: 'Programos informacija',
				closeWallet: 'U�daryti pinigine',
				closeProgram: 'U�daryti programa',
				copyClipboard: 'Copy address to clipboard'
			},
			nav: [
				'Skydelis',
				'�inutes',
				'Kontaktai',
				'Transakcijos',
				'"I�kasti" blokai',
				'Aktyvu bir�a',
				'Naujienos',
				'Aplikacijos',
				'Saskaitos',
				'Nustatymai',
				'U�daryti programa'
			],
			bootNodeWarning: "Norint naudotis NCC pilnai, vietinis prieigos ta�kas turi buti paleistas."
		},
		dashboard: {
			assets: {
				title: 'Jusu aktyvai'
			},
			importance: {
				title: 'Svarbumo balas',
				unknown: 'Statusas ne�inomas',
				start: 'Pradeti "kasti"',
				harvesting: '"Kasimas" vyksta',
				stop: 'Nutraukti "kasima"',
				description: 'Saskaitos svarbumas NEM "debesyje"'
			},
			transactions: {
				title: 'Paskutines transakcijos',
				sendNem: 'Siusti NEM',
				balance: 'Turimas balansas',
				syncStatus: '(bloke {{1}}{{#2}} : po {{3}} dienu{{/2}})',
				unknown: 'ne�inomas',
				columns: [
					'',
					'Laikas',
					'Siuntejas/Gavejas',
					'�inute',
					'',
					'Informacija',
					'Patvirtinimai',
					'Mokestis',
					'Suma'
				],
				types: {
					pending: 'Atliekama transakcija',
					outgoing: 'I�einanti transakcija',
					incoming: 'Ieinanti transakcija',
					self: 'Transakcija sau',
				},
				noMessage: '�inutes nera',
				encrypted: '�inute �ifruota',
				view: 'Per�iureti',
				pending: 'Atliekama',
				seeAll: 'Per�iureti visas transakcijas',
				noTransactions: 'Nera dar atlikta jokiu transakciju'
			},
			nemValue: {
				title: 'NEM vertes statistika'
			},
			messages: {
				titleTooltip: '�inutes'
			},
			news: {
				titleTooltip: 'Naujienos'
			},
			notAvailable: 'Dar nera �ioje alpha versijoje'
		},
		transactions: {
			title: 'Transakcijos',
			sendNem: 'Siusti NEM',
			balance: 'Turimas balansas',
			filters: {
				confirmed: 'Confirmed',
				unconfirmed: 'Unconfirmed',
				incoming: 'Ieinancios',
				outgoing: 'I�einancios',
			},
			table: {
				columns: [
					'',
					'Laikas',
					'Siuntejas/Gavejas',
					'�inute',
					'',
					'Informacija',
					'Patvirtinimai',
					'Mokestis',
					'Suma'
				],
				types: {
					pending: 'Vykdoma transakcija',
					outgoing: 'I�einanti transakcija',
					incoming: 'Ieinanti transakcija',
					self: 'Transakcija sau',
				},
				noMessage: 'Nera �inutes',
				encrypted: '�inute �ifruota',
				view: 'Per�iureti',
				pending: 'Vykdoma',
				noTransactions: 'Nera atliktu transakciju',
				loading: 'Krauna daugiau transakciju...'
			}
		},
		harvestedBlocks: {
			title: '"I�kasti" blokai',
			feeEarned: 'I� paskutiniu 25 "i�kastu" bloku gauti mokesciai',
			table: {
				columns: [
					'Bloko numeris',
					'Laikas',
					'Bloko "hash"',
					'Mokestis'
				],
				noBlocks: 'Nera "i�kastu" bloku ',
				loadMore: 'Per�iureti senesnius "i�kastus" blokus'
			},
			harvesting: {
				unknown: 'Statusas ne�inomas',
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
			save: 'I�saugoti pakeitimus',
			saveSuccess: 'Nustatymai i�saugoti sekmingai'
		}
	}
});