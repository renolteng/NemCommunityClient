<!DOCTYPE html>
<!--[if IE]><html lang="en" class="ie"><![endif]-->
<!--[if !IE]><!--><html lang="en" class="non-ie"><!--<![endif]-->
	<head>
		<meta charset="utf-8" />
	    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
	    <script>if(document.documentMode===10||document.documentMode===11)document.documentElement.className='ie'</script>
		<link rel="stylesheet" type="text/css" href="styles/css/all.css" />
		<title>NEM Community Client</title>
		<link rel="shortcut icon" href="images/Favicon.ico" />
	</head>
	<body>
		<script type="text/ractive" id="template">
			<div class="statusIndicator {{^nisStatus}}hide{{/}} {{#nisStatus.type === 'critical'}}statusIndicator--critical{{/}} {{#nisStatus.type === 'warning'}}statusIndicator--warning{{/}} {{#nisStatus.type === 'message'}}statusIndicator--message{{/}}">
				<span>{{nisStatus.message}}</span>
			</div>
			{{#layout.0.name}}
				{{>0}}
			{{/}}
			<inputModal modalClass="input" disableEasyClose="true">
				{{#message}}
					<p class="modal-message">
						{{message}}
					</p>
				{{/}}
				<div class="modal-form">
					{{#fields}}
						<div class="modal-form-field">
							<p class="modal-form-line">
								<label class="modal-form-label">
									{{.label.content}}
								</label>
							</p>
							{{#.type === 'text' || .type === 'password'}}
								<p class="modal-form-line">
									<input type="{{.type}}" class="modal-form-input" disabled="{{.readonly}}" value="{{values[.name]}}" on-keyup="inputKeyup" />
								</p>
							{{/}}
							{{#.sublabel}}
								<p class="modal-form-sublabel modal-form-line {{#nullContent}}nullContent{{/}}">
									{{.content}}
								</p>
							{{/}}
						</div>
					{{/}}
				</div>
				<div>
					<button type="button" class="modal-submitBtn modal-button primary {{#processing}}disabled{{/}}" on-click="submit">
						{{^processing}}
							{{submitLabel}}
						{{/}}
						{{#processing}}
							{{processingLabel || submitLabel}}
						{{/}}
					</button>
				</div>
			</inputModal>
			<confirmModal modalClass="confirm" disableEasyClose="true">
				<p><em>{{message}}</em></p>
				<div class="modal-actions">
					{{#actions}}
						<button type="button" class="modal-button {{.cssClass}}" on-click="confirm:{{.action}}">{{.label}}</button>
					{{/}}
				</div>
			</confirmModal>
			<messageModal modalClass="message" closeOnEnter="true">
				<p><em>{{{message}}}</em></p>
				<div class="modal-actions">
					<button type="button" class="modal-button neutral" on-click="closeModal">OK</button>
				</div>
			</messageModal>
			<errorModal modalClass="error" texts="{{texts}}">
				<div>
					<img src="images/sad-face.png" alt="Sadly"/>
				</div>
				<h1 class="modal-error-title">{{texts.modals.error.title}}</h1>
				<p class="modal-error-caption"><em>{{fill(texts.modals.error.caption, errorId)}}</em></p>
				<p class="modal-error-message">{{message}}</p>
			</errorModal>
			<unclosableMessageModal modalClass="unclosable message" noCloseButton="true" disableEasyClose="true">
				<p><em>{{{message}}}{{runningEllipsis}}</em></p>
			</unclosableMessageModal>
		</script>
		<script type="text/ractive" id="modal-template">
			<div tabindex="-1" class="modal-container {{#isActive}}active{{/}}" on-click="{{^disableEasyClose}}modalContainerClick{{/}}" on-keyup="{{^disableEasyClose}}modalContainerKeyup{{/}}">
				<article class="modal {{modalClass}}">
					<div class="modal-head">
						<h1 class="modal-title">{{modalTitle}}</h1>
					</div>
					<div class="modal-body">
						{{>content}}
					</div>
					{{^noCloseButton}}
						<button type="button" class="modal-closeBtn icon-close-plain" on-click="closeModal"></button>
					{{/}}
				</article>
			</div>
		</script>
		<script>window.entryPage='<%= entryPage %>'</script>
		<script data-main="scripts/index" src="scripts/require.js"></script>
	</body>
</html>