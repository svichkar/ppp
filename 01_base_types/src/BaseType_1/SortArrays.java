package BaseType_1;

import com.nixsolutions.*;
import java.util.Random;

public class SortArrays {

    public static void main(String[] args) {

        int iterations = 20;
        long [][] sotringTime = new long[2][iterations];
        long sortingTimeCustom = 0;
        long sortingTimeJava = 0;

        for(int iter = 0; iter < iterations; iter++) {
            //create array with random integers (from -100 to 100)
            int[] arrayToSort = randomIntArray(10000, -100, 100);
            //custom sorting method
            long startTime = System.nanoTime();
            int[] firstArrSorted = Arrays.sort(arrayToSort);
            long finishTimet = System.nanoTime();
            sotringTime [0][iter] = finishTimet - startTime;
            sortingTimeCustom =+ sotringTime [0][iter];
            //java sorting method
            startTime = System.nanoTime();
            java.util.Arrays.sort(arrayToSort);
            finishTimet = System.nanoTime();
            sotringTime [1][iter] = finishTimet - startTime;
            sortingTimeJava =+ sotringTime [1][iter];

            //check that both methods sort arrays with the same way
            boolean arrEquals = java.util.Arrays.equals(firstArrSorted, arrayToSort);
            if(!arrEquals)
                System.out.println("In " + iter + " iteration arrays were sorted different ways!");
        }

            System.out.println("Average time of sorting using Arrays.sort method is: " + sortingTimeCustom/iterations);
            System.out.println("Average time of sorting using java.util.Arrays.sort method is: " + sortingTimeJava/iterations);

    }

    public static int[] randomIntArray(int size, int minValue, int maxValue) {
        int[] outArray = new int[size];
        int randomRage = maxValue - minValue + 1;
        for(int count = 0; count < outArray.length; count++) {
            Random random = new Random();
            outArray[count] = random.nextInt(randomRage) + minValue;
        }
        return outArray;
    }
}
