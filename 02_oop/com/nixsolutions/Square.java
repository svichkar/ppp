package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Square extends Figures {

    @Override
    public float squareCalculating() {
        float square = (float) Math.pow(coordinate[1][1] - coordinate[0][1], 2);
        return square;
    }

    @Override
    public void reSize(float delta) {
        coordinate[1][1] = coordinate[1][1] * delta;
        coordinate[2][0] = coordinate[2][0] * delta;
        coordinate[2][1] = coordinate[2][1] * delta;
        coordinate[3][0] = coordinate[3][0] * delta;
    }

    public Square(float xPoint, float yPoint, float side) {
        name = "Square";
        coordinate = new float[4][2];
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
