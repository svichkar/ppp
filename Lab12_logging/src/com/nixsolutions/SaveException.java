package com.nixsolutions;

import java.io.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.Save;

public class SaveException implements Save {
	private static final Logger LOG = LogManager.getLogger("SaveAction");

	@Override
	public void save(String textToSave, String path) throws WriteException {
		LOG.entry(textToSave, path);
		File file = new File(path);
		FileWriter fw = null;
		try {
			if (!file.exists()) {
				LOG.debug("File not exist. Create new file.");
				String folderPath = path.substring(0, path.lastIndexOf('\\'));
				new File(folderPath).mkdirs();
				file.createNewFile();
			}
			fw = new FileWriter(file, false);// to get exception set second parameter to true
			fw.write(textToSave);
			fw.flush();
			if (!read(path).equals(textToSave)) {
				throw new WriteException();
			}
		} catch (IOException e) {
			throw new WriteException();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					LOG.catching(e);
				}
			} else {
				LOG.warn("FileWriter was null");
			}
		}
	}

	private String read(String path) {
		LOG.entry(path);
		BufferedReader br = null;
		String toReturn = "";
		try {
			br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine()) != null) {
				toReturn += line;
			}
		} catch (IOException ex) {
			LOG.catching(ex);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					LOG.catching(e);
				}
			} else {
				LOG.warn("BufferedReader was null");
			}
		}
		return LOG.exit(toReturn);
	}
}
