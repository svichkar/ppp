package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.EmployeeCarOrderDAO;
import com.nixsolutions.servicestation.entity.EmployeeCarOrder;
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
public class ImplEmployeeCarOrderDAO implements EmployeeCarOrderDAO {
    public static Logger LOGGER = LogManager.getLogger(ImplCarOrderStatusDAO.class.getName());

    @Override
    public boolean create(EmployeeCarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO employee_car_order " +
                     "(employee_id, car_order_id) VALUES (?, ?);")) {
            pStatement.setInt(1, entity.getEmployeeId());
            pStatement.setInt(2, entity.getCarOrderId());
            LOGGER.trace("Row in employee_car_order was created");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean update(EmployeeCarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE employee_car_order " +
                     "SET employee_id=?, car_order_id=? WHERE employee_id=?;")) {
            pStatement.setInt(1, entity.getEmployeeId());
            pStatement.setInt(2, entity.getCarOrderId());
            pStatement.setInt(3, entity.getEmployeeId());
            LOGGER.trace("Row in employee_car_order with id = " + entity.getEmployeeId() + " was updated");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(EmployeeCarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM employee_car_order " +
                     "WHERE employee_id=?;")) {
            pStatement.setInt(1, entity.getEmployeeId());
            LOGGER.trace("Row in employee_car_order with id = " + entity.getEmployeeId() + " was deleted");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public EmployeeCarOrder findById(Integer id) {
        EmployeeCarOrder employeeCarOrder = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM employee_car_order " +
                     "WHERE employee_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            employeeCarOrder = new EmployeeCarOrder();
            rSet.next();
            employeeCarOrder.setEmployeeId(rSet.getInt("employee_id"));
            employeeCarOrder.setCarOrderId(rSet.getInt("car_order_id"));
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        if (employeeCarOrder != null) {
            LOGGER.trace("Row in employee_car_order with id = " + employeeCarOrder.getEmployeeId() + " was found");
        }
        return employeeCarOrder;
    }

    @Override
    public List<EmployeeCarOrder> findAll() {
        List<EmployeeCarOrder> employeeCarOrders = new ArrayList<>();
        int i = 0;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM employee_car_order;")) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                EmployeeCarOrder employeeCarOrder = new EmployeeCarOrder();
                employeeCarOrder.setEmployeeId(rSet.getInt("employee_id"));
                employeeCarOrder.setCarOrderId(rSet.getInt("car_order_id"));
                employeeCarOrders.add(employeeCarOrder);
                i++;
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in employee_car_order were found");
        return employeeCarOrders;
    }
}
