package com.manetskiy;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

public class TestRobot {

    private Robot robot;


    @Before
    public void setUp() {
        robot = new Robot();
    }

    @Test
    public void shouldHaveStartDirectionEast() {
        assertTrue(robot.currentPosition().contains("EAST"));
    }

    @Test
    public void shouldSendItsLocationAndDirectionWhenDoesStepForward() throws IOException {
        //given
        OutputStream out = new ByteArrayOutputStream();
        robot.setOutputStream(out);
        //when
        robot.turnLeft();
        robot.stepForward();
        robot.stepForward();
        robot.turnRight();
        robot.stepForward();
        //then
        assertEquals("X: 0; Y: 1; Direction: NORTH\n" +
                "X: 0; Y: 2; Direction: NORTH\n" +
                "X: 1; Y: 2; Direction: EAST\n", out.toString());
    }

    @Test
    public void shouldDoStepForward() throws IOException {
        robot.stepForward();
        assertTrue(robot.currentPosition().contains("X: 1"));
    }

    @Test
    public void shouldTurnLeft() {
        robot.turnLeft();
        assertTrue(robot.currentPosition().contains("NORTH"));
    }

    @Test
    public void shouldTurnRight() {
        robot.turnRight();
        assertTrue(robot.currentPosition().contains("SOUTH"));
    }

    @Test
    public void shouldTurnAroundBy180Degrees() {
        robot.turnRight();
        robot.turnRight();
        assertTrue(robot.currentPosition().contains("WEST"));
    }

    @Test
    public void shouldTurnAroundBy360Degrees() {
        //given
        String[] correctDirections = new String[]{"EAST", "NORTH", "WEST", "SOUTH", "EAST"};
        String[] expectedDirections = new String[correctDirections.length];
        //when
        //turn robot left 4 times and write direction of each turn
        for (int i = 0; i < 4; i++) {
            expectedDirections[i] = robot.currentPosition().replaceAll("^.*(?<=:)", "").trim();
            robot.turnLeft();
        }
        expectedDirections[4] = robot.currentPosition().replaceAll("^.*(?<=:)", "").trim();
        //then
        assertTrue(Arrays.equals(correctDirections, expectedDirections));
    }

    @Test
    public void shouldPassLongDistance() throws IOException {
        //when
        robot.turnRight();
        for (int i = 0; i < 500; i++) {
            robot.turnRight();
            robot.stepForward();
            robot.turnLeft();
            robot.stepForward();
        }
        assertEquals("X: -500; Y: -500; Direction: SOUTH\n", robot.currentPosition());
    }


}
