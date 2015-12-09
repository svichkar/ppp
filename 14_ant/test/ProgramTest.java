
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;


/**
 * Created by kozlovskij on 12/4/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProgramTest {

    @Mock
    private Robot robot;
    @InjectMocks
    private Program program;

    @Test
    public void shouldMoveWhenCommandF() {
        program.run("f");
        verify(robot).stepForward();
    }

    @Test
    public void shouldTurnRightWhenCommandR() {
        program.run("r");
        verify(robot).turnRight();
    }

    @Test
    public void shouldTurnLeftWhenCommandL() {
        program.run("l");
        verify(robot).turnLeft();
    }

    @Test
    public void shouldExecuteCommandsInRightOrder() {
        program.run("lffrflfrrfff");
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
}
