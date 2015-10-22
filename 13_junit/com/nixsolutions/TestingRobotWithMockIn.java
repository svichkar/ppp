package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nixsolutions.Position.direction;

@RunWith(MockitoJUnitRunner.class)
public class TestingRobotWithMockIn {
	@Mock
	private BufferedWriter writer;
	@InjectMocks
	private Robot r2d3;

	@Test
	public void shouldTurnLeft() throws IOException {
		// when
		r2d3.turnLeft();
		// then
		Assert.assertEquals(direction.positiveY, r2d3.getPosition()
				.getDirection());
		// check that file was not created
		Assert.assertFalse(r2d3.getLogFile().exists());
	}

	@Test
	public void shouldTurnRight() throws Exception {
		// when
		r2d3.turnRight();
		// then
		Assert.assertEquals(direction.negativeY, r2d3.getPosition()
				.getDirection());
		// check that file was not created
		Assert.assertFalse(r2d3.getLogFile().exists());
	}

	@Test
	public void shouldTurnLeftAndGoForward() throws IOException {
		// when
		r2d3.turnLeft();
		r2d3.stepForward();
		// then
		Assert.assertEquals(direction.positiveY, r2d3.getPosition()
				.getDirection());
		Assert.assertEquals(1, r2d3.getPosition().Y());
		// check that file was not created
		Assert.assertFalse(r2d3.getLogFile().exists());
	}

	@Test
	public void should2TurnLeftAnd2GoForward() throws IOException {
		// when
		r2d3.turnLeft();
		r2d3.turnLeft();
		r2d3.stepForward();
		r2d3.stepForward();
		// then
		Assert.assertEquals(direction.negativeX, r2d3.getPosition()
				.getDirection());
		Assert.assertEquals(-2, r2d3.getPosition().X());
		// check that file was not created
		Assert.assertFalse(r2d3.getLogFile().exists());
	}

}
