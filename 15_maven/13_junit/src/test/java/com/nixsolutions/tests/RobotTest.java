import com.nixsolutions.Program;
import com.nixsolutions.Robot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rybkinrolla on 04.12.2015.
 */
public class RobotTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void shouldMoveRobotBySteps() throws IOException {
        File coordinatesFile = folder.newFile("coordinates.txt");
        Robot robot = new Robot(coordinatesFile);
        Program program = new Program(robot);
        List<String> testCoordinates = Arrays.asList("x0y1", "x0y2", "x1y2", "x1y3", "x1y2", "x1y1", "x1y0");
        program.loadProgramInsideRobot("lffrflfrrfff");
        List<String> passedRobotCoordinates = Files.readAllLines(coordinatesFile.toPath());
        assertEquals(passedRobotCoordinates,testCoordinates);
    }
}
