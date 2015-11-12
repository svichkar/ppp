package com.nixsolutions;

import java.util.Random;

/**
 * Main class is created to show the work with Figure related classes
 *
 * @author Rybkinrolla
 */
public class Main {
    /**
     * Standard entry point
     *
     * @param args array of String
     */
    public static void main(String[] args) {
        Random generator = new Random();
        Figure[] arrayOfRandomFigures = new Figure[10];
        for (int i = 0; i < arrayOfRandomFigures.length; i++) {
            switch (generator.nextInt(3)) {
                case 0:
                    arrayOfRandomFigures[i] = new Triangle();
                    break;
                case 1:
                    arrayOfRandomFigures[i] = new Foursquare();
                    break;
                case 2:
                    arrayOfRandomFigures[i] = new Circle();
                    break;
            }
        }
        for (int i = 0; i < arrayOfRandomFigures.length; i++) {
            arrayOfRandomFigures[i].resize((float) Math.random());
        }
        for (int i = 0; i < arrayOfRandomFigures.length; i++) {
            for (int j = i + 1; j < arrayOfRandomFigures.length; j++) {
                if (arrayOfRandomFigures[i].getArea() > arrayOfRandomFigures[j].getArea()) {
                    Figure temp = arrayOfRandomFigures[i];
                    arrayOfRandomFigures[i] = arrayOfRandomFigures[j];
                    arrayOfRandomFigures[j] = temp;
                }
            }
        }
    }
}
