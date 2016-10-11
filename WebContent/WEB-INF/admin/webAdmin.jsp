<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div{
		width: 700px;
		margin-top: 35px;
	}
	li{
		background-color: orange;
		border-radius:2px;
		margin-top:5px;
		box-shadow:1px 1px 1px #000000;
	}
	ul{
		list-style: none;
	}
	input {
		margin-left: 40px;
	}
</style>
</head>
  <font style="color: red;font-size: 15px;">${message }</font>
<body>
	<form action="AdminCLServlet?method=msg" method="post">
		<div>
			<ul>
				<li>主题名称：<input type="text" name="topic" value="${topic }" ></li>
			    <li>人群名称：<input type="text" name="name1" value="${name1 }" ><input id="name2" type="text" name="name2" value="${name2 }" ><input id="name3" type="text" name="name3" value="${name3 }" ></li>
			    <li>小组人数：<input type="text" name="groupUserCount" value="${groupUserCount }" ></li>
			    <li>系统说明：<textarea name="document" rows="6" cols="52" >${document }</textarea></li>
			</ul>
		</div>
		<input type="submit" onclick="return add()" value="提交">
	</form>
</body>
</html>