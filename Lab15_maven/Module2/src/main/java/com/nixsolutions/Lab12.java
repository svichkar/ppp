package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lab12 {
	private static final Logger LOG = LogManager.getLogger("SaveMain");

	public static void main(String[] args) {
		LOG.entry();
		String currentdir = System.getProperty("user.dir");
		String fullPath = currentdir + "\\Files\\text.txt";
		LOG.info("Path to file: {}", fullPath);
		SaveException saveWithCustomException = new SaveException();
		try {
			saveWithCustomException.save("Some text", fullPath);
		} catch (WriteException ex) {
			LOG.error("File saved incorect. Contact support.", ex);
			System.exit(1);
		}
		try {
			saveWithCustomException.save("Some another text", fullPath);
		} catch (WriteException ex) {
			LOG.error("File saved incorect. Contact support.", ex);
			System.exit(1);
		}
		LOG.exit();
	}
}
