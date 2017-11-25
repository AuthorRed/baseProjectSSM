<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片上传页面</title>
<style type="text/css">
#imgContainer {
    width: 1000px;
    height: 600px;
    background-color: #cdb74c;
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
</head>
<script>
    $(document).ready(function(){
       console.log('ready function works');
       $("#file").change(function () {
           $("#form1").ajaxSubmit({
               success: function (data) { 
               	console.log('这里是返回的图片url:'+data);
               	//var imgSrc="${pageContext.request.contextPath}"+data;
                   //$("returnImg").attr("src",data);
               	//$("imgContainer").append("<img src="+data+">");
               	addImg(data);
               },
               error: function (error) { alert(error); },
               url: '${pageContext.request.contextPath}/upload.do', /*设置post提交到的页面*/
               type: "post", /*设置表单以post方法提交*/
               dataType: "text" /*设置返回值类型为文本*/
           });
       });
    });

    function addImg(data){
        $("#imgContainer").append("<img src='"+data+"'>");
    }
</script>
<body>
<h1>号哥你最牛逼</h1>
<form id="form1" action="${pageContext.request.contextPath}/upload.do" method="post" enctype="multipart/form-data">    
<input id="file" type="file" name="file"/>
</form> 
<a href="javascript:void(0)" onclick="addImg()">添加图片</a>
<div id="imgContainer"></div>
</body>
</html>