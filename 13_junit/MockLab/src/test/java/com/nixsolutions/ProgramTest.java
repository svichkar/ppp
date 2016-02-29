package com.nixsolutions;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ProgramTest {

	private Program programTestObj;

	@Before
	public void setUpBeforeTests() {
		programTestObj = new Program();
	}

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Test
	public void shouldMoveAccordingToThePath() throws IOException {
		// given
		File tempFile = tempFolder.newFile("tempFile.txt");
		List<String> listActual = new ArrayList<>();
		List<String> listExpected = new ArrayList<>();
		listExpected.add("Position UP; Coordinates [0;1]");
		listExpected.add("Position UP; Coordinates [0;2]");
		listExpected.add("Position RIGHT; Coordinates [1;2]");
		listExpected.add("Position UP; Coordinates [1;3]");
		listExpected.add("Position DOWN; Coordinates [1;2]");
		listExpected.add("Position DOWN; Coordinates [1;1]");
		listExpected.add("Position DOWN; Coordinates [1;0]");				
		// when
		programTestObj.makeRobotMove("lffrflfrrfff", tempFile);
		try (BufferedReader lineReader = new BufferedReader(new FileReader(tempFile.getPath()))) {
			String lineText = null;
			while ((lineText = lineReader.readLine()) != null) {
				listActual.add(lineText);
			}
		}
		// then
		assertTrue(listActual.equals(listExpected));
	}
}
