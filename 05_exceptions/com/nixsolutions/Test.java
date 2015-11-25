package com.nixsolutions;

public class Test {

	public static void main(String[] args) {
		FileSaver save = new FileSaver();
		try {
			save.save("test string to save",
					"D:\\workspace\\test_test\\test.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
