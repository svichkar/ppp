package com.nixsolutions.servicestation.dao.impl;

import com.nixsolutions.servicestation.dao.ClientDAO;
import com.nixsolutions.servicestation.entity.Client;
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
public class ImplClientDAO implements ClientDAO {
    public static Logger LOGGER = LogManager.getLogger(ImplClientDAO.class.getName());
    private PreparedStatement pStatement;
    private PreparedStatement pStatement2;

    @Override
    public boolean create(Client entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("INSERT INTO client " +
                     "(firs_name, last_name) VALUES (?, ?);")) {
            pStatement.setString(1, entity.getFirstName());
            pStatement.setString(2, entity.getLastName());
            LOGGER.trace("Row in client was created");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean update(Client entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("UPDATE client " +
                     "SET first_name=?, last_name=? WHERE client_id=?;")) {
            pStatement.setString(1, entity.getFirstName());
            pStatement.setString(2, entity.getLastName());
            pStatement.setInt(3, entity.getClientId());
            LOGGER.trace("Row in client with id = " + entity.getClientId() + " was updated");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public boolean delete(Client entity) {
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("DELETE FROM client WHERE client_id=?;")) {
            pStatement.setInt(1, entity.getClientId());
            LOGGER.trace("Row in car_type with id = " + entity.getClientId() + " was deleted");
            return pStatement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    public Client findById(Integer id) {
        Client client = null;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM client WHERE client_id=?;")) {
            pStatement.setInt(1, id);
            ResultSet rSet = pStatement.executeQuery();
            client = new Client();
            rSet.next();
            client.setClientId(rSet.getInt("client_id"));
            client.setFirstName(rSet.getString("first_name"));
            client.setLastName(rSet.getString("last_name"));
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        if (client != null) {
            LOGGER.trace("Row in client with id = " + client.getClientId() + " was found");
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        int i = 0;
        try (Connection connection = CustomConnectionManager.getConnection();
             PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM client;")) {
            ResultSet rSet = pStatement.executeQuery();
            while (rSet.next()) {
                Client client = new Client();
                client.setClientId(rSet.getInt("client_id"));
                client.setFirstName(rSet.getString("first_name"));
                client.setLastName(rSet.getString("last_name"));
                clients.add(client);
                i++;
            }
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
        }
        LOGGER.trace(i + " rows in client were found");
        return clients;
    }

    public void setClientToUpperCase(Client entity) throws SQLException {
        Connection connection2 = null;
        try {
            connection2 = CustomConnectionManager.getConnection();
            connection2.setAutoCommit(false);
            pStatement = connection2.prepareStatement("UPDATE client SET first_name=? WHERE client_id=?;");
            pStatement.setString(1, entity.getFirstName().toUpperCase());
            pStatement.execute();
            pStatement2 = connection2.prepareStatement("UPDATE client SET last_name=? WHERE client_id=?;");
            pStatement2.setString(1, entity.getLastName().toUpperCase());
            pStatement2.execute();
            connection2.commit();
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
            connection2.rollback();
        } finally {
            if (pStatement != null) {
                pStatement.close();
            }
            if (pStatement2 != null) {
                pStatement2.close();
            }
            if (connection2 != null) {
                try {
                    connection2.close();
                } catch (SQLException e) {
                    LOGGER.error(e);
                }
            }
        }
    }
}
