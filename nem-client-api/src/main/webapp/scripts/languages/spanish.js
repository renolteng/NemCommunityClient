define({
           id: 'es',
           name: 'Spanish',
           texts: {
            preferences: {
              thousandSeparator: '\u2009',
              decimalSeparator: '.'
            },
               faults: {
                   //101: 'File not found.',
                   101: 'Archivo no encontrado.',
                   //102: 'Wallet has not been created.',
                   102: 'El monedero no ha sido creado.',
                   //103: 'Wallet file is corrupted. Please recover your wallet from a back-up you should have taken when you created the wallet or added an account to it.',
                   103: 'El archivo de monedero est&aacute; corrupto. Favor recuperar su monedero de una copia de seguridad que debio de haber creado en el momento en que el monedero fue creado o en que agrego una cuenta a este.',
                   //104: 'The provided password is not correct. Hopefully you can remember the correct password. The password cannot be recovered if lost!',
                   104: 'La contrase&ntilde;a especificada es inv&aacute;lida. Esperemos que pueda recordar la contrase&ntilde;a correcta. La contrase&ntilde;a no puede ser recuperada si se pierde!',
                   //106: 'Before you can work with a wallet, it has to be opened. To ensure that you are eligible for accessing the wallet, you have to provide the password for that wallet.',
                   106: 'Antes de que puedas comenzar a usar un monedero, este debe abrirse. Debes de proveer la contrase&ntilde;a para asegurar que eres elegible para accesar el monedero.',
                   //107: 'Wallet does not contain this account.',
                   107: 'El monedero no contiene esta cuenta.',
                   //108: 'The account cannot be removed. Most likely because the account still has a balance greater than 0 NEMs or the account you are trying to remove is the primary account.',
                   108: 'La cuenta no puede ser removida. Probablemente se debe a que la cuenta a&uacute;n tiene un balance mayor de 0 NEMs o a que la cuenta que est&aacute;s tratando de remover es la cuenta primaria.',
                   //109: 'Another wallet with the same name exists already. Please choose an other wallet name.',
                   109: 'Ya existe un monedero con el mismo nombre. Por favor, elija otro nombre de monedero.',
                   //110: 'Wallet already contains this account.',
                   110: 'El monedero ya contiene esta cuenta.', //TODO Revisit?
                   //202: 'An encrypted message cannot be sent because the recipient has never made a transaction before.',
                   202: 'Enviar una transacci&oacute;n encriptada no es posible debido a que el destinatario nunca ha realizado una transacci&oacute;n.',
                   //305: 'NEM Infrastructure Server is not available.',
                   305: 'El Servidor de Infraestructura NEM no est&aacute; disponible.',
                   //306: 'An error occurred that the development team did not have foreseen. Apologies for this, maybe a retry might help. Otherwise, please open up an issue within the NEM NIS/NCC community.',
                   306: 'Un error imprevisto ha ocurrido. Pedimos disculpas por este problema. Tal vez re-intentar ayude a resolver el problema. De otra ma nera',
                   //400: 'Some parameter is missing or invalid.',
                   400: 'Alg&uacute;n parametro requerido no ha sido especificado o es inv&aacute;lido.',
                   //404: 'The requested resource could not be found.',
                   404: 'El recurso solicitado no se ha encontrado.',
                   //500: 'Failed to save configuration file.',
                   500: 'Error al guardar archivo de configuraci&oacute;n.',
                   //600: 'NCC requires NIS server to be booted for sending and receiving transactions from the NEM cloud. Please use the NCC menu entry for booting the local node.',
                   600: 'NCC requiere la iniciaci&oacute;n de un servidor NIS para enviar y recibir transacciones de la nube NEM. Por favor, use las opciones de menu NCC para iniciar un servidor NIS local.',
                   //601: 'The nis node is already booted. A second attempt to boot nis is not possible.',
                   601: 'El nodo NIS ya ha sido iniciado. No es v&aacute;lido intentar iniciar el nodo NIS ya estando iniciado.',
                   //700: 'The provided account does not satisfy the basic criteria for harvesting. Mainly it is related to the amount of NEMs within the account. Harvesting starts with at least 1000 NEM.',
                   700: 'La cuenta especificada no satisface los criterios b&aacute;sicos para recolectar. Normalmente este problema est&aacute; relacionado al balance de la cuenta. Al menos 1000 NEMs son necesarios para participar en el proceso de recolecta.',
                   //701: 'The provided deadline is in the past. The deadline must be provided within a 1 day period.',
                   701: 'La fecha limite especificada esta en el pasado. La fecha limite especificada debe de estar dentro del periodo de un dia.',
                   //702: 'The provided deadline is too far in the future. The deadline must be within one day time period.',
                   702: 'La fecha limite especificada esta muy lejos en el futuro. La fecha limite especificada debe de estar dentro del periodo de un dia.',
                   //703: 'Your account does not have the right balance to send the provided amount of NEMs.',
                   703: 'La cuenta no tiene balance suficiente para enviar la cantidad especificada de NEMs.',
                   //704: 'The provided message text is too large to be send via NEM. Please try to reduce the length of the message you want to send.',
                   704: 'El texto especificado es muy largo para ser enviado via NEM. Por favor, intente reducir el tama&ntilde;o del mensaje que desea enviar.',
                   //705: 'The transaction hash already exists in the database or the list of unconfirmed transactions.',
                   705: 'The transaction hash already exists in the database or the list of unconfirmed transactions.',
                   //706: 'The signature of the transaction could not be verified.',
                   706: 'La firma de la transacci&oacute;n no pudo ser verificada.',
                   //707: 'The time stamp of the transaction id too far in the past.',
                   707: 'La marca de tiempo de la transacci&oacute;n esta muy lejos en el pasado.',
                   //708: 'The time stamp of the transaction is too far in the future.',
                   708: 'La marca de tiempo de la transacci&oacute;n esta muy lejos en el futuro.',
                   //709: 'The account is unknown. An account needs to be part of at least one transaction (sender or recipient) to be known to the network.',
                   709: 'La cuenta es desconocida. Una cuenta necesita ser parte de al menos una transacci&oacute;n (ya sea emisor o destinatario) para ser conocida en la red.',
                   //901: 'There was an error setting up offline mode.'
                   901: 'Hubo un error al intentar establecer el modo fuera de linea' // TODO Revisit: 'modo desconectado' sounds better?
               },
               common: {
                   //success: 'Success', //title of the Success message modals
                   success: '&Eacute;xito', //title of the Success message modals
                   nisStatus: {
                       nccUnavailable: 'NCC is not available',
                       //unavailable: 'NIS is not available',
                       unavailable: 'NIS no esta disponible',
                       booting: 'Booting NIS...',
                       //notBooted: 'NIS requires to be booted. Please open your wallet and boot a local node via the popup dialog.',
                       notBooted: 'NIS requiere ser iniciado. Por favor, abra el monedero e inicie un nodo local',
                       retrievingStatus: 'Retrieving NIS status...',
                       //synchronizing: 'NIS is synchronizing. At block {{1}}, est. {{2}} days behind.'
                       synchronizing: 'NIS est&aacute; sincronizando. En el bloque {{1}}: aproximadamente {{2}} d&iacute;a(s) atras.'
                   },
                   synchronized: 'NIS is synchronized!'
               },
               modals: {
                   error: {
                       //title: 'Oops!',
                       title: 'Oops!',
                       //caption: 'ERROR {{1}}'
                       caption: 'ERROR {{1}}'
                   },
                   confirmDefault: {
                       //yes: 'Yes',
                       yes: 'Si',
                       //no: 'No'
                       no: 'No'
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
                       //title: 'Send NEM'
                       title: 'Enviar NEM',
                       //labelDesc: 'This account is labeled as {{1}}',
                       labelDesc: 'Esta cuenta est&aacute; etiquetada como {{1}}',
                       //nullLabelDesc: "This account doesn't have a label",
                       nullLabelDesc: "Esta cuenta no est&aacute; etiquetada.",
                       //amount: 'Amount',
                       amount: 'Cantidad',
                       //recipient: "Recipient's account",
                       recipient: "Cuenta de destinatario",
                       //message: 'Message',
                       message: 'Mensaje',
                       //encrypt: 'Encrypt message',
                       encrypt: 'Encriptar mensaje',
                       //fee: 'Fee',
                       fee: 'Tarifa',
                       //dueBy: 'Due by',
                       dueBy: 'Debido por',
                       //resetFee: 'Reset to minimum fee',
                       resetFee: 'Reestablecer a valor de tarifa m&iacute;nimo',
                       //hours: 'hours',
                       hours: 'horas',
                       //password: 'Password',
                       password: 'Contrase&ntilde;a',
                       //send: 'Send',
                       send: 'Enviar',
                       //sending: 'Sending...',
                       sending: 'Enviando...',
                       //successMessage: 'Transaction has been sent successfully!'
                       successMessage: '&iexcl;La transacci&oacute;n ha sido enviada exitosamente!'
                   },
                   clientInfo: {
                       //title: 'Client info',
                       title: 'Informacion de cliente',
                       //ncc: 'NEM Community Client - NCC',
                       ncc: 'Cliente de Comunidad NEM - NCC',
                       //signer: 'Signer',
                       signer: 'Firmante',
                       //remoteServer: 'Remote Server',
                       remoteServer: 'Servidor Remoto',
                       //local: 'Local',
                       local: 'Local', // TODO: Translate to 'Servidor Local' instead?
                       //nis: 'NEM Infrastructure Server - NIS',
                       nis: 'Servidor de Infraestructura NEM - NIS',
                       //sync: 'Synchronized',
                       sync: 'Sincronizado',
                       //notSync: 'Not synchronized',
                       notSync: 'No esta sincronizado',
                       //notConnected: 'Not connected to NEM Cloud',
                       notConnected: 'No esta conectado a la nube NEM',
                       //loading: 'Loading...'
                       loading: 'Cargando...'
                   },
                   transactionDetails: {
                       //title: 'Transaction Details',
                       title: 'Detalles de Transacci&oacute;n',
                       // this might be block or transaction ID
                       //id: 'ID',
                       id: 'ID',
                       // this might be block or transaction Hash
                       //hash: 'Hash',
                       hash: 'Hash',
                       //type: 'Transaction Type',
                       type: 'Tipo de Transacci&oacute;n',
                       //pending: 'Pending',
                       pending: 'Pendiente',
                       //outgoing: 'Outgoing',
                       outgoing: 'Saliente',
                       //incoming: 'Incoming',
                       incoming: 'Entrante',
                       //self: 'Self',
                       self: 'Yo', //TODO: Otra traducccion?
                       //sender: 'Sender',
                       sender: 'Emisor',
                       //recipient: 'Recipient',
                       recipient: 'Destinatario', //TODO 'Recipiente', 'Recibidor'?
                       //message: 'Message',
                       message: 'Mensaje',
                       //noMessage: 'No message',
                       noMessage: 'Sin mensaje',
                       //encrypted: 'Message is encrypted',
                       encrypted: 'Mensaje est&aacute; encriptado.',
                       //time: 'Timestamp',
                       time: 'Marca de tiempo',
                       //confirmations: 'Confirmations',
                       confirmations: 'Confirmaciones',
                       //amount: 'Amount',
                       amount: 'Cantidad',
                       //fee: 'Fee'
                       fee: 'Tarifa'
                   },
                   bootLocalNode: {
                       //title: 'Boot local node',
                       title: 'Inicializar nodo local',
                       //account: 'Account to boot local node',
                       account: 'Cuenta para inicializar nodo local',
                       //noLabel: '<span class="null">&lt;No label&gt;</span>',
                       noLabel: '<span class="null">&lt;Sin etiqueta&gt;</span>',
                       //wallet: 'Wallet',
                       wallet: 'Monedero',
                       //node: 'Node name',
                       node: 'Nombre de nodo',
                       //boot: 'Boot',
                       boot: 'Inicializar',
                       //booting: 'Booting...'
                       booting: 'Inicializando...'
                   },
                   notBootedWarning: {
                       //title: 'Node has not been booted!',
                       title: 'Nodo no ha sido iniciado!',
                       settings: 'Settings'
                   },
                   closeWallet: {
                       //title: 'Close wallet',
                       title: 'Cerrar monedero',
                       //message: 'Are you sure you want to close your wallet and return to landing page?'
                       message: 'Est&aacute; seguro que desea cerrar el monedero y volver a la pagina de inicio?'
                   },
                   createAccount: {
                       //title: 'Create new account',
                       title: 'Crear cuenta nueva',
                       //label: 'Private label',
                       label: 'Etiqueta privada', //TODO: 'Identificador'?
                       //wallet: 'Wallet',
                       wallet: 'Monedero',
                       //password: "Wallet's password",
                       password: "Contrase&ntilde;a de monedero",
                       //successMessage: 'Account {{1}} {{#2}}({{2}}){{/2}} has been created!',
                       successMessage: 'Cuenta {{1}} {{#2}}({{2}}){{/2}} ha sido creada!',
                       //create: 'Create'
                       create: 'Crear'
                   },
                   addAccount: {
                       //title: 'Add an Existing Account',
                       title: 'A&ntilde;adir cuenta existente',
                       //privateKey: "Account's Private Key",
                       privateKey: "Clave privada de cuenta",
                       //wallet: 'Wallet',
                       wallet: 'Monedero',
                       //password: "Wallet's password",
                       password: "Contrase&ntilde;a de monedero",
                       //successMessage: 'Account {{1}} {{#2}}({{2}}){{/2}} has been added to wallet!',
                       successMessage: 'Cuenta {{1}} {{#2}}({{2}}){{/2}} ha sido a&ntilde;adida al monedero!',
                       //add: 'Add',
                       add: 'A&ntilde;adir',
                       //label: 'Label'
                       label: 'Etiqueta'
                   },
                   setPrimary: {
                       //title: 'Set primary account',
                       title: 'Definir cuenta primaria',
                       //account: 'Account to be set as Primary',
                       account: 'Cuenta a ser definida como primaria',
                       //noLabel: '<span class="null">&lt;No label&gt;</span>',
                       noLabel: '<span class="null">&lt;Sin etiqueta&gt;</span>',
                       //wallet: 'Wallet',
                       wallet: 'Monedero',
                       //password: "Wallet's password",
                       password: "Contrase&ntilde;a de monedero",
                       //successMessage: 'Account {{1}} {{#2}}({{2}}){{/2}} has been set as primary!',
                       successMessage: 'Cuenta {{1}} {{#2}}({{2}}){{/2}} ha sido definida como cuenta primaria!',
                       //set: 'Set as primary'
                       set: 'Definir como primaria'
                   },
                   changeWalletName: {
                       //title: 'Change wallet name',
                       title: 'Cambiar nombre de monedero',
                       //wallet: 'Current wallet name',
                       wallet: 'Nombre actual de monedero',
                       //newName: 'New wallet name',
                       newName: 'Nuevo nombre de monedero',
                       //password: "Wallet's password",
                       password: "Contrase&ntilde;a de monedero",
                       //successMessage: 'Wallet name has been successfully changed from <em>{{1}}</em> to <em>{{2}}</em>',
                       successMessage: 'El nombre de monedero ha sido cambiado exitosamente de <em>{{1}}</em> a <em>{{2}}</em>',
                       //change: 'Change'
                       change: 'Cambiar'
                   },
                   changeWalletPassword: {
                       //title: 'Change wallet password',
                       title: 'Cambiar contrase&ntilde;a de monedero',
                       //wallet: 'Wallet',
                       wallet: 'Monedero',
                       //password: 'Current password',
                       password: 'Contrase&ntilde;a actual',
                       //newPassword: 'New password',
                       newPassword: 'Nueva contrase&ntilde;a',
                       //confirmPassword: 'Confirm new password',
                       confirmPassword: 'Confirmar nueva contrase&ntilde;a',
                       //successMessage: 'Wallet password has been successfully changed',
                       successMessage: 'La contrase&ntilde;a de monedero ha sido cambiada exitosamente.',
                       //change: 'Change',
                       change: 'Cambiar',
                       //passwordNotMatchTitle: 'Oops!',
                       passwordNotMatchTitle: 'Oops!',
                       //passwordNotMatchMessage: 'Your entered password and password confirmation do not match. Please be sure you type your new password correctly.'
                       passwordNotMatchMessage: 'La nueva contrase&ntilde;a especificada no coincide con la contrase&ntilde;a de confirmaci&oacute;n. Por favor, asegurese de especificar la nueva contrase&ntilde;a correctamente.'
                   },
                   changeAccountLabel: {
                       //title: 'Change account label',
                       title: 'Cambiar etiqueta de cuenta',
                       //label: 'Account label',
                       label: 'Etiqueta de cuenta',
                       //wallet: 'Wallet',
                       wallet: 'Monedero',
                       //password: "Wallet's password",
                       password: "Contrase&ntilde;a de monedero",
                       //successMessage: 'Account {{1}} is now labeled as {{2}}',
                       successMessage: 'Cuenta {{1}} ahora esta etiquetada como {{2}}',
                       //change: 'Change'
                       change: 'Cambiar'
                   },
                   removeAccount: {
                       //title: 'Remove account',
                       title: 'Remover cuenta',
                       //wallet: 'Wallet',
                       wallet: 'Monedero',
                       //password: "Wallet's password",
                       password: "Contrase&ntilde;a de monedero",
                       //warning: 'Please ensure that your account has no NEMs left before you remove it, or they would be lost forever.',
                       warning: 'Por favor, aseg&uacute;rese que la cuenta que desea remover no contiene NEMs, ya que estos se perderian para siempre al removerla.',
                       //successMessage: 'Account {{1}} {{#2}}({{2}}){{/2}} has been removed!',
                       successMessage: 'Cuenta {{1}} {{#2}}({{2}}){{/2}} ha sido removida!',
                       //remove: 'Remove'
                       remove: 'Remover'
                   },
                   nisUnavailable: {
                       //title: 'NIS unavailable',
                       title: 'NIS no esta disponible',
                       //message: 'Disconnected from NIS, waiting for connection'
                       message: 'Desconectado de NIS, esperando conecci&oacute;n'
                   },
                   shutdown: {
                       //title: 'Close program',
                       title: 'Cerrar programa',
                       //message: 'Are you sure you want to close NEM Community Client?'
                       message: 'Est&aacute; seguro que desea cerrar el Cliente de Comunidad NEM?'
                   }
               },
               landing: {
                   //importSuccess: 'Wallet has been sucessfully imported!',
                   importSuccess: 'El monedero ha sido importado exitosamente!',
                   nav: {
                       //start: 'Getting Started',
                       start: 'Comienze aqu&iacute;',
                       //about: 'About NEM',
                       about: 'Acerca de NEM',
                       //help: 'Help'
                       help: 'Ayuda'
                   },
                   main: {
                       //leftTitle: 'New to <em>NEM</em>?',
                       leftTitle: 'Nuevo en <em>NEM</em>?',
                       //leftButton: 'Create new wallet',
                       leftButton: 'Crear nuevo monedero',
                       //walletNamePlh: 'Name of your wallet',
                       walletNamePlh: 'Nombre de su monedero',
                       //passwordPlh: 'Password',
                       passwordPlh: 'Contrase&ntilde;a',
                       //create: 'Create',
                       create: 'Crear',
                       //rightTitle: 'Already a <em>NEM</em>ber?',
                       rightTitle: 'Ya es miembro de <em>NEM</em>?',
                       //rightButton: 'Open your wallet',
                       rightButton: 'Abrir su monedero',
                       //openButton: 'Open',
                       openButton: 'Abrir',
                       //walletsFound: 'Found <strong>{{1}}</strong> <em>wallets</em>',
                       walletsFound: 'Se encontro <strong>{{1}}</strong> <em>monedero(s)</em>',
                       //copyright: 'Photography by <em>Cas Cornelissen</em>'
                       copyright: 'Fotograf&iacute;a por <em>Cas Cornelissen</em>'
                   },
                   carousel: {
                       items: [
                           {
                               //title: 'NCC encrypts your wallet',
                               title: 'NCC encripta su monedero',
                               //description: '<em>Security</em> is very important for NEM to avoid theft of NEM coins &amp; assets.'
                               description: 'La <em>seguridad</em> es bien importante para evitar robo de monedas NEM y/o activos.'
                           },
                           {
                               //title: 'NCC encrypts your wallet',
                               title: 'NCC encripta su monedero',
                               //description: '<em>Security</em> is very important for NEM to avoid theft of NEM coins &amp; assets.'
                               description: 'La <em>seguridad</em> es bien importante para evitar robo de monedas NEM y/o activos.'
                           }
                       ]
                   },
                   about: {
                       sections: [
                           {
                               //title: 'How NCC works?',
                               title: 'Como funciona NCC?',
                               paragraphs: [
                                   //'<strong>NCC</strong> provides an access to your assets and NEMs like a traditional wallet does. You may',
                                   //'<strong>NCC</strong> requires access to an <strong>NIS</strong> server in order to operate. Standard is to have a local server active (is installed together with the <strong>NCC</strong>)',
                                   //'You may also configure an access to a remote <strong>NIS</strong>.'
                                   '<strong>NCC</strong> te provee acceso a tus activos y NEMs como cualquier monedero tradicional. Usted puede',
                                   '<strong>NCC</strong> requiere acceso a un servidor <strong>NIS</strong> para poder operar. La convenci&oacute;n es tener un servidor NIS local activo (instalado junto a <strong>NCC</strong>)',
                                   'Usted tambi&eacute;n puede configurar acceso a un servidor <strong>NIS</strong> remoto'
                               ],
                               listItems: [
                                   'Tener multiples monederos', //'Have multiple wallets',
                                   'Asociar multiples cuentas a un monedero' //'Define multiple accounts to be included in a wallet'
                               ]
                           },
                           {
                               //title: 'What is &#42;NIS?',
                               title: 'Qu&eacute; es &#42;NIS?',
                               paragraphs: [
                                   //'This component is responsible for keeping the <strong>NEM</strong> cloud alive.',
                                   //'The more <strong>NIS</strong> the better the security.',
                                   //'<strong>NIS</strong> is the access point into the <strong>NEM</strong> cloud.'
                                   'Este componente es responsable de mantener la nube <strong>NEM</strong> con vida',
                                   'Mayor sera la seguridad mientras mas instancias de <strong>NIS</strong> esten conectadas a la nube <strong>NEM</strong>',
                                   '<strong>NIS</strong> es el punto de acceso a la nube <strong>NEM</strong>'

                               ],
                               //legend: '<strong>&#42;NIS</strong> stands for <strong>NEM Infrastructure Server</strong>'
                               legend: '<strong>&#42;NIS</strong> significa <strong>Servidor de Infraestructura NEM</strong>'
                           }
                       ]
                   },
                   footer: {
                       //copyright: '&copy; Copyright 2014. NEM Community Client.'
                       copyright: '&copy; Copyright 2014. Cliente de Comunidad NEM.'
                   }
               },
               wallet: {
                   //lastAccess: 'About {{1}} days ago',
                   lastAccess: 'Hace {{1}} dia(s) aproximadamente',
                   //lastAccessJustNow: 'Just now',
                   lastAccessJustNow: 'En estos momentos',
                   //lastAccessTooltip: 'Last access was {{1}}',
                   lastAccessTooltip: '&Uacute;ltimo acceso fue en {{1}}',
                   //primary: 'primary',
                   primary: 'primario',
                   primaryShort: 'P',
                   //noLabel: '<No label>',
                   noLabel: '<Sin etiqueta>',
                   //copyToClipboard: 'Click to copy address to clipboard',
                   copyToClipboard: 'Clic para copiar direcci&oacute;n al portapapeles',
                   //copiedToClipboard: 'Address has been copied to clipboard!',
                   copiedToClipboard: 'Direcci&oacute;n ha sido copiada al portapapeles!',
                   actions: {
                       //refreshInfo: 'Refresh Info',
                       refreshInfo: 'Actualizar informacion',
                       //bootLocalNode: 'Boot Local Node',
                       bootLocalNode: 'Inicializar nodo local',
                       //changeWalletName: 'Change Wallet Name',
                       changeWalletName: 'Cambiar nombre de monedero',
                       //changeWalletPassword: 'Change Wallet Password',
                       changeWalletPassword: 'Cambiar contrase&ntilde;a de monedero',
                       //mergeWallets: 'Merge Wallets',
                       mergeWallets: 'Fusionar monederos',
                       //exportWallet: 'Export Wallet',
                       exportWallet: 'Exportar monedero',
                       //createAccount: 'Create new Account',
                       createAccount: 'Crear cuenta nueva',
                       //addAccount: 'Add an Existing Account',
                       addAccount: 'A&ntilde;adir cuenta existente',
                       //changeAccountLabel: 'Change Account Label',
                       changeAccountLabel: 'Cambiar etiqueta de cuenta',
                       //setPrimary: 'Set as Primary Account',
                       setPrimary: 'Set como cuenta primaria',
                       //removeAccount: 'Remove Account',
                       removeAccount: 'Remover cuenta',
                       //clientInfo: 'Client Info',
                       clientInfo: 'Informacion de cliente',
                       //closeWallet: 'Close Wallet',
                       closeWallet: 'Cerrar monedero',
                       //closeProgram: 'Close Program'
                       closeProgram: 'Cerrar programa'
                   },
                   nav: [
                       'Panel de informaci&oacute;n', //'Dashboard',
                       'Mensajes', //'Messages',
                       'Contactos', //'Contacts',
                       'Transacciones', //'Transactions',
                       'Bloques recolectados', //'Harvested blocks',
                       'Intercambio de activos', //'Asset Exchange',
                       'Noticias', //'News',
                       'Aplicaciones', //'Applications',
                       'Cuentas', //'Accounts',
                       'Configuraci&oacute;n', //'Settings',
                       'Cerrar programa' //'Close Program'
                   ],
                   //bootNodeWarning: "A local node needs to be booted before you can fully utilize NCC's features."
                   bootNodeWarning: "Se necesita iniciar un nodo local antes de poder utilizar todas las caracter&iacute;sticas de NCC."
               },
               dashboard: {
                   assets: {
                       //title: 'Your assets'
                       title: 'Tus activos'
                   },
                   importance: {
                       //title: 'Importance score',
                       title: 'Puntuaci&oacute;n de importancia',
                       //unknown: 'Unknown status',
                       unknown: 'Estado desconocido',
                       //start: 'Start harvesting',
                       start: 'Comenzar recolecta',
                       //harvesting: 'Harvesting',
                       harvesting: 'Recolectando',
                       //stop: 'Stop harvesting',
                       stop: 'Detener recolecta',
                       //description: 'importance of account to the NEM cloud'
                       description: 'importancia de la cuenta para la nube NEM'
                   },
                   transactions: {
                       //title: 'Recent Transactions',
                       title: 'Transacciones recientes',
                       //sendNem: 'Send NEM',
                       sendNem: 'Enviar NEM',
                       //balance: 'Current balance',
                       balance: 'Saldo actual',
                       //syncStatus: '(at block {{1}}{{#2}} : est. {{3}} days behind{{/2}})',
                       syncStatus: '(En el bloque {{1}}{{#2}}: aproximadamente {{3}} dia(s) atras{{/2}})',
                       //unknown: 'unknown',
                       unknown: 'desconocido',
                       columns: [
                           '',
                           'Tiempo', //'Time',
                           'Emisor/Destinatario', //'Sender/Recipient',
                           'Mensaje', //'Message',
                           '',
                           'Detalles', // 'Details',
                           'Confirmaciones', //'Confirmations',
                           'Tarifa', //'Fee',
                           'Cantidad' // 'Amount'
                       ],
                       types: {
                           //pending: 'Pending transaction',
                           pending: 'Transacci&oacute;n pendiente',
                           //outgoing: 'Outgoing transaction',
                           outgoing: 'Transacci&oacute;n saliente',
                           //incoming: 'Incoming transaction',
                           incoming: 'Transacci&oacute;n entrante',
                           //self: 'Self transaction',
                           self: 'Transacci&oacute;n a s&iacute; mismo'
                       },
                       //noMessage: 'No message',
                       noMessage: 'Sin mensaje',
                       //encrypted: 'Message is encrypted',
                       encrypted: 'Mensaje esta encriptado',
                       //view: 'View',
                       view: 'Ver',
                       //pending: 'Pending',
                       pending: 'Pendiente',
                       //seeAll: 'See all transactions',
                       seeAll: 'Ver todas las transacciones',
                       //noTransactions: 'No transactions have been performed yet'
                       noTransactions: 'Aun no se ha ejecutado ninguna transacci&oacute;n'
                   },
                   nemValue: {
                       //title: 'NEM value statistics'
                       title: 'Valores de estad&iacute;sticas de NEM'
                   },
                   messages: {
                       //titleTooltip: 'Messages'
                       titleTooltip: 'Mensajes'
                   },
                   news: {
                       //titleTooltip: 'News'
                       titleTooltip: 'Noticias'
                   },
                   //notAvailable: 'Not yet available in alpha release'
                   notAvailable: 'No disponible en versi&oacute;n alfa'
               },
               transactions: {
                   //title: 'Transactions',
                   title: 'Transacciones',
                   //sendNem: 'Send NEM',
                   sendNem: 'Enviar NEM',
                   //balance: 'Current Balance',
                   balance: 'Balance actual',
                   filters: {
                       //all: 'All transactions',
                       all: 'Todas las transacciones',
                       //pending: 'Pending',
                       pending: 'Pendiente',
                       //incoming: 'Incoming',
                       incoming: 'Entrante',
                       //outgoing: 'Outgoing'
                       outgoing: 'Saliente'
                   },
                   table: {
                       columns: [
                           '',
                           'Tiempo', //'Time',
                           'Emisor/Destinatario', //'Sender/Recipient',
                           'Mensaje', //'Message',
                           '',
                           'Detalles', // 'Details',
                           'Confirmaciones', //'Confirmations',
                           'Tarifa', //'Fee',
                           'Cantidad' // 'Amount'
                       ],
                       types: {
                           //pending: 'Pending transaction',
                           pending: 'Transacci&oacute;n pendiente',
                           //outgoing: 'Outgoing transaction',
                           outgoing: 'Transacci&oacute;n saliente',
                           //incoming: 'Incoming transaction',
                           incoming: 'Transacci&oacute;n entrante',
                           //self: 'Self transaction',
                           self: 'Transacci&oacute;n a s&iacute; mismo'
                       },
                       //noMessage: 'No message',
                       noMessage: 'Sin mensaje',
                       //encrypted: 'Message is encrypted',
                       encrypted: 'Mensaje esta encriptado',
                       //view: 'View',
                       view: 'Ver',
                       //pending: 'Pending',
                       pending: 'Pendiente',
                       //noTransactions: 'No transactions have been performed yet',
                       noTransactions: 'Aun no se ha ejecutado ninguna transacci&oacute;n',
                       //loading: 'Loading more transactions...'
                       loading: 'Cargando mas transacciones...'
                   }
               },
               harvestedBlocks: {
                   //title: 'Harvested Blocks',
                   title: 'Bloques recolectados',
                   //feeEarned: 'Fees earned from the last 25 harvested blocks',
                   feeEarned: 'Comisiones devengadas por los &uacute;ltimos 25 bloques recolectados',
                   table: {
                       columns: [
                           'Altura', //'Height',
                           'Tiempo', //'Time', TODO: Translate to 'Hora' instead?
                           'Hash de bloque', //'Block hash',
                           'Tarifa' //'Fee'
                       ],
                       //noBlocks: 'No harvested blocks ',
                       noBlocks: 'No hay bloques recolectados',
                       //loadMore: 'See older harvested blocks'
                       loadMore: 'Ver bloques recolectados mas viejos'
                   },
                   harvesting: {
                       //unknown: 'Unknown status',
                       unknown: 'Estado desconocido',
                       //start: 'Start harvesting',
                       start: 'Comenzar recolecta',
                       //harvesting: 'Harvesting',
                       harvesting: 'Recolectando',
                       //stop: 'Stop harvesting',
                       stop: 'Detener recolecta'
                   }
               },
               settings: {
                   //title: 'Settings',
                   title: 'Configuraci&oacute;n',
                   settings: [
                       {
                           //name: 'Language',
                           name: 'Idioma'
                       }
                   ],
                   //save: 'Save changes',
                   save: 'Guardar cambios',
                   //saveSuccess: 'Settings have been saved successfully'
                   saveSuccess: 'La configuraci&oacute;n ha sido actualizada exitosamente' //TODO: revisit
               }
           }
       });