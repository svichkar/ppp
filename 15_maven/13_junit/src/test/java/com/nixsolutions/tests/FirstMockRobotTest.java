package com.nixsolutions.tests;

import com.nixsolutions.Program;
import com.nixsolutions.Robot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;


/**
 * Created by rybkinrolla on 04.12.2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class FirstMockRobotTest {
    @Mock
    private Robot robot;
    @InjectMocks
    private Program program;

    @Test
    public void shouldMoveStepForward() throws IOException {
        program.loadProgramInsideRobot("f");
        verify(robot).stepForward();
    }

    @Test
    public void shouldTurnLeft() throws IOException {
        program.loadProgramInsideRobot("l");
        verify(robot).turnLeft();
    }

    @Test
    public void shouldTurnRight() throws IOException {
        program.loadProgramInsideRobot("r");
        verify(robot).turnRight();
    }

    @Test
    public void shouldDoMovesInOrder() throws IOException {
        program.loadProgramInsideRobot("lffrflfrrfff");
        InOrder inOrder = inOrder(robot);
        inOrder.verify(robot).turnLeft();
        inOrder.verify(robot, times(2)).stepForward();
        inOrder.verify(robot).turnRight();
        inOrder.verify(robot).stepForward();
        inOrder.verify(robot).turnLeft();
        inOrder.verify(robot).stepForward();
        inOrder.verify(robot, times(2)).turnRight();
        inOrder.verify(robot, times(3)).stepForward();
    }

    @Test
    public void shouldNotDoAnyMoveWithUndefinedInstruction() throws IOException {
        program.loadProgramInsideRobot("z");
        verify(robot, times(0)).turnRight();
        verify(robot, times(0)).turnLeft();
        verify(robot, times(0)).stepForward();
    }


}
