package com.nixsolutions.studentgrade.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by svichkar on 12/18/2015.
 */
public class H2ConnectionManager {

    private static final Logger LOG = LogManager.getLogger(H2ConnectionManager.class);
    public static JdbcConnectionPool connectionPool;

    public static Connection getConnection() {

        if (connectionPool == null) {

            Properties prop = new Properties();
            try {
                prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
            } catch (IOException e) {
                LOG.error(e);
                throw new RuntimeException(e);
            }
            String user = prop.getProperty("jdbc.user");
            String password = prop.getProperty("jdbc.password");

            String dbLocation = H2ConnectionManager.class.getClassLoader().getResource("sqllab.h2.db").getFile();
            String url = "jdbc:h2:file:" + dbLocation.replaceAll("%20", " ").replaceAll(".h2.db", "") + ";FILE_LOCK=NO";

            connectionPool = JdbcConnectionPool.create(url, user, password);
            connectionPool.setMaxConnections(25);
            connectionPool.setLoginTimeout(120);
        }

        try {
            return connectionPool.getConnection();
        } catch (SQLException e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    public static void closePoolConnections() {
        connectionPool.dispose();
    }
}
