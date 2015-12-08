/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocksworkshop;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 *
 * @author mednorcom
 */
public class RobotAndProgramTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private File outputLogFile;

    @Before
    public void setUp() {
        outputLogFile = new File(folder.getRoot(), "robo.log");
    }

    /**
     * Test of getCoordinates method, of class Robot.
     */
    @Test
    public void robotShouldMoveAccordingToProgram() throws IOException {
        Robot myRobo = new Robot(new RobotFileLogWriter(outputLogFile.getPath()));
        Program myProgram = new Program(myRobo);
        myProgram.executeCommand("ffflfrflllllfrrrrrf");
        List<String> logIssues =  FileUtils.readLines(outputLogFile);
        assertEquals("Robot position: 1.0", logIssues.get(0));
        assertEquals("Robot position: 2.0", logIssues.get(1));
        assertEquals("Robot position: 3.0", logIssues.get(2));
        assertEquals("Robot position: 3.1", logIssues.get(3));
        assertEquals("Robot position: 4.1", logIssues.get(4));
        assertEquals("Robot position: 4.2", logIssues.get(5));
        assertEquals("Robot position: 5.2", logIssues.get(6));
    }

}
