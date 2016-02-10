/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nix.bean;

/**
 *
 * @author mednorcom
 */
public class ClientBean {

    private Long clientId;
    private String firstName;
    private String lastName;
    private WebUserBean webUserBean;

    public ClientBean() {
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public WebUserBean getWebUserBean() {
        return webUserBean;
    }

    public void setWebUserBean(WebUserBean webUserBean) {
        this.webUserBean = webUserBean;
    }

}
