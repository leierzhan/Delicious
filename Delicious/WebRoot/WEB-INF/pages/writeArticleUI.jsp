<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>投稿免费吃</title>
    <meta charset="gb2312">
    <meta http-equiv="X-UA-Co mpatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/webuploader.min.js"></script>
    <script src="../js/Eleditor.js"></script>
  </head>
  
  <body style="margin:0px auto;text-align:center;">
    <input type="hidden" name="userId" value="${userId }">
    <input type="hidden" name="articleId" value="${articleId }">
    
    <div id="articleEditor">
        
    </div>
         
    <script>
    var userId=$("input[name='userId']").val();
    var articleTitle=$("p[name='title']").text();
    var Edr = new Eleditor({
                el: '#articleEditor', //容器
                //placeHolder: '请输入内容',
                upload:{ //上传配置
                   server: 'http://www.cnmjw.com.cn/Delicious/articleHandler/uploadImage', //上传路径
                   compress: true, //上传前是否压缩图片
                   fileSizeLimit: 6 //限制图片上传大小，单位M
                },
                toolbars: [
                           'insertText',
                           'editText',
                           'insertImage',
                           'insertLink',
                           'deleteBefore',
                           'deleteAfter',
                           'insertHr',
                           'deleteThis',
                           //自定义一个按钮
                           {
                             id: 'changeIndent',
                             name: '保存提交',
                             handle: function(select,controll){//回调返回选择的dom对象和控制按钮对象
                               //上传文章内容，并跳转页面，上传发票及填写店铺地址
                               //执行ajax
                               $.ajax({
               					type:"post",
               					url:"saveArticleContent",
               					data:{
               						articleTitle:articleTitle,
               						articleContent:Edr.getContent(),
               						userId:userId,
               					},
               					success:function(data){
               						window.location.href = "uploadBillUI?userId="+userId+"&articleId="+data+"";
               					},
               					error:function(data){
               						alert("文章上传失败！");
               					}
               				});
                             }
                         },
                         'cancel',
                       ]
        });
    //请记住下面常用方法---------------------------------------->
    //Edr.append( str ); 往编辑器追加内容
    //Edr.getContent(); 获取编辑器内容
    //Edr.getContentText(); 获取编辑器纯文本
    //Edr.destory(); 移除编辑器
    </script>
       
  </body>
</html>
