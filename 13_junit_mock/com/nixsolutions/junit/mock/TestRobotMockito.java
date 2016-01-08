package com.nixsolutions.junit.mock;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestRobotMockito {
	private Program controller;
	private List<String> expectedRobotPath;

	@Mock
	private SaveService saver;

	@InjectMocks
	private Robot rob;

	@Captor
	private ArgumentCaptor<String> captor;

	@Before
	public void preconditions() {
		controller = new Program(rob);
		expectedRobotPath = new ArrayList<>();
		expectedRobotPath.add("xAxis= 1; yAxis= 0");
		expectedRobotPath.add("xAxis= 2; yAxis= 0");
		expectedRobotPath.add("xAxis= 2; yAxis= 1");
		expectedRobotPath.add("xAxis= 3; yAxis= 1");
		expectedRobotPath.add("xAxis= 2; yAxis= 1");
		expectedRobotPath.add("xAxis= 1; yAxis= 1");
		expectedRobotPath.add("xAxis= 0; yAxis= 1");
	}

	@Test
	public void shouldSaveCorrectCoordinates() {
		// given
		String roboPath = "lffrflfrrfff";
		List<String> actualCoordinates;
		// when
		controller.executeCommands(roboPath);
		// then
		verify(saver, times(7)).save(captor.capture(), any(String.class));
		actualCoordinates = captor.getAllValues();
		assertEquals(expectedRobotPath, actualCoordinates);
	}
}
