package com.nixsolutionslog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutionslog.CustomException;

public class CustomSave implements exception.Save {
	private static final Logger LOGGER = LogManager.getLogger("GlobalLogger");
	
	@Override
	public void save(String text, String path) {
		LOGGER.warn("Checking arguments.");
		if (text.length()==0) {
			throw LOGGER.throwing(new CustomException());
		}
		if (path.length()==0) {
			throw LOGGER.throwing(new CustomException());
		}
		LOGGER.info("Working with files.");
		File f = new File(path);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException ex) {
				LOGGER.error("File is not created.", ex);
			}
		}
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
			fOut.write(text.getBytes());
			fOut.flush();
		} catch (IOException ex) {
			LOGGER.error("Stream to file is not created.", ex);
			throw new CustomException(ex);
		} finally {
			try {
				if (fOut != null) {
					fOut.close();
				}
			} catch (Exception ex) {
				LOGGER.warn("Stream from file is not closed.", ex);
			}
		}
	}
}
