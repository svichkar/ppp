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
			LOGGER.entry();
			cSave.save("test", "F:\\test.txt");
			LOGGER.debug("Coming to exception point.");
			//cSave.save("", "D:\\test.txt");
			cSave.save("test3", "D:\\test.txt");
			LOGGER.exit("Flow tracing.");
		} catch (CustomException ex) {
			LOGGER.error("We catched our custom exception in 'main' method.", ex);
		}
	}
}
