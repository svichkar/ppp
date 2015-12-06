package com.nixsolutions.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

/**
 * Created by konstantin on 12/6/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProgramWithMockTest {

    @Mock
    private File file;

    @InjectMocks
    private Robot robot;
    @Captor
    private ArgumentCaptor<File> captor;

    @Test
    public void test () {

        //http://www.vogella.com/tutorials/Mockito/article.html



    }

}
