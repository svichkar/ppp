import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexx on 16.11.2015.
 */
public abstract class Figures implements Geometry {
    private String name;
    private double square;
    private boolean canShift;
    private double scale;

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Figures(String name, int center, int square, int perimeter) {
        this.name = name;
        this.square = square;
    }

    public Figures() {
    }

    public void setCanShift(boolean canShift) {
        this.canShift = canShift;
    }

    public boolean isCanShift() {
        return canShift;
    }

    public Figures(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getSquare() {
        return square;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScale() {
        return scale;
    }

    protected abstract void draw();

    public abstract void shift(String direction, int step, long start);

    public abstract double calcSquare();

    public String toString() {
        return ("Figure name: " + getName() + ". Figure square: " + String.format("%.2f", getSquare()) + " by scale coefficient " + String.format("%.5f", getScale())) + "\n";
    }
}
