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
import nix.jdbcworkshop.entities.Client;
import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import nix.jdbcworkshop.dao.ClientDao;

/**
 *
 * @author mednorcom
 */
public class ClientDaoH2 implements ClientDao {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private Configuration jdbcConfig;

    public ClientDaoH2() {
    }

    @Override
    public void create(Client client) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newClient = conn.prepareStatement(
                    "INSERT INTO client (first_name, last_name) VALUES (?,?)");
            newClient.setString(1, client.getFirstName());
            newClient.setString(2, client.getLastName());
            newClient.executeUpdate();
            ResultSet counters = newClient.getGeneratedKeys();
            if (counters.next()) {
                client.setClientId(counters.getLong(1));
            }
            counters.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void update(Client client) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newClient = conn.prepareStatement(
                    "UPDATE client SET first_name = ?, last_name = ? WHERE client_id = ?");
            newClient.setString(1, client.getFirstName());
            newClient.setString(2, client.getLastName());
            newClient.setLong(3, client.getClientId());
            newClient.executeUpdate();
            newClient.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void delete(Client client) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newClient = conn.prepareStatement(
                    "DELETE FROM client WHERE client_id = ?");
            newClient.setLong(1, client.getClientId());
            newClient.executeUpdate();
            newClient.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public Client findClientById(long clientId) {
        Client searchedClient = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newClient
                    = conn.prepareStatement("SELECT * FROM client WHERE client_id = ?");
            newClient.setLong(1, clientId);
            ResultSet searchResults = newClient.executeQuery();

            if (searchResults.next()) {
                searchedClient = new Client();
                searchedClient.setClientId(searchResults.getLong("client_id"));
                searchedClient.setFirstName(searchResults.getString("first_name"));
                searchedClient.setLastName(searchResults.getString("last_name"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return searchedClient;
    }

    @Override
    public List<Client> getClientList() {
        return getClientList(0, -1);
    }

    @Override
    public List<Client> getClientList(int limit) {
        return getClientList(0, limit);
    }

    @Override
    public List<Client> getClientList(int offset, int limit) {
        List<Client> results = new ArrayList<>();
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newClient
                    = conn.prepareStatement("SELECT * FROM client LIMIT ? OFFSET ?");
            newClient.setInt(1, limit);
            newClient.setInt(2, offset);
            ResultSet searchResults = newClient.executeQuery();
            while (searchResults.next()) {
                Client searchedClient = new Client();
                searchedClient.setClientId(searchResults.getLong("client_id"));
                searchedClient.setFirstName(searchResults.getString("first_name"));
                searchedClient.setLastName(searchResults.getString("last_name"));
                results.add(searchedClient);
            }
            searchResults.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return results;
    }

}
