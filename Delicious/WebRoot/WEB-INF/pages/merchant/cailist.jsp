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
border:1px solid yellow;
border-radius:4px;
margin-left:4px;
padding:5px;
background-color: orange;
color:#eee;	
}



</style>


<body style="background-color: #eee;">
 <header class="bar bar-nav">
  <button class="button pull-left go" title="../merchant/goMerchantSys">
  <
    </button>

    <h1 class="title">菜单</h1>
  </header>
	<div class="content" style="margin-top:-40px;">
		<div class="list-block media-list">
			<ul>
			<c:if test="${empty grs}">
				<li style="text-align:center;height:35px;color:#8d8d8d;line-height:35px;">
			-没有添加厨师请点击右上角”+“添加菜-
			</li>
			</c:if>
			<c:if test="${!empty grs}">
			
			<c:forEach items="${grs}" var="g" >
				<li><a href="../merchant/goUpdateGreens?id=${g.id}" class="item-link item-content">
						<div class="item-media">
							<img
								src="${g.imgs}"
								width="60"height="60">
						</div>
						<div class="item-inner">
							<div class="item-title-row">
								<div class="item-title" style="padding-left:4px;"><b>${g.name}</b></div>
								<div class="item-after">￥${g.price}</div>
							</div>
							<div class="item-subtitle"><code>${g.tags}</code></div>
							<div style="line-height:40px;margin:4px;font-size:16px;"><img alt="sc" src="../imgsvg/zan.svg" style="width:23px;">&nbsp;${g.zan}
							</div>
						</div>
				</a></li>
			
				</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>




</body>
</html>
