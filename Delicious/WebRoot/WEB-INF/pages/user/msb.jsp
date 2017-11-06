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
    <div class="content" style="margin-top:200px;">
    <div class="buttons-tab">
      <a href="#tab1" class="tab-link active button">任务奖励</a>
      <a href="#tab2" class="tab-link button">转账记录</a>
      <a href="#tab3" class="tab-link button">消费记录</a>
    </div>
    <div class="tabs">
      <div id="tab1" class="tab active">
       <div class="content-block"  >
         <div class="list-block media-list" style="margin:-10px;margin-top:10px;">
      <ul>
      <c:forEach items="${task }" var="t">
      <li>
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title-row">
                <div class="item-title"><c:if test="${t.type==0}"><code>文章</code></c:if><c:if test="${t.type==1}"><code>签到</code></c:if> |${t.wzname}</div>
              </div>
              <div class="item-subtitle" style="color:#d8d8d8;">${t.timec}</div>
            </div>
             <div class="item-after" style="margin:10px;">+${t.num}</div>
          </div>
        </li>
        </c:forEach>
        </ul>
        </div>
        </div>
      </div>
      <div id="tab2" class="tab">
        <div class="content-block">
         <div class="list-block media-list" style="margin:-10px;margin-top:10px;">
      <ul>
      <c:forEach items="${deal}" var="t">
      <li>
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title-row">
                <div class="item-title">${t.fromusername}&nbsp;<span style="color:#8d8d8d;font-size:12px;">转账到</span>&nbsp;${t.tousername}</div>
              </div>
              <div class="item-subtitle" style="color:#d8d8d8;">${t.timec}</div>
            </div>
             <div class="item-after" style="margin:10px;">${t.num}</div>
          </div>
        </li>
        </c:forEach>
        
        </ul>
        </div>
          
        </div>
      </div>
      <div id="tab3" class="tab">
        <div class="content-block">
           <div class="list-block media-list" style="margin:-10px;margin-top:10px;">
      <ul>
      <c:forEach items="${consume}" var="t">
      <li>
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title-row">
                <div class="item-title"><c:if test="${t.type==0}"><code>消费</code></c:if>|${t.storename}</div>
              </div>
              <div class="item-subtitle" style="color:#d8d8d8;">${t.timec}</div>
            </div>
             <div class="item-after" style="margin:10px;">${t.num}</div>
          </div>
        </li>
        </c:forEach>
        
        
        
        </ul>
        </div>
        </div>
      </div>
    </div>
  </div>
  
  
  
  
  </body>
</html>
