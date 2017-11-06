<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>地图模块</title>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no"> 
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.0&key=1325b2ff459661c1391a861d4aa88a77"></script> 
     <style type="text/css">
      body,html,#container{
        height: 100%;
        margin: 0px;
        font-size: 12px;
      }
     .panel {
        color: #333;
        padding: 6px;
        border: 1px solid silver;
        box-shadow: 3px 4px 3px 0px silver;
        position: absolute;
        background-color: #eee;
        top: 10px;
        right: 10px;
        border-radius: 5px;
        overflow: hidden;
        line-height: 20px;
      }
      #input{
        width: 250px;
        height: 25px;
      }
    </style>
    <title>地理编码</title>
    
  </head>
  <body>
   <div id="container" tabindex="0"></div>
   <div class ='panel'>
             输入地址显示位置:<br>
     <input id ="input" value =${address }></input>
     <div id = 'message'></div>
   </div>
   <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.0&key=1325b2ff459661c1391a861d4aa88a77"></script>
   <script type="text/javascript">
    var map = new AMap.Map('container',{
            resizeEnable: true,
            zoom: 13,
            center: [113.39,36.9]
    });
    AMap.plugin('AMap.Geocoder',function(){
        var geocoder = new AMap.Geocoder({
            city: "0371"//城市，默认：“全国”
        });
        var marker = new AMap.Marker({
            map:map,
            bubble:true
        });
        var input = document.getElementById('input');
        input.onchange = function(e){
            var address = input.value;
            geocoder.getLocation(address,function(status,result){
              if(status=='complete'&&result.geocodes.length){
                marker.setPosition(result.geocodes[0].location);
                map.setCenter(marker.getPosition())
                document.getElementById('message').innerHTML = '';
              }else{
                document.getElementById('message').innerHTML = '获取位置失败';
              }
            });
        };
        input.onchange();
    });
   </script>
   <script type="text/javascript" src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>
  </body>
</html>
