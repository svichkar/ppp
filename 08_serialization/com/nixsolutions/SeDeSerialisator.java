package com.nixsolutions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class SeDeSerialisator {

	public static void main(String[] args) {
		ToBeSerialized serializTest = new ToBeSerialized();
		serializTest.setParam1("serializ 1");

		try {
			ToBeSerialized check = deSerealize(serialize(serializTest));
			System.out.println(check.getParam1());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * the method receives an object, serialize it and GZIP it, result is
	 * converted in to byte array
	 * 
	 * @param obj
	 *            an object which will be serialized
	 * @param <T>
	 * @return an array of GZIPPED object
	 * @throws IOException
	 */
	public static <T> byte[] serialize(T obj) throws IOException {

		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		GZIPOutputStream zipOut = new GZIPOutputStream(byteOut);
		ObjectOutputStream os = new ObjectOutputStream(zipOut);

		os.writeObject(obj);
		os.close();

		byte[] hs = byteOut.toByteArray();

		return hs;
	}

	/**
	 * the method receives an array which is a GZIPPED serialized object,
	 * unarchive and deserialize it
	 * 
	 * @param path
	 *            an array of the GZIPPED serialized object
	 * @param <T>
	 * @return deserialized object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> T deSerealize(byte[] path)
			throws IOException, ClassNotFoundException {

		ByteArrayInputStream byteIn = new ByteArrayInputStream(path);
		GZIPInputStream zipIn = new GZIPInputStream(byteIn);
		ObjectInputStream os = new ObjectInputStream(zipIn);

		T iAmBack = (T) os.readObject();
		os.close();

		return iAmBack;
	}
}
