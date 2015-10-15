package com.nixsolutions;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MockTesting {
	private Program program;
	
	@Before
	public void initialize() {
		program = new Program();
	}
	
	@Test
	public void shouldMovePlusXWhenCommandF() throws IOException {
		//given
		String command = "f";
		//when
		program.execute(command);
		//then
		Assert.assertEquals(1, program.getRobot().getCoordX());
	}
	
	@Test
	public void shouldMovePlusYWhenCommandLF() throws IOException {
		//given
		String command = "lf";
		//when
		program.execute(command);
		//then
		Assert.assertEquals(1, program.getRobot().getCoordY());
	}
}
