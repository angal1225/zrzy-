package com.formwork.db;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface SqlInterfaceDao {
	public List<Map<String, String>> queryDataMap(String sqlString, List<JdbcParameter> inParams);
	public List<String[]> queryDataStrs(String sqlString, List<JdbcParameter> inParams);
	public String queryString(String sqlString, List<JdbcParameter> inParams);
	public String queryString(String sqlString);
	public long queryCount(String sqlString, List<JdbcParameter> inParams) ;
	public long queryCount(String sqlString);
	public int updateData(String sqlString, List<JdbcParameter> inParams) ;
	public List<Map<String, String>> queryDataMap(String sqlString);
	public List<String[]> queryDataStrs(String sqlString) ;
	public int updateData(String sqlString);
	public boolean rollback();
	public boolean commit();
	public boolean close();
	public Connection getConnection();
}
