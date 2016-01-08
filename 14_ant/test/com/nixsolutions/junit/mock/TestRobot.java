package com.nixsolutions.junit.mock;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestRobot {
	private Robot roby;
	private Program controller;
	private List<String> expectedRobotPath;
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Before
	 public void preconditions(){
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
	public void sholudHaveaLogFileWithRightCoordinates() throws IOException {
		//given
		Path tempFolder = folder.newFolder().toPath();
		SaveService saver = new SaveService(tempFolder.toString());
		roby = new Robot(saver);
		controller = new Program(roby);
		//when
		controller.executeCommands("lffrflfrrfff");
		//then
		Path result = Paths.get(tempFolder.toString(), "roboLog.log");
		assertEquals(expectedRobotPath, Files.readAllLines(result));
	}
}
