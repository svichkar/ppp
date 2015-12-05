/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocksworkshop;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author mednorcom
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotMockTest {

    
//@InjectMocks private Robot myRobo = null;

    /**
     * Test of getCoordinates method, of class Robot.
     */
    @Test
    public void robotShouldMoveAccordingToProgram() throws IOException {
       
        Robot myRobo = mock(Robot.class);
        myRobo.getCoordinates();
        
        
        /*given(logFile.exists() == false).willReturn(true);
        Program myProgram = new Program(myRobo);
        myProgram.executeCommand("ffflfrflllllfrrrrrf");
        assertEquals(myRobo.getCoordinates(), "5.2");*/
    }

}
