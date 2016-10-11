package com.hbkj.service;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbkj.model.AdviceBO;
import com.hbkj.model.VoteBO;

/**
 * Servlet implementation class TakeVoteCLServlet
 */
@WebServlet("/TakeVoteCLServlet")
public class TakeVoteCLServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		//获取用户id
		String s_userId = (String)request.getSession().getAttribute("userId");
		int userId = Integer.parseInt(s_userId);
		//获取本人赞同的部门意见
		String[] s_sectorAdviceId = request.getParameterValues("sectorAdviceId");
		//获取本人赞同的小组意见
		String s_groupAdviceId = request.getParameter("groupAdviceId");
		
		VoteBO VBO = new VoteBO();
		AdviceBO ABO = new AdviceBO();
		int agreeCount = 0;
		int adviceId = 0;
		boolean flag = false;
		//对部门意见的count进行加一操作，如果对某条意见已经点过赞，则直接跳过
		if(s_sectorAdviceId != null){
			for(int i = 0; i < s_sectorAdviceId.length; i++){
				adviceId = Integer.parseInt(s_sectorAdviceId[i]);
				try {
					VBO.addVote(userId, adviceId);
					agreeCount = ABO.getCountByAdviceId(adviceId);
					flag = ABO.updateAdviceCount(adviceId, agreeCount+1);
				} catch (Exception e) {
					System.out.println("您对该意见已经表决过自己的看法");
					continue;
				}
			}
		}
		
		
		if(s_groupAdviceId!=null){
			adviceId = Integer.parseInt(s_groupAdviceId);
			//如果没有对该组内的意见进行过点赞则向vote中添加纪录，否则更新记录
			if(!VBO.alreadyVoteInGroup(userId)){
				VBO.addVote(userId, adviceId);
				agreeCount = ABO.getCountByAdviceId(adviceId);
				flag = ABO.updateAdviceCount(adviceId, agreeCount+1);
				if(agreeCount+1 == 2){
					ABO.updateAdviceLevel(adviceId,"1");
					ABO.clearVote(userId);
					ABO.clearAdvice(userId);
					ABO.clearAdviceCount(adviceId);
				}
			}else{
				//对之前点过的意见的count进行减一
				int old_adviceId = VBO.getAdviceIdByUserId(userId);
				agreeCount = ABO.getCountByAdviceId(old_adviceId);
				ABO.updateAdviceCount(old_adviceId, agreeCount-1);
				//更新本人点赞的意见的id并对该意见的count加一
				VBO.updateVote(userId, adviceId);
				agreeCount = ABO.getCountByAdviceId(adviceId);
				flag = ABO.updateAdviceCount(adviceId, agreeCount+1);
				if(agreeCount+1 == 2){
					ABO.updateAdviceLevel(adviceId,"1");
					ABO.clearVote(userId);
					ABO.clearAdvice(userId);
					ABO.clearAdviceCount(adviceId);
				}
			}
		}
		if(flag){
			request.getRequestDispatcher("WEB-INF/voteSuccess.jsp").forward(request, response);
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
