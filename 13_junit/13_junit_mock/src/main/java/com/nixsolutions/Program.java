package com.nixsolutions;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Class Program.
 */
public class Program {

	/** The robot. */
	private Robot robot;

	/** The commands. */
	private String commands;

	/** The file writer. */
	private FileWriter fileWriter;

	/**
	 * Execute.
	 *
	 * @param robot
	 *            the robot
	 * @param commands
	 *            the commands movements of Robot
	 * @param fileWriter
	 *            the file writer
	 * @throws Exception
	 *             the exception
	 */
	public void execute(Robot robot, String commands, FileWriter fileWriter) throws Exception {
		this.robot = robot;
		this.commands = commands;
		this.fileWriter = fileWriter;
		launchRobot(commands);
	}

	/**
	 * Launch robot about needed commands.
	 *
	 * @param commands
	 *            the commands
	 * @throws Exception
	 *             the exception
	 */
	public void launchRobot(String commands) throws Exception {
		for (char separateCommand : commands.toCharArray()) {
			switch (separateCommand) {
			case 'l':
				robot.turnLeft();
				break;
			case 'r':
				robot.turnRight();
				break;
			case 'f':
				writePointsToFile(robot.stepForward());
				break;

			default:
				throw new Exception(
						"It isn't correct command - " + separateCommand + "We need to use only 'l', 'r', 'f'");
			}
		}
	}

	/**
	 * Write points to file.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void writePointsToFile(ByteArrayOutputStream byteArrayOutputStream) throws IOException {
		try {
			fileWriter.write(byteArrayOutputStream.toString());
			fileWriter.flush();
		} catch (IOException e) {
			fileWriter.close();
			throw new IOException(e);
		}
	}
}
