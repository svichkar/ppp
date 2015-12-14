package com.nixsolutions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.BufferedWriter;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class TestMockSecond {
	@Mock
	private BufferedWriter bw;
	@InjectMocks
	private Robot robot;
	@Captor
	private ArgumentCaptor<String> captor;

	@Before
	public void setUp() {
		robot = new Robot(bw);
	}

	@Test
	public void shouldWriteCorrectPositionAfterStepForward() throws IOException {
		robot.stepForward();
		verify(bw).write(captor.capture());
		assertEquals(captor.getValue(), "angle=0 x=1 y=0\n");
	}

	@Test
	public void shouldWriteCorrectPositionAfterRotationsAndStepForward() throws IOException {
		robot.turnLeft();
		robot.turnLeft();
		robot.turnRight();
		robot.stepForward();
		verify(bw).write(captor.capture());
		assertEquals(captor.getValue(), "angle=90 x=0 y=1\n");
	}

}
