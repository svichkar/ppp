package com.nixsolutions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockTestsForProgram {

	@Mock
	private Robot robot;

	@InjectMocks
	private Program program;

	@Test
	public void shouldMoveAccoringInputCommandlffr() {
		program.runCommand(robot, "lffr");
		verify(robot, times(1)).changeDirection("l");
		verify(robot, times(2)).moveOneStepFowdard();
		verify(robot, times(1)).changeDirection("r");

	}

}
