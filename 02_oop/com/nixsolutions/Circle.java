package com.nixsolutions;

/**
 * The class Circle extends class {@link Figure}
 * provides work with circle geometric figures
 *
 * @author Dmitry Zonovyi
 * @version 0.1
 */
public class Circle extends Figure {
    /**
     * Constructor which takes two {@link Coordinate} of circle
     *
     * @param center        Coordinates in the center of the circle
     * @param coordOnCircle Coordinates on the circle
     */
    public Circle(Coordinate center, Coordinate coordOnCircle) {
        coords.add(center);
        coords.add(coordOnCircle);
    }

    /**
     * {@inheritDoc}
     * <p/>
     * Formula for the calculate square of the circle
     * S=πr2
     */
    @Override
    public double square() {
        return square = Math.PI * Math.pow(distanceBeforeCoords(coords.get(0), coords.get(1)), 2);
    }

    @Override
    public String toString() {
        return "Circle{ square: " + square + " " + super.toString() + "}";
    }

}
