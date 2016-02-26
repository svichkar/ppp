package com.nixsolutions;

import main.java.com.nixsolutions.Robot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by sobolenko on 2/26/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotClassTestMock {

    @Mock
    ByteArrayOutputStream trace;

    @InjectMocks
    Robot myRobot;

    @Test
    public void isMovementCorrect() throws IOException, NoSuchFieldException, IllegalAccessException {
        String sample = "2,-1,90";
        myRobot.stepForward();
        myRobot.stepForward();
        myRobot.turnRight();
        myRobot.stepForward();
        myRobot.turnLeft();
        assertEquals(sample, myRobot.getCurrentPosition());
    }
}
