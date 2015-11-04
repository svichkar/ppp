package com.nixsolutions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProgramTest {
	
	@Mock
	private Robot mockRobot;
	@InjectMocks
	private Program program;
	
	@Test
	public void shouldExecuteTurnLeft() throws Exception {		
		program.launchRobot("l");
		verify(mockRobot).turnLeft();
	}
	@Test
	public void shouldExecuteTurnRight() throws Exception {		
		program.launchRobot("r");
		verify(mockRobot).turnRight();
	}
	@Test
	public void shouldExecuteStepForvard() throws Exception {		
		program.launchRobot("f");
		verify(mockRobot).stepForward();
	}
	@Test
	public void shouldExecuteStepForvardTwise() throws Exception {		
		program.launchRobot("ff");
		verify(mockRobot, times(2)).stepForward();
	}
}
