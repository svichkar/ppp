package com.nixsolutions;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by Lexx on 16.11.2015.
 * Class determine the figure Square.
 */
public class Square extends Figures {
    Canvas myCanvas = new Canvas();
    private String name;
    private JFrame frame;
    private double square;
    private double scale;
    private int[] coord_LeftLowerCorner = new int[2];
    private int[] coord_RightLowerCorner = new int[2];
    private int[] coord_LeftUpperCorner = new int[2];
    private int[] coord_RightUpperCorner = new int[2];
    /**
     * canShift - indicates, can the figure shift (true - yes, false - not)
     */
    private boolean canShift;

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
     * @param frame                  - current frame
     */
    public Square(String name, int[] coord_LeftLowerCorner, int[] coord_RightLowerCorner, int[] coord_LeftUpperCorner, int[] coord_RightUpperCorner, double scale, JFrame frame) {
        super(name);
        this.coord_LeftLowerCorner = coord_LeftLowerCorner;
        this.coord_RightLowerCorner = coord_RightLowerCorner;
        this.coord_LeftUpperCorner = coord_LeftUpperCorner;
        this.coord_RightUpperCorner = coord_RightUpperCorner;
        this.frame = frame;
        this.name = name;
        this.scale = scale;
        calcSquare();
        scaling();
    }

    /**
     * @return shiftable whether a figure
     */
    public boolean isCanShift() {
        return canShift;
    }

    /**
     * Set variable which means, whether shiftable the figure or not
     *
     * @param canShift boolean (true - yes, false - not)
     */
    public void setCanShift(boolean canShift) {
        this.canShift = canShift;
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

    @Override
    public void draw() {
        draw(coord_LeftUpperCorner, coord_LeftUpperCorner, ((int) calc_A()), (int) calc_B());
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
     * Draw a figure into the frame.
     * uses coordinates of corners and side length
     *
     * @param coord_LeftUpperCorner
     * @param coord_LeftLowerCorner
     * @param side_A                (int) length
     * @param side_B                (int) length
     */
    public void draw(int[] coord_LeftUpperCorner, int[] coord_LeftLowerCorner, int side_A, int side_B) {
        myCanvas.setCanvasSquare(frame, name, coord_LeftUpperCorner[0], coord_LeftUpperCorner[1], side_A, side_B);
        frame.getContentPane().add(myCanvas);
        frame.setVisible(true);
    }

    /**
     * Uses for correct 'the figure's state' update after shift
     */
    public void re_draw() {
        myCanvas.setCanvasSquare(frame, name, coord_LeftUpperCorner[0], coord_LeftUpperCorner[1], calc_A(), calc_B());
        frame.getContentPane().add(myCanvas);
        frame.repaint();
    }

    /**
     * Shifting a figure through adding or subtraction step length from current coordinates.
     *
     * @param direction of shifting (values: up, down, left, right)
     * @param step      of shifting
     * @param start     not used
     */
    @Override
    public void shift(String direction, int step, long start) {
        if (direction == null) {
            System.out.print("Direction is null");
        }
        if (!this.canShift) {
            return;
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
        re_draw();
    }

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
        re_draw();
    }
}
