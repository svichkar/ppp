package com.nixsolutions;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nixsolutions.Position.direction;

@RunWith(MockitoJUnitRunner.class)
public class TestingProgramWithRobotMock {

	@Mock
	private Robot r2d3;
	@InjectMocks
	private Program program;

	@Test
	public void shouldTurnLeft() throws IOException {
		// given
		Position p1 = new Position();
		p1.setX(0);
		p1.setY(0);
		p1.setDirection(direction.positiveY);
		String cmdLeft = "l";
		when(r2d3.isReadyToGo()).thenReturn(true);
		when(r2d3.getPosition()).thenReturn(p1);
		// when
		program.executeProgram(cmdLeft);
		// then
		verify(r2d3).turnLeft();
		Assert.assertEquals(direction.positiveY, r2d3
				.getPosition().getDirection());
	}

	@Test
	public void shouldTurnRight() throws IOException {
		// given
		Position p1 = new Position();
		p1.setX(0);
		p1.setY(0);
		p1.setDirection(direction.negativeY);
		String cmdRight = "r";
		when(r2d3.isReadyToGo()).thenReturn(true);
		when(r2d3.getPosition()).thenReturn(p1);
		// when
		program.executeProgram(cmdRight);
		// then
		verify(r2d3).turnRight();
		Assert.assertEquals(direction.negativeY, r2d3
				.getPosition().getDirection());
	}

	@Test
	public void shouldTurnLeftAndGoForward() throws IOException {
		// given
		Position p1 = new Position();
		p1.setX(0);
		p1.setY(1);
		p1.setDirection(direction.positiveY);
		String cmdRightForward = "lf";

		when(r2d3.isReadyToGo()).thenReturn(true);
		when(r2d3.getPosition()).thenReturn(p1);
		// when
		program.executeProgram(cmdRightForward);
		// then
		verify(r2d3).turnLeft();
		verify(r2d3).stepForward();
		Assert.assertEquals(direction.positiveY, r2d3
				.getPosition().getDirection());
		Assert.assertEquals(1, r2d3.getPosition().Y());
	}

	@Test
	public void shouldStayAtTheSamePlaceIfNotReady() throws IOException {
		// given
		Position p1 = new Position();
		p1.setX(0);
		p1.setY(0);
		p1.setDirection(direction.positiveX);
		String cmdRightForward = "rf";

		when(r2d3.isReadyToGo()).thenReturn(false);
		when(r2d3.getPosition()).thenReturn(p1);
		// when
		program.executeProgram(cmdRightForward);
		// then
		verify(r2d3).isReadyToGo();
		Assert.assertEquals(direction.positiveX, r2d3
				.getPosition().getDirection());
		Assert.assertEquals(0, r2d3.getPosition().Y());
	}

	@Test
	public void shouldShowPassedDistance() throws IOException {
		// given
		String cmd2TurnRightAnd5Forward = "rrfffff";
		when(r2d3.passedDistance()).thenReturn(5);
		when(r2d3.isReadyToGo()).thenReturn(true);
		// when
		program.executeProgram(cmd2TurnRightAnd5Forward);
		// then
		verify(r2d3, times(2)).turnRight();
		verify(r2d3, times(5)).stepForward();
		Assert.assertEquals(5, r2d3.passedDistance());
	}

	@Test
	public void shouldReturnCorrectStatusAfterDefiningIt() throws IOException {
		// given
		boolean newStatus = true;
		when(r2d3.isReadyToGo()).thenReturn(newStatus);
		// when
		r2d3.setReadyToGo(newStatus);
		// then
		verify(r2d3).setReadyToGo(newStatus);
		Assert.assertTrue(r2d3.isReadyToGo());
	}
}
