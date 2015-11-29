package com.nixsolutions.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
	public static final Logger LOG = LogManager.getLogger();

	public static void main(String[] args) {
		LOG.entry();
		FileSaver save = new FileSaver();
		try {
			LOG.debug("first try with valid parameters");
			save.save("test string to save", "/Users/evgeniykryzhanovskiy/text1.txt");
			LOG.debug("second try with invalid parameters, error will occure");
			save.save("test string to save", "geniykryzhanovskiy/text.txt");
		} catch (Exception e) {
			LOG.catching(e);
		}
		LOG.exit();
	}
}
