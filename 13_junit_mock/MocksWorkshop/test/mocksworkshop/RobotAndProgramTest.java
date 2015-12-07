/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocksworkshop;

import java.io.File;
import java.io.IOException;
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

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getCoordinates method, of class Robot.
     */
    @Test
    public void robotShouldMoveAccordingToProgram() throws IOException {
        Robot myRobo = new Robot(new File(folder.getRoot(), "robo.log").getPath());
        Program myProgram = new Program(myRobo);
        myProgram.executeCommand("ffflfrflllllfrrrrrf");
        assertEquals("5.2", myRobo.getCoordinates());
    }

}
