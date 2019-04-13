package com.zrzy.role.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zrzy.role.bean.RoleBean;
import com.formwork.base.page.PageBean;
import com.formwork.db.JdbcParameter;
import com.formwork.db.SqlInterfaceDao;

public class RoleDao {
	public PageBean queryList(RoleBean roleBean,
			SqlInterfaceDao testJDBC) {
		PageBean pageBean = new PageBean();
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();

		String sql = "select a.role_id, a.role_name, a.role_status, b.name as status_name from role_info a, base_dict b "
				+ "where a.role_status = b.id ";
		String sqlCount = "select a.role_id, a.role_name, a.role_status, b.name as status_name from role_info a, base_dict b "
				+ "where a.role_status = b.id ";

		if (roleBean.getRole_id() != null && !"".equals(roleBean.getRole_id())) {
			sql += "and a.role_id = ? ";
			sqlCount += "and a.role_id = ? ";
			listJdbcParameter.add(new JdbcParameter("role_id", Types.VARCHAR,
					roleBean.getRole_id()));
		}
		if (roleBean.getRole_name() != null
				&& !"".equals(roleBean.getRole_name())) {
			sql += "and a.role_name like CONCAT ('%',?,'%') ";
			sqlCount += "and a.role_name like CONCAT ('%',?,'%') ";
			listJdbcParameter.add(new JdbcParameter("role_name", Types.VARCHAR,
					roleBean.getRole_name()));
		}
		if (roleBean.getRole_status() != null
				&& !"".equals(roleBean.getRole_status())) {
			sql += "and a.role_status = ? ";
			sqlCount += "and a.role_status = ? ";
			listJdbcParameter.add(new JdbcParameter("role_status",
					Types.VARCHAR, roleBean.getRole_status()));
		}
		
		long count = testJDBC.queryCount(sqlCount, listJdbcParameter);
		pageBean.setTotal(String.valueOf(count));
		sql += " limit ?, ?";

		listJdbcParameter.add(new JdbcParameter("startRow",
				Types.VARCHAR, String.valueOf(roleBean.getStartRow())));
		listJdbcParameter.add(new JdbcParameter("rows",
				Types.VARCHAR, String.valueOf(roleBean.getRows())));
		List<Map<String, String>> resultList = testJDBC.queryDataMap(sql, listJdbcParameter);
		pageBean.setRows(resultList);
		return pageBean;
	}
	
	public List<Map<String, String>> queryList(String role_id,
			SqlInterfaceDao testJDBC) {
		PageBean pageBean = new PageBean();
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();

		String sql = "select a.role_id, a.role_name, a.role_status, b.name as status_name from role_info a, base_dict b "
				+ "where a.role_status = b.id ";

		if (role_id != null && !"".equals(role_id)) {
			sql += "and a.role_id = ? ";
			listJdbcParameter.add(new JdbcParameter("role_id", Types.VARCHAR,
					role_id));
		}
		
		return testJDBC.queryDataMap(sql, listJdbcParameter);
	}

	public int addSave(RoleBean roleBean, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();

		String sql = "insert into role_info(role_id, role_name, role_status) values(?, ?, ?)";
		listJdbcParameter.add(new JdbcParameter("role_id", Types.VARCHAR,
				roleBean.getRole_id()));
		listJdbcParameter.add(new JdbcParameter("role_name", Types.VARCHAR,
				roleBean.getRole_name()));
		listJdbcParameter.add(new JdbcParameter("role_status", Types.VARCHAR,
				roleBean.getRole_status()));

		return testJDBC.updateData(sql, listJdbcParameter);
	}

	public int upSave(RoleBean roleBean, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();

		String sql = "update role_info set role_name = ?, role_status = ? where role_id = ?";
		listJdbcParameter.add(new JdbcParameter("role_name", Types.VARCHAR,
				roleBean.getRole_name()));
		listJdbcParameter.add(new JdbcParameter("role_status", Types.VARCHAR,
				roleBean.getRole_status()));
		listJdbcParameter.add(new JdbcParameter("role_id", Types.VARCHAR,
				roleBean.getRole_id()));

		return testJDBC.updateData(sql, listJdbcParameter);
	}

	public long roleFunc(String role_id, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String sql = "select count(0) from role_func_rel where role_id = ?";
		listJdbcParameter.add(new JdbcParameter("role_id", Types.VARCHAR,
				role_id));

		return testJDBC.queryCount(sql, listJdbcParameter);
	}

	public int deleteRole(String role_id, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();

		String string = "delete from role_info where role_id = ?";
		listJdbcParameter.add(new JdbcParameter("role_id", Types.VARCHAR,
				role_id));

		return testJDBC.updateData(string, listJdbcParameter);
	}

	public List<Map<String, String>> getFuncTree(String role_id,
			SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();

		String sql = "select a.func_id as id,a.func_name as name,a.parent_id as pId, "
				+ "case when (select count(1) from function_info "
				+ "where parent_id=a.func_id)=0 then 'false' else 'true' end isparent, "
				+ "case when (select count(1) from role_func_rel where func_id=a.func_id ";

		if (role_id != null && !"".equals(role_id)) {
			sql += "and role_id = ? ";
			listJdbcParameter.add(new JdbcParameter("role_id", Types.VARCHAR,
					role_id));
		}

		sql += ")=0 then 'false' else 'true' end checked from function_info a";

		return testJDBC.queryDataMap(sql, listJdbcParameter);
	}

	public int deleteRoleFunc(String role_id, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "delete from role_func_rel where role_id = ?";

		listJdbcParameter.add(new JdbcParameter("role_id", Types.VARCHAR,
				role_id));

		return testJDBC.updateData(string, listJdbcParameter);
	}

	public int saveRoleFunc(String role_id, String func_id, String status,
			SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "insert into role_func_rel(role_id, func_id, status) values(?, ?, ?)";

		listJdbcParameter.add(new JdbcParameter("role_id", Types.VARCHAR,
				role_id));
		listJdbcParameter.add(new JdbcParameter("func_id", Types.VARCHAR,
				func_id));
		listJdbcParameter
				.add(new JdbcParameter("status", Types.VARCHAR, status));

		return testJDBC.updateData(string, listJdbcParameter);
	}
}
