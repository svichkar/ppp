package com.nixsolutions;

import java.io.IOException;

public class SerializationLab {

	public static void main(String[] args) {
		try {
			Person personToSerialize = new Person("Jon", "Snow", 19);
			byte[] personByteArr = SerializePerson.serialize(personToSerialize);
			Person personDeserialized = SerializePerson.deserialize(personByteArr);
			System.out.println("Deserialized object: " + personDeserialized.toString());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
