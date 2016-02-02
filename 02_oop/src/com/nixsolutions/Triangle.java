package com.nixsolutions;

/**
 * Created by Lexx on 16.11.2015.
 * Class determine the figure Triangle.
 * Note: variable nCorners = 3, and not possible to change
 */
public class Triangle extends Figures {
    private String name;
    private int[] xSides;
    private int[] ySides;
    private double square;
    private int nCorners = 3;
    private double scale;

    public Triangle(String name) {
        super(name);
    }

    /**
     * Set scale coefficient
     *
     * @param scale coefficient
     */
    @Override
    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getScale() {
        return scale;
    }

    /**
     * Constructor
     *
     * @param name     - name of Figure
     * @param xSides   - X coordinates of sides vertices
     * @param ySides   - Y coordinates of sides vertices
     * @param nCorners - deprecated
     * @param scale    - scale of figure
     */
    public Triangle(String name, int[] xSides, int[] ySides, int nCorners, double scale) {
        super(name);
        this.name = name;
        this.xSides = xSides;
        this.ySides = ySides;
        this.scale = scale;
        calcSquare();
        scaling();
    }

    /**
     * Shifting a figure through adding or subtraction step length from current coordinates.
     *
     * @param direction of shifting (values: up, down, left, right)
     * @param step      of shifting
     */
    @Override
    public void shift(String direction, int step) {
        if (direction == null) {
            System.out.print("Direction is null");
        }
        if (direction.toLowerCase().equals("up")) {
            this.ySides[0] -= step;
            this.ySides[1] -= step;
            this.ySides[2] -= step;
        }
        if (direction.toLowerCase().equals("down")) {
            this.ySides[0] += step;
            this.ySides[1] += step;
            this.ySides[2] += step;
        }
        if (direction.toLowerCase().equals("left")) {
            this.xSides[0] -= step;
            this.xSides[1] -= step;
            this.xSides[2] -= step;
        }
        if (direction.toLowerCase().equals("right")) {
            this.xSides[0] += step;
            this.xSides[1] += step;
            this.xSides[2] += step;
        }

    }

    @Override
    /**
     * Change figure dimension through subtraction coordinates of corners
     * subtraction occur in cycle, until the calcSquare() return bigger value;
     * example:
     * the Square = 134;
     * scale  = 0.5;
     * result 134 * 0.5 = 67.
     */
    public void scaling() {
        double scaleModifier = this.square * this.scale;
        while (calcSquare() > scaleModifier) {
            this.ySides[1]++;
            this.xSides[0]++;
            this.xSides[1]--;
            this.xSides[2]--;
        }
    }

    /**
     * Calculate length through coordinates corners
     *
     * @return length of A side
     */
    public double calc_A() {
        return Math.sqrt((Math.pow(xSides[1] - xSides[0], 2)) + (Math.pow(ySides[1] - ySides[0], 2)));
    }

    /**
     * Calculate length through coordinates corners
     *
     * @return length of B side
     */
    public double calc_B() {
        return Math.sqrt((Math.pow(xSides[2] - xSides[1], 2)) + (Math.pow(ySides[2] - ySides[1], 2)));
    }

    /**
     * Calculate length through coordinates corners
     *
     * @return length of C side
     */
    public double calc_C() {
        return Math.sqrt((Math.pow(xSides[2] - xSides[0], 2)) + (Math.pow(ySides[2] - ySides[0], 2)));
    }

    @Override
    public double calcSquare() {
        double p = (calc_A() + calc_B() + calc_C()) / 2;
        this.square = Math.sqrt(p * (p - calc_A()) * (p - calc_B()) * (p - calc_C()));
        return square;
    }

    @Override
    public double getSquare() {
        return square;
    }
}
