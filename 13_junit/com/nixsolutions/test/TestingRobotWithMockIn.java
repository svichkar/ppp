package com.nixsolutions.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nixsolutions.src.Position.direction;
import com.nixsolutions.src.Robot;

@RunWith(MockitoJUnitRunner.class)
public class TestingRobotWithMockIn {
	@Mock
	private File file;
	@Mock
	private BufferedWriter bw;
	@InjectMocks
	private Robot r2d3;

	@Before
	public void Init() {
		r2d3 = new Robot(file);
		r2d3.setWriter(bw);
	}

	@Test
	public void shouldTurnLeft() throws IOException {
		// when
		r2d3.turnLeft();
		// then
		Assert.assertEquals(direction.positiveY, r2d3.getPosition()
				.getDirection());
	}

	@Test
	public void shouldTurnRight() throws Exception {
		// when
		r2d3.turnRight();
		// then
		Assert.assertEquals(direction.negativeY, r2d3.getPosition()
				.getDirection());
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
	}
	
	@Test
	public void should5TurnRightAnd3GoForward() throws IOException {
		// when
		r2d3.turnRight();
		r2d3.turnRight();
		r2d3.turnRight();
		r2d3.turnRight();
		r2d3.turnRight();
		r2d3.stepForward();
		r2d3.stepForward();
		r2d3.stepForward();
		// then
		Assert.assertEquals(direction.negativeY, r2d3.getPosition()
				.getDirection());
		Assert.assertEquals(-3, r2d3.getPosition().Y());
	}
	
	@Test
	public void should5GoForward() throws IOException {
		// when
		r2d3.stepForward();
		r2d3.stepForward();
		r2d3.stepForward();
		r2d3.stepForward();
		r2d3.stepForward();
		// then
		Assert.assertEquals(direction.positiveX, r2d3.getPosition()
				.getDirection());
		Assert.assertEquals(5, r2d3.getPosition().X());
	}

}
