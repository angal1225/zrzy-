package com.zrzy.people.service;

import java.util.List;
import java.util.Map;

import com.formwork.base.page.PageBean;
import com.formwork.base.service.BaseService;
import com.formwork.db.daoimp.SqlImpDao;
import com.zrzy.people.bean.PeopleBean;
import com.zrzy.people.dao.PeopleDao;

public class PeopleService extends BaseService {
	private PeopleDao peopleDao = new PeopleDao();
	
	public PageBean queryList(PeopleBean peopleBean){
		SqlImpDao sqlImpDao = new SqlImpDao();
		PageBean queryList = peopleDao.queryList(peopleBean, sqlImpDao);
		sqlImpDao.close();
		return queryList;
	}
	
	public List<Map<String, String>> sexList(){
		SqlImpDao sqlImpDao = new SqlImpDao();
		List<Map<String, String>> sexList = peopleDao.sexList(sqlImpDao);
		sqlImpDao.close();
		return sexList;
	}
	
	public boolean checkPeopleId(String people_id){
		SqlImpDao sqlImpDao = new SqlImpDao();
		boolean checkPeopleId = peopleDao.checkPeopleId(people_id, sqlImpDao);
		sqlImpDao.close();
		return checkPeopleId;
	}
	
	public boolean addSave(PeopleBean peopleBean){
		SqlImpDao sqlImpDao = new SqlImpDao();
		boolean sign = peopleDao.addSave(peopleBean, sqlImpDao);
		if(sign){
			sqlImpDao.commit();
		}else{
			sqlImpDao.rollback();
		}
		sqlImpDao.close();
		return sign;
	}
	
	public Map<String,String> queryInfo(String people_id){
		SqlImpDao sqlImpDao = new SqlImpDao();
		Map<String, String> queryInfo = peopleDao.queryInfo(people_id, sqlImpDao);
		sqlImpDao.close();
		return queryInfo;
	}
	
	public boolean upSave(PeopleBean peopleBean){
		SqlImpDao sqlImpDao = new SqlImpDao();
		boolean sign = peopleDao.upSave(peopleBean, sqlImpDao);
		if(sign){
			sqlImpDao.commit();
		}else{
			sqlImpDao.rollback();
		}
		sqlImpDao.close();
		return sign;
	}
	
	public boolean deleteSave(PeopleBean peopleBean){
		SqlImpDao sqlImpDao = new SqlImpDao();
		boolean sign = peopleDao.deleteSave(peopleBean.getPeople_id(), sqlImpDao);
		if(sign){
			sqlImpDao.commit();
		}else{
			sqlImpDao.rollback();
		}
		sqlImpDao.close();
		return sign;
	}
	
	public boolean deleteSaveBat(String people_ids){
		SqlImpDao sqlImpDao = new SqlImpDao();
		String[] people_id_array = people_ids.split(",");
		for(String people_id : people_id_array){
			peopleDao.deleteSave(people_id, sqlImpDao);
		}
		sqlImpDao.commit();
		sqlImpDao.close();
		
		return true;
	}
}
