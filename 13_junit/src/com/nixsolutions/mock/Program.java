package com.nixsolutions.mock;


import java.io.File;

/**
 * Created by svichkar on 12/5/2015.
 */
public class Program {

    private Robot robot;

    public Program(Robot robot) {
        this.robot = robot;
    }

     protected void move (String operation) {

         char[] stepsArray = operation.toCharArray();

         for (char step : stepsArray){
             switch (step) {
                 case 'f':
                     robot.stepForward();
                     break;
                 case 'l':
                     robot.turnLeft();
                     break;
                 case 'r':
                     robot.turnRight();
                     break;
             }
         }
    }
}
