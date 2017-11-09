(function(){

<<<<<<< HEAD
	//µ¯´°¹«¹²²¿·Öjs£¬ËùÓÐµ¯´°ÒÑ¾­Ð´ºÃ£¬µ÷ÓÃÊ±°´ÕÕÒ³ÃæÖÐ×¢ÊÍ·½·¨Ê¹ÓÃ¼´¿É
	var $oMasking;
	var $oWindowContainer;
	//´ò¿ªµ¯´°·½·¨
	$.fn.openWindow = function(setTitle,setContents,setButton){
		
		//Æ´½Óµ¯´°ÄÚÈÝ£¬²¢ÇÒÔÚµ÷ÓÃ´ò¿ªµ¯´°·½·¨Ê±½«ÄÚÈÝÈû½øbody
		var _html ='<div class="window-masking"></div>'+
		'<div class="window-container fix" id="addNew">'+
			'<h2></h2>'+
			'<div class="window-content">'+
				'<p class="window-text"></p>'+
			'</div>'+
			'<div class="window-btn fix">'+
				'<a class="cancel-button fl" href="javascript:;"></a>'+
				'<a class="confirm-button fr" href="javascript:;"></a>'+
				'<a class="ack-button fr" href="javascript:;"></a>'+
			'</div>'+
		'</div>'; 
		//½«Æ´½ÓºÃµÄhtmlÈû½øbodyÀïÃæ
		$('body').append(_html);
		$oMasking = $('.window-masking');
		$oWindowContainer = $('.window-container');
		//µã»÷È¡Ïû°´Å¥¹Ø±Õµ¯´°
		$('.cancel-button,.window-masking,.ack-button').on('click',function(){
			closeWindow();
		});
		$('.confirm-button').on('click',function(){
			setContents=setContents.substring(3,setContents.length);
			alert(setContents);
			window.location.href = "tel:"+setContents;
		});
		//ÉèÖÃÃÉ°æÕ¹Ê¾
		modal = new Modal();
		console.log(setButton+","+setContents+","+setButton)
		modal.setTitle(setTitle);
		modal.setContents(setContents);
				//ÉèÖÃ°´Å¥¸öÊýºÍÁ´½Ó
		modal.setButton(setButton);
		$oMasking.show();
		//ÉèÖÃµ¯´°Ãæ°åÕ¹Ê¾
		$oWindowContainer.show();
	}
	//¹Ø±Õµ¯´°·½·¨
	function closeWindow(){
		$oMasking = $('.window-masking');
		$oWindowContainer = $('.window-container');
		//¹Ø±Õµ¯´°µÄÊ±ºò½«ÃÉ°æºÍhtml´ÓÒ³ÃæÖÐÒÆ³ýµô
		$oMasking.remove();
		$oWindowContainer.remove();
	}
	//³õÊ¼»¯
	var Modal = function () {
	    thismodal = $('#addNew');
	};
	//ÐÞ¸ÄÄÚÈÝ·½·¨
	Modal.prototype = {
		setContents:function(obj){
			//ÕÒµ½ÐèÒªÐÞ¸ÄÄÚÈÝµÄ±êÇ©p£¬»ñÈ¡µ÷ÓÃÖÐÉèÖÃµÄÌáÊ¾Óï
	    	thismodal.find('p.window-text').html(obj);   
		},
		setTitle:function(obj){
			//ÕÒµ½ÐèÒªÐÞ¸ÄµÄµ¯´°±êÌâ£¬»ñÈ¡µ÷ÓÃÖÐÉèÖÃµÄµ¯´°±êÌâ
			if(obj!=""){
				thismodal.find('h2').show().html(obj); 
			}
	    	
		},
		setButton: function (obj){
			console.log(obj)
		    //½âÎö´«¹ýÀ´µÄ²ÎÊýjson
		    var json=eval(obj);
		   
		    
		    if(json.length==1){
		    	//Ò»¸ö°´Å¥
		    	thismodal.find('a.ack-button').show().html(json[0]);
		    }
		    if(json.length==2){
		    	//Á½¸ö°´Å¥
=======
	//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½jsï¿½ï¿½ï¿½ï¿½ï¿½Ðµï¿½ï¿½ï¿½ï¿½Ñ¾ï¿½Ð´ï¿½Ã£ï¿½ï¿½ï¿½ï¿½ï¿½Ê±ï¿½ï¿½ï¿½ï¿½Ò³ï¿½ï¿½ï¿½ï¿½×¢ï¿½Í·ï¿½ï¿½ï¿½Ê¹ï¿½Ã¼ï¿½ï¿½ï¿½
	var $oMasking;
	var $oWindowContainer;
	//ï¿½ò¿ªµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	$.fn.openWindow = function(setTitle,setContents,setButton){
		
		//Æ´ï¿½Óµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ý£ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Úµï¿½ï¿½Ã´ò¿ªµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ê±ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½body
		var _html ='<div class="window-masking"></div>'+
		'<div class="window-container fix" id="addNew">'+
			'<h2></h2>'+
			'<div class="window-content">'+
				'<p class="window-text"></p>'+
			'</div>'+
			'<div class="window-btn fix">'+
				'<a class="cancel-button fl" href="javascript:;"></a>'+
				'<a class="confirm-button fr" href="javascript:;"></a>'+
				'<a class="ack-button fr" href="javascript:;"></a>'+
			'</div>'+
		'</div>'; 
		//ï¿½ï¿½Æ´ï¿½ÓºÃµï¿½htmlï¿½ï¿½ï¿½bodyï¿½ï¿½ï¿½ï¿½
		$('body').append(_html);
		$oMasking = $('.window-masking');
		$oWindowContainer = $('.window-container');
		//ï¿½ï¿½ï¿½È¡ï¿½ï¿½Å¥ï¿½Ø±Õµï¿½ï¿½ï¿½
		$('.cancel-button,.window-masking,.ack-button').on('click',function(){
			closeWindow();
		});
		$('.confirm-button').on('click',function(){
			setContents=setContents.substring(3,setContents.length);
			window.location.href = "tel:"+setContents;
		});
		//ï¿½ï¿½ï¿½ï¿½ï¿½É°ï¿½Õ¹Ê¾
		modal = new Modal();
		console.log(setButton+","+setContents+","+setButton)
		modal.setTitle(setTitle);
		modal.setContents(setContents);
				//ï¿½ï¿½ï¿½Ã°ï¿½Å¥ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
		modal.setButton(setButton);
		$oMasking.show();
		//ï¿½ï¿½ï¿½Ãµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Õ¹Ê¾
		$oWindowContainer.show();
	}
	//ï¿½Ø±Õµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	function closeWindow(){
		$oMasking = $('.window-masking');
		$oWindowContainer = $('.window-container');
		//ï¿½Ø±Õµï¿½ï¿½ï¿½ï¿½ï¿½Ê±ï¿½ï¿½ï¿½É°ï¿½ï¿½htmlï¿½ï¿½Ò³ï¿½ï¿½ï¿½ï¿½ï¿½Æ³ï¿½ï¿½
		$oMasking.remove();
		$oWindowContainer.remove();
	}
	//ï¿½ï¿½Ê¼ï¿½ï¿½
	var Modal = function () {
	    thismodal = $('#addNew');
	};
	//ï¿½Þ¸ï¿½ï¿½ï¿½ï¿½Ý·ï¿½ï¿½ï¿½
	Modal.prototype = {
		setContents:function(obj){
			//ï¿½Òµï¿½ï¿½ï¿½Òªï¿½Þ¸ï¿½ï¿½ï¿½ï¿½ÝµÄ±ï¿½Ç©pï¿½ï¿½ï¿½ï¿½È¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ãµï¿½ï¿½ï¿½Ê¾ï¿½ï¿½
	    	thismodal.find('p.window-text').html(obj);   
		},
		setTitle:function(obj){
			//ï¿½Òµï¿½ï¿½ï¿½Òªï¿½Þ¸ÄµÄµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½â£¬ï¿½ï¿½È¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ÃµÄµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
			if(obj!=""){
				thismodal.find('h2').show().html(obj); 
			}
	    	
		},
		setButton: function (obj){
			console.log(obj)
		    //ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ä²ï¿½ï¿½ï¿½json
		    var json=eval(obj);
		   
		    
		    if(json.length==1){
		    	//Ò»ï¿½ï¿½ï¿½ï¿½Å¥
		    	thismodal.find('a.ack-button').show().html(json[0]);
		    }
		    if(json.length==2){
		    	//ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Å¥
>>>>>>> branch 'master' of https://github.com/leierzhan/Delicious.git
		    	thismodal.find('a.cancel-button').show().html(json[0]); 
		    	thismodal.find('a.confirm-button').show().html(json[1]);

		    }
		}
	}
	
	})()
	
	
