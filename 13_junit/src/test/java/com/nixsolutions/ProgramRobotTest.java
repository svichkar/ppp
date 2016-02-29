package com.nixsolutions;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class ProgramRobotTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void shouldGenerateFileWithPathOfRobot() throws IOException {
        // given
        File file = folder.newFile();
        Program program = new Program(file);
        String commands = "lffrflfrrfff";
        String expectedResult = "0,0,E" + "\n"
                + "0,0,N" + "\n"
                + "0,1,N" + "\n"
                + "0,2,N" + "\n"
                + "0,2,E" + "\n"
                + "1,2,E" + "\n"
                + "1,2,N" + "\n"
                + "1,3,N" + "\n"
                + "1,3,E" + "\n"
                + "1,3,S" + "\n"
                + "1,2,S" + "\n"
                + "1,1,S" + "\n"
                + "1,0,S" + "\n";
        // when
        program.manipulateRobot(commands);
        // then
        assertEquals(expectedResult, readFile(file));
    }

    private String readFile(File file) throws IOException {
        byte[] fileContents = null;
        try (FileInputStream stream = new FileInputStream(file)) {
            fileContents = new byte[stream.available()];
            stream.read(fileContents);
        }
        return new String(fileContents, "UTF-8");
    }
}
