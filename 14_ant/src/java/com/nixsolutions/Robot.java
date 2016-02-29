package java.com.nixsolutions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by sobolenko on 12/21/2015.
 */
public class Robot {
    private int Xcoordinate = 0;
    private int Ycoordinate = 0;
    private int direction = 90;
    ByteArrayOutputStream trace;
    Program myProgram;

    public Robot(ByteArrayOutputStream trace, Program myProgram) {
        this.myProgram = myProgram;
        this.trace = trace;
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

    public ByteArrayOutputStream getTrace() throws IOException {
        trace.reset();
        trace.write((Xcoordinate + "," + Ycoordinate + "," + direction + "\n").getBytes());
        return trace;
    }

    public String getCurrentPosition() {
        return Xcoordinate + "," + Ycoordinate + "," + direction;
    }
}
