import com.nixsolutions.Program;
import com.nixsolutions.Robot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;


/**
 * Created by kozlovskij on 12/4/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTask2Program {

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
        program.run("lrfr");
        InOrder inOrder = inOrder(robot);
        inOrder.verify(robot).turnLeft();
        inOrder.verify(robot).turnRight();
        inOrder.verify(robot).stepForward();
        inOrder.verify(robot).turnRight();
    }
}
