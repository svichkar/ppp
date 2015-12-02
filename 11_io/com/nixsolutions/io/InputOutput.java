package com.nixsolutions.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.StandardCopyOption;

public class InputOutput {

	public static void main(String[] args) {
		try {
			new InputOutput().copyStructureAndFiles(new File("D:\\java\\TestNIO"), new File("D:\\java\\Copy_Test_NIO"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Copies all of the folders and files from pathSource to pathDestination
	 * @param pathSource - path to the root folder where we are going to copy files and folders from
	 * @param pathDestination - path to the root folder where we are going to copy files and folders to
	 * @throws IOException
	 */
	private void copyStructureAndFiles(File pathSource, File pathDestination) throws IOException {
		if (pathSource.isDirectory()) {
			if (!pathDestination.exists()) {
				pathDestination.mkdir();
			}
			String[] files = pathSource.list();
			for (String fileName : files) {
				copyStructureAndFiles(new File(pathSource, fileName), new File(pathDestination, fileName));
			}
		} else {
			Files.copy(pathSource.toPath(), pathDestination.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
	}

}
