package comparison;

import java.util.Random;


public class SortingComparator {

    public static void main(String[] args) {
        long startTime;
        long finishTime;
        int loopCounter = 0;
        int numberOfTimes = 20;
        long[][] elapsedTimes = new long[2][numberOfTimes];

        for(int i = 0; i < numberOfTimes; i++){
            int[] numbers = getRandomlyFilledArray();

            startTime = System.currentTimeMillis();
            com.nixsolutions.Arrays.sort(numbers);
            finishTime = System.currentTimeMillis();
            elapsedTimes[0][loopCounter] = finishTime-startTime;

            startTime = System.currentTimeMillis();
            java.util.Arrays.sort(numbers);
            finishTime = System.currentTimeMillis();
            elapsedTimes[1][loopCounter] = finishTime-startTime;

            loopCounter++;
        }

        System.out.println("Avg time for com.nixsolutions.Arrays.sort(): " + getAvgTime(elapsedTimes, 0));
        System.out.println("Avg time for java.util.Arrays.sort(): " + getAvgTime(elapsedTimes, 1));
    }

    private static int[] getRandomlyFilledArray(){
        Random rand = new Random();
        int[] toReturn = new int[10000];
        for(int i = 0; i < toReturn.length; i++){
            toReturn[i] = (rand.nextInt(201)-100);
        }
        return toReturn;
    }

    private static float getAvgTime(long[][] a, int firstDimensionIndex){
        float toReturn = 0;
        for(int i = 0; i < a[firstDimensionIndex].length; i++){
            toReturn += a[firstDimensionIndex][i];
        }
        return toReturn/a[firstDimensionIndex].length;
    }
}
