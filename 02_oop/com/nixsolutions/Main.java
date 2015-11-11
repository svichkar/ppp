package com.nixsolutions;

import java.util.Random;

/**
 * Created by kozlovskij on 11/11/2015.
 */
public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Figures[] allowedTypes = new Figures[3];
        allowedTypes[0] = new Circle();
        allowedTypes[1] = new Square();
        allowedTypes[2] = new Triangle();

        Figures[] figures = new Figures[10];
        for (int i = 0; i < figures.length; i++) {
            figures[i] = allowedTypes[random.nextInt(3)];
            figures[i].reSize(Math.random());
        }
        Figures temp;
        for (int i = 0; i < figures.length; i++) {
            for (int j = 0; j < figures.length; j++) {
                if (figures[i].getSquare() > figures[j].getSquare()) {
                    temp = figures[i];
                    figures[i] = figures[j];
                    figures[j] = temp;
                }
            }
        }
        for (Figures elem : figures) {
            System.out.println(elem.toString());
        }
    }
}
