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
import nix.jdbcworkshop.entities.WebRole;
import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import nix.jdbcworkshop.dao.WebRoleDao;

/**
 *
 * @author mednorcom
 */
public class WebRoleDaoH2 implements WebRoleDao {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private Configuration jdbcConfig;

    public WebRoleDaoH2() {
    }

    @Override
    public void create(WebRole webRole) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newWebRole = conn.prepareStatement(
                    "INSERT INTO web_role (web_role_name) VALUES (?)");
            newWebRole.setString(1, webRole.getWebRoleName());
            newWebRole.executeUpdate();
            ResultSet counters = newWebRole.getGeneratedKeys();
            if (counters.next()) {
                webRole.setWebRoleId(counters.getShort(1));
            }
            counters.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void update(WebRole webRole) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newWebRole = conn.prepareStatement(
                    "UPDATE web_role SET web_role_name = ? WHERE web_role_id = ?");
            newWebRole.setString(1, webRole.getWebRoleName());
            newWebRole.setShort(2, webRole.getWebRoleId());
            newWebRole.executeUpdate();
            newWebRole.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public void delete(WebRole webRole) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newWebRole = conn.prepareStatement(
                    "DELETE FROM web_role WHERE web_role_id = ?");
            newWebRole.setShort(1, webRole.getWebRoleId());
            newWebRole.executeUpdate();
            newWebRole.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
    }

    @Override
    public WebRole findWebRoleById(short webRoleId) {
        WebRole searchedWebRole = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newWebRole
                    = conn.prepareStatement(
                            "SELECT * FROM web_role WHERE web_role_id = ?");
            newWebRole.setShort(1, webRoleId);
            ResultSet searchResults = newWebRole.executeQuery();

            if (searchResults.next()) {
                searchedWebRole = new WebRole();
                searchedWebRole.setWebRoleId(searchResults.getShort(
                        "web_role_id"));
                searchedWebRole.setWebRoleName(searchResults.getString("web_role_name"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return searchedWebRole;
    }

    @Override
    public List<WebRole> getWebRoleList() {
        return getWebRoleList(0, -1);
    }

    @Override
    public List<WebRole> getWebRoleList(int limit) {
        return getWebRoleList(0, limit);
    }

    @Override
    public List<WebRole> getWebRoleList(int offset, int limit) {
        List<WebRole> results = new ArrayList<>();
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newWebRole
                    = conn.prepareStatement("SELECT * FROM web_role LIMIT ? OFFSET ?");
            newWebRole.setInt(1, limit);
            newWebRole.setInt(2, offset);
            ResultSet searchResults = newWebRole.executeQuery();
            while (searchResults.next()) {
                WebRole searchedWebRole = new WebRole();
                searchedWebRole.setWebRoleId(searchResults.getShort(
                        "web_role_id"));
                searchedWebRole.setWebRoleName(searchResults.getString("web_role_name"));
                results.add(searchedWebRole);
            }
            searchResults.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
        }
        return results;
    }

}
