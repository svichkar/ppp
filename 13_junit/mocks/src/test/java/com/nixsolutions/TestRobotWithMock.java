package com.nixsolutions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class TestRobotWithMock {

    @Mock
    private FileWriter fileWriter;

    @InjectMocks
    private Robot robot;

    @Captor
    private ArgumentCaptor<Writer> captor;

    @Test
    public void shouldHaveStartDirectionsRight() throws IOException {
        //given
        Map<String, Integer> coordinates = new HashMap<String, Integer>();
        coordinates.put("X", 1);
        coordinates.put("Y", 0);

        // when
        robot.stepForward();

        // then
        Map<String, Integer> robotCoordinates = robot.getCoordinates();
        assertEquals(robotCoordinates, coordinates);
    }

    @Test
    public void shouldGoToCorrectCoordinatesAfterMovingUsingAllActions() throws IOException {
        //given
        Map<String, Integer> coordinates = new HashMap<String, Integer>();
        coordinates.put("X", 3);
        coordinates.put("Y", 1);

        // when
        robot.stepForward();
        robot.stepForward();
        robot.turnLeft();
        robot.stepForward();
        robot.turnRight();
        robot.stepForward();

        // then
        Map<String, Integer> robotCoordinates = robot.getCoordinates();
        assertEquals(robotCoordinates, coordinates);
    }

    @Test
    public void shouldOutputCoordinatesToWriterStream() throws IOException {
        //given
        List<String> messagesForLog = new ArrayList<>();
        messagesForLog.add("x = " + robot.getCoordinates().get("X") + "; " + "y = " + robot.getCoordinates().get("Y") + "\n");

        // when
        robot.stepForward();
        messagesForLog.add("x = " + robot.getCoordinates().get("X") + "; " + "y = " + robot.getCoordinates().get("Y") + "\n");
        robot.turnRight();
        messagesForLog.add("x = " + robot.getCoordinates().get("X") + "; " + "y = " + robot.getCoordinates().get("Y") + "\n");
        robot.stepForward();
        messagesForLog.add("x = " + robot.getCoordinates().get("X") + "; " + "y = " + robot.getCoordinates().get("Y") + "\n");
        robot.turnLeft();
        messagesForLog.add("x = " + robot.getCoordinates().get("X") + "; " + "y = " + robot.getCoordinates().get("Y") + "\n");
        robot.stepForward();
        messagesForLog.add("x = " + robot.getCoordinates().get("X") + "; " + "y = " + robot.getCoordinates().get("Y") + "\n");

        // then
        for(int i = 0; i< 6; i++) {
            verify(fileWriter).write(messagesForLog.get(i));
        }
    }
}
