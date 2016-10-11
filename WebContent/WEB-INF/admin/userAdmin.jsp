<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		border:1px solid black;
		border-radius:3px;
		margin-left: 40px;
		padding-top: 5px;
		width: 1000px;
	}
	th{
		background-color: orange;
	}
	tr{
		height: 40px;
	}
	td{
		width:20%;
		text-align: center;
	}
	div{
		margin-left:40px;
		width: 1000px;
		text-align: center;
	}
	a{
		text-decoration: none;
		color:blue;
		cursor: pointer;
	}
</style>
</head>
<font style="color: red;font-size: 15px;">${message }</font>
<body>
	<table>
		<tr><th>姓名</th><th>密码</th><th>部门</th><th>小组</th><th>删除</th></tr>
		<c:forEach items="${users }" var="user">
			<tr>
				<td>${user.userName }</td>
				<td>${user.password }</td>
				<c:choose>
					<c:when test="${user.sector=='1' }">
						<td>${name1 }</td>
					</c:when>
					<c:when test="${user.sector=='2' }">
						<td>${name2 }</td>
					</c:when>
					<c:when test="${user.sector=='3' }">
						<td>${name3 }</td>
					</c:when>
				</c:choose>
				<td>${user.groupId }</td>
				<td><a href="AdminCLServlet?method=userDelete&userId=${user.userId }">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	<div>
		<c:if test="${pageNow>1 }">
			<a href="AdminCLServlet?method=userAdmin&pageNow=${pageNow-1 }">上一页&nbsp;&nbsp;&nbsp;</a>
		</c:if>
		<c:forEach var="i" begin="1" end="${pageCount }" step="1">
			<a href="AdminCLServlet?method=userAdmin&pageNow=${i }">${i }&nbsp;&nbsp;&nbsp;</a>
		</c:forEach>
		<c:if test="${pageNow<pageCount }">
			<a href="AdminCLServlet?method=userAdmin&pageNow=${pageNow+1 }">下一页</a>
		</c:if>
	</div>
</body>
</html>