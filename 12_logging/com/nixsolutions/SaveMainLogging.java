package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;

public class SaveMainLogging {
	private static final Logger LOG = LogManager.getRootLogger();

	public static void main(String[] args) {
		LOG.entry(args);
		try {
			LOG.trace("Write string to file");
			MySaveLogging s = new MySaveLogging();
			File f = new File("D:/fileForString.txt");
			String textFirst = "My string";
			String textSecond = "New string";
			LOG.debug("File exists: " + f.exists());
			s.save(textFirst, f.getPath());
			LOG.debug("Text \"" + textFirst + "\" is added to file " + f.getPath());
			LOG.debug("File exists: " + f.exists());
			s.save(textSecond, f.getPath());
			LOG.debug("Text \"" + textSecond + "\" is added to file " + f.getPath());
		} catch (RuntimeException e) {
			LOG.error("Exception is thrown: ", e);
			throw new RuntimeException(e);
		} finally {
			LOG.exit();
		}
	}

}
