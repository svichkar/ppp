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
    @Override
    public void save(String data, String fileName){
        String pathName = System.getProperty("user.dir");
        File outputFile = new File(fileName);
        boolean reWrite = false;
        FileWriter fwriter = null;
        try {
            if (!outputFile.exists()) {
                fwriter = new FileWriter(outputFile);
                fwriter.write("");
            } else {
                throw new CustomExeption("File already exists");
            }
            fwriter.write(data);
            fwriter.close();
        } catch (IOException io) {
            throw new CustomExeption(io);
        }
    }
}

/*JFrame frame = new JFrame();
int result = JOptionPane.showConfirmDialog(frame, "File already exists, overwrite it?", "", JOptionPane.OK_CANCEL_OPTION);
frame.dispose();
        if (result == 0) {
        try {
        outputFile.delete();
        fwriter = new FileWriter(outputFile);
        fwriter.write(data);
        fwriter.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
        }
        }
        }*/
