package com.nixsolutions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Serializer {
    public static byte[] serializeAndZip(Object inputObj) {
	ByteArrayOutputStream outputArrayByte = new ByteArrayOutputStream();
	GZIPOutputStream gzip;
	ObjectOutputStream oos;
	try {
	    gzip = new GZIPOutputStream(outputArrayByte);
	    oos = new ObjectOutputStream(gzip);
	    oos.writeObject(inputObj);
	    if (oos != null) {
		oos.close();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return outputArrayByte.toByteArray();
    }

    public static Object deserializeAndUnzip(byte[] inputByteArray) {
	ByteArrayInputStream inputArrayByte = new ByteArrayInputStream(inputByteArray);
	GZIPInputStream gunzip;
	ObjectInputStream ois;
	Object asd = null;
	try {
	    gunzip = new GZIPInputStream(inputArrayByte);
	    ois = new ObjectInputStream(gunzip);
	    asd = ois.readObject();
	    if (ois != null) {
		ois.close();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	return asd;
    }
}
