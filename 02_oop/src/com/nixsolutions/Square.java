import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by Lexx on 16.11.2015.
 */
public class Square extends Figures {
    MyCanvas myCanvas = new MyCanvas();
    private String name;
    private JFrame frame;
    private double square;
    private int[] coord_LeftLowerCorner = new int[2];
    private int[] coord_RightLowerCorner = new int[2];
    private int[] coord_LeftUpperCorner = new int[2];
    private int[] coord_RightUpperCorner = new int[2];
    private double side_A_Length;
    private double side_B_Length;
    private boolean canShift;

    public Square(String name, int center, int square, int perimeter) {
        super(name);
    }

    public Square(String name, int[] coord_LeftLowerCorner, int[] coord_RightLowerCorner, int[] coord_LeftUpperCorner, int[] coord_RightUpperCorner, JFrame frame) {
        super(name);
        this.coord_LeftLowerCorner = coord_LeftLowerCorner;
        this.coord_RightLowerCorner = coord_RightLowerCorner;
        this.coord_LeftUpperCorner = coord_LeftUpperCorner;
        this.coord_RightUpperCorner = coord_RightUpperCorner;
        this.frame = frame;
        this.name = name;
    }

    public boolean isCanShift() {
        return canShift;
    }

    public void setCanShift(boolean canShift) {
        this.canShift = canShift;
    }

    @Override public String getName() {
        return name;
    }

    @Override public double getSquare() {
        return square;
    }

    public double calc_A() {
        return Math.sqrt((Math.pow(coord_LeftLowerCorner[0] - coord_LeftUpperCorner[0], 2)) + (Math.pow(coord_LeftLowerCorner[1] - coord_LeftUpperCorner[1], 2)));
    }

    public double calc_B() {
        return Math.sqrt((Math.pow(coord_RightUpperCorner[0] - coord_LeftUpperCorner[0], 2)) + (Math.pow(coord_RightUpperCorner[1] - coord_LeftUpperCorner[1], 2)));
    }

    @Override public void draw() {
        draw(coord_LeftUpperCorner, coord_LeftUpperCorner, ((int) calc_A()), (int) calc_B());
    }

    public double calcSquare() {
        this.square = calc_A() * calc_B();
        return square;
    }

    public void draw(int[] coord_LeftUpperCorner, int[] coord_LeftLowerCorner, int side_A, int side_B) {
        myCanvas.setCanvas(coord_LeftUpperCorner[0], coord_LeftUpperCorner[1], side_A, side_B);
        frame.setTitle("Image");
        frame.setSize(500, 500);
        //frame.setLocation(300, 50);
        frame.getContentPane().add(myCanvas);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void re_draw() {
        myCanvas.setCanvas(coord_LeftUpperCorner[0], coord_LeftUpperCorner[1], calc_A(), calc_B());
        frame.getContentPane().add(myCanvas);
        frame.repaint();
    }

    @Override public void shift(String direction, int step, long start) {
        if (direction == null) {

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

    @Override public <T> void scale(T obj, int scaleModifier) {
        for (int i = 0; calcSquare() > scaleModifier; i++) {
            this.coord_LeftLowerCorner[1]--;
            this.coord_RightUpperCorner[0]--;
        }
        re_draw();
    }

    class MyCanvas extends JComponent {

        private int coord_LeftLowerCorner = 0;
        private int coord_LeftUpperCorner = 0;
        private int side_A = 0;
        private int side_B = 0;

        public MyCanvas() {

        }

        public void setCanvas(int coord_LeftLowerCorner, int coord_LeftUpperCorner, double side_A_length, double side_B_length) {
            this.coord_LeftLowerCorner = coord_LeftLowerCorner;
            this.coord_LeftUpperCorner = coord_LeftUpperCorner;
            this.side_A = ((int) side_A_length);
            this.side_B = ((int) side_B_length);
        }

        public void paint(Graphics g) {
            g.drawRect(this.coord_LeftLowerCorner, this.coord_LeftUpperCorner, ((int) side_A), ((int) side_B));
            //g.drawString(Square.class.getName(), this.coord_LeftLowerCorner, this.coord_LeftUpperCorner);
        }
    }

}
