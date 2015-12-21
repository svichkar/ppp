/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.app;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
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
        try {
            jdbcConfig = new PropertiesConfiguration("jdbc.properties");
            Class.forName(jdbcConfig.getString("jdbc.driver"));
        } catch (ClassNotFoundException | ConfigurationException ex) {
            LOGGER.error(ex);
        }
        try (Connection conn
                = DriverManager.getConnection(jdbcConfig.getString("jdbc.connection.string"),
                        jdbcConfig.getString("jdbc.username"),
                        jdbcConfig.getString("jdbc.password"))) {
            conn.setAutoCommit(false);
            conn.createStatement().executeUpdate("DROP ALL OBJECTS;");
            LOGGER.info("Tables are deleted");
            System.out.println("Tables are deleted");
            conn.close();
        } catch (SQLException ex) {
            LOGGER.error(ex);
        }
    }

}
