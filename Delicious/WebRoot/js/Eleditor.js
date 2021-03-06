/**
* Version: 1.5
* Title: Eleditor 移动端富文本编辑器
* Site: https://eleditor.fixel.cn
* Author: Try
*/
(function(w){

	var _version = '1.5';
	var _namespace = 'Eleditor';
	var _notctname = ['INPUT', 'IMG', 'TEXTAREA'];
	var _toolnames = { 
		insertText: '插文字',
		insertImage: '插图片',
		insertLink: '插链接',
		insertHr: '水平线',
		deleteBefore: '删除前',
		deleteAfter: '删除后',
		editText: '改文字',
		deleteThis: '删除',
		undo: '撤销',
		cancel: '取消',
	};
	var _editorTpl = '';
	
	w[_namespace] = function(){};

	if( typeof jQuery === 'undefined' && typeof Zepto === 'undefined' ){
		return console.warn('|--Eleditor 请引入jQuery或者Zepto！');
	}else if( typeof $ === 'undefined' ){
		var $ = typeof jQuery != 'undefined' ? jQuery : Zepto;
	}

	var _scriptPath = function(func){
		var _js = document.scripts;
		_js = _js[ _js.length - 1 ].src.substring(0, _js[ _js.length-1 ].src.lastIndexOf("/") + 1 );
		func(_js);
		return _js;
	}(function(s){
		var _buildLib = '<link rel="stylesheet" href="'+s+'layout/base.css">';
		$('head').append(_buildLib);
	});

	var _inArray = function(s,a){
	    for(var i in a){
	        if(a[i]==s){
	            return true;
	        }
	    }
	    return false;
	};
	var _formatInnerText = function (t) {
        var s = t.replace(/\ +/g, "");
        s = t.replace(/[ ]/g, "");
        s = t.replace(/[\r\n]/g, "");
        return s.replace(/(^\s*)|(\s*$)/g, "");
    };
	var _getLayerMaxZIndex = function(){
	    var _max = Math.max.apply(null, 
	    　　$.map($('body *'), function(e) {
	    		var _$e = $(e);
				if (_$e.css('position') != 'static')
					return parseInt(_$e.css('z-index')) || 1;
	    }));
	    return (_max + '').indexOf('Infinity') >= 0 ? 1 : _max + 1;
	};

	var _genEditorUid = function(){
		return _namespace + '' + +new Date;
	};


	var _correctHtmlStructure = function(){

		var _$wrap = arguments[0],
			_empty = arguments[1];

		if( _formatInnerText(_$wrap.text()) == '' && _$wrap.find('img').length === 0 ){
			_$wrap.append(_empty);
		}

		if( _$wrap.find('*').length === 0 ){
			_$wrap.html('<p class="Eleditor-placeholder">'+_$wrap.html()+'</p>');
		}
	};

	var _buildEditorModule = function(_toolbars, _uid){

<<<<<<< HEAD
		var _html = '<div class="Eleditor-wrap" style="z-index:'+_getLayerMaxZIndex()+'" id="'+_uid+'"><div class="Eleditor-controller"><ul>';

		for (var i = 0; i < _toolbars.length; i++) {
			var _it = _toolbars[i],
				_id = typeof _it === 'object' ? _it.id : _it,
				_tag = (typeof _it === 'object' && _it.tag) ? _it.tag.toLocaleLowerCase() : null,
				_name = typeof _it === 'object' ? _it.name : _toolnames[_it];
			_html += '<li event="'+_id+'" '+(_tag ? 'on-tag="'+_tag+'"' : '')+' class="Eleditor-'+_id+'">'+_name+'</li>';
		}

		_html += '</ul></div><div class="Eleditor-loading"><p></p></div><div class="Eleditor-textEditor"><div class="Eleditor-textStyle"><ul><li class="Eleditor-textStyle-bold"></li><li class="Eleditor-textStyle-color"><span></span></li><li class="Eleditor-textStyle-underline"><span></span></li><li class="Eleditor-textStyle-bgColor"></li><li class="Eleditor-textStyle-fontSize"></li><li class="Eleditor-textStyle-lineHeight"></li><li class="Eleditor-textStyle-align Eleditor-textStyle-alignLeft" align="left"></li><li class="Eleditor-textStyle-align Eleditor-textStyle-alignCenter" align="center"></li><li class="Eleditor-textStyle-align Eleditor-textStyle-alignRight" align="right"></li></ul><button class="Eleditor-textEditor-clean">清空</button></div><div class="Eleditor-textEditor-colors"><div class="Eleditor-textEditor-modulePane"><span></span></div><ul><li><span style="background-color:#000000;"></span></li><li><span style="background-color:#0f3efe;"></span></li><li><span style="background-color:#a97a46;"></span></li><li><span style="background-color:#03fcfe;"></span></li><li><span style="background-color:#00f72c;"></span></li><li><span style="background-color:#ff4cfd;"></span></li><li><span style="background-color:#fc951e;"></span></li><li><span style="background-color:#932890;"></span></li><li><span style="background-color:#ff2a1a;"></span></li><li><span style="background-color:#fdf935;"></span></li><li><span style="background-color:#ffffff;border: 1px solid #ccc;"></span></li><li><span class="Eleditor-inheritValue" style="background-color:transparent; border: 1px solid #dedede;"></span></li></ul></div><div class="Eleditor-textEditor-fontsizes"><div class="Eleditor-textEditor-modulePane"><span>字体大小</span></div><ul><li class="Eleditor-inheritValue">默认</li><li>14px</li><li>16px</li><li>20px</li><li>28px</li><li>35px</li></ul></div><div class="Eleditor-textEditor-lineheight"><div class="Eleditor-textEditor-modulePane"><span>行高</span></div><ul><li class="Eleditor-inheritValue">默认</li><li>20px</li><li>25px</li><li>30px</li><li>35px</li><li>40px</li></ul></div><div class="Eleditor-inputarea"><input placeholder="请输入超链接" type="text" /><div class="textarea" contenteditable="true"></div></div><div class="Eleditor-method"><button class="Eleditor-commit">提交</button><button class="Eleditor-cancel">取消</button></div></div></div>';
		return _html;
	};

	w[_namespace] = function(){

		console.log('|--Eleditor Initing');

		var _arg = arguments[0];
			_arg.upload = _arg.upload || {};
			_arg.toolbars = _arg.toolbars || [],
			_editorUid = _genEditorUid(),
			_historys = [],
			_placeHolder = _arg.placeHolder || '<p class="Eleditor-placeholder" name="title">点击此处编辑标题</p>';

		if( _arg.toolbars.length === 0 ){
			_arg.toolbars = [
				'insertText',
				'editText',
				'insertImage',
				'insertLink',
				'deleteBefore',
				'deleteAfter',
				'insertHr',
				'deleteThis',
				'undo',
				'cancel',
			];
		}

		if( _arg.el instanceof jQuery ){
			var _$wrap = _arg.el;
		}else{
			var _$wrap = $(_arg.el);
			
			if( _$wrap.length === 0 ){
				return console.warn('|--Eleditor '+_arg.el+'元素不存在，请在DOMContentLoaded后初始化Eleditor');
			}else if( _$wrap.length != 1 ){
				var _$wrap = $(_$wrap[0]);
			}

		}

		if( _$wrap.attr('Eleditor-Inited') === 'true' ){
			return console.warn('|--Eleditor '+_arg.el+'已经绑定了Eleditor');
		}

		_$wrap.attr({'Eleditor-Inited': 'true', 'Eleditor-Uid': _editorUid});

		_correctHtmlStructure(_$wrap, _placeHolder);

		/*insert editor*/
		var _$window = $(w);
		var _$scrollWrap = $('html,body');
		var _$editorWrap = $(_buildEditorModule(_arg.toolbars, _editorUid));
		var _$editorController = _$editorWrap.find('.Eleditor-controller');
		var _$editorLoadingMask = _$editorWrap.find('.Eleditor-loading');
		var _$editorTextModule = _$editorWrap.find('.Eleditor-textEditor');
		var _$editorTextArea = _$editorTextModule.find(".Eleditor-inputarea .textarea");
		var _$editorUploadImageBtn = _$editorController.find(".Eleditor-insertImage");
		var _$editorTextLinkArea = _$editorTextModule.find(".Eleditor-inputarea input");
		var _$editorColorModule = _$editorTextModule.find(".Eleditor-textEditor-colors");
		var _$editorFontsizeModule = _$editorTextModule.find(".Eleditor-textEditor-fontsizes");
		var _$editorUndoBtn = _$editorWrap.find(".Eleditor-undo");
		var _$editorLineheightModule = _$editorTextModule.find(".Eleditor-textEditor-lineheight");

		_$wrap.addClass('Eleditor-area');
		_$wrap.after(_$editorWrap);

		console.log('|--Eleditor Mounted To', _$wrap);


		/*bindEvent*/
		var _$selected = null,
			_uploader = null;
		var _showEditorControllerLayer = function(_$e){
			_$selected = _$e;
			_$e.addClass('Eleditor-active');

			var _calTop = _$e.offset().top + _$e.outerHeight();

			$.each(_$editorController.find('li'), function(i, e){
				var _$e = $(e),
					_tg = _$e.attr('on-tag');
				if( _tg ){
					if( _$selected[0].tagName.toLocaleLowerCase() == _tg ){
						_$e.show();
					}else{
						_$e.hide();
					}
				}
			});

			_$editorController.show();
			_flushEditorControllerLayerPosi();

			if( typeof _$scrollWrap.animate === 'function' ){
				_$scrollWrap.stop().animate({scrollTop: (_calTop - 150) + 'px'}, 500);
			}else{
				_$scrollWrap.scrollTop((_calTop - 150) + 'px');
			}
			
			_uploader && _uploader.refresh();
			document.body.style.overflow='hidden';
		};
		var _flushEditorControllerLayerPosi = function(){
			_$editorController.css({ 
				top: _$selected.offset().top + _$selected.outerHeight(),
				width: _$wrap.width() - 5
			});
		};
		var _hideEditorControllerLayer = function(){
			_$wrap.find('.Eleditor-active').removeClass('Eleditor-active');
			_$editorController.hide();
			document.body.style.overflow='auto';
		};
		var _showEditorWrapMask = function(){
			_$editorController.hide();
			_$editorWrap.addClass('Eleditor-mask');
		};
		var _hideEditorWrapMask = function(){
			_$editorWrap.removeClass('Eleditor-mask');
		};
		var _showLoadingMask = function(_html){
			_showEditorWrapMask();
			_$editorLoadingMask.show();
			_$editorLoadingMask.html('<p>'+_html+'</p>')
		};
		var _appendHistory = function(){
			_historys.push( arguments[0] );
			_flushHistoryBtn();
		}
		var _handleHistory = function(){

			if( _historys.length === 0 ){
				return;
			}

			var _handle = _historys.pop();

			if( _handle.m == 'insertNode' ){
				_handle.node.remove();
			}
			if( _handle.m == 'editNode' ){
				_handle.node.attr('style', _handle.unode.attr('style') || '').html(_handle.unode);
			}
			if( _handle.m == 'deleteNode' ){
				if( _handle.pnode.length > 0 ){
					_handle.pnode.after(_handle.node);
				}else{
					_$wrap.prepend(_handle.node);
				}
				_$wrap.find('.Eleditor-placeholder').remove();
			}
			if( _handle.m == 'deleteBeforeNode' ){
				for (var i = _handle.bnode.length - 1; i >= 0; i--) {
					_handle.node.before(_handle.bnode[i]);
				}
			}
			if( _handle.m == 'deleteAfterNode' ){
				_handle.node.after(_handle.anode);
			}						
			_flushHistoryBtn();

		}
		var _flushHistoryBtn = function(){

			if( _historys.length == 0 ){
				_$editorUndoBtn.hide();
			}else{
				_$editorUndoBtn.show();
			}

		}
		var _hideLoadingMask = function(){
			_hideEditorWrapMask();
			_$editorLoadingMask.hide();
		};
		var _syncRenderTextEditorView = function(){
			_$editorTextModule.attr('role', 'edit').show();
			_$editorTextArea.html(_$selected.html());
			_$editorTextArea.attr('style', _$selected.attr('style'));
			if( _$selected.css('font-weight') == 'bold' ){
				_$editorTextModule.find('.Eleditor-textStyle-bold').addClass('Eleditor-active');
			}
			if( _$selected.css('text-decoration') == 'underline' ){
				_$editorTextModule.find('.Eleditor-textStyle-underline').addClass('Eleditor-active');
			}			
			if( _$selected[0].tagName == 'A' ){
				_$editorTextModule.attr('type', 'link');
				_$editorTextLinkArea.val(_$selected.attr('href'));
			}else{
				_$editorTextModule.attr('type', 'word');
			}

			var _selectAlign = _$selected.css('text-align');
			if( _inArray(_selectAlign, ['left', 'center', 'right']) ){
				_$editorTextModule.find('.Eleditor-textStyle-align[align='+_selectAlign+']').addClass('Eleditor-active');
			}else{
				_$editorTextModule.find('.Eleditor-textStyle-align').removeClass('Eleditor-active');
			}
			_$editorTextModule.find('.Eleditor-textStyle-color span').css('background-color', _$selected.css('color'));
		};

		if( _inArray('insertImage', _arg.toolbars) ){
		if( typeof WebUploader != 'undefined' ){
				_uploader = WebUploader.create({
								auto: true,
							    server: _arg.upload.server || '/upload',
							    pick: _$editorUploadImageBtn,
							    duplicate: true,
							    compress: typeof _arg.upload.compress == 'undefined' ? false : _arg.upload.compress,
							    resize: false,
							    fileSingleSizeLimit: _arg.upload.fileSizeLimit ? _arg.upload.fileSizeLimit*1024*1024 : undefined,
							    accept: {
							        title: 'Images',
							        extensions: 'gif,jpg,jpeg,bmp,png,webp',
							        mimeTypes: 'image/*'
							    }
							});
				_uploader.on( 'uploadStart', function( _file, _percentage ) {
					_hideEditorControllerLayer();
				    _showLoadingMask('上传图片中<span id="uploadProgress">1</span>%');
				});		
				_uploader.on( 'uploadProgress', function( _file, _percentage ) {
				    $('#uploadProgress').html( parseFloat((_percentage * 100).toFixed(2)) );
				});
				_uploader.on( 'error', function() {
				    if ( arguments[0]=="Q_TYPE_DENIED" ){
			            alert("请上传图片格式文件");
			        }else if( arguments[0]=="F_EXCEED_SIZE" ){
			            alert("文件大小不能超过"+(arguments[1] / 1048576)+"M");
			        }
				});
				_uploader.on('uploadComplete',function() {
			        _hideLoadingMask();
				});			
				_uploader.on('uploadSuccess',function(_file,_call){
					alert(_call);
				    if( _call.status == 1 ){
				    	var _$upImg = $('<img width="100%" src="'+_call.url+'">');
						_$selected.after(_$upImg);
						_appendHistory({m:'insertNode',node: _$upImg });
				    }else{
				    	alert(_call.msg);
				    }
				});
		}
		}

		var _editorModuleEvents = {
			insertText: function(){
				_showEditorWrapMask();
				_$editorTextModule.attr({'role': 'insert', 'type': 'word'}).show();
			},
			insertLink: function(){
				_showEditorWrapMask();
				_$editorTextModule.attr({'role': 'insert', 'type': 'link'}).show();
			},
			insertImage: function(){
				if( typeof WebUploader === 'undefined' ){
					alert('图片上传请手动引入插件根目录webuploader.min.js');
				}
			},
			insertHr: function(){
				var _$hr = $('<div class="horizontal-line" style="padding: 10px 0;border-bottom: 1px solid #aaa;margin-bottom: 20px;"></div>');
				_$selected.after(_$hr);
				_appendHistory({ m: 'insertNode', node: _$hr });
				_hideEditorControllerLayer();
			},
			editText: function(){
				if( _inArray(_$selected[0].tagName, _notctname) ){
					return this.insertText();
				}
				_showEditorWrapMask();
				_syncRenderTextEditorView();
			},
			deleteThis: function(){
				_appendHistory({ m: 'deleteNode', node: _$selected, pnode: _$selected.prev() });
				_$selected.remove();
				_hideEditorControllerLayer();
				_correctHtmlStructure(_$wrap, _placeHolder);
			},
			deleteBefore: function(){
                var _$prev = _$selected.prev();
                _appendHistory({ m: 'deleteBeforeNode', node: _$selected, bnode: _$selected.prevAll() });
                var _$prev_prev;
                while (_$prev.length > 0) {
                    _$prev_prev = _$prev.prev();
                    _$prev.remove();
                    _$prev = _$prev_prev
                }
                var _$parent = _$selected.parent();
                while (_$parent.length > 0 && !_$parent.hasClass("Eleditor-area")) {
                    _$prev = _$parent.prev();
                    while (_$prev.length > 0) {
                        _$prev_prev = _$prev.prev();
                        _$prev.remove();
                        _$prev = _$prev_prev
                    }
                    _$parent = _$parent.parent()
                }
                _hideEditorControllerLayer();
                _correctHtmlStructure(_$wrap, _placeHolder);
			},
			deleteAfter: function(){
                var _$next = _$selected.next();
                _appendHistory({ m: 'deleteAfterNode', node: _$selected, anode: _$selected.nextAll() });
                var _$next_next;
                while (_$next.length > 0) {
                    _$next_next = _$next.next();
                    _$next.remove();
                    _$next = _$next_next
                }
                var _$parent = _$selected.parent();
                while (_$parent.length > 0 && !_$parent.hasClass("Eleditor-area")) {
                    _$next = _$parent.next();
                    while (_$next.length > 0) {
                        _$next_next = _$next.next();
                        _$next.remove();
                        _$next = _$next_next
                    }
                    _$parent = _$parent.parent()
                }
                _hideEditorControllerLayer();
                _correctHtmlStructure(_$wrap, _placeHolder);
			},
			undo: function(){
				_handleHistory();
				_hideEditorControllerLayer();
			},
			cancel: function(){
				_hideEditorControllerLayer();
			}
		};

		for (var i = 0; i < _arg.toolbars.length; i++) {
			if( typeof _arg.toolbars[i] === 'object' ){
				_editorModuleEvents[_arg.toolbars[i].id] = _arg.toolbars[i].handle;
			}
		};

		/*text area click*/
		_$window.on('resize', function(){
			_flushEditorControllerLayerPosi();
		});

		_$editorController.on('click', 'ul li', function() {
			var _$this = $(this),
				_event = _$this.attr('event');
			if( typeof _editorModuleEvents[_event] === 'function' ){
				if( typeof _toolnames[_event] != 'undefined' ){
					_editorModuleEvents[_event]();
				}else{
					_editorModuleEvents[_event](_$selected, _$this) !== false && _editorModuleEvents.cancel();
				}
			}
		});

		/*textEditor*/
		_$editorTextModule.on('click', '.Eleditor-textStyle-bold', function() {
			_$editorTextArea.css("font-weight", $(this).hasClass("Eleditor-active") ? "normal" : "bold");
            $(this).toggleClass("Eleditor-active");
		});
		_$editorTextModule.on('click', '.Eleditor-textStyle-underline', function() {
			_$editorTextArea.css("text-decoration", $(this).hasClass("Eleditor-active") ? "none" : "underline");
            $(this).toggleClass("Eleditor-active");
		});		
		_$editorTextModule.on('click', '.Eleditor-textStyle-color,.Eleditor-textStyle-bgColor', function() {
			var _$this = $(this);
			var _role = _$this.hasClass('Eleditor-textStyle-bgColor') ? 'bgcolor' : 'color';
			_$editorColorModule.find('.Eleditor-textEditor-modulePane span').html(_role == 'bgcolor' ? '文字背景颜色' : '文字颜色');
			_$editorColorModule.attr('role', _role).show();
			$(this).addClass('Eleditor-active');
		});
		_$editorTextModule.on('click', '.Eleditor-textStyle-fontSize', function() {
			_$editorFontsizeModule.show();
			$(this).addClass('Eleditor-active');
		});	
		_$editorTextModule.on('click', '.Eleditor-textStyle-lineHeight', function() {
			_$editorLineheightModule.show();
			$(this).addClass('Eleditor-active');
		});		
		_$editorLineheightModule.on('click', 'ul li', function() {
			if( !$(this).hasClass('Eleditor-inheritValue') ){
				_$editorTextArea.css("line-height", $(this).html());
			}else{
				_$editorTextArea.css("line-height", 'inherit');
				_$editorTextModule.find('.Eleditor-textStyle-lineHeight').removeClass('Eleditor-active');
			}
			_$editorLineheightModule.hide();
		});				
		_$editorFontsizeModule.on('click', 'ul li', function() {
			if( !$(this).hasClass('Eleditor-inheritValue') ){
				_$editorTextArea.css("font-size", $(this).html());
			}else{
				_$editorTextArea.css("font-size", 'inherit');
				_$editorTextModule.find('.Eleditor-textStyle-fontSize').removeClass('Eleditor-active');
			}
			_$editorFontsizeModule.hide();
		});

		_$editorTextModule.on('click', ".Eleditor-textStyle-align", function() {
			var _align = $(this).attr('align');
			_$editorTextArea.css({"text-align": _align, "display": 'block'});
			_$editorTextModule.find(".Eleditor-textStyle-align.Eleditor-active").removeClass('Eleditor-active');
			$(this).addClass('Eleditor-active');
		});	

		_$editorTextModule.on('click', ".Eleditor-textEditor-clean", function() {
			_$editorTextArea.html("");
		});

		_$editorTextModule.on('click', ".Eleditor-cancel,.Eleditor-commit", function() {

			if( $(this).hasClass('Eleditor-commit') ){
				var _style = _$editorTextArea.attr('style') || '';
				var _content = _$editorTextArea.html();
				var _unode = _$selected.clone();
				if( !_content ){
					return alert('请输入内容文字');
				}

				if( _$editorTextModule.attr('role') == 'edit' || _$selected.hasClass('Eleditor-placeholder') ){
					if( _$editorTextModule.attr('type') == 'link' ){
						var _link = _$editorTextLinkArea.val();
						_$selected.attr('href', _link);
					}
					_$selected.attr('style', _style).removeClass('Eleditor-placeholder').html( _content );
					
					_appendHistory({ m: 'editNode', node: _$selected, unode: _unode });
					_flushEditorControllerLayerPosi();
				}else{
					var _buildWordHtml = '';
					if( _$editorTextModule.attr('type') == 'link' ){
						var _link = _$editorTextLinkArea.val();
						_buildWordHtml = '<a target="_BLANK" style="'+_style+'" href="'+_link+'">'+_content+'</a>';
					}else{
						_buildWordHtml = '<p style="'+_style+'">'+_content+"</p>";
					}
					var _buildWordHtml = $(_buildWordHtml);
					_$selected.after(_buildWordHtml);
					_flushEditorControllerLayerPosi();
					_appendHistory({ m: 'insertNode', node: _buildWordHtml });
				}
			}

			_$editorTextModule.find('.Eleditor-active').removeClass('Eleditor-active');
			_$editorTextModule.find('.Eleditor-textStyle-color span').removeAttr('style');
			_$editorTextArea.removeAttr('style').html('');
			_$editorTextLinkArea.val('');
			_hideEditorWrapMask();
			_$editorTextModule.hide();
			_hideEditorControllerLayer();
		});	
			
		_$editorColorModule.on('click', 'ul li span', function() {
			var _color = $(this).css('background-color');
			if( _$editorColorModule.attr('role') == 'color' ){
				if( !$(this).hasClass('Eleditor-inheritValue') ){
					_$editorTextArea.css("color", _color);
					_$editorTextModule.find('.Eleditor-textStyle-color span').css("background-color", _color);
				}else{
					_$editorTextArea.css("color", 'inherit');
					_$editorTextModule.find('.Eleditor-textStyle-color').removeClass('Eleditor-active').find('span').removeAttr('style');
				}
			}else{
				if( !$(this).hasClass('Eleditor-inheritValue') ){
					_$editorTextArea.css("background-color", _color);
				}else{
					_$editorTextArea.css("background-color", 'inherit');
					_$editorTextModule.find('.Eleditor-textStyle-bgColor').removeClass('Eleditor-active');
				}
			}
			_$editorColorModule.hide();
		});

		/*controller*/
        _$wrap.on('click', '*', function(_e) {

			var _$this = $(this);

            if( !_$this.hasClass('Eleditor-active') ){
            	_hideEditorControllerLayer();
            	_showEditorControllerLayer(_$this);
            }

            return _e.preventDefault() == 0;
        });


		return {

			append: function(){
				return _$wrap.append(arguments[0]);
			},
			getContent: function(){
				return _$wrap.html();
			},
			getContentText: function(){
				return _formatInnerText(_$wrap.text());
			},
			destory: function(){
				_$wrap.removeAttr('Eleditor-Inited Eleditor-Uid');
				_$wrap.removeClass('Eleditor-area');
				_$wrap.off().find('.Eleditor-active').removeClass('Eleditor-active');
				_$editorWrap.find('*').off();
				_$editorWrap.remove();
				console.log('|--Eleditor '+_editorUid+' destoryed');
			}

=======
		var _html = '<div class="Eleditor-wrap" style="z-index:'+_getLayerMaxZIndex()+'" id="'+_uid+'"><div class="Eleditor-controller"><ul style="display:table;margin:0 auto;text-align:center;padding:0;">';

		for (var i = 0; i < _toolbars.length; i++) {
			var _it = _toolbars[i],
				_id = typeof _it === 'object' ? _it.id : _it,
				_tag = (typeof _it === 'object' && _it.tag) ? _it.tag.toLocaleLowerCase() : null,
				_name = typeof _it === 'object' ? _it.name : _toolnames[_it];
			_html += '<li event="'+_id+'" '+(_tag ? 'on-tag="'+_tag+'"' : '')+' class="Eleditor-'+_id+'">'+_name+'</li>';
		}

		_html += '</ul></div><div class="Eleditor-loading"><p></p></div><div class="Eleditor-textEditor"><div class="Eleditor-textStyle"><ul><li class="Eleditor-textStyle-bold"></li><li class="Eleditor-textStyle-color"><span></span></li><li class="Eleditor-textStyle-underline"><span></span></li><li class="Eleditor-textStyle-bgColor"></li><li class="Eleditor-textStyle-fontSize"></li><li class="Eleditor-textStyle-lineHeight"></li><li class="Eleditor-textStyle-align Eleditor-textStyle-alignLeft" align="left"></li><li class="Eleditor-textStyle-align Eleditor-textStyle-alignCenter" align="center"></li><li class="Eleditor-textStyle-align Eleditor-textStyle-alignRight" align="right"></li></ul><button class="Eleditor-textEditor-clean">清空</button></div><div class="Eleditor-textEditor-colors"><div class="Eleditor-textEditor-modulePane"><span></span></div><ul><li><span style="background-color:#000000;"></span></li><li><span style="background-color:#0f3efe;"></span></li><li><span style="background-color:#a97a46;"></span></li><li><span style="background-color:#03fcfe;"></span></li><li><span style="background-color:#00f72c;"></span></li><li><span style="background-color:#ff4cfd;"></span></li><li><span style="background-color:#fc951e;"></span></li><li><span style="background-color:#932890;"></span></li><li><span style="background-color:#ff2a1a;"></span></li><li><span style="background-color:#fdf935;"></span></li><li><span style="background-color:#ffffff;border: 1px solid #ccc;"></span></li><li><span class="Eleditor-inheritValue" style="background-color:transparent; border: 1px solid #dedede;"></span></li></ul></div><div class="Eleditor-textEditor-fontsizes"><div class="Eleditor-textEditor-modulePane"><span>字体大小</span></div><ul><li class="Eleditor-inheritValue">默认</li><li>14px</li><li>16px</li><li>20px</li><li>28px</li><li>35px</li></ul></div><div class="Eleditor-textEditor-lineheight"><div class="Eleditor-textEditor-modulePane"><span>行高</span></div><ul><li class="Eleditor-inheritValue">默认</li><li>20px</li><li>25px</li><li>30px</li><li>35px</li><li>40px</li></ul></div><div class="Eleditor-inputarea"><input placeholder="请输入超链接" type="text" /><div class="textarea" contenteditable="true"></div></div><div class="Eleditor-method"><button class="Eleditor-commit">提交</button><button class="Eleditor-cancel">取消</button></div></div></div>';
		return _html;
	};

	w[_namespace] = function(){

		console.log('|--Eleditor Initing');

		var _arg = arguments[0];
			_arg.upload = _arg.upload || {};
			_arg.toolbars = _arg.toolbars || [],
			_editorUid = _genEditorUid(),
			_historys = [],
			_placeHolder = _arg.placeHolder || '<p class="Eleditor-placeholder" name="title">点击此处编辑标题</p>';

		if( _arg.toolbars.length === 0 ){
			_arg.toolbars = [
				'insertText',
				'editText',
				'insertImage',
				'insertLink',
				'deleteBefore',
				'deleteAfter',
				'insertHr',
				'deleteThis',
				'undo',
				'cancel',
			];
		}

		if( _arg.el instanceof jQuery ){
			var _$wrap = _arg.el;
		}else{
			var _$wrap = $(_arg.el);
			
			if( _$wrap.length === 0 ){
				return console.warn('|--Eleditor '+_arg.el+'元素不存在，请在DOMContentLoaded后初始化Eleditor');
			}else if( _$wrap.length != 1 ){
				var _$wrap = $(_$wrap[0]);
			}

		}

		if( _$wrap.attr('Eleditor-Inited') === 'true' ){
			return console.warn('|--Eleditor '+_arg.el+'已经绑定了Eleditor');
		}

		_$wrap.attr({'Eleditor-Inited': 'true', 'Eleditor-Uid': _editorUid});

		_correctHtmlStructure(_$wrap, _placeHolder);

		/*insert editor*/
		var _$window = $(w);
		var _$scrollWrap = $('html,body');
		var _$editorWrap = $(_buildEditorModule(_arg.toolbars, _editorUid));
		var _$editorController = _$editorWrap.find('.Eleditor-controller');
		var _$editorLoadingMask = _$editorWrap.find('.Eleditor-loading');
		var _$editorTextModule = _$editorWrap.find('.Eleditor-textEditor');
		var _$editorTextArea = _$editorTextModule.find(".Eleditor-inputarea .textarea");
		var _$editorUploadImageBtn = _$editorController.find(".Eleditor-insertImage");
		var _$editorTextLinkArea = _$editorTextModule.find(".Eleditor-inputarea input");
		var _$editorColorModule = _$editorTextModule.find(".Eleditor-textEditor-colors");
		var _$editorFontsizeModule = _$editorTextModule.find(".Eleditor-textEditor-fontsizes");
		var _$editorUndoBtn = _$editorWrap.find(".Eleditor-undo");
		var _$editorLineheightModule = _$editorTextModule.find(".Eleditor-textEditor-lineheight");

		_$wrap.addClass('Eleditor-area');
		_$wrap.after(_$editorWrap);

		console.log('|--Eleditor Mounted To', _$wrap);


		/*bindEvent*/
		var _$selected = null,
			_uploader = null;
		var _showEditorControllerLayer = function(_$e){
			_$selected = _$e;
			_$e.addClass('Eleditor-active');

			var _calTop = _$e.offset().top + _$e.outerHeight();

			$.each(_$editorController.find('li'), function(i, e){
				var _$e = $(e),
					_tg = _$e.attr('on-tag');
				if( _tg ){
					if( _$selected[0].tagName.toLocaleLowerCase() == _tg ){
						_$e.show();
					}else{
						_$e.hide();
					}
				}
			});

			_$editorController.show();
			_flushEditorControllerLayerPosi();

			if( typeof _$scrollWrap.animate === 'function' ){
				_$scrollWrap.stop().animate({scrollTop: (_calTop - 150) + 'px'}, 500);
			}else{
				_$scrollWrap.scrollTop((_calTop - 150) + 'px');
			}
			
			_uploader && _uploader.refresh();
			document.body.style.overflow='hidden';
		};
		var _flushEditorControllerLayerPosi = function(){
			_$editorController.css({ 
				top: _$selected.offset().top + _$selected.outerHeight(),
				width: _$wrap.width() - 5
			});
		};
		var _hideEditorControllerLayer = function(){
			_$wrap.find('.Eleditor-active').removeClass('Eleditor-active');
			_$editorController.hide();
			document.body.style.overflow='auto';
		};
		var _showEditorWrapMask = function(){
			_$editorController.hide();
			_$editorWrap.addClass('Eleditor-mask');
		};
		var _hideEditorWrapMask = function(){
			_$editorWrap.removeClass('Eleditor-mask');
		};
		var _showLoadingMask = function(_html){
			_showEditorWrapMask();
			_$editorLoadingMask.show();
			_$editorLoadingMask.html('<p>'+_html+'</p>')
		};
		var _appendHistory = function(){
			_historys.push( arguments[0] );
			_flushHistoryBtn();
		}
		var _handleHistory = function(){

			if( _historys.length === 0 ){
				return;
			}

			var _handle = _historys.pop();

			if( _handle.m == 'insertNode' ){
				_handle.node.remove();
			}
			if( _handle.m == 'editNode' ){
				_handle.node.attr('style', _handle.unode.attr('style') || '').html(_handle.unode);
			}
			if( _handle.m == 'deleteNode' ){
				if( _handle.pnode.length > 0 ){
					_handle.pnode.after(_handle.node);
				}else{
					_$wrap.prepend(_handle.node);
				}
				_$wrap.find('.Eleditor-placeholder').remove();
			}
			if( _handle.m == 'deleteBeforeNode' ){
				for (var i = _handle.bnode.length - 1; i >= 0; i--) {
					_handle.node.before(_handle.bnode[i]);
				}
			}
			if( _handle.m == 'deleteAfterNode' ){
				_handle.node.after(_handle.anode);
			}						
			_flushHistoryBtn();

		}
		var _flushHistoryBtn = function(){

			if( _historys.length == 0 ){
				_$editorUndoBtn.hide();
			}else{
				_$editorUndoBtn.show();
			}

		}
		var _hideLoadingMask = function(){
			_hideEditorWrapMask();
			_$editorLoadingMask.hide();
		};
		var _syncRenderTextEditorView = function(){
			_$editorTextModule.attr('role', 'edit').show();
			_$editorTextArea.html(_$selected.html());
			_$editorTextArea.attr('style', _$selected.attr('style'));
			if( _$selected.css('font-weight') == 'bold' ){
				_$editorTextModule.find('.Eleditor-textStyle-bold').addClass('Eleditor-active');
			}
			if( _$selected.css('text-decoration') == 'underline' ){
				_$editorTextModule.find('.Eleditor-textStyle-underline').addClass('Eleditor-active');
			}			
			if( _$selected[0].tagName == 'A' ){
				_$editorTextModule.attr('type', 'link');
				_$editorTextLinkArea.val(_$selected.attr('href'));
			}else{
				_$editorTextModule.attr('type', 'word');
			}

			var _selectAlign = _$selected.css('text-align');
			if( _inArray(_selectAlign, ['left', 'center', 'right']) ){
				_$editorTextModule.find('.Eleditor-textStyle-align[align='+_selectAlign+']').addClass('Eleditor-active');
			}else{
				_$editorTextModule.find('.Eleditor-textStyle-align').removeClass('Eleditor-active');
			}
			_$editorTextModule.find('.Eleditor-textStyle-color span').css('background-color', _$selected.css('color'));
		};

		if( _inArray('insertImage', _arg.toolbars)){
		if( typeof WebUploader != 'undefined' ){
				_uploader = WebUploader.create({
								auto: true,
							    server: _arg.upload.server || '/upload',
							    pick: _$editorUploadImageBtn,
							    duplicate: true,
							    compress: typeof _arg.upload.compress == 'undefined' ? false : _arg.upload.compress,
							    resize: false,
							    fileSingleSizeLimit: _arg.upload.fileSizeLimit ? _arg.upload.fileSizeLimit*1024*1024 : undefined,
							    accept: {
							        title: 'Images',
							        extensions: 'gif,jpg,jpeg,bmp,png,webp',
							        mimeTypes: 'image/*'
							    }
							});
				_uploader.on( 'uploadStart', function( _file, _percentage ) {
					_hideEditorControllerLayer();
				    _showLoadingMask('上传图片中<span id="uploadProgress">1</span>%');
				});		
				_uploader.on( 'uploadProgress', function( _file, _percentage ) {
				    $('#uploadProgress').html( parseFloat((_percentage * 100).toFixed(2)) );
				});
				_uploader.on('error',function(){
				    if (arguments[0]=="Q_TYPE_DENIED"){
			            alert("请上传图片格式文件");
			        }else if( arguments[0]=="F_EXCEED_SIZE" ){
			            alert("文件大小不能超过"+(arguments[1] / 1048576)+"M");
			        }
				});
				_uploader.on('uploadComplete',function(){
			        _hideLoadingMask();
				});			
				_uploader.on('uploadSuccess',function(_file,_call){
				    if(_call.status == 1){
				    	var _$upImg = $('<img width="90%" src="'+_call.url+'">');
						_$selected.after(_$upImg);
						_appendHistory({m:'insertNode',node: _$upImg});
				    }
				});
		}
		}

		var _editorModuleEvents = {
			insertText: function(){
				_showEditorWrapMask();
				_$editorTextModule.attr({'role': 'insert', 'type': 'word'}).show();
			},
			insertLink: function(){
				_showEditorWrapMask();
				_$editorTextModule.attr({'role': 'insert', 'type': 'link'}).show();
			},
			insertImage: function(){
				if( typeof WebUploader === 'undefined' ){
					alert('图片上传请手动引入插件根目录webuploader.min.js');
				}
			},
			insertHr: function(){
				var _$hr = $('<div class="horizontal-line" style="padding: 10px 0;border-bottom: 1px solid #aaa;margin-bottom: 20px;"></div>');
				_$selected.after(_$hr);
				_appendHistory({ m: 'insertNode', node: _$hr });
				_hideEditorControllerLayer();
			},
			editText: function(){
				if( _inArray(_$selected[0].tagName, _notctname) ){
					return this.insertText();
				}
				_showEditorWrapMask();
				_syncRenderTextEditorView();
			},
			deleteThis: function(){
				_appendHistory({ m: 'deleteNode', node: _$selected, pnode: _$selected.prev() });
				_$selected.remove();
				_hideEditorControllerLayer();
				_correctHtmlStructure(_$wrap, _placeHolder);
			},
			deleteBefore: function(){
                var _$prev = _$selected.prev();
                _appendHistory({ m: 'deleteBeforeNode', node: _$selected, bnode: _$selected.prevAll() });
                var _$prev_prev;
                while (_$prev.length > 0) {
                    _$prev_prev = _$prev.prev();
                    _$prev.remove();
                    _$prev = _$prev_prev
                }
                var _$parent = _$selected.parent();
                while (_$parent.length > 0 && !_$parent.hasClass("Eleditor-area")) {
                    _$prev = _$parent.prev();
                    while (_$prev.length > 0) {
                        _$prev_prev = _$prev.prev();
                        _$prev.remove();
                        _$prev = _$prev_prev
                    }
                    _$parent = _$parent.parent()
                }
                _hideEditorControllerLayer();
                _correctHtmlStructure(_$wrap, _placeHolder);
			},
			deleteAfter: function(){
                var _$next = _$selected.next();
                _appendHistory({ m: 'deleteAfterNode', node: _$selected, anode: _$selected.nextAll() });
                var _$next_next;
                while (_$next.length > 0) {
                    _$next_next = _$next.next();
                    _$next.remove();
                    _$next = _$next_next
                }
                var _$parent = _$selected.parent();
                while (_$parent.length > 0 && !_$parent.hasClass("Eleditor-area")) {
                    _$next = _$parent.next();
                    while (_$next.length > 0) {
                        _$next_next = _$next.next();
                        _$next.remove();
                        _$next = _$next_next
                    }
                    _$parent = _$parent.parent()
                }
                _hideEditorControllerLayer();
                _correctHtmlStructure(_$wrap, _placeHolder);
			},
			undo: function(){
				_handleHistory();
				_hideEditorControllerLayer();
			},
			cancel: function(){
				_hideEditorControllerLayer();
			}
		};

		for (var i = 0; i < _arg.toolbars.length; i++) {
			if( typeof _arg.toolbars[i] === 'object' ){
				_editorModuleEvents[_arg.toolbars[i].id] = _arg.toolbars[i].handle;
			}
		};

		/*text area click*/
		_$window.on('resize', function(){
			_flushEditorControllerLayerPosi();
		});

		_$editorController.on('click', 'ul li', function() {
			var _$this = $(this),
				_event = _$this.attr('event');
			if( typeof _editorModuleEvents[_event] === 'function' ){
				if( typeof _toolnames[_event] != 'undefined' ){
					_editorModuleEvents[_event]();
				}else{
					_editorModuleEvents[_event](_$selected, _$this) !== false && _editorModuleEvents.cancel();
				}
			}
		});

		/*textEditor*/
		_$editorTextModule.on('click', '.Eleditor-textStyle-bold', function() {
			_$editorTextArea.css("font-weight", $(this).hasClass("Eleditor-active") ? "normal" : "bold");
            $(this).toggleClass("Eleditor-active");
		});
		_$editorTextModule.on('click', '.Eleditor-textStyle-underline', function() {
			_$editorTextArea.css("text-decoration", $(this).hasClass("Eleditor-active") ? "none" : "underline");
            $(this).toggleClass("Eleditor-active");
		});		
		_$editorTextModule.on('click', '.Eleditor-textStyle-color,.Eleditor-textStyle-bgColor', function() {
			var _$this = $(this);
			var _role = _$this.hasClass('Eleditor-textStyle-bgColor') ? 'bgcolor' : 'color';
			_$editorColorModule.find('.Eleditor-textEditor-modulePane span').html(_role == 'bgcolor' ? '文字背景颜色' : '文字颜色');
			_$editorColorModule.attr('role', _role).show();
			$(this).addClass('Eleditor-active');
		});
		_$editorTextModule.on('click', '.Eleditor-textStyle-fontSize', function() {
			_$editorFontsizeModule.show();
			$(this).addClass('Eleditor-active');
		});	
		_$editorTextModule.on('click', '.Eleditor-textStyle-lineHeight', function() {
			_$editorLineheightModule.show();
			$(this).addClass('Eleditor-active');
		});		
		_$editorLineheightModule.on('click', 'ul li', function() {
			if( !$(this).hasClass('Eleditor-inheritValue') ){
				_$editorTextArea.css("line-height", $(this).html());
			}else{
				_$editorTextArea.css("line-height", 'inherit');
				_$editorTextModule.find('.Eleditor-textStyle-lineHeight').removeClass('Eleditor-active');
			}
			_$editorLineheightModule.hide();
		});				
		_$editorFontsizeModule.on('click', 'ul li', function() {
			if( !$(this).hasClass('Eleditor-inheritValue') ){
				_$editorTextArea.css("font-size", $(this).html());
			}else{
				_$editorTextArea.css("font-size", 'inherit');
				_$editorTextModule.find('.Eleditor-textStyle-fontSize').removeClass('Eleditor-active');
			}
			_$editorFontsizeModule.hide();
		});

		_$editorTextModule.on('click', ".Eleditor-textStyle-align", function() {
			var _align = $(this).attr('align');
			_$editorTextArea.css({"text-align": _align, "display": 'block'});
			_$editorTextModule.find(".Eleditor-textStyle-align.Eleditor-active").removeClass('Eleditor-active');
			$(this).addClass('Eleditor-active');
		});	

		_$editorTextModule.on('click', ".Eleditor-textEditor-clean", function() {
			_$editorTextArea.html("");
		});

		_$editorTextModule.on('click', ".Eleditor-cancel,.Eleditor-commit", function() {

			if( $(this).hasClass('Eleditor-commit') ){
				var _style = _$editorTextArea.attr('style') || '';
				var _content = _$editorTextArea.html();
				var _unode = _$selected.clone();
				if( !_content ){
					return alert('请输入内容文字');
				}

				if( _$editorTextModule.attr('role') == 'edit' || _$selected.hasClass('Eleditor-placeholder') ){
					if( _$editorTextModule.attr('type') == 'link' ){
						var _link = _$editorTextLinkArea.val();
						_$selected.attr('href', _link);
					}
					_$selected.attr('style', _style).removeClass('Eleditor-placeholder').html( _content );
					
					_appendHistory({ m: 'editNode', node: _$selected, unode: _unode });
					_flushEditorControllerLayerPosi();
				}else{
					var _buildWordHtml = '';
					if( _$editorTextModule.attr('type') == 'link' ){
						var _link = _$editorTextLinkArea.val();
						_buildWordHtml = '<a target="_BLANK" style="'+_style+'" href="'+_link+'">'+_content+'</a>';
					}else{
						_buildWordHtml = '<p style="'+_style+'">'+_content+"</p>";
					}
					var _buildWordHtml = $(_buildWordHtml);
					_$selected.after(_buildWordHtml);
					_flushEditorControllerLayerPosi();
					_appendHistory({ m: 'insertNode', node: _buildWordHtml });
				}
			}

			_$editorTextModule.find('.Eleditor-active').removeClass('Eleditor-active');
			_$editorTextModule.find('.Eleditor-textStyle-color span').removeAttr('style');
			_$editorTextArea.removeAttr('style').html('');
			_$editorTextLinkArea.val('');
			_hideEditorWrapMask();
			_$editorTextModule.hide();
			_hideEditorControllerLayer();
		});	
			
		_$editorColorModule.on('click', 'ul li span', function() {
			var _color = $(this).css('background-color');
			if( _$editorColorModule.attr('role') == 'color' ){
				if( !$(this).hasClass('Eleditor-inheritValue') ){
					_$editorTextArea.css("color", _color);
					_$editorTextModule.find('.Eleditor-textStyle-color span').css("background-color", _color);
				}else{
					_$editorTextArea.css("color", 'inherit');
					_$editorTextModule.find('.Eleditor-textStyle-color').removeClass('Eleditor-active').find('span').removeAttr('style');
				}
			}else{
				if( !$(this).hasClass('Eleditor-inheritValue') ){
					_$editorTextArea.css("background-color", _color);
				}else{
					_$editorTextArea.css("background-color", 'inherit');
					_$editorTextModule.find('.Eleditor-textStyle-bgColor').removeClass('Eleditor-active');
				}
			}
			_$editorColorModule.hide();
		});

		/*controller*/
        _$wrap.on('click', '*', function(_e) {

			var _$this = $(this);

            if( !_$this.hasClass('Eleditor-active') ){
            	_hideEditorControllerLayer();
            	_showEditorControllerLayer(_$this);
            }

            return _e.preventDefault() == 0;
        });


		return {

			append: function(){
				return _$wrap.append(arguments[0]);
			},
			getContent: function(){
				return _$wrap.html();
			},
			getContentText: function(){
				return _formatInnerText(_$wrap.text());
			},
			destory: function(){
				_$wrap.removeAttr('Eleditor-Inited Eleditor-Uid');
				_$wrap.removeClass('Eleditor-area');
				_$wrap.off().find('.Eleditor-active').removeClass('Eleditor-active');
				_$editorWrap.find('*').off();
				_$editorWrap.remove();
				console.log('|--Eleditor '+_editorUid+' destoryed');
			}
>>>>>>> branch 'master' of https://github.com/leierzhan/Delicious.git
		}
	}

})(window);
