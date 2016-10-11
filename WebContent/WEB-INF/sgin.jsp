<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
	<link rel="stylesheet" type="text/css" href="css/base.css">
	<link rel="stylesheet" href="css/register.css">
	<script type="text/javascript" src='js/login.js'></script>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <div id="register">
		<h1>在线信息收集与调查系统</h1>
		<form action="UserSginCLServlet" method="post">
			<input class="name" name="userName" type="text" placeholder='用户名' maxlength="8">
			<input class="password" name="password" type="password" placeholder='密码' maxlength="15">
			<select class="select" name="sector">  
				<option value ="1">${name1 }</option>  
				<option value ="2">${name2 }</option>  
				<option value="3">${name3 }</option> 
			</select>  
			<input class="login" type="submit" value="注册">
		</form>
	</div>
  </body>
</html>
