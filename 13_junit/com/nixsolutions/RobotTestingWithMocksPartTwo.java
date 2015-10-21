package com.nixsolutions;

import static org.mockito.BDDMockito.*;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RobotTestingWithMocksPartTwo {
	@Mock
	private File file;
	@InjectMocks
	private Robot robot;

	@Before
	public void initialize() throws IOException {
		when(file.exists()).thenReturn(true);
	}

	@Test
	public void shouldMovePlusXWhenCommandF() throws IOException {
		// given
		// when
		robot.stepForward();
		// then
		Assert.assertEquals(1, robot.getCoordX());
	}

	@Test
	public void shouldMovePlusYWhenCommandLF() throws IOException {
		// given
		// when
		robot.turnLeft();
		robot.stepForward();
		// then
		Assert.assertEquals(1, robot.getCoordY());
	}

	@Test
	public void shouldMoveMinusXWhenCommandLLF() throws IOException {
		// given
		// when
		robot.turnLeft();
		robot.turnLeft();
		robot.stepForward();
		// then
		Assert.assertEquals(-1, robot.getCoordX());
	}

	@Test
	public void shouldMoveMinusXWhenCommandRRF() throws IOException {
		// given
		// when
		robot.turnRight();
		robot.turnRight();
		robot.stepForward();
		// then
		Assert.assertEquals(-1, robot.getCoordX());
	}

	@Test
	public void shouldMoveMinusXWhenCommandRF() throws IOException {
		// given
		// when
		robot.turnRight();
		robot.stepForward();
		// then
		Assert.assertEquals(-1, robot.getCoordY());
	}

	/*
	 * @Before public void initialize() throws IOException { program =
	 * mock(Program.class); robot = new Robot("");
	 * when(program.getRobot()).thenReturn(robot); }
	 * 
	 * @Test public void shouldMovePlusXWhenCommandF() throws IOException { //
	 * given String command = "f"; //doNothing().when(program).execute(command);
	 * doAnswer(new Answer<Object>() {
	 * 
	 * @Override public Object answer(InvocationOnMock invocation) throws
	 * IOException { program.getRobot().stepForward(); return null; }
	 * }).when(program).execute("f"); // when program.execute(command); // then
	 * verify(program).execute("f"); Assert.assertEquals(1,
	 * program.getRobot().getCoordX()); }
	 * 
	 * @Test public void shouldMovePlusYWhenCommandLF() throws IOException { //
	 * given String command = "lf"; doAnswer(new Answer<Object>() {
	 * 
	 * @Override public Object answer(InvocationOnMock invocation) throws
	 * IOException { program.getRobot().turnLeft();
	 * program.getRobot().stepForward(); return null; }
	 * }).when(program).execute("lf"); // when program.execute(command); // then
	 * verify(program).execute("lf"); Assert.assertEquals(1,
	 * program.getRobot().getCoordY()); }
	 * 
	 * @Test public void shouldMoveMinusXWhenCommandLLF() throws IOException {
	 * // given String command = "llf"; doAnswer(new Answer<Object>() {
	 * 
	 * @Override public Object answer(InvocationOnMock invocation) throws
	 * IOException { program.getRobot().turnLeft();
	 * program.getRobot().turnLeft(); program.getRobot().stepForward(); return
	 * null; } }).when(program).execute("llf"); // when
	 * program.execute(command); // then verify(program).execute("llf");
	 * Assert.assertEquals(-1, program.getRobot().getCoordX()); }
	 * 
	 * @Test public void shouldMoveMinusXWhenCommandRRF() throws IOException {
	 * // given String command = "rrf"; doAnswer(new Answer<Object>() {
	 * 
	 * @Override public Object answer(InvocationOnMock invocation) throws
	 * IOException { program.getRobot().turnRight();
	 * program.getRobot().turnRight(); program.getRobot().stepForward(); return
	 * null; } }).when(program).execute("rrf"); // when
	 * program.execute(command); // then verify(program).execute("rrf");
	 * Assert.assertEquals(-1, program.getRobot().getCoordX()); }
	 * 
	 * @Test public void shouldMoveMinusYWhenCommandRF() throws IOException { //
	 * given String command = "rf"; doAnswer(new Answer<Object>() {
	 * 
	 * @Override public Object answer(InvocationOnMock invocation) throws
	 * IOException { program.getRobot().turnRight();
	 * program.getRobot().stepForward(); return null; }
	 * }).when(program).execute("rf"); // when program.execute(command); // then
	 * verify(program).execute("rf"); Assert.assertEquals(-1,
	 * program.getRobot().getCoordY()); }
	 */

}
