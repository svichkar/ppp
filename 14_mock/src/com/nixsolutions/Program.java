package com.nixsolutions;

import java.io.IOException;

public class Program {
    private Robot robot;

    public Program(Robot robot) {
	this.robot = robot;
    }

    public void moveRobot(String commandString) throws IOException {
	char[] commandsArray = commandString.toCharArray();
	for (char command : commandsArray) {
	    switch (command) {
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
	robot.closeFw();
    }
}
