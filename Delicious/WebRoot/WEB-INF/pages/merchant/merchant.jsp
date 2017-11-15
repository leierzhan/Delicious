<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


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
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>



<style>
* {
	margin: 0;
	padding: 0;
}


</style>


</head>

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

<script type="text/javascript">
$(document).ready(function(){
	$(".go").click(function(){
		var url=$(this).attr("title");
		window.location.href=url;
	});
});
</script>


<body style="background-color: #eee;">

<header class="bar bar-nav">
  <h1 class="title">商户中心</h1>
</header>

  <p class="buttons-row" style="margin-top:60px; ">

  <a href="javascript:;" class="button sys" style="height:65px;border:none;">
 <img alt="" src="../imgsvg/sys.svg" style="width:40px;"/><br>扫一扫
 </a>
  <a href="http://127.0.0.1:8080/Delicious/merchant/goPage?url=nonum" class="button" style="height:65px;border:none;"> 
   <img alt="" src="../imgsvg/no.svg" style="width:40px;"/><br>输入号码</a>
  </p>
   <div class="list-block media-list" style="margin:0;" >
      <ul>
        <li>
          <a href="../storeHandler/storeDetailUI?storeId=${store.id}&userId=${userid}" class="item-link item-content" style="background-color:orange;">
            <div class="item-media"><img src="${store.imgsurl}" width="80"height="80"></div>
            <div class="item-inner"  style="color:#eee;">
              <div class="item-title-row">
                <div class="item-title">${store.storename}</div>
                <div class="item-after" >进入店铺</div>
              </div>
              <div class="item-subtitle">${store.address}</div>
              <div class="item-text" style="color:#eee;">${store.tel}</div>
            </div>
          </a>
        </li>
      </ul>
   </div>
     <div class="list-block" style="margin:0;">
      <ul>
    <li class="item-content">
          <div class="item-inner">
            <div class="item-title" style="color:#8d8d8d;">管理功能</div>
          </div>
        </li>
        </ul>
        </div>
        <div style="width:100%;height:auto;text-align:center;">
        
      <div class="row  no-gutter" style="margin:0 auto;" >
      
  
        <div class="col-40 go" title="../merchant/goUpdateStoreinfo" style="height:85px;background-color:#ddb23b;color:#eee;padding:8px;">
         <img alt="" src="../imgsvg/store.svg" style="width:40px;"/><br>
        店铺信息
         </div>
         
           
           
        <div class="col-60 go" title="../merchant/goGreens" style="	height:85px;background-color:#1288bb;color:#eee;padding:8px;">
          <img alt="" src="../imgsvg/cai.svg" style="width:40px;"/><br>
        菜品信息</div>
        
                
      </div>
      <div class="row no-gutter"  style="margin:0 auto;">
      <div class="col-100 go" title="../merchant/goChefList" style="height:85px;background-color:#66ddee;color:#eee;padding:8px;">
     <img alt="" src="../imgsvg/wenzhang.svg" style="width:40px;"/><br>     
        店铺厨师</div>
        </div>
        
      <div class="row no-gutter"  style="margin:0 auto;">
                    <div class="col-50 go" title="../merchant/goPage?url=wzlist" style="height:85px;background-color:#666666;color:#eee;padding:8px;">
     <img alt="" src="../imgsvg/wenzhang.svg" style="width:40px;"/><br>     
        店铺文章</div>
    

        <div class="col-50 go" title="../merchant/goPage?url=qianlist" style="height:85px;background-color:#3570dd;color:#eee;padding:8px;">
          <img alt="" src="../imgsvg/record.svg" style="width:40px;"/><br>
          
        收款记录</div>
     </div>
</div>

</body>
</html>
