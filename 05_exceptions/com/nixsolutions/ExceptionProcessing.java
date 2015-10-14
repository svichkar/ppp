package com.nixsolutions;

import com.nixsolutions.CustomException;
import com.nixsolutions.CustomSave;

public class ExceptionProcessing {

	public static void main(String[] args) {
		CustomSave cSave = new CustomSave();
		try {
			cSave.save("test", "D:\\test.txt");
			cSave.save("", "D:\\test.txt");
			cSave.save("test3", "D:\\test.txt");
		} catch (CustomException ex) {
			System.out.println("We catched our custom exception in 'main' method.");
		}
	}
}
