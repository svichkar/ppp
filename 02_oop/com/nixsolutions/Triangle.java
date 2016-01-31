package com.nixsolutions;

/**
 * The class Square extends class {@link Figure}
 * provides work with triangle geometric figures
 *
 * @author Dmitry Zonovyi
 * @version 0.1
 */
public class Triangle extends Figure {

    /**
     * Constructor which takes three {@link Coordinate} of triangle
     *
     * @param a first Coordinate of the square
     * @param b second Coordinate of the square
     * @param c third Coordinate of the square
     */
    public Triangle(Coordinate a, Coordinate b, Coordinate c) {
        coords.add(a);
        coords.add(b);
        coords.add(c);
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Formula for the calculate square of the Triangle
     * through three lines
     * S=sqrt{p*(p-a)*(p-b)*(p-c)}
     */
    @Override
    public double square() {
        double sideA = distanceBeforeCoords(coords.get(0), coords.get(1));
        double sideB = distanceBeforeCoords(coords.get(0), coords.get(2));
        double sideC = distanceBeforeCoords(coords.get(1), coords.get(2));
        double p = (sideA + sideB + sideC) / 2;
        return square = Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
    }

    @Override
    public String toString() {
        return "Triangle{ square: " + square + " " + super.toString() + "}";
    }

}
