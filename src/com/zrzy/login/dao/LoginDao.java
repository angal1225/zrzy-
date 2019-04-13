package com.zrzy.login.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zrzy.login.bean.LoginBean;
import com.formwork.base.bean.KeyValueBean;
import com.formwork.base.bean.SessionBean;
import com.formwork.base.page.PageBean;
import com.formwork.db.JdbcParameter;
import com.formwork.db.SqlInterfaceDao;

public class LoginDao {
	public PageBean getPageList(LoginBean loginBean, SqlInterfaceDao testJDBC) {
		PageBean pageBean = new PageBean();
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String login_id = loginBean.getLogin_id();
		String login_name = loginBean.getLogin_name();

		String countString = "select count(*) from login_info a, base_dict b where a.login_status = b.id ";

		String string = "select a.login_id, a.login_name, a.login_pd, a.login_role, a.login_status, b.name as status_name, a.create_login, a.create_date "
				+ "from login_info a, base_dict b where a.login_status = b.id ";
		if (login_id != null && !"".equals(login_id)) {
			string += "and a.login_id = ? ";
			countString += "and a.login_id = ? ";
			listJdbcParameter.add(new JdbcParameter("login_id", Types.VARCHAR,
					login_id));
		}
		if (login_name != null && !"".equals(login_name)) {
			string += "and a.login_name like ? ";
			countString += "and a.login_name like ? ";
			listJdbcParameter.add(new JdbcParameter("login_id", Types.VARCHAR,
					"%" + login_name + "%"));
		}
		long number = testJDBC.queryCount(countString, listJdbcParameter);

		string += "limit ?,?";
		listJdbcParameter.add(new JdbcParameter("startNumber", Types.VARCHAR,
				String.valueOf(loginBean.getStartRow())));
		listJdbcParameter.add(new JdbcParameter("size", Types.VARCHAR, String
				.valueOf(loginBean.getRows())));

		List<Map<String, String>> list = testJDBC.queryDataMap(string,
				listJdbcParameter);
		
		pageBean.setTotal(String.valueOf(number));
		pageBean.setRows(list);
		return pageBean;
	}

	public List<Map<String, String>> getLoginInfo(LoginBean loginBean,
			SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String login_id = loginBean.getLogin_id();

		String string = "select a.login_id, a.login_name, a.login_pd, a.login_role, a.login_status, b.name as status_name, a.create_login, a.create_date, a.portrait_url "
				+ "from login_info a, base_dict b where a.login_status = b.id and a.login_id = ?";
		listJdbcParameter.add(new JdbcParameter("login_id", Types.VARCHAR,
				login_id));

		List<Map<String, String>> list = testJDBC.queryDataMap(string,
				listJdbcParameter);
		return list;
	}

	public List<Map<String, String>> getBaseDict(String type,
			SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		List<KeyValueBean> listKv = new ArrayList<KeyValueBean>();

		String string = "select a.id as value, a.name as text from base_dict a where a.type = ?";
		listJdbcParameter.add(new JdbcParameter("type", Types.VARCHAR,
				type));

		List<Map<String, String>> list = testJDBC.queryDataMap(string,
				listJdbcParameter);
		
//		if(list != null && list.size() > 0){
//			for(Map<String, String> map : list){
//				KeyValueBean keyValueBean = new KeyValueBean();
//				keyValueBean.setKey(map.get("id"));
//				keyValueBean.setValue(map.get("name"));
//				listKv.add(keyValueBean);
//			}
//		}
//		
		
//		return listKv;
		return list;
	}

	public boolean addLogin(LoginBean loginBean, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "insert into login_info(login_id, login_name, login_pd, login_role, login_status, create_login, create_date) "
				+ "values(?, ?, ?, ?, ?, ?,now()) ";

		listJdbcParameter.add(new JdbcParameter("login_id", Types.VARCHAR,
				loginBean.getLogin_id()));
		listJdbcParameter.add(new JdbcParameter("login_name", Types.VARCHAR,
				loginBean.getLogin_name()));
		listJdbcParameter.add(new JdbcParameter("login_pd", Types.VARCHAR,
				loginBean.getLogin_pd()));
		listJdbcParameter.add(new JdbcParameter("login_role", Types.VARCHAR,
				loginBean.getLogin_role()));
		listJdbcParameter.add(new JdbcParameter("login_status", Types.VARCHAR,
				loginBean.getLogin_status()));
		listJdbcParameter.add(new JdbcParameter("create_login", Types.VARCHAR,
				loginBean.getCreate_login()));

		int number = testJDBC.updateData(string, listJdbcParameter);
		if (number > 0) {
			return true;
		}
		return false;
	}

	public boolean updateLogin(LoginBean loginBean, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "update login_info set login_name = ?, login_role = ?, login_status = ?, portrait_url = ? where login_id=?";

		listJdbcParameter.add(new JdbcParameter("login_name", Types.VARCHAR,
				loginBean.getLogin_name()));
		listJdbcParameter.add(new JdbcParameter("login_role", Types.VARCHAR,
				loginBean.getLogin_role()));
		listJdbcParameter.add(new JdbcParameter("login_status", Types.VARCHAR,
				loginBean.getLogin_status()));
		listJdbcParameter.add(new JdbcParameter("portrait_url", Types.VARCHAR,
				loginBean.getPortrait_url()));
		listJdbcParameter.add(new JdbcParameter("login_id", Types.VARCHAR,
				loginBean.getLogin_id()));

		int number = testJDBC.updateData(string, listJdbcParameter);
		if (number > 0) {
			return true;
		}
		return false;
	}

	public boolean updateLoginPwd(LoginBean loginBean, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "update login_info set login_pd = ? where login_id=? and login_pd = ?";

		listJdbcParameter.add(new JdbcParameter("new_passwd", Types.VARCHAR,
				loginBean.getNew_passwd()));
		listJdbcParameter.add(new JdbcParameter("login_id", Types.VARCHAR,
				loginBean.getLogin_id()));
		listJdbcParameter.add(new JdbcParameter("old_passwd", Types.VARCHAR,
				loginBean.getOld_passwd()));

		int number = testJDBC.updateData(string, listJdbcParameter);
		if (number > 0) {
			return true;
		}
		return false;
	}

	public boolean updateLoginPd(LoginBean loginBean, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "update login_info set login_pd = ? where login_id=?";

		listJdbcParameter.add(new JdbcParameter("login_pd", Types.VARCHAR,
				loginBean.getLogin_pd()));
		listJdbcParameter.add(new JdbcParameter("login_id", Types.VARCHAR,
				loginBean.getLogin_id()));

		int number = testJDBC.updateData(string, listJdbcParameter);
		if (number > 0) {
			return true;
		}
		return false;
	}

	public boolean deleteLogin(String login_id, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "delete from login_info where login_id = ?";

		listJdbcParameter.add(new JdbcParameter("login_id", Types.VARCHAR,
				login_id));

		int number = testJDBC.updateData(string, listJdbcParameter);
		if (number > 0) {
			return true;
		}
		return false;
	}
	
	public boolean deleteLogin(String[] login_ids, SqlInterfaceDao testJDBC){
		if(login_ids != null && login_ids.length > 0){
			for(String login_id : login_ids){
				if(!deleteLogin(login_id, testJDBC)){
					return false;
				}
			}
		}
		return true;
	}
	
	public LoginBean getLoginInfoToLoginId(String login_id, SqlInterfaceDao testJDBC){
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		LoginBean listBean = new LoginBean();

		String string = "select login_pd, login_status from login_info where login_id = ?";
		listJdbcParameter.add(new JdbcParameter("login_id", Types.VARCHAR,
				login_id));

		List<Map<String, String>> resultList = testJDBC.queryDataMap(string,
				listJdbcParameter);
		
		if(resultList != null && resultList.size() > 0){
			Map<String, String> map = resultList.get(0);
			listBean.setLogin_pd(map.get("login_pd"));
			listBean.setLogin_status(map.get("login_status"));
		}
		
		return listBean;
	}
	
	public SessionBean getSessionBean(String login_id, SqlInterfaceDao testJDBC){
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		List<SessionBean> list = new ArrayList<SessionBean>();
		SessionBean sessionBean = new SessionBean();

		String string = "select a.login_id, a.login_name, a.portrait_url, b.role_id, b.role_name " +
				"from login_info a, role_info b where a.login_role = b.role_id and login_id = ?";
		listJdbcParameter.add(new JdbcParameter("login_id", Types.VARCHAR,
				login_id));

		List<Map<String, String>> resultList = testJDBC.queryDataMap(string,
				listJdbcParameter);
		
		if(resultList != null && resultList.size() > 0){
			Map<String, String> map = resultList.get(0);
			sessionBean.setLogin_id(map.get("login_id"));
			sessionBean.setLogin_name(map.get("login_name"));
			sessionBean.setPortrait_url(map.get("portrait_url"));
			sessionBean.setRole_id(map.get("role_id"));
			sessionBean.setRole_name(map.get("role_name"));
		}
		
		return sessionBean;
	}
	
	public List<Map<String, String>> getFuncTree(String login_id, SqlInterfaceDao testJDBC){
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		List<SessionBean> list = new ArrayList<SessionBean>();
		SessionBean sessionBean = new SessionBean();

		String string = "select a.func_id as id,a.func_name as name,a.parent_id as " +
				"pId,a.func_url as link, " +
				"case (select count(*) from function_info where parent_id=a.func_id) when 0 " +
				"then 'false' else 'true' end as isParent " +
				"from function_info a, login_info b, role_info c, role_func_rel d " +
				"where b.login_role = c.role_id and c.role_id = d.role_id and d.func_id = a.func_id " +
				"and b.login_id=? order by a.show_order";
		listJdbcParameter.add(new JdbcParameter("login_id", Types.VARCHAR,
				login_id));

		List<Map<String, String>> resultList = testJDBC.queryDataMap(string,
				listJdbcParameter);
		
		
		return resultList;
	}
	
	public List<Map<String, String>> getRoleDict(SqlInterfaceDao testJDBC){
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "select role_id as value, role_name as text from role_info";
		List<Map<String, String>> resultList = testJDBC.queryDataMap(string,
				listJdbcParameter);
		return resultList;
	}
}
