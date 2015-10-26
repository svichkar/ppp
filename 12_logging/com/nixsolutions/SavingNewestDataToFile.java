package com.nixsolutions;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class SavingNewestDataToFile {

	private final static Logger LOG = LogManager
			.getLogger(SavingNewestDataToFile.class);
	private static final Marker TEXTTOVALIDATE = MarkerManager
			.getMarker("TEXTTOVALIDATE");

	public static void main(String[] args) {

		MyNewestSave ms = new MyNewestSave();
		LOG.info("Entering into application...");
		LOG.info("Define array of words...");
		String[] words = new String[] { "", "else", "switch", "for", "while",
				"", "case", "catch", "throw", "finally", "if" };

		int counter = 0;

		LOG.info("Getting file for writing log...");
		Path file = Paths.get(".\\temp\\test.txt");
		while (counter < words.length) {
			LOG.trace(TEXTTOVALIDATE, String.format(
					"Writing word \"%1s\" to file \"%2s\" ....%n",
					words[counter], file.toAbsolutePath()));
			try {
				ms.save(words[counter], file.toAbsolutePath().toString());
			} catch (Exception ex) {
				LOG.error(ex, ex);
				LOG.debug(String.format("Text is \" %s\"", words[counter]), ex);
			}
			counter++;
		}
		LOG.info("Exiting application...");
	}
}
