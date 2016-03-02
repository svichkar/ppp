package com.nixsolutions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RobotTest {

    private ByteArrayOutputStream stream;
    private Robot robot;

    @Before
    public void init() {
        stream = new ByteArrayOutputStream();
        robot = new Robot(stream);
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
    public void shouldRobotInCoordinateX2Y2() {
        //given
        Coordinate expected = new Coordinate(2, 2);

        //when
        robot.stepForward();
        robot.stepForward();
        robot.turnLeft();
        robot.stepForward();
        Coordinate lastCoord = robot.stepForward();

        //then
        Assert.assertEquals(expected, lastCoord);
    }

    @Test
    public void shouldRobotWriteToStream() {
        //given
        Coordinate expected = new Coordinate(1, 0);

        //when
        robot.stepForward();
        String coorFromStream = stream.toString().split("\\n")[1];

        //then
        Assert.assertEquals(expected.toString(), coorFromStream);
    }

    @Test
    public void shouldRobotTurnedOn360ThroughTheLeftSide() {
        //given
        Coordinate expected = new Coordinate(1, 0);

        //when
        robot.turnLeft();
        robot.turnLeft();
        robot.turnLeft();
        robot.turnLeft();
        Coordinate coordAfterTurn = robot.stepForward();

        //then
        Assert.assertEquals(expected, coordAfterTurn);
    }

    @Test
    public void shouldRobotTurnedOn360ThroughTheRightSide() {
        //given
        Coordinate expected = new Coordinate(1, 0);

        //when
        robot.turnRight();
        robot.turnRight();
        robot.turnRight();
        robot.turnRight();
        Coordinate coordAfterTurn = robot.stepForward();

        //then
        Assert.assertEquals(expected, coordAfterTurn);
    }

}