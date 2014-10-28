var srcPath = 'nem-client-api/src/main/webapp/';
var destPath = 'nem-client-api/target/classes/';

module.exports = function(grunt) {
	grunt.initConfig({
		copy: {
			all: {
				files: [ {expand: true, cwd: srcPath, src: '**', dest: destPath} ]
			},
			css: {
				files: [ {expand: true, cwd: srcPath, src: 'styles/css/all.css', dest: destPath} ]
			},
			templates: {
				files: [ {expand: true, cwd: srcPath, src: '*.html', dest: destPath} ]
			}
		},

		concat: {
			options: {
		    	stripBanners: true
		    },
		    css: {
		    	src: ['nem-client-api/src/main/webapp/styles/css/shared.css', 
		    	'nem-client-api/src/main/webapp/styles/css/pages/*.css'],
		    	dest: 'nem-client-api/src/main/webapp/styles/css/all.css'
		    }
		},

		watch: {
			copySrc: {
				files: ['nem-client-api/src/main/webapp/**'],
				tasks: ['copy:all'],
				options: {
			    	spawn: false
		      	}
			},
			css: {
				files: ['nem-client-api/src/main/webapp/styles/css/shared.css', 'nem-client-api/src/main/webapp/styles/css/pages/page_groups/*.css', 'nem-client-api/src/main/webapp/styles/css/pages/*.css'],
				tasks: ['concat:css', 'copy:css'],
				options: {
			    	spawn: false
		      	}
			},
			template: {
				files: ['nem-client-api/src/main/webapp/entry.tpl'],
				tasks: ['template', 'copy:templates'],
				options: {
			    	spawn: false
		      	}
			}
		},

		concurrent: {
			all: ['watch:copySrc', 'watch:css', 'watch:template'],
			options: {
				logConcurrentOutput: true
			}
		},

		template: {
			landing: {
				options: {
					data: {
						entryPage: 'landing'
					}
				},
				files: {
					'nem-client-api/src/main/webapp/index.html': ['nem-client-api/src/main/webapp/entry.tpl']
				}
			},
			dashboard: {
				options: {
					data: {
						entryPage: 'dashboard'
					}
				},
				files: {
					'nem-client-api/src/main/webapp/dashboard.html': ['nem-client-api/src/main/webapp/entry.tpl']
				}
			},
			transactions: {
				options: {
					data: {
						entryPage: 'transactions'
					}
				},
				files: {
					'nem-client-api/src/main/webapp/transactions.html': ['nem-client-api/src/main/webapp/entry.tpl']
				}
			},
			harvestedBlocks: {
				options: {
					data: {
						entryPage: 'harvested-blocks'
					}
				},
				files: {
					'nem-client-api/src/main/webapp/harvested-blocks.html': ['nem-client-api/src/main/webapp/entry.tpl']
				}
			},
			transactions: {
				options: {
					data: {
						entryPage: 'transactions'
					}
				},
				files: {
					'nem-client-api/src/main/webapp/transactions.html': ['nem-client-api/src/main/webapp/entry.tpl']
				}
			},
			start: {
				options: {
					data: {
						entryPage: 'start'
					}
				},
				files: {
					'nem-client-api/src/main/webapp/start.html': ['nem-client-api/src/main/webapp/entry.tpl']
				}
			}
		}
	});

	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-concurrent');
	grunt.loadNpmTasks('grunt-template');

	grunt.event.on('watch', function(action, filepath) {
		grunt.config('copy.all.files', [{
			expand: true, 
			cwd: 'nem-client-api/src/main/webapp/', 
			src: filepath.replace(/\\/g, '/').replace(srcPath, ''), 
			dest: destPath
		}]);
	});
}