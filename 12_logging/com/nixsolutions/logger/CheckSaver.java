package com.nixsolutions.logger;

import org.apache.logging.log4j.*;

public class CheckSaver {

    private static final Logger LOG = LogManager.getLogger(CheckSaver.class);

    public static void main(String[] argc) {
        LOG.entry(argc);
        Saver saver = new Saver();
        saver.save("line for the save", "D:/2.txt");
        LOG.trace("successfully save");
        LOG.exit();
    }

}