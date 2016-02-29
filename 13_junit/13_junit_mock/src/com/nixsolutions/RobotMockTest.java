package com.nixsolutions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

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
	 * @throws Exception the exception
	 */
	@Test
	public void shouldBeCorrectMovementToForvard() throws Exception {		
		//given
		int valueX = 2;
		
		//when
		robot.stepForward();
		robot.stepForward();

		//then
		Assert.assertEquals(valueX, robot.GetCurrentPointX());
	}
}