package com.nixsolutions;

import java.io.IOException;
import java.io.Writer;

/**
 * Represents a robot, which is able to move forward and to turn left and right
 * in four directions. Results of each command performed are written by robot to
 * a stream using the supplied Writer, and are represented by a single line,
 * containing robot's coordinates and direction where it looks. Each line ends
 * with a new line character (\n). Example line of results:
 * <p>
 * 1,2,N
 * <p>
 * where 1 and 2 are X and Y coordinates accordingly, and N is a short code of
 * compass direction (N - North, E - East, S - South, W - West).
 */
public class Robot {
    /**
     * Holds short codes of compass directions. N - North, E - East, S - South,
     * W - West. Direction code can be accessed by index of appropriate
     * character.
     */
    private static final String DIRECTIONS = "NESW";

    /**
     * Represents the current direction (0 stands for North, 1 - for East, 2 -
     * for South, 3 - for West);
     */
    private int direction;
    private int positionX;
    private int positionY;
    private Writer writer;

    /**
     * Robot spawns at 0,0 coordinates and is looking towards X axis.
     * 
     * @param outputWriter
     *            Writer using which to write execution results.
     * @throws IOException
     *             on exceptional IO conditions.
     */
    public Robot(Writer outputWriter) throws IOException {
        this.positionX = 0;
        this.positionY = 0;
        this.direction = 1; // 1 stands for East, E in DIRECTIONS constant.
        this.writer = outputWriter;
        reportStatus();
    }

    private void reportStatus() throws IOException {
        String status = positionX + "," + positionY + "," + DIRECTIONS.charAt(direction) + "\n";
        writer.append(status);
    }

    /**
     * Turns the robot to the left direction, then new direction is reported
     * using Writer.
     * 
     * @throws IOException
     */
    public void turnLeft() throws IOException {
        if (direction == 0) {
            direction = DIRECTIONS.length() - 1;
        } else if (direction < DIRECTIONS.length()) {
            direction--;
        } else {
            throw new IllegalStateException("Invalid direction: [" + direction + "]");
        }
        reportStatus();
    }

    /**
     * Turns the robot to the right direction, then new direction is reported
     * using Writer.
     * 
     * @throws IOException
     */
    public void turnRight() throws IOException {
        if (direction == (DIRECTIONS.length() - 1)) {
            direction = 0;
        } else if (direction < DIRECTIONS.length()) {
            direction++;
        } else {
            throw new IllegalStateException("Invalid direction: [" + direction + "]");
        }
        reportStatus();
    }

    /**
     * Robot makes one step in current direction, changing the appropriate
     * coordinate by the value of 1, then new coordinates are reported using
     * Writer.
     * 
     * @throws IOException
     */
    public void stepForward() throws IOException {
        if (direction == 0) { // if directed to North.
            positionY++;
        } else if (direction == 1) { // if directed to East.
            positionX++;
        } else if (direction == 2) { // if directed to South.
            positionY--;
        } else if (direction == 3) { // if directed to West.
            positionX--;
        } else {
            throw new IllegalStateException("Invalid direction: [" + direction + "]");
        }
        reportStatus();
    }
}
