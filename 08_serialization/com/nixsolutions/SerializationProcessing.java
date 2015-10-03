package com.nixsolutions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationProcessing {

	public static void main(String[] args) {
		Account acc = new Account(12L, "Stew", "Assistant");
		Account acc2;
		try {
			serialize(acc, "F:\\Serialization.dat");
			acc2 = (Account) deserialize("F:\\Serialization.dat");
			System.out.println(acc.hashCode());
			System.out.println(acc2.hashCode());
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fio = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fio);
		Object obj = ois.readObject();
		ois.close();
		return obj;
	}
	
	public static void serialize(Object obj, String fileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj); 
        oos.flush();
        oos.close();
	}
}
