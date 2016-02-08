package com.nixsolutions;

import java.io.Serializable;

/**
 * Created by PAVALVL on 2/8/2016.
 */
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private String role;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
