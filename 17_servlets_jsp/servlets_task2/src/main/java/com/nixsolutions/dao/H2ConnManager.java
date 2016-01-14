package com.nixsolutions.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

public class H2ConnManager {
	public static final Logger LOG = LogManager.getLogger();
	private static JdbcConnectionPool pool;
	private static String url;
	private static String name;
	private static String pswd;

	private H2ConnManager() {
	}

	public static Connection getConnection() throws SQLException {
		if (pool == null) {
			setProperties();
			pool = JdbcConnectionPool.create(url, name, pswd);
		}
		Connection connect = pool.getConnection();
		return LOG.exit(connect);
	}

	private static void setProperties() {
		Properties prop = new Properties();
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties");
		String dbPath = new File(Thread.currentThread().getContextClassLoader().getResource("sqllab.mv.db").getPath())
				.getParentFile().getPath();
		LOG.trace(dbPath);
		try {
			prop.load(input);
			url = "jdbc:h2:file:" + dbPath + "/sqllab;FILE_LOCK=NO;IFEXISTS=TRUE";
			//url = prop.getProperty("host");
			name = prop.getProperty("login");
			pswd = prop.getProperty("password");
		} catch (IOException e) {
			LOG.error(e);
		}
	}
	
	public static void closeQuitely(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
	
	public static void closeQuitely(ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
	
	public static void closeQuitely(Statement st){
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
	
	public static void rollbackQuitely(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				LOG.error(e);
			}
		}
	}
}
