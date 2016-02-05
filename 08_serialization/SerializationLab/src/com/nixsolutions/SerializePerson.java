package com.nixsolutions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializePerson {

	public static <E> byte[] serialize(E obj) throws IOException {
		try (ByteArrayOutputStream byteArrStream = new ByteArrayOutputStream();
				ObjectOutputStream objectStream = new ObjectOutputStream(byteArrStream)) {
			objectStream.writeObject(obj);
			return byteArrStream.toByteArray();
		}
	}

	public static <E> E deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		try (ByteArrayInputStream byteArrStream = new ByteArrayInputStream(bytes);
				ObjectInputStream objectStream = new ObjectInputStream(byteArrStream)) {
			return (E) objectStream.readObject();
		}
	}
}
