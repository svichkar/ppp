package com.nixsolutions;

import main.java.com.nixsolutions.Robot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

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
    public void fileValidateTest() throws IOException, NoSuchFieldException, IllegalAccessException {
        String sample = "2,-1";
        myRobot.stepForward();
        myRobot.stepForward();
        myRobot.turnRight();
        myRobot.stepForward();
        Field Xcoordinate = myRobot.getClass().getDeclaredField("Xcoordinate");
        Field Ycoordinate = myRobot.getClass().getDeclaredField("Ycoordinate");
        Xcoordinate.setAccessible(true);
        Ycoordinate.setAccessible(true);
        int XcoordRobot = (int) Xcoordinate.get(myRobot);
        int YcoordRobot = (int) Ycoordinate.get(myRobot);
        assertEquals(sample,XcoordRobot+","+YcoordRobot);
    }
}
