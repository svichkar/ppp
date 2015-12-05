package com.nixsolutions.junit.mock;

public class Program {

	private char[] robotPath;
	private Robot rob;

	public Program(Robot robot) {
		rob = robot;
	}

	public void executeCommands(String commands){
		robotPath = commands.toCharArray();
		
		for (char c : robotPath) {
			switch (c) {
			case 'f':
				rob.stepForward();
				break;
			case 'l':
				rob.turnLeft();
				break;
			case 'r':
				rob.turnRight();
				break;
			default:
				break;
			}
		}
	}
}
