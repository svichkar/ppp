import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by Lexx on 16.11.2015.
 */
public class Triangle extends Figures {
    private String name;
    private int[] xSides;
    private int[] ySides;
    private double square;
    private int nCorners = 3;
    private boolean canShift;
    private JFrame frame;
    Canvas myCanvas = new Canvas();
    private double scale;

    public Triangle(String name) {
        super(name);
    }

    public void setCanShift(boolean canShift) {
        this.canShift = canShift;
    }

    public boolean isCanShift() {
        return canShift;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getScale() {
        return scale;
    }

    public Triangle(String name, int[] xSides, int[] ySides, int nCorners, double scale, JFrame frame) {
        super(name);
        this.name = name;
        this.xSides = xSides;
        this.ySides = ySides;
        this.frame = frame;
        this.scale = scale;
        calcSquare();
        scaling();
    }

    @Override
    public void draw() {
        myCanvas.setCanvasTriangle(frame, this.name, this.xSides, this.ySides, this.nCorners);
        frame.getContentPane().add(myCanvas);
        frame.setVisible(true);
    }
    /**
     *
     */
    public void re_draw() {
        myCanvas.setCanvasTriangle(frame, this.name, this.xSides, this.ySides, this.nCorners);
        frame.getContentPane().add(myCanvas);
        frame.repaint();
    }

    @Override
    public void shift(String direction, int step, long start) {
        if (direction == null) {
            System.out.print("Direction is null");
        }
        if (!this.canShift) {
            return;
        }
        if (direction.toLowerCase().equals("up")) {
            this.ySides[0] -= step;
            this.ySides[1] -= step;
            this.ySides[2] -= step;
        }
        if (direction.toLowerCase().equals("down")) {
            this.ySides[0] += step;
            this.ySides[1] += step;
            this.ySides[2] += step;
        }
        if (direction.toLowerCase().equals("left")) {
            this.xSides[0] -= step;
            this.xSides[1] -= step;
            this.xSides[2] -= step;
        }
        if (direction.toLowerCase().equals("right")) {
            this.xSides[0] += step;
            this.xSides[1] += step;
            this.xSides[2] += step;
        }
        re_draw();

    }

    public void scaling() {
        double scaleModifier = this.square * this.scale;
        while (calcSquare() > scaleModifier) {
            this.ySides[1]++;
            this.xSides[0]++;
            this.xSides[1]--;
            this.xSides[2]--;
        }
        re_draw();
    }

    public double calc_A() {
        return Math.sqrt((Math.pow(xSides[1] - xSides[0], 2)) + (Math.pow(ySides[1] - ySides[0], 2)));
    }

    public double calc_B() {
        return Math.sqrt((Math.pow(xSides[2] - xSides[1], 2)) + (Math.pow(ySides[2] - ySides[1], 2)));
    }

    public double calc_C() {
        return Math.sqrt((Math.pow(xSides[2] - xSides[0], 2)) + (Math.pow(ySides[2] - ySides[0], 2)));
    }

    @Override
    public double calcSquare() {
        double p = (calc_A() + calc_B() + calc_C()) / 2;
        this.square = Math.sqrt(p * (p - calc_A()) * (p - calc_B()) * (p - calc_C()));
        return square;
    }

    @Override
    public double getSquare() {
        return square;
    }
}
