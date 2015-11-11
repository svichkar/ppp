package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Square extends Figures {

    @Override
    public void squareCalculating() {
        double square = Math.pow(coordinate[1][1] - coordinate[0][1], 2);
        this.setSquare(square);

    }

    @Override
    public void reSize(double delta) {
        coordinate[1][1] = coordinate[1][1] * delta;
        coordinate[2][0] = coordinate[2][0] * delta;
        coordinate[2][1] = coordinate[2][1] * delta;
        coordinate[3][0] = coordinate[3][0] * delta;
        this.squareCalculating();
    }

    public Square() {
        this.setName("Square");
        double xPoint = maxA * Math.random();
        double yPoint = maxA * Math.random();
        double side = maxA * Math.random();
        coordinate = new double[4][2];
        coordinate[0][0] = xPoint;
        coordinate[0][1] = yPoint;
        coordinate[1][0] = xPoint;
        coordinate[1][1] = yPoint + side;
        coordinate[2][0] = xPoint + side;
        coordinate[2][1] = yPoint + side;
        coordinate[3][0] = xPoint + side;
        coordinate[3][1] = yPoint;
    }

    public Square(double xPoint, double yPoint, double side) {
        this.setName("Square");
        coordinate = new double[4][2];
        coordinate[0][0] = xPoint;
        coordinate[0][1] = yPoint;
        coordinate[1][0] = xPoint;
        coordinate[1][1] = yPoint + side;
        coordinate[2][0] = xPoint + side;
        coordinate[2][1] = yPoint + side;
        coordinate[3][0] = xPoint + side;
        coordinate[3][1] = yPoint;
    }
}
