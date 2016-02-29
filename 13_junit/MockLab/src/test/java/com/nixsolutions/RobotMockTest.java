package com.nixsolutions;

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.io.ByteArrayOutputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RobotMockTest {

	@Mock
	private ByteArrayOutputStream streamMockObj;

	@InjectMocks
	private Robot robotTestObj;

	@Test
	public void shouldChangeCoordsAccordingToMovements() {
		// when
		robotTestObj.turnLeft();
		robotTestObj.turnLeft();
		robotTestObj.stepForward();
		robotTestObj.stepForward();
		robotTestObj.turnRight();
		robotTestObj.stepForward();
		// then
		assertEquals(robotTestObj.getCoords(), new Point(-2, 1));
	}
}
