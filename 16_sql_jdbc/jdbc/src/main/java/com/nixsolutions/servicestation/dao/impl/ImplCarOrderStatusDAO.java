package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarOrderStatusDAO;
import com.nixsolutions.servicestation.entity.CarOrderStatus;
import com.nixsolutions.servicestation.util.CustomConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class ImplCarOrderStatusDAO implements CarOrderStatusDAO{
    public static Logger LOGGER = LogManager.getLogger(ImplCarOrderStatusDAO.class.getName());

    @Override
    public boolean create(CarOrderStatus entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO car_order_status (name) VALUES (?);")) {
            pStatement.setString(1, entity.getName());
            LOGGER.trace("Row in car_order_status was created");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean update(CarOrderStatus entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE car_order_status " +
                     "SET name=? WHERE car_order_status_id=?;")) {
            pStatement.setString(1, entity.getName());
            pStatement.setInt(2, entity.getCarOrderStatusId());
            LOGGER.trace("Row in car_order_status with id = " + entity.getCarOrderStatusId() + " was updated");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(CarOrderStatus entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM car_order_status " +
                     "WHERE car_order_status_id=?;")) {
            pStatement.setInt(1, entity.getCarOrderStatusId());
            LOGGER.trace("Row in car_order_status with id = " + entity.getCarOrderStatusId() + " was deleted");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public CarOrderStatus findById(Integer id) {
        CarOrderStatus carOrderStatus = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM car_order_status " +
                     "WHERE car_order_status_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            carOrderStatus = new CarOrderStatus();
            rSet.next();
            carOrderStatus.setCarOrderStatusId(rSet.getInt("car_order_status_id"));
            carOrderStatus.setName(rSet.getString("name"));
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        if (carOrderStatus != null) {
            LOGGER.trace("Row in car_order_status with id = " + carOrderStatus.getCarOrderStatusId() + " was found");
        }
        return carOrderStatus;
    }

    @Override
    public List<CarOrderStatus> findAll() {
        List<CarOrderStatus> carOrderStatuses = new ArrayList<>();
        int i = 0;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM car_order_status;")) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                CarOrderStatus carOrderStatus = new CarOrderStatus();
                carOrderStatus.setCarOrderStatusId(rSet.getInt("car_order_status_id"));
                carOrderStatus.setName(rSet.getString("name"));
                carOrderStatuses.add(carOrderStatus);
                i++;
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in car_order_status were found");
        return carOrderStatuses;
    }
}