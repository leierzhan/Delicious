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
.userinfo{
margin-top:50px;
width:100%;
height:80px;
text-align:center;
}
.userinfo img{
margin-top:10px;
}
.userstore{
width:100%;
height:auto;
line-height: 45px;
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

<div class="userinfo">
<img alt="" src="${userinfo.headImgUrl }" style="width:40px;border-radius:10px;">
<h4>${userinfo.truename }(${userinfo.nickname })</h4>

</div>

<div class="userstore">
            <c:if test="${empty storeinfo}">
             <div class="list-block media-list" style="margin:0;" >
      <ul>
        <li>
          <a href="../storeHandler/storeZp?userId=${userid}" class="item-link item-content" style="background-color:#33efef;">
            <div class="item-inner"  style="color:#eee;">
              <div class="item-title-row">
                <div class="item-title"></div>
                <div class="item-after" >找工作</div>
              </div>
              <div class="item-subtitle"><h2>状态：无业</h2></div>
                 <div class="item-after" >   </div>
            </div>
          </a>
        </li>
      </ul>
   </div>
            </c:if>
              <c:if test="${not empty storeinfo}">
 <div class="list-block media-list" style="margin:0;" >
      <ul>
        <li>
          <a href="../storeHandler/storeDetailUI?storeId=${storeinfo.id}&userId=${userid}" class="item-link item-content" style="background-color:orange;">
            <div class="item-media"><img src="${storeinfo.imgsurl}" width="80"></div>
            <div class="item-inner"  style="color:#eee;">
              <div class="item-title-row">
                <div class="item-title">${storeinfo.storename}</div>
                <div class="item-after" >进入店铺</div>
              </div>
              <div class="item-subtitle">${storeinfo.address}</div>
              <div class="item-text" style="color:#eee;">${storeinfo.tel}</div>
            </div>
          </a>
        </li>
      </ul>
   </div>
</c:if>
</div>
<h2 style="text-align:left;border-bottom:1px solid gray;">功能</h2>
        <div style="width:100%;height:auto;text-align:center;">
        
          <div class="row" style="width:80%;margin:0 auto;" >
        <div class="col-50 go" title="../merchant/goChefList" style="margin-top:15px;height:85px;background-color:#808ddd;color:#eee;padding:8px;border-radius:10px;">
     <img alt="" src="../imgsvg/chushi.svg" style="width:40px;"/><br>     
        厨师信息
          </div>
        <div class="col-50 go" title="../merchant/goGreens" style="margin-top:15px;	height:85px;background-color:#1288bb;color:#eee;padding:8px;border-radius:10px;">
          <img alt="" src="../imgsvg/cai.svg" style="width:40px;"/><br>
        菜品信息</div>
        </div>
          <div class="row" style="width:80%;margin:0 auto;" >
        <div class="col-50 go" title="../merchant/goPage?url=wzlist" style="margin-top:15px;height:85px;background-color:#666666;color:#eee;padding:8px;border-radius:10px;">
     <img alt="" src="../imgsvg/wenzhang.svg" style="width:40px;"/><br>     
        从业经历</div>
        </div>

</div>

</body>
</html>
