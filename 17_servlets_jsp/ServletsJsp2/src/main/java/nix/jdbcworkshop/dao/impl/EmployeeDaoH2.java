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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import nix.jdbcworkshop.entities.Employee;
import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import nix.jdbcworkshop.dao.EmployeeDao;

/**
 *
 * @author mednorcom
 */
public class EmployeeDaoH2 implements EmployeeDao {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private Configuration jdbcConfig;

    public EmployeeDaoH2() {
    }

    @Override
    public void create(Employee employee) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployee = conn.prepareStatement(
                    "INSERT INTO employee (first_name, last_name, employee_category_id, web_user_id)"
                    + "VALUES (?,?,?,?)");
            newEmployee.setString(1, employee.getFirstName());
            newEmployee.setString(2, employee.getLastName());
            newEmployee.setShort(3, employee.getEmployeeCategoryId());
            if (employee.getWebUserId() != null) {
                newEmployee.setLong(4, employee.getWebUserId());
            } else {
                newEmployee.setNull(4, Types.BIGINT);
            }
            newEmployee.executeUpdate();
            ResultSet counters = newEmployee.getGeneratedKeys();
            if (counters.next()) {
                employee.setEmployeeId(counters.getLong(1));
            }
            counters.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(Employee employee) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployee = conn.prepareStatement(
                    "UPDATE employee SET first_name = ?, last_name = ?, employee_category_id = ?, "
                    + "web_user_id = ? WHERE employee_id = ?");
            newEmployee.setString(1, employee.getFirstName());
            newEmployee.setString(2, employee.getLastName());
            newEmployee.setShort(3, employee.getEmployeeCategoryId());
            if (employee.getWebUserId() != null) {
                newEmployee.setLong(4, employee.getWebUserId());
            } else {
                newEmployee.setNull(4, Types.BIGINT);
            }
            newEmployee.setLong(5, employee.getEmployeeId());
            newEmployee.executeUpdate();
            newEmployee.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Employee employee) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployee = conn.prepareStatement(
                    "DELETE FROM employee WHERE employee_id = ?");
            newEmployee.setLong(1, employee.getEmployeeId());
            newEmployee.executeUpdate();
            newEmployee.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Employee findEmployeeById(long employeeId) {
        Employee searchedEmployee = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployee
                    = conn.prepareStatement("SELECT * FROM employee WHERE employee_id = ?");
            newEmployee.setLong(1, employeeId);
            ResultSet searchResults = newEmployee.executeQuery();

            if (searchResults.next()) {
                searchedEmployee = new Employee();
                searchedEmployee.setEmployeeId(searchResults.getLong("employee_id"));
                searchedEmployee.setFirstName(searchResults.getString("first_name"));
                searchedEmployee.setLastName(searchResults.getString("last_name"));
                searchedEmployee.setEmployeeCategoryId(
                        searchResults.getShort("employee_category_id"));
                searchedEmployee.setWebUserId(searchResults.getLong("web_user_id"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
        return searchedEmployee;
    }

    @Override
    public List<Employee> getEmployeeList() {
        return getEmployeeList(0, -1);
    }

    @Override
    public List<Employee> getEmployeeList(int limit) {
        return getEmployeeList(0, limit);
    }

    @Override
    public List<Employee> getEmployeeList(int offset, int limit) {
        List<Employee> results = new ArrayList<>();
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newEmployee
                    = conn.prepareStatement("SELECT * FROM employee LIMIT ? OFFSET ?");
            newEmployee.setInt(1, limit);
            newEmployee.setInt(2, offset);
            ResultSet searchResults = newEmployee.executeQuery();
            while (searchResults.next()) {
                Employee searchedEmployee = new Employee();
                searchedEmployee.setEmployeeId(searchResults.getLong("employee_id"));
                searchedEmployee.setFirstName(searchResults.getString("first_name"));
                searchedEmployee.setLastName(searchResults.getString("last_name"));
                searchedEmployee.setEmployeeCategoryId(
                        searchResults.getShort("employee_category_id"));
                searchedEmployee.setWebUserId(searchResults.getLong("web_user_id"));
                results.add(searchedEmployee);
            }
            searchResults.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
        return results;
    }

}
