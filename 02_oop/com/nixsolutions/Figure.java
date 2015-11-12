package com.nixsolutions;

/**
 * Abstract class Figure is created to define logic of work with Firgures
 *
 * @author Rybkinrolla
 */
public abstract class Figure {
    /**
     * Field coordinates is used to define start point of figure or other points
     */
    private float[][] coordinates;

    /**
     * Method is used to get coordinates of a figure
     *
     * @return float array of X and Y coordinates of points of a figure
     */
    public float[][] getCoordinates() {
        return coordinates;
    }

    /**
     * Method is used to set coordinates of a figure
     *
     * @param coordinates float array of X and Y coordinates of points of a figure
     */
    public void setCoordinates(float[][] coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Abstract method is used to return the area of a figure
     *
     * @return float value of the area of a figure
     */
    public abstract float getArea();

    /**
     * Abstract method is used to resize a figure
     *
     * @param delta float multiplier to reduce or increase the size of figure
     */
    public abstract void resize(float delta);

    /**
     * Defined method to move figure on axis X and Y
     *
     * @param deltaX quantity of points to move on X axis. Can be less than zero.
     *               If move on X axis is not required set it to zero
     * @param deltaY quantity of points to move on Y axis. Can be less than zero
     *               If move on Y axis is not required set it to zero
     */
    public void move(float deltaX, float deltaY) {
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i][0] += deltaX;
            coordinates[i][1] += deltaY;
        }
    }
}
