package com.nixsolutions;

public class Arrays {
	
public static int[] sort(int[] arrayNotSorted){		
		
		int[] array = new int[arrayNotSorted.length];
		System.arraycopy(arrayNotSorted, 0, array, 0, arrayNotSorted.length);
		
		for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int t = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = t;                	
                }
            }
		}
		return array;
	}

}
