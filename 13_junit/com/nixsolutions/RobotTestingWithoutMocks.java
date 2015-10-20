package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RobotTestingWithoutMocks {
	private Program program;
	private File folderPath;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder(); 
	
	@Before
	public void initialize() throws IOException {
		folderPath = folder.newFolder();
		program = new Program(new Robot(folderPath));
	}

	@Test
	public void shouldMovePlusXWhenCommandF() throws IOException {
		// given
		String command = "f";
		// when
		program.execute(command);
		// then
		Assert.assertEquals(1, program.getRobot().getCoordX());
	}

	@Test
	public void shouldMovePlusYWhenCommandLF() throws IOException {
		// given
		String command = "lf";
		// when
		program.execute(command);
		// then
		Assert.assertEquals(1, program.getRobot().getCoordY());
	}

	@Test
	public void shouldMoveMinusXWhenCommandLLF() throws IOException {
		// given
		String command = "llf";
		// when
		program.execute(command);
		// then
		Assert.assertEquals(-1, program.getRobot().getCoordX());
	}
	
	@Test
	public void shouldMoveMinusXWhenCommandRRF() throws IOException {
		// given
		String command = "rrf";
		// when
		program.execute(command);
		// then
		Assert.assertEquals(-1, program.getRobot().getCoordX());
	}
	
	@Test
	public void shouldMoveMinusYWhenCommandRF() throws IOException {
		// given
		String command = "rf";
		// when
		program.execute(command);
		// then
		Assert.assertEquals(-1, program.getRobot().getCoordY());
	}
	
	@Test
	public void shouldWriteToFile() throws IOException {
		// given
		String command = "f";
		String expectedText = "Coordinate X: 1. Coordinate Y: 0.";
		// when
		program.execute(command);
		// then
		Assert.assertEquals(expectedText, Files.readAllLines(
				new File(folderPath, "robotLog.log").toPath()).get(0));
	}
}
