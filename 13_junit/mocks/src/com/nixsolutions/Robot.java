package com.nixsolutions;

import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.nio.file.*;

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
    private File robotsMovingCoordinates;
    private BufferedWriter bw;

    /**
     * Constructor
     */
    public Robot(File fileForRobotsMovingCoordinates){
        coordX = 0;
        coordY = 0;
        currentDirection = direction.RIGHT;
        this.robotsMovingCoordinates = fileForRobotsMovingCoordinates;
    }

    /**
     * Method that returns coordinates of Robot
     * @return X and Y coordinates of Robot
     */
    public Map<String, Integer> getCoordinates(){
        Map<String, Integer> coordinates = new HashMap<>();
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
        addCurrentCoordinatesToFile();
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

    private void addCurrentCoordinatesToFile() throws IOException {
        if (!robotsMovingCoordinates.exists()) {
            Files.createFile(robotsMovingCoordinates.toPath());
        }
        if (bw == null) {
            bw = Files.newBufferedWriter((robotsMovingCoordinates.toPath()), StandardOpenOption.APPEND);
        }
        bw.write("x = " + getCoordinates().get("X") + "; " + "y = " + getCoordinates().get("Y") + "\n");
        bw.flush();
    }
}