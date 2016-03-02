package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;

/**
 * The Class MockMain.
 */
public class MockMain {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		Program program = new Program();
		File file = new File("PointsMovement.txt");
		FileWriter fileWriter = new FileWriter(file);
		System.out.println("Starting movements of robot...");
		Robot robot = new Robot();
		program.execute(robot, "lffrflfrrfff", fileWriter);
		System.out.println("Robot has finished his movements!");
	}
}