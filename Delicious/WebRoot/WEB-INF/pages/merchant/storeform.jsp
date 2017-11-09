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
<script src="../js/ajaxfileupload.js"></script>

    <script type="text/javascript" src='//webapi.amap.com/maps?v=1.4.0&key=1325b2ff459661c1391a861d4aa88a77&plugin=AMap.ToolBar'></script>
    <!-- UI组件库 1.0 -->
    <script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>


<style>
* {
	margin: 0;
	padding: 0;
}
.item-input img{
float:left;
margin:3px;
}
.item-input button{
float:left;
margin:5px;
font-size:15px;
}
.item-input div{
float:left;
margin:5px;
}

.showimg{
display:none;
}
.yshowimg{
display:none;
}
 .map {
 display:none;
    
    	z-index:99;
        height: 100%;
        width: 100%;
    }
    
    #bottom {
    display:none;
    z-index:99;
        color: #444;
        background-color: #fff;
        width: 100%;
      	position: fixed;
      	left:0;
      	bottom: 1px;
      
    }
    
    #start{
    width:100%;
    height:40px;
        font-weight: 600;
        padding-left: 15px;
        padding-top: 4px;
        border:1px solid #234eef;
        background-color: #fff;
        color:#234eef;
    }
    
</style>


</head>
<%
String imgurl="http://192.168.5.172:8080/Delicious/"; %>
<script type="text/javascript">
$(document).ready(function(){
	
/* 	$("#simg").on("change",function(){ 
		 var fileCount = document.getElementById("simg").files.length;
			$(".snum>span").text(fileCount);
		
	}); */
	var imgs="";
	$(".ssc").click(function(){
		
		$.ajaxFileUpload  
		   (    
		        {   
		            url: '../merchant/uploadGoodsImgs?type=1', //上传文件附带参数
		            secureuri:false,  
		            fileElementId:"simg",  
		            dataType: 'JSON', 			
		            success: function (data)  
		            { 
		            	imgs+=data+",";
		            	$(".simg").val(imgs);
		            	//document.getElementById("simg").value="";
		            	var s=data.split(",");	
		            	var html="";
		            
		            	
		            	for (var i = 0; i < s.length; i++) {
		            		html+="<img  src='<%=imgurl%>merchant_img/"+s[i]+"' class='simgsrc img"+init+"' style='margin-left:10px;margin-top:10px;margin-bottom:10px;width:120px;height:120px; float:left;'><img class='delimg' src='../imgsvg/gb.svg' alt='"+init+"' title='"+s[i]+"' style='cursor:pointer;z-index:10;width:25px;position:relative;right:12px;top:-5px;' />";
							init++;
							}
		            	//alert(html);
		            	$(".showimgs").append(html);
		            	$(".showimg").css("display","block");
		            },  
		            error: function (data, status, e)  
		            {    
		               alert("上传图片失败!"+status);  
		            }  
		        }  
		    );
	});
	
	
	var yimgs="";
	var init=0;
$(".ysc").click(function(){
		
		$.ajaxFileUpload  
		   (    
		        {   
		            url: '../merchant/uploadGoodsImgs?type=2', //上传文件附带参数
		            secureuri:false,  
		            fileElementId:"yimg",  
		            dataType: 'JSON', 			
		            success: function (data)  
		            { 
		            	yimgs+=data+",";
		            	$(".yimg").val(yimgs);
		            	//document.getElementById("simg").value="";
		            	var s=data.split(",");
		            	var html="";
		            
		            	
		            	for (var i = 0; i < s.length; i++) {
							html+="<img  src='<%=imgurl%>merchant_img/"+s[i]+"' class='yimgsrc img"+init+"' style='margin-top:10px;margin-bottom:10px;margin-left:20%;width:60%; float:left;'><img class='delimg' src='../imgsvg/gb.svg' alt='"+init+"' title='"+s[i]+"' style='cursor:pointer;z-index:10;width:25px;position:relative;right:12px;top:-5px;' />";
							init++;
		            	}
		            	//alert(html);
		            	$(".yshowimgs").append(html);
		            	$(".yshowimg").css("display","block");
		            },  
		            error: function (data, status, e)  
		            {    
		               alert("上传图片失败!"+status);  
		            }  
		        }  
		    );
	});
	
	$(document).on("click",".delimg",function(){
		var i=$(this).attr("alt");
		$(this).remove();
	
		$(".img"+i).remove();
	});
	
	
	function getimg(){
		simgsrc="";
		yimgsrc="";
		$(".simgsrc").each(function(){
			simgsrc+=$(this).attr("src")+",";
			
		});
		$(".yimgsrc").each(function(){
			yimgsrc+=$(this).attr("src")+",";
			
		});
		
		
		alert(simgsrc);
		alert(yimgsrc);
	}
	
	var simgsrc="";
	var yimgsrc="";
	//店铺添加提交
	$(".tj").click(function(){
		getimg();
		
		
		var imgs=simgsrc;
		var dname=$("input[name='dname']").val();
		var address=$("textarea[name='address']").val();
		var rule=$("select[name='storerule']").val();
		var renjun=$("input[name='renjun']").val();
		var tel=$("input[name='tel']").val();
		var pp=$("textarea[name='pp']").val();
		var yimg=yimgsrc;
		var focus=$("#lnglat").text();
		
		$.ajax({
			type:"POST",
			url:"../merchant/addStoreupdate",
			data:{
				imgs:imgs,
				name:dname,
				address:address,
				storerule:rule,
				tel:tel,
				renjun:renjun,
				pp:pp,
				yimg:yimg,
				focus:focus
			},
			success:function(data){
				if(data>0){
				window.location.href="../merchant/goPage?url=shz";
				}else{
				window.history.go(-1);
				}
			},error:function(d,e){
				alert(e);
			}
			
		});
		
		
		
	});
	
	
	$(".dttt").click(function(){
		$(".map").css("display","block");
		$("#bottom").css("display","block");
		
	});

	$("#start").click(function(){
		
		$(".map").css("display","none");
		$("#bottom").css("display","none");
		
		var ad=$("#address").text();
		$("textarea[name='address']").val(ad);
		
	});
	

	
});

</script>

    <script type="text/javascript">
    
    AMapUI.loadUI(['misc/PositionPicker'], function(PositionPicker) {
        var map = new AMap.Map('container', {
            zoom: 10,
            scrollWheel: false
        });
        var positionPicker = new PositionPicker({
            mode: 'dragMarker',
            map: map,
            iconStyle: { //自定义外观
                url: '//webapi.amap.com/ui/1.0/assets/position-picker2.png',
                ancher: [24, 40],
                size: [48, 48]
            }
        });

        positionPicker.on('success', function(positionResult) {
            document.getElementById('lnglat').innerHTML = positionResult.position;
            document.getElementById('address').innerHTML = positionResult.address;
            document.getElementById('nearestJunction').innerHTML = positionResult.nearestJunction;
            document.getElementById('nearestRoad').innerHTML = positionResult.nearestRoad;
            document.getElementById('nearestPOI').innerHTML = positionResult.nearestPOI;
        });
        positionPicker.on('fail', function(positionResult) {
            document.getElementById('lnglat').innerHTML = ' ';
            document.getElementById('address').innerHTML = ' ';
            document.getElementById('nearestJunction').innerHTML = ' ';
            document.getElementById('nearestRoad').innerHTML = ' ';
            document.getElementById('nearestPOI').innerHTML = ' ';
        });
  
        positionPicker.start();

        map.addControl(new AMap.ToolBar({
            liteStyle: true
        }));
    });
</script>


<body style="background-color: #eee;">

<header class="bar bar-nav">
  <h1 class="title">店铺信息</h1>
</header>
<div class="content" style="margin-top:-35px;">
    <div class="list-block">
      <ul>
        <!-- Text inputs -->
        <li class="align-center">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">店铺图片</div>
              <div class="item-input">
              			<input class="simg" style="display:none;"/>
                      	<div class="button pull snum" style="width:60px;height:60px;padding-top:15px;padding-left:25px;"><span>+</span>
                      	<input id="simg" name="files"  style="position:relative;right:30px; width:60px;height:60px;opacity:0;" type="file" multiple="multiple"></div>
              <button class="button pull ssc"  style="width:60px;height:60px;">上传</button>
              </div>
            </div>
          </div>
        </li>
        
       <li class="align-top showimg">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-input showimgs">
                
              </div>
            </div>
          </div>
        </li>
        
        
          <li class="align-center">

        
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">店名</div>
              <div class="item-input">
                <input type="text" name="dname" placeholder="店铺名称">
              </div>
            </div>
          </div>
        </li>
        <!-- Date -->
       
          <li class="align-center">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">地址</div>
              <div class="item-input " >
                <textarea  name="address" placeholder="详细地址" style="width:80%;float:left;" ></textarea>
                <a href="javascript:;" class="dttt" style="width:20%;float:left;margin-top:30px;">地图</a>
              </div>
            </div>
          </div>
        </li>
        <li class="align-center">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">电话</div>
              <div class="item-input">
                <input type="tel" name="tel" placeholder="联系电话" />
              </div>
            </div>
          </div>
        </li>
            <li class="align-center">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">人均消费</div>
              <div class="item-input">
                <input type="text" name="renjun" placeholder="人均" />
              </div>
            </div>
          </div>
        </li>
            <li class="align-center">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">消费规则</div>
              <div class="item-input">
                   <select name="storerule">
                   <c:forEach items="${rules}" var="r">
                    <option value="${r.id }">满${r.man }元，可用${r.di}元	</option>
                   </c:forEach>
                </select>
              </div>
            </div>
          </div>
        </li>
            <li class="align-center">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">品牌文化</div>
              <div class="item-input">
                <textarea  placeholder="品牌文化/店铺故事" name="pp" ></textarea>
              </div>
            </div>
          </div>
        </li>

          <li class="align-center">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-title label">营业执照</div>
              <div class="item-input">
              			<input class="yimg" style="display:none;"/>
                      	<div class="button pull ynum" style="width:60px;height:60px;padding-top:15px;padding-left:25px;"><span>+</span>
                      	<input id="yimg" name="files"  style="position:relative;right:30px; width:60px;height:60px;opacity:0;" type="file" multiple="multiple"></div>
              <button class="button pull ysc"  style="width:60px;height:60px;">上传</button>
              </div>
            </div>
          </div>
        </li>
        
       <li class="align-top yshowimg">
          <div class="item-content">
            <div class="item-inner">
              <div class="item-input yshowimgs">
              </div>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div class="content-block">
      <div class="row">
        <div class="col-100"><a href="#" class="button button-big button-fill button-success tj">申请入驻</a></div>
      </div>
    </div>
  </div>
  
  
  
    <div id="container" class="map" tabindex="0"></div>
    <div id='bottom'>
        <div>
            <button id='start'>确定地点</button>
        </div>
        <div>
            <div id='lnglat'></div>
            <div id='address'></div>
            <br><br><br>
  
        </div>
    </div>
  

</body>
</html>
