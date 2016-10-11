package com.hbkj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hbkj.dao.AdviceDao;
import com.hbkj.dao.base.DaoSupport;
import com.hbkj.entity.Advice;
import com.hbkj.model.ConnDB;

public class AdviceDaoImpl extends DaoSupport<Advice> implements AdviceDao {
	//获取总页数
	public int getPageCount(int pageSize){
		int pageCount = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = ConnDB.getConn();
			pstmt = conn.prepareStatement("select count(adviceid) count from advice");
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
}
