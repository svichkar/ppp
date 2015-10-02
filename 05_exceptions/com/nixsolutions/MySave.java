package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

import exception.Save;

public class MySave implements Save {

	public void save(String arg0, String arg1) {
		BufferedWriter bufferW = null;
		FileWriter fw = null;

		try {

			if (arg0.length() == 0) {
				throw new MyException("Text has 0 length");
			}
			
			if (arg1.length() == 0)
			{	
				throw new MyException("File is not defined");
			}
			
			File fileForSavingString = new File(arg1);
			if (!fileForSavingString.exists()) {
				fileForSavingString.createNewFile();
			}
			fw = new FileWriter(fileForSavingString);
			bufferW = new BufferedWriter(fw);
			bufferW.write(arg0);
			bufferW.newLine();
			// push from buffer
			bufferW.flush();
		} catch (MyException ex) {
			System.out.printf(
					"Exception caught by own exception. Message is \"%s\"%n",
					ex.getMessage());
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferW != null) {
					bufferW.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
