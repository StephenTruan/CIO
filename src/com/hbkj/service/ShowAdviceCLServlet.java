package com.hbkj.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbkj.model.AdviceBO;
import com.hbkj.model.AdviceBean;

/**
 * Servlet implementation class ShowAdviceCLServlet
 */
@WebServlet("/ShowAdviceCLServlet")
public class ShowAdviceCLServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String s_userId = (String) request.getSession().getAttribute("userId");
		int userId = Integer.parseInt(s_userId);
		AdviceBO ABO = new AdviceBO();
		ArrayList<AdviceBean> groupList = ABO.getGroupAdviceByUserId(userId);
		ArrayList<AdviceBean> sectorList = ABO.getSectorAdviceByUserId(userId);
			request.setAttribute("groupList", groupList);
			request.setAttribute("sectorList", sectorList);
			request.getRequestDispatcher("WEB-INF/takeVote.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
