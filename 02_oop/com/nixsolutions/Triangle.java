package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Triangle extends Figures {
    /**
     * Implementation of the Figures method. Calculating by |(Bx-Ax)*(Cy-Ay)-(Cx-AX)*(By-Ay)|/2
     */
    @Override
    public void squareCalculating() {
        double square = Math.abs((getCoordinate()[1][0] - getCoordinate()[0][0]) * (getCoordinate()[2][1] - getCoordinate()[0][1]) - (getCoordinate()[2][0] - getCoordinate()[0][0]) * (getCoordinate()[1][1] - getCoordinate()[0][1])) / 2;
        this.setSquare(square);
    }

    /**
     * Implementation of the Figures method. Shift left top point, right bottom point of object
     * @param delta resize sides object (side * delta)
     */
    @Override
    public void reSize(double delta) {
        setCoordinate(getCoordinate()[1][1] * delta, 1, 1);
        setCoordinate(getCoordinate()[2][0] * delta, 2, 0);
        this.squareCalculating();
    }

    /**
     * Constructor for create new Triangle without parameters. Set left bottom point in random position on the grid. Set random sides
     */
    public Triangle() {
        this.setName("Triangle");
        double xPoint = maxA * Math.random();
        double yPoint = maxA * Math.random();
        double sideA = maxA * Math.random();
        double sideB = maxA * Math.random();
        createCoordinate(new double[3][2]);
        setCoordinate(xPoint, 0, 0);
        setCoordinate(yPoint, 0, 1);
        setCoordinate(xPoint, 1, 0);
        setCoordinate(yPoint + sideA, 1, 1);
        setCoordinate(xPoint + sideB, 2, 0);
        setCoordinate(yPoint , 2, 1);
        this.squareCalculating();
    }

    /**
     * Constructor for create new Triangle without parameters. Set left bottom point in xPoint,yPoint position on the grid. Set sideA and sideB
     * @param xPoint it's point on x axe for new object left bottom point
     * @param yPoint it's point on x axe for new object left bottom point
     * @param sideA length
     * @param sideB length
     */
    public Triangle(double xPoint, double yPoint, double sideA, double sideB) {
        this.setName("Triangle");
        createCoordinate(new double[3][2]);
        setCoordinate(xPoint, 0, 0);
        setCoordinate(yPoint, 0, 1);
        setCoordinate(xPoint, 1, 0);
        setCoordinate(yPoint + sideA, 1, 1);
        setCoordinate(xPoint + sideB, 2, 0);
        setCoordinate(yPoint , 2, 1);
        this.squareCalculating();
    }
}
