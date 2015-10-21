package com.nixsolutions;

import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RobotTestingWithMocksPartOne {
	@Mock
	private Robot robot;
	@InjectMocks
	private Program program;

	@Test
	public void shouldMovePlusXWhenCommandF() throws IOException {
		// given
		String command = "f";
		// when
		program.execute(command);
		// then
		verify(robot).stepForward();
	}
	
	@Test
	public void shouldMovePlusYWhenCommandLF() throws IOException {
		// given
		String command = "lf";
		// when
		program.execute(command);
		// then
		verify(robot).turnLeft();
		verify(robot).stepForward();
	}
	
	@Test
	public void shouldMoveMinusXWhenCommandLLF() throws IOException {
		// given
		String command = "llf";
		// when
		program.execute(command);
		// then
		verify(robot, times(2)).turnLeft();
		verify(robot).stepForward();
	}
	
	@Test
	public void shouldMoveMinusXWhenCommandRRF() throws IOException {
		// given
		String command = "rrf";
		// when
		program.execute(command);
		// then
		verify(robot, times(2)).turnRight();
		verify(robot).stepForward();
	}
	
	@Test
	public void shouldMoveMinusYWhenCommandRF() throws IOException {
		// given
		String command = "rf";
		// when
		program.execute(command);
		// then
		verify(robot).turnRight();
		verify(robot).stepForward();
	}
}
