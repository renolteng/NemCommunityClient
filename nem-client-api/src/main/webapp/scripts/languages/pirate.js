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
        		unavailable: "Ship not in range",
        		notBooted: "Ship still in port. Pick yer coffer and set the sails.",
        		synchronizing: "Setting sails. At block {{1}}, est. {{2}} behind.",
        		daysBehind: {
        			0: "less than 1 day",
        			1: "1 day",
        			many: "{{1}} days"
        		}
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
			logo: "data:image/png;base64,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",
            importSuccess: "Coffer collected!",
			nav: {
				start: "Getting <strong>Started</strong>",
				about: "About <strong>NEM</strong>",
				help: "<strong>Help</strong>"
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
			logo: "data:image/png;base64,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",
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
