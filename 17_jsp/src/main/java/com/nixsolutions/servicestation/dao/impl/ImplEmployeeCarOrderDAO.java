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
    public static Logger LOGGER = LogManager.getLogger(ImplEmployeeCarOrderDAO.class.getName());

    @Override
    public void create(EmployeeCarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO employee_car_order " +
                     "(employee_id, car_order_id) VALUES (?, ?);")) {
            pStatement.setInt(1, entity.getEmployeeId());
            pStatement.setInt(2, entity.getCarOrderId());
            pStatement.execute();
            LOGGER.trace("Row in employee_car_order was created");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(EmployeeCarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE employee_car_order " +
                     "SET employee_id=?, car_order_id=? WHERE identifier=?;")) {
            pStatement.setInt(1, entity.getEmployeeId());
            pStatement.setInt(2, entity.getCarOrderId());
            pStatement.setInt(3, entity.getIdentifier());
            pStatement.execute();
            LOGGER.trace("Row in employee_car_order with id = " + entity.getIdentifier() + " was updated");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(EmployeeCarOrder entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM employee_car_order " +
                     "WHERE identifier=?;")) {
            pStatement.setInt(1, entity.getIdentifier());
            pStatement.execute();
            LOGGER.trace("Row in employee_car_order with id = " + entity.getIdentifier() + " was deleted");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public EmployeeCarOrder findById(Integer id) {
        EmployeeCarOrder employeeCarOrder = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM employee_car_order " +
                     "WHERE identifier=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            employeeCarOrder = new EmployeeCarOrder();
            if(rSet.next()) {
                employeeCarOrder.setIdentifier(rSet.getInt("identifier"));
                employeeCarOrder.setEmployeeId(rSet.getInt("employee_id"));
                employeeCarOrder.setCarOrderId(rSet.getInt("car_order_id"));
            } else {
                employeeCarOrder=null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (employeeCarOrder != null) {
            LOGGER.trace("Row in employee_car_order with id = " + employeeCarOrder.getIdentifier() + " was found");
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
                employeeCarOrder.setIdentifier(rSet.getInt("identifier"));
                employeeCarOrder.setEmployeeId(rSet.getInt("employee_id"));
                employeeCarOrder.setCarOrderId(rSet.getInt("car_order_id"));
                employeeCarOrders.add(employeeCarOrder);
                i++;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in employee_car_order were found");
        return employeeCarOrders;
    }
}
