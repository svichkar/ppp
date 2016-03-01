package com.nixsolutions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * Created by sobolenko on 2/26/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProgramClassTestMock {

    @Mock
    Robot myRobot;

    @InjectMocks
    Program myProgram;

    @Test
    public void isMetodsCall() throws IOException, NoSuchFieldException, IllegalAccessException {
        //given
        //when
        myProgram.executeCommand("lffrflfrrfff");
        //then
        InOrder orderedExecution = Mockito.inOrder(this.myRobot);
        orderedExecution.verify(myRobot).turnLeft();
        orderedExecution.verify(myRobot).stepForward();
        orderedExecution.verify(myRobot).stepForward();
        orderedExecution.verify(myRobot).turnRight();
        orderedExecution.verify(myRobot).stepForward();
        orderedExecution.verify(myRobot).stepForward();
        orderedExecution.verify(myRobot).stepForward();
        orderedExecution.verify(myRobot).stepForward();
        verify(myRobot, times(7)).stepForward();
        verify(myRobot, times(2)).turnLeft();
        verify(myRobot, times(3)).turnRight();
    }
}
