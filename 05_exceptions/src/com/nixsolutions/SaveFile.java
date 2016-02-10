package com.nixsolutions;

import exception.Save;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by pantiukhin on 2/3/2016.
 */
public class SaveFile implements Save {
    private File fileName = new File("fileToBeSaved.txt");
    private String absPath = fileName.getAbsolutePath();
    private String stringToBeSaved = "String to be saved";

    public static void main(String[] args) throws CustomException {
        SaveFile saveFile = new SaveFile();
        saveFile.save(saveFile.stringToBeSaved, saveFile.absPath);
    }

    /**
     * Implements the save method from the Save interface
     */
    public void save(String stringToBeSaved, String absolutePath) {
        try {
            prepareFileForWriting();
            writeToFile(stringToBeSaved, absPath);
        } catch (CustomException ex) {
            ex.getErrorCause();
            ex.printStackTrace();
        }
    }

    /**
     * Prepares a file for saving (specifies the path and name)
     */
    public void prepareFileForWriting() throws CustomException {
        if (!fileName.exists()) {
            try {
                fileName.createNewFile();
            } catch (IOException ex) {
                throw new CustomException("File " + absPath + " has not been created");
            }
        } else {
            throw new CustomException("File " + absPath + " already exists");
        }
    }

    /**
     * Writes a string to the new file
     */
    public void writeToFile(String stringToBeWrittenToFile, String path) throws CustomException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            writer.write(stringToBeWrittenToFile);
        } catch (IOException e) {
            throw new CustomException("String " + stringToBeWrittenToFile + " has not been written to the file");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new CustomException("The writer has not been closed");
                }
            }
        }
    }
}
