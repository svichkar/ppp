/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
public class DeleteTables {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Configuration jdbcConfig = null;
        Configuration dbunitConfig = null;
        try {
            jdbcConfig = new PropertiesConfiguration("jdbc.properties");
            dbunitConfig = new PropertiesConfiguration("dbunit.properties");
            Class.forName(jdbcConfig.getString("jdbc.driver"));
        } catch (ClassNotFoundException | ConfigurationException ex) {
            LOGGER.error(ex);
        }
        try (Connection conn
                = DriverManager.getConnection(jdbcConfig.getString("jdbc.connection.string"),
                        jdbcConfig.getString("jdbc.username"),
                        jdbcConfig.getString("jdbc.password"));
                Connection testConnection
                = DriverManager.getConnection(dbunitConfig.getString("dbunit.connection.string"),
                        dbunitConfig.getString("dbunit.username"),
                        dbunitConfig.getString("dbunit.password"))) {
            conn.setAutoCommit(false);
            testConnection.setAutoCommit(false);
            conn.createStatement().executeUpdate("DROP ALL OBJECTS;");
            LOGGER.info("Tables are deleted");
            System.out.println("Tables are deleted");
            testConnection.createStatement().executeUpdate("DROP ALL OBJECTS;");
            LOGGER.info("Test tables are deleted");
            System.out.println("Test tables are deleted");
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
    }

}
