package com.nixsolutions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class IoTask {

	/**
	 * Class for validating task for IO lab
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		///source
		File rootFolder = new File(".\\");
		///target
		File tempFolder = new File("D:\\temp\\1");

		try {
			if (!rootFolder.exists()) {
				System.out.printf("Source folder %s doesn't exist",
						rootFolder.toString());
			}
			if (!tempFolder.exists()) {
				tempFolder.mkdir();
			}
			FileUtils.copyDirectoryToDirectory(rootFolder, tempFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
