package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.Save;

public class SaveImpl implements Save {

	static final Logger LOG = LogManager.getLogger(SaveImpl.class.getName());

	@Override
	public void save(String text, String path) {
		LOG.entry(text, path);
		File file = new File(path);
		if (!file.exists()) {
			try (FileWriter fileWriter = new FileWriter(file)) {
				LOG.debug("File \'{}\' is created.", file.getAbsoluteFile());
				fileWriter.write(text);
				LOG.debug("Text \'{}\' is written to the file.", text);
			} catch (IOException ex) {
				LOG.error("Exception was caught while file processing.", ex);
			}
		} else {
			throw LOG.throwing(new FileAlreadyExistsException("File with following path already exists: " + path));
		}
		LOG.exit();
	}
}
