package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Circle extends Figures {

    /**
     * Constructor for create new Circle without parameters. Set center point in random position on the grid. Set random radius
     */
    public Circle() {
        this.setName("Circle");
        createCoordinate(new double[2][2]);
        double xPoint = MAXALLOWED * Math.random();
        double yPoint = MAXALLOWED * Math.random();
        double radius = MAXALLOWED * Math.random();
        setCoordinate(xPoint, 0, 0);
        setCoordinate(yPoint, 0, 1);
        setCoordinate(xPoint, 1, 0);
        setCoordinate(yPoint + radius, 1, 1);
        this.squareCalculating();
    }

    /**
     * Constructor for create new Circle with parameters. Set center point in xPoint,yPoint position on the grid. Set radius
     * @param xPoint it's point on x axe for new object center
     * @param yPoint it's point on y axe for new object center
     * @param radius it's length for object radius
     */
    public Circle(double xPoint, double yPoint, double radius) {
        this.setName("Circle");
        createCoordinate(new double[2][2]);
        setCoordinate(xPoint, 0, 0);
        setCoordinate(yPoint, 0, 1);
        setCoordinate(xPoint, 1, 0);
        setCoordinate(yPoint + radius, 1, 1);
        this.squareCalculating();
    }

    /**
     * Implementation of the Figures method. Calculating by Pi * radius * radius
     */
    @Override
    public void squareCalculating() {
        double square = (Math.pow((getCoordinate()[1][1] - getCoordinate()[0][1]),2) * Math.PI);
        this.setSquare(square);
    }

    /**
     * Implementation of the Figure method. Change radius length
     * @param delta resize radius object (radius * delta)
     */
    @Override
    public void reSize(double delta) {
        setCoordinate(getCoordinate()[1][1] * delta, 1, 1);
        this.squareCalculating();
    }


}
