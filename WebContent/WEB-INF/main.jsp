<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
	<title>在线信息收集与调查系统</title>
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script type="text/javascript" src='js/animate.js'></script>
	<script type="text/javascript" src='js/main.js'></script>

    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <div id="box" class="box">
	<button id="control" type="button">系统使用说明</button>
	
	<div id='help'>
		<button id="back" type="button" >返回</button>
		<div class="content">
			每位成员最多发表一条意见，而且只能赞同一条小组意见，但可以赞同多条部门意见。
		</div>
	</div>
	<h1 class='head'>在线信息收集与调查系统</h1>
	<div id="admin"><a id="a" href="AdminCLServlet?method=toAdmin">管理员</a></div>
	<div id="explain">
		<p class="title">主题：${topic }</p>
		<hr>
		<p class="content">
			说明:${document }
		</p>
		<button class="register" onclick="sgin()" type="button">注册</button>
		<button class="login" onclick="login()" type="button">登录</button>
	</div>
	</div>
  </body>
</html>
