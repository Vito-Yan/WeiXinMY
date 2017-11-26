<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
  	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
  	<!-- 引入 WeUI -->
    <link rel="stylesheet" href="css/weui.min.css">
    <title>登录</title>
    <script type="text/javascript">
 		/* $(function(){
 			$("#submit").click(function(){
 				$.ajax({
            		url:"login.do",
            		data:{"acc":$("#account").val()},
            		success:function(data){
            			$("#account").val();
        			}  
 				});
 			});
 		}); 		 */	 		
    </script>
  </head>
  
  <body ontouchstart>
  	<form action="login.do" method="post" >
	<div class="weui-cells weui-cells_form">
    	<div class="weui-cell">
        	<div class="weui-cell__hd"><label class="weui-label">学号</label></div>
        	<div class="weui-cell__bd">
            	<input class="weui-input" type="text" name="account" id="account" placeholder="请输入9位学号">
        	</div>
    	</div>
    	<div class="weui-cell">
        	<div class="weui-cell__hd"><label class="weui-label">密码</label></div>
        	<div class="weui-cell__bd">
            	<input class="weui-input" type="password" name="password" id="password" placeholder="默认：111">
        	</div>
    	</div>
	</div>
	<br/>
	<input type="submit" class="weui-btn weui-btn_primary" id="submit" value="绑定">
  	</form>
  </body>
</html>