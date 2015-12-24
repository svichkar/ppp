package com.nixsolutions.library.dao.impl;

import com.nixsolutions.library.app.CustomConnectionManager;
import com.nixsolutions.library.dao.TicketDAO;
import com.nixsolutions.library.entity.Ticket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        Ticket newEntity = null;
        try {
            connection = CustomConnectionManager.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO rent_journal (book_id, client_id, rent_date, expired_date, return_date) VALUES ('" +
                    entity.getBookId() + "', '" + entity.getClientId() + "', '" + entity.getRentDate()+ "', '" +
                    entity.getExpiredDate() + "', '" + entity.getReturnDate() + "');");
            ResultSet keys = statement.getGeneratedKeys();
            keys.next();
            newEntity = new Ticket(keys.getInt(1), entity.getBookId(), entity.getClientId(), entity.getRentDate(), entity.getExpiredDate(), entity.getReturnDate());
            connection.commit();
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
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE rent_journal SET book_id='" + entity.getBookId() + "', client_id='" +
                    entity.getClientId() + "', rent_date='" + entity.getRentDate() + "', expired_date='" +
                    entity.getExpiredDate() + "', return_date='" + entity.getReturnDate() + "' WHERE ticket_id='" +
                    entity.getTicketId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Ticket entity) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM rent_journal WHERE ticket_id='" + entity.getTicketId() + "';");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Ticket findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM rent_journal WHERE ticket_id = '" + id + "';");
            resultSet.last();
            if (resultSet.getRow() == 1) {
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
        try (Connection connection = CustomConnectionManager.getConnection()) {
            Statement statement = connection.createStatement();
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
}
