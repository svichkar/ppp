package com.nixsolutions;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProgramMockTest {

	@Mock
	private Robot robotMockObj;

	@InjectMocks
	private Program programTestObj;

	@Test
	public void shouldCallCorrespondingMethodsIgnoringUnallowableSymbols() {
		// given
		String robotPath = "qlLwlfr666ekkrFuuyRr4rffflLLl1";
		// when
		programTestObj.makeRobotMove(robotPath);
		// then
		InOrder order = inOrder(robotMockObj);
		order.verify(robotMockObj, times(3)).turnLeft();
		order.verify(robotMockObj).stepForward();
		order.verify(robotMockObj, times(2)).turnRight();
		order.verify(robotMockObj).stepForward();
		order.verify(robotMockObj, times(3)).turnRight();
		order.verify(robotMockObj, times(3)).stepForward();
		order.verify(robotMockObj, times(4)).turnLeft();
	}
}