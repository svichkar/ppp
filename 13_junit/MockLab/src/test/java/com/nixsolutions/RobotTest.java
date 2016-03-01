package com.nixsolutions;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class RobotTest {

	@Test
	public void shouldChangeCoordsAccordingToMovements() {
		// given
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		Robot robotTestObj = new Robot(outStream);
		List<String> listExpected = new ArrayList<>();
		listExpected.add("Position RIGHT; Coordinates [1;0]");
		listExpected.add("Position RIGHT; Coordinates [2;0]");
		listExpected.add("Position DOWN; Coordinates [2;-1]");
		listExpected.add("Position DOWN; Coordinates [2;-2]");
		listExpected.add("Position DOWN; Coordinates [2;-3]");
		// when
		robotTestObj.turnLeft();
		robotTestObj.turnLeft();
		robotTestObj.turnLeft();
		robotTestObj.turnLeft();
		robotTestObj.stepForward();
		robotTestObj.stepForward();
		robotTestObj.turnRight();
		robotTestObj.stepForward();
		robotTestObj.turnRight();
		robotTestObj.turnRight();
		robotTestObj.turnRight();
		robotTestObj.turnRight();
		robotTestObj.stepForward();
		robotTestObj.stepForward();
		List<String> listActual = new ArrayList<>(Arrays.asList(outStream.toString().split("\\r?\\n")));
		// then
		assertTrue(listActual.equals(listExpected));
	}
}
