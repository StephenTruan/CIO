<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" import="java.util.*,com.hbkj.model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ArrayList<AdviceBean> groupList = (ArrayList<AdviceBean>)request.getAttribute("groupList");
ArrayList<AdviceBean> sectorList = (ArrayList<AdviceBean>)request.getAttribute("sectorList");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>表决</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/content.css">

  </head>
  
<body style="background:url(image/indexBg.png)">
<div class="box">
            <div class="h">表决<div align="right"><a href="OutCLServlet"><font size="3px" style="color: white;">[退出]</font></a></div></div>
            
			<form action="TakeVoteCLServlet" method="post">
            <table class="table" border='1'>
            <thead>
               <tr>
                      <th width="1000px" height="60">已筛选的意见</th>
                      <th width="100px">赞同</th>
                </tr>
            </thead>
            <tbody class="tbody">
			 <%  if(sectorList.size()>0){
				 AdviceBean sectorAdvice = null;
				 for(int i = 0; i<sectorList.size();i++){ 
					 sectorAdvice = sectorList.get(i);
			 %>
                  <tr>
                      <td><%=sectorAdvice.getContent() %></td>
                      <td>
                          <input type="checkbox" name="sectorAdviceId" value="<%=sectorAdvice.getAdviceId() %>" />
                      </td>
                  </tr>
			  <%
				  }
				  	}else{
			  %>
				  <tr align="center">
				  	<td colspan="2" >抱歉！还没有任何一条意见通过筛选！</td>
				  </tr>
			  <%
			  	}
			  %>
            </tbody>
            </table>
             <table class="table" border='1'>
            <thead>
               <tr>
                      <th width="1000px" height="60">组内未通过筛选意见</th>
                      <th width="100px">赞同</th>
                </tr>
            </thead>
            <tbody class="tbody">
			<% if(groupList.size()>0){
					AdviceBean groupAdvice = null;
					for(int i = 0; i<groupList.size();i++){ 
						groupAdvice = groupList.get(i);
			%>
                 <tr>
                      <td><%=groupAdvice.getContent() %></td>
                      <td>
                          <input type="radio" name="groupAdviceId" value="<%=groupAdvice.getAdviceId() %>" />
                      </td>
                 </tr>
				 <%
						}
					 }else{
				 %>
					 <tr align="center">
					 <td colspan="2">抱歉！您的组内其他成员还没有发表意见！</td>
					 </tr>
				 <%
					 }
				 %>
            </tbody>
            </table>
            <input class="submit" type="submit" value="提交">
            <input class="back" type="reset" value="重置">
			</form>
</div>
</body>
</html>
