package com.nixsolutions.src;

import java.io.File;
import java.io.IOException;

public class RunProgram {

	public static void main(String[] args) throws IOException {
		File logRobot = new File(".//temp//robot.log");
		Robot rb = new Robot(logRobot);
		Program pr = new Program(rb);
		pr.executeProgram("lffrflfrrffff");
	}

}
