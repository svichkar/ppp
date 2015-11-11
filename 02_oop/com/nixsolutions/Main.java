package com.nixsolutions;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Main {
    public static void main(String[] args) {
        float delta = (float) Math.random();
        Figures temp;
        temp = new Square(10, 15, 25);
        temp.showCoordinate();
        System.out.println();
        System.out.println( temp.squareCalculating());

        temp.reSize(delta);
        temp.showCoordinate();
        System.out.println();
        System.out.println( temp.squareCalculating());

    }
}
