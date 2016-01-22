/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.dao;

import java.util.List;
import nix.jdbcworkshop.entities.WebRole;

/**
 *
 * @author mednorcom
 */
public interface WebRoleDao {

    public void create(WebRole webRole);

    public void update(WebRole webRole);

    public void delete(WebRole webRole);

    public WebRole findWebRoleById(short webRoleId);

    public List<WebRole> getWebRoleList();

    public List<WebRole> getWebRoleList(int limit);

    public List<WebRole> getWebRoleList(int offset, int limit);
}
