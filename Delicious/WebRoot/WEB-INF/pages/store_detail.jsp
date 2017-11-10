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
    <title>店铺详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="../css/global.css">
    <link rel="stylesheet" href="../css/swiper-3.4.2.min.css">
    
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/swiper-3.4.2.jquery.min.js"></script>
    <script type="text/javascript" src="../js/windowOpen.js" ></script>
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
         var mySwiper = new Swiper('.swiper-container',{
		    direction: 'horizontal',
		    loop: true,
		    // 如果需要分页器
		    pagination: '.swiper-pagination',
		    // 如果需要前进后退按钮
		    //nextButton: '.swiper-button-next',
		    //prevButton: '.swiper-button-prev',
		    // 如果需要滚动条
		    scrollbar: '.swiper-scrollbar',
		    scrollbarHide:true,
		   });      
			$('.open-window').on('click',function(){
				var telNum=$(this).text();
				var setTitle = '呼叫';
				var setContents = "拨打："+telNum;
				var setButton = '["取消","确定"]';
				$(this).openWindow(setTitle,setContents,setButton);
			});
			//特色菜点赞
			$(document).on("click",".zan",function(){
				$(this).children("span.zancount").text(Number($(this).children("span.zancount").text())+1);
				//用ajax向数据库添加
				var greenId=$("input[name='greenId']").val();
				$.ajax({
					type:"get",
					url:"http://www.cnmjw.com.cn/Delicious/storeHandler/addZan",
                    data:{"greenId":greenId,"data":new Date()},
					success:function(data){
					},
					error:function(data){
					}
				});
			});
			//评论点赞
			$(document).on("click",".img_zan",function(){
				$(this).prev("span[class='zanshow']").text(Number($(this).prev("span[class='zanshow']").text())+1);
				//用ajax向数据库添加
				var commentId=$(this).attr("name");
				$.ajax({
					type:"get",
					url:"http://www.cnmjw.com.cn/Delicious/storeHandler/addCommentZan",
                    data:{"commentId":commentId,"data":new Date()},
					success:function(data){
                      
					},
					error:function(data){
					}
				});
			});
			
	    });
    </script>
  </head>
  <body>
    <div class="content">
    <input type="hidden" name="storeId" value="${storeId}">
    <input type="hidden" name="userId" value="${userId}">
	  <div class="swiper-container" style="width:100%;height:200px;">
       <div class="swiper-wrapper" style="width:100%;">
          <c:forEach items="${coverList}" var="cover">
            <div class="swiper-slide" style="width:100%;">
               <img src="${cover}" style="width:100%;">
            </div>
          </c:forEach>
       </div>
      <!-- 如果需要分页器 -->
      <div class="swiper-pagination"></div>
      <!-- 如果需要滚动条 -->
      <div class="swiper-scrollbar" style="width:0px;"></div>
   </div>
   <p style="margin-left:14px;font-size:18px;">${storeInfo.storename }</p>
   <div style="margin-left:16px;">
     <img src="../image/star_grade.png" style="vertical-align:middle">
     <span style="margin-left:16px;font-size:16px;">${storeInfo.per_capite }</span>
   </div>
   <div>
     <img src="../image/position.png" style="vertical-align:middle;margin-left:16px;">
     <img src="../image/verticle_line.png" style="vertical-align:middle;margin-left:-10px;">
     <a href="storePosition?address=${storeInfo.address }" style="color:#333333;font-size:16px;margin-left:-10px;">${storeInfo.address }</a>
   </div>
   <hr>
   <div>
     <img src="../image/phone.png" style="vertical-align:middle;margin-left:16px;">
     <img src="../image/verticle_line.png" style="vertical-align:middle;margin-left:-10px;">
     <span class="open-window" style="font-size:16px;margin-left:-10px;">${storeInfo.tel }</span>
     <img src="../image/arrow_right.png" style="vertical-align:middle;float:right;maring-right:16px;">
   </div>
   <div style="margin-top:16px;margin-left:16px;">
     <span style="font-size:18px;">本店厨师</span>
     <c:forEach items="${chefList }" var="chef">
	     <div style="margin-top:16px;">
	       <a href="chefDetail?chefId=${chef.id }&userId=${userId }">
	         <img src="${chef.headimg }" style="width:60px;height:60px;vertical-align:middle;">
	         <span style="vertical-align:middle;margin-left:16px;font-size:14px;">${chef.name }</span>
	         <img src="../image/star_grade.png" style="vertical-align:middle;margin-left:16px;">
	       </a>
	     </div>
	     <hr>
     </c:forEach>
   <div style="margin-top:16px;">
      <span style="font-size:18px;">本店特色</span>
        <div class="list-block media-list">
	    <ul style="margin-top:-16px;">
	    <c:forEach items="${greenList }" var="green">
	      <li>
	        <div class="item-link item-content" style="margin-left:-10px;">
	          <div class="item-media"><img src="../image/zhuti.jpg" style='width:60px;height:60px;'></div>
	          <div class="item-inner">
	            <div class="item-title-row" style="margin-top:13px;">
	              <div class="item-title"><span style="font-size:18px;">${green.name }</span></div>
	              <div class="item-after"><span style="font-size:16px;margin-right:32px;">${green.price }元</span></div>
	            </div>
	            <div class="item-subtitle"><span style="font-size:16px;">${green.tags }</span></div>
	           <div class="zan">
	             <img src="../image/zan.png" style="vertical-align:middle;width:20px;height:20px;">
	             <span style="font-size:16px;" class="zancount">${green.zan }</span>
	             <input type="hidden" name="greenId" value="${green.id }">
	           </div>
	          </div>
	         </div>
	      </li>
	    </c:forEach>
	    </ul>
  </div>
   </div>
   <div class="list-block media-list">
      <div style="display:inline;">
         <div style="display:inline;">
            <span style="font-size:18px;">网友点评</span>
         </div>
         <div class="write_comment" style="display:inline;float:right;margin-right:16px;">
           <a href="writeComment?storeId=${storeId }&userId=${userId }">
             <span style="font-size:14px;">写评论</span>
             <img src="../image/write_comment.png" style="width:12px;height:12px;vertical-align:middle;margin-left:-5px;">
           </a>
         </div>
      </div>
      <ul style="padding-top:10px;margin-left:-18px;">
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
          <div style="display:inline;">
            <span style="font-size:14px;margin-left:32px;">赞</span>
            <span class="zanshow" style="font-size:14px;margin-left:-5px;">${com.zan }</span>
            <img class="img_zan" name="${com.id }" style="width:20px;vertical-align:middle;margin-left:25px;margin-top:3px;" src="../image/love.png">
          </div>
          <hr style="height:1px;background:#E7E7E7;border:none;margin-top:5px">
       </li>
       </c:forEach>
       </ul>
       <div style="text-align:center;">
          <a href="moreComment?storeId=${storeId }" >点击查看更多评论</a>
       </div>
    </div>
  </div>
  </div>
    <!-- 默认必须要执行$.init(),实际业务里一般不会在HTML文档里执行，通常是在业务页面代码的最后执行 -->
    <script>$.init()</script>
    <script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
  </body>
</html>