package com.nixsolutions.logger;

import exception.Save;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class Saver implements {@link Save} helps save input line to the file
 *
 * @author Dmitry Zinovyi
 */
public class Saver implements Save {

    private static final Logger LOG = LogManager.getLogger(Saver.class);

    /**
     * Save to file input string
     *
     * @param input      saving string
     * @param pathToFile path to the file
     * @throws FileAlreadyExists
     */
    @Override
    public void save(String input, String pathToFile) {
        LOG.entry();
        LOG.debug("input parameters. input: \"{}\", pathToFile: \"{}\"", input, pathToFile);
        File file = new File(pathToFile);
        FileWriter fw = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
                fw = new FileWriter(file);
                fw.write(input);
                LOG.trace("write to file");
                fw.flush();
                LOG.trace("flush FileWriter");
            } catch (IOException e) {
                LOG.warn("in catch block");
                LOG.throwing(e);
                throw new RuntimeException(e);
            } finally {
                LOG.trace("in finally block");
                if (fw != null) {
                    try {
                        fw.close();
                        LOG.info("stream closed");
                    } catch (IOException e) {
                        LOG.warn("in catch block");
                        LOG.throwing(e);
                        throw new RuntimeException(e);
                    }
                }
            }
        } else {
            LOG.warn("create FileAlreadyExists exception");
            throw LOG.throwing(new FileAlreadyExists("File already exist"));
        }
        LOG.exit();
    }

}
