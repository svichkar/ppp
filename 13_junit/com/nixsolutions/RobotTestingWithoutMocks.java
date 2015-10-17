package com.nixsolutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RobotTestingWithoutMocks {
	private Program program;
	private String folderPath;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder(); 
	
	@Before
	public void initialize() throws IOException {
		folderPath = folder.newFolder().getAbsolutePath();
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
		Assert.assertEquals(expectedText, Files.readAllLines(Paths.get(folderPath, "robotLog.log")).get(0));
	}
}
