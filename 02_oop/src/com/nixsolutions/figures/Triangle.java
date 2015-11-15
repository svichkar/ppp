package com.nixsolutions.figures;

/**
 * Class implements Figure method and describes triangle figure
 * @author Sirotkin Mikhail
 */
public class Triangle extends Figure {
    /**
     * Constructor of Triangle class
     * @param point1
     * @param point2
     * @param point3
     */
    public Triangle(double[] point1, double[] point2, double[] point3)
    {
        setTypeName("triangle");
        setupCoordinates(point1, point2, point3);
    }

    /**
     * Method that set up coordinates. Uses in constructor and in changeSize method
     * @param point1 coordinates of point1
     * @param point2 coordinates of point2
     * @param point3 coordinates of point3
     */
    private void setupCoordinates(double[] point1, double[] point2, double[] point3){
        double[][] coordinates = new double[3][2];
        coordinates[0] = point1;
        coordinates[1] = point2;
        coordinates[2] = point3;
        setCoordinates(coordinates);
    }

    /**
    * @return double that represent triangle area size. Heron's formula is uses.
    */
    @Override
    public double calculateArea() {
        double[] sidesSizes = calculateSidesLength();
        double halfperimetr = (sidesSizes[0]+sidesSizes[1]+sidesSizes[2])/2;
        return Math.sqrt(halfperimetr*(halfperimetr-sidesSizes[0])*(halfperimetr-sidesSizes[1])
                *(halfperimetr-sidesSizes[2])) ;
    }

    /**
    @return double[] with size of each triangle side. Pythagorean theorem is used here
    */
    private double[] calculateSidesLength(){
        double[] sidesLength = new double[3];
        for(int i=0; i<3;i++){
            double[] point1 = getCoordinates()[i];
            double[] point2;
            if(i<2)
                point2 = getCoordinates()[i+1];
            else point2 = getCoordinates()[0];
            sidesLength[i] = Math.sqrt(Math.pow(point1[0] - point2[0],2) + Math.pow(point1[1] - point2[1],2));
        }
        return sidesLength;
    }

    /**
     * Default ratio is 2
     * Coordinates will be changed
     * @param increase boolean type.It sets if figure's size  will be increased or decreased
     */
    @Override
    public void changeSize(boolean increase)
    {
        int ratio = 2;
        double[][] curCoord = getCoordinates();
        double[][] newCoord = new double[3][2];
        for(int i=0; i<3;i++) {
            newCoord[i][0] = curCoord[i][0] * ratio;
            newCoord[i][1] = curCoord[i][1] * ratio;
        }
        setupCoordinates(newCoord[0], newCoord[1], newCoord[2]);
    }


}
