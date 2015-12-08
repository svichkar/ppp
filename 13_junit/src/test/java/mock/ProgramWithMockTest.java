package test.java.mock;

import main.java.mock.Program;
import main.java.mock.Robot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by konstantin on 12/6/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProgramWithMockTest {

    @Mock
    private Robot robot;
    @InjectMocks
    private Program program;

    @Test
    public void leftStepOperationShouldTurnLeft() {

        program.move("l");
        verify(robot).turnLeft();
    }

    @Test
    public void rightStepOperationShouldTurnRight() {

        program.move("r");
        verify(robot).turnRight();
    }

    @Test
    public void forwardOperationShouldMoveForwardAccordingToDirection() {

        program.move("f");
        verify(robot).stepForward();
    }

    @Test
    public void combinedOperationShouldChangeDirectionAndLocationAppropriately() {

        InOrder order = inOrder(robot);
        program.move("lffrflfrrfff");

        order.verify(robot).turnLeft();
        order.verify(robot, times(2)).stepForward();
        order.verify(robot).turnRight();
        order.verify(robot).stepForward();
        order.verify(robot).turnLeft();
        order.verify(robot).stepForward();
        order.verify(robot, times(2)).turnRight();
        order.verify(robot, times(3)).stepForward();
    }
}
