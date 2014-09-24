define({
                id: 'ru',
                name: 'Русский',
                texts: {
                  preferences: {
                    thousandSeparator: '\u2009',
                    decimalSeparator: '.'
                  },
                            faults: {
                101: 'Файл не найден.',
                102: 'Кошелёк не создан.',
                103: 'Файл кошелька повреждён. Пожалуйста, восстановите Ваш файл кошелька из раннее сохранённого или добавьте новый аккаунт.',
                104: 'Введённый пароль неверен.  Попытайтесь вспомнить правильный пароль. Пароль не может быть восстановлен, если утерян!',
                106: 'Перед  тем, как Вы начнёте работать с кошельком, убедитесь , что он открыт. Чтобы получить доступ к кошельку, введите пароль.',
                107: 'Кошелёк не содержит данный аккаунт.',
                108: 'Аккаунт не может быть удалён .  Вероятнее всего, что аккаунт содержит на балансе более чем 0 NEM или аккаунт, который Вы пытаетесь удалить, является главным.',
                109: 'Кошелёк с таким же именем уже существует. Пожалуйста, выберете другое имя для кошелька.',
                110: 'Wallet already contains this account.',
                202: 'Отсутствует public key.',
                305: 'NEM Infrastructure Server  не доступен',
                306: 'Произошла непредвиденная ошибка . Приносим свои извинения, повторите попытку снова. В противном случае, обратитесь за поддержкой в NEM NIS/NCC Community.',
                400: 'Некоторые параметры отсутствуют',
                404: 'Неправильное значение загрузки',
                500: 'Невозможно сохранить файл конфигурации',
                600: 'Для отсылки и приёма транзакций, NCC требует перезагрузку NIS сервера. Пожалуйста, используйте меню NCC для перезагрузки в локальный узел.',
                601: 'NIS узел уже запущен. Повторная попытка перезагрузки NIS невозможна.',
                700: 'Предоставленный аккаунт не соответствует базовым требованиям для Генерации . В основном, это связанно с количеством NEM на счету.  Генерация начинается хотя бы с 1,000 NEM.',
                701: 'Предоставленный срок завершения в прошлом. Срок должен быть предоставлен в течении 1-дневного периода.',
                702: 'Предоставленный срок завершения находится  далеко в будущем. Срок должен быть предоставлен в течении 1-дневного периода.',
                703: 'Ваш аккаунт не располагает достаточным количеством средств для отправки этой суммы NEM.',
                704: 'Предоставленный текст сообщения слишком большой для отправки через NEM. Пожалуйста, сократите текст  для его отправки.',
                705: 'Хэш сделки уже существует или в базе данных, или в списке неподтвержденных операций.',
                706: 'Подпись сделки не может быть проверена.',
                707: 'Временная отметка транзакции слишком далеко в прошлом.',
                708: 'Временная отметка транзакции слишком далеко в будущем.',
                709: 'Неизвестный аккакунт. Аккаунт должен быть частью хотя бы одной транзакции (входящей или исходящей),  чтобы быть опознанным сетью.',
                901: 'There was an error setting up offline mode.'
            },
            common: {
                    success: 'Успешно', //title of the Success message modals
                    nisStatus: {
                                nccUnavailable: 'NCC is not available',
                                unavailable: 'NIS не доступен',
                                notBooted: 'NIS требует перезагрузки. Пожалуйста, откройте кошелёк и загрузитесь как локальный узел через всплывающий диалог.',
                                synchronizing: 'NIS is synchronizing. At block {{1}}, est. {{2}} behind.',
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
                                                   title: 'Oops!',
                                                   caption: 'ОШИБКА {{1}}'
                                       },
                                       confirmDefault: {
                                                   yes: 'Да',
                                                   no: 'Нет'
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
          name: 'Node name',
          account: 'Account',
          primaryAccount: 'Primary Account',
          auto: 'Auto boot when a wallet is opened'
        },
        save: 'Save',
        saveSuccess: 'Settings have been saved successfully'
      },
                                       sendNem: {
                                                   title: 'Послать NEM',
                                                   labelDesc: 'Этот аккаунт маркирован как {{1}}',
                                                   nullLabelDesc: "Этот аккаунт не имеет маркировки",
                                                   amount: 'Сумма',
                                                   recipient: "Аккаунт получателя",
                                                   message: 'Сообщение',
                                                   encrypt: 'Зашифрованное сообщение',
                                                   fee: 'Оплата',
                                                   dueBy: 'В течение',
                                                   resetFee: 'Сброс на минимальную оплату',
                                                   hours: 'часов',
                                                   password: 'Пароль',
                                                   send: 'Послать',
                                                   sending: 'Посылается...',
                                                   successMessage: 'Транзакция была успешно послана!'
                                       },
                                       clientInfo: {
                                                   title: 'Client info',
                                                   ncc: 'NEM Community Client - NCC',
                                                   signer: 'Владелец подписи',
                                                   remoteServer: 'Удалённый Сервер',
                                                   local: 'Локальный',
                                                   nis: 'NEM Infrastructure Server - NIS',
                                                   sync: 'Синхронизирован',
                                                   notSync: 'Не синхронизирован',
                                                       notConnected: 'Не соединён с  NEM Cloud',
                                                   loading: 'Загрузка...'
                                       },
                                       transactionDetails: {
                                                   title: 'Детали транзакции',
                                                   // this might be block or transaction ID
                                                   id: 'ID',
                                                   // this might be block or transaction Hash
                                                   hash: 'Хэш',
                                                   type: 'Тип транзакции',
                                                   pending: 'В ожидании',
                                                   outgoing: 'Исходящие',
                                                   incoming: 'Входящие',
                                                   self: 'Авто',
                                                   sender: 'Отправитель',
                                                   recipient: 'Получатель',
                                                   message: 'Сообщение',
                                                   noMessage: 'Нет сообщений',
                                                   encrypted: 'Сообщение зашифровано',
                                                   time: 'Время создания',
                                                   confirmations: 'Подтверждения',
                                                   amount: 'Сумма',
                                                   fee: 'Оплата'
                                       },
                                       bootLocalNode: {
                                                   title: 'Загрузить как локальный узел',
                                                   account: 'Аккаунт для загрузки в локальный узел',
                                                   noLabel: '<span class="null">&lt;Не маркированный&gt;</span>',
                                                   wallet: 'Кошелёк',
                                                   node: 'Имя узла',
                                                   boot: 'Загрузить',
                                                   booting: 'Загрузка...'
                                       },
                                       notBootedWarning: {
                                                   title: 'Узел не был загружен!',
                                                   message: 'Вы должны загрузить локальный узел перед тем, как Вы сможете посылать NEM!'
                                       },
                                       closeWallet: {
                                                   title: 'Закрыть кошелёк',
                                                   message: 'Вы уверены, что хотите закрыть кошелёк и возвратиться на главную страницу ?'
                                       },
                                       createAccount: {
                                                   title: 'Создать аккаунт',
                                                   label: 'Приватная маркировка',
                                                   wallet: 'Кошелёк',
                                                   password: "Пароль кошелька",
                                                   successMessage: 'Аккаунт {{1}} {{#2}}({{2}}){{/2}} был создан!',
                                                   create: 'Создать'
                                       },
                                       addAccount: {
                                                   title: 'Добавить Существующий Аккаунт',
                                                   privateKey: "Частный Ключ Аккаунта ",
                                                   wallet: 'Кошелёк',
                                                   password: "Пароль Аккаунта",
                                                   successMessage: 'Аккаунт {{1}} {{#2}}({{2}}){{/2}} был добавлен кошелёк!',
                                                   add: 'Добавить',
                                                   label: 'Маркировка'
                                       },
                                       setPrimary: {
                                                   title: 'Установить как Главный Аккаунт',
                                                   account: 'Установить аккаунт как Главный',
                                                   noLabel: '<span class="null">&lt;нет маркировки&gt;</span>',
                                                   wallet: 'Кошелёк',
                                                   password: "Пароль кошелька",
                                                   successMessage: 'Аккаунт {{1}} {{#2}}({{2}}){{/2}} был установлен как Главный!',
                                                   set: 'Установлен как главный',
                                       },
                                       changeWalletName: {
                                                   title: 'Изменить имя кошелька',
                                                   wallet: 'Текущее имя кошелька',
                                                   newName: 'Новое имя кошелька',
                                                   password: "Пароль кошелька",
                                                   successMessage: 'Имя кошелька было успешно изменено с <em>{{1}}</em> на <em>{{2}}</em>',
                                                   change: 'Изменить'
                                       },
                                       changeWalletPassword: {
                                                   title: 'Изменить пароль к  кошельку',
                                                   wallet: 'Кошелёк',
                                                   password: 'Текущий пароль',
                                                   newPassword: 'Новый пароль',
                                                   confirmPassword: 'Подтвердить новый пароль',
                                                   successMessage: 'Пароль от кошелька был успешно изменён',
                                                   change: 'Изменить',
                                                   passwordNotMatchTitle: 'Oops!',
                                                   passwordNotMatchMessage: 'Введённый Вами пароль и подтверждение пароля не совпадают. Пожалуйста убедитесь, что Вы правильно написали новый пароль.'
                                       },
                                       changeAccountLabel: {
                                                   title: 'Изменить маркировку аккаунта',
                                                   label: 'Маркировка аккаунта',
                                                   wallet: 'Кошелёк',
                                                   password: "Пароль от кошелька",
                                                   successMessage: 'Аккаунт {{1}} теперь маркирован как {{2}}',
                                                   change: 'Изменить'
                                       },
                                       removeAccount: {
                                                   title: 'Удалить аккаунт',
                                                   wallet: 'Кошелёк',
                                                   password: "Пароль от кошелька",
                                                   warning: 'Пожалуйста  убедитесь, что Ваш аккаунт не имеет NEM монет, прежде чем Вы удалите его, или они будут потеряны навсегда.',
                                                   successMessage: 'Аккаунт {{1}} {{#2}}({{2}}){{/2}} был удалён!',
                                                   remove: 'Удалить'
                                       },
                                       nisUnavailable: {
                                                   title: 'NIS недоступен',
                                                   message: 'Отключился от NIS, жду подключения'
                                       },
                                       shutdown: {
                                                   title: 'Закрыть программу',
                                                   message: 'Вы уверены, что хотите закрыть NEM Community Client?'
                                       }
                            },
                            landing: {
                                       logo: 'images/nem_logo.png',
                                       importSuccess: 'Кошелёк успешно импортирован!',
                                       nav: {
                                                   start: 'Введение',
                                                   about: 'О NEM',
                                                   settings: 'Settings'
                                       },
                                       main: {
                                                   leftTitle: 'Новичок в <em>NEM</em>?',
                                                   leftButton: 'Создать новый кошелёк',
                                                   walletNamePlh: 'Имя Вашего кошелька',
                                                   passwordPlh: 'Пароль',
                                                   create: 'Создать',
                                                   rightTitle: 'Вы уже <em>NEM</em>ber?',
                                                   rightButton: 'Открыть Ваш кошелёк',
                                                   openButton: 'Открыть',
                                                   walletsFound: 'Найдено <strong>{{1}}</strong> <em>кошелёк</em>',
                                                   copyright: 'Фотография <em>Cas Cornelissen</em>'
                                       },
                                       carousel: {
                                                   items: [
                                                               {
                                                                           title: 'NCC зашифровывает Ваш кошелёк',
                                                                           description: '<em>Безопасность!</em> Важнейшая задача NEM состоит в защите Ваших NEM монет и активов.'
                                                               },
                                                               {
                                                                           title: 'NCC зашифровывает Ваш кошелёк',
                                                                           description: '<em>Безопасность!</em> Важнейшая задача NEM состоит в защите Ваших NEM монет и активов.'
                                                               }
                                                   ]
                                       },
                                       about: {
                                                   sections: [
                                                               {
                                                                           title: 'Как работает NCC?',
                                                                           paragraphs: [
                                                                                      '<strong>NCC</strong>предоставляет доступ к Вашим NEM монетам и активам  как обычный кошелёк. Вы можете',
                                                                                      '<strong>NCC</strong> требует доступ к<strong>NIS</strong>серверу для корректной работы. Локальный сервер должен быть активный ( устанавливается вместе с <strong>NCC</strong>)',
                                                                                      'Вы можете сконфигурировать доступ до удалённого  <strong>NIS</strong>.'
                                                                           ],
                                                                           listItems: [
                                                                                      'Иметь несколько кошельков',
                                                                                      'Определить несколько аккаунтов, которые будут включены в кошелёк'
                                                                           ]
                                                               },
                                                               {
                                                                           title: 'Что такое &#42;NIS?',
                                                                           paragraphs: [
                                                                                      'Этот компонент отвечает за функционирование <strong>NEM</strong>cloud.',
                                                                                      'Чем больше <strong>NIS</strong> тем лучше безопасность.',
                                                                                      '<strong>NIS</strong>это точка доступа в<strong>NEM</strong> cloud.'
                                                                           ],
                                                                           legend: '<strong>&#42;NIS</strong> расшифровывается как <strong>NEM Infrastructure Server</strong>'
                                                               }
                                                   ]
                                       },
                                       footer: {
                                                   copyright: '© 2014 NEM Community Client.Все права защищены.'
                                       }
                            },
                            wallet: {
                                       logo: 'images/nem_logo.png',
                                       lastAccess: 'Примерно {{1}} дней назад',
                                       lastAccessJustNow: 'Недавно',
                                       lastAccessTooltip: 'Последний доступ был {{1}}',
                                       primary: 'Главный',
                                       primaryShort: 'Г',
                                       noLabel: '<Без маркировки>',
                                       copiedToClipboard: 'Адрес был скопирован в буфер обмена!',
                                       actions: {
                                                   refreshInfo: 'Обновить информацию',
                                                   bootLocalNode: 'Загрузить Локальный Узел',
                                                   changeWalletName: 'Изменить Имя Кошелька',
                                                   changeWalletPassword: 'Изменить Пароль Кошелька',
                                                   mergeWallets: 'Объединить Кошельки',
                                                   exportWallet: 'Экспорт Кошелька',
                                                   createAccount: 'Создать Новый Аккаунт',
                                                   addAccount: 'Добавить Существующий Аккаунт',
                                                   changeAccountLabel: 'Изменить Маркировку Аккаунта',
                                                   setPrimary: 'Установить как Главный Аккаунт',
                                                   removeAccount: 'Удалить Аккаунт',
                                                   clientInfo: 'Информация о Клиенте',
                                                   closeWallet: 'Закрыть Кошелёк',
                                                   closeProgram: 'Закрыть Программу',
                                                   copyClipboard: 'Скопируйте адрес в буфер обмена'
                                       },
                                       nav: [
                                                   'Панель Управления',
                                                   'Сообщения',
                                                   'Контакты',
                                                   'Транзакции',
                                                   'Собранные Блоки',
                                                   'Обмен Активов',
                                                   'Новости',
                                                   'Приложения',
                                                   'Аккаунты',
                                                   'Настройки',
                                                   'Закрыть Программу'
                                       ],
                                       bootNodeWarning: "Для полного функционирования NCC  нужно загрузить локальный узел."
                            },
                            dashboard: {
                                       assets: {
                                                   title: 'Ваши Активы'
                                       },
                                       importance: {
                                                   title: 'Оценка Важности',
                                                   unknown: 'Неизвестный Статус',
                                                   start: 'Начать Сборку',
                                                   harvesting: 'Сборка',
                                                   stop: 'Остановить Сборку',
                                                   description: 'Важность аккаунта для NEM cloud'
                                       },
                                       transactions: {
                                                   title: 'Недавние Транзакции',
                                                   sendNem: 'Послать NEM',
                                                   balance: 'Текущий Баланс',
                                                   syncStatus: '(Блок {{1}}{{#2}} : примерно {{3}} дней{{/2}})',
                                                   unknown: 'неизвестно',
                                                   columns: [
                                                               '',
                                                               'Время',
                                                               'Отправитель/Получатель',
                                                               'Сообщения',
                                                               '',
                                                               'Детали',
                                                               'Подтверждения',
                                                               'Оплата',
                                                               'Сумма'
                                                   ],
                                                   types: {
                                                               pending: 'Незаконченные Транзакции',
                                                               outgoing: 'Исходящие Транзакции',
                                                               incoming: 'Входящие Транзакции',
                                                               self: 'Авто транзакции',
                                                   },
                                                   noMessage: 'Нет Сообщений',
                                                   encrypted: 'Сообщение Зашифровано',
                                                   view: 'Обзор',
                                                   pending: 'В ожидании',
                                                   seeAll: 'Посмотреть все транзакции',
                                                   noTransactions: 'Не было сделано ни одной транзакции'
                                       },
                                       nemValue: {
                                                   title: 'Статистика NEM'
                                       },
                                       messages: {
                                                   titleTooltip: 'Сообщения'
                                       },
                                       news: {
                                                   titleTooltip: 'Новости'
                                       },
                                       notAvailable: 'Недоступно в Альфа версии'
                            },
                            transactions: {
                                       title: 'Транзакции',
                                       sendNem: 'Послать NEM',
                                       balance: 'Текущий Баланс',
                                       filters: {
							confirmed: 'Подтвержденные',
							unconfirmed: 'Неподтверждённых',
                                                   	incoming: 'Входящие',
                                                   	outgoing: 'Исходящие',
                                       },
                                       table: {
                                                   columns: [
                                                               '',
                                                               'Время',
                                                               'Отправитель/Получатель',
                                                               'Сообщение',
                                                               '',
                                                               'Детали',
                                                               'Подтверждения',
                                                               'Оплата',
                                                               'Сумма'
                                                   ],
                                                   types: {
                                                               pending: 'Транзакции в ожидании',
                                                               outgoing: 'Исходящие Транзакции',
                                                               incoming: 'Входящие Транзакции',
                                                               self: 'Авто транзакции',
                                                   },
                                                   noMessage: 'Нет сообщения',
                                                   encrypted: 'Сообщение Зашифровано',
                                                   view: 'Обзор',
                                                   pending: 'В ожидании',
                                                   noTransactions: 'Не было сделано ни одной транзакции',
                                                   loading: 'Показать больше транзакций...'
                                       }
                            },
                            harvestedBlocks: {
                                       title: 'Сгенерированные Блоки',
                                       feeEarned: 'Вознаграждение за последние 25 сгенерированных блоков',
                                       table: {
                                                   columns: [
                                                               'Блок',
                                                               'Время',
                                                               'Хэш  Блока',
                                                               'Оплата'
                                                   ],
                                                   noBlocks: 'Нет Сгенерированных Блоков ',
                                                        loadMore: 'Посмотреть более старые сгенерированные блоки'
                                       },
                                       harvesting: {
                                                   unknown: 'Статус Неизвестен',
                                                   start: 'Начать Генерацию',
                                                   harvesting: 'Генерация',
                                                   stop: 'Остановить Генерацию'
                                       }
                            },
                            settings: {
                                       title: 'Настройки',
                                       settings: [
                                                   {
                                                               name: 'Язык'
                                                   }
                                       ],
                                       save: 'Сохранить настройки',
                                       saveSuccess: 'Настройки успешно сохранены'
                            }
                }
});
