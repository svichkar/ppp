package com.manetskiy;

import java.io.*;

public class Robot {
    private int x = 0;
    private int y = 0;
    private Direction currentDirection = Direction.EAST;
    OutputStream out = null;

    public Robot(OutputStream out) {
        this.out = out;
    }

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
            out.write(currentPosition().getBytes());
        } catch (IOException ex) {
            throw new IOException();
        }
    }

    public void turnRight() {
        if (currentDirection != Direction.WEST) {
            currentDirection = currentDirection.right();
        } else {
            currentDirection = Direction.NORTH;
        }
    }

    public void turnLeft() {
        if (currentDirection != Direction.NORTH) {
            currentDirection = currentDirection.left();
        } else {
            currentDirection = Direction.WEST;
        }
    }

    private String currentPosition() {
        return "X: " + x + " Y: " + y + " Direction: " + currentDirection.toString() + "\n";
    }
}
