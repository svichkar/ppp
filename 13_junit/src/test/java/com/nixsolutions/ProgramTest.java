package com.nixsolutions;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProgramTest {
    @Mock
    private Robot robot;

    @InjectMocks
    private Program program;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void manipulatesRobotAccordingToSequenceOfCommandCodes()
            throws IOException {
        // when
        program.manipulateRobot(robot, "lrfrRRRrfFflllLLlff");
        // then
        InOrder inOrder = inOrder(robot);
        inOrder.verify(robot).turnLeft();
        inOrder.verify(robot).turnRight();
        inOrder.verify(robot).stepForward();
        inOrder.verify(robot, times(5)).turnRight();
        inOrder.verify(robot, times(3)).stepForward();
        inOrder.verify(robot, times(6)).turnLeft();
        inOrder.verify(robot, times(2)).stepForward();
    }

    @Test
    public void failsOnIllegalCommandCode() throws IOException {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Illegal command code: [s]");
        program.manipulateRobot(robot, "s");
    }

    @Test
    public void failsOnNullCommandCode() throws IOException {
        exception.expect(NullPointerException.class);
        program.manipulateRobot(robot, null);
    }

}
