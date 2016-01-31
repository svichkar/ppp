import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexx on 16.11.2015.
 * Abstract class which determinate mutual characteristics of all figures.
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

    /**
     * setCanShift(boolean canShift) - set true or false(true - yes, false - not)
     *
     * @param canShift boolean
     */
    public void setCanShift(boolean canShift) {
        this.canShift = canShift;
    }

    /**
     * isCanShift()
     *
     * @return true or false(true - yes, false - not)
     */
    public boolean isCanShift() {
        return canShift;
    }

    /**
     * Constructor
     *
     * @param 'figure's name'
     */
    public Figures(String name) {
        this.name = name;
    }

    /**
     * name - the figure's name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get square - square shape
     *
     * @return Square
     */
    public double getSquare() {
        return square;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScale() {
        return scale;
    }

    /**
     * draw() - render the figure
     */
    protected abstract void draw();

    /**
     * shift(String direction, int step, long start) - shifting the figure.
     *
     * @param direction - how direction you want to shift
     * @param step      - which step of shifting (how many pixels)
     * @param start
     */
    public abstract void shift(String direction, int step, long start);

    public abstract double calcSquare();

    /**
     * toString() - overridden method, which display information at appropriate form.
     *
     * @return string
     */
    public String toString() {
        return ("Figure name: " + getName() + ". Figure square: " + String.format("%.2f", getSquare()) + " by scale coefficient " + String.format("%.5f", getScale())) + "\n";
    }
}
