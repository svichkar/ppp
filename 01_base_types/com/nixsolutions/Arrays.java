package com.nixsolutions;

class Arrays {

    public static int[] sort(int[] arr) {
        int[] sortArray = java.util.Arrays.copyOf(arr, arr.length);
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < sortArray.length - 1; i++) {
                if (sortArray[i] > sortArray[i + 1]) {
                    int temp = sortArray[i];
                    sortArray[i] = sortArray[i + 1];
                    sortArray[i + 1] = temp;
                    isSorted = false;
                }
            }
        }
        return sortArray;
    }

}
