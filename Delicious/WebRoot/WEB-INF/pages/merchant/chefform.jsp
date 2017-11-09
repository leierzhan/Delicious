<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<title>唇齿源</title>

<meta name="viewport" id="viewport"
	content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="../css/sm.min.css">
<script type='text/javascript'
	src='../css/zepto.min.js' charset='utf-8'></script>
<script type='text/javascript'
	src='../css/sm.min.js' charset='utf-8'></script>
<script type='text/javascript' src="../js/jquery.min.js" charset='utf-8'></script>
<script src="../css/jweixin.js"></script>
<script src="../js/ajaxfileupload.js"></script>




<style>
* {
	margin: 0;
	padding: 0;
}
.tj{
background-color: #339edf;
color:white;
}
.err{
background-color: red;
color:white;
}
.item-input img{
float:left;
margin:3px;
}
.item-input button{
float:left;
margin:5px;
font-size:15px;
}
.item-input div{
float:left;
margin:5px;
}

.showimg{
display:none;
}
.yshowimg{
display:none;
}
.checkcode{
      display:none;                            
}
</style>


</head>

<script type="text/javascript">

$(document).ready(function(){
	

$(".ssc").click(function(){
	
	$.ajaxFileUpload  
	   (    
	        {   
	            url: '../merchant/uploadGoodsImgs?type=1', //上传文件附带参数
	            secureuri:false,  
	            fileElementId:"simg",  
	            dataType: 'JSON', 			
	            success: function (data)  
	            { 
	            	$(".simg").val(data);
	            	//document.getElementById("simg").value="";
	            	var html="";
	            
	            	
						html+="<img  src='../merchant_img/"+data+"' style='width:60px;height:60px;border:1px solid red; float:left;'>";
	            	//alert(html);
	            	$(".showimgs").html(html);
	            	$(".showimg").css("display","block");
	            },  
	            error: function (data, status, e)  
	            {    
	               alert("上传图片失败!"+status);  
	            }  
	        }  
	    );
});


			//手机验证码
			$(".getcode").click(function(){
			var tel=$("input[name='tel']").val();
			var one=tel.substring(0,1);
			//alert(tel+","+tel.length+","+one);
			if(tel.length==11&&one==1){
				$("input[name='tel']").css("border-bottom","1px solid green");
				  time($(this)); 
				  $.ajax({
					  type:"post",
					  url:"../merchant/getPhoneCode",
					  data:{
						  tel:tel
					  },
					  success:function(data){
						  $(".checkcode").text(data);
						  wait=30;
					  }
				  }); 
			}else{
				
				$("input[name='tel']").css("border-bottom","1px solid red");
			}
				
			});
			
			 //倒计时
			  var wait=60;
				function time(o) {
				    if (wait == 0) {
				    	o.attr("disabled",false);
				        o.val("重新获取");
				        wait = 60;
				    } else { // www.jbxue.com
				    	o.attr("disabled",true);
				        o.val(wait + "秒后可重发");
				        wait--;
				        setTimeout(function() {
				            time(o);
				        },
				        1000);
				    }
					  
				}
				
				
				$("input[name='code']").bind('input propertychange', function() {
				
					var code=$(".checkcode").text();
					if(code==$(this).val()&&$(this).val()!=""){
						$(".sq").removeClass("err");
						$(".sq").addClass("tj");
					}else{
						var len=$(this).val().length;
						if(len<5){
							$(".sq").removeClass("tj");
							$(".sq").addClass("err");
						}
					}
					
					//alert($(this).val());

				});





//chushi添加提交
	$(document).on("click",".tj",function(){
	var imgs=$(".simg").val();
	alert(1);
	var name=$("input[name='name']").val();
	var tags=$("input[name='tags']").val();
	var tel=$("input[name='tel']").val();
	
	$.ajax({
		type:"POST",
		url:"../merchant/addChef",
		data:{
			imgs:imgs,
			name:name,
			tags:tags,
			tel:tel
		},
		success:function(data){
			if(data>0){
			window.location.href="../merchant/goAddChef";
			}else{
			window.history.go(-1);
			}
		},error:function(d,e){
			alert(e);
		}
		
	});
	
	
	
});

});
</script>



<body style="background-color: #eee;">

<header class="bar bar-nav">
  <h1 class="title">厨师信息</h1>
</header>
<div class="content" style="margin-top:-35px;">
    <div class="list-block">
      <ul>
        <!-- Text inputs -->
               <li class="align-center">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">厨师头像</div>
              <div class="item-input">
              			<input class="simg" style="display:none;"/>
                      	<div class="button pull snum" style="width:60px;height:60px;padding-top:15px;padding-left:25px;"><span>+</span>
                      	<input id="simg" name="files"  style="position:relative;right:30px; width:60px;height:60px;opacity:0;" type="file" multiple="multiple"></div>
              <button class="button pull ssc"  style="width:60px;height:60px;">上传</button>
              </div>
            </div>
          </div>
        </li>
               <li class="align-top showimg">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-input showimgs">
                
              </div>
            </div>
          </div>
        </li>
        <li>
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">厨师姓名</div>
              <div class="item-input">
                <input type="text"  name="name" placeholder="厨师名称">
              </div>
            </div>
          </div>
        </li>
        <!-- Date -->

          <li class="align-top">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">厨师标签</div>
              <div class="item-input">
                <input type="text" name="tags" placeholder="多个标签”，“隔开（粤菜，湖南菜，烧烤）" />
              </div>
            </div>
          </div>
        </li>
                <li>
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">手机</div>
              <div class="item-input">
              <div class="row no-gutter">
			        <div class="col-60" > <input type="tel" name="tel" placeholder="手机号码" ></div>
			        <div class="col-40"><input class="button getcode" type="button" style="margin-top:10px;" value="获取验证码" /></div>
     		  </div>
              </div>
            </div>
          </div>
        </li>
                  <li class="align-top">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">手机验证码</div>
              <div class="item-input">
                <input type="text" name="code"/>
                <span class="checkcode"></span>
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div class="content-block">
      <div class="row">
        <div class="col-100"><a href="javascript:;" class="button button-big  sq">申请入驻</a></div>
      </div>
    </div>
  </div>
  

</body>
</html>
