/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.entities;

import java.io.Serializable;

/**
 *
 * @author mednorcom
 */
public class WebRole implements Serializable{

    private Short webRoleId;
    private String webRoleName;

    public WebRole() {
    }

    public WebRole(Short webRoleId, String webRoleName) {
        this.webRoleId = webRoleId;
        this.webRoleName = webRoleName;
    }

    public Short getWebRoleId() {
        return webRoleId;
    }

    public void setWebRoleId(Short webRoleId) {
        this.webRoleId = webRoleId;
    }

    public String getWebRoleName() {
        return webRoleName;
    }

    public void setWebRoleName(String webRoleName) {
        this.webRoleName = webRoleName;
    }

}
