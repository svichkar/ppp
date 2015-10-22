package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.nixsolutions.Position.direction;

public class TestingWithoutMocks {

	private Program program;
	private Robot robot;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void Init() throws IOException {
		robot = new Robot();
		program = new Program(robot);
		File tempFolder = folder.getRoot();
		robot.setFolderForLog(tempFolder);
	}

	@Test
	public void shouldTurnLeft() throws Exception {
		// given
		String cmdLeft = "l";
		// when
		program.executeProgram(cmdLeft);
		// then
		Assert.assertEquals(direction.positiveY, robot.getPosition()
				.getDirection());
		Position positionFromFile = getPositionFromFile(robot.getLogFile());
		Assert.assertEquals(direction.positiveY,
				positionFromFile.getDirection());
	}

	@Test
	public void shouldTurnRight() throws Exception {
		// given
		String cmdRight = "r";
		// when
		program.executeProgram(cmdRight);
		// then
		Assert.assertEquals(direction.negativeY, robot.getPosition()
				.getDirection());
		Position positionFromFile = getPositionFromFile(robot.getLogFile());
		Assert.assertEquals(direction.negativeY,
				positionFromFile.getDirection());
	}

	@Test
	public void shouldMoveForward() throws Exception {
		// given
		String cmdForward = "f";
		// when
		program.executeProgram(cmdForward);
		// then
		Assert.assertEquals(1, robot.getPosition().X());
		Assert.assertEquals(direction.positiveX, robot.getPosition()
				.getDirection());
		Position positionFromFile = getPositionFromFile(robot.getLogFile());
		Assert.assertEquals(1, positionFromFile.X());
		Assert.assertEquals(direction.positiveX,
				positionFromFile.getDirection());
	}

	@Test
	public void shouldMoveForwardAndTurnRight() throws Exception {
		// given
		String cmdForwardandRight = "fr";
		// when
		program.executeProgram(cmdForwardandRight);
		// then
		Assert.assertEquals(1, robot.getPosition().X());
		Assert.assertEquals(direction.negativeY, robot.getPosition()
				.getDirection());
		Position positionFromFile = getPositionFromFile(robot.getLogFile());
		Assert.assertEquals(1, positionFromFile.X());
		Assert.assertEquals(direction.negativeY,
				positionFromFile.getDirection());
	}

	@Test
	public void shouldStayAtTheSamePlaceIfNotReady() throws Exception {
		// given
		String cmdTurnRightTurnRightAndForward = "rrf";
		robot.setReadyToGo(false);
		// when
		program.executeProgram(cmdTurnRightTurnRightAndForward);
		// then
		Assert.assertEquals(0, robot.getPosition().X());
		Assert.assertEquals(direction.positiveX, robot.getPosition()
				.getDirection());
		Position positionFromFile = getPositionFromFile(robot.getLogFile());
		Assert.assertEquals(0, positionFromFile.X());
		Assert.assertEquals(direction.positiveX,
				positionFromFile.getDirection());
	}

	@Test
	public void should2TurnRightAndForward() throws Exception {
		// given
		String cmdTurnRightTurnRightAndForward = "rrf";
		// when
		program.executeProgram(cmdTurnRightTurnRightAndForward);
		// then
		Assert.assertEquals(-1, robot.getPosition().X());
		Assert.assertEquals(direction.negativeX, robot.getPosition()
				.getDirection());
		Position positionFromFile = getPositionFromFile(robot.getLogFile());
		Assert.assertEquals(-1, positionFromFile.X());
		Assert.assertEquals(direction.negativeX,
				positionFromFile.getDirection());
	}

	@Test
	public void shouldShowPassedDistance() throws Exception {
		// given
		String cmd2TurnRightAnd5Forward = "rrfffff";
		// when
		program.executeProgram(cmd2TurnRightAnd5Forward);
		// then
		Assert.assertEquals(5, robot.passedDistance());
	}

	@Test
	public void shouldNotShowPassedDistanceIfNotReady() throws IOException {
		// given
		robot.setReadyToGo(false);
		String cmd2TurnRightAnd5Forward = "rrffffff";
		// when
		program.executeProgram(cmd2TurnRightAnd5Forward);
		// then
		Assert.assertEquals(0, robot.passedDistance());
	}

	@Test
	public void shouldReturnCorrectStatusAfterDefiningIt() throws IOException {
		// given
		boolean newStatus = false;
		// when
		robot.setReadyToGo(newStatus);
		// then
		Assert.assertFalse(robot.isReadyToGo());
	}

	@Test
	public void shouldNotMoveForEmptyProgram() throws Exception {
		// given
		String cmdEmpty = "";
		// when
		program.executeProgram(cmdEmpty);
		// then
		Assert.assertEquals(0, robot.getPosition().X());
		Assert.assertEquals(0, robot.getPosition().Y());
		Position positionFromFile = getPositionFromFile(robot.getLogFile());
		Assert.assertEquals(0, positionFromFile.X());
		Assert.assertEquals(0, positionFromFile.Y());
	}

	private Position getPositionFromFile(File log) throws Exception {
		//define zero position
		Position parsedPosition = new Position();
		parsedPosition.setDirection(direction.positiveX);
		//
		File logFile = log;
		// get all lines
		if (logFile.exists()) {
			try {
				List<String> alllines = FileUtils.readLines(logFile);
				// get the latest line from log file
				String lastLine = alllines.get(alllines.size() - 1);

				String coodinates = lastLine.substring(
						lastLine.indexOf('(') + 1, lastLine.indexOf(')'));
				String direction = lastLine.substring(
						lastLine.indexOf("Direction:") + 10,
						lastLine.lastIndexOf(';'));
				// fill the result position
				if (coodinates.length() > 0 & direction.length() > 0) {
					String[] xy = coodinates.split(",");
					parsedPosition.setX(Integer.valueOf(xy[0].trim()));
					parsedPosition.setY(Integer.valueOf(xy[1].trim()));
					parsedPosition
							.setDirection(com.nixsolutions.Position.direction
									.valueOf(direction.trim()));
				}
			} catch (IOException ex) {
				throw new IOException(ex);
			} catch (Exception ex) {
				throw new Exception(ex);
			}
		}
		return parsedPosition;
	}
}
