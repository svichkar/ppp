package com.nixsolutions.library.bean;

import com.nixsolutions.library.entity.Role;
import com.nixsolutions.library.entity.User;

/**
 * Created by kozlovskij on 1/11/2016.
 */
public class BeanUser {
    private User user;
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
