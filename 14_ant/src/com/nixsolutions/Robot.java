package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class Robot {

    private ByteArrayOutputStream br;
    private Coordinate curCoordinate;
    private Coordinate[] directions = new Coordinate[4]; //this class located in 02_oop folder
    private int index = 0;
    private static final Logger LOG = LogManager.getLogger(Robot.class);

    public Robot(ByteArrayOutputStream br) {
        this.br = br;
        directions[0] = new Coordinate(1, 0);
        directions[1] = new Coordinate(0, 1);
        directions[2] = new Coordinate(-1, 0);
        directions[3] = new Coordinate(0, -1);
        curCoordinate = new Coordinate(0, 0);
        try {
            this.br.write(curCoordinate.toString().getBytes());
            this.br.write("\n".getBytes());
        } catch (IOException e) {
            //it does not occur
            LOG.catching(e);
        }
    }

    public void setBr(ByteArrayOutputStream br) {
        this.br = br;
    }

    public Coordinate getCurCoordinate() {
        return curCoordinate;
    }

    public void turnLeft() {
        changeIndex(1);
    }

    public void turnRight() {
        changeIndex(-1);
    }

    public void stepForward() {
        curCoordinate.setX((int) (curCoordinate.getX() + directions[index].getX()));
        curCoordinate.setY((int) (curCoordinate.getY() + directions[index].getY()));
        try {
            this.br.write(curCoordinate.toString().getBytes());
            this.br.write("\n".getBytes());
        } catch (IOException e) {
            LOG.catching(e);
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
