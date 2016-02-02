package com.nixsolutions;

/**
 * Created by Lexx on 16.11.2015.
 * Class determine the figure Square.
 */
public class Square extends Figures {
    private String name;
    private double square;
    private double scale;
    private int[] coord_LeftLowerCorner = new int[2];
    private int[] coord_RightLowerCorner = new int[2];
    private int[] coord_LeftUpperCorner = new int[2];
    private int[] coord_RightUpperCorner = new int[2];

    public Square(String name, int center, int square, int perimeter) {
        super(name);
    }

    /**
     * Constructor
     *
     * @param name                   - name of Figure
     * @param coord_LeftLowerCorner  - left lower corner coordinates (array[X,Y])
     * @param coord_RightLowerCorner - right lower corner coordinates (array[X,Y])
     * @param coord_LeftUpperCorner  - left upper corner coordinates (array[X,Y])
     * @param coord_RightUpperCorner - right lower corner coordinates (array[X,Y])
     * @param scale                  -  scale of figure
     */
    public Square(String name, int[] coord_LeftLowerCorner, int[] coord_RightLowerCorner, int[] coord_LeftUpperCorner, int[] coord_RightUpperCorner, double scale) {
        super(name);
        this.coord_LeftLowerCorner = coord_LeftLowerCorner;
        this.coord_RightLowerCorner = coord_RightLowerCorner;
        this.coord_LeftUpperCorner = coord_LeftUpperCorner;
        this.coord_RightUpperCorner = coord_RightUpperCorner;
        this.name = name;
        this.scale = scale;
        calcSquare();
        scaling();
    }

    /**
     * Set scale coefficient
     *
     * @param scale coefficient
     */
    @Override
    public void setScale(double scale) {
        this.scale = scale;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getSquare() {
        return square;
    }

    @Override
    public double getScale() {
        return scale;
    }

    /**
     * Calculate length through coordinates corners
     * calculate length A side of a square
     *
     * @return length of A side
     */
    public double calc_A() {
        return Math.sqrt((Math.pow(coord_LeftLowerCorner[0] - coord_LeftUpperCorner[0], 2)) + (Math.pow(coord_LeftLowerCorner[1] - coord_LeftUpperCorner[1], 2)));
    }

    /**
     * Calculate length through coordinates corners
     * calculate length B side of a square
     *
     * @return length of B side
     */
    public double calc_B() {
        return Math.sqrt((Math.pow(coord_RightUpperCorner[0] - coord_LeftUpperCorner[0], 2)) + (Math.pow(coord_RightUpperCorner[1] - coord_LeftUpperCorner[1], 2)));
    }

    /**
     * Calculate the Square, as lendth A side * length B side
     *
     * @return double
     */
    public double calcSquare() {
        this.square = calc_A() * calc_B();
        return square;
    }

    /**
     * Shifting a figure through adding or subtraction step length from current coordinates.
     *
     * @param direction of shifting (values: up, down, left, right)
     * @param step      of shifting
     */
    @Override
    public void shift(String direction, int step) {
        if (direction == null) {
            System.out.print("Direction is null");
        }
        if (direction.toLowerCase().equals("up")) {
            this.coord_LeftLowerCorner[1] -= step;
            this.coord_LeftUpperCorner[1] -= step;
            this.coord_RightLowerCorner[1] -= step;
            this.coord_RightUpperCorner[1] -= step;
        }
        if (direction.toLowerCase().equals("down")) {
            this.coord_LeftLowerCorner[1] += step;
            this.coord_LeftUpperCorner[1] += step;
            this.coord_RightLowerCorner[1] += step;
            this.coord_RightUpperCorner[1] += step;
        }
        if (direction.toLowerCase().equals("left")) {
            this.coord_LeftLowerCorner[0] -= step;
            this.coord_LeftUpperCorner[0] -= step;
            this.coord_RightLowerCorner[0] -= step;
            this.coord_RightUpperCorner[0] -= step;
        }
        if (direction.toLowerCase().equals("right")) {
            this.coord_LeftLowerCorner[0] += step;
            this.coord_LeftUpperCorner[0] += step;
            this.coord_RightLowerCorner[0] += step;
            this.coord_RightUpperCorner[0] += step;
        }
    }

    @Override
    /**
     * Change figure dimension through subtraction coordinates of corners
     * subtraction occur in cycle, until the calcSquare() return bigger value;
     * example:
     * the Square = 134;
     * scale  = 0.5;
     * result 134 * 0.5 = 67.
     */
    public void scaling() {
        double scaleModifier = this.square * this.scale;
        while (calcSquare() > scaleModifier) {
            this.coord_LeftLowerCorner[1]--;
            this.coord_RightUpperCorner[0]--;
        }
    }
}
