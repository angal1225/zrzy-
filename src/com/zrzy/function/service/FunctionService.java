package com.zrzy.function.service;

import java.util.List;
import java.util.Map;

import com.zrzy.function.bean.FunctionBean;
import com.zrzy.function.dao.FunctionDao;
import com.formwork.base.bean.ReturnBean;
import com.formwork.base.service.BaseService;
import com.formwork.db.SqlInterfaceDao;
import com.formwork.db.daoimp.SqlImpDao;

public class FunctionService extends BaseService {

	public List<Map<String, String>> getFunction(String parent_id) {
		return getFunction(parent_id, false);
	}

	public List<Map<String, String>> getFunction(String parent_id, boolean is_only) {
		FunctionDao functionDao = new FunctionDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		String distance = null;
		if (parent_id == null || "".equals(parent_id)) {
			parent_id = "0000";
		}
		if (is_only) {
			distance = "0";
		}
		List<Map<String, String>> list = functionDao.getFunction(parent_id, distance, sqlInterfaceDao);
		return list;
	}

	public Map<String, String> getFunctionInfo(String func_id) {FunctionDao functionDao = new FunctionDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		List<Map<String, String>> list = functionDao.getFunctionInfo(func_id, null, sqlInterfaceDao);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	public boolean saveUpdate(FunctionBean functionBean) {
		FunctionDao functionDao = new FunctionDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		
		int num = functionDao.updateSave(functionBean, sqlInterfaceDao);
		if (num < 1) {
			sqlInterfaceDao.rollback();
			return false;
		}
		sqlInterfaceDao.commit();
		return true;
	}

	public ReturnBean saveDelete(String func_id) {
		FunctionDao functionDao = new FunctionDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		
		ReturnBean returnBean = new ReturnBean();
		returnBean.setReturnValue("true");

		if (func_id == null || "".equals(func_id)) {
			returnBean.setReturnValue("false");
			returnBean.setReturnInfo("部门为空");
			return returnBean;
		}

		List<Map<String, String>> list = functionDao.getFunctionInfo(null, func_id, sqlInterfaceDao);
		if (list != null && list.size() > 0) {
			returnBean.setReturnValue("false");
			returnBean.setReturnInfo("此节点有子节点，不允许删除");
			return returnBean;
		}
		functionDao.deleteSave(func_id, sqlInterfaceDao);
		functionDao.deleteSaveRel(func_id, sqlInterfaceDao);

		sqlInterfaceDao.commit();
		return returnBean;
	}

	public boolean saveAdd(FunctionBean functionBean) {
		FunctionDao functionDao = new FunctionDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		
		int num = functionDao.addSave(functionBean, sqlInterfaceDao);
		if (num < 1) {
			sqlInterfaceDao.rollback();
			throw new RuntimeException();
		}
		functionDao.funcRelSelectSave(functionBean.getFunc_id(), functionBean.getParent_id(), sqlInterfaceDao);
		functionDao.funcRelSave(functionBean.getFunc_id(), functionBean.getFunc_id(), "0", sqlInterfaceDao);
		sqlInterfaceDao.commit();
		return true;
	}
}
