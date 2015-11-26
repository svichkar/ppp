package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by kozlovskij on 11/13/2015.
 */
public class TestLogging {
    private static final Logger LOGGER = LogManager.getRootLogger();
    public static void main(String[] args) {
        CustomSaveLogging test = new CustomSaveLogging();
        try {
            LOGGER.trace("start the copying process");
            test.save("test", "D:\\test.txt");
            LOGGER.debug(test.getText() + "-" + test.getPath());
            test.save("test1", "");
            LOGGER.debug(test.getText() + "-" + test.getPath());
            test.save("test2", "D:\\test.txt");
            LOGGER.debug(test.getText() + "-" + test.getPath());
        } catch (RuntimeException e) {
            LOGGER.error("We caught exception: ",  e);
        } finally {
            LOGGER.trace("application close");
        }
    }
}
