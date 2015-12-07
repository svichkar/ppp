package com.nixsolutions.junit.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestProgramMockito {

	@Mock
	private Robot rob;

	@InjectMocks
	private Program controller;

	@Test
	public void shouldTriggerCorrectRobotCommands() {
		// when
		controller.executeCommands("lffrflfrrfff");
		// then
		verify(rob, times(7)).stepForward();
		verify(rob, times(2)).turnLeft();
		verify(rob, times(3)).turnRight();
	}
	
	@Test
	public void shouldIgnoreIncorrectCommands() {
		// when
		controller.executeCommands("qwetyuiopasdghjkzxcvbnm");
		// then
		verify(rob, times(0)).stepForward();
		verify(rob, times(0)).turnLeft();
		verify(rob, times(0)).turnRight();
	}

}
