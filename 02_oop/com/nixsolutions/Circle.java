package com.nixsolutions;

public class Circle extends Figure {

    public Circle(Coordinate center, Coordinate coordOnCircle) {
        coords.add(center);
        coords.add(coordOnCircle);
    }

    //Pi*R^2
    @Override
    public double square() {
        return square = Math.PI * Math.pow(distanceBeforeCoords(coords.get(0), coords.get(1)), 2);
    }

    @Override
    public String toString() {
        return "Circle{ square: " + square + " " + super.toString() + "}";
    }

}
