package com.nixsolutions.library.dao.impl;

import com.nixsolutions.library.app.CustomConnectionManager;
import com.nixsolutions.library.dao.ClientDAO;
import com.nixsolutions.library.entity.Client;
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
public class ClientDaoImpl implements ClientDAO {
    public static Logger LOGGER = LogManager.getLogger(ClientDaoImpl.class.getName());

    @Override
    public Client create(Client entity) {
        Connection connection = null;
        Statement statement = null;
        Client newEntity = null;
        try {
            connection = CustomConnectionManager.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO client (first_name, last_name, phone, email) VALUES ('" +
                    entity.getFirstName() + "', '" + entity.getLastName() + "', '" + entity.getPhone()+ "', '" +
                    entity.getEmail() + "');");
            ResultSet keys = statement.getGeneratedKeys();
            connection.commit();
            keys.next();
            newEntity = this.findByID(keys.getInt(1));
            LOGGER.trace("added line in client table, with id:" + newEntity.getClientId());
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
    public void update(Client entity) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE client SET first_name='" + entity.getFirstName() + "', last_name='" +
                    entity.getLastName() + "', phone='" + entity.getPhone() + "', email='" +
                    entity.getEmail() + "' WHERE client_id='" +
                    entity.getClientId() + "';");
            LOGGER.trace("updated line in client table, with id:" + entity.getClientId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Client entity) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM client WHERE client_id='" + entity.getClientId() + "';");
            LOGGER.trace("deleted line in client table, with id:" + entity.getClientId());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public Client findByID(Integer id) {
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client WHERE client_id = '" + id + "';");
            if (resultSet.next()) {
                Client entity = new Client(resultSet.getInt("client_id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("phone"), resultSet.getString("email"));
                return entity;
            } else {
                LOGGER.trace("id " + id + " not found in client table");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> list = new ArrayList<>();
        try (Connection connection = CustomConnectionManager.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client;");
            while (resultSet.next())
                list.add(new Client (resultSet.getInt("client_id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getString("phone"), resultSet.getString("email")));
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            return null;
        }
    }
}
