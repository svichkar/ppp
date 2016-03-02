package com.nixsolutions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;

/**
 * The Class RobotMockTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotMockTest {

	/** The mock byte array output stream. */
	@Mock
	private ByteArrayOutputStream mockByteArrayOutputStream;

	/** The robot. */
	@InjectMocks
	private Robot robot;

	/**
	 * Should be correct movement to forvard.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void shouldBeCorrectMovementToForvard() throws Exception {
		// given
		int valueX = 2;
		int valueY = 0;
		int valueAngle = 90;

		// when
		robot.stepForward();
		robot.stepForward();
		robot.turnLeft();
		robot.turnLeft();
		robot.turnRight();

		// then
		Assert.assertEquals(valueX, robot.GetCurrentPointX());
		Assert.assertEquals(valueY, robot.GetCurrentPointY());
		Assert.assertEquals(valueAngle, robot.GetCurrentAngle());
	}

	@Test
	public void shouldBeCorrectTurnOn360() throws Exception {
		// given
		int valueAngle = 0;

		// when
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();
		robot.turnLeft();

		// then
		Assert.assertEquals(valueAngle, robot.GetCurrentAngle());
	}
}