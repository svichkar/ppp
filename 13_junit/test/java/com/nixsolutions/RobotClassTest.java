package com.nixsolutions;

import main.java.com.nixsolutions.Program;
import main.java.com.nixsolutions.Robot;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sobolenko on 2/25/2016.
 */
public class RobotClassTest {

    List<String> sample = new ArrayList<>();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void initialize()
    {
        sample.add("1,0,90");
        sample.add("1,0,180");
        sample.add("1,-1,180");
        sample.add("1,-2,180");
        sample.add("1,-2,90");
        sample.add("2,-2,90");
    }

    @Test
    public void fileValidateTest() throws IOException {
        //given
        Program newProgram = new Program();
        Path trace = Paths.get(folder.newFolder().toURI());
        Path finalPath = trace.resolve("Trace.dat");
        Robot myRobot = new Robot(new ByteArrayOutputStream(),newProgram);
        //when
        newProgram.sendCommands("frfflf", finalPath.toFile(),myRobot);
        //than
        assertEquals(sample,Files.readAllLines(finalPath));
    }
}
