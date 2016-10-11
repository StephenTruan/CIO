package com.hbkj.dao.base;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
	public T getRow(ResultSet rs)throws SQLException;
}
