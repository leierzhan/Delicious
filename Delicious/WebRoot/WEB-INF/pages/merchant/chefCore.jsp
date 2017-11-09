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
<title>唇齿缘</title>

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



//chushi添加提交
$(".tj").click(function(){
	var imgs=$(".simg").val();
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
			window.location.href="../merchant/goChefList";
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
  <h1 class="title">厨师中心</h1>
</header>
<div class="content" style="margin-top:-35px;">


  </div>
  

</body>
</html>
