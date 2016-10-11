package com.hbkj.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnDB {
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/inf";
	private static String name = "com.mysql.jdbc.Driver";
	private static String user = "root";
	private static String password = "root";
	
	private static Connection conn = null;
	
	public static Connection getConn(){
		try {
			Class.forName(name);
			conn = DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeConn(Connection conn,PreparedStatement pstmt,ResultSet rs){
		try {
			if(rs!=null){rs.close();}
			if(pstmt!=null){pstmt.close();}
			if(conn!=null){conn.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
