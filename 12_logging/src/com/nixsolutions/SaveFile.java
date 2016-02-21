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
        LOG.info("set output file: " + outputFile);
        outputFile.getParentFile().mkdirs();
        LOG.info("create all dirs structure, according paths " + outputFile.getParentFile());
        FileWriter fwriter = null;
        LOG.info("fwriter variable initialize");
        try {
            LOG.info("check if exists", outputFile.exists());
            if (!outputFile.exists()) {
                LOG.info(outputFile + " not exists");
                fwriter = new FileWriter(outputFile);
                LOG.info("create new FileWriter for file '" + outputFile + "'");
                fwriter.write(data);
                LOG.info("write data to file:\n " + data);
            } else {
                LOG.info(outputFile + " exists");
                throw LOG.throwing(new CustomException("File already exists"));
            }
            LOG.info("close all streams");
            fwriter.flush();
            fwriter.close();
        } catch (IOException io) {
            throw new CustomException(io);
        }
    }
}