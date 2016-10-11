package com.hbkj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hbkj.dao.base.RowMapper;
import com.hbkj.entity.User;
import com.hbkj.model.ConnDB;

public class UserDaoImpl {
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
	public User getUserByName(String username){
		User user = null;
		try {
			conn = CDB.getConn();
			pstmt = conn.prepareStatement("select * from user where username=?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user = new User();
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
	
	//分页
	public List<User> findByPage(int pageSize, String sql, int pageNow, RowMapper<User> rm, Object... obj) {
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int begin = (pageNow-1)*pageSize;
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			if(obj != null){
				for (i= 1; i <= obj.length; i++) {
					pstmt.setObject(i, obj[i-1]);
				}
			}
			pstmt.setInt(i++,begin);
			pstmt.setInt(i,pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()){
				User user = rm.getRow(rs);
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDB.closeConn(conn,pstmt, rs);
		}
		return users;
	}
	
	//获取总页数
	public int getPageCount(int pageSize){
		int pageCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement("select count(userid) count from user");
			rs = pstmt.executeQuery();
			if(rs.next()){
				pageCount = (int) Math.ceil((double)rs.getInt("count")/pageSize);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDB.closeConn(conn,pstmt, null);
		}
		return pageCount;
	}
	
	//删除成员
	public int deleteById(int id, String sql){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDB.closeConn(conn,pstmt, null);
		}
		return n;
	}
}
