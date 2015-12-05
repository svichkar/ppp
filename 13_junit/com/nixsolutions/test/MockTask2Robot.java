
import com.nixsolutions.Robot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kozlovskij on 12/4/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTask2Robot {
    @Mock
    private File file;

    @Mock
    private Writer writer;

    @InjectMocks
    private Robot robot;

    @Captor
    private ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

    @Before
    public void setUp() {
        when(file.exists()).thenReturn(true);
        robot.setFileWriter(writer);
    }

    @Test
    public void shouldMoveForwardAlongX () throws IOException {
        String expectedResults = "1.0\n";
        String actualResults;

        robot.stepForward();

        verify(writer).write(captor.capture());
        actualResults = captor.getValue();
        Assert.assertEquals(expectedResults,actualResults);
    }

    @Test
    public void shouldMoveBackAlongX () throws IOException {
        String expectedResults = "-1.0\n";
        String actualResults;

        robot.turnLeft();
        robot.turnLeft();
        robot.stepForward();

        verify(writer).write(captor.capture());
        actualResults = captor.getValue();
        Assert.assertEquals(expectedResults,actualResults);
    }

    @Test
    public void shouldMoveForwardAlongY () throws IOException {
        String expectedResults = "0.1\n";
        String actualResults;

        robot.turnLeft();
        robot.stepForward();

        verify(writer).write(captor.capture());
        actualResults = captor.getValue();
        Assert.assertEquals(expectedResults,actualResults);
    }

    @Test
    public void shouldMoveBackAlongY () throws IOException {
        String expectedResults = "0.-1\n";
        String actualResults;

        robot.turnRight();
        robot.stepForward();

        verify(writer).write(captor.capture());
        actualResults = captor.getValue();
        Assert.assertEquals(expectedResults,actualResults);
    }

    @Test
    public void shouldReturnRightCoordinates() throws IOException {
        String [] expectedResults = {"0.1\n", "0.2\n", "1.2\n", "1.3\n", "1.2\n", "1.1\n", "1.0\n"};
        List<String> actualResults;

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

        verify(writer, times(7)).write(captor.capture());
        actualResults = captor.getAllValues();
        Assert.assertArrayEquals(expectedResults,actualResults.toArray());
    }
}
