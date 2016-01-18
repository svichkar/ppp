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
import nix.jdbcworkshop.entities.EmployeeCategory;
import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import nix.jdbcworkshop.dao.EmployeeCategoryDao;

/**
 *
 * @author mednorcom
 */
public class EmployeeCategoryDaoH2 implements EmployeeCategoryDao {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private Configuration jdbcConfig;

    public EmployeeCategoryDaoH2() {
    }

    @Override
    public void create(EmployeeCategory employeeCategory) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployeeCategory = conn.prepareStatement(
                    "INSERT INTO employee_category (name) VALUES (?)");
            newEmployeeCategory.setString(1, employeeCategory.getName());
            newEmployeeCategory.executeUpdate();
            ResultSet counters = newEmployeeCategory.getGeneratedKeys();
            if (counters.next()) {
                employeeCategory.setEmployeeCategoryId(counters.getShort(1));
            }
            counters.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void update(EmployeeCategory employeeCategory) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployeeCategory = conn.prepareStatement(
                    "UPDATE employee_category SET name = ? WHERE employee_category_id = ?");
            newEmployeeCategory.setString(1, employeeCategory.getName());
            newEmployeeCategory.setShort(2, employeeCategory.getEmployeeCategoryId());
            newEmployeeCategory.executeUpdate();
            newEmployeeCategory.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void delete(EmployeeCategory employeeCategory) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployeeCategory = conn.prepareStatement(
                    "DELETE FROM employee_category WHERE employee_category_id = ?");
            newEmployeeCategory.setLong(1, employeeCategory.getEmployeeCategoryId());
            newEmployeeCategory.executeUpdate();
            newEmployeeCategory.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public EmployeeCategory findEmployeeCategoryById(long employeeCategoryId) {
        EmployeeCategory searchedEmployeeCategory = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployeeCategory
                    = conn.prepareStatement("SELECT * FROM employee_category WHERE employee_category_id = ?");
            newEmployeeCategory.setLong(1, employeeCategoryId);
            ResultSet searchResults = newEmployeeCategory.executeQuery();

            if (searchResults.next()) {
                searchedEmployeeCategory = new EmployeeCategory();
                searchedEmployeeCategory.setEmployeeCategoryId(searchResults.getShort("employee_category_id"));
                searchedEmployeeCategory.setName(searchResults.getString("name"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return searchedEmployeeCategory;
    }

    @Override
    public List<EmployeeCategory> getEmployeeCategoryList() {
        return getEmployeeCategoryList(0, -1);
    }

    @Override
    public List<EmployeeCategory> getEmployeeCategoryList(int limit) {
        return getEmployeeCategoryList(0, limit);
    }

    @Override
    public List<EmployeeCategory> getEmployeeCategoryList(int offset, int limit) {
        List<EmployeeCategory> results = new ArrayList<>();
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployeeCategory
                    = conn.prepareStatement("SELECT * FROM employee_category LIMIT ? OFFSET ?");
            newEmployeeCategory.setInt(1, limit);
            newEmployeeCategory.setInt(2, offset);
            ResultSet searchResults = newEmployeeCategory.executeQuery();
            while (searchResults.next()) {
                EmployeeCategory searchedEmployeeCategory = new EmployeeCategory();
                searchedEmployeeCategory.setEmployeeCategoryId(searchResults.getShort("employee_category_id"));
                searchedEmployeeCategory.setName(searchResults.getString("name"));
                results.add(searchedEmployeeCategory);
            }
            searchResults.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return results;
    }

}
