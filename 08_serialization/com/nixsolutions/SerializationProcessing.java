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
			serialize(acc, "D:\\AllTestGarbage\\Serialization.dat");
			acc2 = (Account) deserialize("D:\\AllTestGarbage\\Serialization.dat");
			System.out.println(acc.hashCode());
			System.out.println(acc2.hashCode());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Account deserialize(String fileName) throws IOException, ClassNotFoundException {
		FileInputStream fio = new FileInputStream(fileName);
		ObjectInputStream ois = new ObjectInputStream(fio);
		String fields = (String) ois.readObject();
		ois.close();
		String[] fieldsValues = fields.split(";");
		return new Account(Long.parseLong(fieldsValues[0]), fieldsValues[1], fieldsValues[2]);
	}
	
	public static void serialize(Object obj, String fileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj.toString()); 
        fos.close();
	}
}
