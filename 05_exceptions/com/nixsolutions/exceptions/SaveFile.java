package com.nixsolutions.exceptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFile implements exception.Save {

	@Override
	public void save(String strTextForSaving, String strPath) {
		FileWriter writeFile = null;
		try {
			if (strTextForSaving.isEmpty() || strPath.isEmpty()) {
				throw new CustomException();
			}
			File fileToSave = new File(strPath);
			try {
				if (fileToSave.exists()) {

					System.out.println("File was rewritten");
					writeFile = new FileWriter(fileToSave);
					writeFile.write("");

				} else {

					System.out.println("File was created");
					fileToSave.createNewFile();
					writeFile = new FileWriter(fileToSave);

				}

				writeFile.write(strTextForSaving);
				writeFile.flush();

			} catch (IOException e) {
				throw new CustomException(e);
			}

		} catch (CustomException ex) {
			throw new RuntimeException(ex);
		}
	}
}
