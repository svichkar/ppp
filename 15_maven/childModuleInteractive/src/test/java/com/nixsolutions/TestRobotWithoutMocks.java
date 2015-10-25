package com.nixsolutions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestRobotWithoutMocks {
	private Program program;
	private File folderPath;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder(); 
	
	@Before
	public void initialize() throws IOException {
		folderPath = folder.newFolder();
		program = new Program(new Robot(new File(folderPath, "robotLog.log")));
	}

	@Test
	public void shouldMovePlusXWhenCommandF() throws IOException {
		// given
		String command = "f";
		String expectedText = "Coordinate X: 1. Coordinate Y: 0.";
		// when
		program.execute(command);
		// then
		Assert.assertEquals(expectedText, Files.readAllLines(
				new File(folderPath, "robotLog.log").toPath()).get(0));
	}

	@Test
	public void shouldMovePlusYWhenCommandLF() throws IOException {
		// given
		String command = "lf";
		String expectedText = "Coordinate X: 0. Coordinate Y: 1.";
		// when
		program.execute(command);
		// then
		Assert.assertEquals(expectedText, Files.readAllLines(
				new File(folderPath, "robotLog.log").toPath()).get(0));
	}

	@Test
	public void shouldMoveMinusXWhenCommandLLF() throws IOException {
		// given
		String command = "llf";
		String expectedText = "Coordinate X: -1. Coordinate Y: 0.";
		// when
		program.execute(command);
		// then
		Assert.assertEquals(expectedText, Files.readAllLines(
				new File(folderPath, "robotLog.log").toPath()).get(0));
	}
	
	@Test
	public void shouldMoveMinusXWhenCommandRRF() throws IOException {
		// given
		String command = "rrf";
		String expectedText = "Coordinate X: -1. Coordinate Y: 0.";
		// when
		program.execute(command);
		// then
		Assert.assertEquals(expectedText, Files.readAllLines(
				new File(folderPath, "robotLog.log").toPath()).get(0));
	}
	
	@Test
	public void shouldMoveMinusYWhenCommandRF() throws IOException {
		// given
		String command = "rf";
		String expectedText = "Coordinate X: 0. Coordinate Y: -1.";
		// when
		program.execute(command);
		// then
		Assert.assertEquals(expectedText, Files.readAllLines(
				new File(folderPath, "robotLog.log").toPath()).get(0));
	}
}
