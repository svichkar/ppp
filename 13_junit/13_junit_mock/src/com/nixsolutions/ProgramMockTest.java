package com.nixsolutions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.FileWriter;

/**
 * The Class ProgramMockTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProgramMockTest {

	/** The mock robot. */
	@Mock
	private Robot mockRobot;
	
	/** The mockfile writer. */
	@Mock
	private FileWriter mockfileWriter;
	
	/** The program. */
	@InjectMocks
	private Program program;

	/**
	 * Should be execute all commands step forward of robot.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void shouldBeExecuteAllCommandsStepForwardOfRobot() throws Exception {
		// given
		String command = "lffrflfrrfff";

		// when
		when(mockRobot.writeToStream()).thenReturn(new ByteArrayOutputStream());
		program.execute(mockRobot, command, mockfileWriter);

		// then
		verify(mockRobot, times(7)).stepForward();
	}
}