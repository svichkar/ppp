package com.manetskiy;

import java.io.*;

public class Robot {
    private int x = 0;
    private int y = 0;
    private Direction currentDirection;
    private OutputStream outputStream = null;

    public Robot() {
        currentDirection = Direction.EAST;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    //Inner class for calculating direction.
    private enum Direction {
        NORTH(1), EAST(2), SOUTH(3), WEST(4);

        private final int code;

        Direction(int code) {
            this.code = code;
        }

        private Direction left() {
            return values()[ordinal() - 1];
        }

        private Direction right() {
            return values()[ordinal() + 1];
        }
    }

    /**
     * Moves one step forward.
     * Logs its trace to OutputStream if it is provided.
     *
     * @throws IOException
     */
    public void stepForward() throws IOException {
        if (currentDirection == Direction.EAST)
            x++;
        if (currentDirection == Direction.WEST)
            x--;
        if (currentDirection == Direction.NORTH)
            y++;
        if (currentDirection == Direction.SOUTH)
            y--;
        try {
            if (outputStream != null)
                outputStream.write(currentPosition().getBytes());
        } catch (IOException ex) {
            throw new IOException();
        }
    }

    /**
     * Turns right by 90 degrees.
     */
    public void turnRight() {
        if (currentDirection != Direction.WEST) {
            currentDirection = currentDirection.right();
        } else {
            currentDirection = Direction.NORTH;
        }
    }

    /**
     * Turns left by 90 degrees.
     */
    public void turnLeft() {
        if (currentDirection != Direction.NORTH) {
            currentDirection = currentDirection.left();
        } else {
            currentDirection = Direction.WEST;
        }
    }

    /**
     * Generates info about current position and direction.
     *
     * @return String with position and direction.
     */
    protected String currentPosition() {
        return "X: " + x + " Y: " + y + " Direction: " + currentDirection.toString() + "\n";
    }
}
