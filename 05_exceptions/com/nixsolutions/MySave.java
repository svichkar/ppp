package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MySave implements exception.Save {

	@Override
	public void save(String str, String path) {
		File myFile = new File(path);

		if (!myFile.exists()) {
			try {
				myFile.createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(myFile, false);
			fw.write(str);
			fw.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
