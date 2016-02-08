package com.nixsolutions.studentgrade.model;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by svichkar on 12/18/2015.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "first_name", nullable = false, length = 256)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 256)
    private String lastName;

    @Column(name = "login", nullable = false, unique = true, length = 256)
    private String login;

    @Column(name = "password", nullable = false, length = 256)
    private String userPassword;

    @Column(name = "email", nullable = false, unique = true, length = 256)
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    @Cascade(CascadeType.DETACH)
    private Role role;

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!userId.equals(user.userId)) return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!login.equals(user.login)) return false;
        if (!userPassword.equals(user.userPassword)) return false;
        if (!email.equals(user.email)) return false;
        return role.equals(user.role);
    }

    @Override
    public int hashCode() {
        int result = userId.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + userPassword.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }
}
