package com.hbkj.service;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbkj.model.UserBO;
import com.hbkj.model.UserBean;

/**
 * Servlet implementation class UserLoginCLServlet
 */
@WebServlet("/UserLoginCLServlet")
public class UserLoginCLServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String userName = new String(request.getParameter("userName").getBytes("ISO-8859-1"),"utf-8");
		String password = new String(request.getParameter("password").getBytes("ISO-8859-1"),"utf-8");
		UserBO UBO = new UserBO();
		UserBean user = UBO.getUserByName(userName);
		if(userName.equals("admin")&&password.equals("webadmin")){
			System.out.println(userName+" "+password);
			request.getRequestDispatcher("WEB-INF/admin/main.jsp").forward(request, response);				
		}else if(user!=null){
			if(userName.equals(user.getUserName())){
				if(password.equals(user.getPassword())){
					request.getSession().setAttribute("userId", String.valueOf(user.getUserId()));
					request.getSession().setAttribute("sector", user.getSector());
					request.getRequestDispatcher("WEB-INF/publish.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("WEB-INF/err.jsp").forward(request, response);
				}
			}else{
				request.getRequestDispatcher("WEB-INF/err.jsp").forward(request, response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/err.jsp").forward(request, response);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
