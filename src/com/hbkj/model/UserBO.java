package com.hbkj.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hbkj.dao.base.RowMapper;

public class UserBO {
	private ConnDB CDB = new ConnDB();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	/**
	 * ��ӳ�Ա
	 * @param userName
	 * @param password
	 * @param sector
	 * @return boolean
	 */
	public boolean addUser(String userName,String password,String sector,int groupid){
		Boolean flag = false;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("insert into user(username,password,sector,groupid)values(?,?,?,?)");
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			pstmt.setString(3, sector);
			pstmt.setInt(4, groupid);
			if(pstmt.executeUpdate()>0){
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
	 * ���ݳ�Ա���ֻ�ȡ��Ա����
	 * @param username
	 * @return userBean
	 */
	public UserBean getUserByName(String username){
		UserBean user = null;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("select * from user where username=?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user = new UserBean();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setSector(rs.getString(4));
				user.setGroupId(rs.getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CDB.closeConn(conn, pstmt, rs);
		}
		return user;
	}
	/**
	 * ���ݳ�ԱId���³�Ա�����
	 * @param userId
	 * @param content
	 * @return boolean
	 */
	public boolean updateContentByUserId(int userId,String content){
		boolean flag = false;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("update user set content=? where userid=?");
			pstmt.setString(1, content);
			pstmt.setInt(2, userId);
			if(pstmt.executeUpdate()>0){
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
	 * ��ȡ�����ų�Ա����
	 * @param userid
	 * @return Int
	 */
	public int countSecPeople(String sector){
		int count = 0;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("select count(*) from user where sector=?");
			pstmt.setString(1, sector);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			CDB.closeConn(conn, pstmt, null);
		}
		return count;
	}
	
	public List<UserBean> findByPage(int pageSize,String sql, int pageNow, RowMapper<UserBean> rm, Object... obj) {
		List<UserBean> users = new ArrayList<UserBean>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int begin = (pageNow-1)*pageSize;
		int end = pageSize*pageNow;
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			for (i= 1; i <= obj.length; i++) {
				pstmt.setObject(i, obj[i-1]);
			}
			pstmt.setInt(i++,begin);
			pstmt.setInt(i,end);
			rs = pstmt.executeQuery();
			while(rs.next()){
				UserBean user = rm.getRow(rs);
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDB.closeConn(conn,pstmt, rs);
		}
		return users;
	}
}
