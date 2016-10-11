package com.hbkj.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VoteBO {
	private ConnDB CDB = new ConnDB();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	/**
	 * 添加一条赞同记录
	 * @param userId
	 * @param adviceId
	 * @return boolean
	 */
	public boolean addVote(int userId,int adviceId){
		boolean flag = false;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("insert into vote(userId,adviceId)values(?,?)");
			pstmt.setInt(1, userId);
			pstmt.setInt(2, adviceId);
			if(pstmt.executeUpdate() > 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CDB.closeConn(conn, pstmt, null);
		}
		return flag;
	}
	/**
	 * 是否对本组内的意见点过赞同
	 * @param userId
	 * @return boolean
	 */
	public boolean alreadyVoteInGroup(int userId){
		boolean flag = false;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("select * from vote where userid=? and adviceid in(select adviceid from advice where userid in(select userid from user where groupid=(select groupid from user where userid=?)) and level='0')");
			pstmt.setInt(1, userId);
			pstmt.setInt(2, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CDB.closeConn(conn, pstmt, null);
		}
		return flag;
	}
	/**
	 * 修改点暂记录
	 * @param userId
	 * @param adviceId
	 * @return boolean
	 */
	public boolean updateVote(int userId,int adviceId){
		boolean flag = false;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("update vote set adviceid=? where userid=? and adviceid in(select adviceid from advice where level='0')");
			pstmt.setInt(1, adviceId);
			pstmt.setInt(2, userId);
			if(pstmt.executeUpdate() > 0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CDB.closeConn(conn, pstmt, null);
		}
		return flag;
	}
	/**
	 * 获取本人对哪一条意见点过赞
	 * @param userId
	 * @return int
	 */
	public int getAdviceIdByUserId(int userId){
		int adviceId = 0;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("select v.adviceid from vote as v, advice as a, user as u where v.userid=? and v.userid=u.userid and v.adviceid=a.adviceid and a.adviceid in(select adviceid from advice where userid in(select userid from user where groupid=(select groupid from user where userid=?)) and level='0')");
			pstmt.setInt(1, userId);
			pstmt.setInt(2, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				adviceId = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CDB.closeConn(conn, pstmt, rs);
		}
		return adviceId;
	}
}
