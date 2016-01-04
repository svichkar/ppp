package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.EmployeeDAO;
import com.nixsolutions.servicestation.entity.Employee;
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
public class ImplEmployeeDAO implements EmployeeDAO{
    public static Logger LOGGER = LogManager.getLogger(ImplCarOrderStatusDAO.class.getName());

    @Override
    public boolean create(Employee entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO employee " +
                     "(first_name, last_name,  employee_category_id) VALUES (?, ? ,?);")) {
            pStatement.setString(1, entity.getFirstName());
            pStatement.setString(2, entity.getLastName());
            pStatement.setInt(3, entity.getEmployeeCategoryId());
            LOGGER.trace("Row in employee was created");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean update(Employee entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE employee " +
                     "SET first_name=?, last_name=?, employee_category_id=? WHERE employee_id=?;")) {
            pStatement.setString(1, entity.getFirstName());
            pStatement.setString(2, entity.getLastName());
            pStatement.setInt(3, entity.getEmployeeCategoryId());
            pStatement.setInt(4,entity.getEmployeeId());
            LOGGER.trace("Row in employee with id = " + entity.getEmployeeId() + " was updated");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(Employee entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM employee " +
                     "WHERE employee_id=?;")) {
            pStatement.setInt(1, entity.getEmployeeId());
            LOGGER.trace("Row in employee with id = " + entity.getEmployeeId() + " was deleted");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public Employee findById(Integer id) {
        Employee employee = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM employee " +
                     "WHERE employee_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            employee = new Employee();
            rSet.next();
            employee.setEmployeeCategoryId(rSet.getInt("employee_id"));
            employee.setFirstName(rSet.getString("first_name"));
            employee.setLastName(rSet.getString("last_name"));
            employee.setEmployeeCategoryId(rSet.getInt("employee_category_id"));
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        if (employee != null) {
            LOGGER.trace("Row in employee with id = " + employee.getEmployeeId() + " was found");
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        int i = 0;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM employee;")) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeCategoryId(rSet.getInt("employee_id"));
                employee.setFirstName(rSet.getString("first_name"));
                employee.setLastName(rSet.getString("last_name"));
                employee.setEmployeeCategoryId(rSet.getInt("employee_category_id"));
                employees.add(employee);
                i++;
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in employee_category were found");
        return employees;
    }
}
