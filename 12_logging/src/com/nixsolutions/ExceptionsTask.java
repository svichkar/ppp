package com.nixsolutions;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ExceptionsTask {

    private static final Logger LOG = LogManager.getLogger(ExceptionsTask.class.getName());

    public static void main(String[] args) {
	LOG.entry();
	WriteTextInFile writeText = new WriteTextInFile();
	LOG.debug(writeText);
	writeText.save("Hello World!", "Test.txt");
    }
}
