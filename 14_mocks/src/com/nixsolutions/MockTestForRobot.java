package com.nixsolutions;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockTestForRobot {

	@Mock
	private FileWriter fw = mock(FileWriter.class);

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void shouldMakeThreeStepsForward() throws IOException {

		Robot robot = new Robot(fw);
		robot.moveOneStepFowdard();
		robot.moveOneStepFowdard();
		robot.moveOneStepFowdard();
		assertEquals(3, robot.getX());

	}
	@Test
	public void shouldTurnRightThreeTimes() throws IOException {

		Robot robot = new Robot(fw);
		robot.changeDirection("r");
		robot.changeDirection("r");
		robot.changeDirection("r");
		assertEquals(1, robot.getDirection());
	}

	@Test
	public void commandShouldBeExecutedAndWrittenToFile() throws IOException {

		Robot robot = new Robot(fw);
		robot.changeDirection("l");
		robot.moveOneStepFowdard();
		robot.moveOneStepFowdard();
		robot.changeDirection("r");

		verify(fw).write("Now robot is on x - 0, y - 0 and looks at 2\r\n");
		verify(fw).write("Now robot is on x - 0, y - 0 and looks at 1\r\n");
		verify(fw).write("Now robot is on x - 0, y - 1 and looks at 1\r\n");
		verify(fw).write("Now robot is on x - 0, y - 2 and looks at 1\r\n");
		verify(fw).write("Now robot is on x - 0, y - 2 and looks at 2\r\n");

	}
}
