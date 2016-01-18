package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.ClientDao;
import com.nixsolutions.dao.DaoException;
import com.nixsolutions.dao.H2ConnManager;
import com.nixsolutions.entity.Client;

public class ClientDaoImpl implements ClientDao {
	public static final Logger LOG = LogManager.getLogger();
	
	@Override
	public List<Client> getAllClients() {
		LOG.entry();
		String sql = "SELECT * FROM client;";
		List<Client> clients = new ArrayList<>();
		try (Connection conn = H2ConnManager.getConnection(); Statement statem = conn.createStatement()) {
			ResultSet result = statem.executeQuery(sql);
			while (result.next()) {
				Client client = new Client();
				client.setClientId(result.getLong("client_id"));
				client.setFirstName(result.getString("first_name"));
				client.setSecondName(result.getString("last_name"));
				client.setPhone(result.getString("phone"));
				client.setEmail(result.getString("email"));
				clients.add(client);
			}
			LOG.trace("all the clients were retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get all clients", e));
		}
		return LOG.exit(clients);
	}

	@Override
	public Client getClientById(Long clientId) {
		LOG.entry(clientId);
		String sql = "SELECT * FROM client WHERE client_id = ?;";
		Client client = null;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setLong(1, clientId);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				client = new Client();
				client.setClientId(result.getLong("client_id"));
				client.setFirstName(result.getString("first_name"));
				client.setSecondName(result.getString("last_name"));
				client.setPhone(result.getString("phone"));
				client.setEmail(result.getString("email"));
			}
			LOG.trace("the client was retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get a client by Id", e));
		}
		return LOG.exit(client);
	}

	@Override
	public void createClient(Client client) {
		LOG.entry(client);
		String sql = "INSERT INTO client (first_name, last_name, phone, email) VALUES (?, ?, ?, ?)";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, client.getFirstName());
			statem.setString(2, client.getSecondName());
			statem.setString(3, client.getPhone());
			statem.setString(4, client.getEmail());
			statem.executeUpdate();
			LOG.exit("client was created");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to create an author", e));
		}
	}

	@Override
	public void updateClient(Client client) {
		LOG.entry(client);
		String sql = "UPDATE client SET first_name = ?, last_name = ?, phone=?, email=?  WHERE client_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, client.getFirstName());
			statem.setString(2, client.getSecondName());
			statem.setString(3, client.getPhone());
			statem.setString(4, client.getEmail());
			statem.setLong(5, client.getClientId());
			statem.executeUpdate();
			LOG.exit("client with id: " + client.getClientId() + " was updated");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to update the author", e));
		}
	}

	@Override
	public void deleteClient(Client client) {
		LOG.entry(client);
		String sql = "DELETE FROM client WHERE client_id = ?";
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setLong(1, client.getClientId());
			statem.executeUpdate();
			LOG.exit("client with id: " + client.getClientId() + " was deleted");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to delete the author", e));
		}

	}

	@Override
	public Client getClientByName(String readerName) {
		LOG.entry(readerName);
		String sql = "SELECT * FROM client WHERE first_name = ? OR last_name = ?;";
		Client client = null;
		try (Connection conn = H2ConnManager.getConnection(); PreparedStatement statem = conn.prepareStatement(sql)) {
			statem.setString(1, readerName);
			statem.setString(2, readerName);
			ResultSet result = statem.executeQuery();
			if (result.next()) {
				client = new Client();
				client.setClientId(result.getLong("client_id"));
				client.setFirstName(result.getString("first_name"));
				client.setSecondName(result.getString("last_name"));
				client.setPhone(result.getString("phone"));
				client.setEmail(result.getString("email"));
			}
			LOG.trace("the client was retrieved");
		} catch (SQLException e) {
			LOG.throwing(new DaoException("not able to get a client by Id", e));
		}
		return LOG.exit(client);
	}

}
