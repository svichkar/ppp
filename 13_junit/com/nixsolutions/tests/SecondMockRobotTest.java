
import com.nixsolutions.Robot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * Created by rybkinrolla on 05.12.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class SecondMockRobotTest {
    @Mock
    private File pathToWrite;
    @Mock
    private BufferedWriter bf;

    @InjectMocks
    private Robot robot;
    @Captor
    private ArgumentCaptor<String> captor;

    @Before
    public void initializeRobot() throws IOException {
        when(pathToWrite.exists()).thenReturn(true);
        robot.setBufferedWriter(bf);
    }
    @Test
    public void shouldStepForward() throws IOException {
        robot.stepForward();
        verify(bf).write(captor.capture());
        assertEquals(captor.getValue(),"x1y0\n");
    }

    @Test
    public void shouldTurnRightAndStepForward() throws IOException {
        robot.turnRight();
        robot.stepForward();
        verify(bf).write(captor.capture());
        assertEquals(captor.getValue(),"x0y-1\n");
    }
    @Test
    public void shouldTurnLeftAndStepForward() throws IOException {
        robot.turnLeft();
        robot.stepForward();
        verify(bf).write(captor.capture());
        assertEquals(captor.getValue(),"x0y1\n");
    }
}

