import javax.swing.*;
import java.awt.*;

/**
 * Created by w700 on 31.01.2016.
 */
public class Canvas extends JComponent {
    private int coord_LeftUpperCornerX = 0;
    private int coord_LeftUpperCornerY = 0;
    private int side_A = 0;
    private int side_B = 0;
    private Figures currFigure;

    public Canvas() {

    }

    public void setCanvasSquare(int coord_LeftLowerCorner, int coord_LeftUpperCorner, double side_A_length, double side_B_length) {
        this.coord_LeftUpperCornerX = coord_LeftLowerCorner;
        this.coord_LeftUpperCornerY = coord_LeftUpperCorner;
        this.side_A = ((int) side_A_length);
        this.side_B = ((int) side_B_length);
    }
    public void paint(Graphics g) {
        g.drawRect(this.coord_LeftUpperCornerX, this.coord_LeftUpperCornerY, ((int) side_A), ((int) side_B));
        //g.drawString(Square.class.getName(), this.coord_LeftLowerCorner, this.coord_LeftUpperCorner);
    }
}
