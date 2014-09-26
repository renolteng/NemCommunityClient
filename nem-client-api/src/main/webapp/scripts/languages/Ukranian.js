define({
	id: 'UA',
	name: 'Ukrainian',
	texts: {
		preferences: {
			thousandSeparator: '\u2009',
			decimalSeparator: '.'
		},
		faults: {
            101: 'Файл не знайдено.',
            102: 'Гаманець не був створений.',
            103: 'Файл гаманця пошкоджений. Будь ласка відновіть файл вашого гаманця з резерву, який ви повинні були створити при створенні гаманця, або створiть новий акаунт .',
            104: 'Введений пароль невірний. Сподіваємося, ви зможете згадати вірний пароль. Пароль не може бути відновлений, якщо він був втрачений',
            106: 'Перш ніж ви зможете працювати з гаманцем, він повинен бути відкритий. Щоб переконатися, що ви авторизованi на доступ до файлу гаманця, ви повинні ввести пароль гаманця.',
            107: 'Гаманець не містить цей акаунт.',
            108: 'Акаунт не може бути видалений. Швидше за все тому, що баланс рахунку все ще перевищує 0 NEM, або акаунт, який ви намагаєтеся видалити є основним.',
            109: 'Гаманець з таким ім'ям вже існує. Будь ласка, виберіть інше ім'я гаманця.',
            110: 'Гаманець вже містить цей акаунт.',
            202: 'Зашифроване повідомлення не може бути відправлено, так як одержувач ніколи не здійснював жодної транзакції.',
            305: 'NEM Infrastructure Server не досяжний.',
            306: 'Сталася помилка, не передбачувана командою розробникiв. Вибачте за це, та повторiть спробу ще раз. В іншому випадку, будь ласка, відкрийте питання у співтовариствi NEM NIS/NCC.',
            400: 'Деякі параметри відсутні, або недійснi.',
            404: 'Запитаний ресурс не знайдено.',
            500: 'Не вдалося зберегти файл налаштувань.',
            600: 'NCC вимагає запущений сервер NIS для надсилання та отримання транзакцiй з NEM cloud. Будь ласка скористайтеся меню NCC для запуску локального вузла.',
            601: 'Сервер NIS вже запущено. Завантажити NIS вдруге неможливо.',
            700: ' Акаунт не відповідає основним критеріям для початку харвестiнгу. Для початку генерації блоків на рахунку має бути щонайменьше 1000 NEM.',
            701: 'Встановленний крайній термін вже в минулому. Термін повинен бути в рамках 1-денного періоду.',
            702: 'Встановленний крайній термін занадто далеко в майбутньому. Термін повинен бути в рамках 1-денного періоду.',
            703: 'Баланс вашого рахунку не дозволяє вiдправити вказану кiлькiсть NEM.',
            704: 'Вказане текстове повiдомлення занадто велике, щоб бути вiдправленим через мережу NEM. Будь ласка, спробуйте зменшити довжину повідомлення, яке потрібно відправити.',
            705: 'Хеш тразакції вже існує в базі даних або в списку непідтверджених операцій.',
            706: 'Підпис тразакції не може бути перевірений.',
            707: 'Мітка часу транзакції занадто далеко в минулому.',
            708: 'Мітка часу транзакції занадто далеко в майбутньому.',
            709: 'Рахунок невідомий мережi. Рахунок повинен бути частиною щонайменше однієї угоди (відправником або одержувачєм), щоб стати відомим в мережі.',
            901: 'При переході в автономний режим сталася помилка.'
        },
        common: {
        	success: 'Успiшно', //title of the Success message modals
        	nisStatus: {
        		nccUnavailable: 'NCC is not available',
        		unavailable: 'NIS не досяжний',
        		booting: 'Booting NIS...',
        		notBooted: 'NIS має бути запущений. Будь ласка, відкрийте гаманець і за допомогою спливаючого меню запустiть локальний вузол.',
        		retrievingStatus: 'Retrieving NIS status...',
        		synchronizing: 'NIS синхронізується. Блок {{1}}, приблизно. {{2}} днiв.'
				daysBehind: {
        			0: 'less than 1 day',
        			1: '1 day',
        			many: '{{1}} days'
        		},
        		synchronized: 'NIS is synchronized!'
        	}
        },
		modals: {
			error: {
				title: 'От халепа!',
				caption: 'Помилка {{1}}'
			},
			confirmDefault: {
				yes: 'Так',
				no: 'Нi'
			},
			settings: {
				title: 'Settings',
				language: {
					label: 'Language'
				},
				remoteServer: {
					tabTitle: 'Remote Server',
					protocol: 'Protocol',
					protocolOptions: [
						{
							value: 'http', // please dont't change
							display: 'HTTP'
						},
						{
							value: 'https', // please dont't change
							display: 'HTTPS'
						}
					],
					host: 'Host',
					port: 'Port'
				},
				autoBoot: {
					tabTitle: 'Auto-boot',
					nodeOptions: [
						{
							value: 'Local NIS', // please dont't change
							display: 'Local NIS'
						}
					],
					name: 'Node name',
					account: 'Account',
					primaryAccount: 'Primary Account',
					auto: 'Auto boot when a wallet is opened'
				},
				save: 'Save',
				saveSuccess: 'Settings have been saved successfully'
			},
			sendNem: {
				title: 'Відправити NEM',
				labelDesc: 'Цей акаунт позначений як {{1}}',
				nullLabelDesc: "Цей акаунт не має позначки",
				amount: 'Кiлькiсть',
				recipient: "Акаунт одержувача",
				message: 'Повiдомлення',
				encrypt: 'Зашифрованне повiдомлення',
				fee: 'Комісія',
				dueBy: 'Протягом',
				resetFee: 'Встановити мiнiмальну комiсiю',
				hours: 'Годин',
				password: 'Парольна фраза',
				send: 'Вiдправити',
				sending: 'Вiдправлення...',
				successMessage: 'Транзакцiя пройшла успішно!'
			},
			clientInfo: {
				title: 'Iнформацiя',
				ncc: 'NEM Community Client - NCC',
				signer: 'Пiдписувач',
				remoteServer: 'Віддалений сервер',
				local: 'Локальний',
				nis: 'NEM Infrastructure Server - NIS',
				sync: 'Синхронiзований',
				notSync: 'Не синхронiзований',
				notConnected: 'не підключений до NEM Cloud',
				loading: 'Завантаження...'
			},
			transactionDetails: {
				title: 'Деталi транзакцiї',
				// this might be block or transaction ID
				id: 'Блок або ідентифікаційний код транзакцiї',
				// this might be block or transaction Hash
				hash: 'Хеш транзакцiї',
				type: 'Тип транзакцiї',
				pending: 'В очiкуваннi',
				outgoing: 'Вихiдна',
				incoming: 'Вхiдна',
				self: 'Собi',
				sender: 'Вiдправник',
				recipient: 'Одержувач',
				message: 'Повiдомлення',
				noMessage: 'Без повiдомлення',
				encrypted: 'Повiдомлення зашифроване',
				time: 'Час',
				confirmations: 'Пiдтверджень',
				amount: 'Кiлькiсть',
				fee: 'Комісія'
			},
			bootLocalNode: {
				title: 'Завантажити локальний вузол',
				account: 'Акаунт, з якого буде завантажено локальний вузол',
				noLabel: '<span class="null">&lt;Без позначки&gt;</span>',
				wallet: 'Гаманець',
				node: 'Iм'я локального вузла',
				boot: 'Завантажити',
				booting: 'Завантаження...'
			},
			notBootedWarning: {
				title: 'Локальний вузол не був запущений!',
				message: 'Перед тим, як ви зможете вiдправляти NEM, треба завантажити локальний вузол!'
			},
			closeWallet: {
				title: 'Закрити гаманець',
				message: 'Ви впевнені, що хочете закрити свій гаманець і повернутися до початкової сторінки?'
			},
			createAccount: {
				title: 'Створити новий акаунт',
?				label: 'Приватна позначка',
				wallet: 'Гаманець',
				password: "Пароль файлу гаманця",
				successMessage: 'Акаунт {{1}} {{#2}}({{2}}){{/2}} був успiшно створений!',
				create: 'Створити'
			},
			addAccount: {
				title: 'Додати існуючій акаунт',
				privateKey: "Приватний ключ акаунта",
				wallet: 'Гаманець',
				password: "Пароль файлу гаманця",
				successMessage: 'Акаунт {{1}} {{#2}}({{2}}){{/2}} було успiшно додано до гаманця!',
				add: 'Додати',
				label: 'Позначка'
			},
			setPrimary: {
				title: 'Встановити основний акаунт',
				account: 'Акаунт буде встановлено як основний',
				noLabel: '<span class="null">&lt;Без позначки&gt;</span>',
				wallet: 'Гаманець',
				password: "Пароль файлу гаманця",
				successMessage: 'Акаунт {{1}} {{#2}}({{2}}){{/2}} був встановлений як основний!',
				set: 'Встановити як основний',
			},
			changeWalletName: {
				title: 'Змiнити iм'я гаманця',
				wallet: 'Поточне iм'я гаманця',
				newName: 'Нове iм'я гаманця',
				password: "Пароль файлу гаманця",
				successMessage: 'Iм'я гаманця було успiшно змiнено з <em>{{1}}</em> на <em>{{2}}</em>',
				change: 'Змiнити'
			},
			changeWalletPassword: {
				title: 'Змiнити пароль гаманця',
				wallet: 'Гаманець',
				password: 'Дiючий пароль',
				newPassword: 'Новий пароль',
				confirmPassword: 'Підтвердiть новий пароль',
				successMessage: 'Пароль гаманця успiшно змiнено',
				change: 'Змiнити',
				passwordNotMatchTitle: 'ой лишенько!',
				passwordNotMatchMessage: 'Ваш введений пароль і підтвердження пароля не збігаються. Будь ласка, будьте уважнiше!'
			},
			changeAccountLabel: {
				title: 'Змiнити позначку акаунта',
				label: 'Позначка акаунта',
				wallet: 'Гаманець',
				password: "Пароль файлу гаманця",
				successMessage: 'Акаунт {{1}} тепер позначений як {{2}}',
				change: 'Змiнити'
			},
			removeAccount: {
				title: 'Видалити акаунт',
				wallet: 'Гаманець',
				password: "Пароль файлу гаманця",
				warning: 'Будь ласка переконайтеся, що на вашому рахунку не залишилося коштiв. В іншому випадку вони будуть втраченi назавжди.',
				successMessage: 'Акаунт {{1}} {{#2}}({{2}}){{/2}} видалено!',
				remove: 'Видалити'
			},
			nisUnavailable: {
				title: 'NIS недосяжний',
				message: 'Від'єднаний вiд NIS, очікування з'єднання'
			},
			shutdown: {
				title: 'Закрити програму',
				message: 'Ви впевнені, що хочете закрити клiент NEM?'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'Гаманець був успішно імпортований!',
			nav: {
				start: 'Приступаемо до роботи',
				about: 'Про NEM',
				settings: 'Settings'
			},
			main: {
				leftTitle: 'Новий у мережi <em>NEM</em>?',
				leftButton: 'Створити новий гаманець',
				walletNamePlh: 'Iм'я гаманця',
				passwordPlh: 'Пароль',
				create: 'Створити',
				rightTitle: 'Вже зареестрованi у мережi <em>NEM</em>?',
				rightButton: 'Вiдкрити мiй гаманець',
				openButton: 'Вiдкрити',
				walletsFound: 'Знайдено <strong>{{1}}</strong> <em>гаманцiв</em>',
				copyright: 'Фото <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC шифрує ваш гаманець',
						description: '<em>Безпека</em> дуже важлива для запобігання крадіжки NEM монет &amp; активiв.'
					},
					{
						title: 'NCC шифрує ваш гаманець',
						description: '<em>Безпека</em> дуже важлива для запобігання крадіжки NEM монет &amp; активiв.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'Як працює NCC?',
						paragraphs: [
							'<strong>NCC</strong> надає доступ до ваших активів і NEM монет як традиційний гаманець. ви можете:',
							'Для корректної роботи <strong>NCC</strong> вимагає доступу до сервера <strong>NIS</strong>. зазвичай вiн запускається автоматично (встановлюється разом з <strong>NCC</strong>)',
							'Ви також можете налаштувати віддалений доступ до сервера <strong>NIS</strong>.'
						],
						listItems: [
							'Мати декiлька гаманцiв',
							'Визначити кiлька акаунтiв, якi будуть включенi в гаманець'
						]
					},
					{
						title: 'Що таке &#42;NIS?',
						paragraphs: [
							'Цей компонент вiдповiдає за підтримання працездатностi <strong>NEM</strong> cloud.',
							'Чим бiльша кiлькiсть <strong>NIS</strong>, тим краща безпека мережi.',
							'<strong>NIS</strong> є точкою доступу до <strong>NEM</strong> cloud.'
						],
						legend: '<strong>&#42;NIS</strong> розшифровується як <strong>NEM Infrastructure Server</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2014. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: 'Приблизно {{1}} днiв тому',
			lastAccessJustNow: 'Щойно',
			lastAccessTooltip: 'В останне заходили {{1}}',
			primary: 'Головний',
			primaryShort: 'Г',
			noLabel: '<Без позначки>',
			copiedToClipboard: 'Скопійовано!',
			actions: {
				refreshInfo: 'Оновити iнформацiю',
				bootLocalNode: 'Завантажити локальний вузол',
				changeWalletName: 'Змiнити iм'я гаманця',
				changeWalletPassword: 'Змiнити пароль гаманця',
				mergeWallets: 'Об'єднати гаманцi',
				exportWallet: 'Експортувати гаманець',
				createAccount: 'Створити новий акаунт',
				addAccount: 'Додати існуючий акаунт',
				changeAccountLabel: 'Змiнити позначку акаунта',
				setPrimary: 'Встановити основний акаунт',
				removeAccount: 'Видалити акаунт',
				clientInfo: 'Iнформацiя',
				closeWallet: 'Закрити гаманець',
				closeProgram: 'Закрити програму'
				copyClipboard: 'Cкопіювати адресу в буфер обміну'
			},
			nav: [
				'Головна сторiнка',
				'Повiдомлення',
				'Контакти',
				'Транзакцiї',
				'Сгенерованi блоки',
				'Бiржа активiв',
				'Новини',
				'Додатки',
				'Акаунти',
				'Налаштування',
				'Закрити програму'
			],
			bootNodeWarning: "Локальний вузол повинен бути завантажений, перш ніж ви можете повною мірою використовувати можливості NCC."
		},
		dashboard: {
			assets: {
				title: 'Вашi активи'
			},
			importance: {
				title: 'Оцінка важливостi',
				unknown: 'Статус невiдомий',
				start: 'Почати харвестiнг (генерацiю блокiв)',
				harvesting: 'Харвестiнг',
				stop: 'Зупинити харвестiнг',
				description: 'Важливість акаунта для NEM cloud'
			},
			transactions: {
				title: 'Останні транзакцiї',
				sendNem: 'Вiдправити NEM',
				balance: 'Поточний баланс',
				syncStatus: '(Блок {{1}}{{#2}} : прибл. {{3}} днів тому{{/2}})',
				unknown: 'Невiдомо',
				columns: [
					'',
					'Час',
					'Вiдправник/Отримувач',
					'Повiдомлення',
					'',
					'Деталi',
					'Підтвердженнь',
					'Комiсiя',
					'Кiлькiсть'
				],
				types: {
					pending: 'Очiкуючи транзакцiї',
					outgoing: 'Вихiднi транзакцiї',
					incoming: 'Вхiднi транзакцiї',
					self: 'Транзакцiї самому собi',
				},
				noMessage: 'Без повiдомлень',
				encrypted: 'Зашифроване повiдомлення',
				view: 'Переглянути',
				pending: 'В очiкуваннi',
				seeAll: 'Переглянути всi транзакцiї',
				noTransactions: 'Не було здiйснено жодної транзакцiї'
			},
			nemValue: {
				title: 'Статистика NEM'
			},
			messages: {
				titleTooltip: 'Повiдомлення'
			},
			news: {
				titleTooltip: 'Новини'
			},
			notAvailable: 'Не доступно в альфа-версії'
		},
		transactions: {
			title: 'Транзакцiї',
			sendNem: 'Відправити NEM',
			balance: 'Поточний баланс',
			filters: {
				all: 'Всi транзакцiї',
				pending: 'В очiкуваннi',
				incoming: 'Вхiднi',
				outgoing: 'Вихiднi',
			},
			table: {
				columns: [
					'',
					'Час',
					'Вiдправник/Отримувач',
					'Повiдомлення',
					'',
					'Деталi',
					'Підтвердженнь',
					'Комiсiя',
					'Кiлькiсть'
				],
				types: {
					pending: 'Очiкуючи транзакцiї',
					outgoing: 'Вихiднi транзакцiї',
					incoming: 'Вхiднi транзакцiї',
					self: 'Транзакцiї самому собi',
				},
				noMessage: 'Без повiдомлень',
				encrypted: 'Зашифроване повiдомлення',
				view: 'Переглянути',
				pending: 'В очiкуваннi',
				noTransactions: 'Не було здiйснено жодної транзакцiї',
				loading: 'Завантаження транзакцiй...'
			}
		},
		harvestedBlocks: {
			title: 'Сгенерованi блоки',
			feeEarned: 'Комісійні, отримані за останні 25 сгенерованих блоків',
			table: {
				columns: [
					'Порядковий номер',
					'Час',
					'Хеш блоку',
					'Комiсiя'
				],
				noBlocks: 'Немає сгенерованих блокiв ',
				loadMore: 'Переглянути попереднi сгенерованi блоки'
			},
			harvesting: {
				unknown: 'Статус невiдомий',
				start: 'Почати харвестiнг (генерацiю блокiв)',
				harvesting: 'Харвестiнг',
				stop: 'Зупинити харвестiнг'
			}
		},
		settings: {
			title: 'Налаштування',
			settings: [
				{
					name: 'Мова'
				}
			],
			save: 'Зберегти зміни',
			saveSuccess: 'Налаштування були успiшно збереженi'
		}
	}
});