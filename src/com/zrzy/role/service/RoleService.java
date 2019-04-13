package com.zrzy.role.service;

import java.util.List;
import java.util.Map;

import com.zrzy.role.bean.RoleBean;
import com.zrzy.role.dao.RoleDao;
import com.formwork.base.bean.CommonUtils;
import com.formwork.base.bean.ReturnBean;
import com.formwork.base.page.PageBean;
import com.formwork.base.service.BaseService;
import com.formwork.db.SqlInterfaceDao;
import com.formwork.db.daoimp.SqlImpDao;

public class RoleService extends BaseService {
	
	
	public PageBean roleQueryList(RoleBean roleBean) {
		RoleDao roleDao = new RoleDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		
		return roleDao.queryList(roleBean, sqlInterfaceDao);
		
	}

	public Map<String, String> roleQueryInfo(String role_id) {
		RoleDao roleDao = new RoleDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();

		List<Map<String, String>> list = roleDao.queryList(role_id, sqlInterfaceDao);
		
		if(list != null && list.size() > 0){
			return list.get(0);
		}

		return null;
	}

	public boolean addSave(RoleBean roleBean) {
		RoleDao roleDao = new RoleDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		
		try{
			int num = roleDao.addSave(roleBean, sqlInterfaceDao);
			if (num < 1) {
				sqlInterfaceDao.rollback();
				return false;
			}
			sqlInterfaceDao.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			sqlInterfaceDao.rollback();
		}
		
		return false;
	}

	public boolean upSave(RoleBean roleBean) {
		RoleDao roleDao = new RoleDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		
		try{
			int num = roleDao.upSave(roleBean, sqlInterfaceDao);
			if (num < 1) {
				sqlInterfaceDao.rollback();
				return false;
			}
			sqlInterfaceDao.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			sqlInterfaceDao.rollback();
		}
		
		return false;
		
	}

	public ReturnBean saveDelete(String ck_sign) {
		RoleDao roleDao = new RoleDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		ReturnBean returnBean = new ReturnBean();
		try{
			String[] roles = ck_sign.split(";");

			for (String role_id : roles) {
				long count = roleDao.roleFunc(role_id, sqlInterfaceDao);
				if (count > 0) {
					throw new RuntimeException(role_id + "编码权限已绑定系统模块，请先解除绑定");
				}
				roleDao.deleteRole(role_id, sqlInterfaceDao);
			}
			sqlInterfaceDao.commit();
			return returnBean;
		}catch (Exception e) {
			e.printStackTrace();
			returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
			returnBean.setReturnInfo("操作失败");
		}
		
		return returnBean;
	}
	
	public List<Map<String, String>> getFuncTree(String role_id) {
		RoleDao roleDao = new RoleDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		return roleDao.getFuncTree(role_id, sqlInterfaceDao);
	}
	
	public void saveRoleFunc(String role_id, String ck_sign) {
		RoleDao roleDao = new RoleDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		try{
			String[] functions = ck_sign.split(";");
			roleDao.deleteRoleFunc(role_id, sqlInterfaceDao);
			for(String function : functions){
				if(function!=null && !"".equals(function)){
					roleDao.saveRoleFunc(role_id, function, "0201", sqlInterfaceDao);
				}
			}
			sqlInterfaceDao.commit();
		}catch (Exception e) {
			e.printStackTrace();
			sqlInterfaceDao.rollback();
		}
	}
	
	
	
	
}
