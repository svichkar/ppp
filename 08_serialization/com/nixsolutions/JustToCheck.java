package com.nixsolutions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class JustToCheck {

	public static void main(String[] args) {
		// init my object
		MyPreyClass myO = new MyPreyClass("Zulla");
		myO.setAge(10);
		myO.setDescription("Antelope");
		// /define file
		String file = ".\\temp\\myprey.zip";
		// /try to serialize it
		try {

			if (!Processor.Serialize(myO, file)) {
				System.out.printf("Object %s was not written!", myO.name());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.printf("Exception appears during serialization - %s%n", e.getMessage());
		}

		MyPreyClass myPreyBack = null;
		try {
			myPreyBack = (MyPreyClass) Processor.Deserialze(file);
		} catch (IOException e) {
			System.out.printf("Exception appears during deserialization - %s%n", e.getMessage());
		}
		System.out.println("After processing...");
		
		if (myPreyBack != null)
		{
		System.out.printf("Object has name:%1s age:%2s desc:%3s",
				myPreyBack.name(), myPreyBack.age(), myPreyBack.description());
		}
		else
		{
			System.out.println("Object is still being null!");
		}

	}

}
