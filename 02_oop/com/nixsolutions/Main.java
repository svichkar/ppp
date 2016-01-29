package com.nixsolutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static final int CAPACITY = 10;

    public static void main(String[] argc) {
        ArrayList<Figure> listFigures = new ArrayList<>(CAPACITY);
        Random random = new Random();

        //create random figures
        for (int i = 0; i < CAPACITY; i++) {
            switch (random.nextInt(3)) {
                case 0:
                    listFigures.add(new Circle(new Coordinate(random.nextInt(10), random.nextInt(10)),
                            new Coordinate(random.nextInt(10), random.nextInt(10))));
                    break;
                case 1:
                    listFigures.add(new Square(new Coordinate(random.nextInt(10), random.nextInt(10)),
                            new Coordinate(random.nextInt(10), random.nextInt(10)),
                            new Coordinate(random.nextInt(10), random.nextInt(10)),
                            new Coordinate(random.nextInt(10), random.nextInt(10))));
                    break;
                case 2:
                    listFigures.add(new Triangle(new Coordinate(random.nextInt(10), random.nextInt(10)),
                            new Coordinate(random.nextInt(10), random.nextInt(10)),
                            new Coordinate(random.nextInt(10), random.nextInt(10))));
                    break;
            }
        }

        //before resize
        System.out.println("1. Created different figures:");
        for (Figure figure : listFigures) {
            System.out.println(figure);
        }
        System.out.println();
        System.out.println();
        //resize
        for (Figure figure : listFigures) {
            figure.resize(random.nextDouble());
        }
        //after resize
        System.out.println("2. Resized figures:");
        for (Figure figure : listFigures) {
            System.out.println(figure);
        }
        //sort
        Collections.sort(listFigures);
        System.out.println();
        System.out.println();
        System.out.println("2. Sorted figures:");
        //after sort
        for (Figure figure : listFigures) {
            System.out.println(figure);
        }
    }

}