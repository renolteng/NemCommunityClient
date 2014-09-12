define({
	id: 'pt',
	name: 'Português',
	texts: {
		faults: { // mensagens de modais de erro 
            101: 'Arquivo não encontrado.',
            102: 'A carteira não foi criada.',
            103: 'O arquivo da carteira está corrompido. Por favor, recupere sua carteira de um back-up que você deveria ter feito quando a criou.',
            104: 'A senha está incorreta. Esperamos que você consiga se lembrar da senha correta. Ela não pode ser recuperada se for perdida!',
            106: 'Antes de você começar a usar uma carteira, ela precisa ser aberta. Para garantir que você pode acessá-la, você precisará fornecer a senha para aquela carteira.',
            107: 'A carteira não contém essa conta',
            108: 'A conta não pode ser removida. Provavelmente pelo motivo de que a conta tem saldo maior que 0 NEMs ou a conta que você está tentando deletar é sua conta primária.',
            109: 'Uma outra conta com o mesmo nome já existe. Por favor, escolha outro nome para sua carteira.',
            110: 'A carteira já contem essa conta.',
            202: 'Sem chave pública de criptografia',
            305: 'NEM Infraestrutura de Servidores indisponível',
            306: 'Ocorreu um erro que o time de desenvolvedores não havia previsto. Pedimos desculpas por esse problema. Talvez uma nova tentativa possa resolver o problema. Caso contrário, abra uma reclamação de problemas NIS/NCC na comunidade NEM',
            400: 'Está faltando algum parâmetro.',
            404: 'Valor de estratégia de boot inválido.',
            500: 'Falha ao salvar o arquivo de configuração.',
            600: 'NCC requer que o servidor NIS local seja inicializado para envio e recebimento de transações na nuvem NEM. Por favor, utilize a entrada de menu no cliente NCC para inicializar o nó local.',
	     601: 'O seu nó NIS já foi iniciado. Não é possível tentar reinicia-lo uma segunda vez.',
            700: 'A conta fornecida não satisfaz o critério básico para colheira. Geralmente esse problema é relacionado com o tanto de NEMs na conta. Para a colheira iniciar, são necessários, pelo menos, 1000 NEMs.',
            701: 'O prazo final fornecido está no passado. O prazo limite deve estar dentro do período de um dia.',
            702: 'O prazo final está muito no futuro. O prazo limite deve estar dentro do período de um dia.',
            703: 'Sua conta não tem o saldo suficiente para o envio da quantidade solicitada de NEMs.',
            704: 'O texto fornecido é muito grande para ser enviado via NEM. Por favor, tente reduzir o tamanho da mensagem que você quer enviar.',
            705: 'O Hash de transação já existe no banco de dados, na lista de transações não confirmadas.',
            706: 'A assinatura da transação não pode ser verificada.',
            707: 'A data da Id de transação está muito no passado.',
            708: 'A data da Id de transação está muito no futuro.',
            709: 'A conta é desconhecida pela rede. Uma conta precisa estar envolvida em pelo menos uma transação, seja com remetente ou destinatário, para ser conhecida pela rede.',
            901: 'Houve um erro na configuração de modo "off-line".'
        },
        common: {
        	success: 'Successo', //title of the Success message modals
        	nisStatus: {
        		unavailable: 'NIS não está disponível',
        		notBooted: 'NIS necessita ser inicializado. Por favor, abra a carteira e inicialize o nó NIS local via diálogo pop-up.',
        		synchronizing: 'NIS está sincronizando no bloco {{1}}. Estimativa de {{2}} atrás.',
                daysBehind: {
                    0: 'Menos que um dia',
                    1: '1 dia',
                    many: '{{1}} dias'
                }
        	}
        },
		modals: {
			error: {
				title: 'Oopa!',
				caption: 'ERRO {{1}}'
			},
			confirmDefault: {
				yes: 'Sim',
				no: 'Não'
			},
			sendNem: {
				title: 'Enviar NEM',
				labelDesc: 'Esta conta está identificada como <strong>{{1}}</strong>',
				nullLabelDesc: "Esta conta não tem uma identificação",
				amount: 'Total',
				recipient: "Destinatário",
				message: 'Mensagem',
				encrypt: 'Encriptar mensagem',
				fee: 'Taxa',
				dueBy: 'Devido por', //precisara de correção dependendo do que faz
				resetFee: 'Reestabelecer o valor mínimo de taxa',
				hours: 'Horas',
				password: 'Password',
				send: 'Enviar',
				sending: 'Enviando...',
				successMessage: 'A transação foi enviada com sucesso!'
			},
			clientInfo: {
				title: 'Informações do cliente',
				ncc: 'NEM Cliente da comunidade - NCC',
				signer: 'Signatário',
				remoteServer: 'Servidor Remoto',
				local: 'Local',
				nis: 'NEM Infraestrutura de Servidores  - NIS',
				sync: 'Sincronizado',
				notSync: 'Não sincronizado',
				notConnected: 'Não conectado com a nuvem NEM',
				loading: 'Carregando...'
			},
			transactionDetails: {
				title: 'Detalhes da transação',
				id: 'ID',
				hash: 'Hash',
				type: 'Tipo de transação',
				pending: 'Pendente',
				outgoing: 'Saindo',
				incoming: 'Chegando',
				self: 'Pessoal',
				sender: 'Remetente',
				recipient: 'Destinatário',
				message: 'Mensagem',
				noMessage: 'Sem mensagem',
				encrypted: 'A mensagem está encriptada',
				time: 'Data e hora',
				confirmations: 'Confirmações',
				amount: 'Quantidade',
				fee: 'Taxa'
			},
			bootLocalNode: {
				title: 'Inicializar o nó local',
				account: 'Conta para inicializar o nó local',
				noLabel: '&lt;Sem identificação&gt;',
				wallet: 'Carteira',
				node: 'Nome do nó',
				boot: 'Inicializar',
				booting: 'Inicializando...'
			},
			notBootedWarning: {
				title: 'O nó não foi inicializado!',
				message: 'O nó local precisa ser inicializado antes de você poder enviar moedas NEM!'
			},
			closeWallet: {
				title: 'Fechar carteira',
				message: 'Você tem certeza que quer fechar a sua carteira e retornar para a página inicial?'
			},
			createAccount: {
				title: 'Criar nova conta',
				label: 'Identificação pessoal',
				wallet: 'Carteira',
				password: "Senha da carteira",
				successMessage: 'Conta {{1}} {{#2}}({{2}}){{/2}} foi criada! Não se esqueça de realizar back-up da conta',
				create: 'Criar'
			},
			addAccount: {
				title: 'Adicionar uma conta existente',
				privateKey: "Chave privada da conta",
				wallet: 'Carteira',
				password: "Senha da carteira",
				successMessage: 'Conta {{1}} {{#2}}({{2}}){{/2}} foi criada!',
				add: 'Adicionar',
				label: 'Identificação'
			},
			setPrimary: {
				title: 'Definir conta primária',
				account: 'Conta para ser definida como primária',
				noLabel: '&lt;Sem identificação&gt;',
				wallet: 'Carteira',
				password: "Senha da carteira",
				successMessage: 'Conta {{1}} {{#2}}({{2}}){{/2}} foi definida como primária!',
				set: 'Definir como primária',
			},
			changeWalletName: {
				title: 'Mudar o nome da carteira',
				wallet: 'Nome atual da carteira',
				newName: 'Novo nome da carteira',
				password: "Senha da carteira",
				successMessage: 'O nome da carteira foi trocado com sucesso de <em>{{1}}</em> para <em>{{2}}</em>',
				change: 'Trocar'
			},
			changeWalletPassword: {
				title: 'Trocar a senha da carteira',
				wallet: 'Carteira',
				password: 'Senha atual',
				newPassword: 'Nova senha',
				confirmPassword: 'Confirmar nova senha',
				successMessage: 'A senha da carteira foi trocada com sucesso',
				change: 'Trocar',
				passwordNotMatchTitle: 'Oopa!',
				passwordNotMatchMessage: 'Sua senha digitada não confere com a confirmação da senha. Por favor, tenha certeza que você digitou a sua nova senha corretamente.'
			},
			changeAccountLabel: {
				title: 'Trocar identificação da conta',
				label: 'Identificação da conta',
				wallet: 'Carteira',
				password: "Senha da carteira",
				successMessage: 'A Conta {{1}} agora está identificada como {{2}}',
				change: 'Troca'
			},
			removeAccount: {
				title: 'Remover conta',
				wallet: 'Senha',
				password: "Senha da carteira",
				warning: 'Por favor, tenha certeza que sua conta não tem NEMs restantes antes de você removê-la, ou as moedas serão perdidas para sempre.',
				successMessage: 'A conta {{1}} {{#2}}({{2}}){{/2}} foi removida!',
				remove: 'Remover'
			},
			nisUnavailable: {
				title: 'NIS não está disponível',
				message: 'Desconectado de NIS, aguardando conecção'
			},
			shutdown: {
				title: 'Fechar programa',
				message: 'Você tem certeza que você quer fechar o cliente da comunidade NEM?' //talvez precise correção aqui
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'A carteira foi importada com sucesso!',
			nav: {
				start: 'Inicie Aqui',
				about: 'Sobre NEM',
				help: 'Ajuda'
			},
			main: {
				leftTitle: 'Novo por aqui? Use <em>NEM</em>?',
				leftButton: 'Criar nova carteira',
				walletNamePlh: 'Nome para sua carteira',
				passwordPlh: 'Senha',
				create: 'Criar',
				rightTitle: 'Já é um membro <em>NEM</em>?',
				rightButton: 'Abrir sua carteira',
				openButton: 'Abrir',
				walletsFound: 'Encotrei <strong>{{1}}</strong> <em>carteira(s)</em>', //needs to verify if the "(s)" casues any kind of problem to compilation
				copyright: 'Fotografia por <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC encripta sua carteira',
						description: '<em>Segurança</em> baseada em forte criptografia para evitar roubo de moedas &amp; ativos.'
					},										
					{
						title: 'NCC encripta sua carteira',
						description: '<em>Segurança</em> baseada em forte criptografia para evitar roubo de moedas &amp; ativos.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'Como o NCC funciona?',
						paragraphs: [
							'<strong>NCC</strong> providencia acesso aos seus ativos e moedas como uma carteira de moeda digital tradicional faz. Você pode',
							'<strong>NCC</strong> requerer acesso a um servidor <strong>NIS</strong> para operar. O padrão é ter um servidor local ativo (está instalado junto com o <strong>NCC</strong>)',
							'Você também pode configurar acesso a um servidor remoto <strong>NIS</strong>.'
						],
						listItems: [
							'Tenha multiplas carteiras',
							'Defina multiplas carteiras para serem incluidas em uma carteira'
						]
					},
					{
						title: 'O que é &#42;NIS?',
						paragraphs: [
							'Este componente é responsável por manter a nuvem <strong>NEM</strong> viva.',
							'Quanto mais <strong>NIS</strong> melhor será a segurança.',
							'<strong>NIS</strong> é o ponto de acesso a nuvem <strong>NEM</strong> de servidores.'
						],
						legend: '<strong>&#42;NIS</strong> significa <strong>NEM Infraestrutura de Servidores</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2014. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: 'Aproximadamente {{1}} dia(s) atrás',
			lastAccessJustNow: 'Agora pouco',
			lastAccessTooltip: 'Ultimo acesso foi em {{1}}',
			primary: 'primário',
			primaryShort: 'P',
			noLabel: '<Sem identificação>',
			copiedToClipboard: 'O endereço foi copiado para a memória!',
			actions: {
				refreshInfo: 'Atualizar informações',
				bootLocalNode: 'Inicializar o nó local',
				changeWalletName: 'Trocar o nome da carteira',
				changeWalletPassword: 'Trocar a senha da carteira',
				mergeWallets: 'Mesclar carteiras',
				exportWallet: 'Exportar carteira',
				createAccount: 'Criar uma nova Conta',
				addAccount: 'Adicionar uma conta existente',
				changeAccountLabel: 'Trocar a identificação da carteira',
				setPrimary: 'Definir como conta primária',
				removeAccount: 'Remover a Conta',
				clientInfo: 'Informação do cliente',
				closeWallet: 'Fechar a carteira',
				closeProgram: 'Fechar o programa',
				copyClipboard: 'Copy address to clipboard'
			},
			nav: [
				'Painel', //Dashboard
				'Mensagens',
				'Contatos',
				'Transações',
				'Blocos colheitados',
				'Permuta de ativos',
				'Novidades',
				'Aplicações',
				'Contas',
				'Configurações',
				'Fechar programa'
			],
			bootNodeWarning: "O nó local precisa ser inicializado antes que você possa utilizar todos os recurosos do NCC."
		},
		dashboard: {
			assets: {
				title: 'Seus ativos'
			},
			importance: {
				title: 'Pontuação de importância',
				unknown: 'Status desconhecido',
				start: 'Começar a colheita',
				harvesting: 'Colheitando',
				stop: 'Parar colheita',
				description: 'importância para a nuvem NEM'
			},
			transactions: {
				title: 'Transações recentes',
				sendNem: 'Enviar NEM',
				balance: 'Saldo atual',
				syncStatus: '(no bloco {{1}}{{#2}} : previsão de {{3}} dias atrás{{/2}})',
				unknown: 'Desconhecido',
				columns: [
					'',
					'Tempo',
					'Remetente/Destinatário',
					'Mensagem',
					'',
					'Detalhes',
					'Confirmações',
					'Taxa',
					'Quantidade'
				],
				types: {
					pending: 'Transação pendente',
					outgoing: 'Transação saindo',
					incoming: 'Transação chegando',
					self: 'Você enviou para você mesmo',
				},
				noMessage: 'Sem mensagem',
				encrypted: 'Mensagem encriptada',
				view: 'Ver',
				pending: 'Pendente',
				seeAll: 'Ver todas as transações',
				noTransactions: 'Nenhuma transação foi realizada ainda.'
			},
			nemValue: {
				title: 'Valores das estatísticas NEM'
			},
			messages: {
				titleTooltip: 'Mensagens'
			},
			news: {
				titleTooltip: 'Novidades'
			},
			notAvailable: 'Não disponível na versão Alfa'
		},
		transactions: {
			title: 'Transações',
			sendNem: 'Enviar NEM',
			balance: 'Saldo atual',
			filters: {
				confirmed: 'Confirmado',
				unconfirmed: 'Não confirmado',
				incoming: 'Chegando',
				outgoing: 'Saindo',
			},
			table: {
				columns: [
					'',
					'Tempo',
					'Remetente/Destinatário',
					'Mensagem',
					'',
					'Detalhes',
					'Confirmações',
					'Taxa',
					'Quantidade'
				],
				types: {
					pending: 'Transação pendente',
					outgoing: 'Transações saindo',
					incoming: 'Transações chegando',
					self: 'Você enviou para você mesmo',
				},
				noMessage: 'Sem mensagem',
				encrypted: 'A mensagem está encriptada',
				view: 'Ver', 
				pending: 'Pendente',
				noTransactions: 'Por enquanto nenhuma transação foi realizada',
				loading: 'Carregando mais transações...'
			}
		},
		harvestedBlocks: {
			title: 'Blocos colhidos',
			feeEarned: 'Taxas ganhas com a colheita dos ultimos 25 blocos',
			table: {
				columns: [ 
					'Altura do bloco',
					'Hora',
					'Hash do bloco',
					'Taxa'
				],
				noBlocks: 'Não há blocos colhidos',
				loadMore: 'Ver blocos colhidos anteriormente'
			},
			harvesting: {
				unknown: 'Status desconhecido',
				start: 'Começar a colheita',
				harvesting: 'Colheitando',
				stop: 'Pare de colheitar'
			}
		},
		settings: {
			title: 'Opções',
			settings: [
				{
					name: 'Linguagem'
				}
			],
			save: 'Salvar modificações',
			saveSuccess: 'As modificações foram salvas com sucesso'
		}
	}
});
