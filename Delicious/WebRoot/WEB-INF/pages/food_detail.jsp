<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>特色菜</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>厨师详情</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="../css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/swiper-3.4.2.jquery.min.js"></script>
  </head>
  
  <body style="background:#ffffff;">
  <div class="content">
    <img src="../image/zhuti.jpg" style="width:100%;"> 
    <div style="width:100%;">
      <span style="margin-left:16px;">红烧猪蹄</span>
      <span style="float:right;margin-right:32px;font-size:14px;color:#E22CE6;">综合评分：4.5</span>
    </div>
    
    <div class="list-block media-list" style="margin-top:10px;">
      <ul>
	    <li>
          <div style="padding-top:16px;">
             <img src="../image/zhuti.jpg" style="vertical-align:middle;;width:40px;height:40px;border-radius:20px;margin-left:16px;">
             <span style="margin-left:16px;font-size:16px;">宋江</span>
          </div>
          <div style="margin-left:16px;margin-right:16px;">
            <span style="font-size:14px;">初秋的空气里都弥漫着桂花醉人的香气~想要留住花的香味？不如就来做一罐蜜酱吧！做好的桂花酱香甜宜人，可搭配各式甜品、糕点、汤羹和茶饮，个香甜可口、回味无穷~</span>
          </div>
          <div style="margin-left:16px;margin-right:16px;margin-top:8px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
          </div>
          <hr style="height:1px;background:#E7E7E7;border:none;">
       </li>
       <li>
          <div style="padding-top:16px;">
             <img src="../image/zhuti.jpg" style="vertical-align:middle;;width:40px;height:40px;border-radius:20px;margin-left:16px;">
             <span style="margin-left:16px;font-size:16px;">宋江</span>
          </div>
          <div style="margin-left:16px;margin-right:16px;">
            <span style="font-size:14px;">初秋的空气里都弥漫着桂花醉人的香气~想要留住花的香味？不如就来做一罐蜜酱吧！做好的桂花酱香甜宜人，可搭配各式甜品、糕点、汤羹和茶饮，个香甜可口、回味无穷~</span>
          </div>
          <div style="margin-left:16px;margin-right:16px;margin-top:8px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
          </div>
          <hr style="height:1px;background:#E7E7E7;border:none;">
       </li>
       <li>
          <div style="padding-top:16px;">
             <img src="../image/zhuti.jpg" style="vertical-align:middle;;width:40px;height:40px;border-radius:20px;margin-left:16px;">
             <span style="margin-left:16px;font-size:16px;">宋江</span>
          </div>
          <div style="margin-left:16px;margin-right:16px;">
            <span style="font-size:14px;">初秋的空气里都弥漫着桂花醉人的香气~想要留住花的香味？不如就来做一罐蜜酱吧！做好的桂花酱香甜宜人，可搭配各式甜品、糕点、汤羹和茶饮，个香甜可口、回味无穷~</span>
          </div>
          <div style="margin-left:16px;margin-right:16px;margin-top:8px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
          </div>
          <hr style="height:1px;background:#E7E7E7;border:none;">
       </li>
       <li>
          <div style="padding-top:16px;">
             <img src="../image/zhuti.jpg" style="vertical-align:middle;;width:40px;height:40px;border-radius:20px;margin-left:16px;">
             <span style="margin-left:16px;font-size:16px;">宋江</span>
          </div>
          <div style="margin-left:16px;margin-right:16px;margin-top:8px;">
            <span style="font-size:14px;">初秋的空气里都弥漫着桂花醉人的香气~想要留住花的香味？不如就来做一罐蜜酱吧！做好的桂花酱香甜宜人，可搭配各式甜品、糕点、汤羹和茶饮，个香甜可口、回味无穷~</span>
          </div>
          <div style="margin-left:16px;margin-right:16px;margin-top:8px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
            <img src="https://r.sinaimg.cn/large/article/1549f54583792d5496658c35abe87d9d.jpg" style="width:35px;height:35px;">
          </div>
          <hr style="height:1px;background:#E7E7E7;border:none;">
       </li>
       </ul>
    </div>
    </div>
  </body>
</html>
