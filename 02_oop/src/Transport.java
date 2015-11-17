/**
 * Created by konstantin on 16.11.2015.
 */
public abstract class Transport implements Comparable<Transport> {

    private int distance = 0;

    /**
     * distance which was traveled
     * @param distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * get distance which was traveled
     * @return distance
     */
    public int getDistance() {
        return this.distance;
    }

    /**
     * implementation of Comparable interface
     * @param t - the Transport to be compared
     * @return a negative integer, zero, or a positive integer
     *         as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Transport t) {
        return Integer.compare(this.getDistance(), t.getDistance());
    }

    /**
     * abstract method left
     */
    public abstract void left();

    /**
     * abstract method right
     */
    public abstract void right();

    /**
     * abstract method go
     */
    public abstract void go();
}
