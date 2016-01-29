import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexx on 16.11.2015.
 */
public abstract class Figures implements Geometry {
    private String name;
    private int center;
    private double square;
    private int perimeter;
    private boolean canShift;
    private double scale;

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public Figures(String name, int center, int square, int perimeter) {
        this.name = name;
        this.center = center;
        this.square = square;
        this.perimeter = perimeter;
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

    public int getCenter() {
        return center;
    }

    public double getSquare() {
        return square;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCenter(int center) {
        this.center = center;
    }

    public void setSquare(int square) {
        this.square = square;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    protected abstract void draw();

    public abstract void shift(String direction, int step, long start);

    public abstract double calcSquare();

    public String toString()
    {
        /*List<String> result = new ArrayList<String>();
        String resultString="";
        result.add("Figure name: "+getName()+"Figure square: "+String.valueOf(getSquare())+" by scale coefficient "+getScale());
        for(String x: result){
            resultString += x;
            resultString +="\n";
        }*/
        return ("Figure name: "+getName()+". Figure square: "+String.valueOf(getSquare())+" by scale coefficient "+getScale())+"\n";
    }
}
