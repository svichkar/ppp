import java.util.Arrays;
import java.util.Random;

/**
 * Created by konstantin on 16.11.2015.
 */
public class Main {

    public static final int TRANSPORT_COUNT = 10;
    public static final int CYCLE_COUNT = 5;

    public static void main(String args[]) {
        Random random = new Random();

        //create array of 10 Transport elements
        Transport[] transports = new Transport[TRANSPORT_COUNT];

        // Fill array of random Transport elements with random transports
        for (int i = 0; i < TRANSPORT_COUNT; i++) {
            int index = random.nextInt(3);
            switch (index) {
                case 0:
                    transports[i] = new Car();
                    break;
                case 1:
                    transports[i] = new Bike();
                    break;
                case 2:
                    transports[i] = new Bus();
                    break;
            }
        }

        // Perform 5 cycles of moving for each Transport element
        for (Transport t : transports) {
            for (int i = 0; i < CYCLE_COUNT; i++) {
                int index = random.nextInt(3);
                switch (index) {
                    case 0:
                        t.left();
                        break;
                    case 1:
                        t.right();
                        break;
                    case 2:
                        int stepsNumber = random.nextInt(5) + 1;
                        for (int j = 0; j < stepsNumber; j++) {
                            t.go();
                        }
                        break;
                }
            }
        }

        //Sort Transport elements by distance ascending
        Transport[] sortedTransport = sort(transports);

        // Output distances for each transport in array in ascending order by distance
        for (Transport t : sortedTransport) {
            System.out.println(String.format("%s: %d", t.getClass().getCanonicalName(), t.getDistance()));
        }
    }

    /**
     * @param array - array of Transport elements
     * @return sortedArray - array of Transport elements sorted by distance ascending
     */
    public static Transport[] sort(Transport[] array) {
        Transport[] sortedArray = array.clone();
        Arrays.sort(sortedArray);
        return sortedArray;
    }
}
