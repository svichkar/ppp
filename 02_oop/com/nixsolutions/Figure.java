package com.nixsolutions;

import java.util.ArrayList;

public abstract class Figure implements Comparable<Figure> {

    protected ArrayList<Coordinate> coords = new ArrayList<>();
    protected double square;

    public void move(double x, double y) {
        for (int i = 0; i < coords.size(); i++) {
            coords.get(i).setX(coords.get(i).getX() + x);
            coords.get(i).setY(coords.get(i).getY() + y);
        }
    }

    public void resize(double coefficient) {
        for (int i = 0; i < coords.size(); i++) {
            coords.get(i).setX(coords.get(i).getX() * coefficient);
            coords.get(i).setY(coords.get(i).getY() * coefficient);
        }
    }

    public double distanceBeforeCoords(Coordinate a, Coordinate b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

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