define({
	id: 'jp',
	name: '日本語',
	texts: {
		preferences: {
			thousandSeparator: '\u2009',
			decimalSeparator: '.'
		},
		faults: {
            101: 'ファイルが見つかりませんでした。',
            102: 'ウォレットが作成できませんでした。',
            103: 'ウォレットのデータが破損しています。バックアップファイルから復元してください。',
            104: 'パスワードが正しくありません。パスワードを忘れてしまった場合、復元することは不可能です。',
            106: 'ウォレットを開く前に、ウォレットのロックを解いてください. ウォレットにパスワードを入力することでアクセスすることができます。',
            107: 'ウォレットにはこのアカウントはありません。',
            108: 'このアカウントは削除することができません。おそらく、アカウント残高がゼロではないか、そのアカウントがプライマリーアカウント（削除不可なアカウント）だからです。',
            109: '同名のウォレットがすでに存在します。他の名前にしてください。',
            110: 'ウォレットにはこのアカウントはもうすでに存在しています。',
            202: '相手が送金及びメッセージを送信されていない為、このメッセージを暗号化して送れない。',
            305: 'NEM Infrastructure Serverは無効。',
            306: '開発者が考えられなかったエラーが発生しまして、申し訳ありません。もう一度実行して、問題が再び発生したら場合、NIS/NCCのサイトにエラーリポートを書いてください。',
            400: 'パラメーターがないか間違っている。',
            404: 'リクエストされたリソースが見つかれなかった。',
            500: 'Failed to save configuration file.',
            600: 'NCCはNISサーバーがNEMクラウドからトランザクション(取引)履歴を送受信するために起動しておく必要があります。ローカルノードを起動するためにNCCメニューエントリを使用してください。',
            601: 'NISノードはすでに起動しています。NISは複数起動することができません。',
            700: '入力されたアカウントがハーベストのための基準を満たしていません。その基準はそのアカウントにあるNEMの量が関係しています。ハーベストは少なくとも1000NEMから始めることができます。',
            701: '提供期限が過ぎています。期限は1日の間です。',
            702: '提供期限はまだ先です。期間は1日の間です。',
            703: '送金しようとしているNEMがあなたの残高を超えています。送金する金額を減らしてください。',
            704: '入力されたメッセージテキストは、NEMを経由して送信するには長すぎます。メッセージを短くしてください。',
            705: 'その取引のハッシュはすでにデータベースの未検証取引の中に存在しています。',
            706: 'トランザクション(取引)の電子署名の正当性を確認できませんでした。',
            707: 'トランザクション(取引)IDのタイムスタンプが古すすぎます。',
            708: 'トランザクション(取引)のタイムスタンプが先すぎます',
            709: '不明なアカウントです。アカウントはNEMクラウド内で共有されている少なくとも1つのトランザクション(取引)に記載されている必要がある。(送信側、受信側どちらでも良い)',
            901: 'オフラインモードに変換する際にエラーが発生しました。'
        },
        common: {
        	success: '成功!', //title of the Success message modals
        	nisStatus: {
        		nccUnavailable: 'NCC is not available',
         		unavailable: 'NISは無効',
         		notBooted: 'NISは必要で、ウォレットを開いて、ローカルノードを起動してください。',
         		retrievingStatus: 'Retrieving NIS status...',
         		synchronizing: 'NISはブロック{{1}}を同期中で、約{{2}}個ブロックを遅れています。',
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
				title: 'おっと…',
				caption: 'エラー{{1}}'
			},
			confirmDefault: {
				yes: 'はい',
				no: 'いいえ'
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
				title: 'NEMを送信する',
				labelDesc: 'このアカウントのラベルは {{1}}',
				nullLabelDesc: "このアカウントにラベルはありません。",
				amount: '金額',
				recipient: "相手のアカウント",
				message: 'メッセージ',
				encrypt: 'メッセージを暗号化する。',
				fee: '手数料',
				dueBy: '期限',
				resetFee: '最低取引手数料に戻す',
				hours: '時間単位',
				password: 'パスワード',
				send: '送る',
				sending: '送信中…',
				successMessage: '送信に成功しました!!'
			},
			clientInfo: {
				title: 'クライアント情報',
				ncc: 'NEM Community Client - NCC',
				signer: '著名者',
				remoteServer: 'リモートサーバー',
				local: 'ローカル',
				nis: 'NEM Infrastructure Server - NIS',
				sync: '同期完了',
				notSync: '同期されていません',
				notConnected: 'NEMクラウドに繋がっていません',
				loading: '読み込み中…'
			},
			transactionDetails: {
				title: 'トランザクション(取引)の詳細',
				// this might be block or transaction ID
				id: 'ID',
				// this might be block or transaction Hash
				hash: 'ハッシュ',
				type: 'トランザクションのタイプ',
				pending: '処理中',
				outgoing: '送金',
				incoming: '入金',
				self: '自分',
				sender: '送信元',
				recipient: '受取先',
				message: 'メッセージ',
				noMessage: 'メッセージなし',
				encrypted: '暗号化されたメッセージ',
				time: 'タイムスタンプ',
				confirmations: '承認数',
				amount: '量',
				fee: '手数料'
			},
			bootLocalNode: {
				title: 'ローカルノードを起動する',
				account: 'ローカルノードを起動するためのアカウント',
				noLabel: '<span class="null">&lt;ラベルなし&gt;</span>',
				wallet: 'ウォレット',
				node: 'ノード名前',
				boot: '起動',
				booting: '起動中…'
			},
			notBootedWarning: {
				title: 'ノードが起動していません!',
				message: 'NEMを送信する前に、ローカルノードを起動する必要があります。'
			},
			closeWallet: {
				title: 'ウォレットを閉じます',
				message: 'ウォレットを終了して、最初のページに戻りますがよろしいですか？'
			},
			createAccount: {
				title: '新しいアカウントを作成する',
				label: 'プライベートラベル',
				wallet: 'ウォレット',
				password: "ウォレットのパスワード",
				successMessage: 'アカウント: {{1}} {{#2}}({{2}}){{/2}} が作成されました。',
				create: '作成'
			},
			addAccount: {
				title: '既存のアカウントを追加します。',
				privateKey: "アカウントのプライベートキー",
				wallet: 'ウォレット',
				password: "ウォレットパスワード",
				successMessage: 'アカウント: {{1}} {{#2}}({{2}}){{/2}} はウォレットに追加されました!',
				add: '追加',
				label: 'プライベートラベル'
			},
			setPrimary: {
				title: 'プライマリーアカウントを設定',
				account: 'プライマリーとして設定するアカウント',
				noLabel: '<span class="null">&lt;ラベルなし&gt;</span>',
				wallet: 'ウォレット',
				password: "ウォレットのパスワード",
				successMessage: 'アカウント: {{1}} {{#2}}({{2}}){{/2}} はプライマリーに設定されました!',
				set: 'プライマリに設定',
			},
			changeWalletName: {
				title: 'ウォレットの名前の変更する',
				wallet: '現在のウォレットの名前',
				newName: '新しいウォレットの名前',
				password: "ウォレットのパスワード",
				successMessage: 'ウォレットの名前が <em>{{1}}</em> から <em>{{2}}</em> に変更されました。',
				change: '変更'
			},
			changeWalletPassword: {
				title: 'ウォレットパスワードを変更する',
				wallet: 'ウォレット',
				password: '現在のパスワード',
				newPassword: '新しいパスワード',
				confirmPassword: '新しいパスワードの再入力',
				successMessage: 'ウォレットパスワードを変更しました。',
				change: '変更',
				passwordNotMatchTitle: 'おっと…',
				passwordNotMatchMessage: 'あなたが入力したパスワードが一致しません。 パスワードを再入力してください。'
			},
			changeAccountLabel: {
				title: 'アカウントのラベルを変更する',
				label: 'アカウントラベル',
				wallet: 'ウォレット',
				password: "ウォレットのパスワード",
				successMessage: 'アカウント: {{1}} は {{2}} に変更されました。',
				change: '変更'
			},
			removeAccount: {
				title: 'アカウントを削除',
				wallet: 'ウォレット',
				password: "ウォレットのパスワード",
				warning: 'アカウントを削除する前にNEMが残っていないか確認してください。削除が完了したら、一旦削除したNEMは復元できません。',
				successMessage: 'アカウント: {{1}} {{#2}}({{2}}){{/2}} は削除されました。',
				remove: '削除'
			},
			nisUnavailable: {
				title: 'NISを使用できません。',
				message: 'NISから切断されました。接続を待っています。'
			},
			shutdown: {
				title: 'ウォレットを終了',
				message: 'NEM Community Clientを閉じますか。'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'ウォレットのインポートに成功しました。',
			nav: {
				start: '始め方',
				about: 'NEMについて',
				settings: 'Settings'
			},
			main: {
				leftTitle: '<em>NEM</em>は初めてですか?',
				leftButton: '新しいウォレットを作成する',
				walletNamePlh: 'あなたのウォレットの名前',
				passwordPlh: 'パスワード',
				create: '作成',
				rightTitle: 'すでに<em>NEM</em>のアカウントを持っていますか?',
				rightButton: 'ウォレットを開く',
				openButton: '開く',
				walletsFound: ' <em>ウォレット:</em><strong>{{1}}</strong> が見つかりました。',
				copyright: '撮影者：<em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'あなたのウォレットをNCC暗号化する。',
						description: '<em>セキュリティ</em> はあなたのNEMcoinの資産を盗難から守る上で非常に&amp;重要です。 '
					},
					{
						title: '',
						description: '<em>セキュリティ</em> はあなたのNEMcoinの資産を盗難から守る上で非常に&amp;重要です。'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'どの様にNCCは動いている?',
						paragraphs: [
							'<strong>NCC</strong> では一般的なウォレットでのNEMやその他の財産へのアクセスができるようになります。',
							'<strong>NCC</strong> サーバーは動作するために <strong>NIS</strong> サーバーへの接続を必要とします。標準ではローカルサーバーを起動します。 (ローカルサーバーは<strong>NCC</strong>と一緒にインストールされています。)',
							'また、遠隔で<strong>NIS</strong>へのアクセスを作成することもできます。.'
						],
						listItems: [
							'複数のウォレットを持っている',
							'複数のウォレットのアカウントを決める'
						]
					},
					{
						title: '&#42;NISとは?',
						paragraphs: [
							'このコンポーネント(NEMの部品の1つ) は<strong>NEM</strong> クラウドを維持するものです。',
							' より強い<strong>NIS</strong> はより強力なセキュリティとなります。',
							'<strong>NIS</strong> は <strong>NEM</strong> クラウドにアクセスする際の中継所となります。'
						],
						legend: '<strong>&#42;NIS</strong> は <strong>NEM のインフラとして機能しています。</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2014. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: '約 {{1}} 日前',
			lastAccessJustNow: '現在',
			lastAccessTooltip: '最後のアクセスは {{1}}',
			primary: '主なウォレット',
			primaryShort: '主',
			noLabel: '<ラベルなし>',
			copiedToClipboard: 'アドレスがクリップボードにコピーされました。',
			actions: {
				refreshInfo: '更新する',
				bootLocalNode: 'ローカルノードを起動する',
				changeWalletName: 'ウォレットの名前を変更する',
				changeWalletPassword: 'ウォレットパスワードを変更する',
				mergeWallets: 'ウォレットを統合する',
				exportWallet: 'ウォレットをバックアップする',
				createAccount: '新しいアカウントを作成する',
				addAccount: '既存のアカウントを追加する',
				changeAccountLabel: 'アカウントラベルを変更する',
				setPrimary: 'プライマリーアカウントに設定する',
				removeAccount: 'アカウントを削除する',
				clientInfo: 'クライアント情報',
				closeWallet: 'ウォレットを閉じる',
				closeProgram: '終了する',
				copyClipboard: 'Copy address to clipboard'
			},
			nav: [
				'ダッシュボード',
				'メッセージ',
				'連絡先',
				'トランザクション(取引)',
				'ハーベストされたブロック',
				'残高のやりとり',
				'News',
				'アプリケーション',
				'アカウント',
				'設定',
				'終了する'
			],
			bootNodeWarning: "NCCの全ての機能を利用するためには、ローカルノードを起動する必要があります。"
		},
		dashboard: {
			assets: {
				title: 'あなたの残高'
			},
			importance: {
				title: '重要なスコア',
				unknown: '不明な状態',
				start: 'ハーベスティングを開始する',
				harvesting: 'ハーベスティング中…',
				stop: 'ハーベスティングを中断する',
				description: 'NEMクラウドのアカウントの重要性'
			},
			transactions: {
				title: '直近の取引',
				sendNem: 'NEMの送金',
				balance: '現在の残高',
				syncStatus: '(現在同期しているブロック: {{1}}{{#2}} 推定で {{3}} 日前の取引{{/2}})',
				unknown: 'unknown',
				columns: [
					'',
					'時間',
					'送信者/受信者',
					'メッセージ',
					'',
					'詳細',
					'承認数',
					'手数料',
					'残高'
				],
				types: {
					pending: '未処理(未検証)のトランザクション(取引)',
					outgoing: '出金のトランザクション(取引)',
					incoming: '入金のトランザクション(取引)',
					self: 'セルフトランザクション',
				},
				noMessage: 'メッセージはありません',
				encrypted: 'メッセージは暗号化されています。',
				view: '表示',
				pending: '未処理（未検証）',
				seeAll: 'すべてのトランザクション(取引)を見る',
				noTransactions: 'まだ取引は行われていません。'
			},
			nemValue: {
				title: 'NEMの統計'
			},
			messages: {
				titleTooltip: 'メッセージ'
			},
			news: {
				titleTooltip: 'ニュース'
			},
			notAvailable: 'アルファ版ではまだ利用できません。'
		},
		transactions: {
			title: '取引',
			sendNem: 'NEMを送金する',
			balance: '現在の残高',
			filters: {
				confirmed: 'Confirmed',
				unconfirmed: 'Unconfirmed',
				incoming: '入金',
				outgoing: '出金',
			},
			table: {
				columns: [
					'',
					'時間',
					'送信者/受信者',
					'メッセージ',
					'',
					'詳細',
					'承認数',
					'手数料',
					'残高'
				],
				types: {
					pending: '未処理（未検証）の取引',
					outgoing: '出金',
					incoming: '入金',
					self: 'セルフトランザクション',
				},
				noMessage: 'メッセージなし',
				encrypted: 'メッセージを暗号させた',
				view: '提示',
				pending: '未定',
				noTransactions: 'まだ取引は行われていません。',
				loading: '過去のトランザクションを見る'
			}
		},
		harvestedBlocks: {
			title: 'ハーベストされたブロック',
			feeEarned: '現在のブロックから25ブロック前から発生する収入',
			table: {
				columns: [
					'ブロック番号',
					'時間',
					'ブロックハッシュ',
					'手数料'
				],
				noBlocks: 'ハーベストされたブロックではありません。 ',
				loadMore: '過去のハーベストされたブロックを見る'
			},
			harvesting: {
				unknown: '不明な状態',
				start: 'ハーベスティングを開始する',
				harvesting: 'ハーベスティング',
				stop: 'ハーベスティングを停止する'
			}
		},
		settings: {
			title: '設定',
			settings: [
				{
					name: '言語'
				}
			],
			save: '変更を保存する',
			saveSuccess: '変更の保存に成功しました'
		}
	}
});