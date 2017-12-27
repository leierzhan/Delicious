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
.pass{
position:fixed;
bottom:0px;
width:100%;
height:50px;
display:none;

border:1px solid gray;
}
.pass input{
float:left;
width:25%;
height:48px;
text-align:center;
}
.alert{
position:fixed;
top:0px;
width:100%;
height:50px;
text-align:center;
line-height:50px;
background-color:orange;
color:red;
border:1px solid yellow;
z-index:100000;
display:none;
}
</style>


</head>

<script type="text/javascript">

$(document).ready(function(){
	 $("button").removeClass("button-warning");
	$('.jine').on('input propertychange', function() {  
		
	    if($(this).val()>=100){
	    	
	    	$(".ky0 code").text("可用");
	    	
	    	$(".box0").prop("checked",true);
	    	
	    	$(".box0").removeAttr("disabled");
	    	
	    }else{
	    	
	    	$(".ky0 code").text("不可用");
	    	
	    	$(".box0").prop("checked",false);
	    	
	    	$(".box0").attr("disabled","disabled");
	    	
	    }	

	    $("button").text("结账");
	    $("button").removeClass("button-warning");
	   }); 
	

	
	
	$(".b,input[name='checkbox']").click(function(){
	    $("button").text("结账");
		 $("button").removeClass("button-warning");
		  $(".pass").css("display","none");
		  $(".pass input").val("");
	});
	
	var dianpu=0;
	   var s=0;
$(document).on('click','.confirm-ok', function () {
	   var jine=$(".jine").val();
	   var man=$(".m").text();
	   var di=$(".d").text();
	   s=Math.floor(jine/man)*di;
	   var tongyong=0;
	   if($(".box0").is(":checked")){
	   tongyong=$("input[name='s0']").val();
	   }else{
		   s=0;
	   }
	   if(s>tongyong){
		   s=tongyong;
	   }
	   dianpu=0;
	   if($(".box").is(":checked")){
		   dianpu=$("input[name='s1']").val();
	   }else{
		   dianpu=0;
	   }
	  
	   var c0=$(".c0 h2").text();
	   var c=$(".c h2").text();
	    if(c0*1<tongyong*1||c*1<dianpu*1){
	    	   $(".subtext").html("<h3 style='color:red;'>输入的美食币不足</h3>");
	    	return false;
	    }
	   
	   var sy=jine-s-dianpu;
	   
	   if(sy<=0){
		   sy=0;
	   }
	     $(".subtext").html("<p>您得总消费金额是："+jine+"<br><span style='color:orange;'>需支付现金:"+sy+"</span>");
	     
	     
	     if(jine!=""){
	 
		    $(this).text("确认支付美食币 "+(1*s+1*dianpu));
		    $(this).addClass("button-warning");
		    
	     }
	     
	     
	     

	    
	    });
	    
$('.b').on('input propertychange',function() {
	 $("button").text("结账");
	 $("button").removeClass("button-warning");
	  $(".pass").css("display","none");
	  $(".pass input").val("");
});
	    
	    var input="<input type='password'/><input type='password'/><input type='password'/><input type='password'/>";
	    $(".pass").html(input);
	    
	    

	    $('.pass input').on('input propertychange',function() {
	    	var v=$(this).val().length;
	    	if(v==1){
	    	$(this).next().focus();
	    	}
	    	var l=true;
	    	var code="";
	    	$(".pass input").each(function(){
	    		if($(this).val()==""){
	    			l=false;
	    			code+=$(this).val()+"";
	    			
	    		}
	    		code+=$(this).val()+"";
	    			
	    	});
	    	
	    	if(l){
	    		
	    		  $(".pass").css("display","none");
	    		  $(".pass input").val("");
	    		//alert(code);
	    		$.ajax({
	    			url:"../page/passCheck",
	    			type:"POST",
	    			data:{
	    				ercode:code
	    			},
	    			success:function(data){
	    				if(data>0){
	    					$.ajax({
	    		    			url:"../order/orderManager/"+data+"/${code}/"+dianpu+"/"+s,
	    		    			type:"GET",
	    		    		
	    		    			success:function(da){
	    		    				if(da){
	    		    					window.location.href="../page/goUserCoreT";
	    		    				}
	    		    				
	    		    			},error:function(d){
	    		    				alert(d);
	    		    			}
	    					});
	    				}else{
   					    $(".alert").fadeIn("slow").fadeOut(3000);
   					    $(".alert").html("密码错误！");
	    				}
	    			}
	    			
	    		});
	    		
	    	}
	    	
	    });
	 
/* 	    var winHeight = $(window).height();   //获取当前页面高度
	    $(window).resize(function(){
	       var thisHeight=$(this).height();
	        if(winHeight - thisHeight >50){
	             //当软键盘弹出，在这里面操作
			
	             
	        }else{
	        	alert(1);
	            $(".pass").css({'bottom':0});

	        }
	    });  */
	    
$(document).on('click','.button-warning', function () {
	
	$(".pass").css("display","block");

});

});
</script>



<body>
<div class="alert"></div>
	    <div class="page-group">
    <div id="page-title-bar-btns" class="page">
  <header class="bar bar-nav">

 
    <h1 class="title"><a href="../order/orderManager/26/${code}/0/0" >用户结账</a></h1>
  </header>

	<div class="content">
    <div class="list-block media-list" style="margin-top:0px;margin-bottom:10px;">
      <ul>
	<li>
          <div class="item-content">
            <div class="item-media"><img src="${storeinfo.imgsurl}" width="44"></div>
            <div class="item-inner">
              <div class="item-title-row">
                <div class="item-title">${storeinfo.storename }</div>
              </div>
              <div class="item-subtitle" style="font-size:15px;">消费规则：<span style="color:orange;">满<code class="m">${storeinfo.man}</code>可用<code class="d">${storeinfo.di}</code></span></div>
            </div>
            	<input class="jine"  style="width:60%;height:100%;margin-left:20px;border:none;font-size:15px;" placeholder="结账金额" type="number" />
            
          </div>
        </li>
        </ul></div>
	
	
		 <div class="list-block media-list msblist">
      <ul>
      
      <c:forEach items="${msbs}" var="m">
        <li style="border-radius:20px;">
          <label class="label-checkbox item-content" style="border-left:18px solid orange;">
              <c:if test="${m.storeid==0 }">
                <input type="checkbox" class="box0"  disabled="disabled"  name="checkbox">
             </c:if>
             <c:if test="${m.storeid!=0 }">
            <input type="checkbox" class="box" name="checkbox">
            </c:if>
            <div class="item-media"><i class="icon icon-form-checkbox"></i></div>
            <div class="item-inner">
              <div class="item-title-row">
               <c:if test="${m.storeid==0 }">
                <div class="item-title c0"><h2>${m.num }</h2></div>
                </c:if>
                   <c:if test="${m.storeid!=0 }">
                <div class="item-title c"><h2>${m.num }</h2></div>
                </c:if>
                <div class="item-after"><h6>${m.endtime}</h6></div>
              </div>
              <div class="item-subtitle" >
              <h4>
             <c:if test="${m.storeid==0 }">
            通用美食币
               <input style="float:right;width:50px;" class="b" name="s0" value="${m.num }">
             </c:if>
                   <c:if test="${m.storeid>0 }">
             ${storeinfo.storename }商家美食币
                    <input style="float:right;width:50px;" class="b" name="s1" value="${m.num }">
             </c:if>
   
             </h4>
              </div>
              <div class="item-text ky${m.storeid}" style="color:#8d8d8d;"><code></code></div>
            
            </div>
          </label>
        </li>
        </c:forEach>
        </ul>
        </div>
        <div class="subtext"></div>
        
        
        
        
<button class="button button-fill button-warning confirm-ok" style="width:100%;position:fixed;bottom:0px;height:45px;line-height: 45px;">结账</button>
        </div>
        <div class="pass"></div>
</div>




</body>
</html>
