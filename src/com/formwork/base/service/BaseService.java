package com.formwork.base.service;

import java.util.List;
import java.util.Map;

import com.formwork.base.dao.BaseDao;
import com.formwork.db.SqlInterfaceDao;
import com.formwork.db.daoimp.SqlImpDao;

public class BaseService {

	public List<Map<String, String>> getBaseDict(String type) {
		BaseDao baseDao = new BaseDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		return baseDao.getBaseDict(type, sqlInterfaceDao);
	}
}
