package com.nixsolutions;

/**
 * Abstract class Figures is define logic of work with figures
 * Created by kozlovskij on 11/11/2015.
 */
public abstract class Figures {

    /**
     * Field for coordinates of points
     */
    private double[][] coordinate;

    /**
     * Field for object name
     */
    private String name;

    /**
     * Field for object square
     */
    private double square;

    /**
     * Constant field, using for create objects without parameters. Parameters for new objects will be set MAXALLOWED*Math.random();
     */
    public final int MAXALLOWED = 100;

    /**
     * Method shows object name and object square in string
     * override Object#toString() method
     * @return String on the form: "object name_object square
     */
    @Override
    public String toString() {
        return name + "_" + square;
    }

    /**
     *Abstract method for calculating object square
     */
    public abstract void squareCalculating();

    /**
     *Abstract method for changing object size
     * @param delta resize sides (radius) object (side * delta)
     */
    public abstract void reSize(double delta);

    /**
     *Method for creating Array for coordinates for all object points
     * @param coordinate new Array for coordinates for all object points
     */
    public void createCoordinate(double[][] coordinate) {
        this.coordinate=coordinate;
    }

    /**
     *Method shifts all object points on the grid
     * @param x shift by x axe
     * @param y shift by y axe
     */
    public void move(double x, double y) {
        for (int i = 0; i < this.getCoordinate().length; i++) {
            this.setCoordinate(getCoordinate()[i][0] + x, i, 0);
            this.setCoordinate(getCoordinate()[i][1] + y, i, 1);
        }
    }

    /**
     *Method updates square field
     * @param square will be set in square field
     */
    protected void setSquare(double square) {

        this.square = square;
    }

    /**
     *Method updates square field
     * @param name will be set in name field
     */
    protected void setName(String name) {

        this.name = name;
    }

    /**
     *Method for set coordinate for all object points
     * @param coordinate it's array for coordinates
     * @param x value for x axe
     * @param y value for y axe
     */
    protected void setCoordinate(double coordinate, int x, int y) {
        this.coordinate[x][y] = coordinate;
    }

    /**
     *Show current object square
     * @return double object square
     */
    public double getSquare() {
        double square = this.square;
        return square;
    }

    /**
     *Show current object coordinates
     * @return array with coordinates for all object points
     */
    public double[][] getCoordinate() {
        double[][] coordinate = this.coordinate;
        return coordinate;
    }
}
