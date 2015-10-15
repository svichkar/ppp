package com.nixsolutions;

import java.io.IOException;

public class Program {
	private Robot robot;
	
	public Program() {
		//needs to be changed to reflect cross-platformness 
		robot = new Robot("F:\\");
	}
	
	public Program(String pathToDir) {
		robot = new Robot(pathToDir);
	}
	
	public void execute(String command) throws IOException {
		String[] commandArray = command.split("");
		for (String cmd : commandArray) {
			switch (cmd) {
			case "f":
				robot.stepForward();
				break;
			case "l":
				robot.turnLeft();
				break;
			case "r":
				robot.turnRight();
				break;
			default:
				System.out.println("Command '" + cmd + "' is not recognized. Skipping.");
			}
		}
	}
	
	public void setRobot(Robot robot) {
		this.robot = robot;
	}
	
	public Robot getRobot() {
		return robot;
	}
}
