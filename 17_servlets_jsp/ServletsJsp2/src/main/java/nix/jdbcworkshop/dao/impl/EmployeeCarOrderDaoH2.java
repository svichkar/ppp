/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nix.jdbcworkshop.entities.EmployeeCarOrder;
import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import nix.jdbcworkshop.dao.EmployeeCarOrderDao;

/**
 *
 * @author mednorcom
 */
public class EmployeeCarOrderDaoH2 implements EmployeeCarOrderDao {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private Configuration jdbcConfig;

    public EmployeeCarOrderDaoH2() {
    }

    @Override
    public void create(EmployeeCarOrder employeeCarOrder) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployeeCarOrder = conn.prepareStatement(
                    "INSERT INTO employee_car_order (car_order_id, employee_id) VALUES (?,?)");
            newEmployeeCarOrder.setLong(1, employeeCarOrder.getCarOrderId());
            newEmployeeCarOrder.setLong(2, employeeCarOrder.getEmployeeId());
            newEmployeeCarOrder.executeUpdate();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(EmployeeCarOrder employeeCarOrder) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployeeCarOrder = conn.prepareStatement(
                    "DELETE FROM employee_car_order WHERE car_order_id = ? AND employee_id = ?");
            newEmployeeCarOrder.setLong(1, employeeCarOrder.getCarOrderId());
            newEmployeeCarOrder.setLong(2, employeeCarOrder.getEmployeeId());
            newEmployeeCarOrder.executeUpdate();
            newEmployeeCarOrder.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public EmployeeCarOrder findEmployeeCarOrderByCarOrderId(long carOrderId) {
        EmployeeCarOrder searchedEmployeeCarOrder = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployeeCarOrder
                    = conn.prepareStatement(
                            "SELECT * FROM employee_car_order WHERE car_order_id = ?");
            newEmployeeCarOrder.setLong(1, carOrderId);
            ResultSet searchResults = newEmployeeCarOrder.executeQuery();

            if (searchResults.next()) {
                searchedEmployeeCarOrder = new EmployeeCarOrder();
                searchedEmployeeCarOrder.setCarOrderId(searchResults.getLong("car_order_id"));
                searchedEmployeeCarOrder.setEmployeeId(searchResults.getLong("employee_id"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
        return searchedEmployeeCarOrder;
    }

    @Override
    public EmployeeCarOrder findEmployeeCarOrderByEmployeeId(long employeeId) {
        EmployeeCarOrder searchedEmployeeCarOrder = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployeeCarOrder
                    = conn.prepareStatement(
                            "SELECT * FROM employee_car_order WHERE employee_id = ?");
            newEmployeeCarOrder.setLong(1, employeeId);
            ResultSet searchResults = newEmployeeCarOrder.executeQuery();

            if (searchResults.next()) {
                searchedEmployeeCarOrder = new EmployeeCarOrder();
                searchedEmployeeCarOrder.setCarOrderId(searchResults.getLong("car_order_id"));
                searchedEmployeeCarOrder.setEmployeeId(searchResults.getLong("employee_id"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
        return searchedEmployeeCarOrder;
    }

    @Override
    public List<EmployeeCarOrder> getEmployeeCarOrderList() {
        return getEmployeeCarOrderList(0, -1);
    }

    @Override
    public List<EmployeeCarOrder> getEmployeeCarOrderList(int limit) {
        return getEmployeeCarOrderList(0, limit);
    }

    @Override
    public List<EmployeeCarOrder> getEmployeeCarOrderList(int offset, int limit) {
        List<EmployeeCarOrder> results = new ArrayList<>();
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployeeCarOrder
                    = conn.prepareStatement("SELECT * FROM employee_car_order LIMIT ? OFFSET ?");
            newEmployeeCarOrder.setInt(1, limit);
            newEmployeeCarOrder.setInt(2, offset);
            ResultSet searchResults = newEmployeeCarOrder.executeQuery();
            while (searchResults.next()) {
                EmployeeCarOrder searchedEmployeeCarOrder = new EmployeeCarOrder();
                searchedEmployeeCarOrder.setCarOrderId(searchResults.getLong("car_order_id"));
                searchedEmployeeCarOrder.setEmployeeId(searchResults.getLong("employee_id"));
                results.add(searchedEmployeeCarOrder);
            }
            searchResults.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
        return results;
    }

}
