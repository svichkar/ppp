package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by konstantin on 16.11.2015.
 */
public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String args[]) {

        LOG.entry();
        SaveFile saveFile = new SaveFile();
        LOG.debug("Instance of \"{}\" class was created.", saveFile.getClass().getName());

        saveFile.save("Path is not absolute!!! Exception should be thrown.", "/directory/doesNotExistFile.txt");
        saveFile.save("Write this text into file which doesn't exist.", "d:/directory/doesNotExistFile.txt");
        saveFile.save("Overwrite existing file with current text.", "d:/directory/doesNotExistFile.txt");

        LOG.exit();
    }
}