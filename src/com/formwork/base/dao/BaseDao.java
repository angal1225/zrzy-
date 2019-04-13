package com.formwork.base.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.formwork.base.bean.BaseBean;
import com.formwork.base.page.PageBean;
import com.formwork.db.JdbcParameter;
import com.formwork.db.SqlInterfaceDao;
import com.formwork.db.daoimp.SqlImpDao;
import com.zrzy.people.bean.PeopleBean;

public class BaseDao {

	public List<Map<String, String>> getBaseDict(String type, String id, 
			SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();

		String sql = "select a.id as value, a.name as text from base_dict a " +
				"where 0=0 ";
		if(type != null && !"".equals(type)){
			sql += "and a.type = ? ";
			listJdbcParameter.add(new JdbcParameter("type", Types.VARCHAR,
					type));
		}
		if(id != null && !"".equals(id)){
			sql += "and a.id = ? ";
			listJdbcParameter.add(new JdbcParameter("id", Types.VARCHAR,
					id));
		}

		List<Map<String, String>> list = testJDBC.queryDataMap(sql,
				listJdbcParameter);
		return list;
	}

	public List<Map<String, String>> getBaseDict(String type,
			SqlInterfaceDao testJDBC) {
		return getBaseDict(type, null, 
				testJDBC);
	}
	
	public PageBean pageUtil(String sql, List<JdbcParameter> list, BaseBean baseBean, SqlImpDao sqlImpDao){
		PageBean pageBean = new PageBean();
		String countSql = "select count(0) from ("+sql+") a";
		long count = sqlImpDao.queryCount(countSql, list);
		
		sql += " limit "+baseBean.getStartRow()+", "+baseBean.getRows();
		List<Map<String, String>> queryDataMap = sqlImpDao.queryDataMap(sql, list);
		
		pageBean.setTotal(String.valueOf(count));
		pageBean.setRows(queryDataMap);
		return pageBean;
	}
}
