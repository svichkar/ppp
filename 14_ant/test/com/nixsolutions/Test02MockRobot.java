package com.nixsolutions;

import java.io.FileWriter;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Test02MockRobot {
    private Robot robot;
    @Mock
    private FileWriter mockFw = mock(FileWriter.class);

    @Before
    public void initialize() throws IOException {
	robot = new Robot();
    }

    @Test
    public void robotShouldTurnLeftThreeTimesAndOneStepForward() throws IOException {
	robot.setFw(mockFw);
	robot.turnLeft();
	robot.turnLeft();
	robot.turnLeft();
	robot.stepForward();
	robot.closeFw();
	Assert.assertEquals(180, robot.getDirection());
	Assert.assertEquals(-1, robot.getCoordinateY());
	Assert.assertEquals(0, robot.getCoordinateX());
    }

    @Test
    public void shouldMoveAccordingToCommandsLineAndWriteCoordinatesIntoFile() throws IOException {
	robot.setFw(mockFw);
	Program program = new Program(robot);
	program.moveRobot("lfrf");
	verify(mockFw).write("0;1\r\n");
	verify(mockFw).write("1;1\r\n");
	verify(mockFw, times(2)).flush();
	verify(mockFw).close();
    }
}
