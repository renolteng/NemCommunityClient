define({
	id: 'en',
	name: 'English',
	texts: {
		faults: {
            101: 'File not found.',
            102: 'Wallet has not been created.',
            103: 'Wallet file is corrupted. Please recover your wallet from a back-up you should have taken when you created the wallet or added an account to it.',
            104: 'The provided password is not correct. Hopefully you can remember the correct password. The password cannot be recovered if lost!',
            106: 'Before you can work with a wallet, it has to be opened. To ensure that you are eligible for accessing the wallet, you have to provide the password for that wallet.',
            107: 'Wallet does not contain this account.',
            108: 'The account cannot be removed. Most likely because the account still has a balance greater than 0 NEMs or the account you are trying to remove is the primary account.',
            109: 'Another wallet with the same name exists already. Please choose an other wallet name.',
            110: 'Wallet already contains this account.',
            202: 'An encrypted message cannot be sent because the recipient has never made a transaction before.',
            305: 'NEM Infrastructure Server is not available.',
            306: 'An error occurred that the development team did not have foreseen. Apologies for this, maybe a retry might help. Otherwise, please open up an issue within the NEM NIS/NCC community.',
            400: 'Some parameter is missing or invalid.',
            404: 'The requested resource could not be found.',
            500: 'An error occurred that the development team did not have foreseen. Apologies for this, maybe a retry might help. Otherwise, please open up an issue within the NEM NIS/NCC community.',
            600: 'NCC requires NIS server to be booted for sending and receiving transactions from the NEM cloud. Please use the NCC menu entry for booting the local node.',
            601: 'The nis node is already booted. A second attempt to boot nis is not possible.',
            700: 'The provided account does not satisfy the basic criteria for harvesting. Mainly it is related to the amount of NEMs within the account. Harvesting starts with at least 1000 NEM.',
            701: 'The provided deadline is in the past. The deadline must be provided within a 1 day period.',
            702: 'The provided deadline is too far in the future. The deadline must be within one day time period.',
            703: 'Your account does not have the right balance to send the provided amount of NEMs.',
            704: 'The provided message text is too large to be send via NEM. Please try to reduce the length of the message you want to send.',
            705: 'The transaction hash already exists in the database or the list of unconfirmed transactions.',
            706: 'The signature of the transaction could not be verified.',
            707: 'The time stamp of the transaction id too far in the past.',
            708: 'The time stamp of the transaction is too far in the future.',
            709: 'The account is unknown. An account needs to be part of at least one transaction (sender or recipient) to be known to the network.',
            901: 'There was an error setting up offline mode.'
        },
        common: {
        	success: 'Success', //title of the Success message modals
        	nisStatus: {
        		nccUnavailable: 'NCC is not available',
        		unavailable: 'NIS is not available',
        		notBooted: 'NIS requires to be booted. Please open your wallet and boot a local node via the popup dialog.',
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
				caption: 'ERROR {{1}}'
			},
			confirmDefault: {
				yes: 'Yes',
				no: 'No'
			},
			sendNem: {
				title: 'Send NEM',
				labelDesc: 'This account is labeled as <strong>{{1}}</strong>',
				nullLabelDesc: "This account doesn't have a label",
				amount: 'Amount',
				recipient: "Recipient's account",
				message: 'Message',
				encrypt: 'Encrypt message',
				fee: 'Fee',
				dueBy: 'Due by',
				resetFee: 'Reset to minimum fee',
				hours: 'hours',
				password: 'Password',
				send: 'Send',
				sending: 'Sending...',
				successMessage: 'Transaction has been sent successfully!'
			},
			clientInfo: {
				title: 'Client info',
				ncc: 'NEM Community Client - NCC',
				signer: 'Signer',
				remoteServer: 'Remote Server',
				local: 'Local',
				nis: 'NEM Infrastructure Server - NIS',
				sync: 'Synchronized',
				notSync: 'Not synchronized',
				notConnected: 'Not connected to NEM Cloud',
				loading: 'Loading...'
			},
			transactionDetails: {
				title: 'Transaction Details',
				// this might be block or transaction ID
				id: 'ID',
				// this might be block or transaction Hash
				hash: 'Hash',
				type: 'Transaction Type',
				pending: 'Pending',
				outgoing: 'Outgoing',
				incoming: 'Incoming',
				self: 'Self',
				sender: 'Sender',
				recipient: 'Recipient',
				message: 'Message',
				noMessage: 'No message',
				encrypted: 'Message is encrypted',
				time: 'Timestamp',
				confirmations: 'Confirmations',
				amount: 'Amount',
				fee: 'Fee'
			},
			bootLocalNode: {
				title: 'Boot local node',
				account: 'Account to boot local node',
				noLabel: '&lt;No label&gt;',
				wallet: 'Wallet',
				node: 'Node name',
				boot: 'Boot',
				booting: 'Booting...'
			},
			notBootedWarning: {
				title: 'Node has not been booted!',
				message: 'A local node needs to be booted before you can send NEM!'
			},
			closeWallet: {
				title: 'Close wallet',
				message: 'Are you sure you want to close your wallet and return to landing page?'
			},
			createAccount: {
				title: 'Create new account',
				label: 'Private label',
				wallet: 'Wallet',
				password: "Wallet's password",
				successMessage: 'Account {{1}} {{#2}}({{2}}){{/2}} has been created!',
				create: 'Create'
			},
			addAccount: {
				title: 'Add an Existing Account',
				privateKey: "Account's Private Key",
				wallet: 'Wallet',
				password: "Wallet's password",
				successMessage: 'Account {{1}} {{#2}}({{2}}){{/2}} has been added to wallet!',
				add: 'Add',
				label: 'Label'
			},
			setPrimary: {
				title: 'Set primary account',
				account: 'Account to be set as Primary',
				noLabel: '&lt;No label&gt;',
				wallet: 'Wallet',
				password: "Wallet's password",
				successMessage: 'Account {{1}} {{#2}}({{2}}){{/2}} has been set as primary!',
				set: 'Set as primary',
			},
			changeWalletName: {
				title: 'Change wallet name',
				wallet: 'Current wallet name',
				newName: 'New wallet name',
				password: "Wallet's password",
				successMessage: 'Wallet name has been successfully changed from <em>{{1}}</em> to <em>{{2}}</em>',
				change: 'Change'
			},
			changeWalletPassword: {
				title: 'Change wallet password',
				wallet: 'Wallet',
				password: 'Current password',
				newPassword: 'New password',
				confirmPassword: 'Confirm new password',
				successMessage: 'Wallet password has been successfully changed',
				change: 'Change',
				passwordNotMatchTitle: 'Oops!',
				passwordNotMatchMessage: 'Your entered password and password confirmation do not match. Please be sure you type your new password correctly.'
			},
			changeAccountLabel: {
				title: 'Change account label',
				label: 'Account label',
				wallet: 'Wallet',
				password: "Wallet's password",
				successMessage: 'Account {{1}} is now labeled as {{2}}',
				change: 'Change'
			},
			removeAccount: {
				title: 'Remove account',
				wallet: 'Wallet',
				password: "Wallet's password",
				warning: 'Please ensure that your account has no NEMs left before you remove it, or they would be lost forever.',
				successMessage: 'Account {{1}} {{#2}}({{2}}){{/2}} has been removed!',
				remove: 'Remove'
			},
			nisUnavailable: {
				title: 'NIS unavailable',
				message: 'Disconnected from NIS, waiting for connection'
			},
			shutdown: {
				title: 'Close program',
				message: 'Are you sure you want to close NEM Community Client?'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'Wallet has been sucessfully imported!',
			nav: {
				start: 'Getting Started',
				about: 'About NEM',
				help: 'Help'
			},
			main: {
				leftTitle: 'New to <em>NEM</em>?',
				leftButton: 'Create new wallet',
				walletNamePlh: 'Name of your wallet',
				passwordPlh: 'Password',
				create: 'Create',
				rightTitle: 'Already a <em>NEM</em>ber?',
				rightButton: 'Open your wallet',
				openButton: 'Open',
				walletsFound: 'Found <strong>{{1}}</strong> <em>wallets</em>',
				copyright: 'Photography by <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC encrypts your wallet',
						description: '<em>Security</em> is very important for NEM to avoid theft of NEM coins &amp; assets.'
					},
					{
						title: 'NCC encrypts your wallet',
						description: '<em>Security</em> is very important for NEM to avoid theft of NEM coins &amp; assets.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'How NCC works?',
						paragraphs: [
							'<strong>NCC</strong> provides an access to your assets and NEMs like a traditional wallet does. You may',
							'<strong>NCC</strong> requires access to an <strong>NIS</strong> server in order to operate. Standard is to have a local server active (is installed together with the <strong>NCC</strong>)',
							'You may also configure an access to a remote <strong>NIS</strong>.'
						],
						listItems: [
							'Have multiple wallets',
							'Define multiple accounts to be included in a wallet'
						]
					},
					{
						title: 'What is &#42;NIS?',
						paragraphs: [
							'This component is responsible for keeping the <strong>NEM</strong> cloud alive.',
							'The more <strong>NIS</strong> the better the security.',
							'<strong>NIS</strong> is the access point into the <strong>NEM</strong> cloud.'
						],
						legend: '<strong>&#42;NIS</strong> stands for <strong>NEM Infrastructure Server</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2014. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: 'About {{1}} days ago',
			lastAccessJustNow: 'Just now',
			lastAccessTooltip: 'Last access was {{1}}',
			primary: 'primary',
			primaryShort: 'P',
			noLabel: '<No label>',
			copiedToClipboard: 'Address has been copied to clipboard!',
			actions: {
				refreshInfo: 'Refresh Info',
				bootLocalNode: 'Boot Local Node',
				changeWalletName: 'Change Wallet Name',
				changeWalletPassword: 'Change Wallet Password',
				mergeWallets: 'Merge Wallets',
				exportWallet: 'Export Wallet',
				createAccount: 'Create new Account',
				addAccount: 'Add an Existing Account',
				changeAccountLabel: 'Change Account Label',
				setPrimary: 'Set as Primary Account',
				removeAccount: 'Remove Account',
				clientInfo: 'Client Info',
				closeWallet: 'Close Wallet',
				closeProgram: 'Close Program',
				copyClipboard: 'Copy address to clipboard'
			},
			nav: [
				'Dashboard',
				'Messages',
				'Contacts',
				'Transactions',
				'Harvested blocks',
				'Asset Exchange',
				'News',
				'Applications',
				'Accounts',
				'Settings',
				'Close Program'
			],
			bootNodeWarning: "A local node needs to be booted before you can fully utilize NCC's features."
		},
		dashboard: {
			assets: {
				title: 'Your assets'
			},
			importance: {
				title: 'Importance score',
				unknown: 'Unknown status',
				start: 'Start harvesting',
				harvesting: 'Harvesting',
				stop: 'Stop harvesting',
				description: 'importance of account to the NEM cloud'
			},
			transactions: {
				title: 'Recent Transactions',
				sendNem: 'Send NEM',
				balance: 'Current balance',
				syncStatus: '(at block {{1}}{{#2}} : est. {{3}} days behind{{/2}})',
				unknown: 'unknown',
				columns: [
					'',
					'Time',
					'Sender/Recipient',
					'Message',
					'',
					'Details',
					'Confirmations',
					'Fee',
					'Amount'
				],
				types: {
					pending: 'Pending transaction',
					outgoing: 'Outgoing transaction',
					incoming: 'Incoming transaction',
					self: 'Self transaction',
				},
				noMessage: 'No message',
				encrypted: 'Message is encrypted',
				view: 'View',
				pending: 'Pending',
				seeAll: 'See all transactions',
				noTransactions: 'No transactions have been performed yet'
			},
			nemValue: {
				title: 'NEM value statistics'
			},
			messages: {
				titleTooltip: 'Messages'
			},
			news: {
				titleTooltip: 'News'
			},
			notAvailable: 'Not yet available in alpha release'
		},
		transactions: {
			title: 'Transactions',
			sendNem: 'Send NEM',
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
					'Time',
					'Sender/Recipient',
					'Message',
					'',
					'Details',
					'Confirmations',
					'Fee',
					'Amount'
				],
				types: {
					pending: 'Pending transaction',
					outgoing: 'Outgoing transaction',
					incoming: 'Incoming transaction',
					self: 'Self transaction',
				},
				noMessage: 'No message',
				encrypted: 'Message is encrypted',
				view: 'View',
				pending: 'Pending',
				noTransactions: 'No transactions have been performed yet',
				loading: 'Loading more transactions...'
			}
		},
		harvestedBlocks: {
			title: 'Harvested Blocks',
			feeEarned: 'Fees earned from the last 25 harvested blocks',
			table: {
				columns: [
					'Height',
					'Time',
					'Block hash',
					'Fee'
				],
				noBlocks: 'No harvested blocks ',
				loadMore: 'See older harvested blocks'
			},
			harvesting: {
				unknown: 'Unknown status',
				start: 'Start harvesting',
				harvesting: 'Harvesting',
				stop: 'Stop harvesting'
			}
		},
		settings: {
			title: 'Settings',
			settings: [
				{
					name: 'Language'
				}
			],
			save: 'Save changes',
			saveSuccess: 'Settings have been saved successfully'
		}
	}
});