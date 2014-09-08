define({
	id: 'vi',
	name: 'Tiếng Việt',
	texts: {
		faults: {
            101: 'Không tìm thấy file.',
            102: 'Ví của bạn chưa được tạo.',
            103: 'File ví của bạn đã bị hỏng. Xin hãy khôi phục ví của bạn từ một nguồn dự phòng mà bạn đã tạo khi bạn tạo ví hoặc khi thêm tài khoản vào đó.',
            104: 'Mật khẩu bạn cung cấp không đúng. Hy vọng rằng bạn vẫn nhớ mật khẩu đúng. Mật khẩu không thể khôi phục được nếu đã bị mất!',
            106: 'Trước khi bạn có thể làm việc với một chiếc ví, nó phải được mở. Để chắc chắn rằng bạn có quyền truy cập vào ví, bạn phải cung cấp mật khẩu cho ví đó.',
            107: 'Ví không chứa tài khoản này',
            108: 'Tài khoản không thể bị xoá. Có vẻ bởi vì tài khoản vẫn còn số dư lón hơn 0 NEM hoặc tài khoản bạn đang cố gắng xoá là tài khoản chính.',
            109: 'Một ví khác có cùng tên đã tồn tại. Hãy chọn một cái tên khác.',
            110: 'Tài khoản này đã có trong ví rồi.',
            202: 'Không thể gửi thông điệp mã hoá bởi vì người nhận chưa từng thực hiện một giao dịch trước đây.',
            305: 'NEM Infrastructure Server không khả dụng.',
            306: 'Một lỗi nằm ngoài dự tính của nhóm phát triển đã xảy ra. Xin lỗi bạn vì điều này, có thể thử lại sẽ có tác dụng. Nếu không, hãy tạo một issue trong cộng đồng NIS/NCC của NEM.',
            400: 'Một tham số nào đó bị thiếu hoặc không hợp lệ.',
            404: 'Không tìm thấy tài nguyên được yêu cầu.',
            500: 'Lưu file cấu hình thất bại.',
            600: 'NCC yêu cầu máy chủ NIS phải được khởi động để gửi và nhận các giao dịch từ NEM cloud. Hãy dùng thực đơn của NCC để khởi động local node.',
            601: 'NIS node đã được khởi động rồi. Không thể khởi động NIS một lần nữa.',
            700: 'Tài khoản được cung cấp không thoả mãn các tiêu chí cơ bản để được thu hoạch. Phần lớn liên quan tới lượng NEM có trong tài khoản. Việc thu hoạch có thể bắt đầu với ít nhất 1000 NEM.',
            701: 'Deadline được cung cấp đã trôi qua.',
            702: 'Deadline được cung cấp quá xa trong tương lai.',
            703: 'Tài khoản của bạn không có số dư đủ để gửi số lượng NEM này.',
            704: 'Đoạn thông điệp được cung cấp quá lớn để gửi qua NEM. Hãy cố gắng giảm độ dài của thông điệp mà bạn gửi.',
            705: 'Hash của giao dịch đã tồn tại trong cơ sở dữ liệu hoặc trong danh sách những giao dịch chưa được xác nhận.',
            706: 'Không thể xác nhận chữ ký của giao dịch.',
            707: 'Mốc thời gian của giao dịch quá xa trong quá khứ.',
            708: 'Mốc thời gian của giao dịch quá xa về tương lai.',
            709: 'Tài khoản không được biết đến. Một tài khoản cần phải tham gia vào ít nhất một giao dịch (là người gửi hoặc người nhận) để được mạng lưới biết đến.',
            901: 'There was an error setting up offline mode.'
        },
        common: {
        	success: 'Thành công', //title of the Success message modals
        	nisStatus: {
        		unavailable: 'NIS không khả dụng',
        		notBooted: 'NIS cần phải được khởi động. Hãy mở ví của bạn và khởi động local node bằng hộp thoại hiện ra.',
        		synchronizing: 'NIS đang đồng bộ hoá. Đang ở block {{1}}, trễ khoảng {{2}}.',
                daysBehind: {
                    0: 'ít hơn 1 ngày',
                    1: '1 ngày',
                    many: '{{1}} ngày'
                }
        	}
        },
		modals: {
			error: {
				title: 'Ui chà!',
				caption: 'LỖI {{1}}'
			},
			confirmDefault: {
				yes: 'Có',
				no: 'Không'
			},
			sendNem: {
				title: 'Gửi NEM',
				labelDesc: 'Tài khoản này được dán nhãn là <strong>{{1}}</strong>',
				nullLabelDesc: "Tài khoản này không được dán nhãn",
				amount: 'Số lượng',
				recipient: "Người nhận",
				message: 'Thông điệp',
				encrypt: 'Mã hoá thông điệp',
				fee: 'Chi phí',
				dueBy: 'Hết hạn',
				resetFee: 'Đặt lại phí tối thiểu',
				hours: 'giờ',
				password: 'Mật khẩu',
				send: 'Gửi',
				sending: 'Đang gửi...',
				successMessage: 'Giao dịch đã được gửi đi thành công!',
			},
			clientInfo: {
				title: 'Thông tin client',
				ncc: 'NEM Community Client - NCC',
				signer: 'Chữ ký',
				remoteServer: 'Máy chủ từ xa',
				local: 'Cục bộ',
				nis: 'NEM Infrastructure Server - NIS',
				sync: 'Đồng bộ',
				notSync: 'Không đồng bộ',
				notConnected: 'Không kết nối tới NEM Cloud',
				loading: 'Đang tải...'
			},
			transactionDetails: {
				title: 'Chi tiết giao dịch',
				// this might be block or transaction ID
				id: 'ID',
				// this might be block or transaction Hash
				hash: 'Hash',
				type: 'Loại giao dịch',
				pending: 'Đang chờ xác nhận',
				outgoing: 'Gửi đi',
				incoming: 'Gửi đến',
				self: 'Tự gửi',
				sender: 'Người gửi',
				recipient: 'Người nhận',
				message: 'Thông điệp',
				noMessage: 'Không có thông điệp nào',
				encrypted: 'Thông điệp được mã hoá',
				time: 'Mốc thời gian',
				confirmations: 'Số xác nhận',
				amount: 'Số lượng',
				fee: 'Chi phí'
			},
			bootLocalNode: {
				title: 'Khởi động local node',
				account: 'Tài khoản để khởi động local node',
				noLabel: '<span class="null">&lt;Không có nhãn&gt;</span>',
				wallet: 'Ví',
				node: 'Tên node',
				boot: 'Khởi động',
				booting: 'Đang khởi động...'
			},
			notBootedWarning: {
				title: 'Node chưa được khởi động!',
				message: 'Local node cần phải được khởi động trước khi bạn có thể gửi NEM đi!'
			},
			closeWallet: {
				title: 'Đóng ví',
				message: 'Bạn có chắc bạn muốn đóng ví của mình và trở về trang chủ?'
			},
			createAccount: {
				title: 'Tạo tài khoản mới',
				label: 'Nhãn cá nhân',
				wallet: 'Ví',
				password: "Mật khẩu ví",
				successMessage: 'Tài khoản {{1}} {{#2}}({{2}}){{/2}} đã được tạo!',
				create: 'Tạo'
			},
			addAccount: {
				title: 'Thêm tài khoản đã tồn tại',
				privateKey: "Khoá bí mật",
				wallet: 'Ví',
				password: "Mật khẩu ví",
				successMessage: 'Tài khoản {{1}} {{#2}}({{2}}){{/2}} đã được thêm vào ví!',
				add: 'Thêm',
				label: 'Nhãn'
			},
			setPrimary: {
				title: 'Đặt tài khoản chính',
				account: 'Tài khoản được đặt làm tài khoản chính',
				noLabel: '<span class="null">&lt;Không có nhãn&gt;</span>',
				wallet: 'Ví',
				password: "Mật khẩu ví",
				successMessage: 'Tài khoản {{1}} {{#2}}({{2}}){{/2}} đã được đặt làm tài khoản chính!',
				set: 'Đặt làm tài khoản chính',
			},
			changeWalletName: {
				title: 'Đổi tên ví',
				wallet: 'Tên ví hiện tại',
				newName: 'Tên ví mới',
				password: "Mật khẩu ví",
				successMessage: 'Tên ví đã được đổi thành công từ <em>{{1}}</em> sang <em>{{2}}</em>',
				change: 'Thay đổi'
			},
			changeWalletPassword: {
				title: 'Đổi mật khẩu ví',
				wallet: 'Ví',
				password: 'Mật khẩu hiện tại',
				newPassword: 'Mật khẩu mới',
				confirmPassword: 'Xác nhận mật khẩu mới',
				successMessage: 'Mật khẩu ví đã được thay đổi thành công',
				change: 'Thay đổi',
				passwordNotMatchTitle: 'Ui chà!',
				passwordNotMatchMessage: 'Mật khẩu và mật khẩu xác nhận mà bạn nhập không khớp. Hãy chắc chắn rằng bạn gõ đúng mật khẩu mới.'
			},
			changeAccountLabel: {
				title: 'Đổi nhãn tài khoản',
				label: 'Nhãn tài khoản',
				wallet: 'Ví',
				password: "Mật khẩu ví",
				successMessage: 'Tài khoản {{1}} giờ đây được dán nhãn là {{2}}',
				change: 'Thay đổi'
			},
			removeAccount: {
				title: 'Xoá tài khoản',
				wallet: 'Ví',
				password: "Mật khẩu ví",
				warning: 'Please ensure that your account has no NEMs left before you remove it, or they would be lost forever.',
				successMessage: 'Tài khoản {{1}} {{#2}}({{2}}){{/2}} đã được xoá!',
				remove: 'Xoá'
			},
			nisUnavailable: {
				title: 'Không thể kết nối với NIS',
				message: 'Mất kết nối từ NIS, đang đợi kết nối'
			},
			shutdown: {
				title: 'Đóng chương trình',
				message: 'Bạn có chắc rằng bạn muốn đóng NEM Community Client không?'
			}
		},
		landing: {
			logo: 'images/nem_logo.png',
			importSuccess: 'Ví đã được nhập thành công!',
			nav: {
				start: '<strong>Khởi đầu</strong>',
				about: 'Về <strong>NEM</strong>',
				help: '<strong>Trợ giúp</strong>'
			},
			main: {
				leftTitle: 'Mới đến với <em>NEM</em>?',
				leftButton: 'Tạo một ví mới',
				walletNamePlh: 'Tên ví của bạn',
				passwordPlh: 'Mật khẩu',
				create: 'Tạo',
				rightTitle: 'Đã là <em>thành viên</em>?',
				rightButton: 'Mở ví của bạn',
				openButton: 'Mở',
				walletsFound: 'Tìm thấy <strong>{{1}}</strong> <em>ví</em>',
				copyright: 'Ảnh chụp bởi <em>Cas Cornelissen</em>'
			},
			carousel: {
				items: [
					{
						title: 'NCC mã hoá ví của bạn',
						description: '<em>Bảo mật</em> là rất quan trọng với NEM để ngăn ngừa nạn trộm cắp NEM &amp; tài sản.'
					},
					{
						title: 'NCC mã hoá ví của bạn',
						description: '<em>Bảo mật</em> là rất quan trọng với NEM để ngăn ngừa nạn trộm cắp NEM &amp; tài sản.'
					}
				]
			},
			about: {
				sections: [
					{
						title: 'NCC hoạt động thế nào?',
						paragraphs: [
							'<strong>NCC</strong> cho phép bạn tiếp cận với tài sản và NEM của bạn như một chiếc ví thông thường vẫn làm. Bạn có thể',
							'<strong>NCC</strong> yêu cầu truy cập tới một <strong>NIS</strong> server để có thể hoạt động. Thôgn thường là sẽ có một server chạy trên máy bạn (được cài đặt cùng với <strong>NCC</strong>)',
							'Bạn cũng có thể cấu hình truy cập tới một máy chủ <strong>NIS</strong> từ xa.'
						],
						listItems: [
							'Có nhiều ví cùng lúc',
							'Lập ra nhiều tài khoản chứa trong một chiếc ví'
						]
					},
					{
						title: '&#42;NIS là gì?',
						paragraphs: [
							'Thành phần này chịu trách nhiệm giữ cho <strong>NEM</strong> cloud hoạt động.',
							'Càng nhiều <strong>NIS</strong> chạy thì độ bảo mật càng cao.',
							'<strong>NIS</strong> là điểm truy cập tới <strong>NEM</strong> cloud.'
						],
						legend: '<strong>&#42;NIS</strong> là viết tắt của <strong>NEM Infrastructure Server</strong>'
					}
				]
			},
			footer: {
				copyright: '&copy; Quyền tác giả 2014. NEM Community Client.'
			}
		},
		wallet: {
			logo: 'images/nem_logo.png',
			lastAccess: 'Khoảng {{1}} ngày trước',
			lastAccessJustNow: 'Mới đây',
			lastAccessTooltip: 'Truy cập lần cuối từ {{1}}',
			primary: 'chính',
			primaryShort: 'C',
			noLabel: '<Không nhãn>',
			copiedToClipboard: 'Địa chỉ đã được copy vào clipboard!',
			actions: {
				refreshInfo: 'Cập nhật dữ liệu',
				bootLocalNode: 'Khởi động Local Node',
				changeWalletName: 'Đổi tên ví',
				changeWalletPassword: 'Đổi mật khẩu ví',
				mergeWallets: 'Hợp nhất nhiều ví',
				exportWallet: 'Xuất ví',
				createAccount: 'Tạo tài khoản mới',
				addAccount: 'Thêm tài khoản đã tồn tại',
				changeAccountLabel: 'Đổi nhãn tài khoản',
				setPrimary: 'Đặt làm tài khoản chính',
				removeAccount: 'Xoá tài khoản',
				clientInfo: 'Thông tin client',
				closeWallet: 'Đóng ví',
				closeProgram: 'Đóng chương trình',
				copyClipboard: 'Copy địa chỉ vào clipboard'
			},
			nav: [
				'Bảng tin',
				'Tin nhắn',
				'Liên lạc',
				'Các giao dịch',
				'Block thu hoạch được',
				'Trao đổi tài sản',
				'Tin tức',
				'Ứng dụng',
				'Các tài khoản',
				'Thiết lập',
				'Đóng chương trình'
			],
			bootNodeWarning: "Local node cần phải được khởi động trước khi bạn có thể sử dụng đầy đủ các tính năng của NCC"
		},
		dashboard: {
			assets: {
				title: 'Tài sản của bạn'
			},
			importance: {
				title: 'Điểm tầm quan trọng',
				unknown: 'Không rõ trạng thái',
				start: 'Bắt đầu thu hoạch',
				harvesting: 'Đang thu hoạch',
				stop: 'Ngừng thu hoạch',
				description: 'tầm quan trọng của tài khoản đối với NEM cloud'
			},
			transactions: {
				title: 'Các giao dịch gần đây',
				sendNem: 'Gửi NEM',
				balance: 'Số dư hiện tại',
				syncStatus: '(tại block {{1}}{{#2}} : chậm khoảng {{3}} ngày{{/2}})',
				unknown: 'không xác định',
				columns: [
					'',
					'Thời điểm',
					'Người gửi/nhận',
					'Thông điệp',
					'',
					'Chi tiết',
					'Số xác nhận',
					'Chi phí',
					'Số lượng'
				],
				types: {
					pending: 'Giao dịch đang chờ xác nhận',
					outgoing: 'Giao dịch gửi đi',
					incoming: 'Giao dịch gửi đến',
					self: 'Giao dịch tự gửi',
				},
				noMessage: 'Không có',
				encrypted: 'Thông điệp được mã hoá',
				view: 'Xem',
				pending: 'Đang chờ xác nhận',
				seeAll: 'Xem tất cả giao dịch',
				noTransactions: 'Chưa có giao dịch nào'
			},
			nemValue: {
				title: 'Thống kê giá trị NEM'
			},
			messages: {
				titleTooltip: 'Tin nhắn'
			},
			news: {
				titleTooltip: 'Tin tức'
			},
			notAvailable: 'Chưa khả dụng trong phiên bản alpha'
		},
		transactions: {
			title: 'Các giao dịch',
			sendNem: 'Gửi NEM',
			balance: 'Số dư hiện tại',
			filters: {
				confirmed: 'Đã xác nhận',
				unconfirmed: 'Chưa xác nhận',
				incoming: 'Gửi đến',
				outgoing: 'Gửi đi',
			},
			table: {
				columns: [
					'',
					'Thời điểm',
					'Người gửi/nhận',
					'Thông điệp',
					'',
					'Chi tiết',
					'Số xác nhận',
					'Chi phí',
					'Số lượng'
				],
				types: {
					pending: 'Giao dịch đang chờ xác nhận',
					outgoing: 'Giao dịch gửi đi',
					incoming: 'Giao dịch gửi đến',
					self: 'Giao dịch tự gửi',
				},
				noMessage: 'Không có',
				encrypted: 'Thông điệp được mã hoá',
				view: 'Xem',
				pending: 'Đang chờ xác nhận',
				noTransactions: 'Chưa có giao dịch nào',
				loading: 'Đang tải thêm các giao dịch...'
			}
		},
		harvestedBlocks: {
			title: 'Các block thu hoạch được',
			feeEarned: 'Phí giao dịch kiếm được từ 25 block mới nhất',
			table: {
				columns: [
					'Chiều cao',
					'Thời điểm',
					'Block hash',
					'Chi phí'
				],
				noBlocks: 'Chưa có block nào được thu hoạch',
				loadMore: 'Xem các block cũ'
			},
			harvesting: {
				unknown: 'Không rõ trạng thái',
				start: 'Bắt đầu thu hoạch',
				harvesting: 'Đang thu hoạch',
				stop: 'Ngừng thu hoạch',
			}
		},
		settings: {
			title: 'Thiết lập',
			settings: [
				{
					name: 'Ngôn ngữ'
				}
			],
			save: 'Lưu thay đổi',
			saveSuccess: 'Thiết lập đã được lưu thành công'
		}
	}
});