package com.zrzy.function.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zrzy.function.bean.FunctionBean;
import com.formwork.base.dao.BaseDao;
import com.formwork.db.JdbcParameter;
import com.formwork.db.SqlInterfaceDao;

public class FunctionDao extends BaseDao {
	public List<Map<String, String>> getFunction(String parent_id, String distance, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();

		String sql = "select a.func_id as id, a.func_name as name, a.parent_id as pId, " +
				"case when (select count(1) from function_info where parent_id=a.func_id)=0 then 'false' else 'true' end isparent " +
				"from function_info a, function_rel b where a.func_id = b.func_id ";
		if (parent_id != null && !"".equals(parent_id)) {
			sql += "and b.parent_id = ? ";
			listJdbcParameter.add(new JdbcParameter("parent_id", Types.VARCHAR,
					parent_id));
		}
		if (distance != null && !"".equals(distance)) {
			sql += "and b.distance = ? ";
			listJdbcParameter.add(new JdbcParameter("distance", Types.VARCHAR,
					distance));
		}
		sql += "order by show_order";

		return testJDBC.queryDataMap(sql,
				listJdbcParameter);
	}

	public List<Map<String, String>> getFunctionInfo(String func_id, String parent_id,
			SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();

		String sql = "select a.func_id, a.func_name, a.func_url, " +
				"a.show_order, a.func_status, a.note " +
				"from function_info a where 0=0 ";

		if (func_id != null && !"".equals(func_id)) {
			sql += "and a.func_id = ? ";
			listJdbcParameter.add(new JdbcParameter("func_id", Types.VARCHAR,
					func_id));
		}
		if (parent_id != null && !"".equals(parent_id)) {
			sql += "and a.parent_id = ? ";
			listJdbcParameter.add(new JdbcParameter("parent_id", Types.VARCHAR,
					parent_id));
		}

		List<Map<String, String>> list = testJDBC.queryDataMap(sql,
				listJdbcParameter);
		return list;
	}

	public int addSave(FunctionBean functionBean,
			SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();

		String sql = "insert into function_info(func_id, func_name, parent_id, func_url, show_order, func_status, note) " +
				"values(?, ?, ?, ?, ?, ?, ?)";
		
		if(functionBean != null){
			listJdbcParameter.add(new JdbcParameter("func_id", Types.VARCHAR,
					functionBean.getFunc_id()));
			listJdbcParameter.add(new JdbcParameter("func_name", Types.VARCHAR,
					functionBean.getFunc_name()));
			listJdbcParameter.add(new JdbcParameter("parent_id", Types.VARCHAR,
					functionBean.getParent_id()));
			listJdbcParameter.add(new JdbcParameter("func_url", Types.VARCHAR,
					functionBean.getFunc_url()));
			listJdbcParameter.add(new JdbcParameter("show_order", Types.VARCHAR,
					functionBean.getShow_order()));
			listJdbcParameter.add(new JdbcParameter("func_status", Types.VARCHAR,
					functionBean.getFunc_status()));
			listJdbcParameter.add(new JdbcParameter("note", Types.VARCHAR,
					functionBean.getNote()));
		}
		
		return testJDBC.updateData(sql, listJdbcParameter);
	}

	public int funcRelSelectSave(String func_id, String parent_id, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "insert into function_rel(func_id, parent_id, distance) " +
				"select '"+func_id+"', parent_id, distance+1 from function_rel where func_id = ?";

		listJdbcParameter.add(new JdbcParameter("func_id", Types.VARCHAR,
				parent_id));

		return testJDBC.updateData(string, listJdbcParameter);
	}

	public int funcRelSave(String func_id, String parent_id, String distance, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "insert into function_rel(func_id, parent_id, distance) values(?, ?, ?)";

		listJdbcParameter.add(new JdbcParameter("func_id", Types.VARCHAR,
				func_id));
		listJdbcParameter.add(new JdbcParameter("parent_id", Types.VARCHAR,
				parent_id));
		listJdbcParameter.add(new JdbcParameter("distance", Types.VARCHAR,
				distance));

		return testJDBC.updateData(string, listJdbcParameter);
	}

	public int updateSave(FunctionBean functionBean, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "update function_info " +
				"set func_name = ?, func_url = ?, show_order = ?, func_status = ?, note = ? " +
				"where func_id=?";

		listJdbcParameter.add(new JdbcParameter("func_name", Types.VARCHAR,
				functionBean.getFunc_name()));
		listJdbcParameter.add(new JdbcParameter("func_url", Types.VARCHAR,
				functionBean.getFunc_url()));
		listJdbcParameter.add(new JdbcParameter("show_order", Types.VARCHAR,
				functionBean.getShow_order()));
		listJdbcParameter.add(new JdbcParameter("func_status", Types.VARCHAR,
				functionBean.getFunc_status()));
		listJdbcParameter.add(new JdbcParameter("note", Types.VARCHAR,
				functionBean.getNote()));
		listJdbcParameter.add(new JdbcParameter("func_id", Types.VARCHAR,
				functionBean.getFunc_id()));

		return testJDBC.updateData(string, listJdbcParameter);
	}

	public int deleteSave(String func_id, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "delete from function_info where func_id = ?";

		listJdbcParameter.add(new JdbcParameter("func_id", Types.VARCHAR,
				func_id));

		return testJDBC.updateData(string, listJdbcParameter);
	}

	public int deleteSaveRel(String func_id, SqlInterfaceDao testJDBC) {
		List<JdbcParameter> listJdbcParameter = new ArrayList<JdbcParameter>();
		String string = "delete from function_rel where func_id = ?";

		listJdbcParameter.add(new JdbcParameter("func_id", Types.VARCHAR,
				func_id));

		return testJDBC.updateData(string, listJdbcParameter);
	}
	
}
