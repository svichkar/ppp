package com.nixsolutions.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {

		Accounts account = new Accounts(1l, "Vasya", "Student_Dvoe4nik");
		Accounts deserializedAccount;
		String path = System.getProperty("user.dir") + "\\account.dat";

		System.out.println("Before serialization: " + account.toString());
		System.out.println("ID = " + account.id.toString());
		System.out.println("Role = " + account.role);
		System.out.println("Name = " + account.name);

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
			oos.writeObject(account);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
			deserializedAccount = (Accounts) ois.readObject();
			System.out.println("After serialization: " + deserializedAccount.toString());
			System.out.println("ID = " + deserializedAccount.id.toString());
			System.out.println("Role = " + deserializedAccount.role);
			System.out.println("Name = " + deserializedAccount.name);
			ois.close();
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}

	}
}
