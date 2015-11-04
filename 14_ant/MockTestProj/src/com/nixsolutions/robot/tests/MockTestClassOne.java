/**
 * 
 */
package com.nixsolutions.robot.tests;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nixsolutions.robot.main.Programm;
import com.nixsolutions.robot.main.RobotClass;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import static org.mockito.Mockito.verify;

/**
 * @author mixeyes
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTestClassOne {
	@Mock
	private RobotClass robot;

	@InjectMocks
	private Programm program;

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
