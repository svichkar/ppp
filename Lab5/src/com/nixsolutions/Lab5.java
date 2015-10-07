package com.nixsolutions;

public class Lab5 {

	public static void main(String[] args) {
		String currentdir = System.getProperty("user.dir");
		String fullPath = currentdir + "\\Files\\text.txt";
		SaveException saveWithCustomException = new SaveException();
		try {
			saveWithCustomException.save("Some text", fullPath);
		} catch (WriteException ex) {
			System.out.println("File saved incorect. Contact support.");
			System.exit(1);
		}
		try {
			saveWithCustomException.save("Some another text", fullPath);
		} catch (WriteException ex) {
			System.out.println("File saved incorect. Contact support.");
			System.exit(1);
		}
	}

}
