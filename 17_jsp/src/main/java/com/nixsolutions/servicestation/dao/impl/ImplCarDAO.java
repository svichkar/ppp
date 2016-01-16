package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.CarDAO;
import com.nixsolutions.servicestation.entity.Car;
import com.nixsolutions.servicestation.entity.extendedentity.CarBean;
import com.nixsolutions.servicestation.entity.extendedentity.UserCarOrderBean;
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
    public void create(Car entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO car (serial_id, car_type_id, client_id) VALUES (?, ? ,?);")) {
            pStatement.setString(1, entity.getSerialId());
            pStatement.setInt(2, entity.getCarTypeId());
            pStatement.setInt(3, entity.getClientId());
            pStatement.execute();
            LOGGER.trace("Row in car was created");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(Car entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE car SET serial_id=?, car_type_id=?, client_id=? WHERE car_id=?;")) {
            pStatement.setString(1, entity.getSerialId());
            pStatement.setInt(2, entity.getCarTypeId());
            pStatement.setInt(3, entity.getClientId());
            pStatement.setInt(4, entity.getCarId());
            pStatement.execute();
            LOGGER.trace("Row in car with id = " + entity.getCarId() + " was updated");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Car entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM car WHERE car_id=?;")) {
            pStatement.setInt(1, entity.getCarId());
            pStatement.execute();
            LOGGER.trace("Row in car with id = " + entity.getCarId() + " was deleted");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Car findById(Integer id) {
        Car car = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM car WHERE car_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            car = new Car();
            if(rSet.next()) {
                car.setCarId(rSet.getInt("car_id"));
                car.setSerialId(rSet.getString("serial_id"));
                car.setCarTypeId(rSet.getInt("car_type_id"));
                car.setClientId(rSet.getInt("client_id"));
            } else {
                car=null;
            }
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in car were found");
        return cars;
    }

    public List<CarBean> getUserCars(){
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
}
