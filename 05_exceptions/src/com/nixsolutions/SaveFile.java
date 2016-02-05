package com.nixsolutions;

import exception.Save;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by sobolenko on 2/2/2016.
 */
public class SaveFile implements Save {
    /**
     * Rewrite file, if this option has been chosen
     *
     * @param data       which write to file
     * @param outputFile file name
     */
    public static void reWrite(String data, File outputFile) {
        try (FileWriter fwriter = new FileWriter(outputFile)) {
            outputFile.delete();
            fwriter.write(data);
            fwriter.flush();
            fwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(String data, String fileName) {

        File outputFile = new File(fileName);
        outputFile.getParentFile().mkdirs();
        FileWriter fwriter = null;
        try {
            if (!outputFile.exists()) {
                fwriter = new FileWriter(outputFile);
                fwriter.write(data);
            } else {
                throw new CustomException("File already exists");
            }
            fwriter.flush();
            fwriter.close();
        } catch (IOException io) {
            throw new CustomException(io);
        }
    }
}