package com.nixsolutions;

import exception.Save;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by sobolenko on 2/2/2016.
 */
public class SaveFile implements Save {
    @Override
    public void save(String data, String fileName) {
        Logger LOG = LogManager.getLogger();
        File outputFile = new File(fileName);
        LOG.debug("set output file: " + outputFile);
        outputFile.getParentFile().mkdirs();
        LOG.info("create all dirs structure, according paths " + outputFile.getParentFile());
        FileWriter fwriter = null;
        LOG.info("fwriter variable initialize");
        try {
            LOG.debug("check if exists", outputFile.exists());
            if (!outputFile.exists()) {
                LOG.info(outputFile + " not exists");
                fwriter = new FileWriter(outputFile);
                LOG.info("create new FileWriter for file '" + outputFile + "'");
                fwriter.write(data);
                LOG.entry(fwriter);
                LOG.info("write data to file:\n " + data);
            } else {
                LOG.warn(outputFile + " exists");
                throw LOG.throwing(new CustomException("File already exists"));
            }
            LOG.info("close all streams");
            fwriter.flush();
            fwriter.close();
        } catch (IOException io) {
            LOG.error("Input output exception error");
            throw new CustomException(io);
        }
    }
}