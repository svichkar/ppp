package com.nixsolutions.mock;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.awt.*;
import java.io.IOException;
import java.nio.file.FileSystems;

import static org.junit.Assert.assertEquals;

/**
 * Created by svichkar on 12/5/2015.
 */
public class SingleTest {

    private Robot robot;
    private Program program;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setup() {

        String fileName = folder.getRoot() + FileSystems.getDefault().getSeparator() + "coordinates.txt";
        robot = new Robot(fileName);
        program = new Program(robot);
    }

    @After
    public void tearDown() {

        robot = null;
        program = null;
    }

    @Test
    public void moveShouldChangeLocationAccordingToCommand() throws IOException {

        program.move("lffrflfrrfff");
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
    public void stepForwardShouldMoveFromStartAlongAxisX() throws IOException {

        Point start = robot.getCoordinates();
        program.move("f");
        Point finish = robot.getCoordinates();

        assertEquals(start.x + 1, finish.x);
        assertEquals(start.y, finish.y);
    }

}
