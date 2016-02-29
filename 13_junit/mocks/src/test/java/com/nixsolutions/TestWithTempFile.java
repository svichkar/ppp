package com.nixsolutions;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestWithTempFile {

    String expected;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setupExpected()
    {
        expected = "x = 0; y = 0\n" +
                "x = 1; y = 0\n" +
                "x = 2; y = 0\n" +
                "x = 2; y = 1\n" +
                "x = 2; y = 2\n" +
                "x = 3; y = 2\n" +
                "x = 4; y = 2\n" +
                "x = 4; y = 1\n" +
                "x = 4; y = 0\n" +
                "x = 3; y = 0\n" +
                "x = 2; y = 0\n";
    }

    @Test
         public void checkProgramWriteCorrectTrackToFile() throws IOException {
        //given
        File outFile = folder.newFile("robotTrack.txt");
        Program program = new Program();
        program.setFile(outFile);
        program.setupRobot();

        //when
        program.setupRobotProgram("fflffrfflllffrff");
        program.closeStream();

        //then
        FileReader fileReader = new FileReader(outFile);
        char[] buffer = new char[(int)outFile.length()];
        fileReader.read(buffer);
        new String(buffer);
        assertEquals(expected, new String(buffer));
    }
}
