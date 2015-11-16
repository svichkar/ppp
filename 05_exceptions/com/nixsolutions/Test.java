package com.nixsolutions;

public class Test {

	public static void main(String[] args) {
		FileSaver save = new FileSaver();
		try {
				save.save("test string to save", "/tests/tsets/tests/test.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
