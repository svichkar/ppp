/**
 * 
 */
package com.nixsolutions.robot.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author mixeyes
 *
 */
public class RobotLogger {
	private File logFile;

	public RobotLogger() {
		String curDir = System.getProperty("user.dir");
		logFile = new File(curDir + "\\logs");
		if (!logFile.exists())
			logFile.mkdir();
		logFile = new File(logFile, "robot.log");
		if (logFile.exists())
			logFile.delete();
		// TODO Auto-generated constructor stub
	}

	public RobotLogger(String filePath) {
		logFile = new File(filePath);
		if (logFile.isDirectory())
			logFile = new File(logFile, "robot.log");
		if (logFile.exists())
			logFile.delete();
		// TODO Auto-generated constructor stub
	}

	public void writeToLog(RobotClass robot) throws IOException {
		String logger = "";
		if (!logFile.exists()) {
			logFile.isDirectory();
			logFile.createNewFile();
		}
		for (String line : Files.readAllLines(logFile.toPath())) {
			logger += line + "\n";
		}
		FileWriter fw = new FileWriter(logFile);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(logger
				+ String.format("Robot position X: %1$s; Y: %2$s; Course: %3$s", robot.getCoordX(), robot.getCoordY(), robot.getCourse().toString()));
		bw.flush();
		bw.close();
	}

}
