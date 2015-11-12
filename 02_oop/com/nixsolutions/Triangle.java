package com.nixsolutions;

import java.util.Random;

/**
 * Triangle class extends Figure and it is created for work with triangle
 *
 * @author Rybkinrolla
 */
public class Triangle extends Figure {
    /**
     * Constructor of a Triangle class. Creates random Triangle object by three points(-100 - 100).
     */
    public Triangle() {
        Random generator = new Random();
        setCoordinates(new float[][]{
                {generator.nextInt(201) - 100, generator.nextInt(201) - 100},
                {generator.nextInt(201) - 100, generator.nextInt(201) - 100},
                {generator.nextInt(201) - 100, generator.nextInt(201) - 100}
        });
    }

    /**
     * Implementation of the Figure method. Is used to return the area of a triangle
     *
     * @return float value of the area of a triangle
     */
    @Override
    public float getArea() {
        float area = Math.abs(((getCoordinates()[1][0] - getCoordinates()[0][0]) * (getCoordinates()[2][1] - getCoordinates()[0][1]) -
                (getCoordinates()[2][0] - getCoordinates()[0][0]) * (getCoordinates()[1][1] - getCoordinates()[0][1]))) / 2;
        return area;
    }

    /**
     * Implementation of the Figure method. Is used to to resize a triangle
     *
     * @param delta float multiplier to reduce or increase the size of a triangle
     */
    @Override
    public void resize(float delta) {
        if (delta > 0) {
            float[][] resizedCoordinates = getCoordinates();
            for (int i = 1; i < resizedCoordinates.length; i++) {
                for (int j = 0; j < resizedCoordinates[i].length; j++) {
                    resizedCoordinates[i][j] = resizedCoordinates[i][j] * delta;
                }
            }
            setCoordinates(resizedCoordinates);
        }
    }
}
