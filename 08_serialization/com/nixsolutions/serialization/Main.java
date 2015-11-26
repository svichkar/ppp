package com.nixsolutions.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {

		Accounts account = new Accounts(1l, "Vasya", "Student");
		String path = System.getProperty("user.dir") + "\\account.dat";
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(account);

			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} 
		
	}

}
