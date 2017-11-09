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
    <title>写评论</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" type="text/css" href="../css/global.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
    <script src="../js/jquery.min.js"></script>
    <script type="text/javascript">
      $(function(){
    	  $(".submit").click(function(){
    		var storeId=$("input[name='storeId']").val();  
    		var userId=$("input[name='userId']").val();  
    		var comment=$("textarea[name='comment']").val();
    		var nickname=$("input[name='nickname']").val();
    		var headurl=$("input[name='headurl']").val();
    		if(comment.length>0){
    			$("ul[class='comment']").append("<li style='padding-top:16px;'>"+
    	                 "<div><img src="+headurl+" style='vertical-align:middle;width:40px;height:40px;border-radius:20px;margin-left:16px;'>"+
    	                 "<span style='margin-left:16px;font-size:16px;'>"+nickname+"</span>"+
    	                 "</div><div style='margin-left:16px;margin-right:16px;margin-top:10px;'>"+
    	                 "<span style='font-size:14px;margin-left:16px;'>"+comment+"</span>"+
    	                 "</div><div style='margin-left:16px;margin-right:16px;margin-top:10px;'>"+
    	                 "<span style='font-size:14px;margin-left:16px;color:#CDCDCD;'>3小时前</span>"+
    	                 "<a style='font-size:14px;margin-left:14px;color:#22B682;'>删除</a>"+
    	                 "</div><hr style='height:1px;background:#E7E7E7;border:none;'>"+
    	                 "</li>"); 
   	    		$.ajax({
   					type:"get",
   					url:"http://www.cnmjw.com.cn/Delicious/storeHandler/saveComment",
                       data:{"storeId":storeId,"userId":userId,"content":comment,"data":new Date()},
   					success:function(data){
   						$("textarea[name='comment']").val("");
   					},
   					error:function(data){
   					}
   				});
    		}else{
    			$("textarea[name='comment']").attr("placeholder","请输入评论");
    		}
    	  });
	  });
    </script>
  </head>
  <body>
  <input type="hidden" name="storeId" value="${storeId }">
  <input type="hidden" name="userId" value="${userId }">
  <input type="hidden" name="nickname" value="${weixinUserInfo.nickname }">
  <input type="hidden" name="headurl" value="${weixinUserInfo.headImgUrl }">
     <header class="bar bar-nav">
       <h1 class="title">${storeInfo.storename }</h1>
     </header>
     <div class="content" style="width:100%;">
       <div style="width:100%;">
         <textarea name="comment" style="width:100%;font-size:14px;padding-left:16px;padding-top:16px;" rows="10" placeholder="留言对所有人可见"></textarea>
       </div>
       <div class="submit" style="width:90%;text-align:center;background:#22B682;padding-top:10px;padding-bottom:10px;color:#ffffff;font-size:16px;border-radius:5px;margin:0 auto;margin-top:16px;">提交</div>
     <div style="width:100%;">
        <div style="margin:0 auto;text-align:center;font-size:16px;margin-top:16px;">我的留言</div>
     </div>
     <div class="list-block media-list" style="margin-top:16px;">
      <ul class="comment" style="background:#EEEEEE;">
      <c:forEach items="${commentEntityList }" var="com">
       <li style="padding-top:16px;">
          <div>
            <img src="${weixinUserInfo.headImgUrl }" style="vertical-align:middle;width:40px;height:40px;border-radius:20px;margin-left:16px;">
            <span style="margin-left:16px;font-size:16px;">${weixinUserInfo.nickname }</span>
          </div>
          <div style="margin-left:16px;margin-right:16px;margin-top:10px;">
            <span style="font-size:14px;margin-left:16px;">${com.comment }</span>
          </div>
          <div style="margin-left:16px;margin-right:16px;margin-top:10px;">
            <span style="font-size:14px;margin-left:16px;color:#CDCDCD;">3小时前</span>
            <a style="font-size:14px;margin-left:14px;color:#22B682;">删除</a>
          </div>
          <hr style="height:1px;background:#E7E7E7;border:none;">
         </li>
      </c:forEach>
       </ul>
    </div>
     </div>
  </body>
</html>