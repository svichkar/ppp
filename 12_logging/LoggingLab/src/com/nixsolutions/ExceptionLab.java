package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionLab {
	
	static final Logger LOG = LogManager.getLogger(ExceptionLab.class.getName());

	public static void main(String[] args) {
		String text = "Aloha!", path = "D:\\greeting.txt";
		LOG.trace("Entering application.");
		SaveImpl saveFile = new SaveImpl();
		LOG.info("Calling metod save() which creates and writes to a file.");
		saveFile.save(text, path);
		LOG.warn("Calling save() with parameter of existing file should fail.");
		saveFile.save(text, path);
	}
}
