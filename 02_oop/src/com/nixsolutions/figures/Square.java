package com.nixsolutions.figures;

/**
 * Class implements Figure method and describes square figure
 * @author Sirotkin Mikhail
 */
public class Square extends Figure {

    /**
     * Field store size of square side
     */
    private int size;

    /**
     * Constructor for square
     * @param leftBottomPointCoordinates
     * @param size
     */
    public Square(double[] leftBottomPointCoordinates, int size)
    {
        this.size = size;
        //[0] -x  [1] -y
        setTypeName("triangle");
        setupCoordinates(leftBottomPointCoordinates);
    }

    /**
    * Method that set up coordinates. Uses in constructor and in changeSize method
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
    * Mathod that change size of circle using increment/decrement to size of square.
    * Size can't be less than or equals 0. In such case decrement won't be executed.
    * Coordinates will be changed
    * @param increment
    */
    @Override
    public void changeSize(boolean increment){
        if(increment){
            size +=1;
        }
        else {
            if((size - 1) <= 0){
                System.out.println("We can't decrement size because it equals 1 or less than 1!");
            }
            else size -=1;
        }
        setupCoordinates(getCoordinates()[0]);
    }
}
