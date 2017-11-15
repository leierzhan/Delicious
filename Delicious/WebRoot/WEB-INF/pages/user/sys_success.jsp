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
color:#8d8d8d;
	margin: 0;
	padding: 0;
}
.userinfo{
height:68px;
background-color: white;
}
.userinfo img{
margin:14px;	
float:left;
width:40px;
height:40px;
}
.user_info{
float:left;
line-height: 68px;
}
.msblist{
margin-top:8px;
}
h2{color:orange;}
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
	<div class="userinfo" >
	<img alt="" src="${userinfo.headImgUrl}">
	<div class="user_info">${userinfo.nickname }
	<c:if test="${userinfo.truename!=null}">(${userinfo.truename })</c:if>
</div>
	
	</div>
	
	
		 <div class="list-block media-list msblist">
      <ul>
      
      <c:forEach items="${msbs}" var="m">
        <li style="border-radius:20px;">
          <label class="label-checkbox item-content" style="border-left:18px solid orange;">
            <input type="checkbox" name="checkbox">
            <div class="item-media"><i class="icon icon-form-checkbox"></i></div>
            <div class="item-inner">
              <div class="item-title-row">
                <div class="item-title"><h2>${m.num }</h2></div>
                <div class="item-after"><h6>${m.endtime}</h6></div>
              </div>
              <div class="item-subtitle" >
              <h4>
             <c:if test="${m.storeid==0 }">
             通用美食币
             </c:if>
                   <c:if test="${m.storeid>0 }">
             制定商家美食币
             </c:if>
             </h4>
              </div>
              <div class="item-text" style="color:#8d8d8d;"><code>可用</code></div>
            </div>
          </label>
        </li>
        </c:forEach>
        </ul>
        </div>
<button class="button button-fill" style="width:100%;position:fixed;bottom:0px;height:45px;line-height: 45px;">结账</button>
        </div>
</div>

</body>
</html>
