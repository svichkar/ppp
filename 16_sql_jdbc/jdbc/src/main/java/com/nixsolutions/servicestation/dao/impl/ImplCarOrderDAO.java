package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarOrderDAO;
import com.nixsolutions.servicestation.entity.CarOrder;
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
public class ImplCarOrderDAO implements CarOrderDAO {
    public static Logger LOGGER = LogManager.getLogger(ImplCarOrderDAO.class.getName());

    @Override
    public boolean create(CarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO car_order " +
                     "(car_id, car_order_status_id, start_date) VALUES (?, ?, ?);")) {
            pStatement.setInt(1, entity.getCarId());
            pStatement.setInt(2, entity.getCarOrderStatusId());
            pStatement.setDate(3, new Date(entity.getStartDate().getTime()));
            LOGGER.trace("Row in car_order was created");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean update(CarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE car_order " +
                     "SET car_id=?, car_order_status_id=?, start_date=?, end_date=? WHERE car_order_id=?;")) {
            pStatement.setInt(1, entity.getCarId());
            pStatement.setInt(2, entity.getCarOrderStatusId());
            pStatement.setDate(3, new Date(entity.getStartDate().getTime()));
            pStatement.setDate(4, new Date(entity.getEndDate().getTime()));
            pStatement.setInt(5, entity.getCarOrderId());
            LOGGER.trace("Row in car_order with id = " + entity.getCarOrderId() + " was updated");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(CarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM car_order WHERE car_order_id=?;")) {
            pStatement.setInt(1, entity.getCarOrderId());
            LOGGER.trace("Row in car_order with id = " + entity.getCarOrderId() + " was deleted");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public CarOrder findById(Integer id) {
        CarOrder carOrder = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM car_order WHERE car_order_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            carOrder = new CarOrder();
            rSet.next();
            carOrder.setCarOrderId(rSet.getInt("car_order_id"));
            carOrder.setCarId(rSet.getInt("car_id"));
            carOrder.setCarOrderStatusId(rSet.getInt("car_order_status_id"));
            carOrder.setStartDate(rSet.getDate("start_date"));
            carOrder.setEndDate(rSet.getDate("end_date"));
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        if (carOrder != null) {
            LOGGER.trace("Row in car_order with id = " + carOrder.getCarOrderId() + " was found");
        }
        return carOrder;
    }

    @Override
    public List<CarOrder> findAll() {
        List<CarOrder> carOrders = new ArrayList<>();
        int i = 0;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM car_order;")) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                CarOrder carOrder = new CarOrder();
                carOrder.setCarOrderId(rSet.getInt("car_order_id"));
                carOrder.setCarId(rSet.getInt("car_id"));
                carOrder.setCarOrderStatusId(rSet.getInt("car_order_status_id"));
                carOrder.setStartDate(rSet.getDate("start_date"));
                carOrder.setEndDate(rSet.getDate("end_date"));
                carOrders.add(carOrder);
                i++;
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in car_order were found");
        return carOrders;
    }
}
