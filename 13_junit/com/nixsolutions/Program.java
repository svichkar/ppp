package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

/**
 * Program for driving robot
 * 
 * @author maxb
 * 
 */
public class Program {
	private Robot r2d2;
	File logFileProgram;


	public Program(Robot rb) throws IOException {

		r2d2 = rb;
		logFileProgram = new File(".//temp//program.log");
		logFileProgram.delete();
		///log it
		writeStatusOfProgramToLogFile("Starting program...");

	}

	public void executeProgram(String programListing) throws IOException {
		if (r2d2.isReadyToGo()) {
			writeStatusOfProgramToLogFile("Robot is ready to go!");
			for (char ch : programListing.toCharArray()) {
				switch (ch) {
				case 'f':
					r2d2.stepForward();
					writeStatusOfProgramToLogFile("Making step forward!");
					break;
				case 'l':
					r2d2.turnLeft();
					writeStatusOfProgramToLogFile("Turning left!");
					break;
				case 'r':
					r2d2.turnRight();
					writeStatusOfProgramToLogFile("Turning right!");
					break;
				default:
					break;
				}
			}

		} else {
			writeStatusOfProgramToLogFile("Robot is not ready!");
		}

	}

	private void writeStatusOfProgramToLogFile(String message)
			throws IOException {
		if (!logFileProgram.exists()) {
			logFileProgram.getParentFile().mkdirs();
			logFileProgram.createNewFile();
		}
		
		Calendar cl = Calendar.getInstance();
		String logForWriting = String.format("%1s %2s%n", cl.getTime() , message);

		FileUtils.writeStringToFile(logFileProgram, logForWriting, true);
	}

}
