package com.nixsolutions;


import java.util.Random;

public class main {

  final static int arrLength = 10;
  static Figure[] arrayOfFigures = new Figure[arrLength];

  /**
   * main method - start point
   */
  public static void main(String[] args) {

    for (int i = 0; i < arrLength; i++) {
      Random rn = new Random();
      int random = rn.nextInt(3);
      Figure tempFigure;
      switch (random) {
        case 0:
          tempFigure =
              new Circle(rn.nextDouble() * 100, rn.nextDouble() * 100, rn.nextDouble() * 50);
          tempFigure.changeSize(rn.nextDouble());
          tempFigure.area = tempFigure.areaCalculation();
          arrayOfFigures[i] = tempFigure;

          break;
        case 1:
          tempFigure = new Square(rn.nextInt(100), rn.nextInt(100), rn.nextDouble() * 50);
          tempFigure.changeSize(rn.nextDouble());
          tempFigure.area = tempFigure.areaCalculation();
          arrayOfFigures[i] = tempFigure;
          break;
        case 2:
          tempFigure =
              new Triangle(rn.nextDouble() * 100, rn.nextDouble() * 100, rn.nextDouble() * 50);
          tempFigure.changeSize(rn.nextDouble());
          tempFigure.area = tempFigure.areaCalculation();
          arrayOfFigures[i] = tempFigure;
          break;
        default:
          break;
      }
 
    }

    sort(arrayOfFigures);

  }

  /**
   * Method for Figures array sorting input array will be sorted.
   * 
   * @param - array of Figures
   */
  public static void sort(Figure[] arr) {

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j].area > arr[j + 1].area) {
          Figure t = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = t;
        }
      }
    }

  }

}
