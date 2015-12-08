package com.nixsolutions.mock;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by svichkar on 12/5/2015.
 */
public class Program {

    private static final Logger LOGGER = LogManager.getLogger(Program.class);

    private Robot robot;

    public Program(Robot robot) {
        this.robot = robot;
    }

     public void move(String operation) {

         char[] stepsArray = operation.toLowerCase().toCharArray();

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
                 default:
                     LOGGER.error("Wrong step specified: {}", step);
                     break;
             }
         }
    }
}
