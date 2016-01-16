package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarOrderDAO;
import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.entity.extendedentity.UserCarOrderBean;
import com.nixsolutions.servicestation.util.CustomConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class ImplCarOrderDAO implements CarOrderDAO {
    public static Logger LOGGER = LogManager.getLogger(ImplCarOrderDAO.class.getName());

    @Override
    public void create(CarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO car_order " +
                     "(car_id, car_order_status_id, start_date) VALUES (?, ?, ?);")) {
            pStatement.setInt(1, entity.getCarId());
            pStatement.setInt(2, entity.getCarOrderStatusId());
            pStatement.setDate(3, new Date(entity.getStartDate().getTime()));
            pStatement.execute();
            LOGGER.trace("Row in car_order was created");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(CarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE car_order " +
                     "SET car_id=?, car_order_status_id=?, start_date=?, end_date=? WHERE car_order_id=?;")) {
            pStatement.setInt(1, entity.getCarId());
            pStatement.setInt(2, entity.getCarOrderStatusId());
            pStatement.setDate(3, new Date(entity.getStartDate().getTime()));
            if (entity.getEndDate() != null) {
                pStatement.setDate(4, new Date(entity.getEndDate().getTime()));
            } else {
                pStatement.setDate(4, null);
            }
            pStatement.setInt(5, entity.getCarOrderId());
            pStatement.execute();
            LOGGER.trace("Row in car_order with id = " + entity.getCarOrderId() + " was updated");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(CarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM car_order WHERE car_order_id=?;")) {
            pStatement.setInt(1, entity.getCarOrderId());
            pStatement.execute();
            LOGGER.trace("Row in car_order with id = " + entity.getCarOrderId() + " was deleted");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public CarOrder findById(Integer id) {
        CarOrder carOrder = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM car_order WHERE car_order_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            carOrder = new CarOrder();
            if (rSet.next()) {
                carOrder.setCarOrderId(rSet.getInt("car_order_id"));
                carOrder.setCarId(rSet.getInt("car_id"));
                carOrder.setCarOrderStatusId(rSet.getInt("car_order_status_id"));
                carOrder.setStartDate(rSet.getDate("start_date"));
                carOrder.setEndDate(rSet.getDate("end_date"));
            } else {
                carOrder = null;
            }
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in car_order were found");
        return carOrders;
    }

    public List<UserCarOrderBean> getUserCarOrders(String login) {
        List<UserCarOrderBean> userCarOrderBeans = new ArrayList<>();
        int i=0;
        String sql = "SELECT co.car_order_id, cos.name, car.serial_id, ct.brand, ct.model_name FROM user u " +
                "INNER JOIN client c ON u.user_id = c.user_id " +
                "INNER JOIN car ON c.client_id = car.client_id " +
                "INNER JOIN car_type ct ON car.car_type_id = ct.car_type_id " +
                "INNER JOIN car_order co ON car.car_id = co.car_id " +
                "INNER JOIN car_order_status cos ON co.car_order_status_id = cos.car_order_status_id " +
                "WHERE u.login = ?;";
        try (Connection conn = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = conn.prepareStatement(sql)) {
            pStatement.setString(1, login);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                UserCarOrderBean uco = new UserCarOrderBean();
                uco.setCarOrderId(rs.getInt("car_order_id"));
                uco.setCarOrderStatus(rs.getString("name"));
                uco.setSerialId(rs.getString("serial_id"));
                uco.setCarModel(rs.getString("brand") + " " + rs.getString("model_name"));
                uco.setUserLogin(login);
                userCarOrderBeans.add(uco);
                i++;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (userCarOrderBeans != null) {
            LOGGER.trace(i + " rows by getUserCarOrders were found");
        }
        return userCarOrderBeans;
    }

    public List<UserCarOrderBean> getUserCarOrders() {
        List<UserCarOrderBean> userCarOrderBeans = new ArrayList<>();
        int i = 0;
        String sql = "SELECT u.login, co.car_order_id, cos.name, car.serial_id, ct.brand, ct.model_name FROM user u " +
                "INNER JOIN client c ON u.user_id = c.user_id " +
                "INNER JOIN car ON c.client_id = car.client_id " +
                "INNER JOIN car_type ct ON car.car_type_id = ct.car_type_id " +
                "INNER JOIN car_order co ON car.car_id = co.car_id " +
                "INNER JOIN car_order_status cos ON co.car_order_status_id = cos.car_order_status_id ORDER BY u.login;";
        try (Connection conn = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = conn.prepareStatement(sql)) {
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                UserCarOrderBean uco = new UserCarOrderBean();
                uco.setUserLogin(rs.getString("login"));
                uco.setCarOrderId(rs.getInt("car_order_id"));
                uco.setCarOrderStatus(rs.getString("name"));
                uco.setSerialId(rs.getString("serial_id"));
                uco.setCarModel(rs.getString("brand") + " " + rs.getString("model_name"));
                userCarOrderBeans.add(uco);
                i++;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (userCarOrderBeans != null) {
            LOGGER.trace(i + " rows by getUserCarOrders were found");
        }
        return userCarOrderBeans;
    }
}
