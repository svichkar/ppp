package com.nixsolutions.mock;

import java.awt.*;

/**
 * Created by svichkar on 12/5/2015.
 */
public class Robot {

    private Point coordinates = new Point();
    private Directions direction;

    public Robot() {

        coordinates.setLocation(0, 0);
        System.out.println(String.format("X: %s; Y: %s", coordinates.x, coordinates.y));
        setDirection(Directions.RIGHT);
    }

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
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

        System.out.println(String.format("X: %s; Y: %s", coordinates.x, coordinates.y));
    }


}
