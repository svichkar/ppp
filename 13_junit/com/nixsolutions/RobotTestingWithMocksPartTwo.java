package com.nixsolutions;

import static org.mockito.BDDMockito.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RobotTestingWithMocksPartTwo {
	@Mock
	private File file;
	@Mock
	private FileWriter fw;
	@InjectMocks
	private Robot robot;

	@Before
	public void initialize() throws IOException {
		when(file.exists()).thenReturn(true);
		robot.setWriter(fw);
	}

	@Test
	public void shouldMovePlusXWhenCommandF() throws IOException {
		// given
		// when
		robot.stepForward();
		// then
		Assert.assertEquals(1, robot.getCoordX());
	}

	@Test
	public void shouldMovePlusYWhenCommandLF() throws IOException {
		// given
		// when
		robot.turnLeft();
		robot.stepForward();
		// then
		Assert.assertEquals(1, robot.getCoordY());
	}

	@Test
	public void shouldMoveMinusXWhenCommandLLF() throws IOException {
		// given
		// when
		robot.turnLeft();
		robot.turnLeft();
		robot.stepForward();
		// then
		Assert.assertEquals(-1, robot.getCoordX());
	}

	@Test
	public void shouldMoveMinusXWhenCommandRRF() throws IOException {
		// given
		// when
		robot.turnRight();
		robot.turnRight();
		robot.stepForward();
		// then
		Assert.assertEquals(-1, robot.getCoordX());
	}

	@Test
	public void shouldMoveMinusXWhenCommandRF() throws IOException {
		// given
		// when
		robot.turnRight();
		robot.stepForward();
		// then
		Assert.assertEquals(-1, robot.getCoordY());
	}
}
