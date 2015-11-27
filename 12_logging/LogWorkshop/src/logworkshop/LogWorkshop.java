/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logworkshop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mednorcom
 */
public class LogWorkshop {

    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LOGGER.entry(args);
        try {
            LOGGER.trace("Calling file writer");
            new FileSaver().save("test", "E2:\\out.txt");
            LOGGER.info("Write complete");
        } catch (RuntimeIOException ex) {
            LOGGER.error("File write Error; Please check the details", ex);
        } catch (NullPointerException ex) {
            LOGGER.error("Null pointer Exception; Please check the details", ex);
        }
        LOGGER.exit();

    }

}
