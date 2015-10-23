package com.nixsolutions.robot;

import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;;

@RunWith(MockitoJUnitRunner.class)
public class MockTestClassOne {
	@Mock
	private RobotClass robot;

	@InjectMocks
	private Program program;

	@Test
	public void isTurnedRight() throws IOException {
		char moveR = 'r';
		program.moveRobot(moveR);
		verify(robot).turnRight();
	}

	@Test
	public void isTurnedLeft() throws IOException {
		char moveL = 'l';
		program.moveRobot(moveL);
		verify(robot).turnLeft();
	}

	@Test
	public void isStepForward() throws IOException {
		char step = 'f';
		program.moveRobot(step);
		verify(robot).stepForward();
	}
}
