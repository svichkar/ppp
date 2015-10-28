package com.nixsolutions;

import java.io.FileWriter;
import java.io.IOException;

public class ExecutionMovement {
    public static void main(String[] args) throws IOException {
	Robot robot = new Robot();
	FileWriter fw = new FileWriter("logRobot.txt", true);
	robot.setFw(fw);
	Program prog = new Program(robot);
	prog.moveRobot("lffrflfrrfff");
    }
}
