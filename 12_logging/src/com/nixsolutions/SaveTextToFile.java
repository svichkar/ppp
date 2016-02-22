package com.nixsolutions;

import exception.Save;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implements Save interface
 * @author Sirotkin Mikhail
 */
public class SaveTextToFile implements Save {

    public static final Logger LOG = LogManager.getLogger();

    /**
     * Override method 'save'. Write textToSave to file and save it to next path: 'absolutePathToFile'
     * Rewrite file if it already exist
     * @param textToSave - text for saving
     * @param absolutePathToFile
     */
    @Override
    public void save(String textToSave, String absolutePathToFile){
        LOG.entry(textToSave, absolutePathToFile);
        if(textToSave == null)
            LOG.throwing(new CustomRTException("Input text String is null!"));
            //throw new CustomRTException("Input text String is null!");
        if(absolutePathToFile == null)
            LOG.throwing(new CustomRTException("Path input object is null!"));
        File fileToWrite = new File(absolutePathToFile);
        if(!fileToWrite.exists()){
            try{
                LOG.info("File is absent (path '{}'), so let create a new one", absolutePathToFile);
                fileToWrite.createNewFile();
                LOG.info("File was created!");
            } catch (IOException ex){
                LOG.error(ex.getMessage(), ex);
                throw new CustomRTException("Can't create file by path '" + absolutePathToFile + "'!. Exception message: "
                        + ex.getMessage());
            }
        } else LOG.info("File {} has already existed.", absolutePathToFile);
        try(FileWriter writer = new FileWriter(absolutePathToFile, false))
        {
            writer.write(textToSave);
            LOG.info("Text {} have been written to file!", textToSave);
        }
        catch(IOException ex){
            LOG.error(ex.getMessage(), ex);
            LOG.throwing(new CustomRTException("Can't write text to file by path '" + absolutePathToFile + "'!. Exception message: "
                    + ex.getMessage()));
        }
    }

}
