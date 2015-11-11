package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Triangle extends Figures {
    @Override
    public void squareCalculating() {
        double square = Math.abs((coordinate[1][0] - coordinate[0][0]) * (coordinate[2][1] - coordinate[0][1]) - (coordinate[2][0] - coordinate[0][0]) * (coordinate[1][1] - coordinate[0][1])) / 2;
        this.setSquare(square);
    }

    @Override
    public void reSize(double delta) {
        coordinate[1][1] = coordinate[1][1] * delta;
        coordinate[2][0] = coordinate[2][0] * delta;
        this.squareCalculating();
    }

    public Triangle() {
        this.setName("Triangle");
        double xPoint = maxA * Math.random();
        double yPoint = maxA * Math.random();
        double sideA = maxA * Math.random();
        double sideB = maxA * Math.random();
        coordinate = new double[3][2];
        coordinate[0][0] = xPoint;
        coordinate[0][1] = yPoint;
        coordinate[1][0] = xPoint;
        coordinate[1][1] = yPoint + sideA;
        coordinate[2][0] = xPoint + sideB;
        coordinate[2][1] = yPoint;
    }

    public Triangle(double xPoint, double yPoint, double sideA, double sideB) {
        this.setName("Triangle");
        coordinate = new double[3][2];
        coordinate[0][0] = xPoint;
        coordinate[0][1] = yPoint;
        coordinate[1][0] = xPoint;
        coordinate[1][1] = yPoint + sideA;
        coordinate[2][0] = xPoint + sideB;
        coordinate[2][1] = yPoint;
    }
}
