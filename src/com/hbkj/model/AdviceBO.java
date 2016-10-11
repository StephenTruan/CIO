package com.hbkj.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdviceBO {
	private ConnDB CDB = new ConnDB();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public String getSectorByUserId(int userId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sector = null;
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement("select sector from user where userid=?");
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				sector = rs.getString("sector");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDB.closeConn(conn,pstmt, rs);
		}
		return sector;
	}
	
	/**
	 * ������
	 * @param content
	 * @param userId
	 * @return boolean
	 */
	public boolean addAdvice(String content,int userId,String sector){
		boolean flag = false;
		sector = getSectorByUserId(userId);
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("insert into advice(content,userid,sector)values(?,?,?)");
			pstmt.setString(1, content);
			pstmt.setInt(2, userId);
			pstmt.setString(3, sector);
			if(pstmt.executeUpdate()>0){
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CDB.closeConn(conn, pstmt, rs);
		}
		return flag;
	}
	/**
	 * ��ȡ��Ա����������
	 * @param userId
	 * @return ArrayList
	 */
	public ArrayList<AdviceBean> getGroupAdviceByUserId(int userId){
		ArrayList<AdviceBean> list = new ArrayList<AdviceBean>();
		AdviceBean advice = null;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("select * from advice where userid in(select userid from user where groupid=(select groupid from user where userid=?) and sector=(select sector from user where userid=?)) and userid!=? and level='0'");
			pstmt.setInt(1, userId);
			pstmt.setInt(2, userId);
			pstmt.setInt(3, userId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				advice = new AdviceBean();
				advice.setAdviceId(rs.getInt(1));
				advice.setContent(rs.getString(2));
				advice.setAdviceAgreeCount(rs.getInt(3));
				advice.setAdviceLevel(rs.getString(4));
				advice.setUserId(rs.getInt(5));
				list.add(advice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CDB.closeConn(conn, pstmt, rs);
		}
		return list;
	}
	/**
	 * ��ȡ��Ա���ڲ���ɸѡ��������
	 * @param userId
	 * @return ArrayList
	 */
	public ArrayList<AdviceBean> getSectorAdviceByUserId(int userId){
		ArrayList<AdviceBean> list = new ArrayList<AdviceBean>();
		AdviceBean advice = null;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("select * from advice where userid in(select userid from user where sector=(select sector from user where userid=?) and level='1')");
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()){
				advice = new AdviceBean();
				advice.setAdviceId(rs.getInt(1));
				advice.setContent(rs.getString(2));
				advice.setAdviceAgreeCount(rs.getInt(3));
				advice.setAdviceLevel(rs.getString(4));
				advice.setUserId(rs.getInt(5));
				list.add(advice);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CDB.closeConn(conn, pstmt, rs);
		}
		return list;
	}
	/**
	 * ��ȡ�������������ͬ����
	 * @param adviceId
	 * @return int
	 */
	public int getCountByAdviceId(int adviceId){
		int count = 0;
		try {
			conn =CDB.getConn();
			pstmt = conn.prepareStatement("select agreecount from advice where adviceid=?");
			pstmt.setInt(1, adviceId);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CDB.closeConn(conn, pstmt, rs);
		}
		return count;
	}
	/**
	 * ����ͬ���������������
	 * @param adviceId
	 * @return boolean
	 */
	public boolean updateAdviceCount(int adviceId, int agreeCount){
		boolean flag = false;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("update advice set agreecount=? where adviceid=?");
			pstmt.setInt(1, agreeCount);
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
	 * ������������ĵȼ�
	 * @param adviceId
	 * @return boolean
	 */
	public boolean updateAdviceLevel(int adviceId,String level){
		boolean flag = false;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("update advice set level=? where adviceid=?");
			pstmt.setString(1, level);
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
	 * ��ձ��������е��޼�¼
	 * @param userId
	 * @return boolean
	 */
	public boolean clearVote(int userId){
		boolean flag = false;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("delete from vote where adviceid in(select adviceid from advice where userid in(select userid from user where groupid=(select groupid from user where userid=?)))");
			pstmt.setInt(1, userId);
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
	 * ��ձ�����û�б�ɸѡ���������
	 * @param userId
	 * @return boolean
	 */
	public boolean clearAdvice(int userId){
		boolean flag = false;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("delete from advice where userid in(select userid from user where groupid=(select groupid from user where userid=?))and level='0'");
			pstmt.setInt(1, userId);
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
	 * ����ɸѡ����������ĵ�����������
	 * @param userId
	 * @return boolean
	 */
	public boolean clearAdviceCount(int adviceId){
		boolean flag = false;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("update advice set agreecount=0 where adviceid=?");
			pstmt.setInt(1, adviceId);
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
}
