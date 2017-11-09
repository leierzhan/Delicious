(function(){

	//������������js�����е����Ѿ�д�ã�����ʱ����ҳ����ע�ͷ���ʹ�ü���
	var $oMasking;
	var $oWindowContainer;
	//�򿪵�������
	$.fn.openWindow = function(setTitle,setContents,setButton){
		
		//ƴ�ӵ������ݣ������ڵ��ô򿪵�������ʱ���������body
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
		//��ƴ�Ӻõ�html���body����
		$('body').append(_html);
		$oMasking = $('.window-masking');
		$oWindowContainer = $('.window-container');
		//���ȡ��ť�رյ���
		$('.cancel-button,.window-masking,.ack-button').on('click',function(){
			closeWindow();
		});
		$('.confirm-button').on('click',function(){
			setContents=setContents.substring(3,setContents.length);
			window.location.href = "tel:"+setContents;
		});
		//�����ɰ�չʾ
		modal = new Modal();
		console.log(setButton+","+setContents+","+setButton)
		modal.setTitle(setTitle);
		modal.setContents(setContents);
				//���ð�ť���������
		modal.setButton(setButton);
		$oMasking.show();
		//���õ������չʾ
		$oWindowContainer.show();
	}
	//�رյ�������
	function closeWindow(){
		$oMasking = $('.window-masking');
		$oWindowContainer = $('.window-container');
		//�رյ�����ʱ���ɰ��html��ҳ�����Ƴ��
		$oMasking.remove();
		$oWindowContainer.remove();
	}
	//��ʼ��
	var Modal = function () {
	    thismodal = $('#addNew');
	};
	//�޸����ݷ���
	Modal.prototype = {
		setContents:function(obj){
			//�ҵ���Ҫ�޸����ݵı�ǩp����ȡ���������õ���ʾ��
	    	thismodal.find('p.window-text').html(obj);   
		},
		setTitle:function(obj){
			//�ҵ���Ҫ�޸ĵĵ������⣬��ȡ���������õĵ�������
			if(obj!=""){
				thismodal.find('h2').show().html(obj); 
			}
	    	
		},
		setButton: function (obj){
			console.log(obj)
		    //�����������Ĳ���json
		    var json=eval(obj);
		   
		    
		    if(json.length==1){
		    	//һ����ť
		    	thismodal.find('a.ack-button').show().html(json[0]);
		    }
		    if(json.length==2){
		    	//������ť
		    	thismodal.find('a.cancel-button').show().html(json[0]); 
		    	thismodal.find('a.confirm-button').show().html(json[1]);

		    }
		}
	}
	
	})()
	
	
