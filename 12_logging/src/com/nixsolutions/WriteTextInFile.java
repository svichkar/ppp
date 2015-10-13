package com.nixsolutions;

import java.io.File;
import exception.Save;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class WriteTextInFile implements Save {

    private static final Logger LOG = LogManager.getLogger(WriteTextInFile.class.getName());

    @Override
    public void save(String text, String path) {
	LOG.entry();
	File txtFile = new File(path);
	if (!txtFile.exists()) { // Check if file exists
	    try {
		if (txtFile.getPath().lastIndexOf(File.separator) > -1) {
		    File dirPath = new File(path.substring(0, txtFile.getPath().lastIndexOf(File.separator)));
		    dirPath.mkdirs();
		    LOG.info("Directory by path " + path.substring(0, txtFile.getPath().lastIndexOf(File.separator))
			    + " is created");
		}
		txtFile.createNewFile(); // create new file
		LOG.info("File " + txtFile.getPath() + " is created");
	    } catch (FileWriteExeption e) {
		LOG.throwing(LOG.getLevel().ERROR, new RuntimeException(e.getMessage()));
	    } catch (IOException e) {
		LOG.throwing(LOG.getLevel().ERROR, new RuntimeException(e.getMessage()));
	    }
	} else {
	    LOG.info("File \"" + txtFile.getPath() + "\" is already exist");
	}
	try (FileWriter fw = new FileWriter(txtFile)) {
	    fw.write(text); // write text into file
	    LOG.info("Text \"" + text + "\" is written into file succesfully");
	} catch (FileWriteExeption e) {
	    LOG.throwing(LOG.getLevel().ERROR, new RuntimeException(e.getMessage()));
	} catch (IOException e) {
	    LOG.throwing(LOG.getLevel().ERROR, new RuntimeException(e.getMessage()));
	}
    }
}
