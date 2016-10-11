package com.hbkj.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.hbkj.dao.AdviceDao;
import com.hbkj.dao.base.RowMapper;
import com.hbkj.dao.impl.AdviceDaoImpl;
import com.hbkj.entity.Advice;

public class AdviceService {
	AdviceDao ad = new AdviceDaoImpl();
	
	//获取部门意见
	public List<Advice> getAdvice(String sector, int pageSize, int pageNow){
		return ad.findByPage(pageSize, "select * from advice where level='1' and sector=? limit ?,?", pageNow, new AdviceRowMapper(), sector);
	}
	
	//获取页数
	public int getPageCount(int pageSize){
		return ad.getPageCount(pageSize);
	}
	
	class AdviceRowMapper implements RowMapper{

		@Override
		public Object getRow(ResultSet rs) throws SQLException {
			// TODO Auto-generated method stub
			int adviceId = rs.getInt("adviceid");
			String content = rs.getString("content");
			int adviceAgreeCount = rs.getInt("agreecount");
			String adviceLevel = rs.getString("level");
			int userId = rs.getInt("userid");
			String sector = rs.getString("sector");
			return new Advice(adviceId, content, adviceAgreeCount, adviceLevel, userId,sector);
		}}
}
