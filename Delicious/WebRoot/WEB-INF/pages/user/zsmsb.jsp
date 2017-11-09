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


<style>
* {
	margin: 0;
	padding: 0;
}

.zq {
	width: 150px;
	height: 35px;
	border-radius: 15px;
	border: 1px solid #eeeeee;
	text-align: center;
	margin: 0 auto;
}

.zq a {
	line-height: 35px;
	width: 100%;
	color: #eeeeee;
}
.cardtel{
display:none;
}
</style>


</head>

<script type="text/javascript">
  $(document).ready(function(){
	 $("input[type='checkbox']").click(function(){
		 var id=$(this).attr("title");
		// alert(id+$(this).attr("type"));
		 
		 	if($(this).prop('checked')){
		 	
				var num= $(".id"+id+">.numshow").text();
				$(".id"+id+" input[name='num']").val(num);
				 $(".id"+id+" input[name='num']").css("display","inline-block");
				 $(".id"+id+">.numshow").css("display","none");
			 }else{
				 $(".id"+id+"  input[name='num']").css("display","none");
				 $(".id"+id+" >.numshow").css("display","inline-block");
			 }
	 });
	 $("input[name='tel']").bind("input propertychange change",function(event){
	        var tel=$("input[name='tel']").val();
	        if(tel.length==11){
	        	$.ajax({
	        		type:"post",
	        		url:"../page/goCheckUser",
	        		data:{
	        			tel:tel
	        		},success:function(d){
	        			if(d==""){
	        				$("input[name='tel']").css("border-color","red");
	        			}else{
	        				$("input[name='tel']").css("border-color","green");
	        				
	        			}
	        		}
	        		
	        	});
	        }else{
	        	$("input[name='tel']").css("border-color","red");	
	        }
	        
	    	
	        
	});
	 
	 $("#zs").click(function(){
		 $(".cardtel").css("display","block");
	 });
	 $(".qx").click(function(){
		 $("input[name='tel']").val("");
		 $(".cardtel").css("display","none");
	 });
	 
	 $(".qdzs").click(function(){
		var tel= $("input[name='tel']").val();
		 var ids="";
		 var nums="";
		 $("input[name='id']").each(function(){
			if($(this).prop('checked')){
				ids+=$(this).attr("title")+",";
				nums+=$(".id"+$(this).attr("title")+"> input[name='num']").val()+",";
			}
		 });
		 $.ajax({
     		type:"post",
     		url:"../page/msbZs",
     		data:{
     			ids:ids,
     			nums:nums,
     			tel:tel
     		},success:function(d){
     			//alert(d);
     			if(d==0){
     				$(".cardtel").css("border-color","red");
     				
     			}else{
     				$(".cardtel").css("border-color","green");
     				$(".cardtel >.card-content").html("<p>赠送成功</p>");
     				setInterval(function(){
     					$(".cardtel").css("display","none");
     					window.history.go(0);
     				}, 1000);
     			}
     		}
     		
     	});
		 
		 
	 });

	  
  });
  
  </script>


<body>
	<header class="bar bar-nav">
		<h1 class="title">转赠</h1>
	</header>
	<div class="content">
		<div class="list-block" style="margin:0;">
			<ul>

				<c:forEach items="${msb}" var="s">
					<li>
						<div class="item-content" style="height:80px;">
							<div class="item-media">
								<i class="icon icon-form-toggle"></i>
							</div>
							<div class="item-inner" style="height:80px;">
								<div class="item-title label" style="height:35px;">
									<c:if test="${s.storeid==0}">
										<span style="color:blue;">通用币</span>
									</c:if>
									<c:if test="${s.storeid>0}">
										<span style="color:orange;">${s.storename}</span>
									</c:if>
								</div>
								<div class="item-input id${s.id}">
									<span class="numshow">${s.num}</span>
									 <input style="width:41px;display:none;" placeholder="转出数量" name="num"	value="${s.num}" />
									  <label class="label-switch"style="float:right;"> 
										<input name="id" title="${s.id}" type="checkbox">
										<div class="checkbox id"></div>
									</label>

								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="content-block"
		style="position:fixed;bottom:0px;margin:0;width:100%;">
		<div class="row no-gutter" style="width:100%;">
			<div class="col-50">
				<a href="javascript:window.history.go(-1);"
					class="button button-big button-fill button-danger">返回</a>
			</div>
			<div class="col-50">
				<a href="javascript:void();" class="button button-big button-fill button-success" id="zs">赠送</a>
			</div>
		</div>
	</div>
	
	<div class="card cardtel" style="height:auto;margin-top:80%;padding:23px;">
      <div class="card-content">
       <div class="card-header">赠送号码</div>
        <div class="card-content-inner telb">
        <input placeholder="手机号" name="tel" style="width:100%;height:40px;margin-top:5px;"></div>
  <div class="row">
        <div class="col-50  qx"><a href="javascript:void();" class="button button-big button-fill button-danger">取消</a></div>
        <div class="col-50"><a href="javascript:void();" class="button button-big button-fill button-success qdzs">确定赠送</a></div>
      </div>      </div>
    </div>


</body>
</html>
