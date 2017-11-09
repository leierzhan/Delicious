<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
	<title>上传发票及店铺信息</title>
    <meta charset="gb2312">
    <meta charset="gb2312">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm.min.css">
    <link rel="stylesheet" href="//g.alicdn.com/msui/sm/0.6.2/css/sm-extend.min.css">
    <style>
		#fileBox{margin:1rem 0;}
		#fileBox label{display:block;float:left;height:45px;width:50px;background:url(../image/upload.svg) no-repeat center;}
		#fileBox .file-btn{height:45px;width:50px;margin:0 .5rem .5rem 0;opacity:0;}
		#fileBox .review-box{display:block;float:left;}
		#fileBox .review-box img{height:45px;width:50px;margin:0 .5rem .5rem 0;}
		#fileBox .prev-item{position:relative;display:inline-block;}
		#fileBox .prev-item .closebtn{position:absolute;right: -1px;top: -4px;display: block;height: 14px;width: 14px;color: #fff;font-size: 16px;line-height:14px; text-align: center;background: red;border-radius: 10px;}
		/******图片上传******/
    </style>
  </head>
  
  <body style="margin:0px auto;text-align:center;">
     <header class="bar bar-nav">
         <a class="icon icon-left pull-left" style="color:black;"></a>
         <h1 class="title">上传发票及店铺信息</h1>
     </header>
     
     <div class="content">
     <form action="uploadBillAndInfo" name="uploadBillAndInfo" method="post" enctype="multipart/form-data">
       <input type="hidden" id="articleId" name="articleId" value=${articleId } >
    <div class="list-block">
    <ul>
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-name"></i></div>
          <div class="item-inner">
          <div class="item-title label" style="top:15px;">上传发票</div>
            <!-- 上传照片 -->
            <div class="form-group">
              <div class="file-box clearboth" id="fileBox">
                <!--克隆的节点-->
                <label class="clone-dom" > <input type="file" class="file-btn" name="pictures"/></label>
                <!--克隆的节点-->
                <div class="review-box"></div>
              </div>
            </div>      
          </div>
        </div>
      </li>
      <!-- Text inputs -->
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-name"></i></div>
          <div class="item-inner">
            <div class="item-title label">店铺名称</div>
            <div class="item-input">
              <input type="text" placeholder="XX美食(必填项)" name="shopName">
            </div>
          </div>
        </div>
      </li>
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-name"></i></div>
          <div class="item-inner">
            <div class="item-title label">店铺地址</div>
            <div class="item-input">
              <input type="text" placeholder="具体到XX路XX号(必填项)" name="shopAddress">
            </div>
          </div>
        </div>
      </li>
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-name"></i></div>
          <div class="item-inner">
            <div class="item-title label">店铺介绍</div>
            <div class="item-input">
              <input type="text" placeholder="(选填)" name="shopDesc">
            </div>
          </div>
        </div>
      </li>
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-name"></i></div>
          <div class="item-inner">
            <div class="item-title label">品牌文化</div>
            <div class="item-input">
              <input type="text" placeholder="(选填)" name="shopCulture">
            </div>
          </div>
        </div>
      </li>
      <li>
        <div class="item-content">
          <div class="item-media"><i class="icon icon-form-name"></i></div>
          <div class="item-inner">
            <div class="item-title label">订餐电话</div>
            <div class="item-input">
              <input type="text" placeholder="(选填)" name="shopPhone">
            </div>
          </div>
        </div>
      </li>
    </ul>
  </div>
  <div class="content-block">
    <div class="row">
      <div class="col-100"><input type="submit" class="button button-big button-fill button-success" value="提交"></div>
    </div>
  </div>
  </form>
     </div>
     <!-- 默认必须要执行$.init(),实际业务里一般不会在HTML文档里执行，通常是在业务页面代码的最后执行 -->
    <script>$.init()</script>
    <script type='text/javascript' src='//g.alicdn.com/sj/lib/zepto/zepto.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm.min.js' charset='utf-8'></script>
    <script type='text/javascript' src='//g.alicdn.com/msui/sm/0.6.2/js/sm-extend.min.js' charset='utf-8'></script>
  </body>
  <script>
      //获取文件url
      function createObjectURL(blob){
          if (window.URL){
              return window.URL.createObjectURL(blob);
          } else if (window.webkitURL){
              return window.webkitURL.createObjectURL(blob);
          } else {
              return null;
          }
      }
      var box = $("#fileBox .review-box");    //显示图片box
      var file = $("#file"); //file对象
      var domFragment =  document.createDocumentFragment();   //文档流优化多次改动dom

      $("#fileBox").on("click",".file-btn",function(){
          var index = $(this).parent().index();
          if(index == 6){
              alert("最多可以上传4张图片！");
              return false;
          }
      });
      //触发选中文件事件
      $("#fileBox").on("change", ".file-btn", function(event){
          var imgNum = parseInt($("#fileBox .review-box img").length);
          if(imgNum < 4){
              var file = event.target.files;  //获取选中的文件对象
              var imgTag = $("<img src='' name='uploadImage'/>");
              var fileName = file[0].name;    //获取当前文件的文件名
              var url = createObjectURL(file[0]); //获取当前文件对象的URL
              //忽略大小写
              var jpg = (fileName.indexOf(".jpg") > -1) || (fileName.toLowerCase().indexOf(".jpg") > -1);
              var png = (fileName.indexOf(".png") > -1) || (fileName.toLowerCase().indexOf(".png") > -1);
              var jpeg = (fileName.indexOf(".jpeg") > -1) || (fileName.toLowerCase().indexOf(".jpeg") > -1);

              //判断文件是否是图片类型
              if(jpg || png || jpeg){
                  imgTag.attr("src",url);
              }else{
                  alert("请选择图片类型文件！");
              }

              //最佳显示
              var imgbox = $("<div class='prev-item'><span class='closebtn'>×</span></div>");
              imgbox.append(imgTag);
              box.append(imgbox);
              event.target.parentNode.style.display = "none";
              var cloneDom = $(".clone-dom").eq(0).clone().removeAttr("style");
              $("#fileBox").append(cloneDom);
          }
      });
      $(".review-box").on("click", ".prev-item", function(){
          var index = $(this).index();
          $(this).remove();
          $("#fileBox label:eq(" + (index + 1) + ")").remove();
      });
  </script>
</html>
