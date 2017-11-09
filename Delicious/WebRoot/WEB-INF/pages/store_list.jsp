<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>店铺列表</title>
    <meta charset="gb2312">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" type="text/css" href="../css/global.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.0&key=1325b2ff459661c1391a861d4aa88a77"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
    <script src="../js/jquery.min.js"></script>
    <script type="text/javascript">
      $(function(){
    	  /***************************************
    		由于Chrome、IOS10等已不再支持非安全域的浏览器定位请求，为保证定位成功率和精度，请尽快升级您的站点到HTTPS。
    		***************************************/
    		var lng=$("input[name='longitude']").val();
	    	var lat=$("input[name='latitude']").val();
	    	var position=$("input[name='position']").val();
	    	if(lng.length==0 || lat.length==0){//如果经纬度为空，则重新定位
	    		var map,geolocation;
	    	    //加载地图，调用浏览器定位服务
	    	    map = new AMap.Map('container',{
	    	        resizeEnable: true
	    	    });
	    	    map.plugin('AMap.Geolocation', function() {
	    	        geolocation = new AMap.Geolocation({
	    	            enableHighAccuracy: true,//是否使用高精度定位，默认:true
	    	            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
	    	            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
	    	            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
	    	            buttonPosition:'RB'
	    	        });
	    	        map.addControl(geolocation);
	    	        geolocation.getCurrentPosition();
	    	        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
	    	        AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
	    	    });
	    	}else{//若经纬度不为空，则直接获取数据
	    		$("span[class='position']").text(position);
	    		var userId=$("input[name]").val();
	    		$.ajax({
					type:"get",
					url:"http://www.cnmjw.com.cn/Delicious/storeHandler/getStoreListByJWD",
                    data:{"latitude":lat,"longitude":lng,"position":position,"data":new Date()},
					success:function(data){
                       for(var i=0;i<data.length;i++){
                    	   $("ul[class='store-list']").append(
                    		   "<li><a href='storeDetailUI?storeId="+data[i].id+"&userId="+userId+"' class='item-link item-content'>"+
                   	           "<div class='item-media'><img src="+data[i].imgsurl+" style='width:60px;height:60px;'></div>"+
                   	           "<div class='item-inner'>"+
                   	           "<div class='item-title-row' style='margin-top:16px;'>"+
                   	           "<div class='item-title' style='font-size:16px;'>"+data[i].storename+"</div>"+
                   	           "<div class='item-after' style='margin-right:16 px;'><img src='../image/star_grade.png'></div></div>"+
                   	           "<div class='item-subtitle' style='margin-top:5px;font-size:14px;'>店铺位置："+data[i].address+"</div>"+
                   	           "<div class='item-subtitle' style='margin-top:5px;'>"+
                   	           "<span style='font-size:14px;'>人均消费:"+data[i].per_capite+"</span>"+
                   	           "<span style='font-size:14px;float:right;margin-right:32px;'>"+data[i].distance+"km</span>"+
                   	           "</div><div class='item-text' style='margin-top:5px;'>"+
                   	           "<span style='background:#7763D5;color:#ffffff;padding:3px 5px;font-size:14px;'>消费规则</span>&nbsp;&nbsp;&nbsp;&nbsp;"+
                   	           "<span style='font-size:14px;'>"+data[i].storerule+"</span></div></div></a></li>");
                       }
					},
					error:function(data){
						alert("附近店铺获取失败");
					}
				});
	    	}
    	    //解析定位结果
    	    function onComplete(data){
    	    	var position=data.formattedAddress;
    	    	if(position.length>16){
    	    		position=position.substring(0,15)+"...";
    	    	}
    	    	$("span[class='position']").text(position);
    	    	$("input[name='longitude']").val(data.position.getLng());
    	    	$("input[name='latitude']").val(data.position.getLat());
    	    	$("input[name='position']").val(position);
    	    	//ajax获取附近店铺列表
    	    	var userId=$("input[name]").val();
    	    	$.ajax({
					type:"get",
					url:"http://www.cnmjw.com.cn/Delicious/storeHandler/getStoreListByJWD",
                    data:{"latitude":data.position.getLat(),"longitude":data.position.getLng(),"position":position,"data":new Date()},
					success:function(data){
                       for(var i=0;i<data.length;i++){
                    	   $("ul[class='store-list']").append(
                    		   "<li><a href='storeDetailUI?storeId="+data[i].id+"&userId="+userId+"' class='item-link item-content'>"+
                   	           "<div class='item-media'><img src="+data[i].imgsurl+" style='width:60px;height:60px;'></div>"+
                   	           "<div class='item-inner'>"+
                   	           "<div class='item-title-row' style='margin-top:16px;'>"+
                   	           "<div class='item-title' style='font-size:16px;'>"+data[i].storename+"</div>"+
                   	           "<div class='item-after' style='margin-right:16 px;'><img src='../image/star_grade.png'></div></div>"+
                   	           "<div class='item-subtitle' style='margin-top:5px;font-size:14px;'>店铺位置："+data[i].address+"</div>"+
                   	           "<div class='item-subtitle' style='margin-top:5px;'>"+
                   	           "<span style='font-size:14px;'>人均消费:"+data[i].per_capite+"</span>"+
                   	           "<span style='font-size:14px;float:right;margin-right:32px;'>"+data[i].distance+"km</span>"+
                   	           "</div><div class='item-text' style='margin-top:5px;'>"+
                   	           "<span style='background:#7763D5;color:#ffffff;padding:3px 5px;font-size:14px;'>消费规则</span>&nbsp;&nbsp;&nbsp;&nbsp;"+
                   	           "<span style='font-size:14px;'>"+data[i].storerule+"</span></div></div></a></li>");
                       }
					},
					error:function(data){
						alert("附近店铺获取失败");
					}
				});
    	    }
    	    //解析定位错误信息
    	    function onError(data) {
    	        alert("定位失败");
    	    }
    	    //搜索
    	    $(document).keydown(function (event){
    	    	var userId=$("input[name]").val();
    	        if(event.keyCode==13){
                  //判断搜索框是否有焦点
                  if($("#search").is(":focus")){
                	  var str=$("#search").val();
                	  //发送ajax获取搜索结果
                	  $.ajax({
     					 type:"get",
     					 url:"http://www.cnmjw.com.cn/Delicious/storeHandler/getSearchStoreByKeyWord",
                         data:{"keyWord":str,"data":new Date()},
     					 success:function(data){
     						$("ul[class='store-list']").find("li").remove();
     						for(var i=0;i<data.length;i++){
                         	   $("ul[class='store-list']").append(
                         		   "<li><a href='storeDetailUI?storeId="+data[i].id+"&userId="+userId+"' class='item-link item-content'>"+
                        	           "<div class='item-media'><img src="+data[i].imgsurl+" style='width:60px;height:60px;'></div>"+
                        	           "<div class='item-inner'>"+
                        	           "<div class='item-title-row' style='margin-top:16px;'>"+
                        	           "<div class='item-title' style='font-size:16px;'>"+data[i].storename+"</div>"+
                        	           "<div class='item-after' style='margin-right:16 px;'><img src='../image/star_grade.png'></div></div>"+
                        	           "<div class='item-subtitle' style='margin-top:5px;font-size:14px;'>店铺位置："+data[i].address+"</div>"+
                        	           "<div class='item-subtitle' style='margin-top:5px;'>"+
                        	           "<span style='font-size:14px;'>人均消费:"+data[i].per_capite+"</span>"+
                        	           "<span style='font-size:14px;float:right;margin-right:32px;'>"+data[i].distance+"km</span>"+
                        	           "</div><div class='item-text' style='margin-top:5px;'>"+
                        	           "<span style='background:#7763D5;color:#ffffff;padding:3px 5px;font-size:14px;'>消费规则</span>&nbsp;&nbsp;&nbsp;&nbsp;"+
                        	           "<span style='font-size:14px;'>"+data[i].storerule+"</span></div></div></a></li>");
                            }
     					 },
     					 error:function(data){
     						alert("搜索获取数据失败");
     					 }
         	        }); 
                  }
    	        }
    	    });
      });
    </script>
  </head>
  
  <body style="margin:0px auto;">
    <input name="userId" type="hidden" value="${userId }">
    <input type="hidden" name="latitude" value="${latitude }">
    <input type="hidden" name="longitude" value="${longitude }">
    <input type="hidden" name="position" value="${position }">
   <div class="content">
   
     <div class="bar bar-header-secondary">
       <div class="searchbar" style="background:#ffffff">
        <a class="searchbar-cancel">取消</a>
        <div class="search-input">
          <label class="icon icon-search" for="search" style="color:#7763D5;"></label>
          <input type="search" id='search' placeholder='店铺、美食搜索...'/>
        </div>
       </div>
     </div>
     
     <div style="height:50px;">
       <span style="float:left;position:relative;left:16px;top:50%;transform:translateY(-50%);">定位：<span class="position" style="color:#7763D5;">${position }</span></span>
     </div>
	 <div class="list-block media-list">
       <ul class="store-list">
       </ul>
	 </div>
    </div>
    <!-- 默认必须要执行$.init(),实际业务里一般不会在HTML文档里执行，通常是在业务页面代码的最后执行 -->
    <script>$.init()</script>
    <script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
    
  </body>
</html>
