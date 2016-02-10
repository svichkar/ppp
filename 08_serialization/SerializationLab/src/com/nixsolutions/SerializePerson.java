package com.nixsolutions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class SerializePerson {

	public static <E> byte[] serialize(E obj) throws IOException {
		try (ByteArrayOutputStream byteArrStream = new ByteArrayOutputStream();
				GZIPOutputStream gzipStream = new GZIPOutputStream(byteArrStream);
				ObjectOutputStream objectStream = new ObjectOutputStream(gzipStream)) {
			objectStream.writeObject(obj);
			objectStream.flush();
			gzipStream.finish();
			return byteArrStream.toByteArray();
		}
	}

	public static <E> E deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream byteArrStream = new ByteArrayInputStream(bytes);
				GZIPInputStream gzipStream = new GZIPInputStream(byteArrStream);
				ObjectInputStream objectStream = new ObjectInputStream(gzipStream)) {
			return (E) objectStream.readObject();
		}
	}

}
