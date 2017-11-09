<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="http://map.qq.com/api/js?v=2.exp"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <script>
  wx.config({
	    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: 'wx5ecac8f9f5725183', // 必填，公众号的唯一标识
	    timestamp:1506047126433 , // 必填，生成签名的时间戳
	    nonceStr: 'oce0x4bqmo5v387j', // 必填，生成签名的随机串
	    signature: 'A6987F86E2E4340F26616F55F2FEF936FDEC14CE',// 必填，签名，见附录1
	    jsApiList: ['chooseImage','getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
  function dw(){
	  

  wx.getLocation({
	    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
	    success: function (res) {
	        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
	        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
	        var speed = res.speed; // 速度，以米/每秒计
	        var accuracy = res.accuracy; // 位置精度
	        
	        get_address = function() {
	        	   geocoder = new qq.maps.Geocoder({
	        	       complete : function(result){
	        	           alert(result.detail.address);
	        	       }
	        	   });
	        	   var latLng = new qq.maps.LatLng(latitude,longitude);
	        	   geocoder.getAddress(latLng);    
	        	}();
	      
	    }
	});
  }
  </script>
  
  <body>
    <center>美好的事情即将开始上演 </center><br><button onclick="dw()">定位</button>
  </body>
</html>
