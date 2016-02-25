package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Program {

    private static final Logger LOG = LogManager.getLogger(Program.class);
    private Robot robot;
    private ByteArrayOutputStream bw;

    public Program() {
        bw = new ByteArrayOutputStream();
        robot = new Robot(bw);
    }

    public void moveRobot(String way) {
        if (isCorrectLine(way)) {
            for (String c : way.split("")) {
                switch (c) {
                    case "l": {
                        robot.turnLeft();
                        break;
                    }
                    case "r": {
                        robot.turnRight();
                        break;
                    }
                    case "f": {
                        robot.stepForward();
                        break;
                    }
                    default: {
                        LOG.warn("In default block. Invalid behaviour.");
                    }
                }
            }

            writeToFile();

        } else {
            LOG.warn("Incorrect way for the Robot");
        }
    }

    private boolean isCorrectLine(String way) {
        return true;
    }

    private void writeToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("1.txt");
            fos.write(bw.toByteArray());
        } catch (FileNotFoundException e) {
            LOG.catching(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
