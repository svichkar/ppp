/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.jdbcworkshop.entities;

/**
 *
 * @author mednorcom
 */
public class WebUser {

    private Long webUserId;
    private String webUserLogin;
    private String webUserPassword;
    private Short webRoleId;

    public WebUser() {
    }

    public WebUser(Long webUserId, String webUserLogin, String webUserPassword, Short webRoleId) {
        this.webUserId = webUserId;
        this.webUserLogin = webUserLogin;
        this.webUserPassword = webUserPassword;
        this.webRoleId = webRoleId;
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

    public Short getWebRoleId() {
        return webRoleId;
    }

    public void setWebRoleId(Short webRoleId) {
        this.webRoleId = webRoleId;
    }

}
