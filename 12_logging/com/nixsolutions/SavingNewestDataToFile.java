package com.nixsolutions;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SavingNewestDataToFile {

	public static void main(String[] args) {
		MyNewestSave ms = new MyNewestSave();
		MyLogging.LOG.trace("Entering into application...");
		MyLogging.LOG.trace("Define array of words...");
		String[] words = new String[] { "", "else", "switch", "for", "while",
				"", "case", "catch", "throw", "finally", "if" };

		int counter = 0;
		MyLogging.LOG.trace("Getting file for writing log...");
		Path file = Paths.get(".\\temp\\test.txt");
		while (counter < words.length) {
			MyLogging.LOG.trace(String.format(
					"Writing word \"%1s\" to file \"%2s\" ....%n",
					words[counter], file.toAbsolutePath()));
			try {
				ms.save(words[counter], file.toAbsolutePath().toString());
			} catch (Exception ex) {
				MyLogging.LOG.error(ex, ex);
				MyLogging.LOG.debug(String.format("Text is \" %s\"", words[counter]), ex);
			}
			counter++;
		}
	}
}
