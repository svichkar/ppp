/**
 * 
 */
package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author mixeyes
 *
 */
public interface Save {
	default void save(String stringToSave, String filePath) throws IOException {
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(filePath);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(stringToSave);

		} catch (FileNotFoundException ex) {
			System.out.println("Please Enter valid file name");
			ex.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			bufferedWriter.close();
			fileWriter.close();
		}
	}

}
