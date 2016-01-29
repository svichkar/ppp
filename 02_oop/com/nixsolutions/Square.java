package com.nixsolutions;

public class Square extends Figure {

    public Square() {
    }

    public Square(Coordinate a, Coordinate b, Coordinate c, Coordinate d) {
        coords.add(a);
        coords.add(b);
        coords.add(c);
        coords.add(d);
    }

    @Override
    public double square() {
        return square = Math.pow(distanceBeforeCoords(coords.get(0), coords.get(1)), 2);
    }

    @Override
    public String toString() {
        return "Square{ square: " + square + " " + super.toString() + "}";
    }

}
