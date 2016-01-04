package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.EmployeeCategoryDAO;
import com.nixsolutions.servicestation.entity.EmployeeCategory;
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
public class ImplEmployeeCategoryDAO implements EmployeeCategoryDAO{
    public static Logger LOGGER = LogManager.getLogger(ImplCarOrderStatusDAO.class.getName());

    @Override
    public boolean create(EmployeeCategory entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO employee_category (name) VALUES (?);")) {
            pStatement.setString(1, entity.getName());
            LOGGER.trace("Row in employee_category was created");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean update(EmployeeCategory entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE employee_category " +
                     "SET name=? WHERE employee_category_id=?;")) {
            pStatement.setString(1, entity.getName());
            pStatement.setInt(2, entity.getEmployeeCategoryId());
            LOGGER.trace("Row in employee_category with id = " + entity.getEmployeeCategoryId() + " was updated");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(EmployeeCategory entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM employee_category " +
                     "WHERE employee_category_id=?;")) {
            pStatement.setInt(1, entity.getEmployeeCategoryId());
            LOGGER.trace("Row in employee_category with id = " + entity.getEmployeeCategoryId() + " was deleted");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public EmployeeCategory findById(Integer id) {
        EmployeeCategory employeeCategory = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM employee_category " +
                     "WHERE employee_category_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            employeeCategory = new EmployeeCategory();
            rSet.next();
            employeeCategory.setEmployeeCategoryId(rSet.getInt("employee_category_id"));
            employeeCategory.setName(rSet.getString("name"));
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        if (employeeCategory != null) {
            LOGGER.trace("Row in employee_category with id = " + employeeCategory.getEmployeeCategoryId() + " was found");
        }
        return employeeCategory;
    }

    @Override
    public List<EmployeeCategory> findAll() {
        List<EmployeeCategory> employeeCategories = new ArrayList<>();
        int i = 0;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM employee_category;")) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                EmployeeCategory employeeCategory = new EmployeeCategory();
                employeeCategory.setEmployeeCategoryId(rSet.getInt("employee_category_id"));
                employeeCategory.setName(rSet.getString("name"));
                employeeCategories.add(employeeCategory);
                i++;
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in employee_category were found");
        return employeeCategories;
    }
}