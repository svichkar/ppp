package com.nixsolutions.figures;

/**
 * Astract method that describes main characters and methods of geometric figure
 * @author Sirotkin Mikhail
 */
public abstract class Figure {
    /**
     * Type name, ex. circle, triangle etc.
     */
    private String TypeName = "Figure";
    /**
     * Field for store coordinates
     */
    private double[][] coordinates;

    /**
     * Method to get TypeName field
     * @return value of TypeName field
     */
    public String getFigureType(){
        return TypeName;
    }

    /**
     * Method to change TypeName field
     * @param typeOfFigure
     */
    public void setTypeName(String typeOfFigure){
        TypeName = typeOfFigure;
    }

    /**
     *
     * @return double[][] with current coordinates
     */
    public double[][] getCoordinates(){
        return coordinates;
    }
    public void setCoordinates(double[][] coordinates){
        this.coordinates = coordinates;
    }

    /**
     * Abstract method for calculate square size of figure
     * @return square size of figure
     */
    public abstract double calculateArea();

    /**
     * Abstract method for change size of figure
      * @param increase
     */
    public abstract void changeSize(boolean increase);

    /**
     * Move method. Coordinates will be changed
     * @param offsetX
     * @param offsetY
     */
    public void move(double offsetX, double offsetY){
        for(double[] coordinate : coordinates){
            coordinate[0] +=offsetX;
            coordinate[1] +=offsetX;
        }
    }

}
