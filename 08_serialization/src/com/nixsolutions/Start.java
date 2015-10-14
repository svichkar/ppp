package com.nixsolutions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Start {

    private final static String FILE_PATH = "D:\\serialized.dat";

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	Account acc = new Account(256123L, "Bob", "Admin");
	FileOutputStream fos = null;
	try {
	    byte[] serialized = serialize(acc.toString());
	    byte[] encodedBytes = Base64.getEncoder().encode(serialized);
	    fos = new FileOutputStream(FILE_PATH);
	    fos.write(encodedBytes);
	    fos.close();
	    System.out.println("Serialized and uploaded in dat file");
	    System.out.println("");
	    String decodedString = deserialize(FILE_PATH);
	    System.out.println("Read bytes from file - Done\n");

	    System.out.println("ID = " + decodedString.split("\t")[0] + "\nName = " + decodedString.split("\t")[1]
		    + "\nRole = " + decodedString.split("\t")[2]);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    throw new RuntimeException(e);
	} catch (IOException e) {
	    throw new RuntimeException(e);

	} finally {
	    try {
		fos.close();
	    } catch (IOException e) {
		throw new RuntimeException(e);
	    }
	}
    }

    public static byte[] serialize(Serializable o) throws IOException {

	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	ObjectOutputStream oos = new ObjectOutputStream(baos);
	oos.writeObject(o);
	oos.close();
	return baos.toByteArray();

    }

    public static String deserialize(String path) throws IOException {
	byte[] a = Files.readAllBytes(Paths.get(path));
	ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(Base64.getDecoder().decode(a)));
	try {
	    String o = (String) ois.readObject();
	    return o;

	} catch (ClassNotFoundException e) {

	    throw new RuntimeException(e);

	} finally {

	    ois.close();
	}

    }

}
