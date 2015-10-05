package com.nixsolutions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Lab8 {

	public static void main(String[] args) {

		PersonalAccount account = new PersonalAccount(123456L, "John Doe", "customer");
		PersonalAccount accountNew = null;
		System.out.println("Original account: " + account.toString());

		File file = new File(System.getProperty("user.dir") + "\\PersonalAccount.dat");
		System.out.println("File path: " + System.getProperty("user.dir") + "\\PersonalAccount.dat");
		ObjectOutputStream objectOutput = null;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			objectOutput = new ObjectOutputStream(fos);
			objectOutput.writeObject(account);
			objectOutput.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (objectOutput != null) {
				try {
					objectOutput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		ObjectInputStream objectInput = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			objectInput = new ObjectInputStream(fis);
			accountNew = (PersonalAccount) objectInput.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (objectInput != null) {
				try {
					objectInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Deserialized account: " + accountNew.toString());
	}

}
