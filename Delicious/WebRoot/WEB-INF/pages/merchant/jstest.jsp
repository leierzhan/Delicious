<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<style>
* {
	margin: 0;
	padding: 0;
}


</style>


</head>

<script type="text/javascript">




</script>

<script type="text/javascript">
$(document).ready(function(){
	wx.config({
	    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: 'wx5ecac8f9f5725183', // 必填，公众号的唯一标识
	    timestamp: ${jsconfig.timestamp}, // 必填，生成签名的时间戳
	    nonceStr: '${jsconfig.noncestr}', // 必填，生成签名的随机串
	    signature: '${jsconfig.signature}',// 必填，签名，见附录1
	    jsApiList: [${jsconfig.apis}] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});

	wx.ready(function () {
		 
		  document.querySelector('#checkJsApi').onclick = function () {
			  alert(1);
		    wx.checkJsApi({
		      jsApiList: [
		        ${jsconfig.apis}
		      ],
		      success: function (res) {
		        alert(JSON.stringify(res));
		      }
		    });
		  };
	});

});
</script>


<body style="background-color: #eee;">

<header class="bar bar-nav">
  <h1 id="checkJsApi" class="title">商户中心</h1>
</header>


</body>
</html>
