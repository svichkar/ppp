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
    private double radius;

    /**
     * Constructor setup coordinates  of circle center, radius and type name
     * @param centerCoorinates
     * @param radius
     */
    public Circle(double[] centerCoorinates, double radius){
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
    * Mathod that change size of circle by changing radius
    * @param coefficient for change size
     */
    @Override
    public void changeSize(double coefficient){
        radius = radius * coefficient;
        setupCoordinates(getCoordinates()[0]);
    }
}
