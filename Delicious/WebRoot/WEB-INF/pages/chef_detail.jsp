<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>厨师详情</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="../css/global.css">
    <link rel="stylesheet" href="../css/swiper-3.4.2.min.css">
    
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/swiper-3.4.2.jquery.min.js"></script>
    <script type="text/javascript" src="../js/windowOpen.js" ></script>
  </head>
  <body style="background:#ffffff;">
   <div class="content">
    <div>
      <div style="float:left;">
        <img src="../image/chushi.jpg" style="width:80px;height:80px;margin-left:16px;margin-top:16px;">
      </div>
      <div style="margin-left:16px;float:left;margin-top:16px;">
         <span style="margin-left:16px;">${chefEntity.name }</span><br>
         <c:forEach var="tag" items="${fn:split(chefEntity.tags,'，')}">  
           <span style="font-size:16px;margin-left:16px;background:#F28724;color:#ffffff;padding:4px 10px;border-radius:15px;">${tag} </span>
         </c:forEach>  
         <br>
         <img src="../image/star_grade.png" style="margin-top:-16px;margin-left:16px;">
      </div>
    </div>
    <div style="clear:both;">
	  <hr>
	  <span style="margin-left:16px;">他的招牌菜</span>
	  <hr>
    </div>
    <div class="list-block media-list">
	    <ul>
	    <c:forEach items="${greensList }" var="green" varStatus="status">
	      <li>
	          <div class="item-media"><img src="${green.imgs }" style='width: 4rem;'></div>
	          <div class="item-inner">
	            <div class="item-title-row">
	              <div class="item-title">${green.name }</div>
	              <div class="item-after" style="color:#E22CE6;font-size:14px;margin-right: 32px;">综合评分：4.5</div>
	            </div>
	            <div class="item-subtitle"><span style="font-size:16px;">${green.tags }</span></div>
	          </div>
	      </li>
	    </c:forEach>
	    </ul>
	  </div>
	      </div>
    <!-- 默认必须要执行$.init(),实际业务里一般不会在HTML文档里执行，通常是在业务页面代码的最后执行 -->
    <script>$.init()</script>
    <script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
  </body>
</html>
