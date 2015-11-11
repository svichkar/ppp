package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Triangle extends Figures {
    @Override
    public float squareCalculating() {
        return 0;
    }

    @Override
    public void reSize(float delta) {

    }

    public Triangle(float xPoint, float yPoint, float xPoint1, float yPoint1,float xPoint2, float yPoint2) {
        name = "Triangle";
        coordinate = new float[3][2];
        coordinate[0][0] = xPoint;
        coordinate[0][1] = yPoint;
        coordinate[1][0] = xPoint1;
        coordinate[1][1] = yPoint1;
        coordinate[2][0] = xPoint2;
        coordinate[2][1] = yPoint2;
    }
}
