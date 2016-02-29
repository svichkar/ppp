package com.nixsolutions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.FileWriter;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class TestsProgramWithMocks {

    String expected;

    @Mock
    private FileWriter fileWriter;

    @Mock
    private Robot robot;

    @InjectMocks
    private Program program;

    @Before
    public void setupExpected()
    {
        expected = "x = 0; y = 0\n" +
                "x = 1; y = 0\n"+
                "x = 2; y = 0\n"+
                "x = 2; y = -1\n"+
                "x = 2; y = -2\n"+
                "x = 3; y = -2\n"+
                "x = 4; y = -2\n";
    }

    @Test
    public void shouldCallMethodsSpecifiedCountOfTimes() throws IOException{
        //given
        program.setupRobotProgram("fflfrrrflflfl");
        //when

        //then
        verify(program.getRobot(), times(6)).stepForward();
        verify(program.getRobot(), times(3)).turnRight();
        verify(program.getRobot(), times(4)).turnLeft();
    }
}
