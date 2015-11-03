package com.nixsolutions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Program {

	public static void main(String[] args) {

		File file = new File("D:/robotLog.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file, true);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		Robot robot = new Robot(fw);
		Program pr = new Program();
		pr.runCommand(robot, "lffrflfrrfff");
		pr.disposeFW(robot);

	}

	public void runCommand(Robot robot, String command) {

		char[] commands = command.toLowerCase().toCharArray();

		for (int i = 0; i < commands.length; i++) {
			switch (commands[i]) {
				case 'l' :
					robot.changeDirection("l");
					break;
				case 'r' :
					robot.changeDirection("r");
					break;
				case 'f' :
					robot.moveOneStepFowdard();
					break;

				default :
					break;
			}

		}
	}
	public void disposeFW(Robot robot) {
		robot.stop();
	}
}
