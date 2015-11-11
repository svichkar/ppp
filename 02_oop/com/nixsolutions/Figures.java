package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public abstract class Figures {
    protected double[][] coordinate;
    private String name;
    private double square;
    final int maxA = 100;//maximumOfAllowedCoordinat

    public double getSquare() {
        double square=this.square;
        return square;
    }

    public void getCoordinate() {
        for (int i = 0; i < this.coordinate.length; i++) {
            System.out.println("point " + (i + 1) + " " + this.coordinate[i][0] + "|" + this.coordinate[i][1]);
        }
    }

    protected void setSquare(double square) {
        this.square = square;
    }

    protected void setName (String name) {
        this.name = name;
    }

    public double[][] move(double x, double y) {
        for (int i = 0; i < coordinate.length; i++) {
            coordinate[i][0] += x;
            coordinate[i][1] += y;
        }
        return coordinate;
    }

    public abstract void  squareCalculating();

    public abstract void reSize(double delta);

    @Override
    public String toString() {
        return name + "_" + square;
    }
}
