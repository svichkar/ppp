package com.nixsolutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * Created by rybkinrolla on 04.12.2015.
 */
public class Robot {
    private int coordinateX;
    private int coordinateY;
    private int angleOfRotation;
    private File outCoordinatesFile;

    public Robot(File pathToWriteCoordinates) throws IOException {
        coordinateX = 0;
        coordinateY = 0;
        angleOfRotation = 0;
        outCoordinatesFile = pathToWriteCoordinates;
        if (!Files.exists(outCoordinatesFile.toPath())) {
            Files.createFile(outCoordinatesFile.toPath());
        }
    }

    public void stepForward() throws IOException {
        switch (angleOfRotation) {
            case 90:
                coordinateY++;
                break;
            case 180:
                coordinateX--;
                break;
            case 270:
                coordinateY--;
                break;
            case 0:
                coordinateX++;
                break;
        }
        BufferedWriter bufferedWriter = Files.newBufferedWriter((outCoordinatesFile.toPath()), StandardOpenOption.APPEND);
        bufferedWriter.write("x" + coordinateX + "y" + coordinateY + "\n");
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public void turnRight() {
        if (angleOfRotation == 0) {
            angleOfRotation = 360;
        }
        angleOfRotation -= 90;
    }

    public void turnLeft() {
        if (angleOfRotation == 360) {
            angleOfRotation = 0;
        }
        angleOfRotation += 90;
    }
}
