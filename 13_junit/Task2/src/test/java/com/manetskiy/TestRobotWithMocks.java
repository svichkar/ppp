package com.manetskiy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStream;

@RunWith(MockitoJUnitRunner.class)
public class TestRobotWithMocks {

    @Mock
    OutputStream out;

    @InjectMocks
    private Robot robot;

    @Test
    public void shouldWriteItsLocationWhenDoesStepForward() throws IOException {
        //given
        robot.setOutputStream(out);
        //when
        robot.stepForward();
        //then
        verify(out, times(1)).write(robot.currentPosition().getBytes());

    }
}
