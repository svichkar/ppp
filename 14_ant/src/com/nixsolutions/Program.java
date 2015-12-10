package com.nixsolutions;

import java.io.IOException;

public class Program {
	private Robot robot;

	public Program(Robot robot) {
		this.robot = robot;
	}

	public void moveRobot(String move) throws IOException {
		char[] moveArray = move.toLowerCase().toCharArray();
		for (int i = 0; i < moveArray.length; i++) {
			switch (moveArray[i]) {
			case 'l':
				robot.turnLeft();
				break;
			case 'r':
				robot.turnRight();
				break;
			case 'f':
				robot.stepForward();
				break;
			default:
				break;
			}
		}

	}

}
