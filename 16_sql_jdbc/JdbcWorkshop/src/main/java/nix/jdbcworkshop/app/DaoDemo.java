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
import nix.jdbcworkshop.dao.impl.CarTypeDao;
import nix.jdbcworkshop.entities.CarType;
import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;

/**
 *
 * @author mednorcom
 */
public class DaoDemo {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*Configuration jdbcConfig = null;
        try {
            jdbcConfig = new PropertiesConfiguration("jdbc.properties");
            Class.forName(jdbcConfig.getString("jdbc.driver"));
        } catch (ClassNotFoundException | ConfigurationException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        try (Connection conn
                = ConnectionManagerH2.getConnection(jdbcConfig.getString("jdbc.connection.string"),
                        jdbcConfig.getString("jdbc.username"),
                        jdbcConfig.getString("jdbc.password"))) {
            conn.setAutoCommit(false);
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            for (String sqlStatement
                    : FileUtils.readLines(new File(classLoader.getResource(jdbcConfig.getString("jdbc.dml.file")).getFile()))) {
                conn.createStatement().executeUpdate(sqlStatement);
            }
            
            conn.close();
        } catch (SQLException | IOException | RuntimeException ex) {
            LOGGER.error(ex);
        }*/
        new CarTypeDao().create(new CarType(null,"Tesla","M"));                
    }

}
