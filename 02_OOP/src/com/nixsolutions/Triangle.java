package com.nixsolutions;

public class Triangle extends Figure {

  double coordinateX;
  double coordinateY;
  double sideLength;

  public Triangle(double coordinateX, double coordinateY, double sideLength) {
    coordinates = new double[3][2];
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
    this.sideLength = sideLength;
    coordinates[0][0] = coordinateX;
    coordinates[0][1] = coordinateY;

    coordinates[1][0] = coordinateX + sideLength / 2;
    coordinates[1][1] = coordinateY + Math.sqrt(3) / 2 * sideLength;

    coordinates[2][0] = coordinateX + sideLength;
    coordinates[2][1] = coordinateY;

  }

  @Override
  public void move(String direction, double stepLength) {
    direction = direction.toLowerCase();
    switch (direction) {
      case "forward":
        coordinates[0][1] = coordinates[0][1] + stepLength;
        coordinates[1][1] = coordinates[1][1] + stepLength;
        coordinates[2][1] = coordinates[2][1] + stepLength;
        break;
      case "back": {
        coordinates[0][1] = coordinates[0][1] - stepLength;
        coordinates[1][1] = coordinates[1][1] - stepLength;
        coordinates[2][1] = coordinates[2][1] - stepLength;
      }

        break;
      case "left":
        coordinates[0][0] = coordinates[0][0] - stepLength;
        coordinates[1][0] = coordinates[1][0] - stepLength;
        coordinates[2][0] = coordinates[2][0] - stepLength;
        break;
      case "right":
        coordinates[0][0] = coordinates[0][0] + stepLength;
        coordinates[1][0] = coordinates[1][0] + stepLength;
        coordinates[2][0] = coordinates[2][0] + stepLength;
        break;

      default:
        break;
    }

  }

  @Override
  public Double areaCalculation() {
    return Math.sqrt(3) / 4 * sideLength * sideLength;
  }

  @Override
  public void changeSize(double coefficient) {
    sideLength = sideLength * coefficient;
    coordinates[0][0] = coordinateX;
    coordinates[0][1] = coordinateY;

    coordinates[1][0] = coordinateX + sideLength / 2;
    coordinates[1][1] = coordinateY + Math.sqrt(3) / 2 * sideLength;

    coordinates[2][0] = coordinateX + sideLength;
    coordinates[2][1] = coordinateY;

  }

}
