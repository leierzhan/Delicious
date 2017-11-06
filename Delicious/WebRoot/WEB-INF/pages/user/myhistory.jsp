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
	$(".clear").click(function(){
		$.ajax({type:"post",url:"../page/clearHistory",success:function(d){
			window.history.go(0);
		}});
		
	});
});
</script>



<body>
	    <div class="page-group">
    <div id="page-title-bar-btns" class="page">
  <header class="bar bar-nav">

    <button class="button pull-right clear">
      清除全部
    </button>
    <h1 class="title">我的足迹</h1>
  </header>

	<div class="content">


        <div class="content-block">
        <c:if test="${empty historys}">
		<p style="text-align:center;">空空如也...</p>
		</c:if>
        
        	<c:forEach items="${historys}" var="s">
        
			<!-- 店铺信息 -->
					<div class="card" style="margin:0;">
						<div class="card-header">
							<b>${s.storename }</b><span>${s.timec}</span><span title="${s.storeid}"
								style="float:right;color:#7763D5;">进入店铺</span>
						</div>

						<div class="card-content">
							<div class="card-content-inner content${s.storeid}">
								<img src="${s.imgsurl}"
									style="margin:0;width:100%;height:180px;">
							</div>
						</div>
						<div class="card-footer">${s.address}<span style="float:right;">
								<c:forEach begin="1" end="${s.level}">
									<span class="icon icon-emoji" style="color:yellow;"></span>
								</c:forEach>
							</span> 
						</div>
					</div>
			</c:forEach>

        </div>
        </div>

</div>
</div>

</body>
</html>
