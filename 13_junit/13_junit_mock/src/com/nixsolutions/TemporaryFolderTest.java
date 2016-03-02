package com.nixsolutions;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The Class TemporaryFolderTest.
 */
public class TemporaryFolderTest {

	/** The folder. */
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	/**
	 * Should be correct datain file.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void shouldBeCorrectDataInFile() throws Exception {
		// given
		String expectedData = "[X: 0 and Y: 1 ||| X: 0 and Y: 2 ||| X: 1 and Y: 2 ||| X: 1 and Y: 3 ||| X: 1 and Y: 2 ||| X: 1 and Y: 1 ||| X: 1 and Y: 0 ||| ]";
		Program program = new Program();
		Path path = Paths.get(folder.newFolder().toURI()).resolve("PointsMovement.txt");
		FileWriter fileWriter = new FileWriter(path.toFile());
		// Robot robot = new Robot(new ByteArrayOutputStream());
		Robot robot = new Robot();
		program.execute(robot, "lffrflfrrfff", fileWriter);
		// when
		String actualData = Files.readAllLines(path).toString();
		// then
		assertEquals(expectedData, actualData);
	}
}
