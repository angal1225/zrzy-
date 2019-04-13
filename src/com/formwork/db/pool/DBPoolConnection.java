package com.formwork.db.pool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

public class DBPoolConnection {
    private static DBPoolConnection dbPoolConnection = null;
    private static DruidDataSource druidDataSource = null;
    
    static {
        Properties properties = loadPropertiesFile("db_server.properties");
        try {
            druidDataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);    //DruidDataSrouce����ģʽ
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private DBPoolConnection(){}
    
    public static synchronized DBPoolConnection getInstance(){
        if (null == dbPoolConnection){
            dbPoolConnection = new DBPoolConnection();
        }
        return dbPoolConnection;
    }

    public DruidPooledConnection getConnection() throws SQLException{
        return druidDataSource.getConnection();
    }
    
    private static Properties loadPropertiesFile(String fullFile) {
    	Properties properties = new Properties();
        InputStream in = DBPoolConnection.class.getClassLoader().getResourceAsStream("db.properties");
        try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return properties;
    }
    
}