/**
 * Created by sobolenko on 1/29/2016.
 */
public interface Geometry {
    public String name = "";
    public double square = 0;
    public int scale = 0;

    void shift (String direction, int step, long start);
    <T> void scale (T obj, int scaleModifier);
}