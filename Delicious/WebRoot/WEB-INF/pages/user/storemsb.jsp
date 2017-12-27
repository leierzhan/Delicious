<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	"WebRoot/js/jquery.min.js"-->
	
	<style>
	
*{margin:0;padding:0;}
.headline-bg{
	height:200px;
	overflow:hidden;
	background:-webkit-linear-gradient(top,#7763D5,#5db8ff);
	background:-moz-linear-gradient(top,#7763D5,#5db8ff);
	background:-o-linear-gradient(top,#7763D5,#5db8ff);
	background:-ms-linear-gradient(top,#7763D5,#5db8ff)
}
.zq{

width:150px;
height:35px;
border-radius:15px;
border: 1px solid #eeeeee;
text-align:center;
margin:0 auto;
}
.zq a{
line-height:35px;
width:100%;
color:#eeeeee;
}

	</style>
	
	
  </head>


  <body>
 
    <div class="headline-bg">  
  <h1 style="color:#eeeeee;text-align:center;">${msb}</h1>
  <div class="zq"><span><a href="javascript:void();">赚取枚士币<span style="color:yellow;">&nbsp;|&nbsp;</span></a></span><span><a href="../page/goMsbZs">赠送</a></span></div>
  
  </div>
  <c:forEach items="${storemsb}" var="s">
  <div class="card demo-card-header-pic">
      <div style="background-image:url(${s.imgsurl})" valign="bottom" class="card-header color-white no-border">
      <span style="color:yellow;">${s.storename}</span><span style="color:yellow;background-color:#d8d8d8;padding:5px;border-radius:8px;">${s.num}</span></div>
      <div class="card-content">
        <div class="card-content-inner">
        	<p><b>电话</b>：${s.tel}</p>
          <p><b>地址</b>：${s.address}</p>
          <p class="color-gray">${s.starttime}~${s.endtime}</p>
        </div>
      </div>
      <div class="card-footer">
        <a href="#" class="link">进入店铺</a>
        <span> 
        <c:forEach begin="1" end="${s.level}">
        <span class="icon icon-emoji" style="color:yellow;"></span>
        </c:forEach>
        
        </span>
      </div>
    </div>
  </c:forEach>
  
  
  </body>
</html>
