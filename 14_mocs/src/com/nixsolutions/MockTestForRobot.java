package com.nixsolutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class MockTestForRobot {

	@Mock
	private FileWriter fw = mock(FileWriter.class);

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void shouldMakeThreeStepsForward() throws IOException {

		Robot robot = new Robot(temporaryFolder.newFile("robotLog.txt"));
		robot.setFw(fw);
		robot.moveOneStepFowdard();
		robot.moveOneStepFowdard();
		robot.moveOneStepFowdard();
		assertEquals(3, robot.getX());

	}
	@Test
	public void shouldTurnRightThreeTimes() throws IOException {

		Robot robot = new Robot(temporaryFolder.newFile("robotLog.txt"));
		robot.setFw(fw);
		robot.changeDirection("r");
		robot.changeDirection("r");
		robot.changeDirection("r");
		assertEquals(1, robot.getDirection());
	}

	@Test
	public void commandShouldBeExecutedAndWrittenToFile() throws IOException {

		File file = temporaryFolder.newFile("robotLog.txt");
		Robot robot = new Robot(file);
		robot.setFw(fw);

		String[] virtualLog = new String[5];
		virtualLog[0] = "Now robot is on x - 0, y - 0 and looks at 2";
		virtualLog[1] = "Now robot is on x - 0, y - 0 and looks at 1";
		virtualLog[2] = "Now robot is on x - 0, y - 1 and looks at 1";
		virtualLog[3] = "Now robot is on x - 0, y - 2 and looks at 1";
		virtualLog[4] = "Now robot is on x - 0, y - 2 and looks at 2";

		robot.changeDirection("l");
		robot.moveOneStepFowdard();
		robot.moveOneStepFowdard();
		robot.changeDirection("r");

		try (BufferedReader reader = new BufferedReader(
				new FileReader(file.getAbsolutePath()))) {

			ArrayList<String> dataFromVirtalLog = new ArrayList<>();

			String line;
			while ((line = reader.readLine()) != null) {

				dataFromVirtalLog.add(line);
			}

			String[] resultsFromVirtFileInArray = dataFromVirtalLog
					.toArray(new String[dataFromVirtalLog.size()]);

			assertArrayEquals(virtualLog, resultsFromVirtFileInArray);
		}
	}
}
