package com.nixsolutions;

import java.io.IOException;

public class SerializeTask {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
	Human person = new Human("John", "Dou", 25);
	byte[] serializedPersonObj = Serializer.serializeAndZip(person);
	Human deserializedPersonObj = (Human) Serializer.deserializeAndUnzip(serializedPersonObj);
	System.out.println(deserializedPersonObj.toString());
    }
}
