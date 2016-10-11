package com.hbkj.service;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbkj.model.UserBO;

/**
 * Servlet implementation class UserSginCLServlet
 */
@WebServlet("/UserSginCLServlet")
public class UserSginCLServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String userName = new String (request.getParameter("userName").getBytes("ISO-8859-1"),"utf-8");
		String password = request.getParameter("password");
		String sector = new String (request.getParameter("sector").getBytes("ISO-8859-1"),"utf-8");
		int groupUserCount = Integer.parseInt(String.valueOf(request.getServletContext().getAttribute("groupUserCount")));
		
		UserBO UBO = new UserBO();
		int groupId = UBO.countSecPeople(sector)/groupUserCount;
		Boolean flag = UBO.addUser(userName, password, sector, groupId);
		if(flag){
			request.getRequestDispatcher("WEB-INF/success.jsp").forward(request, response);
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
