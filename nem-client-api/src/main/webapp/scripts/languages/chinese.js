define({
	id: 'cn',
	name: '中文',
	texts: {
		preferences: {
			thousandSeparator: '\u2009',
			decimalSeparator: '.'
		},
		faults: {
            101: '未找到文件。',
            102: '尚未建立钱包。',
            103: '钱包文件已损坏。当您创建的钱包你如果有备份的话，请使用备份找回你的账户，或添加一个新账户。',
            104: '提供的密码不正确。希望您能记住正确的密码。该密码如果丢了无法复原！',
            106: '在使用一个钱包之前，它必须被打开。您必须提供正确密码才有资格打开钱包。',
            107: '钱包不含此账户。',
            108: '该帐户不能被删除。很有可能是因为该帐户仍然有大于0MEM的余额或您要删除的帐户是主帐户。',
            109: '具有相同名称的另一个钱包已经存在。请选择一个其它的钱包名。',
            110: 'Wallet already contains this account.',
            202: '没有公钥。',
            305: 'NEM 基础设施服务器无法使用。',
            306: '对不起，发生一个开发团队没有预见到的错误。重试也许有帮助。否则，请在NEM NIS/NCC社区内提交您碰到的问题。',
            400: '缺失某些参数。',
            404: '启动策略值无效。',
            500: '保存配置文件失败。',
            600: 'NCC需要启动NIS服务器去从 NEM Cloud 发送和接收交易数据。请使用NCC菜单项启动本地节点。',
            601: 'NIS节点已启动。不可能启动第二个NIS节点。',
            700: '所提供的帐户不满足收割的基本标准。主要是涉及到帐户中的NEM金额。必须至少有10.000 NEMs才能开始收割。',
            701: '所提供的截止日期是在过去。截止日期必须在1天之内提供。',
            702: '所提供的截止日期是在太远的未来。截止时间必须在一天之内的时间段。',
            703: '您的帐户没有正确的金额支持您要发送的NEM额。',
            704: '提供消息的文本是太大，无法通过NEM发送。请减少要发送消息的长度。',
            705: '数据库，或未确认交易列表，已经存在此交易的散列值。',
            706: '无法验证本次交易的签名。',
            707: '此交易ID的时间戳在太远的过去。',
            708: '此交易ID的时间戳在太远的将来。',
            709: '该帐户未知。一个帐户需要至少经过一个交易（作为发件人或收件人）才能被网络公知。',
            901: 'There was an error setting up offline mode.'
        },
        common: {
        	success: '成功', //title of the Success message modals
        	appStatus: {
        		nccUnknown: 'NCC status is unknown',
        		nccUnavailable: 'NCC is not available',
        		nccStarting: 'NCC is starting...',
        		nisUnknown: 'NIS status is unknown',
        		nisUnavailable: 'NIS is not available',
        		nisStarting: 'NIS is starting...',
        		notBooted: 'NIS requires to be booted. Please open your wallet and boot a local node via the popup dialog or configure the auto-boot setting.',
        		booting: 'Booting NIS...',
        		nisInfoNotAvailable: 'NIS info is not avaiable yet. Trying to retrieve NIS info...',
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
				title: '哎呀!',
				caption: '错误 {{1}}'
			},
			confirmDefault: {
				yes: '是',
				no: '不'
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
				title: '发送 NEM',
				labelDesc: '此帐户被标记为 {{1}}',
				nullLabelDesc: "此帐户没有标记",
				amount: '金额',
				recipient: "收件人账号",
				message: '信息',
				encrypt: '加密信息',
				fee: '费',
				dueBy: '限期（小时）',
				resetFee: '重设最低收费',
				hours: '小时',
				password: '密码',
				send: '发送',
				sending: '发送...',
				successMessage: '交易发送成功!'
			},
			clientInfo: {
				title: '客户信息',
				ncc: 'NEM 社区客户端 - NCC',
				signer: '签名者',
				remoteServer: '远程服务器',
				local: '本地',
				nis: 'NEM 基础设施服务器 - NIS',
				sync: '完成同步',
				notSync: '没完成同步',
				notConnected: '没有连接 NEM Cloud',
				loading: '加载中...'
			},
			transactionDetails: {
				title: '交易详情',
				// this might be block or transaction ID
				id: 'ID',
				// this might be block or transaction Hash
				hash: 'Hash',
				type: '交易类型',
				pending: '待定',
				outgoing: '传出',
				incoming: '接入',
				self: '本人',
				sender: '发件人',
				recipient: '收件人',
				message: '信息',
				noMessage: '没信息',
				encrypted: '信息被加密',
				time: '时间戳',
				confirmations: '确认',
				amount: '金额',
				fee: '费'
			},
			bootLocalNode: {
				title: '启动本地节点',
				account: '启动本地节点的帐号',
				noLabel: '<span class="null">&lt;没有标识&gt;</span>',
				wallet: '钱包',
				node: '节点名称',
				boot: '启动',
				booting: '启动...'
			},
			notBootedWarning: {
				title: '节点还没有启动!',
				message: '本地节点需要被启动然后才能发送NEM!'
			},
			closeWallet: {
				title: '关闭钱包',
				message: '您确定要关闭钱包并返回到登陆页面?'
			},
			createAccount: {
				title: '创建新帐号',
				label: '私人标识',
				wallet: '钱包',
				password: "钱包的密码",
				successMessage: '帐号 {{1}} {{#2}}({{2}}){{/2}} 已创建!',
				create: '创建'
			},
			addAccount: {
				title: '添加一个现有帐户',
				privateKey: '帐户的私钥',
				wallet: '钱包',
				password: "钱包的密码",
				successMessage: '帐户 {{1}} {{#2}}({{2}}){{/2}} 已被添加到钱包!',
				add: '添加',
				label: '标识'
			},
			setPrimary: {
				title: '设置主账户',
				account: '帐户被设置为主账户',
				noLabel: '<span class="null">&lt;无标识&gt;</span>',
				wallet: '钱包',
				password: "钱包的密码",
				successMessage: '帐户 {{1}} {{#2}}({{2}}){{/2}} 被设置为主账户!',
				set: '设置为主账户',
			},
			changeWalletName: {
				title: '改钱包名',
				wallet: '当前钱包名',
				newName: '新钱包名',
				password: "钱包密码",
				successMessage: '钱包名称已变更成功从<em>{{1}}</em>到<em>{{2}}</em>',
				change: '改'
			},
			changeWalletPassword: {
				title: '改钱包密码',
				wallet: '钱包',
				password: '当前密码',
				newPassword: '新密码',
				confirmPassword: '确认新密码',
				successMessage: '钱包密码已经修改成功',
				change: '改',
				passwordNotMatchTitle: '糟!',
				passwordNotMatchMessage: '您输入的密码和确认密码吻合。请务必正确输入您的新密码'
			},
			changeAccountLabel: {
				title: '更改帐户标识',
				label: '帐户标识',
				wallet: '帐户',
				password: "钱包密码",
				successMessage: '帐户{{1}}现在的标识是{{2}}',
				change: '改'
			},
			removeAccount: {
				title: '删除帐户',
				wallet: '钱包',
				password: "钱包密码",
				warning: '在你删除帐户前请确保您的帐户没MEM，否则会被永远失去了',
				successMessage: '帐户 {{1}} {{#2}}({{2}}){{/2}} 被删除了!',
				remove: '删除'
			},
			nisUnavailable: {
				title: 'NIS 不可用',
				message: '从NIS断开连，等待连接'
			},
			shutdown: {
				title: '关闭程序',
				message: '您确定要关闭NEM社区客户端?'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: '钱包已成功导入!',
			nav: {
				start: '已经 开始',
				about: '关于 NEM',
				settings: 'Settings'
			},
			main: {
				leftTitle: '<em>NEM</em>新手?',
				leftButton: '创建新钱包',
				walletNamePlh: '你钱包的名称',
				passwordPlh: '密码',
				create: '创建',
				rightTitle: '已经是<em>NEM</em>玩家?',
				rightButton: '打开你的钱包',
				openButton: '打开',
				walletsFound: '发现 <strong>{{1}}</strong> <em>钱包</em>',
				copyright: '由<em>Cas Cornelissen</em>摄影'
			},
			carousel: {
				items: [
					{
						title: 'NCC 加密你的钱包',
						description: '<em>安全</em> 是非常重要的以避免NEM被盗 &amp;。'
					},
					{
						title: 'NCC 加密你的钱包',
						description: '<em>安全</em> 是非常重要的以避免NEM被盗 &amp;。'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'NCC是如何工作的?',
						paragraphs: [
							'<strong>NCC</strong> 让你使用NEMs就如使用一个传统的钱包一样。',
							'<strong>NCC</strong> 你可已使用一个<strong>NIS</strong> 服务器操作。 常规是使用一个本地服务器 (和<strong>NCC</strong>一起安装)',
							'您也可以访问一个远程<strong>NIS</strong>。'
						],
						listItems: [
							'有多个钱包',
							'在一个钱包里定义多个帐户'
						]
					},
					{
						title: '什么是&#42;NIS?',
						paragraphs: [
							'负责保持<strong>NEM</strong>云活着.',
							'越多<strong>NIS</strong>服务器安全性越好。',
							'<strong>NIS</strong> 是进入 <strong>NEM</strong> 云的接入点。'
						],
						legend: '<strong>&#42;NIS</strong> 是 <strong>NEM Infrastructure Server</strong>的缩写'
					}
				]
			},
			footer: {
				copyright: '&copy; 版权所有 2014. NEM 社区客户端。'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: '大约 {{1}} 天前',
			lastAccessJustNow: '就现在',
			lastAccessTooltip: '上次访问是 {{1}}',
			primary: '主账户',
			primaryShort: 'P',
			noLabel: '<No label>',
			copiedToClipboard: '地址已复制到剪贴板!',
			actions: {
				refreshInfo: '刷新信息',
				bootLocalNode: '启动本地节点',
				changeWalletName: '改钱包名称',
				changeWalletPassword: '改钱包密码',
				mergeWallets: '合并钱包',
				exportWallet: '输出钱包',
				createAccount: '创建新帐号',
				addAccount: '添加一个现有帐户',
				changeAccountLabel: '更改帐户标识',
				setPrimary: '设为主帐户',
				removeAccount: '删除帐户',
				clientInfo: '客户信息',
				closeWallet: '关闭钱包',
				closeProgram: '关闭程序',
				copyClipboard: 'Copy address to clipboard'
			},
			nav: [
				'仪表板',
				'消息',
				'联系人',
				'交易',
				'收获的矿块',
				'资产交换',
				'新闻',
				'应用',
				'帐户',
				'设置',
				'关闭程序'
			],
			bootNodeWarning: '一个本地节点需要启动后才能充分利用NCC的功能。'
		},
		dashboard: {
			assets: {
				title: '你的资产'
			},
			importance: {
				title: '重要分',
				unknown: '未知状态',
				start: '开始收割',
				harvesting: '正在收割',
				stop: '停止收割',
				description: 'NEM云帐户的重要'
			},
			transactions: {
				title: '最近的交易',
				sendNem: '发送NEM',
				balance: '目前余额',
				syncStatus: '(矿块{{1}}{{#2}} : 估计{{3}}落后{{/2}}天)',
				columns: [
					'',
					'时间',
					'发送人/收件人',
					'留言',
					'',
					'细名',
					'确认',
					'费',
					'金额'
				],
				types: {
					pending: '交易待处理中',
					outgoing: '交易发送中',
					incoming: '交易接收中',
					self: '自我交易',
				},
				noMessage: '无信息',
				encrypted: '消息被加密',
				view: '查看',
				pending: '待定',
				seeAll: '查看所有交易',
				noTransactions: '无交易'
			},
			nemValue: {
				title: 'NEM 值统计'
			},
			messages: {
				titleTooltip: '留言'
			},
			news: {
				titleTooltip: '新闻'
			},
			notAvailable: 'alpha版本尚未公布'
		},
		transactions: {
			title: '交易',
			sendNem: '发送 NEM',
			balance: '目前余额',
			filters: {
				all: '所有交易',
				pending: '待定',
				incoming: '接受中',
				outgoing: '发送中',
			},
			table: {
				columns: [
					'',
					'时间',
					'发送人/接收人',
					'留言',
					'',
					'细节',
					'确认',
					'费',
					'金额'
				],
				types: {
					pending: '交易待处理中',
					outgoing: '交易发送中',
					incoming: '交易接收中',
					self: '自我交易',
				},
				noMessage: '无留言',
				encrypted: '消息被加密',
				view: '查看',
				pending: '待定中',
				noTransactions: '无交易',
				loading: '载入更多的交易...'
			}
		},
		harvestedBlocks: {
			title: '收获矿块',
			feeEarned: '从上25块收获的报酬',
			table: {
				columns: [
					'高',
					'时间',
					'矿块列散值',
					'费'
				],
				noBlocks: '无收获矿块',
				loadMore: '查看到旧的收获的矿块'
			},
			harvesting: {
				unknown: '未知状态',
				start: '开始收割',
				harvesting: '正在收割',
				stop: '停止收割'
			}
		},
		settings: {
			title: '设置',
			settings: [
				{
					name: '语言'
				}
			],
			save: '保存更改',
			saveSuccess: '设置已保存成功'
		}
	}
});