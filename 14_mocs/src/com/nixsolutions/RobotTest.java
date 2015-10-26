package com.nixsolutions;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RobotTest {
	private File file;
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() throws IOException {
		file = folder.newFolder();

	}

	@Test
	public void shouldBeInLastCoordsPoint() throws IOException {
		Robot robot = new Robot(new File(file, "robotLog.txt"));
		String command = "lffrflfrrfff";
		Program pr = new Program();
		pr.runCommand(robot, command);

		List<String> fileData = Files
				.readAllLines(new File(file, "robotLog.txt").toPath());

		assertTrue(fileData.get(fileData.size() - 1)
				.equals("Now robot is on x - 1, y - 0 and looks at 3"));

	}

}
