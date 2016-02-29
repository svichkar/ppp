package com.nixsolutions;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.io.Writer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RobotTest {
    @Mock
    private Writer writer;
    @InjectMocks
    private Robot robot;
    @Captor
    private ArgumentCaptor<String> captor;

    @Test
    public void writesStatusOnSpawn() throws IOException {
        assertWrittenValueEquals("0,0,E\n");
    }

    private void assertWrittenValueEquals(String string) throws IOException {
        verify(writer, atLeastOnce()).append(captor.capture());
        assertThat(captor.getValue(), equalTo(string));
    }

    @Test
    public void writesStatusOnTurnLeft() throws IOException {
        // given
        String[] expectedResults = { "0,0,N\n", "0,0,W\n", "0,0,S\n", "0,0,E\n", "0,0,N\n" };

        for (String result : expectedResults) {
            // when
            robot.turnLeft();
            // then
            assertWrittenValueEquals(result);
        }
    }

    @Test
    public void writesStatusOnTurnRight() throws IOException {
        // given
        String[] expectedResults = { "0,0,S\n", "0,0,W\n", "0,0,N\n", "0,0,E\n", "0,0,S\n" };

        for (String result : expectedResults) {
            // when
            robot.turnRight();
            // then
            assertWrittenValueEquals(result);
        }
    }

    @Test
    public void writesStatusOnStepForward() throws IOException {
        // when
        robot.stepForward();
        robot.turnRight();
        robot.stepForward();
        robot.turnLeft();
        robot.stepForward();
        // then
        assertWrittenValueEquals("2,-1,E\n");
    }

}
