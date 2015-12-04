package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunningExp extends Exp {
	private static String workDir;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	private static final Logger lOG = LogManager.getLogger(RunningExp.class.getName());
	
	public static void main(String[] args) {
		lOG.entry("Program is starting...");
		RunningExp run = new RunningExp();
		workDir = System.getProperty("user.dir");
		lOG.debug("Working directory for saving file is - " + workDir);
		run.save("Laba 12_Log", workDir + "\\Laba1.txt");
		lOG.exit();
	}

}
