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
import nix.jdbcworkshop.entities.WebUser;
import nix.jdbcworkshop.utils.ConnectionManagerH2;
import org.apache.commons.configuration.Configuration;
import org.apache.logging.log4j.LogManager;
import nix.jdbcworkshop.dao.WebUserDao;

/**
 *
 * @author mednorcom
 */
public class WebUserDaoH2 implements WebUserDao {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
    private Configuration jdbcConfig;

    public WebUserDaoH2() {
    }

    @Override
    public void create(WebUser webUser) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newWebUser = conn.prepareStatement(
                    "INSERT INTO web_user (web_user_login, web_user_password, web_role_id) "
                    + "VALUES (?,?,?)");
            newWebUser.setString(1, webUser.getWebUserLogin());
            newWebUser.setString(2, webUser.getWebUserPassword());
            newWebUser.setShort(3, webUser.getWebRoleId());
            newWebUser.executeUpdate();
            ResultSet counters = newWebUser.getGeneratedKeys();
            if (counters.next()) {
                webUser.setWebUserId(counters.getLong(1));
            }
            counters.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException (ex);
        }
    }

    @Override
    public void update(WebUser webUser) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newWebUser = conn.prepareStatement(
                    "UPDATE web_user SET web_user_login = ?, web_user_password = ?, web_role_id = ? "
                    + "WHERE web_user_id = ?");
            newWebUser.setString(1, webUser.getWebUserLogin());
            newWebUser.setString(2, webUser.getWebUserPassword());
            newWebUser.setShort(3, webUser.getWebRoleId());
            newWebUser.setLong(4, webUser.getWebUserId());
            newWebUser.executeUpdate();
            newWebUser.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException (ex);
        }
    }

    @Override
    public void delete(WebUser webUser) {
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newWebUser = conn.prepareStatement(
                    "DELETE FROM web_user WHERE web_user_id = ?");
            newWebUser.setLong(1, webUser.getWebUserId());
            newWebUser.executeUpdate();
            newWebUser.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException (ex);
        }
    }

    @Override
    public WebUser findWebUserById(long webUserId) {
        WebUser searchedWebUser = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newWebUser
                    = conn.prepareStatement("SELECT * FROM web_user WHERE web_user_id = ?");
            newWebUser.setLong(1, webUserId);
            ResultSet searchResults = newWebUser.executeQuery();

            if (searchResults.next()) {
                searchedWebUser = new WebUser();
                searchedWebUser.setWebUserId(searchResults.getLong("web_user_id"));
                searchedWebUser.setWebUserLogin(searchResults.getString("web_user_login"));
                searchedWebUser.setWebUserPassword(searchResults.getString("web_user_password"));
                searchedWebUser.setWebRoleId(searchResults.getShort("web_role_id"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException (ex);
        }
        return searchedWebUser;
    }

    @Override
    public WebUser findWebUserByLogin(String webUserName) {
        WebUser searchedWebUser = null;
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newWebUser
                    = conn.prepareStatement("SELECT * FROM web_user WHERE web_user_login = ?");
            newWebUser.setString(1, webUserName);
            ResultSet searchResults = newWebUser.executeQuery();

            if (searchResults.next()) {
                searchedWebUser = new WebUser();
                searchedWebUser.setWebUserId(searchResults.getLong("web_user_id"));
                searchedWebUser.setWebUserLogin(searchResults.getString("web_user_login"));
                searchedWebUser.setWebUserPassword(searchResults.getString("web_user_password"));
                searchedWebUser.setWebRoleId(searchResults.getShort("web_role_id"));
            } else {
                throw new SQLException("No results found");
            }
            searchResults.close();

        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException(ex);
        }
        return searchedWebUser;
    }

    @Override
    public List<WebUser> getWebUserList() {
        return getWebUserList(0, -1);
    }

    @Override
    public List<WebUser> getWebUserList(int limit) {
        return getWebUserList(0, limit);
    }

    @Override
    public List<WebUser> getWebUserList(int offset, int limit) {
        List<WebUser> results = new ArrayList<>();
        try (Connection conn
                = ConnectionManagerH2.getConnection()) {
            PreparedStatement newWebUser
                    = conn.prepareStatement("SELECT * FROM web_user LIMIT ? OFFSET ?");
            newWebUser.setInt(1, limit);
            newWebUser.setInt(2, offset);
            ResultSet searchResults = newWebUser.executeQuery();
            while (searchResults.next()) {
                WebUser searchedWebUser = new WebUser();
                searchedWebUser.setWebUserId(searchResults.getLong("web_user_id"));
                searchedWebUser.setWebUserLogin(searchResults.getString("web_user_login"));
                searchedWebUser.setWebUserPassword(searchResults.getString("web_user_password"));
                searchedWebUser.setWebRoleId(searchResults.getShort("web_role_id"));
                results.add(searchedWebUser);
            }
            searchResults.close();
        } catch (SQLException | RuntimeException ex) {
            LOGGER.error(ex);
            throw new RuntimeException (ex);
        }
        return results;
    }

}
