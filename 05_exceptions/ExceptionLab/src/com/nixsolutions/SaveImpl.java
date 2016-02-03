package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import exception.Save;

public class SaveImpl implements Save {

	@Override
	public void save(String text, String path) {
		File file = new File(path);
		if (!file.exists()) {
			try (FileWriter fileWriter = new FileWriter(file)) {
				fileWriter.write(text);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			throw new FileAlreadyExistsException("File with following path already exists: " + path);
		}
	}
}
