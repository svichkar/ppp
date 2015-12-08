package com.nixsolutions.mock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


/**
 * Created by svichkar on 12/5/2015.
 */
public class Robot {

    private static final Logger LOGGER = LogManager.getLogger(Robot.class);

    private Point coordinates;
    private Directions direction;
    private String fileName;
    private Writer fileWriter;

    public Robot(String fileName) {

        coordinates = new Point(0, 0);
        setDirection(Directions.RIGHT);
        this.fileName = fileName;
    }

    public Writer getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(Writer fileWriter) {
        this.fileWriter = fileWriter;
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
            if (fileWriter == null) {
                fileWriter = new FileWriter(fileName,true);
            }
            fileWriter.write(String.format("X: %s; Y: %s\n", coordinates.x, coordinates.y));
            fileWriter.flush();
        } catch (IOException e) {
            LOGGER.error("File cannot be written.", e);
        } finally {
            try {
                fileWriter.flush();
            } catch (IOException e) {
                LOGGER.error("File cannot be written.", e);
            }
        }
    }
}
