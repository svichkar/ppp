package com.nixsolutions.asp.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "user", propOrder = {
    "id",
    "userName",
    "password",
    "role"
})
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;
    @Column(name = "user_name", length = 256, nullable = false)
    @XmlElement(required = true)
    private String userName;
    @Column(name = "password", length = 256, nullable = false)
    private String password;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int userId) {
        this.id = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
