package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarDAO;
import com.nixsolutions.servicestation.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by rybkinrolla on 04.01.2016.
 */
public class CarDAOImpl extends GenericAbstractDAO<Car> implements CarDAO {

    /*public List<CarBean> getUserCars(){
        List<CarBean> carBeans = new ArrayList<>();
        int i=0;
        String sql = "SELECT u.login, car.car_id, c.client_id, c.first_name, c.last_name, ct.brand, ct.model_name, car.serial_id FROM user u " +
                "INNER JOIN client c ON u.user_id = c.user_id " +
                "INNER JOIN car ON c.client_id = car.client_id " +
                "INNER JOIN car_type ct ON car.car_type_id = ct.car_type_id ORDER BY u.login";
        try (Connection conn = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = conn.prepareStatement(sql)) {
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                CarBean cb = new CarBean();
                cb.setCarId(rs.getInt("car_id"));
                cb.setLogin(rs.getString("login"));
                cb.setClientFSName(rs.getString("first_name") + " " + rs.getString("last_name"));
                cb.setCarBrand((rs.getString("brand")));
                cb.setCarModel((rs.getString("model_name")));
                cb.setCarVIN(rs.getString("serial_id"));
                cb.setClientId(rs.getInt("client_id"));
                carBeans.add(cb);
                i++;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (carBeans != null) {
            LOGGER.trace(i + " rows by getUserCars were found");
        }
        return carBeans;
    }

    public List<CarBean> getCarWithoutOrder(){
        List<CarBean> carBeans = new ArrayList<>();
        int i=0;
        String sql = "select ct.brand, ct.model_name, c.serial_id, c.car_id from car c " +
                "left join car_order co on c.car_id=co.car_id " +
                "inner join car_type ct on c.car_type_id=ct.car_type_id " +
                "where co.car_order_id is null;";
        try (Connection conn = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = conn.prepareStatement(sql)) {
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                CarBean cb = new CarBean();
                cb.setCarId(rs.getInt("car_id"));
                cb.setCarBrand((rs.getString("brand")));
                cb.setCarModel((rs.getString("model_name")));
                cb.setCarVIN(rs.getString("serial_id"));
                carBeans.add(cb);
                i++;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (carBeans != null) {
            LOGGER.trace(i + " rows by getCarWithoutOrder were found");
        }
        return carBeans;
    }*/
}
