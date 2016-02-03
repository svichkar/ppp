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
        try {
            outputFile.delete();
            FileWriter fwriter = new FileWriter(outputFile);
            fwriter.write(data);
            fwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(String data, String fileName) {
        File outputFile = new File(fileName);
        FileWriter fwriter = null;
        try {
            if (!outputFile.exists()) {
                fwriter = new FileWriter(outputFile);
                fwriter.write("");
            } else {
                throw new CustomException("File already exists");
            }
            fwriter.write(data);
            fwriter.close();
        } catch (IOException io) {
            throw new CustomException(io);
        } catch (CustomException ex) {
            JFrame frame = new JFrame();
            int result = JOptionPane.showConfirmDialog(frame, "File already exists, overwrite it?", "", JOptionPane.OK_CANCEL_OPTION);
            frame.dispose();
            if (result == 0) {
                reWrite(data, outputFile);
            } else {
                ex.printStackTrace();
            }
        }
    }
}