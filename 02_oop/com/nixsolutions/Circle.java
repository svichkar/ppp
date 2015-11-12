package com.nixsolutions;

import java.util.Random;

/**
 * Circle class extends Figure and it is created for work with circle
 *
 * @author Rybkinrolla
 */
public class Circle extends Figure {
    /**
     * Constructor of a Circle class. Creates random Circle object by two points:
     * coordinates of center (sX, sY) and one point on circle
     * with random size(1 - 10 points) and random start point(-100 - 100)
     */
    public Circle() {
        Random generator = new Random();
        float sX = generator.nextInt(201) - 100;
        float sY = generator.nextInt(201) - 100;
        setCoordinates(new float[][]{{sX, sY},
                {sX, sY + (generator.nextInt(10) + 1)}});
    }

    /**
     * Implementation of the Figure method. Is used to return the area of a circle
     *
     * @return float value of the area of a circle
     */
    @Override
    public float getArea() {
        float area = (float) Math.PI * (getCoordinates()[1][1] - getCoordinates()[0][1]) *
                (getCoordinates()[1][1] - getCoordinates()[0][1]);
        return area;
    }

    /**
     * Implementation of the Figure method. Is used to to resize a circle
     *
     * @param delta float multiplier to reduce or increase the size of a circle
     */
    @Override
    public void resize(float delta) {
        if (delta > 0) {
            setCoordinates(new float[][]{{getCoordinates()[0][0], getCoordinates()[0][1]},
                    {getCoordinates()[1][0], getCoordinates()[1][1] * delta}});
        }
    }
}
