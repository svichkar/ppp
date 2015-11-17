/**
 * Created by konstantin on 16.11.2015.
 */
public class Bike extends Transport {
    /**
     * Override method left for Bike
     * distance parameter in the class transport should be increase at 8
     */
    @Override
    public void left() {
        setDistance(getDistance() + 8);
    }

    /**
     * Override method right for Bike
     * distance parameter in the class transport should be increase at 8
     */
    @Override
    public void right() {
        setDistance(getDistance() + 8);
    }

    /**
     * Override method go for Bike
     * distance parameter in the class transport should be increase at 8
     */
    @Override
    public void go() {
        setDistance(getDistance() + 8);
    }
}
