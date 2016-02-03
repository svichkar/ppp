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
    private File fileName;

    public static void main(String[] args) throws IOException {
        SaveFile saveFile = new SaveFile();
        saveFile.prepareFileForSaving();
    }

    /**
     * Implements the save method from the Save interface
     */
    public void save(String stringToBeSaved, String absolutePath) {
        try {
            if (!fileName.exists()) {
                if (fileName.createNewFile()) {
                    writeToFile(stringToBeSaved, absolutePath);
                } else {
                    throw new Exception("The file with the name " + fileName + " has not been created");
                }
            } else {
                throw new IOException("The file with the name " + fileName + " already exists");
            }
        } catch (IOException e) {
            if (fileName.delete()) {
                save(stringToBeSaved, absolutePath);
            } else {
                System.out.println(e.toString());
            }
        } catch (Exception e) {
            save(stringToBeSaved, absolutePath);
        }
    }

    /**
     * Prepares a file for saving (specifies the path and name
     */
    public void prepareFileForSaving() throws IOException {
        String stringToBeSaved = "String to be saved";
        fileName = new File("fileToBeCreated.txt");
        String absPath = fileName.getAbsolutePath();
        save(stringToBeSaved, absPath);
    }

    /**
     * Writes a string to the new file
     */
    public void writeToFile(String stringToBeWrittenToFile, String path) throws IOException {
        String line;
        FileWriter writer = null;
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        try {
            writer = new FileWriter(fileName);
            writer.write(stringToBeWrittenToFile);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        if ((line = reader.readLine()) == null) {
            throw new IOException("Nothing has been written to the file");
        } else {
            System.out.println("The following string has been written to the file: " + line);
            reader.close();
        }
    }
}
