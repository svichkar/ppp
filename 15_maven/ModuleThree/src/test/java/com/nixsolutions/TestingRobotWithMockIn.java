package com.nixsolutions;

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

import com.nixsolutions.Position.direction;
import com.nixsolutions.Robot;

/**
 * Class for testing out robot with mocked writing to file
 * 
 * @author maxb
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class TestingRobotWithMockIn {
	@Mock
	private File file;
	@Mock
	private BufferedWriter bw;
	@InjectMocks
	private Robot r2d3;

	/**
	 * Init robot and define output file
	 * 
	 */
	@Before
	public void Init() {
		r2d3 = new Robot(file);
		r2d3.setWriter(bw);
	}

	/**
	 * Method for turning left
	 * 
	 */
	@Test
	public void shouldTurnLeft() throws IOException {
		// when
		r2d3.turnLeft();
		// then
		Assert.assertEquals(direction.positiveY, r2d3.getPosition()
				.getDirection());
	}

	/**
	 * Method for turning right
	 * 
	 */
	@Test
	public void shouldTurnRight() throws Exception {
		// when
		r2d3.turnRight();
		// then
		Assert.assertEquals(direction.negativeY, r2d3.getPosition()
				.getDirection());
	}

	/**
	 * Method for turning left and go forward
	 * 
	 */
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

	/**
	 * Method for turning left twice and go forward
	 * 
	 */
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

	/**Method for turning right 5 tomes and go forward at 3 steps
	 * 
	 */
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

	/**Maring 5 steps forward
	 * 
	 */
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
