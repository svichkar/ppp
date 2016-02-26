package com.nixsolutions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProgramWithMocksTest {

    @Mock
    private Robot robot;
    @InjectMocks
    private Program program;

    @Test
    public void shouldRobotCorrectMove() {
        //given
        given(program.writeToFile(null)).willReturn();
        //when
        //then
    }

}
