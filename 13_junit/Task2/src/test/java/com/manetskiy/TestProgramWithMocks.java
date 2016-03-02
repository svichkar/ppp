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
    public void shouldTurnRobotLeft() throws IOException {
        //given
        //when
        program.executeCommand("l");
        //then
        verify(robot, times(1)).turnLeft();
    }

    @Test
    public void shouldTurnRobotRight() throws IOException {
        //given
        //when
        program.executeCommand("r");
        //then
        verify(robot, times(1)).turnRight();

    }

    @Test
    public void shouldMoveRobotForward() throws IOException {
        //given
        //when
        program.executeCommand("f");
        //then
        verify(robot, times(1)).stepForward();

    }

    @Test
    public void shouldMoveRobotAccordingToChainOfCommands() throws IOException {
        //given
        //when
        program.executeCommand("rflrfrflr");
        //then
        verify(robot, times(3)).stepForward();
        verify(robot, times(2)).turnLeft();
        verify(robot, times(4)).turnRight();
    }

    @Test
    public void shouldNotMoveRobotIfCommandListIsInvalid() throws IOException {
        //given
        //when
        program.executeCommand("z~!@#$%^&*()950qAnb?\\w");
        //then
        verify(robot, never()).stepForward();
        verify(robot, never()).turnLeft();
        verify(robot, never()).turnRight();
    }

    @Test
    public void invalidCommandShouldNotInterruptRobot() throws IOException {
        //given
        //when
        program.executeCommand("fylr");
        verify(robot, times(1)).stepForward();
        verify(robot, times(1)).turnLeft();
        verify(robot, times(1)).turnRight();
    }

    @Test
    public void shouldIgnoreUppercaseCommands() throws IOException {
        //given
        //when
        program.executeCommand("FLFR");
        //then
        verify(robot, never()).stepForward();
        verify(robot, never()).turnLeft();
        verify(robot, never()).turnRight();
    }
}
