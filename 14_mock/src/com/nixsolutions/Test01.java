package com.nixsolutions;

import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;

public class Test01 {
    private Program prog;
    private Robot robot;
    private File f;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void initialize() throws IOException {
	robot = new Robot();
	f = folder.newFile("logRobot.txt");
	robot.setFw(new FileWriter(f));
	prog = new Program(robot);
    }

    @Test
    public void robotShouldMoveAccordingToInputCommandLineAndFinalCoordinatesShouldBeValid() throws IOException {
	prog.moveRobot("lffrflfrrfff");
	BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
	List<String> coordinatesList = new ArrayList<String>();
	Boolean isEOF = false;
	while (!isEOF) {
	    String nextLine = reader.readLine();
	    if (nextLine != null) {
		coordinatesList.add(nextLine);
	    } else {
		isEOF = true;
	    }
	}
	Assert.assertTrue(coordinatesList.get(coordinatesList.size() - 1).equals("1;0"));
    }
}
