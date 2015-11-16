package com.nixsolutions;

import com.nixsolutions.figures.*;

import java.util.Arrays;
import java.util.Random;
/**
 * @author Sirotkin Mikhail
 * Main executable class that works with figures to demonstrate oop approach
 */
public class Main {
    /**
     * Main executable method
     * @param args
     */
    public static void main(String[] args) {

        Random generator = new Random();
        Figure[] figures = new Figure[10];

        //setup starting point [0;0]
        double[] firstPoint = new double[2];
        firstPoint[0] = 0;
        firstPoint[1] = 0;

        //generate array with random figures type
        for(int indOfFigure = 0; indOfFigure < figures.length; indOfFigure++){
            switch(generator.nextInt(3)){
                case 0:
                    figures[indOfFigure] = new Circle(firstPoint, generator.nextDouble()*100);
                    break;
                case 1:
                    figures[indOfFigure] = new Square(firstPoint, generator.nextDouble()*100);
                    break;
                case 2:
                    //generate random coordinates of point 2 and 3 for triangle
                    double[] secondPoint = new double[2];
                    secondPoint[0] = generator.nextDouble()*100;
                    secondPoint[1] = generator.nextDouble()*100;
                    double[] thirdPoint = new double[2];
                    thirdPoint[0] = generator.nextDouble()*100;
                    thirdPoint[1] = generator.nextDouble()*100;
                    figures[indOfFigure] = new Triangle(firstPoint, secondPoint, thirdPoint);
                    break;
            }
            //add to type name index (ex. 'circle1', 'square4' etc)
            figures[indOfFigure].setTypeName(figures[indOfFigure].getFigureType() + indOfFigure);
        }
        System.out.println("Print list of figures with their size:");
        printArrayNameAndSize(figures);
        // Execute method changeSize for all figures in array. Coefficient from 0 to 1
        for(int ind = 0; ind < figures.length; ind++){
            figures[ind].changeSize(generator.nextDouble());
        }
        System.out.println("Print list of figures with their size after resize:");
        printArrayNameAndSize(figures);
        //Sort array by area size
        for (int i = 0; i < figures.length; i++) {
            for (int j = i + 1; j < figures.length; j++) {
                if (figures[i].calculateArea() > figures[j].calculateArea()) {
                    Figure temp = figures[i];
                    figures[i] = figures[j];
                    figures[j] = temp;
                }
            }
        }
        System.out.println("Print list of figures with their size after sorting by size:");
        printArrayNameAndSize(figures);
    }

    /**
     * Print Figure's typeName and size
     * @param figures
     */
    private static void printArrayNameAndSize(Figure[] figures){
        for(int i = 0; i < figures.length; i++)
            System.out.println(figures[i].getFigureType() + " has size " + figures[i].calculateArea());
    }
}
