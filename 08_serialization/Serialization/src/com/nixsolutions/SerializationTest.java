/**
 * 
 */
package com.nixsolutions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

/**
 * @author mixeyes
 *
 */
public class SerializationTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TransferClass transferClass = new TransferClass("sahdfsdhgfoaf", 33);
		byte[] byteArray = writeOject(transferClass);
		// TODO Auto-generated method stub

		TransferClass newQwerty = (TransferClass) readObject(byteArray);

	}

	public static byte[] writeOject(Object testObject) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gZipOut = null;
		ObjectOutputStream out = null;
		try {
			gZipOut = new GZIPOutputStream(bos, true);
			out = new ObjectOutputStream(gZipOut);
			out.writeObject(testObject);
			out.flush();
			bos.flush();
			return bos.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		return null;
	}

	public static Object readObject(byte[] yourBytes) {
		ByteArrayInputStream bis = new ByteArrayInputStream(yourBytes);
		GZIPInputStream gZip = null;
		ObjectInputStream in = null;
		try {
			gZip = new GZIPInputStream(bis);
			in = new ObjectInputStream(gZip);
			Object o = in.readObject();
			return o;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
			} catch (IOException ex) {
				// ignore close exception
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		return null;

	}
}
