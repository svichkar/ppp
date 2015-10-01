package com.nixsolutions;

import java.io.ObjectInputStream;
import java.io.Serializable;

public class Account implements Serializable{
	private long id;
	private String name;
	private String role;
	
	static final long serialVersionUID = 10275539472837495L;
	
	public Account(long id, String name, String role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}
	
	@Override
	public String toString() {
		return id + ";" + name + ";" + role;
	}
}
