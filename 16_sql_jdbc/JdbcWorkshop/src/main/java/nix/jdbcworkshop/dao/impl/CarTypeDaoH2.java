/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nix.jdbcworkshop.entities.CarType;
import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import nix.jdbcworkshop.dao.CarTypeDao;

/**
 *
 * @author mednorcom
 */
public class CarTypeDaoH2 implements CarTypeDao {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private Configuration jdbcConfig;

    public CarTypeDaoH2() {
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
        try (Connection conn
                = ConnectionManagerH2.getConnection(jdbcConfig.getString("jdbc.connection.string"),
                        jdbcConfig.getString("jdbc.username"),
                        jdbcConfig.getString("jdbc.password"))) {
            PreparedStatement newCarType = conn.prepareStatement(
                    "UPDATE car_type SET brand = ?, model = ? WHERE car_type_id = ?");
            newCarType.setString(1, carType.getBrand());
            newCarType.setString(2, carType.getModel());
            newCarType.setLong(3, carType.getCarTypeId());
            newCarType.executeUpdate();
            newCarType.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void delete(CarType carType) {
        try (Connection conn
                = ConnectionManagerH2.getConnection(jdbcConfig.getString("jdbc.connection.string"),
                        jdbcConfig.getString("jdbc.username"),
                        jdbcConfig.getString("jdbc.password"))) {
            PreparedStatement newCarType = conn.prepareStatement(
                    "DELETE FROM car_type WHERE car_type_id = ?");
            newCarType.setLong(1, carType.getCarTypeId());
            newCarType.executeUpdate();
            newCarType.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public CarType findCarById(long carTypeId) {
        CarType searchedCarType = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection(jdbcConfig.getString("jdbc.connection.string"),
                        jdbcConfig.getString("jdbc.username"),
                        jdbcConfig.getString("jdbc.password"))) {
            PreparedStatement newCarType
                    = conn.prepareStatement("SELECT * FROM car_type WHERE car_type_id = ?");
            newCarType.setLong(1, carTypeId);
            ResultSet searchResults = newCarType.executeQuery();

            if (searchResults.next()) {
                searchedCarType = new CarType();
                searchedCarType.setCarTypeId(searchResults.getLong("car_type_id"));
                searchedCarType.setBrand(searchResults.getString("brand"));
                searchedCarType.setModel(searchResults.getString("model"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return searchedCarType;
    }

}
