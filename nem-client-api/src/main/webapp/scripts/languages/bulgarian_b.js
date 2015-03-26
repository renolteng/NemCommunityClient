define({
	id: 'bg',
	name: 'Български',
	texts: {
		preferences: {
			thousandSeparator: '\u2009',
			decimalSeparator: '.'
		},
		faults: {
			101: 'Файлът на портфейла не съществува',
			102: 'Портфейлът не е създаден.',
			103: 'Файлът на портфейла е повреден.Моля възстановете файла от копие което сте запазили при създаването на портфейла.',
			104: 'Въведената парола е грешна.',
			105: 'Не е предоставена парола за портфейла.',
			106: 'Преди да започнете работа с портфейла се убедете че той е отворен. За да получите достъп до портфейла въведете паролата за този портфейл.',
			107: 'Портфейла не съдържа този акаунт.',
			108: 'Акаунта не може да бъде премахнат. По всяка вероятност това е така защото акаунта има баланс повече от 0 XEM или акаунта ,който се опитвате да премахнете е главен акаунт. ',
			109: 'Друг портфейл със същото име вече съществува. Моля  изберете друго име на портфейла.',
			110: 'Портфейла вече съдържа този акаунт.',
			111: 'Името на портфейла е директория.',
			112: 'Типът на файла на портфейла е некоректен.',
			113: 'Портфейла не може да бъде изтрит.',
			121: 'Адрес бук файла не съществува.',
			122: 'Адрес букът не беше създаден.',
			123: 'Адрес бук файлът е повреден.Моля възстановете вашият адрес бук от резервното копие.',
			124: 'Предоставената парола на адрес бука не е правилна.',
			125: 'Не е предоставена парола на адрес бука.',
			127: 'Адрес бука не съдържа този адрес.',
			128: 'Предоставения адрес е невалиден',
			129: 'Друг адрес бук със същото име вече съществува.Моля изберете друго име на адрес бука.',
			130: 'Адрес бука вече съдържа този адрес.',
			131: 'Името на адрес бука е директория.',
			132: 'Типът на адрес бук файла е некоректен.',
			133: 'Адрес букът не може да бъде изтрит.',
			202: 'Криптирано съобщение не може да бъде изпратено понеже получателя до сега не е извършил нито една транзакция и няма публичен ключ.',
			203: 'The account cannot be converted because not all cosignatories are known. They either need to be in the same wallet or have made at least one transaction.',
			305: 'NEM Infrastructure Server е недостъпен\n\nОпитайте да рестартирате NEM програмата.\n\nАко ползвате отдалечен NIS,проверете вашият конфигуриран хост за правописни грешки или ползвайте друг отдалечен NIS.',
			306: 'Възникна непредвидена грешка.Извиняваме се за това ,опитайте отново може това да помогне.В противен случай се обърнете за помощ към NEM NIS/NCC community.',
			400: 'Някой параметри липсват или са некоректни.',
			401: 'Тази операция не може да бъде завършена защото може да компрометира частният ви ключ като го изпрати до отдалечения NIS',
			404: 'Търсеният ресурс не може да бъде намерен.',
			500: 'Възникна грешка, която не е предвидена от разработчиците. Извиняваме се за това, може би повторен опит може да помогне. В противен случай се обърнете за помощ към NEM NIS/NCC community.',
			600: 'За получаване и изпращане на транзакции NEM Infrastructure Server (NIS) сървъра трябва да бъде рестартиран.Моля използвайте менюто на NCC за рестартиране на локалния възел.',
			601: 'NEM Infrastructure Server (NIS) възела е вече стартиран.',
			602: 'Почти е готово. NEM Infrastructure Server понастоящем зарежда блоковете.\nПортфейлаще функционира когато db се зареди напълно.',
			699: 'Максималния брой на генериращите адреси позволени от сървъра беше превишен.',
			700: 'Предоставения акаунт не удовлетворява основните критерии за генерация.Основно това е свързано с количеството XEM в сметката.Генерацията започва поне с 1000 XEM.',
			701: 'Предоставеният срок е в миналото. Срокът трябва да бъде предоставен в рамките на 1-дневен период.',
			702: 'Предоставеният срок е твърде далеч в бъдещето. Срокът трябва да бъде в рамките на еднодневен период от време.',
			703: 'Your account does not have the right balance to make this transaction.',
			704: 'Предоставеното съобщение е прекалено голямо за да бъде изпратено.Моля опитайте да съкратите дължината на съобщението ,което искате да изпратите.',
			705: 'Транзакцията вече съществува в базата данни или в листа на непотвърдените транзакции.',
			706: 'Подписът на сделката не може да бъде проверен.',
			707: 'Времевата отметка на транзакцията е много далеч в миналото.',
			708: 'Времевата отметка на транзакцията е много далеч в бъдещето.',
			709: 'Неизвестен акаунт. Акаунта трябва да е част от поне една транзакция (входяща или изходяща) за да бъде разпознат от мрежата.',
			710: 'Транзакцията беше отхвърлена защото кеша на транзакциите е препълнен.По голямата такса увеличава шанса транзакцията да бъде приета.',
			730: 'Importance transfer transaction (delegated harvesting) conflicts with existing transaction.',
			731: 'Delegated harvesting account has non zero balance and cannot be used.',
			732: 'Важният трансфер е отхвърлен.Вече съществува чакаща важна трансфер операция.',
			733: 'Delegated harvesting is already active.',
			734: 'Delegated harvesting is NOT active. Cannot deactivate.',
			740: 'Транзакцията не е позволена за мултисигнатурен акаунт.',
			741: 'Мултисигнатурния подпис на транзакцията е отхвърлен.Този акаунт не е косигнатурен на мултисигнатурния акаунт.',
			742: 'Мултисигнатурния подпис на транзакцията е отхвърлен.Асоциираната мултисигнатурна транзакция е неизвестна за NEM мрежата.',
			743: 'Модификацията на мултисигнатурния акаунт е отхвърлена.Един от добавените акаунти е вече косигнатурен.',
			901: 'Възникна грешка при преминаване в режим офлайн.',
			1000: 'Частният ключ и публичният ключ ,който въведохте са разменени.',
			1001: 'Публичният ключ и адресът ,който въведохте са разменени.',
			1002: 'Адресът не принадлежи към основната мрежа'
		},
		common: {
			success: 'Успешно',
			unknown: 'Неизвестен статус',
			unknownMessage: 'Ncc did not get response in a timely manner. Transaction might have been sent to the network.<br /><br />Please, check transactions before attempting to make it again.',
			appStatus: {
				nccUnknown: 'NCC статуса е неизвестен',
				nccUnavailable: 'NCC е недостъпен',
				nccStarting: 'NCC стартира...',
				nisUnknown: 'NIS статуса е неизвестен',
				nisUnavailable: 'NIS е недостъпен',
				nisStarting: 'NIS стартира...',
				notBooted: 'NIS трябва да бъде презареден. Моля отворете портфейла и презаредете локалния възел чрез изплуващия прозорец или конфигурирайте ауто зареждащите настройки',
				loading: 'Зареждане на блоковете от db до блок: ',
				booting: 'Зарежда NIS...',
				nisInfoNotAvailable: 'NIS инфо не е все още на разположение.Опитвам се да получа отново NIS инфо...',
				synchronizing: 'NIS се синхронизира. В блок {{1}}, около {{2}} назад.',
				daysBehind: {
					0: 'по малко от 1 ден',
					1: '1 ден',
					many: '{{1}} дни'
				},
				synchronized: 'NIS е синхронизиран!',
				noRemoteNisAvailable: 'Не е намерен отдалечен NIS в мрежата,изключен от интернет?'
			},
			addressBook: 'Адрес бук',
			password: 'Парола',
			passwordValidation: 'Паролата не трябва да е празна',
			address: 'Адрес',
			privateLabel: 'Частна маркировка',
			publicLabel: 'Публична маркировка',
			noCharge: 'Този акаунт <b>Няма</b> да бъде обложен с никакви такси, мултисигнатурният акаунт ги покрива.',
			fee: 'Такса',
			justUse: 'Просто използвайте',
			dueBy: 'Време до края',
			hours: 'час(а)',
			hoursDue: 'Време до края (часове)',
			hoursDueExplanation: 'If the transaction isn\'t included by the deadline, it is rejected.',
			closeButton: 'Close'
		},
		transactionTypes: [
			'ПРЕХВЪРЛИ ТРАНЗАКЦИЯТА',
			'ВАЖНО ПРЕХВЪРЛЯНЕ',
			'МОДИФИКАЦИЯ НА МУЛТИСИГНАТУРНИЯ АКАУНТ',
			'МУЛТИСИГНАТУРНА ТРАНЗАКЦИЯ'
		],
		transactionDirections: {
			pending: 'Чакаща транзакция',
			outgoing: 'Изходяща транзакция',
			incoming: 'Входяща транзакция',
			self: 'Собственна транзакция',
			importance: 'Важна транзакция',
			modification: 'Сумарна Модификация на Мултисигнатурата'
		},
		modals: {
			error: {
				title: 'Oops!',
				caption: 'Грешка {{1}}'
			},
			yikes: {
				title: 'Олеле!',
				caption: 'Инфо код {{1}}'
			},
			confirmDefault: {
				yes: 'Да',
				no: 'Не'
			},
			settings: {
				title: 'Настройки',
				language: {
					label: 'Език'
				},
				remoteServer: {
					tabTitle: 'Отдалечен сървър',
					protocol: 'Протокол',
					protocolOptions: {
						http: 'HTTP'
					},
					host: 'Хост',
					port: 'Порт',
					defaultPort: 'Използвайте порта по подразбиране.'
				},
				autoBoot: {
					tabTitle: 'Авто зареждане',
					name: 'Име на възела',
					account: 'Акаунт',
					primaryAccount: 'Главен Акаунт',
					auto: 'Авто зареждане когато се отвори портфейла'
				},
				save: 'Запази',
				saveSuccess: 'Настройките бяха успешно запазени'
			},
			multisig: {
				title: 'Превърни акаунта в мултисигнатурен',
				multisigAccount: 'Мултисигнатурен акаунт',
				cosignatories: 'Косигнатурни адреси',
				labelDesc: 'Този акаунт е маркиран като {{1}}',
				nullLabelDesc: 'Този акаунт няма маркировка',
				addCosignatory: '+ Добави косигнатура',
				cancel: 'Отмени',
				convert: 'Конвертирай',
				fee: 'Такса',
				feeValidation: 'Таксата трябва да бъде не по малка от минималната',
				useMinimumFee: 'Ползвай минимална такса',
				txConfirm: {
					title: 'Потвърди Преобразуването в Мултисигнатурен Акаунт',
					total: 'Общо',

				},
				warning: 'Мултисигнатурния акаунт е в списъка на косигнатурите.Това ще предизвика блокиране на акаунта и прекъсване досъпа до фонда му.Вие навярно <b>НЕ ИСКАТЕ </b> да направите това.'
			},
			signMultisig: {
				title: 'Подпиши мултисигнатурна транзакция',
				original: {
					from: 'Мултисигнатурен акаунт',
					to: 'Получател',
					amount: 'Сума',
					fee: 'Вътрешна такса',
					deadline: 'Краен срок'
				},
				multisigFees: 'Мултисигнатурна такса',
				multisigTotal: 'Общо',
				sender: 'Косигнатура',
				fee: 'Такса',
				feeValidation: 'Таксата не трябва да бъде по малка от минималната',
				useMinimumFee: 'Ползвай минимална такса',
				password: 'Парола',
				passwordValidation: 'Паролата не може да бъде празна',
				send: 'Изпрати',
				cancel: 'Отмени',
				sending: 'Изпращам...',
				successMessage: 'Транзакцията беше изпратена успешно!',
				txConfirm: {
					title: 'Потвърди Мултисигнатурната Транзакция',
					message: 'Съобщение',
					encrypted: 'Съобщението е криптирано',
					noMessage: 'Няма съобщение',

				}
			},
			sendNem: {
				title: 'Изпрати XEM',
				sender: 'Изпращач',
				thisAccount: 'Този акаунт',
				labelDesc: 'Този акаунт е маркиран като {{1}}',
				nullLabelDesc: 'Този акаунт не е маркиран',
				amount: 'Сума',
				recipient: 'Акаунт на Получателя',
				recipientValidation: 'Акаунт адреса трябва да е 40 символа дълъг като се изключат тиретата',
				message: 'Съобщение',
				encrypt: 'Криптирано съобщение',
				fee: 'Такса',
				multisigFee: 'Мултисигнатурна такса',
				feeValidation: 'Таксата не трябва да бъде по малка от минималната такса',
				useMinimumFee: 'Ползвай минималната такса',
				password: 'Парола',
				passwordValidation: 'Паролата не трябва да е празна',
				send: 'Изпрати',
				cancel: 'Отказ',
				sending: 'Изпращам...',
				successMessage: 'Транзакцията беше изпратена успешно!',
				txConfirm: {
					title: 'Потвърдете транзакцията',
					amount: 'Сума',
					to: 'До',
					total: 'Общо',
					message: 'Съобщение',
					encrypted: 'Съобщението е криптирано',
					noMessage: 'Без съобщение',
					cancel: 'Отмени',
					confirm: 'Потвърди',
					sending: 'Изпращам...'
				},
				notBootedWarning: {
					title: 'Възелът не бе стартиран!',
					message: 'Локалният възел трябва да бъде стартиран преди да можете да изпратите XEM'
				},
				bootingWarning: {
					title: 'Възелът се стартира',
					message: 'Моля почакайте докато възелът се стартира за да изпратите вашата транзакция.'
				},
				loadingWarning: {
					title: 'Зареждане на db'
				}
			},
			clientInfo: {
				title: 'Клиент инфо',
				ncc: 'NEM Community Client - NCC',
				signer: 'Притежател на подписа',
				remoteServer: 'Отдалечен сървър',
				local: 'Локален',
				nis: 'NEM Infrastructure Server - NIS',
				sync: 'Синхронизиран',
				notSync: 'Не синхронизиран',
				notConnected: 'Не съединен с NEM облака',
				loading: 'Зареждане...'
			},
			transactionDetails: {
				title: 'Детайли на транзакцията',
				id: 'ID',
				hash: 'Hash',
				type: 'Тип на транзакцията',
				direction: 'Посока на Транзакцията',
				pending: 'В очакване',
				outgoing: 'Изходящи',
				incoming: 'Входящи',
				self: 'Собственна',
				sender: 'Изпращач',
				multisigAccount: 'Мултисигнатурен Акаунт',
				issuer: 'Емитент',
				recipient: 'Получател',
				remote: 'Отдалечен',
				multisigMessage: 'Сигнатури на лице',
				message: 'Съобщение',
				noMessage: 'Няма съобщение',
				encrypted: 'Съобщението е криптирано',
				time: 'Време на създаване',
				confirmations: 'Потвърждения',
				confirmationsUnknown: 'Неизвестни',
				amount: 'Сума',
				fee: 'Такса',
				innerFee: 'Вътрешна такса',
				multisigFees: 'Мултисигнатурни такси',
				cosignatory: 'Косигнатура'
			},
			accountDetails: {
				title: 'Детайли на акаунта',
				address: 'Адрес',
				label: 'Маркировка',
				noLabel: 'Без маркировка',
				add: 'Прибави в адрес бука',
				remove: 'Премахни от адрес бука',
				balance: 'Баланс',
				vested: 'утвърдени',
				importance: 'Важност',
				publicKey: 'Публичен ключ',
				noPublicKey: 'Няма публичен ключ',
				harvestedBlocks: 'Генерирани блокове',
				close: 'Затвори'
			},
			bootLocalNode: {
				title: 'Стартирайте локалния възел.',
				account: 'Акаунт за стартиране на локалния възел.',
				noLabel: '<span class="null">&lt;Не маркиран&gt;</span>',
				wallet: 'Портфейл',
				node: 'Име на локалния възел',
				boot: 'Старт',
				booting: 'Стартиране...',
				warning: 'Предупреждение за зареждането на възела',
				warningText: 'You\'re trying to boot a node <u>{{2}}</u><br/><br/>Booting remote node is currently impossible from within NCC.',
				warningStatement: 'You have auto-boot set to true and you\'re using remote node {{3}}.<br/><br/>Booting remote node is currently impossible from within NCC',
				warningQuestion: 'Сигурни ли сте ,че искате да заредите възела <u>{{3}}</u> ползвайки частния ключ на акаунта {{1}} ({{2}} XEM)?<br><br>Това ще разкрие на този акаунт <span class="sublabelWarning">частния ключ</span> във възела: <u>{{3}}</u>.'
			},
			closeWallet: {
				title: 'Затваряне на портфейла',
				message: 'Сигурни ли сте че искате да затворите портфейла и да се върнете на главната страница?'
			},
			createAccount: {
				title: 'Създайте нов акаунт',
				label: 'Частна маркировка',
				wallet: 'Портфейл',
				password: 'Парола на портфейла',
				successMessage: 'Акаунтът {{1}} {{#2}}({{2}}){{/2}} беше създаден!',
				create: 'Създай'
			},
			showPrivateKey: {
				title: 'Show Account\'s PRIVATE Key',
				message: 'This will display account\'s private key on the screen, as a text. In case of any malware present in the system, this might be hazardous operation. Are you sure you want to do that?',
				publicKey: 'Публичен ключ',
				privateKey: 'Частен ключ',
				show: 'Show the key'
			},
			showRemotePrivateKey: {
				title: 'Show Remote Account\'s PRIVATE Key',
				message: 'This will display remote account\'s private key on the screen, as a text. In case of any malware present in the system, this might be hazardous operation. Are you sure you want to do that?',

			},
			addAccount: {
				title: 'Добавете съществуващ акаунт',
				privateKey: 'Частен ключ на акаунта',
				wallet: 'Портфейл',
				password: 'Парола на портфейла',
				successMessage: 'Акаунтът {{1}} {{#2}}({{2}}){{/2}} беше добавен към портфейла!',
				add: 'Добави',
				label: 'Маркировка'
			},
			setPrimary: {
				title: 'Задаване като главен акаунт',
				account: 'Задайте като главен акаунт',
				noLabel: '<span class="null">&lt;Без маркировка&gt;</span>',
				wallet: 'Портфейл',
				password: 'Парола на портфейла',
				successMessage: 'Акаунтът {{1}} {{#2}}({{2}}){{/2}} беше зададен като главен!',
				set: 'Задай като главен'
			},
			changeWalletName: {
				title: 'Сменете името на портфейла',
				wallet: 'Настоящо име на портфейла',
				newName: 'Ново име на портфейла',
				password: 'Парола на портфейла',
				successMessage: 'Името на портфейла е успешно сменено от <em>{{1}}</em> на <em>{{2}}</em>',
				change: 'Смени'
			},
			changeWalletPassword: {
				title: 'Сменете паролата на портфейла',
				wallet: 'Портфейл',
				password: 'Текуща парола',
				newPassword: 'Нова парола',
				confirmPassword: 'Повторете новата парола',
				successMessage: 'Паролата на портфейла беше успешно сменена',
				change: 'Смени',
				passwordNotMatchTitle: 'Oops!',
				passwordNotMatchMessage: 'Въведената от вас парола и потвърдената парола не съвпадат.Моля убедете се ,че правилно сте написали новата парола.'
			},
			changeAccountLabel: {
				title: 'Сменете маркировката на акаунта',
				label: 'Маркировка на акаунта',
				wallet: 'Портфейл',
				password: 'Парола на портфейла',
				successMessage: 'Акаунтът {{1}} сега е маркиран като {{2}}',
				change: 'Смени'
			},
			removeAccount: {
				title: 'Премахнете акаунта',
				account: 'Акаунт',
				label: 'Маркировка на акаунта',
				wallet: 'Свързан портфейл',
				password: 'Парола на портфейла',
				warning: 'Моля уверете се че нямате XEM в акаунта преди да го премахнете иначе те ще бъдат изгубени завинаги.',
				successMessage: 'Акаунтът {{1}} {{#2}}({{2}}){{/2}} беше премахнат!',
				remove: 'Премахни'
			},
			nisUnavailable: {
				title: 'NIS е недостъпен',
				message: 'NIS е прекъснат,очакване на включване'
			},
			shutdown: {
				title: 'Затворете програмата',
				message: 'Сигурни ли сте че искате да затворите NEM Community Client?'
			},
			activateRemote: {
				title: 'Activate Delegated Harvesting',
				wallet: 'Портфейл',
				account: 'Акаунт',
				password: 'Парола на портфейла',
				activate: 'Активирай',
				warning: 'Warning',
				warningText: 'Activation will take 6 hours (360 blocks). Activation will NOT start harvesting automatically.'
			},
			deactivateRemote: {
				title: 'Deactivate Delegated Harvesting',
				wallet: 'Портфейл',
				account: 'Акаунт',
				password: 'Парола на портфейла',
				deactivate: 'Деактивирай',
				warning: 'Warning',
				warningText: 'Deactivation will take 6 hours (360 blocks).'
			},
			startRemote: {
				title: 'Start Delegated Harvesting',
				wallet: 'Портфейл',
				account: 'Акаунт',
				password: 'Парола на портфейла',
				start: 'Старт'
			},
			stopRemote: {
				title: 'Stop Delegated Harvesting',
				wallet: 'Портфейл',
				account: 'Акаунт',
				password: 'Парола на портфейла',
				stop: 'Стоп'
			},
			logoutWarning: {
				leavePage: "You're leaving your wallet. Remember that if you leave your wallet this way, some others may still be able to access your wallet from this computer. To prevent that from happening, please log out using the \"Close wallet\" menu item in the top-right dropdown menu before you close the browser tab or navigate away."
			},
			addContact: {
				title: 'Добави контакт',
				add: 'Добави'
			},
			editContact: {
				title: 'Редактирай контакта',
				saveChanges: 'Запази промените'
			},
			removeContact: {
				title: 'Премахни контакта',
				remove: 'Премахни'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'Портфейлът беше успешно импортнат!',
			nav: {
				start: 'Първи стъпки',
				about: 'Относно NEM',
				settings: 'Настройки'
			},
			main: {
				leftTitle: 'Нов за <em>NEM</em>?',
				leftButton: 'Създай нов портфейл',
				walletNamePlh: 'Име на вашия портфейл',
				passwordPlh: 'Парола',
				confirmPasswordPlh: 'Потвърдете паролата',
				create: 'Създай',
				creating: 'Създаване...',
				rightTitle: 'Вече сте <em>NEM</em>ber?',
				rightButton: 'Отворете вашия портфейл',
				openButton: 'Отвори',
				walletsFound: 'Намерени <strong>{{1}}</strong> <em>Портфейл(и)</em>',
				copyright: 'Фотография от <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC криптира твоя портфейл',
						description: '<em>Сигурността на портфейла </em> е най важна за предотвратяването на кражба на XEM монети и активи.'
					},
					{
						title: 'NCC криптира твоя портфейл',
						description: '<em>Сигурността на портфейла </em> е най важна за предотвратяването на кражба на XEM монети и активи.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'Как работи NCC?',
						paragraphs: [
							'<strong>NCC</strong> предоставя достъп до вашите активи и XEM като обикновен портфейл.Вие можете',
							'<strong>NCC</strong> изисква достъп до <strong>NIS</strong> сървър за да може да работи.Стандартно е да има локален сървър и той трябва да е активен(той се инсталира заедно <strong>NCC</strong>)',
							'Вие също може да конфигурирате достъп до отдалечен <strong>NIS</strong>.'
						],
						listItems: [
							'Да имате няколко портфейла',
							'Да дефинирате няколко акаунта да се съдържат в един портфейл.'
						]
					},
					{
						title: 'Какво е &#42;NIS?',
						paragraphs: [
							'Този компонент отговаря за функционирането на <strong>NEM</strong> облака.',
							'The more <strong>NIS</strong> there are in the network, the better the security.,',
							'<strong>NIS</strong> това е точката за достъп за <strong>NEM</strong> облака.'
						],
						legend: '<strong>&#42;NIS</strong> се разшифрова <strong>NEM Infrastructure Server</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2015. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: 'Около {{1}} дни назад',
			lastAccessJustNow: 'Точно сега',
			lastAccessTooltip: 'Последният достъп е бил {{1}}',
			primary: 'Главен',
			primaryShort: 'Г',
			noLabel: '<Без маркировка>',
			copiedToClipboard: 'Адресът беше копиран в клипборда!',
			actions: {
				refreshInfo: 'Обновяване на информацията',
				bootLocalNode: 'Стартиране на локалния възел',
				changeWalletName: 'Промяна името на портфейла',
				changeWalletPassword: 'Промяна паролата на портфейла',
				mergeWallets: 'Обединяване на портфейли',
				exportWallet: 'Експорт на портфейла',
				createAccount: 'Създаване на нов акаунт',
				createRealAccountData: 'Създайте данните на реалния акаунт',
				verifyRealAccountData: 'Проверете данните на реалния акаунт',
				showPrivateKey: 'Show Account\'s PRIVATE key',
				showRemotePrivateKey: 'Show Remote Account\'s PRIVATE key',
				viewDetails: 'View Account Details',
				addAccount: 'Добавяне на съществуващ акаунт',
				changeAccountLabel: 'Промяна на маркировката на акаунта',
				setPrimary: 'Задай като главен акаунт',
				removeAccount: 'Премахни акаунта',
				clientInfo: 'Информация за клиента',
				closeWallet: 'Затвори портфейла',
				closeProgram: 'Затвори програмата',
				copyClipboard: 'Копирай адреса в клипборда',
				copyDisabled: 'Copying an address requires flash',
				convertMultisig: 'Превърни друг акаунт в мултисигнатурен'
			},
			nav: [
				'Панел за управление',
				'Съобщения',
				'Адрес Бук',
				'Транзакции',
				'Генерирани блокове',
				'Обмен на активи',
				'Новини',
				'Приложения',
				'Акаунти',
				'Настройки',
				'Затворете програмата'
			],
			bootNodeWarning: 'За пълното функциониране на NCC е нужно локалният възел да се рестартира.'
		},
		dashboard: {
			assets: {
				title: 'Вашите активи'
			},
			importance: {
				title: 'Важен резултат',
				unknown: 'Неизвестен статус',
				start: 'Старт на локалното генериране',
				harvesting: 'Генериране',
				stop: 'Стоп на локалното генериране',
				description: 'Важност на акаунта за NEM облака',
				remoteHarvest: {
					activate: 'Activate delegated harvesting',
					activating: 'Activating delegated harvesting...',
					active: 'Delegated harvesting is active',
					deactivate: 'Deactivate delegated harvesting',
					deactivating: 'Deactivating delegated harvesting...',
					startRemoteHarvesting: 'Start delegated harvesting',
					remotelyHarvesting: 'Отдалечено генериране',
					stopRemoteHarvesting: 'Stop delegated harvesting'
				}
			},
			transactions: {
				title: 'Скорошни транзакции',
				sendNem: 'Изпрати XEM',
				signMultisig: 'Подпиши',
				balance: 'Текущ баланс',
				loading: 'Loading balance',
				accountCosignatories: 'Мултисигнатурен акаунт',
				accountCosignatoriesView: 'view cosignatories',
				vestedBalance: 'Утвърден Баланс',
				syncStatus: '( Блок {{1}}{{#2}} : около {{3}} дена на зад {{/2}})',
				notSynced: 'might be inaccurate, NIS not synchronized yet',
				unknown: 'неизвестно',
				columns: [
					'',
					'Време',
					'Изпращач/Получател',
					'Съобщение',
					'',
					'Детайли',
					'Потвърждения',
					'Такса',
					'Сума'
				],
				noMessage: 'Няма съобщение',
				encrypted: 'Съобщението е криптирано',
				view: 'Преглед',
				confirmationsUnknown: 'Неизвестни',
				pending: 'В очакване',
				seeAll: 'Виж всички транзакции',
				noTransactions: 'Не е направена нито една транзакция все още'
			},
			nemValue: {
				title: 'XEM статистически стойности'
			},
			messages: {
				titleTooltip: 'Съобщения'
			},
			news: {
				titleTooltip: 'Новини'
			},
			notAvailable: 'Още е недостъпно в бета версията'
		},
		transactions: {
			title: 'Транзакции',
			sendNem: 'Изпрати XEM',
			balance: 'Текущ баланс',
			filters: {
				confirmed: 'Потвърдени',
				unconfirmed: 'Непотвърдени',
				incoming: 'Входящи',
				outgoing: 'Изходящи'
			},
			table: {
				columns: [
					'',
					'Време',
					'Изпращач/Получател',
					'Съобщение',
					'',
					'Детайли',
					'Потвърждения',
					'Такса',
					'Сума'
				],
				noMessage: 'Няма съобщение',
				encrypted: 'Съобщението е криптирано',
				view: 'Преглед',
				confirmationsUnknown: 'Неизвестни',
				pending: 'В очакване',
				noTransactions: 'Не е направена нито една транзакция все още',
				loading: 'Зареждане на още транзакции...'
			}
		},
		harvestedBlocks: {
			title: 'Генерирани блокове',
			feeEarned: 'Такси събрани от последните 25 генерирани блока',
			unknown: 'Неизвестно',
			table: {
				columns: [
					'Височина',
					'Време',
					'Блок трудност',
					'Такса'
				],
				noBlocks: 'Няма генерирани блокове все още',
				loading: 'Зареждане на още генерирани блокове'
			},
			harvesting: {
				unknown: 'Неизвестен статус',
				start: 'Старт на локалното генериране ',
				harvesting: 'Генериране',
				stop: 'Стоп на локалното генериране',
				remoteHarvest: {
					startRemoteHarvesting: 'Start delegated harvesting',
					stopRemoteHarvesting: 'Stop delegated harvesting'
				}
			}
		},
		addressBook: {
			title: 'Адрес бук',
			addContact: 'Добави контакт',
			table: {
				columns: [
					'Адрес на акаунта',
					'Частна маркировка',
					'Публична маркировка'
				],
				noContacts: 'Няма контакти във Вашия адрес бук'
			},
			noLabel: 'Няма маркировка',
			sendNem: 'Изпрати XEM',
			edit: 'Редактирай',
			remove: 'Премахни'
		},
		settings: {
			title: 'Настройки',
			settings: [
				{
					name: 'Език'
				}
			],
			save: 'Запази промените',
			saveSuccess: 'Настройките бяха успешно запазени'
		}
	}
});
