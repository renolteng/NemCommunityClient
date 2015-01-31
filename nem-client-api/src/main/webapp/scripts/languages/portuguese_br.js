define({
	id: "pt",
	name: "Português",
	texts: {
		preferences: {
			thousandSeparator: "\u2009",
			decimalSeparator: "."
		},
		faults: {
			101: 'The wallet file does not exist.',
			102: "A carteira não foi criada.",
			103: 'Wallet file is corrupt. Please recover your wallet from a backup.',
			104: 'The provided password for the wallet is not correct.',
			105: 'No password was provided for the wallet.',
			106: "Antes de começar a usar a carteira NEM, é preciso logar-se. Por favor, forneça a senha de uma carteira ou crie outra.",
			107: "A carteira não contém essa conta.",
			108: "A conta não pode ser removida. Provavelmente ela tem saldo maior que 0 NEMs ou é a conta primária.",
			109: "Já existe uma conta com o mesmo nome. Por favor, escolha outro.",
			110: "A carteira já contém essa conta.",
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
			202: "Não é possível enviar mensagem, porque o destinatário ainda não possui chave pública.",
			305: "NIS inicializando.",
			306: "Ocorreu um erro desconhecido. Talvez uma nova nova tentativa ou reiniciar o cliente/servidor dê certo; caso contrário, informe aos desenvolvedores do NEM no fórum oficial forum.nemcoin.com.",
			400: "Está faltando algum parâmetro ou os dados estão incorretos.",
			401: 'Esta operação não pode ser completada, ela pode expor a chave privada enviando-a para um NIS remoto.',
			404: "O arquivo solicitado não foi encontrado..",
			500: "Ocorreu um erro desconhecido. Por favor, reinicie o programa, caso não resolva, contate os desenvolvedores através do fórum forum.nemcoin.com.",
			600: "NCC requer que o NIS seja inicializado. Por favor, vá em cliente NCC para inicializar o nó local via menu superior direito.",
			601: "O NIS já foi inicializado.",
			699: 'Maximum number of harvesters allowed on server has been reached.',
			700: "Falha na colheita. Geralmente esse problema está relacionado com o saldo de NEMs na conta. Para colheitar são necessários,  pelo menos, 1000 NEMs.",
			701: "O prazo final fornecido está no passado. O prazo limite deve estar dentro do período de um dia.",
			702: "O prazo final fornecido está no futuro. O prazo limite deve estar dentro do período de um dia.",
			703: "Saldo insuficiente.",
			704: "Texto muito longo. Por favor, reduza o tamanho da mensagem.",
			705: "O Hash de transação já existe no banco de dados, na lista de transações não confirmadas.",
			706: "A assinatura da transação não pôde ser verificada.",
			707: "A data da ID de transação está muito no passado.",
			708: "A data da ID de transação está muito no futuro.",
			709: "Conta desconhecida, Por favor, faça uma transação para conseguir uma chave pública.",
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
			901: "Erro na configuração do modo off-line.",
			1000: "Chave privada e pública não combinam.",
			1001: 'Chave publica e endereço não combinam.',
			1002: 'O endereço não pertence a rede principal de peers.'
		},
		common: {
			success: "Successo",
			appStatus: {
				nccUnknown: "Estado do NCC desconhecido",
				nccUnavailable: "NCC não inicializado",
				nccStarting: "NCC está inicializando...",
				nisUnknown: "Estado do NIS desconhecido",
				nisUnavailable: "NIS não inicializado",
				nisStarting: "NIS está inicializando...",
				notBooted: "NIS requere inicialização. Por favor, abra a carteira para ativar o auto-boot ou inicialize o nó manualmente.",
				booting: "NIS inicializando...",
				nisInfoNotAvailable: "Informações do NIS não disponível. Recuperando informações...",
				synchronizing: "NIS está sincronizando com o bloco {{1}}, encontrado {{2}} atrás.",
				daysBehind: {
					0: "Menos de um dia",
					1: "1 dia",
					many: "{{1}} dias"
				},
				synchronized: "NIS está sincronizado!!",
				noRemoteNisAvailable: 'No remote NIS found in the network, disconnected from internet?'
			},
			addressBook: 'Address book',
			password: "Senha",
			passwordValidation: 'Password must not be blank',
			address: 'Endereço',
			privateLabel: "Identificação",
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
			pending: "Pendente",
			outgoing: "Enviada",
			incoming: "Recebida",
			self: "Enviada para si mesmo",
			importance: 'Importance transaction',
			modification: 'Aggregate Modification of Multisig'
		},
		modals: {
			error: {
				title: "Oopa!",
				caption: "ERRO {{1}}"
			},
			confirmDefault: {
				yes: "Sim",
				no: "Não"
			},
			settings: {
				title: "Configurações",
				language: {
					label: "Idioma"
				},
				remoteServer: {
					tabTitle: "Servidor remoto",
					protocol: "Protocolo",
					protocolOptions: {
						http: "HTTP"
					},
					host: "Endereço Host",
					port: "Porta"
				},
				autoBoot: {
					tabTitle: "Inicialização automática",
					name: "Nome do nó",
					account: "Carteira",
					primaryAccount: "Conta primária",
					auto: "Inicialização automática quando abrir a carteira"
				},
				save: "Salvar",
				saveSuccess: "As configurações foram salvas!"
			},
			multisig: {
				title: 'Convert account to multisig',
				multisigAccount: 'Multisig account',
				cosignatories: "Cosignatories' addresses",
				labelDesc: "Esta conta está identificada como {{1}}",
				nullLabelDesc: "Esta conta não tem uma identificação",
				addCosignatory: '+ Add Cosignatory',
				cancel: 'Cancelar',
				convert: 'Convert',
				fee: "Taxa",
				feeValidation: 'A taxa de transação não deve ser inferior a taxa mínima',
				dueBy: 'Tempo de espera',
				useMinimumFee: 'Usar taxa minima',
				hours: 'hora(s)',
				txConfirm: {
					title: 'Confirm Conversion to Multisig Account',
					total: 'Total',

				},

			},
			signMultisig: {
				title: 'Sign multisig transaction',
				original: {
					from: 'Multisig account',
					to: "Destinatário",
					amount: "Quantidade",
					fee: 'Inner Fee',
					deadline: 'Deadline'
				},
				multisigFees: 'Multisig Fees',
				multisigTotal: 'Total',
				sender: 'Cosignatory',
				fee: "Taxa",
				feeValidation: 'A taxa de transação não deve ser inferior a taxa mínima',
				dueBy: 'Tempo de espera',
				useMinimumFee: 'Usar taxa minima',
				hours: 'hora(s)',
				password: "Senha",
				passwordValidation: 'Password must not be blank',
				send: "Enviar",
				cancel: 'Cancelar',
				sending: 'Enviando...',
				successMessage: "A transação foi enviada!",
				txConfirm: {
					title: 'Confirm Multisig Transaction',
					message: "Mensagem",
					encrypted: "Mensagem criptografada",
					noMessage: "Sem mensagem",

				},

			},
			sendNem: {
				title: "Enviar NEM",
				sender: "Remetente",
				thisAccount: 'This account',
				labelDesc: "Esta conta está identificada como {{1}}",
				nullLabelDesc: "Esta conta não tem uma identificação",
				amount: "Total",
				recipient: "Destinatário",
				recipientValidation: 'Endereços de contas devem ter pelo menos 40 caracteres, excluindo o caractere - .',
				message: "Mensagem",
				encrypt: "Criptografar mensagem",
				fee: "Taxa",
				multisigFee: 'Multisig fee',
				feeValidation: 'A taxa de transação não deve ser inferior a taxa mínima',
				dueBy: "Tempo de aquisicão",
				useMinimumFee: 'Usar taxa minima',
				hours: "horas",
				password: "Senha",
				passwordValidation: 'Password must not be blank',
				send: "Enviar",
				cancel: 'Cancelar',
				sending: "Enviando...",
				successMessage: "A transação foi enviada!",
				txConfirm: {
					title: 'Confirmar Transação',
					amount: 'Quantidade',
					to: 'Para',
					dueBy: 'Tempo de espera',
					hours: 'hora(s)',
					total: 'Total',
					message: 'Mensagem',
					encrypted: 'Mensagem está criptografada',
					noMessage: 'Sem mensagem',
					cancel: 'Cancelar',
					confirm: 'Confirmar',
					sending: 'Enviando...'
				},
				notBootedWarning: {
					title: "O nó não foi inicializado!",
					message: "O nó local precisa ser inicializado para fazer transações!"
				},
				bootingWarning: {
					title: 'O Nó está sendo inicializado',
					message: 'Por favor, espere até o nó inicializar completamente para poder enviar sua transação.'
				}
			},
			clientInfo: {
				title: "Informações do programa",
				ncc: "NEM Community Client - NCC",
				signer: "Signatário",
				remoteServer: "Servidor Remoto",
				local: "Local",
				nis: "NEM Infrastructure Server - NIS",
				sync: "Sincronizado",
				notSync: "Não sincronizado",
				notConnected: "Desconectado da nuvem de peers",
				loading: "Carregando..."
			},
			transactionDetails: {
				title: "Detalhes da transação",
				id: "ID",
				hash: "Hash",
				type: "Tipo de transação",
				direction: 'Transaction Direction',
				pending: "Pendente",
				outgoing: "Enviado",
				incoming: "Recebido",
				self: "Enviado para si mesmo",
				sender: "Remetente",
				multisigAccount: 'Multisig Account',
				issuer: 'Issuer',
				recipient: "Destinatário",
				remote: 'Remote',
				multisigMessage: 'Signatures present',
				message: "Mensagem",
				noMessage: "Sem mensagem",
				encrypted: "Mensagem Criptografada",
				time: "Data e hora",
				confirmations: "Confirmações",
				confirmationsUnknown: 'Desconhecida(o)',
				amount: "Quantidade",
				fee: "Taxa",
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
				title: "Inicializar nó local",
				account: "Conta do nó local",
				noLabel: "<span class=\"null\">&lt;Sem identificação&gt;</span>",
				wallet: "Nome da Carteira",
				node: "Nome para o nó",
				boot: "Inicializar",
				booting: "Inicializando..."
			},
			closeWallet: {
				title: "Fechar carteira",
				message: "Confirmar o fechamento da carteira e o retorno à página inicial?"
			},
			createAccount: {
				title: "Criar nova conta",
				label: "Identificação",
				wallet: "Carteira",
				password: "Senha",
				successMessage: "A conta {{1}} {{#2}}({{2}}){{/2}} foi criada! Por favor, guarde uma cópia em um local seguro.",
				create: "Criar"
			},
			createRealAccountData: {
				title: "Criar dados para sua conta real.",
				message: "Os dados abaixo são para sua conta NEM após o lançamento. Salve o endereço, a chave pública e a chave privada em um lugar SEGURO. Se você perder a sua chave privada, a sua carteira e todas as suas moedas estarão perdidas para SEMPRE!",
				address: "Endereço",
				publicKey: "Chave pública",
				privateKey: "chave privada",
				confirm: {
					title: "Salvar a chave privada",
					message: "Você tem certeza que a sua chave privada foi salva em um local seguro?"
				},
				recheck: {
					title: "Re-checar sua chave privada",
					message: "Por favor, entre novamente com a sua chave privada que acabou de ser fornecida para checarmos se o arquivo foi salvo corretamente. Em caso negativo, por favor, crie uma nova.",
					correct: {
						title: "Beleza!",
						message: "Aparentemente você salvou a chave privada corretamente. Por favor, lembre-se de mantê-la em um local seguro e, se possível, com backups!"
					},
					incorrect: {
						title: "Iche...",
						message: "A chave privada que você forneceu não está correta! Por favor, cheque-a de novo e entre com os dados mais uma vez.",
						tryAgain: 'Tente inserí-la novamente',
						seeOriginal: 'Veja os dados originais'
					},
					recheck: "Checar"
				},
				ok: "OK"
			},
			verifyRealAccountData: {
				title: 'Verificar conta Verdadeira - Versão final',
				message: 'Entre novamente, logo abaixo, com seu endereço, chave pública, chave privada para verificar se está tudo certo.',
				address: 'Endereço',
				publicKey: 'Chave Pública',
				privateKey: 'Chave Privada',
				dataMatched: 'Tudo parece certo! A chave pública, chave privada e endereço que você entrou combinam!',
				verify: 'Verificar'
			},
			addAccount: {
				title: "Adicionar uma Carteira existente",
				privateKey: "Chave privada",
				wallet: "Carteira",
				password: "Senha",
				successMessage: "A conta {{1}} {{#2}}({{2}}){{/2}} foi criada! Por favor, realize um backup e guarde-o em local seguro!",
				add: "Criar",
				label: "Identificação"
			},
			setPrimary: {
				title: "Definir como conta primária",
				account: "Conta que será definida como primária",
				noLabel: "<span class=\"null\">&lt;Sem identificação&gt;</span>",
				wallet: "Carteira",
				password: "Senha",
				successMessage: "Conta {{1}} {{#2}}({{2}}){{/2}} foi definida como primária!",
				set: "Definir como conta primária",

			},
			changeWalletName: {
				title: "Renomear Carteira",
				wallet: "Nome atual",
				newName: "Novo nome",
				password: "Senha",
				successMessage: "O nome <em>{{1}}</em> foi alterado para <em>{{2}}</em>",
				change: "Renomear"
			},
			changeWalletPassword: {
				title: "Trocar a senha",
				wallet: "Nome da carteira",
				password: "Senha atual",
				newPassword: "Nova senha",
				confirmPassword: "Confirmar nova senha",
				successMessage: "Nova senha definida",
				change: "Trocar",
				passwordNotMatchTitle: "Oopa!",
				passwordNotMatchMessage: "As senhas não conferem. Por favor, tente novamente."
			},
			changeAccountLabel: {
				title: "Trocar identificação da conta",
				label: "Nova identificação",
				wallet: "Carteira",
				password: "Senha",
				successMessage: "A Identificação {{1}} foi alterada para {{2}}",
				change: "Trocar"
			},
			removeAccount: {
				title: "Remover conta",
				wallet: "Carteira",
				password: "Senha",
				warning: "Esta operacao não pode ser desfeita.",
				successMessage: "A conta {{1}} {{#2}}({{2}}){{/2}} foi removida!",
				remove: "Remover"
			},
			nisUnavailable: {
				title: "NIS não está disponível",
				message: "NIS não está respondendo, aguardando conexão"
			},
			shutdown: {
				title: "Fechar programa",
				message: "Tem certeza que quer fechar o programa (NCC)?"
			},
			activateRemote: {
				title: "Ativar colheita remota",
				wallet: "Carteira",
				account: "Conta",
				hoursDue: "Tempo de espera",
				password: "Senha",
				activate: "Ativar"
			},
			deactivateRemote: {
				title: "Desativar colheita remota",
				wallet: "Carteira",
				account: "Conta",
				hoursDue: "Tempo para desativar",
				password: "Senha",
				deactivate: "Desativar"
			},
			startRemote: {
				title: "Iniciar colheita remota",
				wallet: "Carteira",
				account: "Conta",
				password: "Senha",
				start: "Iniciar"
			},
			stopRemote: {
				title: "Parar colheita remota",
				wallet: "Carteira",
				account: "Conta",
				password: "Senha",
				stop: "Parar"
			},
			logoutWarning: {
				leavePage: "Você está saindo da carteira. Ela permanecerá aberta neste computador\n\nPara fazer Logout, por favor, clique em \"Fechar carteira\" no menu superior direito.",

			},
			addContact: {
				title: 'Add contact',
				add: "Criar"
			},
			editContact: {
				title: 'Edit contact',
				saveChanges: "Salvar modificações",

			},
			removeContact: {
				title: 'Remove contact',
				remove: "Remover",

			}
		},
		landing: {
			logo: "images/nem_logo.png",
			importSuccess: "Carteira importada!",
			nav: {
				start: "Início",
				about: "Sobre NEM",
				settings: "Configurações"
			},
			main: {
				leftTitle: "Primeira vez?<br>Crie uma carteira <em>NEM</em>!",
				leftButton: "Criar nova carteira",
				walletNamePlh: "Nome para a carteira",
				passwordPlh: "Senha",
				confirmPasswordPlh: 'Confirm password',
				create: "Criar",
				creating: 'Creating...',
				rightTitle: "Já possui uma carteira?",
				rightButton: "Abrir carteira existente",
				openButton: "Abrir",
				walletsFound: "Encontrada(s) <strong>{{1}}</strong> <em>carteira(s)</em>",
				copyright: "Fotografado por <em>Cas Cornelissen</em>"
			},
			carousel: {
				items: [
					{
						title: "Como o NCC funciona?",
						description: "<em>Segurança</em> baseada em forte criptografia para evitar roubo de moedas &amp; ativos."
					},
					{
						title: "NCC encripta sua carteira",
						description: "<em>Segurança</em> baseada em forte criptografia para evitar roubo de moedas &amp; ativos."
					}
				]
			},
			about: {
				sections: [
					{
						title: "Como o NCC funciona?",
						paragraphs: [
							"<strong>NCC</strong> providencia acesso aos seus ativos e moedas como uma carteira de moeda digital tradicional faz. Você pode",
							"<strong>NCC</strong> requerer acesso a um servidor <strong>NIS</strong> para operar. O padrão é ter um servidor local ativo (está instalado junto com o <strong>NCC</strong>)",
							"Você também pode configurar acesso a um servidor remoto <strong>NIS</strong>."
						],
						listItems: [
							"Tenha múltiplas carteiras",
							"Defina múltiplas carteiras para serem incluídas em uma carteira"
						]
					},
					{
						title: "O que é &#42;NIS?",
						paragraphs: [
							"Este componente é responsável por manter a nuvem <strong>NEM</strong> viva.",
							"Quanto mais <strong>NIS</strong> melhor será a segurança.",
							"<strong>NIS</strong> é o ponto de acesso a nuvem <strong>NEM</strong> de servidores."
						],
						legend: "<strong>&#42;NIS</strong> significa <strong>NEM Infraestrutura de Servidores</strong>"
					}
				]
			},
			footer: {
				copyright: "&copy; Copyright 2014. NEM Community Client."
			}
		},
		wallet: {
			logo: "images/nem_logo.png",
			lastAccess: "Aproximadamente {{1}} dia(s) atrás",
			lastAccessJustNow: "Agora a pouco",
			lastAccessTooltip: "Ultimo acesso em {{1}}",
			primary: "primário",
			primaryShort: "1º",
			noLabel: "<Sem identificação>",
			copiedToClipboard: "Endereço copiado para a área de transfêrencia!",
			actions: {
				refreshInfo: "Atualizar informações",
				bootLocalNode: "Inicializar nó",
				changeWalletName: "Trocar o nome da carteira",
				changeWalletPassword: "Trocar a senha da carteira",
				mergeWallets: "Mesclar carteiras",
				exportWallet: "Exportar carteira",
				createAccount: "Criar uma nova Conta",
				createRealAccountData: "Criar dados da conta real",
				verifyRealAccountData: 'Verificar dados da conta Verdadeira',
				addAccount: "Adicionar uma conta existente",
				changeAccountLabel: "Renomear carteira",
				setPrimary: "Definir como conta primária",
				removeAccount: "Remover Conta",
				clientInfo: "Informações do Programa",
				closeWallet: "Fechar carteira",
				closeProgram: "Fechar programa",
				copyClipboard: "Copiar para a área de transferência",
				convertMultisig: 'Convert to multisig'
			},
			nav: [
				"Painel Administrativo",
				"Mensagens",
				'Address Book',
				"Transações",
				"Blocos colhidos",
				"Exchange de ativos",
				"Novidades",
				"Aplicações",
				"Contas",
				"Configurações",
				"Fechar programa"
			],
			bootNodeWarning: "Um nó local precisa ser inicializado para poder utilizar o NIS."
		},
		dashboard: {
			assets: {
				title: "Seus ativos"
			},
			importance: {
				title: "Pontuação de importância",
				unknown: "Status desconhecido",
				start: "Começar colheita local",
				harvesting: "colheitando",
				stop: "Parar colheita local",
				description: "Importância para a nuvem de peers",
				remoteHarvest: {
					activate: "Ativar colheita remota",
					activating: "Ativando...",
					active: "A colheita remota está ativa!",
					deactivate: "Desativar colheita remota",
					deactivating: "Desativando colheita remota...",
					startRemoteHarvesting: "Inicializar colheita remota",
					remotelyHarvesting: "Colheitando remotamente!",
					stopRemoteHarvesting: "Parar a colheita remota"
				}
			},
			transactions: {
				title: "Transações recentes",
				sendNem: "Enviar NEM",
				signMultisig: 'SIGN',
				balance: "Saldo atual",
				syncStatus: "(no bloco {{1}}{{#2}} : encontrado a {{3}} dias atrás{{/2}})",
				unknown: "Desconhecido",
				columns: [
					"",
					"Data",
					"Destinatário",
					"Mensagem",
					"",
					"Detalhes",
					"Confirmações",
					"Taxa",
					"Quantidade"
				],
				noMessage: "Sem mensagem",
				encrypted: "Mensagem criptografada",
				view: "Detalhes",
				confirmationsUnknown: 'Desconhecido(a)',
				pending: "Pendente",
				seeAll: "Ver todas as transações",
				noTransactions: "Nenhuma transação realizada ainda."
			},
			nemValue: {
				title: "Estatísticas desta carteira"
			},
			messages: {
				titleTooltip: "Mensagens"
			},
			news: {
				titleTooltip: "Novidades"
			},
			notAvailable: "Não disponível na versão Alfa"
		},
		transactions: {
			title: "Transações",
			sendNem: "Enviar NEM",
			balance: "Saldo",
			filters: {
				confirmed: "Confirmadas",
				unconfirmed: "Não confirmadas",
				incoming: "Recebidas",
				outgoing: "Enviadas",

			},
			table: {
				columns: [
					"",
					"Tempo",
					"Remetente/Destinatário",
					"Mensagem",
					"",
					"Detalhes",
					"Confirmações",
					"Taxa",
					"Quantidade"
				],
				noMessage: "Sem mensagem",
				encrypted: "Mensagem criptografada",
				view: "Detalhes",
				confirmationsUnknown: 'Desconhecido(a)',
				pending: "Pendente",
				noTransactions: "Nenhuma transação realizada.",
				loading: "Carregando mais transações..."
			}
		},
		harvestedBlocks: {
			title: "Blocos colhidos",
			feeEarned: "Taxas ganhas com a colheita dos últimos 25 blocos",
			unknown: '...',
			table: {
				columns: [
					"Altura do bloco",
					"Hora",
					"Hash do bloco",
					"Taxa"
				],
				noBlocks: "Não há blocos colhidos",
				loading: "Ver blocos colhidos anteriormente"
			},
			harvesting: {
				unknown: "Status desconhecido",
				start: "Iniciar a colheita",
				harvesting: "Colheitando",
				stop: "Parar de colheitar",
				remoteHarvest: {
					startRemoteHarvesting: 'Iniciar colheita remota',
					stopRemoteHarvesting: 'Parar colheita remota'
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
			sendNem: "Enviar NEM",
			edit: 'Edit',
			remove: "Remover"
		},
		settings: {
			title: "Opções",
			settings: [
				{
					name: "Idioma"
				}
			],
			save: "Salvar modificações",
			saveSuccess: "Modificações salvas"
		}
	}
});
