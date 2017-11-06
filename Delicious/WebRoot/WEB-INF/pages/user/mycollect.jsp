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
	$(".qx").click(function(){
		var storeid=$(this).attr("title");
		var chefid=$(this).attr("dir");
		$.ajax({type:"post",url:"../page/cancelCollect?storeid="+storeid+"&chefid="+chefid,success:function(d){
			window.history.go(0);
		}});
		
	});
});
</script>



<body>
	<header class="bar bar-nav">
		<h1 class="title">我的收藏</h1>
	</header>
	<div class="content">

   <div class="buttons-tab">
      <a href="#tab1" class="tab-link active button">店铺</a>
      <a href="#tab2" class="tab-link button">厨师</a>
    </div>
 <div class="tabs">
      <div id="tab1" class="tab  active">
        <div class="content-block">
        <c:if test="${empty collects}">
		<p style="text-align:center;">空空如也...</p>
		</c:if>
        
        	<c:forEach items="${collects}" var="s">
        
			<!-- 店铺信息 -->
			<c:if test="${s.chefid==0}">
					<div class="card" style="margin:0;">
						<div class="card-header">
							<b>${s.storename }</b><span title="${s.storeid}"
								style="float:right;color:#7763D5;">进入店铺</span>
						</div>

						<div class="card-content">
							<div class="card-content-inner content${s.storeid}">
								<img src="${s.imgsurl}"
									style="margin:0;width:100%;height:180px;">
							</div>
						</div>
						<div class="card-footer">${s.timec}<span style="">
								<c:forEach begin="1" end="${s.level}">
									<span class="icon icon-emoji" style="color:yellow;"></span>
								</c:forEach>
							</span> <span class="qx" style="color:gray;float:right;" title="${s.storeid}" dir="${s.chefid}">取消收藏</span>
						</div>
					</div>
			</c:if>

			</c:forEach>
      </div>
      </div>
      
      <div id="tab2" class="tab">
        <div class="content-block">
        
         <div class="list-block media-list">
               <c:if test="${empty collects}">
		<p style="text-align:center;">空空如也...</p>
		</c:if>
      	  <ul>
      	
      	  
          <c:forEach items="${collects}" var="s">
          	<c:if test="${s.storeid==0 }">
          	 <li>
          <div class="item-content">
            <div class="item-media"><img src="http://gqianniu.alicdn.com/bao/uploaded/i4//tfscom/i3/TB10LfcHFXXXXXKXpXXXXXXXXXX_!!0-item_pic.jpg_250x250q60.jpg" width="44"></div>
            <div class="item-inner">
              <div class="item-title-row">
                <div class="item-title">${s.name}</div>
                <div class="item-after qx" title="${s.storeid}" dir="${s.chefid}" style="font-size:15px;">取消收藏</div>
              </div>
 				<div class="item-subtitle">
              <c:forEach begin="1" end="${s.cheflevel}">
				<span class="icon icon-emoji" style="color:yellow;"></span>
				</c:forEach></div>
              <div class="item-text">${s.tags}</div>
            </div></div>
        </li>
        </c:if>
        </c:forEach>
        
        </ul>
        </div>
        </div>
        </div>
</div>
	</div>



</body>
</html>
