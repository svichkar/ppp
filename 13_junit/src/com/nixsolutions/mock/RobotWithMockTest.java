package com.nixsolutions.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.awt.*;
import java.io.File;

import static org.mockito.BDDMockito.given;

/**
 * Created by konstantin on 12/6/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class RobotWithMockTest {

    @Mock
    private String fileName;

    @InjectMocks private Robot robot;
    @Captor private ArgumentCaptor<String> captor;

    @Test
    public void test () {

        //given
        robot.stepForward();

        given(robot.getCoordinates()).willReturn(new Point(0,0));


        //http://www.vogella.com/tutorials/Mockito/article.html



    }
}
