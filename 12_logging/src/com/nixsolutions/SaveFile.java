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
        LOG.info("set output file: "+outputFile);
        outputFile.getParentFile().mkdirs();
        FileWriter fwriter = null;
        try {
            if (!outputFile.exists()) {
                fwriter = new FileWriter(outputFile);
                fwriter.write(data);
            } else {
                throw LOG.throwing(new CustomException("File already exists"));
            }
            fwriter.flush();
            fwriter.close();
        } catch (IOException io) {
            throw new CustomException(io);
        }
    }
}