package com.hbkj.dao.base;

import java.util.List;

public interface Dao<T> {
	T findById(String id,String sql,RowMapper<T> rm);
	List<T> findByPage(int pageSize, String sql, int pageNow, RowMapper<T> rm, Object...objects );
	List<T> findAll(String sql, RowMapper<T> rm, Object...objs);
	int saveOrUpdate(String sql, Object...obj);
	int deleteById(String id, String sql);
	List<String> findIds(String id, String sql);
}
