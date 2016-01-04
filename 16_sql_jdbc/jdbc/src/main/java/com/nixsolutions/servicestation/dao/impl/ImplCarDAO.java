package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarDAO;
import com.nixsolutions.servicestation.entity.Car;
import com.nixsolutions.servicestation.util.CustomConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class ImplCarDAO implements CarDAO {
    public static Logger LOGGER = LogManager.getLogger(ImplCarDAO.class.getName());

    @Override
    public boolean create(Car entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO car (car_id, serial_id, car_type_id, client_id) VALUES (?, ?, ? ,?);")) {
            pStatement.setInt(1, entity.getCarId());
            pStatement.setString(2, entity.getSerialId());
            pStatement.setInt(3, entity.getCarTypeId());
            pStatement.setInt(4, entity.getClientId());
            LOGGER.trace("Row in car was created");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean update(Car entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE car SET car_id=?, serial_id=?, car_type_id=?, client_id=? WHERE car_id=?;")) {
            pStatement.setInt(1, entity.getCarId());
            pStatement.setString(2, entity.getSerialId());
            pStatement.setInt(3, entity.getCarTypeId());
            pStatement.setInt(4, entity.getClientId());
            pStatement.setInt(5, entity.getCarTypeId());
            LOGGER.trace("Row in car with id = " + entity.getCarId() + " was updated");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(Car entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM car WHERE car_id=?;")) {
            pStatement.setInt(1, entity.getCarId());
            LOGGER.trace("Row in car with id = " + entity.getCarId() + " was deleted");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public Car findById(Integer id) {
        Car car = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM car WHERE car_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            car = new Car();
            rSet.next();
            car.setCarId(rSet.getInt("car_id"));
            car.setSerialId(rSet.getString("serial_id"));
            car.setCarTypeId(rSet.getInt("car_type_id"));
            car.setClientId(rSet.getInt("client_id"));
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        if (car != null) {
            LOGGER.trace("Row in car with id = " + car.getCarId() + " was found");
        }
        return car;
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        int i = 0;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM car;")) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                Car car = new Car();
                car.setCarId(rSet.getInt("car_id"));
                car.setSerialId(rSet.getString("serial_id"));
                car.setCarTypeId(rSet.getInt("car_type_id"));
                car.setClientId(rSet.getInt("client_id"));
                cars.add(car);
                i++;
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in car were found");
        return cars;
    }
}
