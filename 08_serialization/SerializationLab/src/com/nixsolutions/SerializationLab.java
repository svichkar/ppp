package com.nixsolutions;

import java.io.IOException;

public class SerializationLab {

	public static void main(String[] args) {
		try {
			Person personToSerialize = new Person("Shouldwritesomenamehere", "Andsomesurnamethere", "Somewhere no one knows",
					"Do not really know what to write", 4000);
			System.out.println("Serialized object: " + personToSerialize.toString());
			byte[] personByteArr = SerializePerson.serialize(personToSerialize);
			Person personDeserialized = SerializePerson.deserialize(personByteArr);
			System.out.println("Deserialized object: " + personDeserialized.toString());
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
