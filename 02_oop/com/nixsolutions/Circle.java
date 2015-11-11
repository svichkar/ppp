package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Circle extends Figures {

    @Override
    public float squareCalculating() {
        float square = (float) Math.pow(((coordinate[1][1] - coordinate[0][1]) * Math.PI), 2);
        return square;
    }

    @Override
    public void reSize(float delta) {
        coordinate[1][1] = coordinate[1][1] * delta;
    }

    public Circle(float xPoint, float yPoint, float radius) {
        name = "Circle";
        coordinate = new float[2][2];
        coordinate[0][0] = xPoint;
        coordinate[0][1] = yPoint;
        coordinate[1][0] = xPoint;
        coordinate[1][1] = yPoint+radius;
    }
}
