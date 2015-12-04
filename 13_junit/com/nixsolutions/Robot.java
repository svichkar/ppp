package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by kozlovskij on 12/4/2015.
 */
public class Robot {
    private static final Logger LOGGER = LogManager.getLogger(Robot.class);
    private int coordinateX = 0;
    private int coordinateY = 0;
    private int direction = 1;
    private File file;

    public Robot(File file) {
        this.file = file;
    }

    public void copyToFile (String line, File file){
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(line);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
    public void turnLeft() {
        direction++;
    }

    public void turnRight() {
        direction--;
    }
    public void stepForward() {
        if (direction < 0) {
            direction = 4 - Math.abs(direction % 4);
        }
        switch (direction % 4) {
            case 0:
                coordinateY--;
                break;
            case 1:
                coordinateX++;
                break;
            case 2:
                coordinateY++;
                break;
            case 3:
                coordinateX--;
                break;
            default:
                LOGGER.error("wrong direction: " + direction);
                break;
        }
        this.copyToFile(coordinateX + "." + coordinateY + "\n", file);
    }
}
