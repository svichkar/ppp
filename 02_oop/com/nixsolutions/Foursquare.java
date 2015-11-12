package com.nixsolutions;

import java.util.Random;

/**
 * Foursquare class extends Figure and it is created for work with square, foursquare
 *
 * @author Rybkinrolla
 */
public class Foursquare extends Figure {
    /**
     * Field side is used to define the length of side of a foursquare
     */
    private float side;

    /**
     * Constructor of a Foursquare class. Creates random Foursquare object with random size(1 - 10 points),
     * and random start point(-100 - 100)
     */
    public Foursquare() {
        Random generator = new Random();
        side = generator.nextInt(10) + 1;
        float sX = generator.nextInt(201) - 100;
        float sY = generator.nextInt(201) - 100;
        setCoordinates(new float[][]{
                {sX, sY},
                {sX + side, sY},
                {sX + side, sY + side},
                {sX, sY + side}
        });
    }

    /**
     * Implementation of the Figure method. Is used to return the area of a foursquare
     *
     * @return float value of the area of a foursquare
     */
    @Override
    public float getArea() {
        float area = side * side;
        return area;
    }

    /**
     * Implementation of the Figure method. Is used to to resize a foursquare
     *
     * @param delta float multiplier to reduce or increase the size of a foursquare
     */
    @Override
    public void resize(float delta) {
        if (delta > 0) {
            side = side * delta;
            setCoordinates(new float[][]{
                    {getCoordinates()[0][0], getCoordinates()[0][1]},
                    {getCoordinates()[1][0] + side, getCoordinates()[1][1]},
                    {getCoordinates()[2][0] + side, getCoordinates()[2][1] + side},
                    {getCoordinates()[3][0], getCoordinates()[3][1] + side}
            });
        }
    }
}
