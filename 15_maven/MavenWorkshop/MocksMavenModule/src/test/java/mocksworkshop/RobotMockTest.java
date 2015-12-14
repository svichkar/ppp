/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocksworkshop;

import java.io.IOException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author mednorcom
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotMockTest {

    @Mock
    private RobotLogWriter moveLogFile;

    @InjectMocks
    private Robot myRobo = new Robot(moveLogFile);

    @Captor
    private ArgumentCaptor<String> logIssueCaptor;

    @Test
    public void robotShouldMoveAccordingToDirection() throws IOException {
        doNothing().when(moveLogFile).writeLog(Matchers.anyString());
        myRobo.stepForward();
        myRobo.turnLeft();
        myRobo.stepForward();
        myRobo.stepForward();
        myRobo.turnRight();
        myRobo.turnRight();
        myRobo.stepForward();
        verify(moveLogFile, times(4)).writeLog(logIssueCaptor.capture());
        assertEquals("Robot position: 1.0\n", logIssueCaptor.getAllValues().get(0));
        assertEquals("Robot position: 1.1\n", logIssueCaptor.getAllValues().get(1));
        assertEquals("Robot position: 1.2\n", logIssueCaptor.getAllValues().get(2));
        assertEquals("Robot position: 1.1\n", logIssueCaptor.getAllValues().get(3));
        assertEquals("1.1", myRobo.getCoordinates());
    }

}
