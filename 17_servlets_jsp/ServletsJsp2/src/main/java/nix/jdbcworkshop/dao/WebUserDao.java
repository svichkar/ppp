/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao;

import java.util.List;
import nix.jdbcworkshop.entities.WebUser;

/**
 *
 * @author mednorcom
 */
public interface WebUserDao {

    public void create(WebUser webUser);

    public void update(WebUser webUser);

    public void delete(WebUser webUser);

    public WebUser findWebUserById(long webUserId);

    public WebUser findWebUserByLogin(String webUserName);

    public List<WebUser> getWebUserList();

    public List<WebUser> getWebUserList(int limit);

    public List<WebUser> getWebUserList(int offset, int limit);
}
