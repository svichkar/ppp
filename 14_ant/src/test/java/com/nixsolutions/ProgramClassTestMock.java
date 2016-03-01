package com.nixsolutions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.mockito.BDDMockito.*;

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
    public void isMetodsCall() throws IOException, NoSuchFieldException, IllegalAccessException {
        //given
        File trace = new File("Trace.dat");
        //when
        when(myRobot.getTrace()).thenReturn(new ByteArrayOutputStream());
        myProgram.sendCommands("lffrflfrrfff", fout, myRobot);
        //then
        verify(myRobot, times(7)).stepForward();
        verify(myRobot, times(2)).turnLeft();
        verify(myRobot, times(3)).turnRight();
    }
}
