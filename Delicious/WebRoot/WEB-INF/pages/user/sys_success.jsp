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

<style>
* {
color:#8d8d8d;
	margin: 0;
	padding: 0;
}
.userinfo{
height:68px;
background-color: white;
}
.userinfo img{
margin:14px;	
float:left;
width:40px;
height:40px;
}
.user_info{
float:left;
line-height: 68px;
}
.msblist{
margin-top:8px;
}
h2{color:orange;}
</style>


</head>

<script type="text/javascript">

$(document).ready(function(){
	$('.jine').on('input propertychange', function() {  
	    if($(this).val()>100){
	    	$(".ky0 code").text("可用");
	    	$(".box").prop("checked",true);
	    	$(".box").removeAttr("disabled");
	    }else{
	    	$(".ky0 code").text("不可用");
	    	$(".box").prop("checked",false);
	    	$(".box").attr("disabled","disabled");

	    }	}); 


$(document).on('click','.confirm-ok', function () {
	   var jine=$(".jine").val();
	   var man=$(".m").text();
	   var di=$(".d").text();
	   var s=Math.floor(jine/man)*di;

	   var tongyong=$(".c0 h2").text();
	   if(s>tongyong){
		   s=tongyong;
	   }
	   var dianpu=$(".c h2").text();
	   
	   var sy=jine-s-dianpu;
	   
	     $(".subtext").html("<p>您得总消费金额是："+jine+"<br>消费通用币:"+s+"<br>消费店铺币："+dianpu+"<br>余付现金："+sy+"</p>");
	     
	    });

});
</script>



<body>
	    <div class="page-group">
    <div id="page-title-bar-btns" class="page">
  <header class="bar bar-nav">

 
    <h1 class="title">用户结账</h1>
  </header>

	<div class="content">
    <div class="list-block media-list" style="margin-top:0px;margin-bottom:10px;">
      <ul>
	<li>
          <div class="item-content">
            <div class="item-media"><img src="${storeinfo.imgsurl}" width="44"></div>
            <div class="item-inner">
              <div class="item-title-row">
                <div class="item-title">${storeinfo.storename }</div>
              </div>
              <div class="item-subtitle" style="font-size:15px;">消费规则：<span style="color:orange;">满<code class="m">${storeinfo.man}</code>可用<code class="d">${storeinfo.di}</code></span></div>
            </div>
            	<input class="jine"  style="width:60%;height:100%;margin-left:20px;border:none;font-size:15px;" placeholder="结账金额" type="number" />
            
          </div>
        </li>
        </ul></div>
	
	
		 <div class="list-block media-list msblist">
      <ul>
      
      <c:forEach items="${msbs}" var="m">
        <li style="border-radius:20px;">
          <label class="label-checkbox item-content" style="border-left:18px solid orange;">
              <c:if test="${m.storeid>0 }">
                <input type="checkbox" name="checkbox">
             </c:if>
             <c:if test="${m.storeid==0 }">
            <input type="checkbox" class="box" disabled="disabled" name="checkbox">
            </c:if>
            <div class="item-media"><i class="icon icon-form-checkbox"></i></div>
            <div class="item-inner">
              <div class="item-title-row">
               <c:if test="${m.storeid==0 }">
                <div class="item-title c0"><h2>${m.num }</h2></div>
                </c:if>
                   <c:if test="${m.storeid!=0 }">
                <div class="item-title c"><h2>${m.num }</h2></div>
                </c:if>
                <div class="item-after"><h6>${m.endtime}</h6></div>
              </div>
              <div class="item-subtitle" >
              <h4>
             <c:if test="${m.storeid==0 }">
             通用美食币
             </c:if>
                   <c:if test="${m.storeid>0 }">
             ${storeinfo.storename }商家美食币
             </c:if>
             </h4>
              </div>
              <div class="item-text ky${m.storeid}" style="color:#8d8d8d;"><code></code></div>
            </div>
          </label>
        </li>
        </c:forEach>
        </ul>
        </div>
        
        <div class="subtext"></div>
        
        
        
        
<button class="button button-fill confirm-ok" style="width:100%;position:fixed;bottom:0px;height:45px;line-height: 45px;">结账</button>
        </div>
</div>

</body>
</html>
