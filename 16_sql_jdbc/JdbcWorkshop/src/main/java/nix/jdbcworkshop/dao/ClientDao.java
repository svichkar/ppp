/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao;

import java.util.List;
import nix.jdbcworkshop.entities.Client;

/**
 *
 * @author mednorcom
 */
public interface ClientDao {
    public void create(Client client);
    public void update(Client client);
    public void delete(Client client);
    public Client findClientById(long clientId);
    public List<Client> getClientList();
    public List<Client> getClientList(int limit);
    public List<Client> getClientList(int offset, int limit);
}
