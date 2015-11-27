package com.nixsolutions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The Class CustomSerialization.
 */
public class CustomSerialization {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		/**
		 * Serialization
		 */
		try {
			Account account = new Account();
			account.setName("Yuriy");
			account.setRole("Admin");
			account.setID(1L);
			System.out.println("Process of serialization is start...");
			FileOutputStream file = new FileOutputStream("lab8.dat");
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(account);
			oos.flush();
			oos.close();
			file.close();
			System.out.println(
					"Process of serialization was finished. Please see file \"lab8.dat\" in the project directory. Results of serialization are: "
							+ account);
			System.out.println("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
