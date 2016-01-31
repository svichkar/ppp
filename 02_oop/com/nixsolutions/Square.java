package com.nixsolutions;

/**
 * The class Square extends class {@link Figure}
 * provides work with square geometric figures
 *
 * @author Dmitry Zonovyi
 * @version 0.1
 */
public class Square extends Figure {
    /**
     * Constructor which takes four {@link Coordinate} of square
     *
     * @param a first Coordinate of the square
     * @param b second Coordinate of the square
     * @param c third Coordinate of the square
     * @param d fourth Coordinate of the square
     */
    public Square(Coordinate a, Coordinate b, Coordinate c, Coordinate d) {
        coords.add(a);
        coords.add(b);
        coords.add(c);
        coords.add(d);
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Formula for the calculate square of the Square
     * S=a*a
     */
    @Override
    public double square() {
        return square = Math.pow(distanceBeforeCoords(coords.get(0), coords.get(1)), 2);
    }

    @Override
    public String toString() {
        return "Square{ square: " + square + " " + super.toString() + "}";
    }

}
