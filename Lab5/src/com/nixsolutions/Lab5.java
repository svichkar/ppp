package com.nixsolutions;

public class Lab5 {

	public static void main(String[] args) {
		String currentdir = System.getProperty("user.dir");
		String fullPath = currentdir + "\\Files\\text.txt";
		SaveException saveWithCustomException = new SaveException();
		saveWithCustomException.save("Some text", fullPath);
		saveWithCustomException.save("Some another text", fullPath);
	}

}
