package com.nixsolutions.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

public class Accounts implements Externalizable {
	transient Long id;
	transient String name, role;
	String strOut;

	public Accounts(Long id, String name, String role) {
		this.id = id;
		this.role = role;
		this.name = name;
		this.strOut = id + ";" + role + ";" + name + ";";
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub

	}

}
