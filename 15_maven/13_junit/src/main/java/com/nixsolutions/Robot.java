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
    private BufferedWriter bufferedWriter;

    public Robot(File pathToWriteCoordinates) throws IOException {
        coordinateX = 0;
        coordinateY = 0;
        angleOfRotation = 0;
        outCoordinatesFile = pathToWriteCoordinates;
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
        writeCoordinatesToFile();
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

    private void writeCoordinatesToFile() throws IOException {
        if (!outCoordinatesFile.exists()) {
            Files.createFile(outCoordinatesFile.toPath());
        }
        if (bufferedWriter == null) {
            bufferedWriter = Files.newBufferedWriter((outCoordinatesFile.toPath()), StandardOpenOption.APPEND);
        }
        if (coordinateY != 0 || coordinateX != 0) {
            bufferedWriter.write("x" + coordinateX + "y" + coordinateY + "\n");
            bufferedWriter.flush();
        }
    }

    public void setBufferedWriter(BufferedWriter bw) {
        bufferedWriter = bw;
    }
}
