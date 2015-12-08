package com.nixsolutions.mock.tests;

import com.nixsolutions.mock.Directions;
import com.nixsolutions.mock.Program;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by svichkar on 12/5/2015.
 */
public class SingleWithoutMockTest {

    private static final Logger LOGGER = LogManager.getLogger(SingleWithoutMockTest.class);
    private com.nixsolutions.mock.Robot robot;
    private Program program;
    private String fileName;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setup() {

        try {
            fileName = folder.newFile("coordinates.txt").getPath();
            robot = new com.nixsolutions.mock.Robot(fileName);
        } catch (IOException e) {
            LOGGER.error("File connot be created.", e);
        }
        program = new Program(robot);
    }

    @Test
    public void stepForwardShouldMoveFromStartAlongPositiveAxisX() throws IOException {

        Point start = robot.getCoordinates();
        program.move("f");

        Point finish = robot.getCoordinates();
        assertEquals(start.x + 1, finish.x);
        assertEquals(start.y, finish.y);

        String actual = "";
        List<String> all =  Files.readAllLines(Paths.get(fileName));
        for (String s : all) {
            actual += s + "\n";
        }

        String expected =   "X: 1; Y: 0\n";
        assertEquals(expected, actual);
    }

    @Test
    public void stepForwardShouldMoveFromStartAlongNegativeAxisX() throws IOException {

        Point start = robot.getCoordinates();
        program.move("llf");

        Point finish = robot.getCoordinates();
        assertEquals(start.x - 1, finish.x);
        assertEquals(start.y, finish.y);

        String actual = "";
        List<String> all =  Files.readAllLines(Paths.get(fileName));
        for (String s : all) {
            actual += s + "\n";
        }

        String expected =   "X: -1; Y: 0\n";
        assertEquals(expected, actual);
    }

    @Test
    public void stepForwardShouldMoveFromStartAlongPositiveAxisY() throws IOException {

        Point start = robot.getCoordinates();
        program.move("lf");

        Point finish = robot.getCoordinates();
        assertEquals(start.x, finish.x);
        assertEquals(start.y + 1, finish.y);

        String actual = "";
        List<String> all =  Files.readAllLines(Paths.get(fileName));
        for (String s : all) {
            actual += s + "\n";
        }
        String expected =   "X: 0; Y: 1\n";
        assertEquals(expected, actual);
    }

    @Test
    public void stepForwardShouldMoveFromStartAlongNegativeAxisY() throws IOException {

        Point start = robot.getCoordinates();
        program.move("rf");

        Point finish = robot.getCoordinates();
        assertEquals(start.x, finish.x);
        assertEquals(start.y - 1, finish.y);

        String actual = "";
        List<String> all =  Files.readAllLines(Paths.get(fileName));
        for (String s : all) {
            actual += s + "\n";
        }
        String expected =   "X: 0; Y: -1\n";
        assertEquals(expected, actual);
    }

    @Test
    public void turnLeftShouldChangeStartDirectionToUp() throws IOException {

        program.move("l");
        assertEquals(Directions.UP, robot.getDirection());
    }

    @Test
    public void turnRightShouldChangeStartDirectionToDown() throws IOException {

        program.move("r");
        assertEquals(Directions.DOWN, robot.getDirection());
    }

    @Test
    public void moveShouldChangeLocationAccordingToCommand() throws IOException {

        program.move("lffrflfrrfff");

        String actual = "";
        List<String> all =  Files.readAllLines(Paths.get(fileName));
        for (String s : all) {
            actual += s + "\n";
        }

        String expected =   "X: 0; Y: 1\n" +
                "X: 0; Y: 2\n" +
                "X: 1; Y: 2\n" +
                "X: 1; Y: 3\n" +
                "X: 1; Y: 2\n" +
                "X: 1; Y: 1\n" +
                "X: 1; Y: 0\n";

        assertEquals(expected, actual);
    }
}
