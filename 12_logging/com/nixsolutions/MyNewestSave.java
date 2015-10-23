package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.core.util.Loader;

import exception.Save;

public class MyNewestSave implements Save {

	public MyNewestSave() {

	}

	public void save(String arg0, String arg1) {
		BufferedWriter bufferW = null;
		FileWriter fw = null;

		try {
			MyLogging.LOG.info("Checking that text was defined");
			if (arg0.length() == 0) {
				throw new MyNewestException("Text has 0 length");
			}

			MyLogging.LOG.info("Checking that file was defined");
			if (arg1.length() == 0) {
				throw new MyNewestException("File is not defined");
			}

			File fileForSavingString = new File(arg1);
			if (!fileForSavingString.exists()) {
				MyLogging.LOG.warn(String.format("File %s doesn't exist. It will be created now%n", arg1));
				fileForSavingString.createNewFile();
			}
			fw = new FileWriter(fileForSavingString);
			bufferW = new BufferedWriter(fw);
			bufferW.write(arg0);
			bufferW.newLine();
			// push from buffer
			bufferW.flush();
		} catch (IOException ioex) {
			MyLogging.LOG.debug(ioex, ioex);
		} finally {
			try {
				if (bufferW != null) {
					bufferW.close();
				}
			} catch (IOException e) {
				MyLogging.LOG.debug(e, e);
			}
		}
	}

}
