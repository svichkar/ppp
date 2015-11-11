package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Circle extends Figures {

    @Override
    public void squareCalculating() {
        double square = Math.pow(((coordinate[1][1] - coordinate[0][1]) * Math.PI), 2);
        this.setSquare(square);
    }

    @Override
    public void reSize(double delta) {
        coordinate[1][1] = coordinate[1][1] * delta;
        this.squareCalculating();
    }

    public Circle() {
        this.setName("Circle");
        coordinate = new double[2][2];
        double xPoint = maxA * Math.random();
        double yPoint = maxA * Math.random();
        double radius = maxA * Math.random();
        coordinate[0][0] = xPoint;
        coordinate[0][1] = yPoint;
        coordinate[1][0] = xPoint;
        coordinate[1][1] = yPoint + radius;
    }

    public Circle(double xPoint, double yPoint, double radius) {
        this.setName("Circle");
        coordinate = new double[2][2];
        coordinate[0][0] = xPoint;
        coordinate[0][1] = yPoint;
        coordinate[1][0] = xPoint;
        coordinate[1][1] = yPoint + radius;
    }
}
