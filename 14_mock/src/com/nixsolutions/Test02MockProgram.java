package com.nixsolutions;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Test02MockProgram {
    @Mock
    private Robot robot;
    @InjectMocks
    private Program prog;

    @Test
    public void shouldCallAppropriateMethodsOfRobot() throws IOException {
	prog.moveRobot("lffrflfrrfff");
	verify(robot, times(2)).turnLeft();
	verify(robot, times(3)).turnRight();
	verify(robot, times(7)).stepForward();
    }
}
