import javax.swing.*;
import java.awt.*;

/**
 * Created by Lexx on 16.11.2015.
 */
public class Circle extends Figures {
    private String name;
    private int[] coord_Center;
    private int radius;
    private double square;
    private JFrame frame;
    private boolean canShift;
    Canvas myCanvas = new Canvas();
    private double scale;

    public Circle(String name) {
        super(name);
    }

    public Circle(String name, int[] coord_Center, int radius, double scale, JFrame frame) {
        super(name);
        this.name = name;
        this.coord_Center = coord_Center;
        this.radius = radius;
        this.frame = frame;
        this.scale = scale;
        calcSquare();
        scaling();
    }

    @Override
    public double getSquare() {
        return square;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getScale() {
        return scale;
    }

    public void setCanShift(boolean canShift) {
        this.canShift = canShift;
    }

    public boolean isCanShift() {
        return canShift;
    }

    @Override
    public void draw() {
        myCanvas.setCanvasCircle(frame, this.name, this.coord_Center, this.radius);
        frame.getContentPane().add(myCanvas);
        frame.setVisible(true);
    }

    public void re_draw() {
        myCanvas.setCanvasCircle(frame, name, this.coord_Center, this.radius);
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
            this.coord_Center[1] -= step;
        }
        if (direction.toLowerCase().equals("down")) {
            this.coord_Center[1] += step;
        }
        if (direction.toLowerCase().equals("left")) {
            this.coord_Center[0] -= step;
        }
        if (direction.toLowerCase().equals("right")) {
            this.coord_Center[0] += step;
        }
        re_draw();

    }

    @Override
    public double calcSquare() {
        square = Math.PI * Math.pow(radius, 2);
        return square;
    }

    public void scaling() {
        double scaleModifier = this.square * this.scale;
        for (int i = 0; calcSquare() > scaleModifier; i++) {
            this.radius--;
        }
        re_draw();
    }
}
