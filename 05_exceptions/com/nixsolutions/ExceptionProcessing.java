package com.nixsolutions;

public class ExceptionProcessing {

	public static void main(String[] args) {
		CustomSave cSave = new CustomSave();
		cSave.save("test", "D:\\test.txt");
		cSave.save("", "D:\\test.txt");
		cSave.save("test3", "D:\\test.txt");
	}
}
