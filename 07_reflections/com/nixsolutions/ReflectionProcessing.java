package com.nixsolutions;

public class ReflectionProcessing {

	public static void main(String[] args) {
		ReflectionTestingObject tObj = new ReflectionTestingObject("test", 3, 12L);
		try {
			String test1 = (String) ReflectionUtilities.getFieldValue(tObj, "name");
			long test2 = (long) ReflectionUtilities.getFieldValue(tObj, "numT");
			int test3 = (int) ReflectionUtilities.getFieldValue(tObj, "num");
		} catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
