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
     * Override method 'save'. Write textToSave to file and save it to next path: 'absolutePathToFile'
     * Rewrite file if it already exist
     * @param textToSave - text for saving
     * @param absolutePathToFile
     */
    @Override
    public void save(String textToSave, String absolutePathToFile){
        if(textToSave == null)
            throw new CustomRTException("Input text String is null!");
        if(absolutePathToFile == null)
            throw new CustomRTException("Path input object is null!");
        File fileToWrite = new File(absolutePathToFile);
        if(!fileToWrite.exists()){
            try{
                fileToWrite.createNewFile();
            } catch (IOException ex){
                throw new CustomRTException("Can't create file by path '" + absolutePathToFile + "'!. Exception message: "
                        + ex.getMessage());
            }
        }
        try(FileWriter writer = new FileWriter(absolutePathToFile, false))
        {
            writer.write(textToSave);
        }
        catch(IOException ex){
            throw new CustomRTException("Can't write text to file by path '" + absolutePathToFile + ". Exception message: "
                    + ex.getMessage());
        }
    }

}
