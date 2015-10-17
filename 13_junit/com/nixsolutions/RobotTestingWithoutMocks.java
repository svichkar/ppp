package com.nixsolutions;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RobotTestingWithoutMocks {
	private Program program;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder(); 
	
	@Before
	public void initialize() throws IOException {
		program = new Program(new Robot(folder.newFolder().getAbsolutePath()));
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
}
