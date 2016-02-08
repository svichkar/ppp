package com.nixsolutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by PAVALVL on 2/3/2016.
 */
public class SaveFile implements Save {
    private File fileName = new File("fileToBeSaved.txt");

    public static void main(String[] args) {
        SaveFile saveFile = new SaveFile();
        try {
            saveFile.prepareFileForSaving();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Implements the save method from the Save interface
     */
    public void save(String stringToBeSaved, String absolutePath) throws Exception {
        if (!fileName.exists()) {
            fileName.createNewFile();
        } else {
            throw new Exception("The file with the name " + fileName + " already exists");
        }
    }

    /**
     * Prepares a file for saving (specifies the path and name
     */
    public void prepareFileForSaving() throws Exception {
        String stringToBeSaved = "String to be saved";
        fileName = new File("fileToBeCreated.txt");
        String absPath = fileName.getAbsolutePath();
        save(stringToBeSaved, absPath);
        writeToFile(stringToBeSaved, absPath);
    }

    /**
     * Writes a string to the new file
     */
    public void writeToFile(String stringToBeWrittenToFile, String path) throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(path);
            writer.write(stringToBeWrittenToFile);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
