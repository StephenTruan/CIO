package com.hbkj.service;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbkj.model.AdviceBO;

/**
 * Servlet implementation class AdviceAddCLServlet
 */
@WebServlet("/AdviceAddCLServlet")
public class AdviceAddCLServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String content = new String(request.getParameter("content").getBytes("ISO-8859-1"),"utf-8");
		String sector = (String) request.getSession().getAttribute("sector");
		int userId = Integer.parseInt((String) request.getSession().getAttribute("userId"));
		AdviceBO ABO = new AdviceBO();
		if(ABO.addAdvice(content, userId, sector)){
			request.getRequestDispatcher("ShowAdviceCLServlet").forward(request, response);
		}else{
			request.setAttribute("flag", "1");
			request.getRequestDispatcher("WEB-INF/publish.jsp").forward(request, response);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
