package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;

public class Robot {

    private OutputStream stream;
    private Coordinate curCoordinate;
    private Coordinate[] directions = new Coordinate[4];
    private int index = 0;
    private static final Logger LOG = LogManager.getLogger(Robot.class);

    public Robot() {
        directions[0] = new Coordinate(1, 0);
        directions[1] = new Coordinate(0, 1);
        directions[2] = new Coordinate(-1, 0);
        directions[3] = new Coordinate(0, -1);
        curCoordinate = new Coordinate(0, 0);
    }

    public Robot(OutputStream stream) {
        this();
        setBr(stream);
    }

    public void setBr(OutputStream stream) {
        this.stream = stream;
        try {
            this.stream.write(curCoordinate.toString().getBytes());
            this.stream.write("\n".getBytes());
        } catch (IOException e) {
            //it does not occur
            LOG.catching(e);
        }
    }

    public void turnLeft() {
        changeIndex(1);
    }

    public void turnRight() {
        changeIndex(-1);
    }

    public Coordinate stepForward() {
        curCoordinate.moveXY(directions[index]);
        try {
            this.stream.write(curCoordinate.toString().getBytes());
            this.stream.write("\n".getBytes());
        } catch (IOException e) {
            LOG.catching(e);
        } finally {
            return curCoordinate;
        }
    }

    private void changeIndex(int i) {
        if (this.index == 0 && i == -1) {
            this.index = 3;
        } else if (this.index == 3 && i == 1) {
            this.index = 0;
        } else {
            this.index += i;
        }
    }

}
