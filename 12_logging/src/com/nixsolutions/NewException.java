package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class NewException implements Save {
    static final Logger LOG = LogManager.getLogger(NewException.class);

    public static void main(String[] args) throws MalformedURLException {
	LOG.info("Application was started");
	NewException newEx = new NewException();
	LOG.debug(newEx);
	LOG.info("Instance newEx of NewException class was created successfully ");
	newEx.save("This is data for input file ", "D:\\1\\1\\1\\1\\1.txt");
	LOG.info("Data was loaded into file by path D:\\1\\1\\1\\1\\1.txt and application is finished.");
    }

    @Override
    public void save(String inputText, String filePath) {
	LOG.trace("Save method started with next input : inputText - " + inputText + ", and filePath : - " + filePath);
	BufferedWriter writer = null;
	try {

	    File file = new File(filePath);
	    LOG.debug("Instance of File class was created.");
	    if (!file.exists()) {
		String directoryPath = filePath.substring(0, filePath.lastIndexOf('\\'));
		new File(directoryPath).mkdirs();

	    }

	    // System.out.println(file.getCanonicalPath());
	    LOG.debug(file.getCanonicalPath());

	    writer = new BufferedWriter(new FileWriter(file)); // if file is not
							       // exist it will
							       // be created ,
							       // otherwise it
							       // will be
							       // overwritten
	    LOG.debug("BufferedWriter was created");
	    writer.write(inputText);
	    // System.out.println("Data: " + inputText + "\nWas successfully
	    // written into file.");
	    LOG.info("Data: " + inputText + ". Was successfully written into file.");
	    LOG.trace("Method is finished.");
	} catch (IOException iOexept) {

	    try {

		throw LOG.throwing(new FileNotFoundExp(iOexept.getMessage()));

	    } catch (FileNotFoundExp e) {

		LOG.catching(e);
		LOG.error("Applcation is dead.");
	    }

	} finally {
	    try {
		LOG.warn("IO Exception could appear.");
		writer.close();
	    } catch (IOException e) {
		LOG.catching(e);
		LOG.error("Applcation is dead.");
	    }
	}

    }

}
