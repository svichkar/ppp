import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by Lexx on 16.11.2015.
 */
public class Circle extends Figures implements Serializable {
    private String name;
    private int[] coord_Center;
    private int radius;
    private JFrame frame;
    private boolean canShift;
    MyCanvas myCanvas = new MyCanvas();

    public Circle(String name) {
        super(name);
    }

    public Circle(String name,int[] coord_Center, int radius, JFrame frame) {
        super(name);
        this.name = name;
        this.coord_Center = coord_Center;
        this.radius = radius;
        this.frame = frame;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setCanShift(boolean canShift) {
        this.canShift = canShift;
    }

    public boolean isCanShift() {
        return canShift;
    }
    @Override
    public void draw(long start) {
        myCanvas.setCanvas(this.name, this.coord_Center, this.radius,start);
        frame.setTitle("Image");
        frame.setSize(500, 500);
        frame.setLocation(300, 50);
        frame.getContentPane().add(myCanvas);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     *
     */
    public void re_draw(long start) {
        myCanvas.setCanvas(name,this.coord_Center,this.radius,start);
        frame.getContentPane().add(myCanvas);
        //tr.update(tr.getGraphics());
        frame.repaint();
        //tr.setVisible(true);
    }

    @Override
    public void shift(String direction, int step, long start) {
        if(direction == null)
        {
        }
        if(!this.canShift)
        {
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
        re_draw(start);

    }

    @Override
    public double calcSquare() {
        return Math.PI*Math.pow(radius,2);
    }

    class MyCanvas extends JComponent {
        private String name;
        private int[] coord_Center;
        private int radius;
        private int radius_parameter;
        private long start;

        public void setCanvas(String name, int[] coord_Center, int radius, long start) {
            this.name = name;
            this.coord_Center = coord_Center;
            this.radius = radius;
            radius_parameter = radius*2;
            this.start = start;
        }
        public MyCanvas() {

        }
        public void paint(Graphics g) {
            g.drawOval(this.coord_Center[0], this.coord_Center[1],this.radius_parameter,this.radius_parameter);
            g.drawString(String.valueOf((int)(System.currentTimeMillis()- start)),this.coord_Center[0],this.coord_Center[1]);
        }
    }
}
