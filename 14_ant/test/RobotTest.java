
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by kozlovskij on 12/4/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotTest {

    @Mock
    private OutputStream stream;

    @InjectMocks
    private Robot robot;

    @Captor
    private ArgumentCaptor<byte []> captor;

    @Test
    public void shouldMoveForwardAlongX () throws IOException {
        byte[] expectedResults = "1.0\n".getBytes();
        byte[] actualResults;

        robot.stepForward();

        verify(stream).write(captor.capture());
        actualResults = captor.getValue();
        Assert.assertArrayEquals(expectedResults,actualResults);
    }

    @Test
    public void shouldMoveBackAlongX () throws IOException {
        byte[] expectedResults = "-1.0\n".getBytes();
        byte[] actualResults;

        robot.turnLeft();
        robot.turnLeft();
        robot.stepForward();

        verify(stream).write(captor.capture());
        actualResults = captor.getValue();
        Assert.assertArrayEquals(expectedResults,actualResults);
    }

    @Test
    public void shouldMoveForwardAlongY () throws IOException {
        byte[] expectedResults = "0.1\n".getBytes();
        byte[] actualResults;

        robot.turnLeft();
        robot.stepForward();

        verify(stream).write(captor.capture());
        actualResults = captor.getValue();
        Assert.assertArrayEquals(expectedResults,actualResults);
    }

    @Test
    public void shouldMoveBackAlongY () throws IOException {
        byte[] expectedResults = "0.-1\n".getBytes();
        byte[] actualResults;

        robot.turnRight();
        robot.stepForward();

        verify(stream).write(captor.capture());
        actualResults = captor.getValue();
        Assert.assertArrayEquals(expectedResults,actualResults);
    }

    @Test
    public void shouldReturnRightCoordinates() throws IOException {
        List<byte[]> expectedResults = new ArrayList<>();
        expectedResults.add("0.1\n".getBytes());
        expectedResults.add("0.2\n".getBytes());
        expectedResults.add("1.2\n".getBytes());
        expectedResults.add("1.3\n".getBytes());
        expectedResults.add("1.2\n".getBytes());
        expectedResults.add("1.1\n".getBytes());
        expectedResults.add("1.0\n".getBytes());
        List<byte[]> actualResults;

        robot.turnLeft();
        robot.stepForward();
        robot.stepForward();
        robot.turnRight();
        robot.stepForward();
        robot.turnLeft();
        robot.stepForward();
        robot.turnRight();
        robot.turnRight();
        robot.stepForward();
        robot.stepForward();
        robot.stepForward();

        verify(stream, times(7)).write(captor.capture());
        actualResults = captor.getAllValues();
        Assert.assertArrayEquals(expectedResults.toArray(),actualResults.toArray());
    }
}
