package com.hbkj.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hbkj.dao.base.RowMapper;
import com.hbkj.dao.impl.UserDaoImpl;
import com.hbkj.entity.User;

public class UserService {
	UserDaoImpl ud = new UserDaoImpl();
	public List<User> getUserByPage(int pageSize, int pageNow){
		String sql = "select * from user limit ?,?";
		List<User> users = ud.findByPage(pageSize, sql, pageNow, new UserRowMapper(), null);
		return users;
	}
	
	public int getPageCount(int pageSize){
		return ud.getPageCount(pageSize);
	}
	
	public int deleteById(int id){
		return ud.deleteById(id, "delete from user where userid=?");
	}
	
	class UserRowMapper implements RowMapper<User>{

		@Override
		public User getRow(ResultSet rs) throws SQLException {
			int userId = rs.getInt("userid");
			String userName = rs.getString("username");
			String password = rs.getString("password");
			String sector = rs.getString("sector");
			int groupId = rs.getInt("groupid");
			User user = new User(userId,userName,password,sector,groupId);
			return user;
		}
	}
}
