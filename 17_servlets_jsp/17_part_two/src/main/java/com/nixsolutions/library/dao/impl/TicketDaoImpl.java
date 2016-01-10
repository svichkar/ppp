package com.nixsolutions.library.dao.impl;

import com.nixsolutions.library.app.CustomConnectionManager;
import com.nixsolutions.library.dao.TicketDAO;
import com.nixsolutions.library.entity.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kozlovskij on 12/23/2015.
 */
public class TicketDaoImpl implements TicketDAO {
    public static Logger LOGGER = LogManager.getLogger(TicketDaoImpl.class.getName());

    @Override
    public Ticket create(Ticket entity) {
        Connection connection = null;
        Statement statement = null;
        Ticket newEntity = null;
        try {
            connection = CustomConnectionManager.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO rent_journal (book_id, client_id, rent_date, expired_date, return_date) VALUES ('" +
                    entity.getBookId() + "', '" + entity.getClientId() + "', '" +
                    new Date(entity.getRentDate().getTime()) + "', '" + new Date(entity.getExpiredDate().getTime()) +
                    "', '" + new Date(entity.getReturnDate().getTime()) + "');");
            ResultSet keys = statement.getGeneratedKeys();
            connection.commit();
            keys.next();
            newEntity = this.findByID(keys.getInt(1));
            LOGGER.trace("added line in rent_journal table, with id:" + newEntity.getTicketId());
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            return newEntity;
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(e);
                }
            }
            return newEntity;
        }
    }

    @Override
    public void update(Ticket entity) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE rent_journal SET book_id='" + entity.getBookId() + "', client_id='" +
                    entity.getClientId() + "', rent_date='" + new Date(entity.getRentDate().getTime()) +
                    "', expired_date='" + new Date(entity.getExpiredDate().getTime()) + "', return_date='" +
                    new Date(entity.getReturnDate().getTime()) + "' WHERE ticket_id='" + entity.getTicketId() + "';");
            LOGGER.trace("updated line in rent_journal table, with id:" + entity.getTicketId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Ticket entity) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM rent_journal WHERE ticket_id='" + entity.getTicketId() + "';");
            LOGGER.trace("deleted line in rent_journal table, with id:" + entity.getTicketId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Ticket findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rent_journal WHERE ticket_id = '" + id + "';");
            if (resultSet.next()) {
                Ticket entity = new Ticket(resultSet.getInt("ticket_id"), resultSet.getInt("book_id"),
                        resultSet.getInt("client_id"), resultSet.getDate("rent_date"), resultSet.getDate("expired_date"),
                        resultSet.getDate("return_date"));
                return entity;
            } else {
                LOGGER.trace("id " + id + " not found in rent_journal table");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> list = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rent_journal;");
            while (resultSet.next())
                list.add(new Ticket (resultSet.getInt("ticket_id"), resultSet.getInt("book_id"),
                        resultSet.getInt("client_id"), resultSet.getDate("rent_date"), resultSet.getDate("expired_date"),
                        resultSet.getDate("return_date")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public List<Ticket> findByBookID(Integer id) {
        List<Ticket> list = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rent_journal WHERE book_id = '" + id + "';");
            while (resultSet.next())
                list.add(new Ticket (resultSet.getInt("ticket_id"), resultSet.getInt("book_id"),
                        resultSet.getInt("client_id"), resultSet.getDate("rent_date"), resultSet.getDate("expired_date"),
                        resultSet.getDate("return_date")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
