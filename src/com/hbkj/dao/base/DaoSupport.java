package com.hbkj.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hbkj.model.ConnDB;

public class DaoSupport<T> implements Dao<T> {

	@Override
	public T findById(String id, String sql, RowMapper<T> rm) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		T t = null;
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				t = rm.getRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDB.closeConn(conn,pstmt, rs);
		}
		return t;
	}

	@Override
	public List<T> findByPage(int pageSize,String sql, int pageNow, RowMapper<T> rm, Object... obj) {
		List<T> ts = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int begin = (pageNow-1)*pageSize;
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			for (i= 1; i <= obj.length; i++) {
				pstmt.setObject(i, obj[i-1]);
			}
			pstmt.setInt(i++,begin);
			pstmt.setInt(i,pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()){
				T t = rm.getRow(rs);
				ts.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDB.closeConn(conn,pstmt, rs);
		}
		return ts;
	}

	@Override
	public int saveOrUpdate(String sql, Object... obj) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement(sql);
			int i = 0;
			for (i = 0; i < obj.length; i++) {
				pstmt.setObject(i+1, obj[i]);
			}
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDB.closeConn(conn,pstmt, null);
		}
		return n;
	}

	@Override
	public int deleteById(String id, String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			n = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDB.closeConn(conn,pstmt, null);
		}
		return n;
	}

	@Override
	public List<T> findAll(String sql, RowMapper<T> rm, Object... objs) {
		List<T> ts = new ArrayList<T>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement(sql);
			int i = 0;
			for (i= 0; i < objs.length; i++) {
				pstmt.setObject(i+1, objs[i]);
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				T t = rm.getRow(rs);
				ts.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDB.closeConn(conn,pstmt, rs);
		}
		return ts;
	}

	@Override
	public List<String> findIds(String id, String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> ids = new ArrayList<String>();
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				ids.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnDB.closeConn(conn,pstmt, rs);
		}
		return ids;
	}

}
