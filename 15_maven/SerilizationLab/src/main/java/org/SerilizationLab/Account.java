package org.SerilizationLab;

import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    public Account(Long id, String name, String role) {
	this.id = id;
	this.name = name;
	this.role = role;

    }

    private Long id;
    private String name;
    private String role;

    @Override
    public String toString() {
	return id + "\t" + name + "\t" + role;
    }

}
