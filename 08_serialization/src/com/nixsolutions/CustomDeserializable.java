package com.nixsolutions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * The Class CustomDeserializable.
 */
public class CustomDeserializable {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		/**
		 * Deserialization
		 */
		try {
			System.out.println("Process of deserialization is start...");
			FileInputStream file = new FileInputStream("lab8.dat");
			ObjectInputStream ois = new ObjectInputStream(file);
			Account accountDeSer = (Account) ois.readObject();
			ois.close();
			file.close();
			System.out.println(
					"Process of deserialization was finished. Results of deserialization are: " + accountDeSer);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
