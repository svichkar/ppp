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
    public int coordinateX;
    public int coordinateY;
    public int directionDegree;
    private File outCoordinatesFile;

    public Robot(File pathToWriteCoordinates) throws IOException {
        coordinateX = 0;
        coordinateY = 0;
        directionDegree = 0;
        outCoordinatesFile = pathToWriteCoordinates;
        if (!Files.exists(outCoordinatesFile.toPath())) {
            Files.createFile(outCoordinatesFile.toPath());
        }
    }

    public void stepForward() throws IOException {
        if (directionDegree == 90) {
            coordinateY++;
        }
        if (directionDegree == 180) {
            coordinateX--;
        }
        if (directionDegree == 270) {
            coordinateY--;
        }
        if (directionDegree == 0) {
            coordinateX++;
        }
        BufferedWriter bufferedWriter = Files.newBufferedWriter((outCoordinatesFile.toPath()), StandardOpenOption.APPEND);
        bufferedWriter.write("x" + coordinateX + "y" + coordinateY + "\n");
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    public void turnRight() {
        if (directionDegree == 0) {
            directionDegree = 360;
        }
        directionDegree -= 90;
    }

    public void turnLeft() {
        directionDegree += 90;
        if (directionDegree == 360) {
            directionDegree = 0;
        }
    }
}
