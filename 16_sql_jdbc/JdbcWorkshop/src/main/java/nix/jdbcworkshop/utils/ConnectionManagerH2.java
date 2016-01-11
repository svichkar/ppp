/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.utils;

import java.sql.Connection;
import java.sql.SQLException;
import org.h2.jdbcx.JdbcConnectionPool;

/**
 *
 * @author mednorcom
 */
public class ConnectionManagerH2 {

    private static JdbcConnectionPool connectionPool;

    public static Connection getConnection(String connectionString, String username, String password) throws SQLException {
        if (connectionPool == null) {
            connectionPool = JdbcConnectionPool.create(connectionString, username, password);
        }
        return connectionPool.getConnection();
    }

    public static void closeConnections() {
        connectionPool.dispose();
    }
}
