/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocksworkshop;

import java.io.File;
import java.io.IOException;
import static java.lang.Math.abs;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mednorcom
 */
public class Robot {

    private final static Logger LOGGER = LogManager.getLogger();
    private int axisX;
    private int axisY;
    private int direction;
    private File movelog;
    public final static int NORTH = 0;
    public final static int EAST = 1;
    public final static int SOUTH = 2;
    public final static int WEST = 3;

    protected int getAxisX() {
        return axisX;
    }

    protected void setAxisX(int axisX) {
        this.axisX = axisX;
    }

    protected int getAxisY() {
        return axisY;
    }

    protected void setAxisY(int axisY) {
        this.axisY = axisY;
    }

    protected int getDirection() {
        return direction;
    }

    protected void setDirection(int direction) {
        if (direction < 0) {
            this.direction = 4 - abs(direction % 4);
        } else {
            this.direction = direction % 4;
        }

    }

    public String getCoordinates() {
        return String.valueOf(axisX) + "." + String.valueOf(axisY);
    }

    public Robot(String filename) {
        axisX = 0;
        axisY = 0;
        direction = EAST;
        this.movelog = FileUtils.getFile(filename);
    }

    public void stepForward() throws IOException {
        switch (direction) {
            case NORTH:
                setAxisY(getAxisY() + 1);
                break;
            case EAST:
                setAxisX(getAxisX() + 1);
                break;
            case SOUTH:
                setAxisY(getAxisY() - 1);
                break;
            case WEST:
                setAxisX(getAxisX() - 1);
                break;
        }
        LOGGER.debug("Coordinates:" + getCoordinates());
        try {
            FileUtils.write(movelog, "Robot position: " + getCoordinates() + "\n", true);
        } catch (IOException ex) {
            LOGGER.warn("Movement log write error", ex);
            throw ex;
        }

    }

    public void turnRight() {
        setDirection(getDirection() + 1);
        LOGGER.debug("Direction changed to:" + getDirection());

    }

    public void turnLeft() {
        setDirection(getDirection() - 1);
        LOGGER.debug("Direction changed to:" + getDirection());
    }

}
