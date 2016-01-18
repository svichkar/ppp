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
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
public class CreateTables {

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
                        jdbcConfig.getString("jdbc.password"));) {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            for (String sqlStatement
                    : FileUtils.readLines(new File(classLoader.getResource(
                            jdbcConfig.getString("jdbc.ddl.file")).getFile()))) {
                conn.createStatement().executeUpdate(sqlStatement);
                LOGGER.info("Success: " + sqlStatement);
            }
            LOGGER.info("DB structure is created");
            System.out.println("DB structure is created");
        } catch (SQLException | IOException ex) {
            LOGGER.error(ex);
        }
        if (!jdbcConfig.getString("jdbc.connection.string")
                .equals(dbunitConfig.getString("dbunit.connection.string"))) {
            try (Connection testConnection = DriverManager.getConnection(
                    dbunitConfig.getString("dbunit.connection.string"),
                    dbunitConfig.getString("dbunit.username"),
                    dbunitConfig.getString("dbunit.password"))) {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                for (String sqlStatement
                        : FileUtils.readLines(new File(classLoader.getResource(
                                jdbcConfig.getString("jdbc.ddl.file")).getFile()))) {
                    testConnection.createStatement().executeUpdate(sqlStatement);
                    LOGGER.info("Success: " + sqlStatement);
                }
                LOGGER.info("Test DB structure is created");
                System.out.println("Test DB structure is created");
            } catch (SQLException | IOException ex) {
                LOGGER.error(ex);
            }
        }

    }

}
