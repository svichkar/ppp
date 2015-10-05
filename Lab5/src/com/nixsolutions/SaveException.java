package com.nixsolutions;

import java.io.*;
import exception.Save;

public class SaveException implements Save {

	@Override
	public void save(String textToSave, String path) {
		File file = new File(path);
		FileWriter fw = null;
		try {
			if (!file.exists()) {
				String folderPath = path.substring(0, path.lastIndexOf('\\'));
				new File(folderPath).mkdirs();
				file.createNewFile();
			}
			fw = new FileWriter(file, false);
			fw.write(textToSave);
			fw.flush();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
