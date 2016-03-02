package com.nixsolutions;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

public class ProgramAndRobotWithoutMocksTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void shouldCheckCorrectRouteWroteToFile() throws IOException {
        //given
        File outputFile = folder.newFile("route.txt");
        FileOutputStream fos = new FileOutputStream(outputFile);
        Robot robot = new Robot();
        Program program = new Program(robot, fos);
        String etalonEndCoord = "Coordinate{x=1.0, y=0.0}";

        //when
        program.executeCommand("lffrflfrrfff");
        String lastLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader(outputFile))) {
            String newLine;
            while ((newLine = br.readLine()) != null) {
                lastLine = newLine;
            }
        } finally {
            fos.close();
        }

        //then
        Assert.assertEquals(etalonEndCoord, lastLine);
    }

}
