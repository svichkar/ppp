import javax.swing.*;
import java.awt.*;

/**
 * Created by w700 on 31.01.2016.
 */
public class Canvas extends JComponent {
    private JFrame frame;
    private int coord_LeftUpperCornerX = 0;
    private int coord_LeftUpperCornerY = 0;
    private int side_A = 0;
    private int side_B = 0;
    private String name = "";
    private int[] xSides;
    private int[] ySides;
    private String currFigureName;
    private int nCorners = 3;
    int[] coord_Center;
    int radius = 0;
    int radius_parameter = 0;
    int frameX = 500;
    int frameY = 500;

    public Canvas() {
    }

    public void setCanvasSquare(JFrame frame, String currFigureName, int coord_LeftLowerCorner, int coord_LeftUpperCorner, double side_A_length, double side_B_length) {
        this.coord_LeftUpperCornerX = coord_LeftLowerCorner;
        this.coord_LeftUpperCornerY = coord_LeftUpperCorner;
        this.side_A = ((int) side_A_length);
        this.side_B = ((int) side_B_length);
        this.currFigureName = currFigureName;
        this.frame = frame;
        setFrame();
    }

    public void setCanvasTriangle(JFrame frame, String name, int[] xSides, int[] ySides, int nCorners) {
        this.name = name;
        this.xSides = xSides;
        this.ySides = ySides;
        this.currFigureName = name;
        this.frame = frame;
        setFrame();
    }

    public void setCanvasCircle(JFrame frame, String name, int[] coord_Center, int radius) {
        this.currFigureName = name;
        this.coord_Center = coord_Center;
        this.radius = radius;
        radius_parameter = radius * 2;
        this.frame = frame;
        setFrame();
    }

    private void setFrame() {
        frame.setTitle("Image");
        frame.setSize(frameX, frameY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void paint(Graphics g) {
        g.drawString("Tips:", frameX - 495, frameY - 90);
        g.drawString("For move figures use 'WASD' keys.", frameX - 495, frameY - 80);
        g.drawString("For select one press number from name of figure, for select all press '0'", frameX - 495, frameY - 70);
        g.drawString("(ex. for figure with name 'square_1' number is 1).", frameX - 495, frameY - 60);
        switch (currFigureName.replaceAll("\\_\\d*", "")) {
            case "square":
                g.drawRect(this.coord_LeftUpperCornerX, this.coord_LeftUpperCornerY, ((int) side_A), ((int) side_B));
                break;
            case "triangle":
                g.drawPolygon(this.xSides, this.ySides, this.nCorners);
                break;
            case "circle":
                g.drawOval(this.coord_Center[0], this.coord_Center[1], this.radius_parameter, this.radius_parameter);
                break;
            default:
                System.out.print("Figure name is incorrect");
        }
    }
}
