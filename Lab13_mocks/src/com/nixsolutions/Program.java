package com.nixsolutions;

public class Program {
	
	private Robot robot; 
	
	public Program(Robot robot){
		this.robot = robot;
	}
	
	public void launchRobot(String commandCodes) throws Exception{
		for (char comand : commandCodes.toCharArray()){
			switch (comand) {
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
				throw new Exception("This is not valid command: " + comand + "Please use: l, r, f");
			}
		}
	}

}
