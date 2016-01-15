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
import java.util.ArrayList;
import java.util.List;
import nix.jdbcworkshop.entities.Car;
import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import nix.jdbcworkshop.dao.CarDao;

/**
 *
 * @author mednorcom
 */
public class CarDaoH2 implements CarDao {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private Configuration jdbcConfig;

    public CarDaoH2() {
    }

    @Override
    public void create(Car car) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCar = conn.prepareStatement(
                    "INSERT INTO car (serial_id,car_type_id,client_id)VALUES (?,?,?)");
            newCar.setString(1, car.getSerialId());
            newCar.setLong(2, car.getCarTypeId());
            newCar.setLong(3, car.getClientId());
            newCar.executeUpdate();
            ResultSet counters = newCar.getGeneratedKeys();
            if (counters.next()) {
                car.setCarId(counters.getInt(1));
            }
            counters.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void update(Car car) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCar = conn.prepareStatement(
                    "UPDATE car SET serial_id = ?, car_type_id = ?, client_id = ? WHERE car_id = ?");
            newCar.setString(1, car.getSerialId());
            newCar.setLong(2, car.getCarTypeId());
            newCar.setLong(3, car.getClientId());
            newCar.setLong(4, car.getCarId());
            newCar.executeUpdate();
            newCar.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void delete(Car car) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCar = conn.prepareStatement(
                    "DELETE FROM car WHERE car_id = ?");
            newCar.setLong(1, car.getCarId());
            newCar.executeUpdate();
            newCar.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public Car findCarById(long carId) {
        Car searchedCar = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCar
                    = conn.prepareStatement("SELECT * FROM car WHERE car_id = ?");
            newCar.setLong(1, carId);
            ResultSet searchResults = newCar.executeQuery();

            if (searchResults.next()) {
                searchedCar = new Car();
                searchedCar.setCarId(searchResults.getLong("car_id"));
                searchedCar.setSerialId(searchResults.getString("serial_id"));
                searchedCar.setCarTypeId(searchResults.getLong("car_type_id"));
                searchedCar.setClientId(searchResults.getLong("client_id"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return searchedCar;
    }

    @Override
    public List<Car> getCarList() {
        return getCarList(0, -1);
    }

    @Override
    public List<Car> getCarList(int limit) {
        return getCarList(0, limit);
    }

    @Override
    public List<Car> getCarList(int offset, int limit) {
        List<Car> results = new ArrayList<>();
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCar
                    = conn.prepareStatement("SELECT * FROM car LIMIT ? OFFSET ?");
            newCar.setInt(1, limit);
            newCar.setInt(2, offset);
            ResultSet searchResults = newCar.executeQuery();
            while (searchResults.next()) {
                Car searchedCar = new Car();
                searchedCar.setCarId(searchResults.getLong("car_id"));
                searchedCar.setSerialId(searchResults.getString("serial_id"));
                searchedCar.setCarTypeId(searchResults.getLong("car_type_id"));
                searchedCar.setClientId(searchResults.getLong("client_id"));
                results.add(searchedCar);
            }
            searchResults.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return results;
    }

}
