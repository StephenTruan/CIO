<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String flag = "0";
String s_flag = (String)request.getAttribute("flag");
if(s_flag!=null){
	flag = s_flag;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   	<title>填写意见</title>
	<script type="text/javascript" src='js/suggestion.js'></script>
	<script type="text/javascript" src='js/publish.js'></script>
	<link rel="stylesheet" type="text/css" href="css/suggestion.css">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<h1 class='title'>请填写您的意见</h1>
    <div id="box">
    <div class="color">
	<form id="form" method="post" action="AdviceAddCLServlet"  >
		<textarea id="txt" name="content"></textarea> 	
		<input type="hidden" id="flag" value=<%=flag %>>
		<input id="sub" name="submit" onclick="return publish()" type="submit" value="提交">
		<a href="ShowAdviceCLServlet"><button id="other" type="button" >查看</button></a>
	</form>
	</div>
	</div>
  </body>
</html>
