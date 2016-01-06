package com.nixsolutions.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
		InputStream input = Thread.currentThread().getContextClassLoader().
				getResourceAsStream("jdbc.properties");
		String dbUrl = Thread.currentThread().getContextClassLoader().getResource("sqllab.mv.db").getPath();
		String db = dbUrl.substring(0, dbUrl.length()-6);
		LOG.trace(dbUrl);
		try {
			prop.load(input);
			url =  "jdbc:h2:file:" + db + ";IFEXISTS=TRUE";//prop.getProperty("host");// "jdbc:h2:file:" + dbPath;// + "/sqllab";
			name = prop.getProperty("login");
			pswd = prop.getProperty("password");
		} catch (IOException e) {
			LOG.error(e);
		}
	}
}
