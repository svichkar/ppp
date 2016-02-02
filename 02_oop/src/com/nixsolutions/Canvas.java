package com.nixsolutions;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Lexx on 31.01.2016.
 * Class which determinate conditions for render image.
 */
public class Canvas extends JComponent {
    private JFrame frame;
    private int coord_LeftUpperCornerX = 0;
    private int coord_LeftUpperCornerY = 0;
    private int sideA = 0;
    private int sideB = 0;
    private String name = "";
    private int[] xSides;
    private int[] ySides;
    private String currFigureName;
    private int nCorners = 3;
    int[] coordCenter;
    int radius = 0;
    int radiusParameter = 0;
    int frameX = 500;
    int frameY = 500;

    public Canvas() {
    }

    /**
     * Determinate conditions for render image (the square)
     *
     * @param frame            - current frame (whole scene should be rendered at same frame)
     * @param currFigureName   - the figure current name
     * @param leftUpperCornerX - X coordinate of left upper corner
     * @param leftUpperCornerY - Y coordinate of left upper corner
     * @param sideAlength      - side A length
     * @param sideBlength      - side B length
     */
    public void setCanvasSquare(JFrame frame, String currFigureName, int leftUpperCornerX, int leftUpperCornerY, double sideAlength, double sideBlength) {
        this.coord_LeftUpperCornerX = leftUpperCornerX;
        this.coord_LeftUpperCornerY = leftUpperCornerY;
        this.sideA = ((int) sideAlength);
        this.sideB = ((int) sideBlength);
        this.currFigureName = currFigureName;
        this.frame = frame;
        setFrame();
    }

    /**
     * Determinate conditions for render image (the triangle)
     *
     * @param frame    - current frame (whole scene should be rendered at same frame)
     * @param name     - the figure current name
     * @param xSides   - X coordinates of sides vertices
     * @param ySides   - Y coordinates of sides vertices
     * @param nCorners - 3
     */
    public void setCanvasTriangle(JFrame frame, String name, int[] xSides, int[] ySides, int nCorners) {
        this.name = name;
        this.xSides = xSides;
        this.ySides = ySides;
        this.currFigureName = name;
        this.frame = frame;
        setFrame();
    }

    /**
     * Determinate conditions for render image (the circle)
     *
     * @param frame        - current frame (whole scene should be rendered at same frame)
     * @param name         - the figure current name
     * @param coord_Center - center of circle
     * @param radius       - radius of circle
     */
    public void setCanvasCircle(JFrame frame, String name, int[] coord_Center, int radius) {
        this.currFigureName = name;
        this.coordCenter = coord_Center;
        this.radius = radius;
        radiusParameter = radius * 2;
        this.frame = frame;
        setFrame();
    }

    /**
     * Frame parameters,
     * size and title
     */
    private void setFrame() {
        frame.setTitle("Image");
        frame.setSize(frameX, frameY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Drawing shapes on stage. (Note: do not move this method)
     * in depend of current figure name value, selects different paint methods.
     *
     * @param g
     */
    public void paint(Graphics g) {
        g.drawString("Tips:", frameX - 495, frameY - 90);
        g.drawString("For move figures use 'WASD' keys.", frameX - 495, frameY - 80);
        g.drawString("For select one press number from name of figure, for select all press '0'", frameX - 495, frameY - 70);
        g.drawString("(ex. for figure with name 'square_1' number is 1).", frameX - 495, frameY - 60);
        switch (currFigureName.replaceAll("\\_\\d*", "")) {
            case "square":
                g.drawRect(this.coord_LeftUpperCornerX, this.coord_LeftUpperCornerY, ((int) sideA), ((int) sideB));
                break;
            case "triangle":
                g.drawPolygon(this.xSides, this.ySides, this.nCorners);
                break;
            case "circle":
                g.drawOval(this.coordCenter[0], this.coordCenter[1], this.radiusParameter, this.radiusParameter);
                break;
            default:
                System.out.print("Figure name is incorrect");
        }
    }
}
