package com.nixsolutions;

/**
 * Created by Lexx on 16.11.2015.
 * Class determine the figure Circle.
 */
public class Circle extends Figures {
    private String name;
    private int[] coord_Center;
    private int radius;
    private double square;
    private double scale;

    public Circle(String name) {
        super(name);
    }

    /**
     * Constructor
     *
     * @param name         - name of Figure
     * @param coord_Center - center of Figure (array of int[X,Y])
     * @param radius       - radius of circle
     * @param scale        -  scale of figure
     */
    public Circle(String name, int[] coord_Center, int radius, double scale) {
        super(name);
        this.name = name;
        this.coord_Center = coord_Center;
        this.radius = radius;
        this.scale = scale;
        calcSquare();
        scaling();
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
    public double getSquare() {
        return square;
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
     * Shifting a figure through adding or subtraction step length from current coordinates.
     *
     * @param direction - how direction you want to shift
     * @param step      - which step of shifting (how many pixels)
     */
    @Override
    public void shift(String direction, int step) {
        if (direction == null) {
            System.out.print("Direction is null");
        }
        if (direction.toLowerCase().equals("up")) {
            this.coord_Center[1] -= step;
        }
        if (direction.toLowerCase().equals("down")) {
            this.coord_Center[1] += step;
        }
        if (direction.toLowerCase().equals("left")) {
            this.coord_Center[0] -= step;
        }
        if (direction.toLowerCase().equals("right")) {
            this.coord_Center[0] += step;
        }

    }

    @Override
    public double calcSquare() {
        square = Math.PI * Math.pow(radius, 2);
        return square;
    }

    @Override
    /**
     * Change figure dimension through subtraction radius
     * subtraction occur in cycle, until the calcSquare() return bigger value;
     */
    public void scaling() {
        double scaleModifier = this.square * this.scale;
        for (int i = 0; calcSquare() > scaleModifier; i++) {
            this.radius--;
        }
    }
}
