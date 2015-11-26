package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.Save;

public class SaveImplementation implements Save {
	
	private static final Logger LOG = LogManager.getLogger(SaveImplementation.class.getName());

	@Override
	public void save(String str, String path) {
		LOG.entry();
		Writer writer = null;
		File logFile = new File(path);
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				LOG.catching(e);
				throw new MyException("IOException caused: ", e);

			}
		}
		LOG.trace("File with {} path is created", path);
		try {
			writer = new FileWriter(logFile);
			writer.write(str);
			writer.write(System.getProperty("line.separator"));
			LOG.debug("String {} is written", str);
			writer.flush();
			LOG.info("Text is saved to the file {}", path);
		} catch (IOException ex) {
			LOG.catching(ex);
			throw new MyException("IOException caused: ", ex);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					LOG.warn("Writer is not closed");
					LOG.catching(ex);
				}
			}
		}

	}

	public static void main(String[] args) {
		LOG.entry();
		SaveImplementation saveObj = new SaveImplementation();
		LOG.debug(saveObj);
		saveObj.save("Lab12: test test test", "");
	}

}
