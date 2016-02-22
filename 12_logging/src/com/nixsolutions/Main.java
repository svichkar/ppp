/**
 * Created by sobolenko on 2/2/2016.
 */
package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        Logger LOG = LogManager.getLogger();
        SaveFile saveFile = new SaveFile();
        LOG.trace("Save to file");
        saveFile.save("test", System.getProperty("user.dir") + "\\TestFile.txt");
        LOG.trace("Program exit");
    }
}
