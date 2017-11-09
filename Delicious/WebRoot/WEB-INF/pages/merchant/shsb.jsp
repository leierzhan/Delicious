<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- 重点参数：iconStyle -->
<!doctype html>
<html lang="zh-CN">

<head>
    <!-- 原始地址：//webapi.amap.com/ui/1.0/ui/misc/PositionPicker/examples/positionPicker2.html -->
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <link rel="stylesheet"	href="../css/sm.min.css">
    <title>唇齿缘</title>

   
</head>

<body>

<div style="margin-top:30%;text-align:center;">
<img alt="" src="../imgsvg/shz.svg" style="width:100px;height:100px;"/>
<h2>审核失败！<br>
<span style="color:orange;">${yy}</span></h2>
<button title="${storeid}" >重新上传</button>
</div>
</body>

</html>