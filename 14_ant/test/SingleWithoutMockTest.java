import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by svichkar on 12/5/2015.
 */
public class SingleWithoutMockTest {

    private Robot robot;
    private Program program;
    private String fileName;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setup() throws IOException {

        fileName = folder.newFile("coordinates.txt").getPath();
        robot = new Robot(fileName);
        program = new Program(robot);
    }

    @Test
    public void stepForwardShouldMoveFromStartAlongPositiveAxisX() throws IOException {

        program.move("f");

        String actual = extractDataFromFile(fileName);
        String expected = "X: 1; Y: 0\n";
        assertEquals(expected, actual);
    }

    @Test
    public void stepForwardShouldMoveFromStartAlongNegativeAxisX() throws IOException {

        program.move("llf");

        String actual = extractDataFromFile(fileName);
        String expected = "X: -1; Y: 0\n";
        assertEquals(expected, actual);
    }

    @Test
    public void stepForwardShouldMoveFromStartAlongPositiveAxisY() throws IOException {

        program.move("lf");

        String actual = extractDataFromFile(fileName);
        String expected = "X: 0; Y: 1\n";
        assertEquals(expected, actual);
    }

    @Test
    public void stepForwardShouldMoveFromStartAlongNegativeAxisY() throws IOException {

        program.move("rf");

        String actual = extractDataFromFile(fileName);
        String expected = "X: 0; Y: -1\n";
        assertEquals(expected, actual);
    }

    @Test
    public void moveShouldChangeLocationAccordingToCommand() throws IOException {

        program.move("lffrflfrrfff");

        String actual = extractDataFromFile(fileName);
        String expected = "X: 0; Y: 1\n" +
                "X: 0; Y: 2\n" +
                "X: 1; Y: 2\n" +
                "X: 1; Y: 3\n" +
                "X: 1; Y: 2\n" +
                "X: 1; Y: 1\n" +
                "X: 1; Y: 0\n";
        assertEquals(expected, actual);
    }

    private String extractDataFromFile(String fileName) throws IOException {

        String coordinates = "";
        List<String> all = Files.readAllLines(Paths.get(fileName));
        for (String s : all) {
            coordinates += s + "\n";
        }

        return coordinates;
    }
}
