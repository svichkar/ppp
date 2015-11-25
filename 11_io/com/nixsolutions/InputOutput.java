package com.nixsolutions;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class InputOutput {

	public static void main(String[] args) {
		File source = new File("D:/Java/eclipse/Task_io");
		File destination = new File("D:/Java/eclipse/Task_io_copy");
		try {
			FileUtils.copyDirectory(source, destination);
			System.out.println("Successful!");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
