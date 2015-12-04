package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.MyOwnExeption;

import exception.Save;

/**
 * The Class Exp.
 */
public abstract class Exp implements Save {
	
	private static final Logger lOG = LogManager.getLogger(Exp.class.getName());
	@Override
	public void save(String stringForSaving, String absolutPathToFile) {
		lOG.info("Path for file - "+absolutPathToFile);
		lOG.info("String for writing - "+stringForSaving);
		File file = new File(absolutPathToFile);
		try {
			if (stringForSaving.isEmpty()) {
				throw lOG.throwing( new MyOwnExeption(
						"String for saving is empty! Please enter data to string for saving in the file."));
			} else {
				if (file.exists()) {
					lOG.warn("Rewriting input string to file.");
					FileWriter fileWriter = new FileWriter(file);
					lOG.debug("Writing string to file...", fileWriter.append(stringForSaving)); 
					fileWriter.flush();
					fileWriter.close();
					lOG.info("String is saved to file.");
				} else {
					lOG.info("Creating file for writing.");
					file.createNewFile();
					FileWriter fileWriter = new FileWriter(file);
					lOG.debug("Writing string to file...", fileWriter.append(stringForSaving));
					fileWriter.flush();
					fileWriter.close();
					lOG.info("String is saved to file.");
				}
			}

		} catch (IOException e) {
			lOG.error(e.getMessage(), e);
			throw lOG.throwing( new MyOwnExeption());
		}

	}
}
