package laba.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {

		String fileName = "Account.dat";

		Account a1 = new Account();
		a1.setId(100);
		a1.setName("miaw");
		a1.setRole("'a1dfkgdfklgj0000000");
		System.out.println(a1.toString());
		try {
			ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(fileName));
			ooStream.writeObject(a1);
			ooStream.flush();
			ooStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Account a2;
		try {
			ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(fileName));
			a2 = (Account) oiStream.readObject();
			oiStream.close();

			System.out.println(a2.getId());
			System.out.println(a2.getName());
			System.out.println(a2.getRole());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
