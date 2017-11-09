<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>评论</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" type="text/css" href="../css/global.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
    <script src="../js/jquery.min.js"></script>
    <style>
     body{
      background:#ffffff;
     }
     .swiper-container {
      width: 600px;
      height: 300px;
     }  
     a:link {
		 font-size: 12px;
		 color: #4C5054;
		 text-decoration: none;
	 }
	 a:visited {
         font-size: 12px;
         color: #4C5054;
         text-decoration: none;
     }
	 a:hover {
	     font-size: 12px;
	     color: #4C5054;
	     text-decoration: none;
	 }
    </style>
    <script type="text/javascript">
      $(function(){
    	  $("span[class='loadmore']").click(function(){
	   		  //获取到最后一条评论的id
	   		  var storeId=$("input[name='storeId']").val();
	   		  var lastCommentId=$("ul li:last-child").children("input[name='lastCommentId']").val(); 
    		  $.ajax({
					type:"get",
					url:"http://www.cnmjw.com.cn/Delicious/storeHandler/getMoreCommentByLastId",
	                 data:{"storeId":storeId,"commentId":lastCommentId,"data":new Date()},
 					success:function(data){
                      if(data.length==0){
                    	  $("span[class='loadmore']").text("没有更多评论");
                      }else{
                    	  for(var i=0;i<data.lenkgth;i++){
                    		  $("ul[class='comment']").append("<li>"+
               				    "<input type='hidden' name='lastCommentId' value="+data[i].id+"><div>"+
              			        "<img src="+data[i].weixinUserInfo.headImgUrl+" style='vertical-align:middle;width:40px;height:40px;border-radius:20px;margin-left:16px;'>"+
              			        "<span style='margin-left:16px;font-size:16px;'>"+data[i].weixinUserInfo.nickname+"</span>"+
              			        "</div><div style='margin-left:16px;margin-right:16px;margin-top:10px;'>"+
               			        "<span style='font-size:14px;margin-left:16px;'>"+data[i].comment+"</span>"+
               			        "</div><div style='margin-left:32px;margin-right:16px;margin-top:10px;'>"+
               			        "<img src='https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg' style='width:55px;height:55px;'>"+
               			        "<img src='https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg' style='width:55px;height:55px;'>"+
               		            "<img src='https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg' style='width:55px;height:55px;'>"+
               			        "</div><hr style='height:1px;background:#E7E7E7;border:none;'></li>");
                    	   }
                      }	
					},
					error:function(data){
					}
				});
    	  });
	  });
    </script>
  </head>
  <body>
  <input type="hidden" name="storeId" value="${storeId }">
  <header class="bar bar-nav">
      <a href="#" onClick="javascript:history.back(-1);" class="icon icon-left pull-left"></a>
      <h1 class="title">网友评论</h1>
    </header>
    <div class="content">
     <div class="list-block media-list" style="margin-top:0px;">
      <ul class="comment">
      <c:forEach items="${commentList }" var="com" varStatus="status">
	    <li>
	    <input type="hidden" name="lastCommentId" value="${com.id }">
	      <c:if test="${status.first}">
            <div style="padding-top:16px;">
             <img src="${com.weixinUserInfo.headImgUrl }" style="vertical-align:middle;width:40px;height:40px;border-radius:20px;margin-left:16px;">
             <span style="margin-left:16px;font-size:16px;">${com.weixinUserInfo.nickname }</span>
            </div>
          </c:if>
          <c:if test="${!status.first}">
            <div>
             <img src="${com.weixinUserInfo.headImgUrl }" style="vertical-align:middle;width:40px;height:40px;border-radius:20px;margin-left:16px;">
             <span style="margin-left:16px;font-size:16px;">${com.weixinUserInfo.nickname }</span>
            </div>
          </c:if>
          <div style="margin-left:16px;margin-right:16px;margin-top:10px;">
            <span style="font-size:14px;margin-left:16px;">${com.comment }</span>
          </div>
          <div style="margin-left:32px;margin-right:16px;margin-top:10px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:55px;height:55px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:55px;height:55px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:55px;height:55px;">
          </div>
          <hr style="height:1px;background:#E7E7E7;border:none;">
       </li>
       </c:forEach>
       </ul>
    </div>
    <div style="text-align:center;margin-bottom:15px;">
        <span class="loadmore" style="font-size:14px;">点击加载更多</span>
    </div>
  </div>
    <!-- 默认必须要执行$.init(),实际业务里一般不会在HTML文档里执行，通常是在业务页面代码的最后执行 -->
    <script>$.init()</script>
    <script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
  </body>
</html>