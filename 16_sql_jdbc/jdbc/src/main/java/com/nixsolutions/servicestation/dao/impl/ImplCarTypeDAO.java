package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarTypeDAO;
import com.nixsolutions.servicestation.entity.CarType;
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
 * Created by rybkinrolla on 29.12.2015.
 */
public class ImplCarTypeDAO implements CarTypeDAO {
    public static Logger LOGGER = LogManager.getLogger(ImplCarTypeDAO.class.getName());

    @Override
    public void create(CarType entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO car_type " +
                     "(brand, model_name) VALUES (?, ?);")) {
            pStatement.setString(1, entity.getBrand());
            pStatement.setString(2, entity.getModelName());
            pStatement.execute();
            LOGGER.trace("Row in car_type was created");

        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(CarType entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE car_type " +
                     "SET brand=?, model_name=? WHERE car_type_id=?;")) {
            pStatement.setString(1, entity.getBrand());
            pStatement.setString(2, entity.getModelName());
            pStatement.setInt(3, entity.getCarTypeId());
            pStatement.execute();
            LOGGER.trace("Row in car_type with id = " + entity.getCarTypeId() + " was updated");
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(CarType entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM car_type WHERE car_type_id=?;")) {
            pStatement.setInt(1, entity.getCarTypeId());
            pStatement.execute();
            LOGGER.trace("Row in car_type with id = " + entity.getCarTypeId() + " was deleted");
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public CarType findById(Integer id) {
        CarType carType = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM car_type WHERE car_type_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            carType = new CarType();
            rSet.next();
            carType.setCarTypeId(rSet.getInt("car_type_id"));
            carType.setBrand(rSet.getString("brand"));
            carType.setModelName(rSet.getString("model_name"));
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        if (carType != null) {
            LOGGER.trace("Row in car_type with id = " + carType.getCarTypeId() + " was found");
        }
        return carType;
    }

    @Override
    public List<CarType> findAll() {
        List<CarType> carTypes = new ArrayList<>();
        int i = 0;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM car_type;")) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                CarType carType = new CarType();
                carType.setCarTypeId(rSet.getInt("car_type_id"));
                carType.setBrand(rSet.getString("brand"));
                carType.setModelName(rSet.getString("model_name"));
                carTypes.add(carType);
                i++;
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in car_type were found");
        return carTypes;
    }
}
