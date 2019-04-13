package com.zrzy.login.service;

import java.util.List;
import java.util.Map;

import com.zrzy.common.MD5Utils;
import com.zrzy.login.bean.LoginBean;
import com.zrzy.login.dao.LoginDao;
import com.formwork.base.bean.CommonUtils;
import com.formwork.base.bean.KeyValueBean;
import com.formwork.base.bean.ReturnBean;
import com.formwork.base.bean.SessionBean;
import com.formwork.base.page.PageBean;
import com.formwork.base.service.BaseService;
import com.formwork.db.SqlInterfaceDao;
import com.formwork.db.daoimp.SqlImpDao;

public class LoginService extends BaseService {

	public boolean addLogin(LoginBean loginBean) {
		boolean sign = false;
		LoginDao loginDao = new LoginDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		sign = loginDao.addLogin(loginBean, sqlInterfaceDao);
		if (sign) {
			sqlInterfaceDao.commit();
		} else {
			sqlInterfaceDao.rollback();
		}
		
		return sign;
	}

	public boolean updateLogin(LoginBean loginBean) {
		boolean sign = false;
		LoginDao loginDao = new LoginDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		sign = loginDao.updateLogin(loginBean, sqlInterfaceDao);
		if (sign) {
			sqlInterfaceDao.commit();
		} else {
			sqlInterfaceDao.rollback();
		}
		return sign;
	}

	public boolean updateLoginPwd(LoginBean loginBean) {
		boolean sign = false;
		LoginDao loginDao = new LoginDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		sign = loginDao.updateLoginPwd(loginBean, sqlInterfaceDao);
		if (sign) {
			sqlInterfaceDao.commit();
		} else {
			sqlInterfaceDao.rollback();
		}
		
		return sign;
	}

	public Map<String, String> getLoginInfo(LoginBean loginBean) {
		LoginDao loginDao = new LoginDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		Map<String, String> map = null;
		List<Map<String, String>> list = loginDao.getLoginInfo(loginBean,
				sqlInterfaceDao);

		if (list != null && list.size() > 0) {
			map = list.get(0);
		}
		return map;
	}

	public PageBean getPageList(LoginBean loginBean) {
		LoginDao loginDao = new LoginDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		PageBean pageBean = loginDao.getPageList(loginBean, sqlInterfaceDao);
		return pageBean;
	}
	public boolean deleteLogin(String[] login_ids) {
		LoginDao loginDao = new LoginDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		if (loginDao.deleteLogin(login_ids, sqlInterfaceDao)) {
			sqlInterfaceDao.commit();
			return true;
		} else {
			sqlInterfaceDao.rollback();
			return false;
		}
	}
	
	public ReturnBean loginCheck(LoginBean loginBean) {
		ReturnBean returnBean = new ReturnBean();
		returnBean.setReturnInfo("登陆成功");
		LoginDao loginDao = new LoginDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		if (loginBean.getLogin_id() == null
				|| "".equals(loginBean.getLogin_id())) {
			returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
			returnBean.setReturnInfo("用户为空");
			return returnBean;
		}

		LoginBean tempLoginBean = loginDao.getLoginInfoToLoginId(loginBean
				.getLogin_id(), sqlInterfaceDao);

		if (tempLoginBean != null) {
			String passwd = tempLoginBean.getLogin_pd();
			String status = tempLoginBean.getLogin_status();
			if ("0202".equals(status)) {
				returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
				returnBean.setReturnInfo("用户已被禁用");
				return returnBean;
			}

			try {
				if ((MD5Utils.encode(loginBean.getLogin_pd())).equals(passwd)) {
					return returnBean;
				} else {
					returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
					returnBean.setReturnInfo("密码错误");
					return returnBean;
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
				returnBean.setReturnInfo("密码错误");
				return returnBean;
			}
		} else {
			returnBean.setReturnValue(CommonUtils.SERVICE_RETURN_FAIL);
			returnBean.setReturnInfo("用户不存在");
			return returnBean;
		}
	}

	public SessionBean getSessionBean(String login_id) {
		LoginDao loginDao = new LoginDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		return loginDao.getSessionBean(login_id, sqlInterfaceDao);
	}

	public List<Map<String, String>> getFuncTree(String login_id) {
		LoginDao loginDao = new LoginDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		return loginDao.getFuncTree(login_id, sqlInterfaceDao);
	}

	public List<Map<String, String>> getRoleDict() {
		LoginDao loginDao = new LoginDao();
		SqlInterfaceDao sqlInterfaceDao = new SqlImpDao();
		return loginDao.getRoleDict(sqlInterfaceDao);
	}
}
