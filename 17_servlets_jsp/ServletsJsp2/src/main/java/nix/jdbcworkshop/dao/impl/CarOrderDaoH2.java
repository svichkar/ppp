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
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import nix.jdbcworkshop.entities.CarOrder;
import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import nix.jdbcworkshop.dao.CarOrderDao;

/**
 *
 * @author mednorcom
 */
public class CarOrderDaoH2 implements CarOrderDao {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private Configuration jdbcConfig;

    public CarOrderDaoH2() {
    }

    @Override
    public void create(CarOrder carOrder) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCarOrder = conn.prepareStatement(
                    "INSERT INTO car_order (car_id,car_order_status_id,start_date,end_date)"
                    + " VALUES (?,?,?,?)");
            newCarOrder.setLong(1, carOrder.getCarId());
            newCarOrder.setShort(2, carOrder.getCarOrderStatusId());
            newCarOrder.setTimestamp(3, new Timestamp(carOrder.getStartDate().getTime()));
            if (carOrder.getEndDate() != null) {
                newCarOrder.setTimestamp(4, new Timestamp(carOrder.getEndDate().getTime()));
            } else {
                newCarOrder.setNull(4, Types.TIMESTAMP);
            }

            newCarOrder.executeUpdate();
            ResultSet counters = newCarOrder.getGeneratedKeys();
            if (counters.next()) {
                carOrder.setCarOrderId(counters.getLong(1));
            }
            counters.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(CarOrder carOrder) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCarOrder = conn.prepareStatement(
                    "UPDATE car_order SET car_id = ?, car_order_status_id = ?, start_date = ?,"
                    + "end_date = ? WHERE car_order_id = ?");
            newCarOrder.setLong(1, carOrder.getCarId());
            newCarOrder.setShort(2, carOrder.getCarOrderStatusId());
            newCarOrder.setTimestamp(3, new Timestamp(carOrder.getStartDate().getTime()));
            if (carOrder.getEndDate() != null) {
                newCarOrder.setTimestamp(4, new Timestamp(carOrder.getEndDate().getTime()));
            } else {
                newCarOrder.setNull(4, Types.TIMESTAMP);
            }
            newCarOrder.setLong(5, carOrder.getCarOrderId());
            newCarOrder.executeUpdate();
            newCarOrder.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(CarOrder carOrder) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCarOrder = conn.prepareStatement(
                    "DELETE FROM car_order WHERE car_order_id = ?");
            newCarOrder.setLong(1, carOrder.getCarOrderId());
            newCarOrder.executeUpdate();
            newCarOrder.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public CarOrder findCarOrderById(long carOrderId) {
        CarOrder searchedCarOrder = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCarOrder
                    = conn.prepareStatement("SELECT * FROM car_order WHERE car_order_id = ?");
            newCarOrder.setLong(1, carOrderId);
            ResultSet searchResults = newCarOrder.executeQuery();

            if (searchResults.next()) {
                searchedCarOrder = new CarOrder();
                searchedCarOrder.setCarOrderId(searchResults.getLong("car_order_id"));
                searchedCarOrder.setCarId(searchResults.getLong("car_id"));
                searchedCarOrder.setCarOrderStatusId(searchResults.getShort("car_order_status_id"));
                searchedCarOrder.setStartDate(searchResults.getTimestamp("start_date"));
                searchedCarOrder.setEndDate(searchResults.getTimestamp("end_date"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return searchedCarOrder;
    }

    @Override
    public List<CarOrder> getCarOrderList() {
        return getCarOrderList(0, -1);
    }

    @Override
    public List<CarOrder> getCarOrderList(int limit) {
        return getCarOrderList(0, limit);
    }

    @Override
    public List<CarOrder> getCarOrderList(int offset, int limit) {
        List<CarOrder> results = new ArrayList<>();
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newCarOrder
                    = conn.prepareStatement("SELECT * FROM car_order LIMIT ? OFFSET ?");
            newCarOrder.setInt(1, limit);
            newCarOrder.setInt(2, offset);
            ResultSet searchResults = newCarOrder.executeQuery();
            while (searchResults.next()) {
                CarOrder searchedCarOrder = new CarOrder();
                searchedCarOrder.setCarOrderId(searchResults.getLong("car_order_id"));
                searchedCarOrder.setCarId(searchResults.getLong("car_id"));
                searchedCarOrder.setCarOrderStatusId(searchResults.getShort("car_order_status_id"));
                searchedCarOrder.setStartDate(searchResults.getTimestamp("start_date"));
                searchedCarOrder.setEndDate(searchResults.getTimestamp("end_date"));
                results.add(searchedCarOrder);
            }
            searchResults.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return results;
    }

}
