package com.nixsolutions;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import java.io.IOException;

import org.junit.Assert;
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
		//given(robot.getCoordX()).willReturn(1);
		// when
		program.execute(command);
		// then
		verify(robot).stepForward();
		//Assert.assertEquals(1, program.getRobot().getCoordX());
	}
	
	@Test
	public void shouldMovePlusYWhenCommandLF() throws IOException {
		// given
		String command = "lf";
		//given(robot.getCoordY()).willReturn(1);
		// when
		program.execute(command);
		// then
		verify(robot).turnLeft();
		verify(robot).stepForward();
		//Assert.assertEquals(1, program.getRobot().getCoordY());
	}
	
	@Test
	public void shouldMoveMinusXWhenCommandLLF() throws IOException {
		// given
		String command = "llf";
		//given(robot.getCoordX()).willReturn(-1);
		// when
		program.execute(command);
		// then
		verify(robot, times(2)).turnLeft();
		verify(robot).stepForward();
		//Assert.assertEquals(-1, program.getRobot().getCoordX());
	}
	
	@Test
	public void shouldMoveMinusXWhenCommandRRF() throws IOException {
		// given
		String command = "rrf";
		//given(robot.getCoordX()).willReturn(-1);
		// when
		program.execute(command);
		// then
		verify(robot, times(2)).turnRight();
		verify(robot).stepForward();
		//Assert.assertEquals(-1, program.getRobot().getCoordX());
	}
	
	@Test
	public void shouldMoveMinusYWhenCommandRF() throws IOException {
		// given
		String command = "rf";
		//given(robot.getCoordY()).willReturn(-1);
		// when
		program.execute(command);
		// then
		verify(robot).turnRight();
		verify(robot).stepForward();
		//Assert.assertEquals(-1, program.getRobot().getCoordY());
	}
}
