/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.utils;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.h2.jdbcx.JdbcConnectionPool;

/**
 *
 * @author mednorcom
 */
public class ConnectionManagerH2 {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private static JdbcConnectionPool connectionPool;
    private static Configuration jdbcConfig = null;

    public static Configuration getJdbcConfig() {
        if (jdbcConfig == null) {
            updateConfig();
        }
        return jdbcConfig;
    }

    public static Connection getConnection(String connectionString,
            String username, String password) throws SQLException {
        if (connectionPool == null) {
            connectionPool = JdbcConnectionPool.create(connectionString, username, password);
        }
        return connectionPool.getConnection();
    }

    public static Connection getConnection() throws SQLException {
        if (connectionPool == null) {
            connectionPool
                    = JdbcConnectionPool.create(getJdbcConfig().getString("jdbc.connection.string"),
                            getJdbcConfig().getString("jdbc.username"),
                            getJdbcConfig().getString("jdbc.password"));
        }
        return connectionPool.getConnection();
    }

    public static void closeConnections() {
        connectionPool.dispose();
    }

    private static void updateConfig() {
        try {
            jdbcConfig = new PropertiesConfiguration("jdbc.properties");
            Class.forName(jdbcConfig.getString("jdbc.driver"));
        } catch (ClassNotFoundException | ConfigurationException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }
}
