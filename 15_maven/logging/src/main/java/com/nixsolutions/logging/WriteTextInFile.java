package com.nixsolutions.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class WriteTextInFile implements Save {

    private static final Logger LOG = LogManager.getLogger(WriteTextInFile.class.getName());

    @Override
    public void save(String text, String path) {
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
		LOG.error(e);
		throw new RuntimeException(e.getMessage());
	    } catch (IOException e) {
		LOG.error(e);
		throw new RuntimeException(e.getMessage());
	    }
	} else {
	    LOG.info("File \"" + txtFile.getPath() + "\" is already exist");
	}
	try (FileWriter fw = new FileWriter(txtFile)) {
	    fw.write(text); // write text into file
	    LOG.info("Text \"" + text + "\" is written into file succesfully");
	} catch (FileWriteExeption e) {
	    LOG.error(e);
	    throw new RuntimeException(e.getMessage());
	} catch (IOException e) {
	    LOG.error(e);
	    throw new RuntimeException(e.getMessage());
	}
    }
}
