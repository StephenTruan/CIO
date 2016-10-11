<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台管理</title>
	<link rel="stylesheet" type="text/css" href="css/admin.css">
    
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
    	<div class="head">后台管理</div>
		<div class="body">
			<div id="left" class="left">
				<ul id='list'>
					<li><a target="right" href="AdminCLServlet?method=webAdmin">网站管理</a></li>
					<li><a target="right" href="AdminCLServlet?method=userAdmin">成员管理</a></li>
					<li><a target="right" href="AdminCLServlet?method=adviceAdmin">意见管理</a></li>
					<li><a href="OutCLServlet">退&nbsp;&nbsp;&nbsp;&nbsp;出</a></li>
				</ul>
			</div>
			<div id="right" class="right">
				<iframe name="right" id='content' >
					
				</iframe>
			</div>
			<!-- <script type="text/javascript">
				var list=document.getElementById('list').getElementsByTagName('a');
				var content=document.getElementById('content');
				for(var i=0;i<list.length;i++){
					(function(i){
							list[i].onclick=function(){
							switch(i){
							case 0:
								content.src='AdminCLServlet?method=webAdmin';
								break;
							case 1:
								content.src='AdminCLServlet?method=userAdmin';
								break;
							case 2:
								content.src='AdminCLServlet?method=adviceAdmin';
								break;
							default:
								break;
							}
						}	
					})(i);
				} 
			</script> -->
			
			<script type="text/javascript">
				var left=document.getElementById('left');
				var right=document.getElementById('right');
				var h=0;
				h=document.documentElement.clientHeight;
				w=document.documentElement.clientWidth;
				left.style.height=h-110+'px';
				right.style.height=h-80+'px';
				right.style.width=w-165+'px';
				window.onresize=function(){
					h=document.documentElement.clientHeight;
					w=document.documentElement.clientWidth;
					left.style.height=h-110+'px';
					right.style.height=h-80+'px';
					right.style.width=w-165+'px';
				}
			</script>
		</div>
  </body>
</html>
