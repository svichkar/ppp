package com.nixsolutions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Processor {

	public static boolean Serialize(Object obj, String zipArchieveName)
			throws IOException {
		boolean status = false;
		if (obj == null) {
			throw new NullPointerException();
		}

		try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
				zipArchieveName, true))) {

			ZipEntry zi = new ZipEntry(String.valueOf(obj.hashCode()));
			zos.putNextEntry(zi);

			ObjectOutputStream oos = new ObjectOutputStream(zos);
			oos.writeObject(obj);
			zos.close();
			oos.close();
			status = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			return status;
		}

	}

	public static Object Deserialze(String zipArchieveName) throws IOException {
		Object someObject = null;
		try (ZipInputStream zis = new ZipInputStream(new FileInputStream(
				zipArchieveName))) {
			ZipEntry entry = (ZipEntry) zis.getNextEntry();
			ObjectInputStream ois = new ObjectInputStream(zis);
		
			someObject = ois.readObject();
			ois.close();
			return someObject;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return someObject;
	}

}
