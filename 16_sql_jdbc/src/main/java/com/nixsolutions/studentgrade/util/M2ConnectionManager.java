package com.nixsolutions.studentgrade.util;

import org.h2.jdbcx.JdbcConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by svichkar on 12/18/2015.
 */
public class M2ConnectionManager {

    public static JdbcConnectionPool connectionPool;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        if (connectionPool == null) {

            Properties prop = new Properties();
            try {
                prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String url = prop.getProperty("jdbc.url");
            String user = prop.getProperty("jdbc.user");
            String password = prop.getProperty("jdbc.password");

            connectionPool = JdbcConnectionPool.create(url, user, password);
            connectionPool.setMaxConnections(25);
            connectionPool.setLoginTimeout(120);
        }

        return connectionPool.getConnection();
    }
}
