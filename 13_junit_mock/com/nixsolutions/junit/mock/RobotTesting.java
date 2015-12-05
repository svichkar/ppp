package com.nixsolutions.junit.mock;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RobotTesting {
	Robot rob = new Robot();

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void initRobot() {
		rob = new Robot();
	}

	public void testWithTempFolder() throws IOException {

	}

	@Test
	public void initStateCheck() {
		int initialFacement = rob.getFacement();
		String initialCoordin = rob.getCoordinates();
		assertTrue(initialFacement == 2);
		assertTrue(initialCoordin.equals("xAxis= 0; yAxis= 0"));
	}

	@Test
	public void clockWiseCheck() {
		rob.turnRight();
		assertTrue(rob.getFacement() == 3);
		rob.turnRight();
		assertTrue(rob.getFacement() == 4);
		rob.turnRight();
		assertTrue(rob.getFacement() == 1);
	}

	@Test
	public void counterClockWiseCheck() {
		rob.turnLeft();
		assertTrue(rob.getFacement() == 1);
		rob.turnLeft();
		assertTrue(rob.getFacement() == 4);
	}

	@Test
	public void moveNorth() {
		while (rob.getFacement() != Robot.LOOKING_NORTH) {
			rob.turnRight();
		}

		rob.stepForward();
		assertTrue(rob.getXaxis() == 1);
	}

	@Test
	public void moveSouth() {
		while (rob.getFacement() != Robot.LOOKING_SOUTH) {
			rob.turnLeft();
		}

		rob.stepForward();
		assertTrue(rob.getXaxis() == -1);
	}

	@Test
	public void moveWest() {
		while (rob.getFacement() != Robot.LOOKING_WEST) {
			rob.turnLeft();
		}

		rob.stepForward();
		assertTrue(rob.getYaxis() == -1);
	}

	@Test
	public void moveEast() {
		while (rob.getFacement() != Robot.LOOKING_EAST) {
			rob.turnRight();
		}

		rob.stepForward();
		assertTrue(rob.getYaxis() == 1);
	}

	@Test
	public void complexMovement() {
		char[] robotPath = "lffrflfrrfff".toCharArray();

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

		assertEquals("xAxis= 0; yAxis= 1", rob.getCoordinates());

	}
}
