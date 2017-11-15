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
background-color: red;
color:#eee;	
}

</style>


<body style="background-color: #eee;">
 <header class="bar bar-nav">
  <button class="button pull-left go" title="../merchant/goMerchantSys">
      <
    </button>
<!--     <button class="button pull-right go" title="../merchant/goAddChef">
      +
    </button> -->
    <h1 class="title">厨师</h1>
  </header>
	<div class="content" style="margin-top:-40px;">
		<div class="list-block media-list">
			<ul>
			
			<c:if test="${empty chefs}">
				<li style="text-align:center;height:35px;color:#8d8d8d;line-height:35px;">
			-没有添加厨师请点击右上角”+“添加厨师-
			</li>
			</c:if>
			<c:if test="${!empty chefs}">
			
			<c:forEach items="${chefs}" var="cf" >
				<li><a href="../merchant/goGreensByChefStore?chefid=${cf.id}" class="item-link item-content">
						<div class="item-media">
							<img style="width:60px;height:60px;"
								src="${cf.headimg}"
								>
						</div>
						<div class="item-inner">
							<div class="item-title-row">
								<div class="item-title" style="padding-left:4px;"><b>${cf.name}</b></div>
								<div class="item-after">查看详细</div>
							</div>
							<div class="item-subtitle"><code>${cf.tags}</code></div>
							<div style="line-height:40px;margin:4px;font-size:16px;"><img alt="sc" src="../imgsvg/sc.svg" style="width:23px;">
							<span style="position: relative;top:-5px;left:5px;color:#8d8d8d;">${cf.collectnum}</span>
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
