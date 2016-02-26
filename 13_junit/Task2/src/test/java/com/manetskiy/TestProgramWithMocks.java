package com.manetskiy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.junit.Assert.*;

import org.mockito.runners.MockitoJUnitRunner;

import java.io.*;

@RunWith(MockitoJUnitRunner.class)
public class TestProgramWithMocks {

    @Mock private Robot robot;
    @Mock private File file;
    @Mock private ByteArrayOutputStream toRobot;
    @Mock private FileOutputStream toFile;
    @InjectMocks private Program program;


    @Test
    public void shouldLaunchCommands() throws IOException {
        //given
        assertEquals("Hello", program.hello());
        //when

        //program.executeCommand("f");
    }
}
