package com.nixsolutions;

public class ExceptionProcessing {

	public static void main(String[] args) {
		CustomSave cSave = new CustomSave();
		cSave.save("test", "D:\\test.txt");
		cSave.save("test3", "D:\\test.txt");
		try {
			try {
				throw new Exception("test Exception");
			} catch (Exception ex) {
				throw new CustomException(ex.getMessage());
			}
		} catch (CustomException ex) {
			ex.printStackTrace();
		}
	}
}
