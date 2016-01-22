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
import nix.jdbcworkshop.entities.CarOrderStatus;
import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import nix.jdbcworkshop.dao.CarOrderStatusDao;

/**
 *
 * @author mednorcom
 */
public class CarOrderStatusDaoH2 implements CarOrderStatusDao {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private Configuration jdbcConfig;

    public CarOrderStatusDaoH2() {
    }

    @Override
    public void create(CarOrderStatus carOrderStatus) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCarOrderStatus = conn.prepareStatement(
                    "INSERT INTO car_order_status (name) VALUES (?)");
            newCarOrderStatus.setString(1, carOrderStatus.getName());
            newCarOrderStatus.executeUpdate();
            ResultSet counters = newCarOrderStatus.getGeneratedKeys();
            if (counters.next()) {
                carOrderStatus.setCarOrderStatusId(counters.getShort(1));
            }
            counters.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void update(CarOrderStatus carOrderStatus) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCarOrderStatus = conn.prepareStatement(
                    "UPDATE car_order_status SET name = ? WHERE car_order_status_id = ?");
            newCarOrderStatus.setString(1, carOrderStatus.getName());
            newCarOrderStatus.setShort(2, carOrderStatus.getCarOrderStatusId());
            newCarOrderStatus.executeUpdate();
            newCarOrderStatus.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void delete(CarOrderStatus carOrderStatus) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCarOrderStatus = conn.prepareStatement(
                    "DELETE FROM car_order_status WHERE car_order_status_id = ?");
            newCarOrderStatus.setLong(1, carOrderStatus.getCarOrderStatusId());
            newCarOrderStatus.executeUpdate();
            newCarOrderStatus.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public CarOrderStatus findCarOrderStatusById(long carOrderStatusId) {
        CarOrderStatus searchedCarOrderStatus = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCarOrderStatus
                    = conn.prepareStatement(
                            "SELECT * FROM car_order_status WHERE car_order_status_id = ?");
            newCarOrderStatus.setLong(1, carOrderStatusId);
            ResultSet searchResults = newCarOrderStatus.executeQuery();

            if (searchResults.next()) {
                searchedCarOrderStatus = new CarOrderStatus();
                searchedCarOrderStatus.setCarOrderStatusId(
                        searchResults.getShort("car_order_status_id"));
                searchedCarOrderStatus.setName(searchResults.getString("name"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return searchedCarOrderStatus;
    }

    @Override
    public List<CarOrderStatus> getCarOrderStatusList() {
        return getCarOrderStatusList(0, -1);
    }

    @Override
    public List<CarOrderStatus> getCarOrderStatusList(int limit) {
        return getCarOrderStatusList(0, limit);
    }

    @Override
    public List<CarOrderStatus> getCarOrderStatusList(int offset, int limit) {
        List<CarOrderStatus> results = new ArrayList<>();
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCarOrderStatus
                    = conn.prepareStatement("SELECT * FROM car_order_status LIMIT ? OFFSET ?");
            newCarOrderStatus.setInt(1, limit);
            newCarOrderStatus.setInt(2, offset);
            ResultSet searchResults = newCarOrderStatus.executeQuery();
            while (searchResults.next()) {
                CarOrderStatus searchedCarOrderStatus = new CarOrderStatus();
                searchedCarOrderStatus.setCarOrderStatusId(
                        searchResults.getShort("car_order_status_id"));
                searchedCarOrderStatus.setName(searchResults.getString("name"));
                results.add(searchedCarOrderStatus);
            }
            searchResults.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return results;
    }

}
