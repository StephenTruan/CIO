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
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<script type="text/javascript" src='js/animate.js'></script>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
   <jsp:forward page="WEB-INF/main.jsp"></jsp:forward>
  </body>
</html>
