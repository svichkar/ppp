package com.nixsolutions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ProgramTest {

    private ByteArrayOutputStream stream;
    private Robot robot;
    private Program program;

    @Before
    public void init() {
        stream = new ByteArrayOutputStream();
        robot = new Robot();
        program = new Program(robot, stream);
    }

    @After
    public void close() {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void shouldProgramMovesRobotToCoordinateX2Y2() {
        //given
        String expectedCoordinate = new Coordinate(2, 2).toString();

        //when
        program.executeCommand("lffrff");
        String[] coords = stream.toString().split("\\n");
        String currentCoordinate = coords[coords.length - 1];

        //then
        Assert.assertEquals(expectedCoordinate, currentCoordinate);
    }

    @Test
    public void shouldProgramDoNotMoveRobotIfCommandIncorrect() {
        //given
        String command = "ffrrddqqP";
        String expectedCoordinate = new Coordinate(0, 0).toString();

        //when
        program.executeCommand(command);
        String[] coords = stream.toString().split("\\n");
        String currentCoordinate = coords[coords.length - 1];

        //then
        Assert.assertEquals(expectedCoordinate, currentCoordinate);
    }

}
