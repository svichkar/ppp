/**
 * Created by konstantin on 16.11.2015.
 */
public class Car extends Transport {

     /**
     * Override method left for Car
     * distance parameter in the class transport should be increase at 11
     */
    @Override
    public void left() {
        setDistance(getDistance() + 11);
    }

    /**
     * Override method right for Car
     * distance parameter in the class transport should be increase at 11
     */
    @Override
    public void right() {
        setDistance(getDistance() + 11);
    }

    /**
     * Override method go for Car
     * distance parameter in the class transport should be increase at 11
     */
    @Override
    public void go() {
        setDistance(getDistance() + 11);
    }

}
