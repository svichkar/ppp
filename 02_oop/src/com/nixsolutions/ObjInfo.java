import java.io.Serializable;

/**
 * Created by sobolenko on 11/20/2015.
 */
public class ObjInfo implements Serializable
{
    private double squareSQ;
    private double circleSQ;

    public ObjInfo(double squareSQ, double circleSQ) {
        this.squareSQ = squareSQ;
        this.circleSQ = circleSQ;
    }

    public double getSquareSQ() {
        return squareSQ;
    }

    public double getCircleSQ() {
        return circleSQ;
    }
}
