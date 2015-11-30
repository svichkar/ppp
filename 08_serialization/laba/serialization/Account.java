package laba.serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Account implements Externalizable {

	private long id;
	private String name;
	private String role;

	
	public String toFileString() {
		return "name=\"" + name + "\"id=\"" + id + "\"role=\"" + role + "\"";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public void readExternal(ObjectInput arg) throws IOException, ClassNotFoundException {
		
		String[] arrOfAccount = ((String) arg.readObject()).split("\"");
		name = arrOfAccount[1];
		id = Long.parseLong(arrOfAccount[3]);
		role = arrOfAccount[5];
	}

	@Override
	public void writeExternal(ObjectOutput arg) throws IOException {
		arg.writeObject(this.toFileString());
	}
}
