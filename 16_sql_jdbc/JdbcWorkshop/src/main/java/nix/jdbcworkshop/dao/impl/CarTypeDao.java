/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import nix.jdbcworkshop.dao.GeneralCarTypeDao;
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
public class CarTypeDao implements GeneralCarTypeDao {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private Configuration jdbcConfig;

    public CarTypeDao()            
    {
        try {
            jdbcConfig = new PropertiesConfiguration("jdbc.properties");
            Class.forName(jdbcConfig.getString("jdbc.driver"));
        } catch (ClassNotFoundException | ConfigurationException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }
    
    @Override
    public void create(CarType carType) { 
        try (Connection conn
                = ConnectionManagerH2.getConnection(jdbcConfig.getString("jdbc.connection.string"),
                        jdbcConfig.getString("jdbc.username"),
                        jdbcConfig.getString("jdbc.password"))) {
            PreparedStatement newCarType 
                    = conn.prepareStatement("INSERT INTO car_type (brand,model)VALUES (?,?)");
            newCarType.setString(1, carType.getBrand());
            newCarType.setString(2, carType.getModel());
            newCarType.executeUpdate();
            ResultSet counters = newCarType.getGeneratedKeys();
            if (counters.next()) {
                carType.setCarTypeId(counters.getInt(1));               
            }
            counters.close();
        } catch (SQLException | RuntimeException ex) {            
            LOGGER.error(ex);
        }
    }

    @Override
    public void update(CarType carType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(CarType carType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CarType> findCar(String brand, String model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
