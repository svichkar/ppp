package com.nixsolutions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by sobolenko on 2/26/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotClassTestMock {

    @Test
    public void isMovementCorrect() throws IOException, NoSuchFieldException, IllegalAccessException {
        //given
        Robot myRobot = new Robot(new ByteArrayOutputStream());
        String sample = "3,-1,90";
        //when
        myRobot.stepForward();
        myRobot.stepForward();
        myRobot.turnRight();
        myRobot.stepForward();
        myRobot.turnLeft();
        myRobot.stepForward();
        //then
        assertEquals(sample, myRobot.getTrace().toString().replace("\n", ""));
    }

    @Test
    public void isRotate360Correct() throws IOException, NoSuchFieldException, IllegalAccessException {
        //given
        Robot myRobot = new Robot(new ByteArrayOutputStream());
        String sample = "0,0,90";
        //when
        myRobot.turnRight();
        myRobot.turnRight();
        myRobot.turnRight();
        myRobot.turnRight();
        myRobot.turnLeft();
        myRobot.turnLeft();
        myRobot.turnLeft();
        myRobot.turnLeft();
        //then
        assertEquals(sample, myRobot.getTrace().toString().replace("\n", ""));
    }

    @Test
    public void isReturnToStartCorrect() throws IOException, NoSuchFieldException, IllegalAccessException {
        //given
        Robot myRobot = new Robot(new ByteArrayOutputStream());
        String sample = "0,0,270";
        //when
        myRobot.stepForward();
        myRobot.turnLeft();
        myRobot.stepForward();
        myRobot.turnLeft();
        myRobot.stepForward();
        myRobot.turnLeft();
        myRobot.stepForward();
        myRobot.turnRight();
        //then
        assertEquals(sample, myRobot.getTrace().toString().replace("\n", ""));
    }
}
