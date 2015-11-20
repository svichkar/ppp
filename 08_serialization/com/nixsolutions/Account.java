package com.nixsolutions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String role;

	public Account(Long id, String name, String role) {
		this.id = id;
		this.name = name;
		this.role = role;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeLong(id);
		out.writeObject(name);
		out.writeObject(role);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		id = in.readLong();
		name = (String) in.readObject();
		role = (String) in.readObject();

	}

	@Override
	public String toString() {

		return "id: " + id + "; name: " + name + "; role: " + role;
	}

	public static void main(String[] args) {

		Account accountAdmin = new Account(1L, "Test Admin", "Admin");
		Account accountUser = new Account(2L, "Test User", " User");
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("test.dat"));
			System.out.println("Original object is: " + accountAdmin);
			os.writeObject(accountAdmin);
			System.out.println("Original object is: " + accountUser);
			os.writeObject(accountUser);
			os.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("test.dat"));
			Account accountAdmin1 = (Account) is.readObject();
			Account accountUser1 = (Account) is.readObject();
			System.out.println("Deserializable object is: " + accountAdmin1);
			System.out.println("Deserializable object is: " + accountUser1);
			is.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
