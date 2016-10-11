package com.hbkj.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbkj.entity.Advice;
import com.hbkj.entity.User;


/**
 * Servlet implementation class AdminCLServlet
 */
@WebServlet("/AdminCLServlet")
public class AdminCLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserService us = new UserService();
	AdviceService as = new AdviceService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("method");
		if("webAdmin".equals(method)){
			webAdmin(request, response);
		}if("msg".equals(method)){
			msg(request,response);
		}if("toAdmin".equals(method)){
			toAdmin(request,response);
		}if("userAdmin".equals(method)){
			userAdmin(request,response);
		}if("userDelete".equals(method)){
			userDelete(request,response);
		}if("adviceAdmin".equals(method)){
			adviceAdmin(request,response);
		}
	}

	private void adviceAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s_pageNow = request.getParameter("pageNow");
		int pageNow = 1;
		int pageSize = 1;
		if(s_pageNow != null){
			pageNow = Integer.parseInt(s_pageNow);
		}
		String sector = request.getParameter("sector");
		
		List<Advice> advices = as.getAdvice(sector, pageSize, pageNow);
		int pageCount = as.getPageCount(pageSize);
		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("advices", advices);
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("sector", sector);
		request.getRequestDispatcher("WEB-INF/admin/adviceAdmin.jsp").forward(request, response);
	}

	private void userDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s_userId = request.getParameter("userId");
		int userId = Integer.parseInt(s_userId);
		
		
		if(us.deleteById(userId)==0){
			request.setAttribute("message", "对不起，操作失败");
			List<User> users = us.getUserByPage(5, 1);
			request.setAttribute("users", users);
			request.getRequestDispatcher("WEB-INF/admin/userAdmin.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "恭喜您，操作成功");
			List<User> users = us.getUserByPage(5, 1);
			request.setAttribute("users", users);
			request.getRequestDispatcher("WEB-INF/admin/userAdmin.jsp").forward(request, response);
				
		}
	}

	private void userAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s_pageNow = request.getParameter("pageNow");
		int pageNow = 1;
		int pageSize = 5;
		if(s_pageNow != null){
			pageNow = Integer.parseInt(s_pageNow);
		}
		
		List<User> users = us.getUserByPage(pageSize, pageNow);
		int pageCount = us.getPageCount(pageSize);
		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("users", users);
		request.setAttribute("pageNow", pageNow);
		request.getRequestDispatcher("WEB-INF/admin/userAdmin.jsp").forward(request, response);
	}

	private void toAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/admin/main.jsp").forward(request, response);
	}

	private void msg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String topic = new String(request.getParameter("topic").getBytes("ISO-8859-1"),"utf-8");
		String name1 = new String(request.getParameter("name1").getBytes("ISO-8859-1"),"utf-8");
		String name2 = new String(request.getParameter("name2").getBytes("ISO-8859-1"),"utf-8");
		String name3 = new String(request.getParameter("name3").getBytes("ISO-8859-1"),"utf-8");
		String groupUserCount = new String(request.getParameter("groupUserCount").getBytes("ISO-8859-1"),"utf-8");
		String document = new String(request.getParameter("document").getBytes("ISO-8859-1"),"utf-8");
		
		
		if("".equals(topic)||"".equals(name1)||"".equals(name2)||"".equals(name3)||"".equals(groupUserCount)||"".equals(document)){
			request.setAttribute("message", "请将数据填写完整");
			request.getRequestDispatcher("WEB-INF/admin/webAdmin.jsp").forward(request, response);
		}else{			
			ServletContext context = request.getServletContext();
			context.setAttribute("topic", topic);
			context.setAttribute("name1", name1);
			context.setAttribute("name2", name2);
			context.setAttribute("name3", name3);
			context.setAttribute("groupUserCount", groupUserCount);
			context.setAttribute("document", document);
			request.setAttribute("message", "恭喜您提交成功");
			request.getRequestDispatcher("WEB-INF/admin/webAdmin.jsp").forward(request, response);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void webAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("WEB-INF/admin/webAdmin.jsp").forward(request, response);
	}

}
