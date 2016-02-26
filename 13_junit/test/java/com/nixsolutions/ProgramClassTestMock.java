package com.nixsolutions;

import main.java.com.nixsolutions.Program;
import main.java.com.nixsolutions.Robot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

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
    public void isMovementCorrect() throws IOException, NoSuchFieldException, IllegalAccessException {
        String sample = "2,-1,90";
        File trace = new File("Trace.dat");
        
        myProgram.sendCommands("frfflf", trace, myRobot);
    }
}
