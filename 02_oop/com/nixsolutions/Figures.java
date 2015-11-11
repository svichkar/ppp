package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public abstract class Figures {
    public float[][] coordinate;
    public String name;

    public void showCoordinate() {
        for (int i = 0; i < this.coordinate.length; i++) {
            System.out.println("point " + (i + 1) + " " + this.coordinate[i][0] + "|" + this.coordinate[i][1]);
        }
    }

    public float[][] move(float x, float y) {
        for (int i = 0; i < coordinate.length; i++) {
            coordinate[i][0] += x;
            coordinate[i][1] += y;
        }
        return coordinate;
    }

    public abstract float squareCalculating();

    public abstract void reSize(float delta);
}
