package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Square extends Figures {

    /**
     * Implementation of the Figures method. Calculating by side * side
     */
    @Override
    public void squareCalculating() {
        double square = Math.pow(getCoordinate()[1][1] - getCoordinate()[0][1], 2);
        this.setSquare(square);

    }

    /**
     * Implementation of the Figures method. Shift left top point, right top point, right bottom point of object
     * @param delta resize sides object (side * delta)
     */
    @Override
    public void reSize(double delta) {
        setCoordinate(getCoordinate()[1][1] * delta, 1, 1);
        setCoordinate(getCoordinate()[2][0] * delta, 2, 0);
        setCoordinate(getCoordinate()[2][1] * delta, 2, 1);
        setCoordinate(getCoordinate()[3][0] * delta, 3, 0);
        this.squareCalculating();
    }

    /**
     * Constructor for create new Square without parameters. Set left bottom point in random position on the grid. Set random side
     */
    public Square() {
        this.setName("Square");
        double xPoint = MAXALLOWED * Math.random();
        double yPoint = MAXALLOWED * Math.random();
        double side = MAXALLOWED * Math.random();
        createCoordinate(new double[4][2]);
        setCoordinate(xPoint, 0, 0);
        setCoordinate(yPoint, 0, 1);
        setCoordinate(xPoint, 1, 0);
        setCoordinate(yPoint + side, 1, 1);
        setCoordinate(xPoint + side, 2, 0);
        setCoordinate(yPoint + side, 2, 1);
        setCoordinate(xPoint + side, 3, 0);
        setCoordinate(yPoint, 3, 1);
        this.squareCalculating();
    }

    /**
     * Constructor for create new Square without parameters. Set left bottom point in xPoint,yPoint position on the grid. Set side
     * @param xPoint it's point on x axe for new object left bottom point
     * @param yPoint it's point on y axe for new object left bottom point
     * @param side length
     */
    public Square(double xPoint, double yPoint, double side) {
        this.setName("Square");
        createCoordinate(new double[4][2]);
        setCoordinate(xPoint, 0, 0);
        setCoordinate(yPoint, 0, 1);
        setCoordinate(xPoint, 1, 0);
        setCoordinate(yPoint + side, 1, 1);
        setCoordinate(xPoint + side, 2, 0);
        setCoordinate(yPoint + side, 2, 1);
        setCoordinate(xPoint + side, 3, 0);
        setCoordinate(yPoint, 3, 1);
        this.squareCalculating();
    }
}
