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

</script>



<body>
	    <div class="page-group">
    <div id="page-title-bar-btns" class="page">
  <header class="bar bar-nav">

 
    <h1 class="title">用户结账</h1>
  </header>

	<div class="content">
用户id：${code}

        </div>

</div>
</div>

</body>
</html>
