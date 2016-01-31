package com.nixsolutions;

import java.util.ArrayList;

/**
 * The abstract class Figure provides common fields and
 * methods for geometric figures
 *
 * @author Dmitry Zonovyi
 * @version 0.1
 */
public abstract class Figure implements Comparable<Figure> {

    protected ArrayList<Coordinate> coords = new ArrayList<>();
    protected double square;

    /**
     * Move the figure on the axis X and Y
     *
     * @param x move on axis X
     * @param y move on axis Y
     */
    public void move(double x, double y) {
        for (int i = 0; i < coords.size(); i++) {
            coords.get(i).setX(coords.get(i).getX() + x);
            coords.get(i).setY(coords.get(i).getY() + y);
        }
    }

    /**
     * Change size of figure for the current coefficient
     * Also coordinates X and Y are being changed
     *
     * @param coefficient the value of which varies on the size (0..1)
     */
    public void resize(double coefficient) {
        for (int i = 0; i < coords.size(); i++) {
            coords.get(i).setX(coords.get(i).getX() * coefficient);
            coords.get(i).setY(coords.get(i).getY() * coefficient);
        }
    }

    /**
     * Calculate length of line before two {@link Coordinate}
     *
     * @param a first Coordinate
     * @param b second Coordinate
     * @return distance between two Coordinates
     */
    protected double distanceBeforeCoords(Coordinate a, Coordinate b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    /**
     * Calculate square of the figure
     *
     * @return square
     */
    public abstract double square();

    @Override
    public int compareTo(Figure o) {
        return (int) (o.square() - square());
    }

    @Override
    public String toString() {
        String ret = "";
        for (Coordinate c : coords) {
            ret += c.toString() + " ";
        }
        return ret;
    }

}