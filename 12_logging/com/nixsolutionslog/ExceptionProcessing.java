package com.nixsolutionslog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.nixsolutionslog.CustomException;
import com.nixsolutionslog.CustomSave;

public class ExceptionProcessing {
	private static final Logger LOGGER = LogManager.getLogger("GlobalLogger");

	public static void main(String[] args) {
		CustomSave cSave = new CustomSave();
		try {
			cSave.save("test", "F:\\test.txt");
			cSave.save("", "D:\\test.txt");
			cSave.save("test3", "D:\\test.txt");
		} catch (CustomException ex) {
			// System.out.println("We catched our custom exception in 'main'
			// method.");
			LOGGER.error("We catched our custom exception in 'main' method.", ex);
		}
	}
}
