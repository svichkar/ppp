package com.nixsolutions;

import java.io.File;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RobotTestingWithMocksPartOne {
	@Mock private File file;
	@InjectMocks private Program program;
	
	
}
