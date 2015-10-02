/**
 * 
 */
package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import javax.sql.rowset.spi.TransactionalWriter;

/**
 * @author mixeyes
 *
 */
public interface Save {
	default void save(String stringToSave, String filePath) throws IOException, SaveException {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(filePath);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(stringToSave);

		} catch (FileNotFoundException ex) {
			throw new SaveException("Please Enter valid file name");
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (fileWriter != null)
				fileWriter.close();
		}
	}

}
