package com.nixsolutions;

import java.util.*;

/**
 * Created by Lexx on 16.11.2015.
 */
public class Main {
    private static List<Figures> figures = new ArrayList<Figures>();

    public static List<Figures> sortingBySquare(List<Figures> figuresNotSort) {
        List<Figures> sortedList = new ArrayList<Figures>();
        double[] maxSquares = new double[figuresNotSort.size()];
        int i = 0;
        for (Figures obj : figuresNotSort) {
            maxSquares[i++] = (obj.getSquare());
        }
        Arrays.sort(maxSquares);
        for (double d : maxSquares) {
            for (Figures obj : figuresNotSort) {
                if (obj.getSquare() == d) {
                    sortedList.add(obj);
                    i++;
                }
            }
        }
        return sortedList;
    }

    /**
     * Method consist with 2 parts:
     * 1. Generate different 10 figures, with different shapes(square, triangle, circle) and dimensions.
     * 2. Array with figures are sorting for square.
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        //Create figures
        for (int i = 0; i < 10; i++) {
            int choise = random.nextInt(3); //generate random number (number matches figure shape (0 - square, 1 - triangle, 3 - circle))
            switch (choise) {
                case 0:
                    int rndX = random.nextInt(50);
                    int rndY = random.nextInt(50);
                    int[] coord_LeftLowerCorner = {0, 50};
                    coord_LeftLowerCorner[0] = coord_LeftLowerCorner[0] + rndX;
                    coord_LeftLowerCorner[1] = coord_LeftLowerCorner[1] + rndY;
                    int[] coord_LeftUpperCorner = {0, 0};
                    coord_LeftUpperCorner[0] = coord_LeftUpperCorner[0] + rndX;
                    coord_LeftUpperCorner[1] = coord_LeftUpperCorner[1] + rndY;
                    int[] coord_RightLowerCorner = {70, 50};
                    coord_RightLowerCorner[0] = coord_RightLowerCorner[0] + rndX;
                    coord_RightLowerCorner[1] = coord_RightLowerCorner[1] + rndY;
                    int[] coord_RightUpperCorner = {70, 0};
                    coord_RightUpperCorner[0] = coord_RightUpperCorner[0] + rndX;
                    coord_RightUpperCorner[1] = coord_RightUpperCorner[1] + rndY;
                    Square square_rndm = new Square("square_" + i, coord_LeftLowerCorner, coord_RightLowerCorner, coord_LeftUpperCorner, coord_RightUpperCorner, 1);
                    figures.add(square_rndm);
                    break;
                case 1:
                    rndX = random.nextInt(50);
                    rndY = random.nextInt(50);
                    int[] xPoint = {100 + rndX, 130 + rndX, 160 + rndX};
                    int[] yPoint = {100 + rndY, 70 + rndY, 100 + rndY};
                    Triangle triangle_rndm = new Triangle("triangle_" + i, xPoint, yPoint, 3, 1);
                    figures.add(triangle_rndm);
                    break;
                case 2:
                    int[] coord_Center_rand = {150 + random.nextInt(50), 150 + random.nextInt(50)};
                    int radius_rand = 25 + random.nextInt(10);
                    Circle circle_rndm = new Circle("circle_" + i, coord_Center_rand, radius_rand, 1);
                    figures.add(circle_rndm);
                    break;
            }
        }
        System.out.print("Figures which was generated: \n" + figures.toString());
        //Scaling figures
        for (Figures fg : figures) {
            double scale = random.nextDouble(); //generate random scale number
            fg.setScale(scale);
            fg.scaling();
        }
        System.out.print("\nFigures after scaling: \n" + figures.toString());
        //Sorted figures
        System.out.print("\nFigures which sorted by square: \n" + sortingBySquare(figures));
    }

}