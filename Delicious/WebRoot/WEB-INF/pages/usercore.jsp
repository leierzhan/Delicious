<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>个人中心</title>
    <meta name="viewport" id="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=yes"/>
    <meta name="apple-mobile-web-app-capable" content="yes" />
	<link rel="stylesheet"	href="../css/sm.min.css" />
	<script type='text/javascript' 	src='../css/zepto.min.js' charset='utf-8'></script>
	<script type='text/javascript'	src='../css/sm.min.js' charset='utf-8'></script>
	<script type='text/javascript' src="../js/jquery.min.js" charset='utf-8'></script>
<script src="../css/jweixin.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <style>
  *{
  margin:0;
  padding:0;
  }
  .headline-bg{
	height:200px;
	overflow:hidden;
	background:-webkit-linear-gradient(top,#7763D5,#eeeeee);
	background:-moz-linear-gradient(top,#7763D5,#7763D5);
	background:-o-linear-gradient(top,#7763D5,#7763D5);
	background:-ms-linear-gradient(top,#7763D5,#eeeeee);
	text-align:center;
}
  .foot{
  width:100%;
  text-align:center;
  }
  .weui-footer__text{
  margin-bottom:10px;
  color:#8d8d8d;
  }
  .ecode{
  position: absolute;
  top:15px;
  color:orange;
  font-size:16px;
  right:23px;
  }
  .shower{
  width:100px;
  height:100px;
  border-radius:15px;
  }
  .show{
    display:none;
  }
  .sys{
  position: fixed;
  right:10px;
  color:white;
  
  }
  </style>
  
  <body>
  
  
<script type="text/javascript">
$(document).ready(function(){
	wx.config({
	    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: 'wx5ecac8f9f5725183', // 必填，公众号的唯一标识
	    timestamp: ${jsconfig.timestamp}, // 必填，生成签名的时间戳
	    nonceStr: '${jsconfig.noncestr}', // 必填，生成签名的随机串
	    signature: '${jsconfig.signature}',// 必填，签名，见附录1
	    jsApiList: [${jsconfig.apis}] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});

/* 	wx.ready(function () {
		 
		  document.querySelector('#checkJsApi').onclick = function () {
			  alert(1);
		    wx.checkJsApi({
		      jsApiList: [
		        ${jsconfig.apis}
		      ],
		      success: function (res) {
		        alert(JSON.stringify(res));
		      }
		    });
		  };
	}); */
	
	
	$(".sys").click(function(){
		wx.scanQRCode({
		    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
		    success: function (res) {
		    var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		    window.location.href=result;
		}
		});
		
	});
	
});
</script>
<%-- <img alt="" src="../imgsvg/sanjiao.svg" class="show" style="width:30px;position:absolute; top:40px;right:30px;">
<div class="show" style="width:100px;height:100px;border-radius:15px;position: absolute;right:20px;top:50px; background-color: white;">
<img src="../ercode/${userid}.jpg"  class="shower show"  >
</div> --%>
  
<a href="javascript:;" class="sys">扫一扫</a>
  	 <div class="headline-bg">
  	   <img  src="${ucp.headimg}"
  	    style='margin-top:15px;border:2px solid gray;width:70px;height:70px;border-radius:25px;'/>
  	   <h3 style='margin:0;text-align:center;color:#fff;'>${ucp.username}&nbsp;
  	   <code><c:if test="${ucp.sex=='1'}">男</c:if><c:if test="${ucp.sex=='0'}">女</c:if></code>
  	   </h3>
  	   <p style='margin-top:10px;'><a href="../page/goUserinfo" style='color:yellow;' >查看详细信息</a></p>
  	   </div>
  	      <div class="row no-gutter">
      <div class="col-50" style="text-align:center;border-right:1px dashed #ffffff;margin:7px 0 7px 0;"> 
      <a href="../page/goMsb?msb=${ucp.msb}"><span style='font-size:23px;color:orange;'>${ucp.msb}</span><br>
  	   <span style='color:#7763D5;'>我的枚士币</span></a>
  	   </div>
  	   
      <div class="col-50" style="text-align:center;margin-top:5px;margin:7px 0 7px 0;">
         <a href="../page/goStoreMsb?msb=${ucp.zdmsb}"><span style='font-size:23px;color:orange;'>${ucp.zdmsb}</span><br>
  	   <span style='color:#7763D5;'>指定店铺枚士币</span></a>
  	   </div>
    </div>
  	   <div class="card"  style="margin:0;">
    <div class="card-content">
      <div class="list-block" style="height:50px;line-height: 50px;">
        <ul>
          <li>
            <a href="../page/goMyWz" class="item-link item-content">
              <div class="item-media"><i class="icon icon-f7"><span class="icon icon-share"></span></i></div>
              <div class="item-inner">
                <div class="item-title">我的文章</div>
                <c:if test="${ucp.wzstatus==1}"><div style="float:right;color:red;"><code>新</code></div></c:if>
              </div>
            </a>
          </li>
        </ul>
      </div>
    </div>
      <div class="card-content">
      <div class="list-block"  style="height:50px;line-height: 50px;">
        <ul>
          <li>
            <a href="../page/goMyCollect" class="item-link item-content">
              <div class="item-media"><i class="icon icon-f7"><span class="icon icon-computer"></span></i></div>
              <div class="item-inner">
                <div class="item-title">我的收藏</div>
                  <c:if test="${ucp.scstatus==1}"><div style="float:right;color:red;"><code>新</code></div></c:if>
              </div>
            </a>
          </li>
        </ul>
      </div>
    </div>
      <div class="card-content">
      <div class="list-block"  style="height:50px;line-height: 50px;">
        <ul>
          <li>
            <a href="../page/goMyHistory" class="item-link item-content"><span class="icon icon-cart"></span>
              <div class="item-media"><i class="icon icon-f7"></i></div>
              <div class="item-inner">
                <div class="item-title">我的足迹</div>
                  <c:if test="${ucp.zjstatus==1}"><div style="float:right;color:red;"><code>新</code></div></c:if>
              </div>
            </a>
          </li>
        </ul>
      </div>
    </div>
    
      <div class="card-content">
      <div class="list-block"  style="height:50px;line-height: 50px;">
        <ul>
          <li>
            <a href="../page/goMyNotice" class="item-link item-content">
              <div class="item-media"><i class="icon icon-f7">  <span class="icon icon-message"></span></i></div>
              <div class="item-inner">
                <div class="item-title">我的通知</div>
                  <c:if test="${ucp.tzstatus==1}"><div style="float:right;color:red;"><code>新</code></div></c:if>
              </div>
            </a>
          </li>
        </ul>
      </div>
    </div>
    
   </div>
   
   
   
   
   
   
   <div class="foot">
     <p><a href="javascript:void(0);" >唇齿缘</a></p>
                <p class="weui-footer__text">Copyright &copy; 2010-2017 CCY</p>
   
   
   </div>
  		
  </body>
</html>
