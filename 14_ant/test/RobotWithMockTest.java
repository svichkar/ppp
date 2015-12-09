import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.io.Writer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by konstantin on 12/6/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotWithMockTest {

    @Mock
    private Writer fileWriter;

    @InjectMocks
    private Robot robot;

    @Captor
    private ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

    @Before
    public void setup() {
        robot.setFileWriter(fileWriter);
    }

    @Test
    public void turnLeftDoesNotWriteAnyData() throws IOException {

        robot.turnLeft();
        verify(fileWriter, times(0)).write(any(String.class));
    }

    @Test
    public void turnRightDoesNotWriteAnyData() throws IOException {

        robot.turnRight();
        verify(fileWriter, times(0)).write(any(String.class));
    }

    @Test
    public void stepForwardShouldChangeLocationAlongPositiveAxisX() throws IOException {

        robot.stepForward();
        verify(fileWriter).write(captor.capture());
        assertEquals("X: 1; Y: 0\n", captor.getValue());
    }

    @Test
    public void stepForwardShouldChangeLocationAlongNegativeAxisX() throws IOException {

        robot.turnRight();
        robot.turnRight();
        robot.stepForward();

        verify(fileWriter).write(captor.capture());
        assertEquals("X: -1; Y: 0\n", captor.getValue());
    }

    @Test
    public void stepForwardShouldChangeLocationAlongPositiveAxisY() throws IOException {

        robot.turnLeft();
        robot.stepForward();

        verify(fileWriter).write(captor.capture());
        assertEquals("X: 0; Y: 1\n", captor.getValue());
    }

    @Test
    public void stepForwardShouldChangeLocationAlongNegativeAxisY() throws IOException {

        robot.turnRight();
        robot.stepForward();

        verify(fileWriter).write(captor.capture());
        assertEquals("X: 0; Y: -1\n", captor.getValue());
    }

    @Test
    public void seriesOfCommandsShouldChangeLocationAppropriately() throws IOException {

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

        String expected =   "X: 0; Y: 1\n" +
                            "X: 0; Y: 2\n" +
                            "X: 1; Y: 2\n" +
                            "X: 1; Y: 3\n" +
                            "X: 1; Y: 2\n" +
                            "X: 1; Y: 1\n" +
                            "X: 1; Y: 0\n";

        verify(fileWriter, times(7)).write(captor.capture());

        String actual = "";
        for (String s : captor.getAllValues()) {
            actual += s;
        }

        assertEquals(expected, actual);
    }
}
