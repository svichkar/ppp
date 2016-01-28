/**
 * Created by Lexx on 16.11.2015.
 */
public abstract class Figures implements canShift
{
   private String name;
   private int center;
   private int square;
   private int perimeter;
   private boolean canShift;

    public Figures(String name, int center, int square, int perimeter) {
        this.name = name;
        this.center = center;
        this.square = square;
        this.perimeter = perimeter;
    }

    public Figures(){}

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

    public int getSquare() {
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

   protected abstract void draw(long start);
   public abstract void shift(String direction, int step, long start);
  // protected abstract void listen();
   public abstract double calcSquare();
}
