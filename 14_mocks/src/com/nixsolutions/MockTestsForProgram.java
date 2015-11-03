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
		try {
			program.runCommand(robot, "lffr");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			program.disposeFW(robot);
		}
		verify(robot, times(1)).changeDirection("l");
		verify(robot, times(2)).moveOneStepFowdard();
		verify(robot, times(1)).changeDirection("r");

	}

}
