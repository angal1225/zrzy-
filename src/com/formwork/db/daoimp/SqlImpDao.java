package com.formwork.db.daoimp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.formwork.db.JdbcParameter;
import com.formwork.db.SqlInterfaceDao;
import com.formwork.db.pool.DBPoolConnection;

public class SqlImpDao implements SqlInterfaceDao {
	private Connection connection = null;
	Logger logger = Logger.getLogger(SqlImpDao.class);

	public List<Map<String, String>> queryDataMap(String sqlString,
			List<JdbcParameter> inParams) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			getConnection();
			preparedStatement = connection.prepareStatement(sqlString);
			logger.debug(sqlString);
			if (inParams != null) {
				for (int i = 0; i < inParams.size(); i++) {
					JdbcParameter param = inParams.get(i);
					int type = param.getParameterType();
					String value = param.getParameterValue();
					logger.debug(param.getParameterName() + ":"
							+ param.getParameterValue());
					switch (type) {
					case Types.CHAR:
						preparedStatement.setString(i + 1, value);
						break;
					case Types.VARCHAR:
						preparedStatement.setString(i + 1, value);
						break;
					case Types.INTEGER:
						preparedStatement
								.setInt(i + 1, Integer.parseInt(value));
						break;
					case Types.DOUBLE:
						preparedStatement.setDouble(i + 1,
								Double.parseDouble(value));
						break;
					case Types.BIGINT:
						preparedStatement.setLong(i + 1, Long.parseLong(value));
						break;
					default:
						throw new RuntimeException("读取数据出错");
					}
				}
			}
			resultSet = preparedStatement.executeQuery();
			list = getListMap(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库出错");
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public List<String[]> queryDataStrs(String sqlString,
			List<JdbcParameter> inParams) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<String[]> list = new ArrayList<String[]>();
		try {
			getConnection();
			preparedStatement = connection.prepareStatement(sqlString);
			logger.debug(sqlString);
			if (inParams != null) {
				for (int i = 0; i < inParams.size(); i++) {
					JdbcParameter param = inParams.get(i);
					int type = param.getParameterType();
					String value = param.getParameterValue();
					logger.debug(param.getParameterName() + ":"
							+ param.getParameterValue());
					switch (type) {
					case Types.CHAR:
						preparedStatement.setString(i + 1, value);
						break;
					case Types.VARCHAR:
						preparedStatement.setString(i + 1, value);
						break;
					case Types.INTEGER:
						preparedStatement
								.setInt(i + 1, Integer.parseInt(value));
						break;
					case Types.DOUBLE:
						preparedStatement.setDouble(i + 1,
								Double.parseDouble(value));
						break;
					case Types.BIGINT:
						preparedStatement.setLong(i + 1, Long.parseLong(value));
						break;
					default:
						throw new RuntimeException("读取数据出错");
					}
				}
			}
			resultSet = preparedStatement.executeQuery();
			list = getListStrs(resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库出错");
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public String queryString(String sqlString, List<JdbcParameter> inParams) {
		String string = null;
		List<String[]> list = queryDataStrs(sqlString, inParams);
		if (list != null && list.size() > 0) {
			String[] strs = list.get(0);
			if (strs != null && strs.length > 0) {
				string = strs[0];
			}
		}
		return string;
	}

	public String queryString(String sqlString) {
		return queryString(sqlString, null);
	}

	public long queryCount(String sqlString, List<JdbcParameter> inParams) {
		String string = queryString(sqlString, inParams);
		long number = 0;
		try {
			number = Integer.parseInt(string);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return number;
	}

	public long queryCount(String sqlString) {
		return queryCount(sqlString, null);
	}

	public int updateData(String sqlString, List<JdbcParameter> inParams) {
		PreparedStatement preparedStatement = null;
		int number = 0;
		try {
			getConnection();
			preparedStatement = connection.prepareStatement(sqlString);
			logger.debug(sqlString);
			if (inParams != null) {
				for (int i = 0; i < inParams.size(); i++) {
					JdbcParameter param = inParams.get(i);
					int type = param.getParameterType();
					String value = param.getParameterValue();
					logger.debug(param.getParameterName() + ":"
							+ param.getParameterValue());
					switch (type) {
					case Types.CHAR:
						preparedStatement.setString(i + 1, value);
						break;
					case Types.VARCHAR:
						preparedStatement.setString(i + 1, value);
						break;
					case Types.INTEGER:
						preparedStatement
								.setInt(i + 1, Integer.parseInt(value));
						break;
					case Types.DOUBLE:
						preparedStatement.setDouble(i + 1,
								Double.parseDouble(value));
						break;
					case Types.BIGINT:
						preparedStatement.setLong(i + 1, Long.parseLong(value));
						break;
					default:
						throw new RuntimeException("读取数据出错");
					}
				}
			}
			number = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("数据库出错");
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return number;
	}

	public List<Map<String, String>> queryDataMap(String sqlString) {
		return queryDataMap(sqlString, null);
	}

	public List<String[]> queryDataStrs(String sqlString) {
		return queryDataStrs(sqlString, null);
	}

	public int updateData(String sqlString) {
		return updateData(sqlString);
	}

	public boolean rollback() {
		try {
			connection.rollback();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean commit() {
		try {
			connection.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean close() {
		try {
			connection.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DBPoolConnection.getInstance().getConnection();
				connection.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	private List<Map<String, String>> getListMap(ResultSet rs)
			throws SQLException {
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();

		while (rs.next()) {
			Map<String, String> map = new HashMap<String, String>();
			for (int i = 1; i <= cols; i++) {
				if (rs.getMetaData().getColumnType(i) == 8) {
					map.put(rsmd.getColumnLabel(i),
							String.valueOf(rs.getBigDecimal(i)));
				} else {
					map.put(rsmd.getColumnLabel(i), rs.getString(i));
				}
			}
			returnList.add(map);
		}
		return returnList;
	}

	private List<String[]> getListStrs(ResultSet rs) throws SQLException {
		List<String[]> returnList = new ArrayList<String[]>();
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();

		while (rs.next()) {
			String[] strs = new String[cols];
			for (int i = 1; i <= cols; i++) {
				if (rs.getMetaData().getColumnType(i) == 8) {
					strs[i - 1] = String.valueOf(rs.getBigDecimal(i));
				} else {
					strs[i - 1] = rs.getString(i);
				}
			}
			returnList.add(strs);
		}
		return returnList;
	}
}
