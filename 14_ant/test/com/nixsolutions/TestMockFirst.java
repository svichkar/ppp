package com.nixsolutions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class TestMockFirst {
	@Mock
	private Robot robot;

	@InjectMocks
	private Program program;

	@Test
	public void shouldMoveAccordingToCommands() throws IOException {
		program.moveRobot("lffrflfrrfff");
		InOrder order = inOrder(robot);
		order.verify(robot).turnLeft();
		order.verify(robot, times(2)).stepForward();
		order.verify(robot).turnRight();
		order.verify(robot).stepForward();
		order.verify(robot).turnLeft();
		order.verify(robot).stepForward();
		order.verify(robot, times(2)).turnRight();
		order.verify(robot, times(3)).stepForward();
	}

	@Test
	public void shouldNotMoveWithUnknownCommands() throws IOException {
		program.moveRobot("unknown");
		verify(robot, times(0)).turnLeft();
		verify(robot, times(0)).turnRight();
		verify(robot, times(0)).stepForward();
	}

}
