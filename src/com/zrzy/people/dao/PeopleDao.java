package com.zrzy.people.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zrzy.login.bean.LoginBean;
import com.zrzy.people.bean.PeopleBean;
import com.formwork.base.bean.KeyValueBean;
import com.formwork.base.bean.SessionBean;
import com.formwork.base.dao.BaseDao;
import com.formwork.base.page.PageBean;
import com.formwork.db.JdbcParameter;
import com.formwork.db.SqlInterfaceDao;
import com.formwork.db.daoimp.SqlImpDao;

public class PeopleDao extends BaseDao {

	public PageBean queryList(PeopleBean peopleBean, SqlImpDao sqlImpDao){
		List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
		String sql = "select a.people_id, a.people_name, a.people_sex, "
				+ "a.people_age, a.people_phone, a.people_card, a.people_address "
				+ "from people_info a "
				+ "where 0=0";

		if(peopleBean.getPeople_name() != null && !"".equals(peopleBean.getPeople_name())){
			sql += " and a.people_name like ?";
			inParams.add(new JdbcParameter("people_name", Types.VARCHAR, "%" + peopleBean.getPeople_name() + "%"));
		}
		if(peopleBean.getPeople_sex() != null && !"".equals(peopleBean.getPeople_sex())){
			sql += " and a.people_sex = ?";
			inParams.add(new JdbcParameter("people_sex", Types.VARCHAR, peopleBean.getPeople_sex()));
		}
		PageBean pageUtil = this.pageUtil(sql, inParams, peopleBean, sqlImpDao);
		
		return pageUtil;
	}
	public Map<String, String> queryInfo(String people_id, SqlImpDao sqlImpDao){
		List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
		String sql = "select a.people_id, a.people_name, a.people_sex, "
				+ "a.people_age, a.people_phone, a.people_card, a.people_address "
				+ "from people_info a "
				+ "where a.people_id = ?";
		inParams.add(new JdbcParameter("people_id", Types.VARCHAR, people_id));
		
		List<Map<String, String>> queryDataMap = sqlImpDao.queryDataMap(sql, inParams);
		if(queryDataMap != null && queryDataMap.size() > 0){
			return queryDataMap.get(0);
		}
		
		return null;
	}

	public List<Map<String, String>> sexList(SqlImpDao sqlImpDao){
		String sql = "select id, name from base_dict where type='03'";
		List<Map<String, String>> queryDataMap = sqlImpDao.queryDataMap(sql);
		sqlImpDao.close();
		return queryDataMap;
	}

	public boolean checkPeopleId(String people_id, SqlImpDao sqlImpDao){
		String sql = "select count(0) from people_info a "
				+ "where people_id = ?";

		List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
		inParams.add(new JdbcParameter("people_id", Types.VARCHAR, people_id));
		long count = sqlImpDao.queryCount(sql, inParams);
		if(count > 0){
			return false;
		}
		return true;
	}

	public boolean addSave(PeopleBean peopleBean, SqlImpDao sqlImpDao){
		String sql = "insert into "
				+ "people_info(people_id, people_name, people_age, "
				+ "people_sex, people_phone, people_card, people_address) "
				+ "values(?, ?, ?, ?, ?, ?, ?)";

		List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
		inParams.add(new JdbcParameter("people_id", Types.VARCHAR, peopleBean.getPeople_id()));
		inParams.add(new JdbcParameter("people_name", Types.VARCHAR, peopleBean.getPeople_name()));
		inParams.add(new JdbcParameter("people_age", Types.VARCHAR, peopleBean.getPeople_age()));
		inParams.add(new JdbcParameter("people_sex", Types.VARCHAR, peopleBean.getPeople_sex()));
		inParams.add(new JdbcParameter("people_phone", Types.VARCHAR, peopleBean.getPeople_phone()));
		inParams.add(new JdbcParameter("people_card", Types.VARCHAR, peopleBean.getPeople_card()));
		inParams.add(new JdbcParameter("people_address", Types.VARCHAR, peopleBean.getPeople_address()));
		int numb = sqlImpDao.updateData(sql, inParams);
		
		if(numb > 0){
			return true;
		}
		return false;
	}

	public boolean upSave(PeopleBean peopleBean, SqlImpDao sqlImpDao){
		String sql = "update people_info set "
				+ "people_name = ?, people_age = ?, people_sex = ?, people_phone = ?, "
				+ "people_card = ?, people_address = ? where people_id = ?";

		List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
		inParams.add(new JdbcParameter("people_name", Types.VARCHAR, peopleBean.getPeople_name()));
		inParams.add(new JdbcParameter("people_age", Types.VARCHAR, peopleBean.getPeople_age()));
		inParams.add(new JdbcParameter("people_sex", Types.VARCHAR, peopleBean.getPeople_sex()));
		inParams.add(new JdbcParameter("people_phone", Types.VARCHAR, peopleBean.getPeople_phone()));
		inParams.add(new JdbcParameter("people_card", Types.VARCHAR, peopleBean.getPeople_card()));
		inParams.add(new JdbcParameter("people_address", Types.VARCHAR, peopleBean.getPeople_address()));
		inParams.add(new JdbcParameter("people_id", Types.VARCHAR, peopleBean.getPeople_id()));
		int numb = sqlImpDao.updateData(sql, inParams);
		
		if(numb > 0){
			return true;
		}
		return false;
	}

	public boolean deleteSave(String people_id, SqlImpDao sqlImpDao){
		String sql = "delete from people_info where people_id = ?";

		List<JdbcParameter> inParams = new ArrayList<JdbcParameter>();
		inParams.add(new JdbcParameter("people_id", Types.VARCHAR, people_id));
		int numb = sqlImpDao.updateData(sql, inParams);
		
		if(numb > 0){
			return true;
		}
		return false;
	}
	
}
