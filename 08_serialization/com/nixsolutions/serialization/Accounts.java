package com.nixsolutions.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Accounts implements Externalizable {
	transient Long id;
	transient String name, role;

	// Constructor with default values
	public Accounts() {
		this.id = 0l;
		this.role = "";
		this.name = "";

	}

	// Constructor with definite fields
	public Accounts(Long id, String name, String role) {
		this.id = id;
		this.role = role;
		this.name = name;

	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		String[] arrFromFile = ((String) in.readObject()).toString().split(";");
		id = (long) Integer.parseInt(arrFromFile[0]);
		role = arrFromFile[1];
		name = arrFromFile[2];
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(id + ";" + role + ";" + name + ";");
	}
	
}
