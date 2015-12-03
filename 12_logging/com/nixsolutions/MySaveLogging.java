package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MySaveLogging implements exception.Save {
	private static final Logger LOG = LogManager.getLogger(MySaveLogging.class.getName());

	@Override
	public void save(String str, String path) {
		LOG.entry(str, path);
		File myFile = new File(path);
		FileWriter fw = null;
		try {

			if (!myFile.exists()) {
				LOG.info("Creating file");
				myFile.createNewFile();
			}
			LOG.info("Writing to the file");
			fw = new FileWriter(myFile, false);
			fw.write(str);
			fw.flush();

		} catch (IOException e) {
			LOG.catching(new RuntimeException(e));
		} finally {
			try {
				LOG.info("Closing FileWriter...");
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				LOG.warn("FileWriter is not closed");
				throw new RuntimeException(e);
			}
		}
		LOG.exit();
	}

}
