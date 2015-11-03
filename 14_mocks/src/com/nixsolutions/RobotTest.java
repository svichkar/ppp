package com.nixsolutions;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RobotTest {
	// Junit
	private File file;
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() throws IOException {
		file = folder.newFile("robotLog1.txt");

	}

	@Test
	public void shouldBeInLastCoordsPoint() throws IOException {

		FileWriter fw = new FileWriter(file, true);
		Robot robot = new Robot(fw);
		String command = "lffrflfrrfff";
		Program pr = new Program();
		pr.runCommand(robot, command);
		pr.disposeFW(robot);

		String[] arr = readLines(file.getAbsolutePath());
		assertTrue(arr[arr.length - 1]
				.equals("Now robot is on x - 1, y - 0 and looks at 3"));

	}

	public String[] readLines(String filename) throws IOException {
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		List<String> lines = new ArrayList<String>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			lines.add(line);
		}
		bufferedReader.close();
		return lines.toArray(new String[lines.size()]);
	}

}
