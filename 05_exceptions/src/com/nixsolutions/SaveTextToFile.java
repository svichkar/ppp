package com.nixsolutions;

import exception.Save;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Implements Save interface
 * @author Sirotkin Mikhail
 */
public class SaveTextToFile implements Save {

    /**
     * Implements method 'save'
     * @param textToSave
     * @param absolutPathToFile
     */
    @Override
    public void save(String textToSave, String absolutPathToFile){

        File fileToWrite = new File(absolutPathToFile);
        if(!fileToWrite.exists()){
            try{
                fileToWrite.createNewFile();
            } catch (IOException ex){
                System.out.println("Can't create file by path '" + absolutPathToFile + "'!. Exception message: "
                        + ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
        try(FileWriter writer = new FileWriter(absolutPathToFile, false))
        {
            writer.write(textToSave);
        }
        catch(IOException ex){
            System.out.println("Can't write text to file by path '" + absolutPathToFile + ". Exception message: "
                    + ex.getMessage());
            throw  new RuntimeException(ex);
        }
    }

}
