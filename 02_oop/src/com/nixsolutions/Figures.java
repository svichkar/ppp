package com.nixsolutions;

/**
 * Created by Lexx on 16.11.2015.
 * Abstract class which determinate mutual characteristics of all figures.
 */
public abstract class Figures {
    private String name;
    private double square;
    private double scale;

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Figures(String name, int center, int square, int perimeter) {
        this.name = name;
        this.square = square;
    }

    public Figures() {
    }

    /**
     * Constructor
     *
     * @param 'figure's name'
     */
    public Figures(String name) {
        this.name = name;
    }

    /**
     * name - the figure's name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get square - square shape
     *
     * @return Square
     */
    public double getSquare() {
        return square;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScale() {
        return scale;
    }

    /**
     * shift(String direction, int step, long start) - shifting the figure.
     *
     * @param direction - how direction you want to shift
     * @param step      - which step of shifting (how many pixels)
     */
    public abstract void shift(String direction, int step);

    public abstract double calcSquare();

    /**
     * toString() - overridden method, which display information at appropriate form.
     *
     * @return string
     */
    public String toString() {
        return ("Figure name: " + getName() + ". Figure square: " + String.format("%.2f", getSquare()) + " by scale coefficient " + String.format("%.5f", getScale())) + "\n";
    }

    public abstract void scaling();
}
