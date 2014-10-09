define({
	id: 'Hn',
	name: 'हिन्दी (Hindi)',
	texts: {
		preferences: {
			thousandSeparator: '\u2009',
			decimalSeparator: '.'
		},
		faults: {
            101: 'फ़ाइल नहीं मिली.',
            102: 'वॉलेट बनाया नहीं गया हे.',
            103: 'वॉलेट फ़ाइल अनुपयोगी है. कृपया बैक अप फ़ाइल से वॉलेट की वसूली कीजिये.',
            104: 'पासवर्ड गलत है. उम्मीद है कि आप सही पासवर्ड याद कर सकते हैं. अगर पासवर्ड खो गया हे तो बरामद नहीं किया  जा सकता हे!',
            106: 'वॉलेट खोलने के बाद हि आप इस्के साथ काम कर सक्ते हैं. वॉलेट का उपयोग करने के लिए आपको पासवर्ड प्रदान  कर्ना हे.',
            107: 'वॉलेट मे अकाउंट शामिल नहीं है.',
            108: 'अकाउंट नहीं हटाया जा सकता हे. यह संभावना है कि अकाउंट खाली नही हे या फिर आप प्राइमरी अकाउंट को हटाने की कोशिश कर रहें हें.',
            109: 'इसी नाम से एक और वॉलेट पहले से ही मौजूद है. एक अन्य नाम चुनें.',
            110: 'अकाउंट पहले से ही इस वॉलेट में हे.',
            202: 'एन्क्रिप्टेड मेसेज नहीं भेजा जा सकता हे क्योंकि रेसिपईयेंट ने अभी तक एक बार भी NEM का ट्रांजैक्शन नही किया है.',
            305: 'NEM इंफ्रास्ट्रक्चर सर्वर उपलब्ध नहीं है.',
            306: 'एक एरर आ गया हे जिसके बारे में विकास टीम ने नही सोचा था. इस बात के लिए हम माफी चाहते हें, शायद एक बार फिरसे प्रयास करने पर मदद मिल सकती है. अन्यथा, NEM NIS/NCC समुदाय के भीतर एक मुद्दे को खोलने का कष्ट करें.',
            400: 'कुछ पैरामीटर गुम या अवैध है.',
            404: 'अनुरोध किया गया संसाधन नहीं पाया जा सका हे.',
            500: 'कॉनफिगरेशन फाइल को बचाने में असफल.',
            600: 'NCC मे NEM की लेनदेन करने के लिए NIS सर्वर को बूट करने की आवश्यकता हे. लोकल नोड बूट करने के लिए NCC मेनू एंट्री का उपयोग करें.',
            601: 'NIS नोड पहले से बूट हो चुकी हे. NIS बूट करने के लिए एक दूसरा प्रयास संभव नहीं है.',
            700: "प्रदान की गयी अकाउंट हार्वेस्टिंग के लिए बुनियादी मानदंडों को पूरा नहीं करता है. मुख्य रूप से यह अकाउंट के अंदर NEM'S की राशि से संबंधित है. हार्वेस्टिंग के लिए कम से कम 1000 NEM की ज़रूरत हे.",
            701: 'प्रदान की गई समय सीमा अतीत में है. समय सीमा 1 दिन की अवधि के अंदर प्रदान की जानी चाहिए.',
            702: 'प्रदान की गई समय सीमा भविष्य में बहुत दूर है. समय सीमा 1 दिन की अवधि के अंदर प्रदान की जानी चाहिए.',
            703: 'आपके अकाउंट में पर्याप्त NEM बैलेंस नही हे.',
            704: 'प्रदान की गई मेसेज टेक्स्ट NEM के माध्यम से भेजने के लिए बहुत बड़ी है. कृपया अपने मेसेज की लंबाई कम करने के लिए प्रयास करें.',
            705: 'ट्रांजैक्शन हैश पहले से ही डेटाबेस या अपुष्ट ट्रांजैक्शन सूची में मौजूद है.',
            706: 'ट्रांजैक्शन हस्ताक्षर का जांच नही किया जा सका है.',
            707: 'ट्रांजैक्शन ID का समय बहुत दूर अतीत में हे.',
            708: 'ट्रांजैक्शन ID का समय बहुत दूर भविष्य में हे.',
            709: 'यह अकाउंट अज्ञात है. नेटवर्क को अकाउंट को पहचानने के लिए अकाउंट में कम से कम एक ट्रांजैक्शन (सेनडर/रेसिपईयेंट) का शामिल होना ज़रूरी होता है.',
            901: 'ऑफलाइन मोड की स्थापना करने में त्रुटि आई है.'
        },
        common: {
        	success: 'Success', //title of the Success message modals
        	appStatus: {
        		nccUnknown: 'NCC की स्तिति अग्यात है',
        		nccUnavailable: 'NCC उपलब्ध नहीं है',
        		nccStarting: 'NCC स्टार्ट हो रहा है...',
        		nisUnknown: 'NIS की स्तिति अग्यात है',
        		nisUnavailable: 'NIS  उपलब्ध नहीं है',
        		nisStarting: 'NIS स्टार्ट हो रहा है...',
        		notBooted: 'NIS को बूट करने की आवश्यकता हे. कृपया अपना वॉलेट खोलें और लोकल नोड बूट करें. आप ऑटो-बूट सेट्टिंग का भी प्रयोग कर सकतें हैं.',
        		booting: 'NIS को बूट किया जा रहा है...',
        		nisInfoNotAvailable: 'NIS की जानकारी अभी तक उपलब्ध नहीं है. जानकारी प्राप्त करने का प्रयास किया जा रहा है. ...',
        		synchronizing: 'NIS सिंक्रनाइज़ हो रहा हे. ब्लॉक {{1}}, स्था. {{2}} दिन पीछे.',
        		daysBehind: {
        			0: '1 दिन से कम',
        			1: '1 दिन',
        			many: '{{1}} दिन'
        		},
        		synchronized: 'NIS सिंक्रनाइज़ हो चुकी है!'
        	}
    //     	nisStatus: {
    //     		notBooted: 'NIS को बूट करने की आवश्यकता है. लोकल नोड बूट करने के लिए NCC मेनू एंट्री का उपयोग करें.',
    //     		synchronized: 'NIS is synchronized!'
    //     	}
        },
		modals: {
			error: {
				title: 'Oops!',
				caption: 'एरर {{1}}'
			},
			confirmDefault: {
				yes: 'हाँ',
				no: 'नहीं'
			},
			settings: {
				title: 'Settings',
				language: {
					label: 'भाषा'
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
					tabTitle: 'ऑटो-बूट',
					name: 'नोड नेम',
					account: 'अकाउंट',
					primaryAccount: 'प्राइमरी अकाउंट',
					auto: 'वॉलेट खोलने पर ऑटो-बूट करें.'
				},
				save: 'Save',
				saveSuccess: 'Settings have been saved successfully'
			},
			sendNem: {
				title: 'NEM भेजें',
				labelDesc: 'इस अकाउंट का लेबल {{1}} हे',
				nullLabelDesc: "इस अकाउंट का लेबल मौजूद नही हे",
				amount: 'रकम',
				recipient: "रेसिपईयेंट अकाउंट",
				message: 'मेसेज',
				encrypt: 'एन्क्रिप्टेड मेसेज',
				fee: 'फ़ीस',
				dueBy: 'चुकाने का समय',
				resetFee: 'न्यूनतम फ़ीस पर रीसेट करें',
				hours: 'घंटे',
				password: 'पासवर्ड',
				send: 'भेजें',
				sending: 'भेजा जा रहा है...',
				successMessage: 'ट्रांजैक्शन सफलतापूर्वक भेज दिया गया है',
				txConfirm: {
					title: 'ट्रांजेक्शन की पुष्टि करें',
					sendLabel: "आप भेजने जा रहे हैं",
					to: 'को',
					message: 'मेसेज',
					encrypted: 'मेसेज एन्क्रिप्टेड है',
					noMessage: 'नो मेसेज',
					cancel: 'रद्द करें',
					send: 'भेजें',
					sending: 'भेजा जा रहा है...'
				}
			},
			clientInfo: {
				title: 'Client info',
				ncc: 'NEM Community Client - NCC',
				signer: 'हस्ताक्षरकर्ता',
				remoteServer: 'रिमोट सर्वर',
				local: 'लोकल',
				nis: 'NEM Infrastructure Server - NIS',
				sync: 'सिंक्रनाइज़ हो चुका है',
				notSync: 'सिंक्रनाइज़ नहीं हुआ है',
				notConnected: 'NEM क्लाउड से कनेक्टेड नहीं हे',
				loading: 'Loading...'
			},
			transactionDetails: {
				title: 'ट्रांजैक्शन विवरण',
				// this might be block or transaction ID
				id: 'ID',
				// this might be block or transaction Hash
				hash: 'Hash',
				type: 'ट्रांजैक्शन प्रकार',
				pending: 'Pending',
				outgoing: 'Outgoing',
				incoming: 'Incoming',
				self: 'Self',
				sender: 'सेनडर',
				recipient: 'रेसिपईयेंट',
				message: 'मेसेज',
				noMessage: 'नो मेसेज',
				encrypted: 'मेसेज एन्क्रिप्टेड है',
				time: 'Timestamp',
				confirmations: 'कन्फर्मेशन्स',
				amount: 'अमाउंट',
				fee: 'फ़ीस'
			},
			bootLocalNode: {
				title: 'लोकल नोड बूट कीजिए',
				account: 'लोकल नोड बूट करने वाला अकाउंट',
				noLabel: '<span class="null">&lt;No label&gt;</span>',
				wallet: 'वॉलेट',
				node: 'नोड नाम',
				boot: 'बूट',
				booting: 'बूट हो रहा है...'
			},
			notBootedWarning: {
				title: 'लोकल नोड बूट नही हो पाया है!',
				message: 'NEM भेजने के लिए लोकल नोड को बूट करना ज़रूरी है!'
			},
			closeWallet: {
				title: 'वॉलेट बंद करें',
				message: 'क्या आप अपने वॉलेट को बंद करके लैंडिंग पेज पर बापस जाना चाहते हैं?'
			},
			createAccount: {
				title: 'नया अकाउंट बनाएँ',
				label: 'निजी लेबल',
				wallet: 'वॉलेट',
				password: "वॉलेट का पासवर्ड",
				successMessage: 'अकाउंट {{1}} {{#2}}({{2}}){{/2}} बना दिया गया है!',
				create: 'बनाएँ'
			},
			addAccount: {
				title: 'कोई मौजूदा अकाउंट जोड़ें',
				privateKey: "अकाउंट का Private Key",
				wallet: 'वॉलेट',
				password: "वॉलेट का पासवर्ड",
				successMessage: 'अकाउंट {{1}} {{#2}}({{2}}){{/2}} वॉलेट में जोड़ दिया गया है!',
				add: 'जोड़ें',
				label: 'लेबल'
			},
			setPrimary: {
				title: 'सेट प्राइमरी अकाउंट',
				account: 'प्राइमरी स्थापित होने वाला अकाउंट',
				noLabel: '<span class="null">&lt;No label&gt;</span>',
				wallet: 'वॉलेट',
				password: "वॉलेट का पासवर्ड",
				successMessage: 'अकाउंट {{1}} {{#2}}({{2}}){{/2}} प्राइमरी सेट हो चुका है!',
				set: 'प्राइमरी सेट करें',
			},
			changeWalletName: {
				title: 'वॉलेट का नाम बदलें',
				wallet: 'मौजूदा वॉलेट का नाम',
				newName: 'नये वॉलेट का नाम',
				password: "वॉलेट का पासवर्ड ",
				successMessage: 'वॉलेट का नाम <em>{{1}}</em> से <em>{{2}}</em> सफलतापूर्वक बदल दिया गया है',
				change: 'बदलें'
			},
			changeWalletPassword: {
				title: 'वॉलेट का पासवर्ड बदलें',
				wallet: 'वॉलेट',
				password: 'मौजूदा पासवर्ड',
				newPassword: 'नया पासवर्ड',
				confirmPassword: 'नए पासवर्ड की पुष्टि करें',
				successMessage: 'वॉलेट के पासवर्ड को सफलतापूर्वक बदल दिया गया है',
				change: 'बदलें',
				passwordNotMatchTitle: 'Oops!',
				passwordNotMatchMessage: 'आपका प्रवेश किया पासवर्ड और पासवर्ड की पुष्टि मेल नहीं खाते हैं. सही ढंग से अपना नया पासवर्ड टाइप करें.'
			},
			changeAccountLabel: {
				title: 'अकाउंट लेबल बदल',
				label: 'अकाउंट लेबल',
				wallet: 'वॉलेट',
				password: "वॉलेट का पासवर्ड",
				successMessage: 'अकाउंट {{1}} अब {{2}} के रूप में चिह्नित है',
				change: 'बदलें'
			},
			removeAccount: {
				title: 'Remove account',
				wallet: 'वॉलेट',
				password: "वॉलेट का पासवर्ड",
				warning: 'कृपया हटाने से पहले यह सुनिश्चित करें कि अकाउंट में कोई भी NEM ना हो, अथवा वे हमेशा के लिए खो जाएँगे.',
				successMessage: 'अकाउंट {{1}} {{# 2}} ({{2}}) {{/ 2}} हटा दिया गया है!',
				remove: 'हटाएँ'
			},
			nisUnavailable: {
				title: 'NIS उपलब्ध नहीं है',
				message: 'NIS से कनेक्शन कट गयी है, कनेक्शन के लिए इंतजार हो रही है'
			},
			shutdown: {
				title: 'Close program',
				message: 'क्या आप NEM समुदाय क्लाइंट को बंद करना चाहते हैं?'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'वॉलेट को सफलतापूर्वक इम्पोर्ट कर दिया गया है!',
			nav: {
				start: 'Getting Started',
				about: 'About NEM',
				settings: 'Settings'
			},
			main: {
				leftTitle: 'क्या आप <em>NEM</em> में नएँ हैं?',
				leftButton: 'नया वॉलेट बनाएँ',
				walletNamePlh: 'अपने वॉलेट का नाम',
				passwordPlh: 'पासवर्ड',
				create: 'बनाएँ',
				rightTitle: 'क्या आप पहले से ही एक <em>NEM</em>ber हैं?',
				rightButton: 'अपना वॉलेट खोलें',
				openButton: 'खोलें',
				walletsFound: 'Found <strong>{{1}}</strong> <em>wallets</em>',
				copyright: 'फोटोग्राफ़ी <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC आपके वॉलेट को एनक्रिप्ट करता है',
						description: 'NEM और असेट्स की चोरी से बचने के लिए <em>सुरक्षा</em> बहुत महत्वपूर्ण है.'
					},
					{
						title: 'NCC आपके वॉलेट को एनक्रिप्ट करता है',
						description: 'NEM और असेट्स की चोरी से बचने के लिए <em>सुरक्षा</em> बहुत महत्वपूर्ण है.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'NCC कैसे काम करता है?',
						paragraphs: [
							'<strong>NCC</strong> एक पारंपरिक वॉलेट की तरह आपके असेट्स और NEMs के लिए एक पहुँच प्रदान करता है.',
							'<strong>NCC</strong> को संचालित रहने के लिए एक <strong>NIS</strong> सर्वर तक पहुँच की आवश्यकता होती है. एक स्थानीय सर्वर को हमेशा सक्रिय रहना होता है(यह <strong>NCC</strong> के साथ इनस्टॉल्ड आता है)',
							'आप एक रिमोट <strong>NIS</strong> भी कॉन्फ़िगर कर सकते हैं.'
						],
						listItems: [
							'मल्टिपल वॉलेट्स',
							'एक वॉलेट में मल्टिपल अकाउंट्स को शामिल करने के लिए अकाउंट्स निर्धारित कीजिए'
						]
					},
					{
						title: '&#42;NIS क्या है?',
						paragraphs: [
							'यह कॉंपोनेंट <strong>NEM</strong> क्लाउड को जिंदा रखने के लिए जिम्मेदार है.',
							'जितने अधिक <strong>NIS</strong> उतनी बेहतर सुरक्षा.',
							'<strong>NEM</strong> क्लाउड की पहुँच बिंदु <strong>NIS</strong> है.'
						],
						legend: '<strong>&#42;NIS</strong> का मतलब <strong>NEM Infrastructure Server</strong> है'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2014. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: '{{1}} दिन पहले',
			lastAccessJustNow: 'थोड़ी देर पहले',
			lastAccessTooltip: 'पिछला लॉगिन था {{1}}',
			primary: 'प्राइमरी',
			primaryShort: 'P',
			noLabel: '<नो लेबल>',
			copiedToClipboard: 'अड्रेस क्लिपबोर्ड में कॉपी कर दिया गया है!',
			actions: {
				refreshInfo: 'जानकारी ताज़ा करे',
				bootLocalNode: 'लोकल नोड बूट करें',
				changeWalletName: 'वॉलेट का नाम बदलें',
				changeWalletPassword: 'वॉलेट का पासवर्ड बदलें',
				mergeWallets: 'मर्ज वॉलेट्स',
				exportWallet: 'एक्सपोर्ट वॉलेट',
				createAccount: 'नया अकाउंट बनाएँ',
				addAccount: 'किसी मौजूदा अकाउंट को जोड़ें',
				changeAccountLabel: 'अकाउंट लेबल बदलें',
				setPrimary: 'प्राइमरी अकाउंट के रूप में सेट करें',
				removeAccount: 'Remove Account',
				clientInfo: 'क्लाइंट इन्फो.',
				closeWallet: 'क्लोज़ वॉलेट',
				closeProgram: 'क्लोज़ प्रोग्राम',
				copyClipboard: 'अड्रेस को क्लिपबोर्ड पर कॉपी करें'
			},
			nav: [
				'डैशबोर्ड',
				'मेसेजस',
				'कॉंटॅक्ट्स',
				'ट्रॅन्सॅक्षन्स',
				'हारवेस्टेड ब्लॉक्स',
				'असेट एक्सचेंज',
				'समाचार',
				'अप्लिकेशन्स',
				'अकाउंट्स',
				'सेटिंग्स',
				'क्लोज़ प्रोग्राम'
			],
			bootNodeWarning: "NCC की सुविधाओं का पूरी तरह से उपयोग करने के लिए आपको एक लोकल नोड बूट करना होगा."
		},
		dashboard: {
			assets: {
				title: 'आपके असेट्स'
			},
			importance: {
				title: 'इंपॉर्टेन्स स्कोर',
				unknown: 'अज्ञात स्थिति',
				start: 'स्टार्ट हार्वेस्टिंग',
				harvesting: 'हार्वेस्टिंग',
				stop: 'स्टॉप हार्वेस्टिंग',
				description: 'NEM क्लाउड के लिए अकाउंट का महत्व'
			},
			transactions: {
				title: 'हाल ही में किए गये ट्रॅन्सॅक्षन्स',
				sendNem: 'NEM भेजें',
				balance: 'Current balance',
				syncStatus: '(at block {{1}}{{#2}} : est. {{3}} days behind{{/2}})',
				unknown: 'अज्ञात',
				columns: [
					'',
					'समय',
					'सेनडर/रेसिपईयेंट',
					'मेसेज',
					'',
					'विवरण',
					'कन्फर्मेशन्स',
					'फ़ीस',
					'अमाउंट'
				],
				types: {
					pending: 'Pending transaction',
					outgoing: 'Outgoing transaction',
					incoming: 'Incoming transaction',
					self: 'Self transaction',
				},
				noMessage: 'नो मेसेज',
				encrypted: 'मेसेज एनक्रिपटेड है',
				view: 'जाँचे',
				pending: 'पेंडिंग',
				seeAll: 'सभी ट्रॅन्सॅक्षन्स',
				noTransactions: 'अभी तक कोई ट्रॅन्सॅक्षन्स नही किया गया है'
			},
			nemValue: {
				title: 'NEM मूल्य आँकड़े'
			},
			messages: {
				titleTooltip: 'मेसेजस'
			},
			news: {
				titleTooltip: 'समाचार'
			},
			notAvailable: 'अल्फा रिलीज में अभी तक उपलब्ध नहीं'
		},
		transactions: {
			title: 'ट्रॅन्सॅक्षन्स',
			sendNem: 'NEM भेजें',
			balance: 'Current Balance',
			filters: {
				confirmed: 'Confirmed',
				unconfirmed: 'Unconfirmed',
				incoming: 'Incoming',
				outgoing: 'Outgoing',
			},
			table: {
				columns: [
					'',
					'समय',
					'सेनडर/रेसिपईयेंट',
					'मेसेज',
					'',
					'विवरण',
					'कन्फर्मेशन्स',
					'फ़ीस',
					'अमाउंट'
				],
				types: {
					pending: 'Pending transaction',
					outgoing: 'Outgoing transaction',
					incoming: 'Incoming transaction',
					self: 'Self transaction',
				},
				noMessage: 'नो मेसेज',
				encrypted: 'मेसेज एनक्रिपटेड है',
				view: 'जाँचे',
				pending: 'Pending',
				noTransactions: 'अभी तक कोई ट्रॅन्सॅक्षन्स नही किया गया है',
				loading: 'Loading...'
			}
		},
		harvestedBlocks: {
			title: 'हारवेस्टेड ब्लॉक्स',
			feeEarned: 'पिछले 25 ब्लॉक्स के हारवेस्टेड फ़ीस',
			table: {
				columns: [
					'Height',
					'Time',
					'Block hash',
					'Fee'
				],
				noBlocks: 'नो हारवेस्टेड ब्लॉक्स ',
				loadMore: 'पुराने हारवेस्टेड ब्लॉक्स'
			},
			harvesting: {
				unknown: 'अज्ञात स्थिति',
				start: 'स्टार्ट हार्वेस्टिंग',
				harvesting: 'हार्वेस्टिंग',
				stop: 'स्टॉप हार्वेस्टिंग'
			}
		},
		settings: {
			title: 'Settings',
			settings: [
				{
					name: 'भाषा'
				}
			],
			save: 'Save changes',
			saveSuccess: 'Settings have been saved successfully'
		}
	}
});
