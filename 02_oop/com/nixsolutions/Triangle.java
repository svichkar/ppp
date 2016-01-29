package com.nixsolutions;

public class Triangle extends Figure {

    public Triangle() {
    }

    public Triangle(Coordinate a, Coordinate b, Coordinate c) {
        coords.add(a);
        coords.add(b);
        coords.add(c);
    }

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
