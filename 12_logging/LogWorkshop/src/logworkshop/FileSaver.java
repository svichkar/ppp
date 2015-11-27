/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logworkshop;

import exception.Save;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Saves string to file
 *
 * @author mednorcom
 */
public class FileSaver implements Save {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Saves string to file
     *
     * @param string - text
     * @param string1 - file and path
     */
    @Override
    public void save(String string, String string1) {

        LOGGER.entry(string, string1);
        FileWriter file = null;
        try {
            LOGGER.trace("Trying Create FileWriter");
            file = new FileWriter(string1);
            LOGGER.trace("Created FileWriter");
            LOGGER.trace("Trying to write");
            file.write(string);
            LOGGER.trace("Write is successful");
        } catch (IOException ex) {
            LOGGER.warn("Cannot write to file", ex);
            LOGGER.debug("string1={}, string={}", string1, string);
            throw new RuntimeIOException(ex);
        } finally {
            try {
                LOGGER.trace("Trying release stream");
                if (file != null) {
                    file.close();
                }
                LOGGER.trace("Stream is released");
            } catch (IOException ex) {
                LOGGER.warn("Cannot release stream", ex);
                throw new RuntimeIOException(ex);
            }
        }
        LOGGER.exit();

    }
}
