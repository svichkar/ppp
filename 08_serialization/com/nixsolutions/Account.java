package com.nixsolutions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	private void writeObject(ObjectOutputStream stream)
			throws IOException {
		stream.writeLong(id);
		stream.writeObject(name);
		stream.writeObject(role);
	}
	
	private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        name = (String) stream.readObject();
        id = stream.readLong();
        role = (String) stream.readObject();
    }
}
