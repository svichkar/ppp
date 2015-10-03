package com.nixsolutions;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Start {

    private final static String FILE_PATH = "D:\\serialized.dat";

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	Account acc = new Account(256123L, "Bob", "Admin");

	try {
	    byte[] serialized = acc.serialize(acc.toString());
	    byte [] encodedBytes = Base64.getEncoder().encode(serialized);
	    FileOutputStream fos = new FileOutputStream(FILE_PATH);
	    fos.write(encodedBytes);
	    fos.close();
	    System.out.println("Serialized and uploaded in dat file");
	    byte[] a = Files.readAllBytes(Paths.get(FILE_PATH));
	    byte[] decodedBytes = Base64.getDecoder().decode(a);
	    System.out.println("Read bytes from file - Done");
	    ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(decodedBytes));
	    try {
		String o = (String) ois.readObject();

		System.out.println(
			"ID = " + o.split("\t")[0] + "\nName = " + o.split("\t")[1] + "\nRole = " + o.split("\t")[2]);
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    finally {
		fos.close();
		ois.close();
	    }
	    

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}
