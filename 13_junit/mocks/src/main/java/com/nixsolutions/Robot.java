package com.nixsolutions;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

/**
 * @author Sirotkin Mikhail
 */
public class Robot {

    private int coordX;
    private int coordY;
    /**
     * Field with possible values of direction
     */
    private enum direction {RIGHT, DOWN, LEFT, UP};
    private direction currentDirection;
    protected Writer writer;

    /**
     * Constructor
     * Define start position as 0:0 and write start coordinates to the stream
     */
    public Robot(Writer writer) throws IOException {
        coordX = 0;
        coordY = 0;
        currentDirection = direction.RIGHT;
        this.writer = writer;
        addCurrentCoordinatesToOutputStream();
    }

    /**
     * Method that returns coordinates of Robot
     * @return X and Y coordinates of Robot
     */
    public Map<String, Integer> getCoordinates(){
        Map<String, Integer> coordinates = new HashMap<String, Integer>();
        coordinates.put("X", coordX);
        coordinates.put("Y", coordY);
        return coordinates;
    }

    /**
     * Methods for moving Robot forward
     */
    public void stepForward() throws IOException {
        switch(currentDirection){
            case RIGHT:
                coordX++;
                break;
            case DOWN:
                coordY--;
                break;
            case LEFT:
                coordX--;
                break;
            case UP:
                coordY++;
                break;
        }
        addCurrentCoordinatesToOutputStream();
    }

    /**
     * Method for turns Robot left
     */
    public void turnLeft(){
        switch(currentDirection){
            case RIGHT:
                currentDirection = direction.UP;
                break;
            case DOWN:
                currentDirection = direction.RIGHT;
                break;
            case LEFT:
                currentDirection = direction.DOWN;
                break;
            case UP:
                currentDirection = direction.LEFT;
                break;
        }
    }

    /**
     * Method for turns Robot right
     */
    public void turnRight(){
        switch(currentDirection){
            case RIGHT:
                currentDirection = direction.DOWN;
                break;
            case DOWN:
                currentDirection = direction.LEFT;
                break;
            case LEFT:
                currentDirection = direction.UP;
                break;
            case UP:
                currentDirection = direction.RIGHT;
                break;
        }
    }

    private void addCurrentCoordinatesToOutputStream() throws IOException {
        writer.write("x = " + getCoordinates().get("X") + "; " + "y = " + getCoordinates().get("Y") + "\n");
    }
}