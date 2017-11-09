<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
* {
	margin: 0;
	padding: 0;
}


</style>


</head>

<script type="text/javascript">



wx.config({
    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: '', // 必填，公众号的唯一标识
    timestamp: '', // 必填，生成签名的时间戳
    nonceStr: '', // 必填，生成签名的随机串
    signature: '',// 必填，签名，见附录1
    jsApiList: [] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});

</script>

<script type="text/javascript">
$(document).ready(function(){
	$(".go").click(function(){
		var url=$(this).attr("title");
		window.location.href=url;
	});
	
});
</script>
<style>
code{
border:1px solid yellow;
border-radius:4px;
margin-left:4px;
padding:5px;
background-color: red;
color:#eee;	
}

</style>


<body style="background-color: #eee;">
 <header class="bar bar-nav">
    <h1 class="title">文章列表</h1>
  </header>
	<div class="content" style="margin-top:-40px;">
		<div class="list-block media-list">
			<ul>
				<li><a href="#" class="item-link item-content">
						<div class="item-media">
							<img
								src="http://gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg"
								width="80">
						</div>
						<div class="item-inner">
							<div class="item-title-row">
								<div class="item-title" style="padding-left:4px;"><b>第一次吃这么好吃的鸡肉卷</b></div>
								<div class="item-after"></div>
							</div>
							<div class="item-subtitle" style="margin-top:10px;"><div >
							<img alt="sc" src="../imgsvg/yul1.svg" style="">&nbsp;<span style="position: relative;top:-10px;">485</span></div>
							</div>
						</div>
				</a></li>

			</ul>
		</div>
	</div>




</body>
</html>
