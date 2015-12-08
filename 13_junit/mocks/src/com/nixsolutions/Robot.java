package com.nixsolutions;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sirotkin Mikhail
 */
public class Robot {
   /*–еализовать класс Robot, умеющий выполн€ть команды turnLeft, turnRight, stepForward. Ќачальные координаты 0,0,
   смотрит в направлении оси X;
   команда шаг вперед измен€ет координаты в соответствии с направлением и записывает их в файл.*/

    private int coordX;
    private int coordY;

    private enum direction {RIGHT, DOWN, LEFT, UP};
    private direction currentDirection;

    /**
     * Constructor
     */
    public Robot(){
        coordX = 0;
        coordY = 0;
        currentDirection = direction.RIGHT;
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
    public void stepForward(){
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
}