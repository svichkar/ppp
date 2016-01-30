
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Lexx on 16.11.2015.
 */
public class Main {
    protected static List<Figures> figures = new ArrayList<Figures>();

    /**
     *
     * @param obj
     */
    public static void turnOff(int obj) {
        for (Figures x : figures) {
            if (x.getName().replaceAll("\\D*", "").equals(String.valueOf(obj))) {
                x.setCanShift(true);
            } else {
                x.setCanShift(false);
            }
        }
    }

    /**
     *
     */
    public static void turnOnAll() {
        for (Figures x : figures) {
            x.setCanShift(true);
        }
    }

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        Listener listener = new Listener();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int choise = random.nextInt(3);
            double scale = random.nextDouble();
            switch (choise) {
                case 0:
                    int rndX = random.nextInt(50);
                    int rndY = random.nextInt(50);

                    int[] coord_LeftLowerCorner = {0, 50};
                    coord_LeftLowerCorner[0] = coord_LeftLowerCorner[0] + rndX;
                    coord_LeftLowerCorner[1] = coord_LeftLowerCorner[1] + rndY;
                    int[] coord_LeftUpperCorner = {0, 0};
                    coord_LeftUpperCorner[0] = coord_LeftUpperCorner[0] + rndX;
                    coord_LeftUpperCorner[1] = coord_LeftUpperCorner[1] + rndY;
                    int[] coord_RightLowerCorner = {70, 50};
                    coord_RightLowerCorner[0] = coord_RightLowerCorner[0] + rndX;
                    coord_RightLowerCorner[1] = coord_RightLowerCorner[1] + rndY;
                    int[] coord_RightUpperCorner = {70, 0};
                    coord_RightUpperCorner[0] = coord_RightUpperCorner[0] + rndX;
                    coord_RightUpperCorner[1] = coord_RightUpperCorner[1] + rndY;
                    Square square_rndm = new Square("square_" + i, coord_LeftLowerCorner, coord_RightLowerCorner, coord_LeftUpperCorner, coord_RightUpperCorner, scale, frame);
                    figures.add(square_rndm);
                    break;
                case 1:
                    rndX = random.nextInt(50);
                    rndY = random.nextInt(50);
                    int[] xPoint = {100 + rndX, 130 + rndX, 160 + rndX};
                    int[] yPoint = {100 + rndY, 70 + rndY, 100 + rndY};
                    Triangle triangle_rndm = new Triangle("triangle_" + i, xPoint, yPoint, 3, scale, frame);
                    figures.add(triangle_rndm);
                    break;
                case 2:
                    int[] coord_Center_rand = {150 + random.nextInt(50), 150 + random.nextInt(50)};
                    int radius_rand = 25 + random.nextInt(10);
                    Circle circle_rndm = new Circle("circle_" + i, coord_Center_rand, radius_rand, scale, frame);
                    figures.add(circle_rndm);
                    break;
            }
        }
        /**
         *
         */
        for (Figures x : figures) {
            x.draw();
            listener.listen(x, frame, false);
            x.setCanShift(true);
        }
        System.out.print(figures.toString());
    }
}

