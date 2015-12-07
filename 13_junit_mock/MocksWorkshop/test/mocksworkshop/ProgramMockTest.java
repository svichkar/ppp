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
public class ProgramMockTest {

    @Mock
    private Robot mockedRobo;

    @InjectMocks
    private Program myTestProgram = new Program(mockedRobo);

    @Captor
    private ArgumentCaptor<String> logIssueCaptor;

    @Test
    public void programShouldControlRobot() throws IOException {
        myTestProgram.executeCommand("lfffrrrrrflllllf");
        verify(mockedRobo, times(6)).turnLeft();
        verify(mockedRobo, times(5)).turnRight();
        verify(mockedRobo, times(5)).stepForward();
    }

}
