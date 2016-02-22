package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class that execute 'save' method from 'SaveTextToFile' class
 * @autor Sirotkin Mikhail
 */
public class Main {

    public static final Logger LOG = LogManager.getLogger();
    /**
     * Main executable method
     * We throw exception 'up' if it happens
     * We use some static path. If
     * @param args
     * @throws
     */
    public static void main(String[] args) throws Exception{

        LOG.debug("\n----------------Start report----------------");
        LOG.entry();
        String toWrite = "text for write to file";
        String path1 = "E:\\JavaPPP\\savefile.txt";

        LOG.debug("Save toWrite value to file with correct parameters:");
        SaveTextToFile sttf = new SaveTextToFile();
        sttf.save(toWrite, path1);

        //next row throw exception with message 'Path input object is null!'
        try {
            LOG.debug("Next row have to throw exception with message 'Path input object is null!'");
            sttf.save(toWrite, null);
        } catch (Exception ex) {
            LOG.catching(ex);
        }

        //next row throws Custom exception with text (Access is Denied) if start on Win8
        try {
            LOG.debug("Next row throws Custom exception with text (Access is Denied) if start on Win8");
            sttf.save(toWrite, "c:");
        } catch (Exception ex) {
            LOG.catching(ex);
        }

        LOG.debug("\n----------------End report----------------");
    }
}
