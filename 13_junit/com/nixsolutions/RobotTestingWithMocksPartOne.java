package com.nixsolutions;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RobotTestingWithMocksPartOne {
	@Mock
	private Robot robot;
	@InjectMocks
	private Program program;

	@Before
	public void initialize() throws IOException {
		program = new Program("D:\\");
	}

	@Test
	public void t() throws IOException {
		// given
		String command = "f";
		when(robot.getCoordX()).thenReturn(2);
		// when
		program.execute(command);
		// then
		//verify(robot).stepForward();
		Assert.assertEquals(1, program.getRobot().getCoordX());
	}
}
