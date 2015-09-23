package com.nixsolutions;

public class Circle extends Figure {

  double radius;
  double coordinateX;
  double coordinateY;

  public Circle(double coordinateX, double coordinateY, double radius) {
    this.radius = radius;
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;

    coordinates = new double[1][2];
    coordinates[0][0] = coordinateX;
    coordinates[0][1] = coordinateY;

  }

  @Override
  public void move(String direction, double stepLenght) {
    direction = direction.toLowerCase();
    switch (direction) {
      case "forward":
        coordinates[0][1] = coordinates[0][1] + stepLenght;
        break;
      case "back": {
        coordinates[0][1] = coordinates[0][1] - stepLenght;
      }

        break;
      case "left":
        coordinates[0][1] = coordinates[0][0] - stepLenght;
        break;
      case "right":
        coordinates[0][1] = coordinates[0][0] + stepLenght;

        break;

      default:
        break;
    }

  }

  @Override
  public Double areaCalculation() {

    return Math.PI * radius * radius;
  }

  @Override
  public void changeSize(double coefficient) {
    this.radius = radius * coefficient;

  }

}
