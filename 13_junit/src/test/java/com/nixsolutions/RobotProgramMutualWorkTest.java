package com.nixsolutions;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by sobolenko on 2/25/2016.
 */
public class RobotProgramMutualWorkTest {

    List<String> sample = new ArrayList<>();

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void initialize() {
        sample.add("0,0,0");
        sample.add("0,1,0");
        sample.add("0,2,0");
        sample.add("0,2,90");
        sample.add("1,2,90");
        sample.add("1,2,0");
        sample.add("1,3,0");
        sample.add("1,3,90");
        sample.add("1,3,180");
        sample.add("1,2,180");
        sample.add("1,1,180");
        sample.add("1,0,180");
    }

    @Test
    public void isOutputFileValid() throws IOException {
        //given
        Path trace = Paths.get(folder.newFolder().toURI()).resolve("Trace.dat");
        FileOutputStream fout = new FileOutputStream(trace.toFile());
        Robot myRobot = new Robot(fout);
        Program newProgram = new Program(fout, myRobot);
        //when
        newProgram.executeCommand("lffrflfrrfff");
        //then
        assertEquals(sample, Files.readAllLines(trace));
    }
}
