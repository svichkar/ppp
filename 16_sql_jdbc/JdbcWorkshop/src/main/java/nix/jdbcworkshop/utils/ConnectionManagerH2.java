/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.ConnectionPoolDataSource;
import org.h2.jdbcx.JdbcConnectionPool;

/**
 *
 * @author mednorcom
 */
public class ConnectionManagerH2 {

    private static JdbcConnectionPool connectionPool;
    private static String connectionString;
    private static String username;
    private static String password;

    public static Connection getConnection(String connectionString, String username, String password) throws SQLException {
        if (connectionPool == null) {
            if (!(connectionString.equals(ConnectionManagerH2.connectionString)
                    && username.equals(ConnectionManagerH2.username)
                    && password.equals(ConnectionManagerH2.password))) {
                connectionPool = JdbcConnectionPool.create(connectionString, username, password);
                ConnectionManagerH2.connectionString = connectionString;
            }
        }
        return connectionPool.getConnection();
    }
}
