package com.nixsolutions.figures;

/**
 * Class implements Figure method and describes square figure
 * @author Sirotkin Mikhail
 */
public class Square extends Figure {

    /**
     * Field store size of square side
     */
    private double size;

    /**
     * Constructor for square
     * @param leftBottomPointCoordinates
     * @param size
     */
    public Square(double[] leftBottomPointCoordinates, double size)
    {
        this.size = size;
        setTypeName("triangle");
        setupCoordinates(leftBottomPointCoordinates);
    }

    /**
    * Method that set up coordinates by leftBottomPointCoordinates and current size
    * It is use in constructor and in changeSize method
    * @param leftBottomPointCoordinates
    */
    private void setupCoordinates(double[] leftBottomPointCoordinates){
        double[][] coordinates = new double[4][2];
        coordinates[0] = leftBottomPointCoordinates;
        //left top point
        coordinates[1][0] = coordinates[0][0];
        coordinates[1][1] = coordinates[0][1] + size;
        //right top point
        coordinates[2][0] = coordinates[0][0] + size;
        coordinates[2][1] = coordinates[0][1] + size;
        //right bottom point
        coordinates[3][0] = coordinates[0][0] + size;
        coordinates[3][1] = coordinates[0][1];
        setCoordinates(coordinates);
    }

    /**
    * Method that calculate square area size
    */
    @Override
    public double calculateArea() {
        return  Math.pow(size, 2);
    }

    /**
    * Mathod that change size of square by changing square' side size. Then new coordinates wil be calculated.
    * Coordinates will be changed except leftBottomPointCoordinates
    * @param coefficient leftBottomPointCoordinates
    */
    @Override
    public void changeSize(double coefficient){
        size = coefficient * size;
        setupCoordinates(getCoordinates()[0]);
    }
}
