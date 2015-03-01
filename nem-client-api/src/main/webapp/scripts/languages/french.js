define({
	id: 'fr',
	name: 'Français',
	texts: {
		preferences: {
			thousandSeparator: ' ',
			decimalSeparator: '.'
		},
		faults: {
			101: 'Fichier non trouvé.',
			102: 'Le portefeuille a été créé.',
			103: 'Le portefeuille est corrompu. S\'il vous plait, récupérez votre portefeuille à partir de la copie de sauvegarde que vous avez faite lors de la création de votre portefeuille or lorsque vous y avez ajouté un compte.',
			104: 'Le mot de passe est incorrect. En espérant que vous pourrez vous en rappeler. Les mots de passe ne peuvent pas être récupérés s\'ils sont perdus!',
			105: 'No password was provided for the wallet.',
			106: 'Avant de pouvoir utiliser un portefeuille, il doit être ouvert. Pour s\'assurer que vous autoriser à accéder à ce portefeuille, vous devez fournir le mot de passe pour ce portefeuille.',
			107: 'Ce portefeuille ne contient pas ce compte.',
			108: 'Ce compte ne peut pas être retiré. Ce problème se produit généralement si vous tentez de retirer un compte avec une balance supérieure à 0 XEMs ou s\'il s\'agit de votre compte primaire.',
			109: 'Un autre portefeuille avec le même nom existe déjà. S\'il vous plait, veuillez choisir un nom différent.',
			110: 'Le portefeuille contient déjà ce compte.',
			111: 'Le nom du portefeuille est un répertoire.',
			112: 'L\'extension du fichier de portefeuille est incorrecte.',
			113: 'Le portefeuille n\'a pas pu être effacé.',
			121: 'Le fichier de carnet d\'adresses n\'existe pas.',
			122: 'Le carnet d\'adresses a été créé.',
			123: 'Le fichier du carnet d\'adresses est corrompu. S\'il vous plait, récupérer votre carnet d\'adresses à partir d\'une copie de sauvegarde.',
			124: 'Le mot de passe pour le carnet d\'adresses que vous avez fourni est incorrect.',
			125: 'Vous n\'avez fourni aucun mot de passe pour le carnet d\'adresses.',
			127: 'Le carnet d\'adresses ne contient pas cette adresse.',
			128: 'The address provided is not valid.',
			129: 'Un autre carnet d\'adresses avec le même nom existe déjà. S\'il vous plait, utilisez un nom différent.',
			130: 'Le carnet d\'adresses contient déjà cette adresse.',
			131: 'Le nom du carnet d\'adresses est un répertoire.',
			132: 'L\'extension du fichier de carnet d\'adresses est incorrecte.',
			133: 'Le carnet d\'adresses n\'a pas pu être effacé.',
			202: 'Un message chiffré ne peut être envoyé si le destinataire n\'a jamais effectué de transaction auparavant.',
			305: 'The NEM Infrastructure Server (NIS) is not available.\n\nTry to restart the NEM software.\n\nIf you are using a remote NIS, check your configured host for typing errors or use another remote NIS.',
			306: 'Une erreur que l\'équipe de développement n\'avait pas anticipée s\'est produite. Nous vous présentons nos excuses et nous espérons qu\'un nouvel essai va régler ce problème. Sinon, ouvrez un billet d\'incident auprès de la communauté NIS/NCC de NEM.',
			400: 'Certains paramètres sont manquants ou invalides.',
			401: 'Cette opération ne peut être effectuée car elle pourrait divulguer votre clé privée en l\'envoyant à un server NIS distant.',
			404: 'La ressource que vous avez demandé ne peut être trouvée.',
			500: 'Une erreur que l\'équipe de développement n\'avait pas anticipée s\'est produite. Nous vous présentons nos excuses et nous espérons qu\'un nouvel essai va régler ce problème. Sinon, ouvrez un billet d\'incident auprès de la communauté NIS/NCC de NEM.',
			600: 'NCC requiert que le serveur NIS soit démarré pour pouvoir envoyer et recevoir des transactions vers le nuage NEM. S\'il vous plait, utilisez le menu NCC pour démarrer le noeud local.',
			601: 'Le noeud NIS est déjà démarré. Une deuxième tentative de démarrage du noeud est impossible.',
			602: 'Cannot perform any operations until db is fully loaded.',
			699: 'Le nombre maximum de cueilleurs permis sur le serveur a été atteint.',
			700: 'Le compte fourni ne satisfait pas les critères de base pour la récolte. C\'est généralement lié à la quantité de XEM dans le compte. Le compte doit avoir au minimum 1000 XEM pour que la récolte débute.',
			701: 'La date limite fournie est dans le passé. La date limite doit être dans un délai dune journée.',
			702: 'La date limite fournie est trop dans le futur. La date limite doit être dans un délai dune journée.',
			703: 'Votre compte n\'a pas une balance suffisante pour envoyer le nombre nombre de XEMs indiqué.',
			704: 'Le message que vous avez inscrit est trop long pour être envoyé via NEM. Essayez de réduire la taille du message que vous tentez d\'expédier.',
			705: 'Le hachage de transaction existe déjà dans la base de données ou dans la liste des transactions non confirmées.',
			706: 'La signature de la transaction n\'a pas pu être vérifiée.',
			707: 'L\'horodatage de la transaction trop loin dans le passé.',
			708: 'TL\'horodatage de la transaction trop loin dans le futur.',
			709: 'Ce compte est inconnu. Un compte à besoin de faire partie d\'au moins une transaction (comme expéditeur ou destinataire) pour être connue du réseau.',
			710: 'La transaction a été rejetée parce que la mémoire tampon dédiée aux transactions est remplie. Des frais plus élevés augmentent les chances que la transaction soit acceptée.',
			730: 'Une transaction de transfert d\'importance (récolte sécurisée) entre en conflit avec une transaction existante.',
			731: 'Le compte de récolte sécurisé à une balance qui n\'est pas égale à zéro et ne peut donc pas être utilisée.',
			732: 'Transaction de transfert d\'importance rejeté. Il y a déjà une transaction de transfert d\'importance en attente.',
			733: 'La recolte sécuriaire est déjà activé.',
			734: 'La récolte sécuritaire n\'est PAS activée. Impossible de la désactiver.',
			740: 'Les transactions multisig ne sont pas permises sur ce compte.',
			741: 'La transaction multisig a été rejetée. Le compte utilisé n\'est cosignataire d\'aucun compte de type multisig.',
			742: 'Le compte utilisé n\'est cosignataire d\'aucun compte de type multisig. La transaction multisig associée n\'est pas connue du réseau NEM.',
			743: 'Modification de compte multisig rejeté. Un des comptes ajoutés est déjà cosignataire.',
			901: 'Il y a une erreur lors de la mise en place du mode déconnecté.',
			1000: 'La clé privée et la clé publique que vous avez fournies ne correspondent pas.',
			1001: 'La clé publique et l\'adresse que vous avez fourni ne correspondent pas.',
			1002: 'L\'adresse ne fait pas partie du réseau principal.'
		},
		common: {
			success: 'Succès',
			appStatus: {
				nccUnknown: 'Le statut de NCC est incconnu',
				nccUnavailable: 'NCC est introuvable',
				nccStarting: 'NCC démarre...',
				nisUnknown: 'Le statut de NIS est incconnu',
				nisUnavailable: 'NIS est introuvable',
				nisStarting: 'NIS démarre...',
				notBooted: 'NIS à besoin d\'être démarré. S\'il vous plaît, ouvrez votre portefeuille et démarrer le noeud local via la boîte de dialogue ou configuré le paramètre de démarrage automatique.',
				loading: 'Loading blocks from db, at block: ',
				booting: 'Démarrage de NIS...',
				nisInfoNotAvailable: 'L\'information sur NIS n\'est pas encore disponible. Tentative de récupération de l\'information sur NIS...',
				synchronizing: 'NIS est en cours de synchronisation. Présentement au bloc {{1}}, est. {{2}} en retard.',
				daysBehind: {
					0: 'moins d\'une journée',
					1: 'une journée',
					many: '{{1}} jours'
				},
				synchronized: 'NIS est synchronisé!',
				noRemoteNisAvailable: 'Aucun server distant NIS n\'a été trouvé sur le réseau. Vérifier votre connection Internet.'
			},
			addressBook: 'Carnet d\'adresses',
			password: 'Mot de passe',
			passwordValidation: 'Le mot de passe ne peut pas être vide',
			address: 'Adresse',
			privateLabel: 'Étiquette privée',
			publicLabel: 'Étiquette publique',
			noCharge: 'Le compte utilisé ne sera <b>PAS</b> chargé de frais pour cette transaction, il seront chargé au compte multisig.',
			justUse: 'Utiliser seulement'
		},
		transactionTypes: [
			'TRANSFERT DE TRANSACTION',
			'TRANSFERT D\'IMPORTANCE ',
			'MODIFICATION DE COMPTE MULTISIG',
			'TRANSACTION MULTISIG '
		],
		transactionDirections: {
			pending: 'Transaction en attente',
			outgoing: 'Transaction sortante',
			incoming: 'Transaction entrante',
			self: 'Faire une transaction vers sois même.',
			importance: 'Transaction d\'importance',
			modification: 'Modification globale de Multisig'
		},
		modals: {
			error: {
				title: 'Oops!',
				caption: 'ERREUR {{1}}'
			},
			confirmDefault: {
				yes: 'Oui',
				no: 'Non'
			},
			settings: {
				title: 'Paramètres',
				language: {
					label: 'Language'
				},
				remoteServer: {
					tabTitle: 'Serveur Distant',
					protocol: 'Protocole',
					protocolOptions: {
						http: 'HTTP'
					},
					host: 'Hôte',
					port: 'Port',
					defaultPort: 'Use default port.'
				},
				autoBoot: {
					tabTitle: 'Démarrage Automatique',
					name: 'Nom du noeud',
					account: 'Compte',
					primaryAccount: 'Compte primaire',
					auto: 'Démarrage automatiquement quand le portefeuille est ouvert'
				},
				save: 'Enregistrer',
				saveSuccess: 'Les paramètres ont été enregistrés avec succès'
			},
			multisig: {
				title: 'Convertir ce compte en multisig',
				multisigAccount: 'Compte multisig',
				cosignatories: 'Adresses des cosignataires',
				labelDesc: 'Ce compte est étiqueté comme {{1}}',
				nullLabelDesc: 'Ce compte n\'a pas d\'étiquette.',
				addCosignatory: '+ Ajouter un Cosignataires',
				cancel: 'Annuler',
				convert: 'Convertir',
				fee: 'Frais',
				feeValidation: 'Les frais ne doivent pas être moins que les frais minimums.',
				dueBy: 'Heure dû',
				useMinimumFee: 'Utiliser les frais minimums',
				hours: 'heures(s)',
				txConfirm: {
					title: 'Confirmez la conversion en compte Multisig',
					total: 'Total',

				},
				warning: 'Le compte Multisig est sur la liste de cosignataires. Cette action va barrer l\'accès à ce compte et au fond qu\'il contient. Vous ne voulez probablement <bPAS</b> executer cette action.'
			},
			signMultisig: {
				title: 'Signer la transaction multisig',
				original: {
					from: 'Compte Multisig',
					to: 'Destinataire',
					amount: 'Montant',
					fee: 'Frais interne',
					deadline: 'Date Limite'
				},
				multisigFees: 'Frais Multisig',
				multisigTotal: 'Total',
				sender: 'Cosignataire',
				fee: 'Frais',
				feeValidation: 'Les frais ne doivent pas être moins que les frais minimums.',
				dueBy: 'Heure dû',
				useMinimumFee: 'Utiliser les frais minimums',
				hours: 'heure(s)',
				password: 'Mot de passe',
				passwordValidation: 'Le mot de passe ne peut pas être vide',
				send: 'Envoyez',
				cancel: 'Annuler',
				sending: 'Envoi...',
				successMessage: 'La transaction a été envoyé avec succès!',
				txConfirm: {
					title: 'Confirmer la transaction Multisig ',
					message: 'Message',
					encrypted: 'Le message est chiffré',
					noMessage: 'Pas de message',

				}
			},
			sendNem: {
				title: 'Envoyez des XEMs',
				sender: 'Expéditeur',
				thisAccount: 'Ce compte',
				labelDesc: 'Ce compte est étiqueté comme {{1}}',
				nullLabelDesc: 'Ce compte n\'est pas étiqueté',
				amount: 'Montant',
				recipient: 'Compte du destinataire',
				recipientValidation: 'Account addresses must be 40 character long excluding dashes',
				message: 'Message',
				encrypt: 'Chiffrez le message',
				fee: 'Frais',
				multisigFee: 'Frais Multisig',
				feeValidation: 'Les frais ne doivent pas être moins que les frais minimums.',
				dueBy: 'Heure dû',
				useMinimumFee: 'Utiliser les frais minimums',
				hours: 'heure(s)',
				password: 'Mot de passe',
				passwordValidation: 'Le mot de passe ne peut pas être vide',
				send: 'Envoyez',
				cancel: 'Annuler',
				sending: 'Envoi...',
				successMessage: 'La transaction a été envoyé avec succès!',
				txConfirm: {
					title: 'Confirmez la transaction',
					amount: 'Montant',
					to: 'À',
					dueBy: 'Heure dû',
					hours: 'heures(s)',
					total: 'Total',
					message: 'Message',
					encrypted: 'Le message est chiffré',
					noMessage: 'Pas de message',
					cancel: 'Annuler',
					confirm: 'Confirmer',
					sending: 'Envoi...'
				},
				notBootedWarning: {
					title: 'Le noeud n\'a pas été démarré.',
					message: 'Le noeud local doit être démarré avant de pouvoir envoyer des XEMs!'
				},
				bootingWarning: {
					title: 'Le noeud est en cours de démarrage',
					message: 'S\'il vous plait, attendre que le processus de démarrage soit terminé avant d\'envoyer votre transaction.'
				},
				loadingWarning: {
					title: 'Chargement de la base de donnée'
				}
			},
			clientInfo: {
				title: 'Information sur le Client',
				ncc: 'NEM Community Client - NCC',
				signer: 'Signataire',
				remoteServer: 'Serveur distant',
				local: 'Local',
				nis: 'NEM Infrastructure Server - NIS',
				sync: 'Synchronisé',
				notSync: 'Pas synchronisé',
				notConnected: 'Pas connecté au Nuage NEM',
				loading: 'Chargement...'
			},
			transactionDetails: {
				title: 'Détails de la transaction',
				id: 'ID',
				hash: 'Hachage',
				type: 'Type de transaction',
				direction: 'Transaction Direction',
				pending: 'En attente',
				outgoing: 'Sortant',
				incoming: 'Entrant',
				self: 'Sois même',
				sender: 'Expéditeur',
				multisigAccount: 'Multisig Account',
				issuer: 'Emetteur',
				recipient: 'Destinataire',
				remote: 'À Distance',
				multisigMessage: 'Signatures présente',
				message: 'Message',
				noMessage: 'Aucun message',
				encrypted: 'Le Message est chiffré',
				time: 'Horodatage',
				confirmations: 'Confirmations',
				confirmationsUnknown: 'Inconnu',
				amount: 'Montant',
				fee: 'Frais',
				innerFee: 'Frais interne',
				multisigFees: 'Frais Multisig',
				cosignatory: 'Cosignataire'
			},
			accountDetails: {
				title: 'Détails du compte',
				address: 'Adresse',
				label: 'Étiquette',
				noLabel: 'Pas d\'étiquette',
				add: 'Ajouter au carnet d\'adresses',
				remove: 'Retirer du carnet d\'adresses',
				balance: 'Balance',
				vested: 'Assigneé',
				importance: 'Importance',
				publicKey: 'Clé publique',
				noPublicKey: 'Pas de clé publique',
				harvestedBlocks: 'Blocs récoltés',
				close: 'Fermeture'
			},
			bootLocalNode: {
				title: 'Démarrage du noeud local',
				account: 'Compte pour démarrarer le noeud local',
				noLabel: '<span class=\'null\'>&lt;Aucune étiquette&gt;</span>',
				wallet: 'Portefeuille',
				node: 'Nom du noeud',
				boot: 'Démarrer',
				booting: 'Démarrage...'
			},
			closeWallet: {
				title: 'Fermeture de portefeuille',
				message: 'Êtes-vous sûr de vouloir fermer ce portefeuille et retourner à la page d\'accueil?'
			},
			createAccount: {
				title: 'Créer un nouveau compte',
				label: 'Étiquette privé',
				wallet: 'Portefeuille',
				password: 'Mot de passe du portefeuille',
				successMessage: 'Le compte {{1}} {{#2}}({{2}}){{/2}} à été créé!',
				create: 'Créer'
			},
			createRealAccountData: {
				title: 'Créer des données réelles de compte .',
				message: 'Les données ci-dessous sont pour votre compte réelles après le lancement de NEM. Enregistrez l\'adresse, la clé publique et surtout la clé privée dans un endroit sûr. Si vous perdez la clé privée, votre compte et tous vos XEM seront perdus à jamais !',
				address: 'Adresse',
				publicKey: 'Clé publique',
				privateKey: 'Clé privée',
				confirm: {
					title: 'Enregistrer la clé privée',
					message: 'Êtes-vous sûr que votre clé privée a été enregistré dans un endroit sûr ?'
				},
				recheck: {
					title: 'Re-vérifier votre clé privée enregistrée',
					message: 'S\'il vous plaît, entrer de nouveau votre clé privée que vous venez de recevoir afin de vérifier que vous avez sauvegardé la bonne. Si votre clé privée est déjà perdue , vous pouvez en créer une nouvelle.',
					correct: {
						title: 'Excellent!',
						message: 'Vous semblez avoir sauvegardé la bonne clé privée. S\'il vous plaît, n\'oubliez pas de toujours la garder en lieu sûr et sécuriser!'
					},
					incorrect: {
						title: 'Hmm...',
						message: 'La clé privée que vous venez d\'entrer n\'est pas correcte ! S\'il vous plaît, entrez votre clé privée de nouveau.',
						tryAgain: 'Essayez de l\'entrer encore une fois',
						seeOriginal: 'Voir les données originales.'
					},
					recheck: 'Vérifier'
				},
				ok: 'Ok'
			},
			verifyRealAccountData: {
				title: 'Vérifier les données réelles de compte',
				message: 'Entrez l\'adresse que vous avez enregistrée à nouveau ainsi que la clé publique et une clé privée pour vérifier si elles correspondent.',
				address: 'Addresse',
				publicKey: 'Clé publique',
				privateKey: 'Clé privée',
				dataMatched: 'Tout semble bon : l\'adresse indiquée, la clé publique et clé privée correspondance.',
				verify: 'Vérifier'
			},
			addAccount: {
				title: 'Ajouter un compte existant',
				privateKey: 'Clé privé du compte',
				wallet: 'Portefeuille',
				password: 'Mot de passe du portefeuille',
				successMessage: 'Le compte {{1}} {{#2}}({{2}}){{/2}} à été ajouté à votre portefeuille!',
				add: 'Ajouter',
				label: 'Étiquette'
			},
			setPrimary: {
				title: 'Définir votre compte principal',
				account: 'Le compte a été défini comme votre compte principal',
				noLabel: '<span class=\'null\'>&lt;Aucune étiquette&gt;</span>',
				wallet: 'Portefeuille',
				password: 'Mot de passe du portefeuille',
				successMessage: 'Le compte {{1}} {{#2}}({{2}}){{/2}} a été défini comme votre compte principal!',
				set: 'Définir comme compte principal'
			},
			changeWalletName: {
				title: 'Changez le nom du portefeuille',
				wallet: 'Nom actuel du portefeuille',
				newName: 'Nouveau nom du portefeuille',
				password: 'Mot de passe du portefeuille',
				successMessage: 'Le nom du portefeuille a été changé avec succès de <em>{{1}}</em> à <em>{{2}}</em>',
				change: 'Changer'
			},
			changeWalletPassword: {
				title: 'Changer le mot de passe du portefeuille',
				wallet: 'Portefeuille',
				password: 'Mot de passe actuel du portefeuille',
				newPassword: 'Nouveau mot de passe',
				confirmPassword: 'Confirmer le nouveau mot de passe',
				successMessage: 'Le mot de passe du portefeuille a été changé avec succès',
				change: 'Changer',
				passwordNotMatchTitle: 'Oops!',
				passwordNotMatchMessage: 'Le mot de passe et le mot de passe de confirmation ne sont pas pareils. S\'il vous plait, assurez-vous de taper votre nouveau mot de passe correctement.'
			},
			changeAccountLabel: {
				title: 'Changer l\'étiquette du compte',
				label: 'Étiquette du compte',
				wallet: 'Portefeuille',
				password: 'Mot de passe du portefeuille',
				successMessage: 'Le compte {{1}} est maintenant étiquetté comme {{2}}',
				change: 'Changer'
			},
			removeAccount: {
				title: 'Retirer un compte',
				wallet: 'Portefeuille',
				password: 'Mot de passe du portefeuille',
				warning: 'S\'il vous plait, assurez-vous que ce compte ne contient plus de XEMs avant de le retirer, sinon les XEMs qu\'il contient seront perdus pour toujours.',
				successMessage: 'Le compte {{1}} {{#2}}({{2}}){{/2}} à été retiré!',
				remove: 'Retirer'
			},
			nisUnavailable: {
				title: 'NIS est non disponible',
				message: 'Déconnecté de NIS, en attente de connexion'
			},
			shutdown: {
				title: 'Fermeture de l\'application',
				message: 'Êtes-vous certain de vouloir fermer l\'application NEM Community Client?'
			},
			activateRemote: {
				title: 'Activer la récolte à distance',
				wallet: 'Portefeuille',
				account: 'Compte',
				hoursDue: 'Heure dû',
				password: 'Mot de passe du portefeuille',
				activate: 'Activer'
			},
			deactivateRemote: {
				title: 'Désactiver la récolte à distance',
				wallet: 'Portefeuille',
				account: 'Compte',
				hoursDue: 'Heure dû',
				password: 'Mot de passe du portefeuille',
				deactivate: 'Désactiver'
			},
			startRemote: {
				title: 'Démarrer la récolte à distance',
				wallet: 'Portefeuille',
				account: 'Compte',
				password: 'Mot de passe du portefeuille',
				start: 'Démarrer'
			},
			stopRemote: {
				title: 'Arrêtez la récolte à distance',
				wallet: 'Portefeuille',
				account: 'Compte',
				password: 'Mot de passe du portefeuille',
				stop: 'Arrêtez'
			},
			logoutWarning: {
				leavePage: 'Vous laissez votre portefeuille ouvert. Rappelez-vous que si vous laissez votre portefeuille de cette façon , d\'autres pourraient potentiellement y accéder de cet ordinateur.Pour empêcher ceci d\'arriver, s\'il vous plaît vous connectez à l\'aide du menu \'Fermeture de portefeuille\' dans le menu déroulant en haut à droite avant de fermer l\'onglet de votre navigateur ou de naviguer ailleurs.'
			},
			addContact: {
				title: 'Ajouter un contact',
				add: 'Ajouter'
			},
			editContact: {
				title: 'Modifier le contact',
				saveChanges: 'Enregistrer les modifications'
			},
			removeContact: {
				title: 'Retirer le contact',
				remove: 'Retirer'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'Le portefeuille a été importé avec succès!',
			nav: {
				start: 'Commencer',
				about: 'À propos de NEM',
				settings: 'Paramètres'
			},
			main: {
				leftTitle: 'Nouveau dans <em>NEM</em>?',
				leftButton: 'Créer un nouveau portefeuille ',
				walletNamePlh: 'Nom de votre portefeuille',
				passwordPlh: 'Mot de passe',
				confirmPasswordPlh: 'Confirmez le mot de passe',
				create: 'Créer',
				creating: 'Creation...',
				rightTitle: 'Déjà un <em>NEM</em>bre?',
				rightButton: 'Ouvrir votre portefeuille',
				openButton: 'Ouvrir',
				walletsFound: '<strong>{{1}}</strong> <em>portefeuille(s) trouvé(s)</em>',
				copyright: 'Photographie par <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC chiffre votre portefeuille',
						description: 'La <em>sécurité</em> est très importante pour éviter le vols de vos pièces XEMs &amp; actifs.'
					},
					{
						title: 'NCC chiffre votre portefeuille',
						description: 'La <em>sécurité</em> est très importante pour éviter le vols de vos pièces XEMs &amp; actifs.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'Comment NCC fonctionne?',
						paragraphs: [
							'<strong>NCC</strong>offre un accès à vos actifs et vos XEMs comme un portefeuille traditionnel le ferait.',
							'<strong>NCC</strong> requiert l\'accès à un serveur <strong>NIS</strong> pour pouvoir être fonctionnelle. Le standard est d\'avoir un serveur local actif (est installé ensemble avec <strong>NCC</strong>)',
							'Vous pouvez aussi configurer un accès à un serveur <strong>NIS</strong> distant.'
						],
						listItems: [
							'Multiples portefeuilles',
							'Définir de multiple compte à être inclus dans votre portefeuille'
						]
					},
					{
						title: 'Qu\'est-ce que le &#42;NIS?',
						paragraphs: [
							'Cette composante est responsable de garder le nuage <strong>NEM</strong> vivant.',
							'Plus il y a de <strong>NIS</strong> meilleure est la sécurité.',
							'<strong>NIS</strong> est le point d\'accès au nuage <strong>NEM</strong>.'
						],
						legend: '<strong>&#42;NIS</strong> signifie <strong>Serveur d\'infrastructure NEM (NEM Infrastructure Server)</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Copyright 2015. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: 'Il y a approximativement {{1}} jours',
			lastAccessJustNow: 'Juste maintenant',
			lastAccessTooltip: 'Dernier accès {{1}}',
			primary: 'primaire',
			primaryShort: 'P',
			noLabel: '<Pas d\'étiquette>',
			copiedToClipboard: 'L\'adresse a été copié dans le presse papier!',
			actions: {
				refreshInfo: 'Actualiser les informations',
				bootLocalNode: 'Démarré le noeud local',
				changeWalletName: 'Changer le nom du portefeuille',
				changeWalletPassword: 'Changer le mot de passe du portefeuille',
				mergeWallets: 'Fusionner des portefeuilles',
				exportWallet: 'Exporter un portefeuille',
				createAccount: 'Créer un nouveau compte',
				createRealAccountData: 'Créer les données réelles de compte',
				verifyRealAccountData: 'Vérifier les données réelles de compte',
				addAccount: 'Ajouter un compte existant',
				changeAccountLabel: 'Modifier l\'étiquette du compte',
				setPrimary: 'Définir comme compte primaire',
				removeAccount: 'Retirer le compte',
				clientInfo: 'Information sur le Client',
				closeWallet: 'Fermer le portefeuille',
				closeProgram: 'Fermer l\'application',
				copyClipboard: 'Copier l\'adresse dans le presse-papier',
				convertMultisig: 'Convertir un autre compte en type multisig'
			},
			nav: [
				'Tableau de bord',
				'Messages',
				'Contacts',
				'Transactions',
				'Blocs récoltés',
				'Bourse d\'actif',
				'Nouvelles',
				'Applications',
				'Comptes',
				'Paramètres',
				'Fermer l\'application'
			],
			bootNodeWarning: 'Un noeud local doit être démarré avant de pouvoir utiliser pleinement les fonctionnalités de NCC.'
		},
		dashboard: {
			assets: {
				title: 'Vos actifs'
			},
			importance: {
				title: 'Score d\'importance',
				unknown: 'statut inconnu',
				start: 'Lancer la récolte locale',
				harvesting: 'Récolte',
				stop: 'Arrêter la récolte locale',
				description: 'importance de compte dans le nuage NEM',
				remoteHarvest: {
					activate: 'Activer la récolte à distance',
					activating: 'Activation de la récolte à distance ...',
					active: 'La récolte à distance est active',
					deactivate: 'Désactiver la récolte à distance',
					deactivating: 'Désactivation de la récolte à distance...',
					startRemoteHarvesting: 'Démarrer la récolte à distance',
					remotelyHarvesting: 'Récolte à distance en cours',
					stopRemoteHarvesting: 'Arrêtez la récolte à distance'
				}
			},
			transactions: {
				title: 'Transactions récentes',
				sendNem: 'Envoyer des XEMs',
				signMultisig: 'SIGNER',
				balance: 'Balance courante',
				vestedBalance: 'Vested Balance',
				syncStatus: '(au blocs {{1}}{{#2}} : est. {{3}} jours en retard{{/2}})',
				unknown: 'inconnu',
				columns: [
					'',
					'Temps',
					'Expéditeur/Destinataire',
					'Message',
					'',
					'Détails',
					'Confirmations',
					'Frais',
					'Montant'
				],
				noMessage: 'Pas de message',
				encrypted: 'Le message est chiffé',
				view: 'Voir',
				confirmationsUnknown: 'Unknown',
				pending: 'En attente',
				seeAll: 'Voir toutes les transactions',
				noTransactions: 'Aucune transaction n\'a encore été effectuée'
			},
			nemValue: {
				title: 'Statistiques sur la valeur de XEM'
			},
			messages: {
				titleTooltip: 'Messages'
			},
			news: {
				titleTooltip: 'Nouvelles'
			},
			notAvailable: 'Pas encore disponible en version alpha'
		},
		transactions: {
			title: 'Transactions',
			sendNem: 'Envoyer des XEMs',
			balance: 'Balance courante',
			filters: {
				confirmed: 'Confirmé',
				unconfirmed: 'Non-confirmé',
				incoming: 'Entrante',
				outgoing: 'Sortante'
			},
			table: {
				columns: [
					'',
					'Temps',
					'Expéditeur/Destinataire',
					'Message',
					'',
					'Détails',
					'Confirmations',
					'Frais',
					'Montant'
				],
				noMessage: 'Pas de message',
				encrypted: 'Le message est chiffé',
				view: 'Voir',
				confirmationsUnknown: 'Inconnu',
				pending: 'En attente',
				noTransactions: 'Aucune transaction n\'a encore été effectuée',
				loading: 'Chargement de transactions supplémentaire...'
			}
		},
		harvestedBlocks: {
			title: 'Blocs récoltés',
			feeEarned: 'Honoraires perçus pour les 25 derniers blocs récoltés',
			unknown: 'Inconnu',
			table: {
				columns: [
					'Hauteur',
					'Temps',
					'Hachage du bloc',
					'Frais'
				],
				noBlocks: 'Aucun blocs récoltés ',
				loading: 'Chargement de blocs récoltées supplémentaire'
			},
			harvesting: {
				unknown: 'Statut inconnu',
				start: 'Lancer la récolte locale',
				harvesting: 'Récolte',
				stop: 'Arrêter la récolte locale',
				remoteHarvest: {
					startRemoteHarvesting: 'Démarrer la récolte distante',
					stopRemoteHarvesting: 'Arrêter la récolte distante'
				}
			}
		},
		addressBook: {
			title: 'Carnet d\'adresses',
			addContact: 'Ajouter un contact',
			table: {
				columns: [
					'Adresse de compte',
					'Étiquette privée',
					'Étiquette publique'
				],
				noContacts: 'Il n\'y a pas de contact dans votre carnet d\'adresses.'
			},
			noLabel: 'Pas d\'étiquette',
			sendNem: 'Evoyer des XEMs',
			edit: 'Modifier',
			remove: 'Supprimer'
		},
		settings: {
			title: 'Paramètres',
			settings: [
				{
					name: 'Langue'
				}
			],
			save: 'Enregistrer les modifications',
			saveSuccess: '`Les paramètres ont été enregistrés avec succès'
		}
	}
});
