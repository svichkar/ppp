package com.nixsolutions;

import java.io.File;

public class Program {

	public static void main(String[] args) {
		
	File file = new File("D:/robotLog.txt");	
	Robot robot = new Robot(file);
	Program pr = new Program();
		pr.runCommand(robot, "lffrflfrrfff");

	}
	
	
	public  void runCommand(Robot robot , String command) {

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
}
