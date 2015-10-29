package com.nixsolutions.logging;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ExceptionsTask {

    private static final Logger LOG = LogManager.getLogger(ExceptionsTask.class.getName());

    public static void main(String[] args) {
	WriteTextInFile writeText = new WriteTextInFile();
	LOG.debug(writeText);
	writeText.save("Hello World!", "Test.txt");
    }
}