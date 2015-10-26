package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import exception.Save;

public class MyNewestSave implements Save {

	private static final Logger LOG = LogManager.getLogger(MyNewestSave.class);

	public void save(String arg0, String arg1) {
		BufferedWriter bufferW = null;
		FileWriter fw = null;

		try {
			LOG.info("Checking that text was defined");
			if (arg0.length() == 0) {
				throw new MyNewestException("Text has 0 length");
			}

			LOG.info("Checking that file was defined");
			if (arg1.length() == 0) {
				throw new MyNewestException("File is not defined");
			}

			File fileForSavingString = new File(arg1);
			if (!fileForSavingString.exists()) {
				LOG.warn(String
						.format("File %s doesn't exist. It will be created now%n",
								arg1));
				fileForSavingString.createNewFile();
			}
			fw = new FileWriter(fileForSavingString);
			bufferW = new BufferedWriter(fw);
			bufferW.write(arg0);
			bufferW.newLine();
			// push from buffer
			bufferW.flush();
		} catch (IOException ioex) {
			LOG.debug(ioex, ioex);
			throw new MyNewestException(ioex);
		} finally {
			try {
				if (bufferW != null) {
					bufferW.close();
				}
			} catch (IOException e) {
				LOG.debug(e, e);
			}
		}
	}

}
