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
$(document).ready(function(){
	$(".go").click(function(){
		var url=$(this).attr("title");
		window.location.href=url;
	});
	
});
</script>
<style>
code{
border-radius:4px;
margin-left:4px;
padding:5px;
color:gray;	
}



</style>


<body style="background-color: #eee;">
 <header class="bar bar-nav">
  <button class="button pull-left go" title="../merchant/goMerchantSys">
      首页
    </button>

    <h1 class="title">收款记录</h1>
  </header>
	<div class="content" style="margin-top:-40px;">
		<div class="list-block media-list">
			<ul>
			<c:if test="${empty str}">
				<li style="text-align:center;height:35px;color:#8d8d8d;line-height:35px;">
			-没有收款记录-
			</li>
			</c:if>
			<c:if test="${!empty str}">
			
			<c:forEach items="${str}" var="g" >
				<li><a href="" class="item-link item-content">
						<div class="item-media">
							<img
								src="${g.headImgUrl}"
								width="80">
						</div>
						<div class="item-inner">
							<div class="item-title-row">
								<div class="item-title" style="padding-left:4px;"><b>${g.nickname}</b></div>
								<div class="item-after">消费：通用币${g.universal_coin}个，店铺币${g.unique_coin}</div>
							</div>
							<div class="item-subtitle"><code>${g.create_time}</code></div>
					
							</div>
				</a></li>
			
				</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>




</body>
</html>
