define({
	id: "rr",
	name: "Arrr!",
	texts: {
		preferences: {
			thousandSeparator: '\u2009',
			decimalSeparator: '.'
		},
		faults: {
            101: "File not found.",
            102: "Wallet has not been created.",
            103: "Wallet file is corrupted. Please recover your wallet from a back-up you should have taken when you created the wallet or added an account to it.",
            104: "Coffer's pick ain't right. Ye surely have tatooed it somewhere. Unleash the Krakken otherwise!",
            106: "Before you can work with a wallet, it has to be opened. To ensure that you are eligible for accessing the wallet, you have to provide the password for that wallet.",
            107: "Wallet does not contain this account.",
            108: "The account cannot be removed. Most likely because the account still has a balance greater than 0 NEMs or the account you are trying to remove is the primary account.",
            109: "Another wallet with the same name exists already. Please choose an other wallet name.",
            110: "Wallet already contains this account.",
            202: "An encrypted message cannot be sent because the recipient has never made a transaction before.",
            305: "NEM Infrastructure Server is not available.",
            306: "An error occurred that the development team did not have foreseen. Apologies for this, maybe a retry might help. Otherwise, please open up an issue within the NEM NIS/NCC community.",
            400: "Some parameter is missing or invalid.",
            404: "The requested resource could not be found.",
            500: "An error occurred that the development team did not have foreseen. Apologies for this, maybe a retry might help. Otherwise, please open up an issue within the NEM NIS/NCC community.",
            600: "NCC requires NIS server to be booted for sending and receiving transactions from the NEM cloud. Please use the NCC menu entry for booting the local node.",
            601: "The nis node is already booted. A second attempt to boot nis is not possible.",
            700: "The provided account does not satisfy the basic criteria for harvesting. Mainly it is related to the amount of NEMs within the account. Harvesting starts with at least 1000 NEM.",
            701: "The provided deadline is in the past. The deadline must be provided within a 1 day period.",
            702: "The provided deadline is too far in the future. The deadline must be within one day time period.",
            703: "Your account does not have the right balance to send the provided amount of NEMs.",
            704: "The provided message text is too large to be send via NEM. Please try to reduce the length of the message you want to send.",
            705: "The transaction hash already exists in the database or the list of unconfirmed transactions.",
            706: "The signature of the transaction could not be verified.",
            707: "The time stamp of the transaction id too far in the past.",
            708: "The time stamp of the transaction is too far in the future.",
            709: "The account is unknown. An account needs to be part of at least one transaction (sender or recipient) to be known to the network.",
            901: "There was an error setting up offline mode."
        },
        common: {
        	success: "Aye!", //title of the Success message modals
        	nisStatus: {
        		nccUnavailable: 'Ship not in range',
        		unavailable: "Ship not in range",
        		booting: 'Setting sails',
        		notBooted: "Ship still in port. Pick yer coffer and set the sails.",
        		retrievingStatus: 'Checking sails',
        		synchronizing: "Setting sails. At block {{1}}, est. {{2}} behind.",
        		daysBehind: {
        			0: 'less than 1 day',
        			1: '1 day',
        			many: '{{1}} days'
        		},
        		synchronized: 'Beauty in port!'
        	}
        },
		modals: {
			error: {
				title: "Blimey!",
				caption: "Shiver me timbers {{1}}"
			},
			confirmDefault: {
				yes: "Aye",
				no: "Avast"
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
				title: "Send booty",
				labelDesc: "This account is labeled as <strong>{{1}}</strong>",
				nullLabelDesc: "This account doesn't have a label",
				amount: "Loot",
				recipient: "Me hearties",
				message: "Parley",
				encrypt: "Message in a bottle",
				fee: "Bounty",
				dueBy: "Due by",
				resetFee: "Reset to minimum fee",
				hours: "hours",
				password: "Coffer's pick",
				send: "Deliver",
				sending: "Delivering...",
				successMessage: "Yer loot delivered!"
			},
			clientInfo: {
				title: "Ship's particulars",
				ncc: "NEM Community Client - NCC",
				signer: "Me hearties",
				remoteServer: "Remote Server",
				local: "Local",
				nis: "NEM Infrastructure Server - NIS",
				sync: "Sails set",
				notSync: "Sails ain't set yet",
				notConnected: "No one in crow's nest",
				loading: "Loading..."
			},
			transactionDetails: {
				title: "Loot Details",
				// this might be block or transaction ID
				id: "ID",
				// this might be block or transaction Hash
				hash: "Hash",
				type: "Loot Type",
				pending: "Queued",
				outgoing: "Delivered",
				incoming: "Hail-shot",
				self: "Buried",
				sender: "Hearties",
				recipient: "Me hearties",
				message: "Parley",
				noMessage: "No parley",
				encrypted: "Message is in bottle",
				time: "Watch glass",
				confirmations: "Sanctions",
				amount: "Loot",
				fee: "Bounty"
			},
			bootLocalNode: {
				title: "Set sail",
				account: "Fleet to set sail",
				noLabel: "<span class='null'>&lt;No label&gt;</span>",
				wallet: "Coffer",
				node: "Ship's name",
				boot: "Row",
				booting: "Settig sails..."
			},
			notBootedWarning: {
				title: "Ship still in port!",
				message: "Hoist yer rigging to deliver ye Gold!"
			},
			closeWallet: {
				title: "Close the coffer",
				message: "Be ye sure ye want to close ye coffer 'n return to port"
			},
			createAccount: {
				title: "New log",
				label: "Private label",
				wallet: "Coffer",
				password: "Coffer's pick",
				successMessage: "Log {{1}} {{#2}}({{2}}){{/2}} has been obtained!",
				create: "Create"
			},
			addAccount: {
				title: "Add an Existing Log",
				privateKey: "Log's Private Key",
				wallet: "Coffer",
				password: "Coffer's pick",
				successMessage: "Log {{1}} {{#2}}({{2}}){{/2}} has been put in the coffer!",
				add: "Put",
				label: "Label"
			},
			setPrimary: {
				title: "Set primary log",
				account: "Log to be set as Primary",
				noLabel: "<span class='null'>&lt;No label&gt;</span>",
				wallet: "Coffer",
				password: "Coffer's pick",
				successMessage: "Log {{1}} {{#2}}({{2}}){{/2}} has been set as primary!",
				set: "Set as primary",
			},
			changeWalletName: {
				title: "Name yer coffer",
				wallet: "Current coffer's name",
				newName: "Decent coffer's name",
				password: "Coffer's password",
				successMessage: "Coffer <em>{{1}}</em> ain't no more, time for <em>{{2}}</em>",
				change: "Aye"
			},
			changeWalletPassword: {
				title: "Change coffer's pick",
				wallet: "Coffer",
				password: "Current pick",
				newPassword: "Craft new pick",
				confirmPassword: "Check the pick",
				successMessage: "Coffer's pick has been replaced",
				change: "Replace",
				passwordNotMatchTitle: "Blimey!",
				passwordNotMatchMessage: "Yer check does not fit yer pick. Call yer locksmith."
			},
			changeAccountLabel: {
				title: "Change log label",
				label: "Log label",
				wallet: "Coffer",
				password: "Coffer's pick",
				successMessage: "Log {{1}} a.k.a. {{2}}",
				change: "Imprint"
			},
			removeAccount: {
				title: "Drop log",
				wallet: "Coffer",
				password: "Coffer's pick",
				warning: "Bury yer all loot before you drop the log, or abandon ya'll hope.",
				successMessage: "Log {{1}} {{#2}}({{2}}){{/2}} has sunk in the sea!",
				remove: "Overboard"
			},
			nisUnavailable: {
				title: "Ship unseen",
				message: "Can't see ship, waitin' for it."
			},
			shutdown: {
				title: "Abandon ship",
				message: "Captain ain't do that!"
			}
		},
		landing: {
			logo: 'images/nem_logo_pirate.png',
            		importSuccess: "Coffer collected!",
			nav: {
				start: "Getting <strong>Started</strong>",
				about: "About <strong>NEM</strong>",
				settings: 'Settings'
			},
			main: {
				leftTitle: "New to <em>NEM</em>?",
				leftButton: "Craft a coffer",
				walletNamePlh: "Name yer Coffer",
				passwordPlh: "Pick",
				create: "Craft",
				rightTitle: "Already a <em>NEM</em>ber?",
				rightButton: "Open yer Coffer",
				openButton: "Pick",
				walletsFound: "Found <strong>{{1}}</strong> <em>logbooks</em>",
				copyright: "Photography by <em>Cas Cornelissen</em>"
			},
			carousel: {
				items: [
					{
						title: "NCC encrypts your wallet",
						description: "<em>Security</em> is very important for NEM to avoid theft of NEM coins &amp; assets."
					},
					{
						title: "NCC encrypts your wallet",
						description: "<em>Security</em> is very important for NEM to avoid theft of NEM coins &amp; assets."
					}
				]
			},
			about: {
				sections: [
					{
						title: "How NCC works?",
						paragraphs: [
							"<strong>NCC</strong> provides an access to your assets and NEMs like a traditional wallet does. You may",
							"<strong>NCC</strong> requires access to an <strong>NIS</strong> server in order to operate. Standard is to have a local server active (is installed together with the <strong>NCC</strong>)",
							"You may also configure an access to a remote <strong>NIS</strong>."
						],
						listItems: [
							"Have multiple wallets",
							"Define multiple accounts to be included in a wallet"
						]
					},
					{
						title: "What is &#42;NIS?",
						paragraphs: [
							"This component is responsible for keeping the <strong>NEM</strong> cloud alive.",
							"The more <strong>NIS</strong> the better the security.",
							"<strong>NIS</strong> is the access point into the <strong>NEM</strong> cloud."
						],
						legend: "<strong>&#42;NIS</strong> stands for <strong>NEM Infrastructure Server</strong>"
					}
				]
			},
			footer: {
				copyright: "&copy; Copyright 2014. NEM Community Client."
			}
		},
		wallet: {
			logo: 'images/nem_logo_pirate.png',
            		lastAccess: "{{1}} days ago",
			lastAccessJustNow: "right now",
			lastAccessTooltip: "Coffer opened {{1}}",
			primary: "primary",
			primaryShort: "P",
			noLabel: "<No label>",
			copiedToClipboard: "Me hearties been copied to clipboard!",
			actions: {
				refreshInfo: "Refresh Info",
				bootLocalNode: "Set the sails",
				changeWalletName: "Name'er coffer",
				changeWalletPassword: "Change Coffer's pick",
				mergeWallets: "Repack coffers",
				exportWallet: "Hail yer Coffer",
				createAccount: "Create new Logbook",
				addAccount: "Add an Existing Logbook",
				changeAccountLabel: "Title'er Logbook",
				setPrimary: "Set as Primary Logbook",
				removeAccount: "Drop the log",
				clientInfo: "Ship's Particulars",
				closeWallet: "Close the Coffer",
				closeProgram: "Abandon Ship",
				copyToClipboard: "Click to copy me hearties to clipboard"
			},
			nav: [
				"Port",
				"Parley",
				"Matey",
				"Loot",
				"Plunderings",
				"Trade",
				"Hail",
				"Navigator",
				"Log",
				"Fix Riggin'",
				"Abandon ship"
			],
			bootNodeWarning: "Hoist yer rigging to deliver ye Gold!"
		},
		dashboard: {
			assets: {
				title: "Yer trades"
			},
			importance: {
				title: "Attack's strength",
				unknown: "Marooned",
				start: "Begin Plundering",
				harvesting: "Plundering",
				stop: "Cease Plundering",
				description: "Ship's rank on th' seven seas"
			},
			transactions: {
				title: "Recent Loot",
				sendNem: "Send Booty",
				balance: "Pieces of Eight",
				syncStatus: "(at block {{1}}{{#2}} : est. {{3}} days behind{{/2}})",
				unknown: "unknown",
				columns: [
					"",
					"Time",
					"Hearties",
					"Parley",
					"",
					"Particulars",
					"Sanctions",
					"Bounty",
					"Loot"
				],
				types: {
					pending: "Queued loot",
					outgoing: "Delivered loot",
					incoming: "Hail-shot",
					self: "Buried loot",
				},
				noMessage: "No parley",
				encrypted: "Message is in a bottle",
				view: "Spyglass",
				pending: "Queued",
				seeAll: "Loot book",
				noTransactions: "No loot has been delivered or gathered"
			},
			nemValue: {
				title: "NEM value statistics"
			},
			messages: {
				titleTooltip: "Parley"
			},
			news: {
				titleTooltip: "Hail"
			},
			notAvailable: "Not yet available in alpha release"
		},
		transactions: {
			title: "Loot",
			sendNem: "Send booty",
			balance: "Pieces of Eight",
			filters: {
				confirmed: "Sanctioned",
				unconfirmed: "Queued",
				incoming: "Hail-shot",
				outgoing: "Delivered",
			},
			table: {
				columns: [
					"",
					"Time",
					"Hearties",
					"Parley",
					"",
					"Particulars",
					"Sanctions",
					"Booty",
					"Loot"
				],
				types: {
					pending: "Queued loot",
					outgoing: "Delivered loot",
					incoming: "Hail-shot",
					self: "Buried loot",
				},
				noMessage: "No parlay",
				encrypted: "Message is in a bottle",
				view: "Spyglass",
				pending: "Queued",
				noTransactions: "No loot has been delivered or gathered",
				loading: "Reading loot book..."
			}
		},
		harvestedBlocks: {
			title: "Plunderings",
			feeEarned: "Bounties found in last 25 plunderings",
			table: {
				columns: [
					"Height",
					"Time",
					"Block hash",
					"Bounty"
				],
				noBlocks: "No bounties",
				loadMore: "Read more bounties"
			},
			harvesting: {
				unknown: "Marooned",
				start: "Begin plundering",
				harvesting: "Plundering",
				stop: "Cease plundering"
			}
		},
		settings: {
			title: "Fix riggin'",
			settings: [
				{
					name: "Parlance"
				}
			],
			save: "AYE!",
			saveSuccess: "Riggin' has been fixed"
		}
	}
});
