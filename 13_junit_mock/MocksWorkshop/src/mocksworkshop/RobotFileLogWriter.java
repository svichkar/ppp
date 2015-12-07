/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocksworkshop;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mednorcom
 */
public class RobotFileLogWriter implements RobotLogWriter {

    private File movelog;
    private final static Logger LOGGER = LogManager.getLogger();

    public RobotFileLogWriter(String filename) {
        this.movelog = FileUtils.getFile(filename);
    }
    
    

    @Override
    public void writeLog(String loggingIssue) throws IOException {
        try {
            FileUtils.write(movelog, loggingIssue, true);
        } catch (IOException ex) {
            LOGGER.warn("Robot log write error", ex);
            throw ex;
        }
    }
}
