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
	var chefid=$("select[name='chefid']").val();
	var price=$("input[name='price']").val();
	
	$.ajax({
		type:"POST",
		url:"../merchant/addGreens",
		data:{
			imgs:imgs,
			name:name,
			chefid:chefid,
			tags:tags,
			price:price
		},
		success:function(data){
			if(data>0){
			window.location.href="../merchant/goGreens";
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
  <h1 class="title">菜信息${storeid }</h1>
</header>
<div class="content" style="margin-top:-35px;">
    <div class="list-block">
      <ul>
        <!-- Text inputs -->
                    <li class="align-center">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">菜图</div>
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
              <div class="item-title label">菜名</div>
              <div class="item-input">
                <input type="text" name="name" placeholder="菜名称">
              </div>
            </div>
          </div>
        </li>
        <!-- Date -->
        <li>
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">价格</div>
              <div class="item-input">
                <input type="number" name="price" placeholder="价格" >
              </div>
            </div>
          </div>
        </li>
          <li class="align-top">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">口味</div>
              <div class="item-input">
                <input type="text" name="tags" placeholder="多个标签”，“隔开（麻辣，酱香）" />
              </div>
            </div>
          </div>
        </li>
           <li class="align-top">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">来自厨师</div>
              <div class="item-input">
                <select name="chefid">
                 <option value="0">无特定厨师</option>
                <c:forEach items="${chefs}" var="c">
                
                <option value="${c.id }">${c.name}</option>
            </c:forEach>
                </select>
                
              </div>
            </div>
          </div>
        </li>

      </ul>
    </div>
    <div class="content-block">
      <div class="row">
        <div class="col-100"><a href="javascript:;" class="button button-big button-fill button-success tj">提交</a></div>
      </div>
    </div>
  </div>
  

</body>
</html>
