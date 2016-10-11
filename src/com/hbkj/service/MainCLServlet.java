package com.hbkj.service;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainCLServlet
 */
@WebServlet("/MainCLServlet")
public class MainCLServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		String flag = request.getParameter("flag");
		if(flag.equals("sgin")){
			request.getRequestDispatcher("WEB-INF/sgin.jsp").forward(request, response);
		}else if(flag.equals("login")){
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
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
