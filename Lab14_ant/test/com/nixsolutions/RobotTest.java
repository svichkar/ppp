package com.nixsolutions;

import java.awt.Point;
import java.io.FileWriter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RobotTest {
	@Mock
	private FileWriter mockFileWriter;
	@InjectMocks
	private Robot robot;
	
	private Point pointActual;
	private Direction derectionActual;
	
	@Test
	public void shouldStepForward() throws Exception {
		pointActual = robot.stepForward();
		Assert.assertEquals(new Point(1, 0), pointActual);
	}
	@Test
	public void shouldTurnRight() throws Exception {
		derectionActual = robot.turnRight();
		Assert.assertEquals(Direction.mY, derectionActual);
	}
	@Test
	public void shouldTurnLeft() throws Exception {
		derectionActual = robot.turnLeft();
		Assert.assertEquals(Direction.Y, derectionActual);
	}
	@Test
	public void shouldTwiceTurnLeft() throws Exception {
		robot.turnLeft();
		derectionActual = robot.turnLeft();
		Assert.assertEquals(Direction.mX, derectionActual);
	}
	@Test
	public void shouldTwiceTurnRightAndTwiceStepForvard() throws Exception {
		robot.turnRight();
		derectionActual = robot.turnRight();
		robot.stepForward();
		pointActual = robot.stepForward();		
		Assert.assertEquals(Direction.mX, derectionActual);
		Assert.assertEquals(new Point(-2, 0), pointActual);
	}
}
