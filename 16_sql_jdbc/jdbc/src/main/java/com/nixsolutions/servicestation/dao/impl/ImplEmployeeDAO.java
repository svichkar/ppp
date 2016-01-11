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
    public static Logger LOGGER = LogManager.getLogger(ImplEmployeeDAO.class.getName());

    @Override
    public void create(Employee entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO employee " +
                     "(first_name, last_name,  employee_category_id) VALUES (?, ?, ?);")) {
            pStatement.setString(1, entity.getFirstName());
            pStatement.setString(2, entity.getLastName());
            pStatement.setInt(3, entity.getEmployeeCategoryId());
            pStatement.execute();
            LOGGER.trace("Row in employee was created");
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void update(Employee entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE employee " +
                     "SET first_name=?, last_name=?, employee_category_id=? WHERE employee_id=?;")) {
            pStatement.setString(1, entity.getFirstName());
            pStatement.setString(2, entity.getLastName());
            pStatement.setInt(3, entity.getEmployeeCategoryId());
            pStatement.setInt(4,entity.getEmployeeId());
            pStatement.execute();
            LOGGER.trace("Row in employee with id = " + entity.getEmployeeId() + " was updated");
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Employee entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM employee " +
                     "WHERE employee_id=?;")) {
            pStatement.setInt(1, entity.getEmployeeId());
            pStatement.execute();
            LOGGER.trace("Row in employee with id = " + entity.getEmployeeId() + " was deleted");
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
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
            if(rSet.next()) {
                employee.setEmployeeCategoryId(rSet.getInt("employee_id"));
                employee.setFirstName(rSet.getString("first_name"));
                employee.setLastName(rSet.getString("last_name"));
                employee.setEmployeeCategoryId(rSet.getInt("employee_category_id"));
            } else {
                employee=null;
            }
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
                employee.setEmployeeId(rSet.getInt("employee_id"));
                employee.setFirstName(rSet.getString("first_name"));
                employee.setLastName(rSet.getString("last_name"));
                employee.setEmployeeCategoryId(rSet.getInt("employee_category_id"));
                employees.add(employee);
                i++;
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in employee were found");
        return employees;
    }
}
