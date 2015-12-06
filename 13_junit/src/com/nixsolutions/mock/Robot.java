package com.nixsolutions.mock;

import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by svichkar on 12/5/2015.
 */
public class Robot {

    private Point coordinates;
    private Directions direction;
    private File file;

    public Robot(String fileName) {

        coordinates = new Point(0, 0);
        setDirection(Directions.RIGHT);

        this.file = FileUtils.getFile(fileName);
        try {
            FileUtils.write(file, String.format("X: %s; Y: %s\n", coordinates.x, coordinates.y), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    public Point getCoordinates() {
        return coordinates.getLocation();
    }

    public void turnLeft() {

        switch (getDirection()) {

            case LEFT:
                setDirection(Directions.DOWN);
                break;

            case RIGHT:
                setDirection(Directions.UP);
                break;

            case UP:
                setDirection(Directions.LEFT);
                break;

            case DOWN:
                setDirection(Directions.RIGHT);
                break;
        }

    }

    public void turnRight() {

        switch (getDirection()) {

            case LEFT:
                setDirection(Directions.UP);
                break;

            case RIGHT:
                setDirection(Directions.DOWN);
                break;

            case UP:
                setDirection(Directions.RIGHT);
                break;

            case DOWN:
                setDirection(Directions.LEFT);
                break;
        }
    }

    public void stepForward() {

        switch (getDirection()) {

            case LEFT:
                coordinates.setLocation(coordinates.x - 1, coordinates.y);
                break;

            case RIGHT:
                coordinates.setLocation(coordinates.x + 1, coordinates.y);
                break;

            case UP:
                coordinates.setLocation(coordinates.x, coordinates.y + 1);
                break;

            case DOWN:
                coordinates.setLocation(coordinates.x, coordinates.y - 1);
                break;
        }

        try {
            FileUtils.write(file, String.format("X: %s; Y: %s\n", coordinates.x, coordinates.y), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
