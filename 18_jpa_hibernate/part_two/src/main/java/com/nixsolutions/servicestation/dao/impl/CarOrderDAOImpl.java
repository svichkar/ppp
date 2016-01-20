package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarOrderDAO;
import com.nixsolutions.servicestation.entity.CarOrder;
import com.nixsolutions.servicestation.util.CustomConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class CarOrderDAOImpl extends GenericAbstractDAO<CarOrder> implements CarOrderDAO {

    /*public List<UserCarOrderBean> getUserCarOrders(String login) {
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
    }*/

    /*public List<CarOrder> getUserCarOrders() {
        List<CarOrder> userCarOrderBeans = new ArrayList<>();
        int i = 0;
        String sql = "SELECT u.login, co.car_order_id, co.start_date, co.end_date, cos.car_order_status_id, cos.name, car.car_id, car.serial_id, ct.brand, ct.model_name FROM user u " +
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
                uco.setStartDate(rs.getDate("start_date"));
                uco.setEndDate(rs.getDate("end_date"));
                uco.setCarOrderStatusId(rs.getInt("car_order_status_id"));
                uco.setCarId(rs.getInt("car_id"));
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
    }*/
}
