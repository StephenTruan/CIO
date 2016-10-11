<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#title{  
      text-align:center;  
     }  
     ul li{  
       list-style-type:none;  
       display:inline;  
     } 
     a{
		text-decoration: none;
		color:blue;
		cursor: pointer;
	}
	table{
		border-collapse: collapse;
		border:1px solid black;
		border-radius:3px;
		padding-top: 5px;
		width: 1000px;
	}
	th{
		background-color: orange;
	}
	tr{
		height: 40px;
	}
	div{
		margin-left:40px;
		width: 1000px;
		text-align: center;
	}
</style>
</head>
<body>

	<div id="title">
		<ul>
			<li><a href="AdminCLServlet?method=adviceAdmin&sector=1">${name1 }部门提交的数据&nbsp;&nbsp;||&nbsp;&nbsp;</a></li>
			<li><a href="AdminCLServlet?method=adviceAdmin&sector=2">${name2 }部门提交的数据&nbsp;&nbsp;||&nbsp;&nbsp;</a></li>
			<li><a href="AdminCLServlet?method=adviceAdmin&sector=3">${name3 }部门提交的数据</a></li>
		</ul>
	</div>

	
	<table align="center" border="1px" >
		<tr><th width="90%">内容详情</th><th width="10%">赞同数量</th></tr>
		<c:forEach items="${advices }" var="advice">
			<tr>
				<td>${advice.content }</td>
				<td>${advice.adviceAgreeCount }</td>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	<div>
		<c:if test="${pageNow>1 }">
			<a href="AdminCLServlet?method=adviceAdmin&pageNow=${pageNow-1 }&sector=${sector }">上一页&nbsp;&nbsp;&nbsp;</a>
		</c:if>
		<c:forEach var="i" begin="1" end="${pageCount }" step="1">
			<a href="AdminCLServlet?method=adviceAdmin&pageNow=${i }&sector=${sector }">${i }&nbsp;&nbsp;&nbsp;</a>
		</c:forEach>
		<c:if test="${pageNow<pageCount }">
			<a href="AdminCLServlet?method=adviceAdmin&pageNow=${pageNow+1 }&sector=${sector }">下一页</a>
		</c:if>
	</div>
</body>
</html>