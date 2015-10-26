package com.nixsolutions.robot.main;

import java.io.IOException;

public class Program {
	private RobotClass robot;

	public static void main(String[] args) throws IOException {
		Program program = new Program();
		program.setRobot(new RobotClass());
		program.getRobot();
		String algorithm = "lffrflfrrfff";
		for(char move:algorithm.toCharArray()){
			program.moveRobot(move);
		}

	}
	
	public void moveRobot(char move) throws IOException {
		switch (move) {
		case 'l':
			getRobot().turnLeft();
			break;
		case 'r':
			getRobot().turnRight();
			break;
		case 'f':
			getRobot().stepForward();
			break;
		default:
			break;
		}
	
	}

	/**
	 * @return the robot
	 */
	public RobotClass getRobot() {
		return robot;
	}

	/**
	 * @param robot the robot to set
	 */
	public void setRobot(RobotClass robot) {
		this.robot = robot;
	}

}
