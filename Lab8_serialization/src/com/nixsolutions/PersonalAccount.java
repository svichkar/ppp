package com.nixsolutions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PersonalAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String role;
	
	public PersonalAccount(long id, String name, String role){
		this.id = id;
	    this.name = name;
	    this.role = role;		
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject((id + ";;" + name + ";;" + role));
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		String[] fields = ((String) in.readObject()).split(";;");
		id = Long.parseLong(fields[0]);
		name = fields[1];
		role = fields[2];
	}
	
	@Override
	public String toString() {
		return String.format("id: %s; name: %s; role: ", id, name, role);
	}
}
