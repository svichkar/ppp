package com.nixsolutions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Program {

	private Robot robotObj;
	private ByteArrayOutputStream outByteStream;

	public Program() {
		outByteStream = new ByteArrayOutputStream();
		robotObj = new Robot(outByteStream);
	}

	public void makeRobotMove(String robotPath) {
		parsePath(robotPath);
	}
	
	public void makeRobotMove(String robotPath, File file) {
		parsePath(robotPath);
		writeToFile(file);
	}
		
	public void parsePath(String robotPath) {
		char[] pathArray = robotPath.toLowerCase().toCharArray();
		for (char item : pathArray) {
			if (item == 'l') {
				robotObj.turnLeft();
			} else if (item == 'r') {
				robotObj.turnRight();
			} else if (item == 'f') {
				robotObj.stepForward();
			}
		}
	}

	public void writeToFile(File file) {
		try (FileOutputStream outFileStream = new FileOutputStream(file)) {
			outFileStream.write(outByteStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
