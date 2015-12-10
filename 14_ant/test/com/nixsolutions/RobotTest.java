package com.nixsolutions;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RobotTest {
	private Robot robot;
	private Program program;
	private File file;
	private BufferedWriter bw = null;

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void setUp() throws IOException {
		file = folder.newFile("test.txt");
		bw = Files.newBufferedWriter(file.toPath());
		robot = new Robot(bw);
		program = new Program(robot);
	}

	@Test
	public void testRobot() throws IOException {
		program.moveRobot("lffrflfrrfff");
		List<String> expected = Arrays.asList("angle=90 x=0 y=1", "angle=90 x=0 y=2", "angle=0 x=1 y=2",
				"angle=90 x=1 y=3", "angle=270 x=1 y=2", "angle=270 x=1 y=1", "angle=270 x=1 y=0");
		assertEquals(expected, Files.readAllLines(file.toPath()));
	}

}
