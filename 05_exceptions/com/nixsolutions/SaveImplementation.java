package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import exception.Save;

public class SaveImplementation implements Save {

	@Override
	public void save(String str, String path) {
		Writer writer = null;
		File logFile = new File(path);
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				throw new MyException("IOException caused: ", e);
			}
		}
		try {
			writer = new FileWriter(logFile);
			writer.write(str);
			writer.write(System.getProperty("line.separator"));
			writer.flush();
			System.out.println("Text was saved to the file");
		} catch (IOException ex) {
			throw new MyException("IOException caused: ", ex);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ex) {
					throw new MyException("IOException caused: ", ex);
				}
			}
		}

	}

	public static void main(String[] args) {

		SaveImplementation saveObj = new SaveImplementation();
		saveObj.save("Lab5: test test test", "exceptions.txt");
	}

}
