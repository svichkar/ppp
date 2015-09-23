package com.nixsolutions;

public class Square extends Figure {

  double sideSize;
  int topLevelPointX;
  int topLevelPointY;

  public Square(int topLevelPointX, int topLevelPointY, double sideSize) {
    coordinates = new double[4][2];
    this.sideSize = sideSize;
    this.topLevelPointX = topLevelPointX;
    this.topLevelPointY = topLevelPointY;

    coordinates[0][0] = topLevelPointX;
    coordinates[0][1] = topLevelPointY;

    coordinates[1][0] = topLevelPointX + sideSize;
    coordinates[1][1] = topLevelPointY;

    coordinates[2][0] = topLevelPointX + sideSize;
    coordinates[2][1] = topLevelPointY - sideSize;

    coordinates[3][0] = topLevelPointX;
    coordinates[3][1] = topLevelPointY - sideSize;

  }

  @Override
  public void move(String direction, double stepLenght) {
    direction = direction.toLowerCase();
    switch (direction) {
      case "forward":
        for (int i = 0; i < coordinates.length; i++) {

          coordinates[i][1] = coordinates[i][1] + stepLenght;
        }
        break;
      case "back": {
        for (int i = 0; i < coordinates.length; i++) {

          coordinates[i][1] = coordinates[i][1] - stepLenght;
        }
      }

        break;
      case "left":
        for (int i = 0; i < coordinates.length; i++) {

          coordinates[i][0] = coordinates[i][0] - stepLenght;
        }
        break;
      case "right":
        for (int i = 0; i < coordinates.length; i++) {

          coordinates[i][0] = coordinates[i][0] + stepLenght;
        }
        break;

      default:
        break;
    }

  }

  @Override
  public Double areaCalculation() {

    return sideSize * 4;
  }

  @Override
  public void changeSize(double coefficient) {

    this.sideSize = sideSize * coefficient;
    coordinates[0][0] = topLevelPointX;
    coordinates[0][1] = topLevelPointY;

    coordinates[1][0] = topLevelPointX + sideSize;
    coordinates[1][1] = topLevelPointY;

    coordinates[2][0] = topLevelPointX + sideSize;
    coordinates[2][1] = topLevelPointY - sideSize;

    coordinates[3][0] = topLevelPointX;
    coordinates[3][1] = topLevelPointY - sideSize;

  }

}
