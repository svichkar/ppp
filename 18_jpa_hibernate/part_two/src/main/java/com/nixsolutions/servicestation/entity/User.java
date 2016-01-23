package com.nixsolutions.servicestation.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rybkinrolla on 13.01.2016.
 */
@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "login", length = 100, nullable = false)
    private String login;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        if (userId.equals(user.userId) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                role.equals(user.role)) {
            return true;
        } else {
            return false;
        }
    }
}
