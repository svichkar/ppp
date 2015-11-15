package com.nixsolutions.figures;

import java.lang.Math;

/**
 * Class implements Figure method and describes circle figure
 * @author Sirotkin Mikhail
 */
public class Circle extends Figure {

    /**
     * Field contains radius size
     */
    private int radius;

    /**
     * Constructor setup coordinates  of circle center, radius and type name
     * @param centerCoorinates
     * @param radius
     */
    public Circle(double[] centerCoorinates, int radius){
        this.radius = radius;
        setTypeName("circle");
        setupCoordinates(centerCoorinates);
    }

    /**
     * Method that set up coordinates. Uses in constructor and in changeSize method
     * Should be used in any time when coordinates need to be changed
     * @param centerCoorinates
     */
    private void setupCoordinates(double[] centerCoorinates){
        double[][] coordinates = new double[2][2];
        coordinates[0] = centerCoorinates;
        coordinates[1][0] = coordinates[0][0];
        coordinates[1][1] = coordinates[0][1] + radius;
        setCoordinates(coordinates);
    }

    /**
     * Method that calculate circle area size
    */
    @Override
    public double calculateArea() {
        return  Math.pow(radius, 2)* Math.PI;
    }

    /**
    * Mathod that change size of circle using increment/decrement to radius
    * Radius can't be less than or equals 0. In such case decrement won't be executed.
    * @param increment sets if size will be increased/decreased
     */
    @Override
    public void changeSize(boolean increment){
        if(increment){
            radius +=1;
        }
        else {
            if((radius - 1) < 0){
                System.out.println("We can't decrement raduis because it less than 1!");
            }
            else radius -=1;
        }
        setupCoordinates(getCoordinates()[0]);
    }
}
