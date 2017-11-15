<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>唇齿缘</title>
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

  </head>
  
  <body>
    <header class="bar bar-nav">
    <h1 class="title">用户信息</h1>
  </header>
  <form action="../page/updateUserinfo" style="margin-top:-10px;">
  <input value="${userinfo.openId}" name="openId" type="hidden">
  <div class="content" style="width:94%;margin:20px auto;border-radius:8px;">
    <div class="list-block">
      <ul>
        <!-- Text inputs -->
        <li>
          <div class="item-content">
            <div class="item-media"><i class="icon icon-form-name"></i></div>
            <div class="item-inner">
              <div class="item-title label">真实姓名</div>
              <div class="item-input">
                <input name="truename" type="text" placeholder="你的名字" value="${userinfo.truename }">
              </div>
            </div>
          </div>
        </li>
        <li>
          <div class="item-content">
            <div class="item-media"><i class="icon icon-form-email"></i></div>
            <div class="item-inner">
              <div class="item-title label">电话</div>
              <div class="item-input">
                <input name="tel" type="tel" placeholder="电话" value="${userinfo.tel}">
              </div>
            </div>
          </div>
        </li>
        <li>
          <div class="item-content">
            <div class="item-media"><i class="icon icon-form-password"></i></div>
            <div class="item-inner">
              <div class="item-title label">邮箱</div>
              <div class="item-input">
                <input type="email" name="email" placeholder="邮箱" value="${userinfo.email}">
              </div>
            </div>
          </div>
        </li>
        <li>
          <div class="item-content">
            <div class="item-media"><i class="icon icon-form-gender"></i></div>
            <div class="item-inner">
              <div class="item-title label">学历</div>
              <div class="item-input">
								<select name="education">
								<c:if test="${userinfo.education==null}"> 
								<option selected="selected" value="">请选择学历</option>
								</c:if>
								<c:if test="${userinfo.education!=null}"> 
								<option selected="selected" value="${userinfo.education}">${userinfo.education}</option>
								</c:if>
									<option value="硕士" >硕士</option>
									<option value="博士">博士</option>
									<option  value="本科">本科</option>
									<option value="大专">大专</option>
									<option value="中专">中专</option>
									<option value="高中">高中</option>
									<option value="小学">小学</option>
								</select>
							</div>
            </div>
          </div>
        </li>
        <!-- Date -->
        <li>
          <div class="item-content">
            <div class="item-media"><i class="icon icon-form-calendar"></i></div>
            <div class="item-inner">
              <div class="item-title label">生日</div>
              <div class="item-input">
                <input type="date" name="brithday" placeholder="Birth day" value="${userinfo.brithday }">
              </div>
            </div>
          </div>
        </li>
        <!-- Switch (Checkbox) -->
        <li>
          <div class="item-content">
            <div class="item-media"><i class="icon icon-form-toggle"></i></div>
            <div class="item-inner">
              <div class="item-title label">住址</div>
              <div class="item-input">
               <textarea name="address" rows="1" cols="8" placeholder="暂时居住地" >${userinfo.address}</textarea>
              </div>
            </div>
          </div>
      </ul>
    </div>
    <div class="content-block">
      <div class="row">
        <a href="../page/goUserCoreT" class="button button-big button-fill button-danger col-50">取消</a>
        <button type="submit" class="button button-big button-fill button-success col-50">提交</button>
      </div>
    </div>
  </div>
  
  </form>
  
  </body>
</html>
