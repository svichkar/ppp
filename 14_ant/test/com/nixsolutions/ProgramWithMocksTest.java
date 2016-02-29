package com.nixsolutions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.times;

import java.io.FileOutputStream;

@RunWith(MockitoJUnitRunner.class)
public class ProgramWithMocksTest {

    @Mock private Robot robot;
    @Mock FileOutputStream fos;
    @InjectMocks private Program program;

    @Test
    public void shouldProgramCorrectMovesRobot() {
        //given
        InOrder order = Mockito.inOrder(robot);
        //when
        program.executeCommand("llffrrff");
        //then
        order.verify(robot, times(2)).turnLeft();
        order.verify(robot, times(2)).stepForward();
        order.verify(robot, times(2)).turnRight();
        order.verify(robot, times(2)).stepForward();
    }

}
