/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.bean;

import java.io.Serializable;
import nix.jdbcworkshop.entities.WebRole;

/**
 *
 * @author mednorcom
 */
public class WebUserBean implements Serializable {

    private Long webUserId;
    private String webUserLogin;
    private String webUserPassword;
    private WebRole role;

    public WebUserBean() {
    }

    public Long getWebUserId() {
        return webUserId;
    }

    public void setWebUserId(Long webUserId) {
        this.webUserId = webUserId;
    }

    public String getWebUserLogin() {
        return webUserLogin;
    }

    public void setWebUserLogin(String webUserLogin) {
        this.webUserLogin = webUserLogin;
    }

    public String getWebUserPassword() {
        return webUserPassword;
    }

    public void setWebUserPassword(String webUserPassword) {
        this.webUserPassword = webUserPassword;
    }

    public WebRole getRole() {
        return role;
    }

    public void setRole(WebRole role) {
        this.role = role;
    }

}
