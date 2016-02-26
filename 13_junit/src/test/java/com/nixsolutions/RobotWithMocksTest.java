package com.nixsolutions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;


@RunWith(MockitoJUnitRunner.class)
public class RobotWithMocksTest {

    @Mock private ByteArrayOutputStream byteArray;
    @InjectMocks private Robot robot;

    @Test
    public void shouldRobotCorrectMove() {
        //given
        Coordinate etalon = new Coordinate(2, 2);
        //when
        robot.stepForward();
        robot.stepForward();
        robot.turnLeft();
        robot.stepForward();
        robot.stepForward();
        //then
        Assert.assertEquals(etalon, robot.getCurCoordinate());
    }

}