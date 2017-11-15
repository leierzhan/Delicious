<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	"WebRoot/js/jquery.min.js""WebRoot/js/jquery.min.js"-->

<style>
* {
	margin: 0;
	padding: 0;
}

.zk{
overflow: auto;
height:300px;

}
.sq{
overflow: auto;
height:60px;
}
.shouq{
display:none;
}

</style>


</head>

<script type="text/javascript">
  $(document).ready(function(){
	  $(".yl").click(function(){
		  $(this).css("display","none");
		  var title=$(this).attr("title");
		  $(".content"+title).addClass("zk");
		  $(".content"+title).removeClass("sq");
		  $(".shouq").css("display","block");
		  
	  });
	  $(".shouq").click(function(){
		  $(this).css("display","none");
		  var title=$(this).attr("title");
		  $(".content"+title).addClass("sq");
		  $(".content"+title).removeClass("zk");
		  $(".yl").css("display","block");
		  
	  });
	  
	  

	  
  });
  
  </script>


<body>
	<header class="bar bar-nav">
		<h1 class="title">我的文章</h1>
	</header>
	<div class="content" >
				   <c:if test="${empty wzs}">
		<p style="text-align:center;">空空如也...</p>
		</c:if>
		<div class="list-block" style="margin:0;">
	
			<ul>
	
				<c:forEach items="${wzs}" var="s">
					 <li>
             <div class="card" style="margin:0;">
      <div class="card-header"><b>${s.wzname }</b><c:if test="${s.status==2}"><code style="color:red;">审核未通过..</code></c:if><c:if test="${s.status==1}"><code style="color:green;">审核通过</code></c:if><c:if test="${s.status==0}"><code>正在审核..</code></c:if>
      <span class="yl" title="${s.id}" style="float:right;color:#7763D5;">预览全部</span>
      <span class="shouq" title="${s.id}" style="float:right;color:#7763D5;">收起</span></div>
      
      <div class="card-content">
        <div class="card-content-inner content${s.id} sq" >${s.articleContent}</div>
      </div>
      <div class="card-footer">${s.author}<span style="float:right;">${s.timec }</span></div>
    </div>
        </li>
				</c:forEach>
			</ul>
		</div>
	</div>



</body>
</html>
