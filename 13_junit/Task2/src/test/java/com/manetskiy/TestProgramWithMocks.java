package com.manetskiy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import java.io.*;

@RunWith(MockitoJUnitRunner.class)
public class TestProgramWithMocks {

    @Mock
    private FileOutputStream out;
    @Mock
    private Robot robot;
    @InjectMocks
    private Program program;


    @Test
    public void shouldExecuteCommands() throws IOException {
        //given
        //when
        program.executeCommand("lfrfQrrflf");
        //then
        verify(robot, times(4)).stepForward();
        verify(robot, times(3)).turnRight();
        verify(robot, times(2)).turnLeft();

    }
}
