package com.nixsolutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class Lab13Test {
	private Program program;
	private Robot robot;
	private File file;
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() throws IOException {
		String fullPath = folder.getRoot() + File.separator + Calendar.getInstance().getTimeInMillis() + "_robot.txt";
		file = new File(fullPath);
		robot = new Robot();
		robot.setCustomFileWriter(new FileWriter(file));		
		program = new Program(robot);
	}

	@Test
	public void shouldStepForward() throws Exception {
		program.launchRobot("f");
		String actualText = readFile();
		Assert.assertTrue(actualText.equals("Coordinates: (1, 0); Direction: X"));
	}
	
	
	@Test
	public void shouldTurnRightAndStepForward() throws Exception {
		program.launchRobot("rf");
		String actualText = readFile();
		Assert.assertTrue(actualText.equals("Coordinates: (0, -1); Direction: mY"));
	}
	
	@Test
	public void shouldTurnLeftAndStepForward() throws Exception {
		program.launchRobot("lf");
		String actualText = readFile();
		Assert.assertTrue(actualText.equals("Coordinates: (0, 1); Direction: Y"));
	}
	
	@Test
	public void shouldTwiceTurnLeftAndTwiceStepForward() throws Exception {
		program.launchRobot("llff");
		String actualText = readFile();
		Assert.assertTrue(actualText.endsWith("Coordinates: (-2, 0); Direction: mX"));
	}	
	
	@Test
	public void shouldFourTimesTurnRightAndTwentyTimesStepForward() throws Exception {
		program.launchRobot("rrrrffffffffffffffffffff");
		String actualText = readFile();
		Assert.assertTrue(actualText.endsWith("Coordinates: (20, 0); Direction: X"));
	}

	private String readFile() {
		BufferedReader br = null;
		String toReturn = "";
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				toReturn += line;
			}
		} catch (IOException ex) {
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			} 
		}
		return toReturn;
	}
}
