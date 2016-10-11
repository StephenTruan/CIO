package com.hbkj.dao;

import com.hbkj.entity.Advice;
import com.hbkj.dao.base.Dao;

public interface AdviceDao extends Dao<Advice> {
	public int getPageCount(int pageSize);
}
