package com.nixsolutions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class SerializationMain {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		TransferObject to = new TransferObject(1L, "Test");
		System.out.println("==============\nBefore transfer");
		to.print();
		byte[] byteArray = serializeAndArchive(to);
		TransferObject is = deserializeAndExtract(byteArray);
		System.out.println("==============\nAfter transfer");
		is.print();

	}

	public static <T> byte[] serializeAndArchive(T t) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream zos = new GZIPOutputStream(baos);
		ObjectOutputStream oos = new ObjectOutputStream(zos);
		oos.writeObject(t);
		oos.close();
		byte[] arr = baos.toByteArray();
		return arr;
	}

	public static <T> T deserializeAndExtract(byte[] arr) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bais = new ByteArrayInputStream(arr);
		GZIPInputStream zis = new GZIPInputStream(bais);
		ObjectInputStream ois = new ObjectInputStream(zis);
		T result = (T) ois.readObject();
		ois.close();
		return result;
	}

}
