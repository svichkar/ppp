/**
 * Created by sobolenko on 1/25/2016.
 */

public class Arrays {
    /**
     * Sorting bubble method
     *
     * @param arr input array
     * @return sorted array
     */
    public static int[] sort(int[] arr) {
        int a = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    a = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = a;
                }
            }
        }
        return arr;
    }
}
