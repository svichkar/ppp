package main.java.com.nixsolutions;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by sobolenko on 12/21/2015.
 */
public class Robot {
    private int Xcoordinate = 0;
    private int Ycoordinate = 0;
    private int direction = 90;
    FileOutputStream trace;
    BufferedWriter traceWritter;

    public Robot(FileOutputStream traceFile, BufferedWriter traceFileWritter) {
        trace = traceFile;
        traceWritter = traceFileWritter;
    }

    public void stepForward() throws IOException {
        switch (direction) {
            case 0:
                Ycoordinate++;
                break;
            case 90:
                Xcoordinate++;
                break;
            case 180:
                Ycoordinate--;
                break;
            case 270:
                Xcoordinate--;
                break;
        }
        System.out.println(Xcoordinate + " " + Ycoordinate);
        writeTrace();
    }

    public void turnLeft() {
        if (direction == 0) {
            direction = 360;
        }
        direction += -90;
    }

    public void turnRight() {
        if (direction == 360) {
            direction = 0;
        }
        direction += 90;
    }

    private void writeTrace() throws IOException {
        try {
            String currPosition = Xcoordinate + "," + Ycoordinate;
            traceWritter.append(currPosition);
            traceWritter.newLine();
            traceWritter.flush();
        } catch (IOException ioExc) {
            traceWritter.flush();
            traceWritter.close();
            throw new IOException(ioExc);
        }
    }

    protected String getCoordinates() {
        return Xcoordinate + "," + Ycoordinate;
    }
}
