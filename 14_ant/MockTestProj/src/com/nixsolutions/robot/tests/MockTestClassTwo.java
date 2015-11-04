package com.nixsolutions.robot.tests;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nixsolutions.robot.main.Programm;
import com.nixsolutions.robot.main.RobotClass;
import com.nixsolutions.robot.main.RobotLogger;

@RunWith(MockitoJUnitRunner.class)
public class MockTestClassTwo {
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private String robotfilePath;
	private Programm program;
	
	
	@Before
	public void initializeRobot() throws IOException {
		robotfilePath =folder.newFile().getAbsolutePath();
		RobotLogger robotLogger = new RobotLogger(robotfilePath);
		program = new Programm();
		program.setRobot(new RobotClass(robotLogger));
	}

	@Test
	public void isTurnedRightFile() throws IOException {
		char moveR = 'r';
		program.moveRobot(moveR);
	    assertEquals(Files.readAllLines(Paths.get(new File(robotfilePath).getAbsolutePath())).get(0),
	        "Robot position X: 0; Y: 0; Course: DOWN");
	}

	@Test
	public void isTurnedLeftFile() throws IOException {
		char moveR = 'l';
		program.moveRobot(moveR);
	    assertEquals(Files.readAllLines(Paths.get(new File(robotfilePath).getAbsolutePath())).get(0),
	        "Robot position X: 0; Y: 0; Course: UP");
	}

	@Test
	public void isStepForwardFile() throws IOException {
		char moveR = 'f';
		program.moveRobot(moveR);
	    assertEquals(Files.readAllLines(Paths.get(new File(robotfilePath).getAbsolutePath())).get(0),
	        "Robot position X: 1; Y: 0; Course: RIGHT");
	}

}
