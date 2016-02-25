package com.nixsolutions;

import main.java.com.nixsolutions.Program;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by sobolenko on 2/25/2016.
 */
public class RobotClassTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void commandExecuteTest() throws IOException {
        Program newProgram = new Program();
        Path trace = Paths.get(folder.newFolder().toURI());
        Path finalPath = trace.resolve("Trace.dat");
        newProgram.sendCommands("frfflf", finalPath.toFile());
    }
}
