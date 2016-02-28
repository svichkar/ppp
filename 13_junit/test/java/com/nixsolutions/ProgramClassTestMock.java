package com.nixsolutions;

import main.java.com.nixsolutions.Program;
import main.java.com.nixsolutions.Robot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.mockito.BDDMockito.doNothing;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyString;

/**
 * Created by sobolenko on 2/26/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProgramClassTestMock {

    @Mock
    Robot myRobot;

    @Mock
    FileWriter fout;

    @InjectMocks
    Program myProgram;

    @Test
    public void isMovementCorrect() throws IOException, NoSuchFieldException, IllegalAccessException {
        //given
        File trace = new File("Trace.dat");
        //when
        Mockito.when(myRobot.getTrace()).thenReturn(new ByteArrayOutputStream());
        Mockito.doNothing().when(myRobot).writeToFileCommand();
        myProgram.sendCommands("frfflf", trace, myRobot);
        //then
        verify(myRobot, times(4)).stepForward();
        verify(myRobot, times(1)).turnLeft();
        verify(myRobot, times(1)).turnRight();
    }
}
