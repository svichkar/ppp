/**
 * Created by konstantin on 16.11.2015.
 */
public class Bus extends Transport {
    /**
     * Override method left for Bus
     * distance parameter in the class transport should be increase at 9
     */
    @Override
    public void left() {
        setDistance(getDistance() + 9);
    }

    /**
     * Override method right for Bus
     * distance parameter in the class transport should be increase at 9
     */
    @Override
    public void right() {
        setDistance(getDistance() + 9);
    }

    /**
     * Override method go for Bus
     * distance parameter in the class transport should be increase at 9
     */
    @Override
    public void go() {
        setDistance(getDistance() + 9);
    }
}
