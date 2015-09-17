package com.nixsolutions;

public class Arrays {
	public static int[] sort(int[] intArr){
		int[] returnArr = java.util.Arrays.copyOf(intArr, intArr.length);
		for (int i=0;i<returnArr.length;i++){
			for (int j=0;j<intArr.length;j++){
				int temp = 0;
				if (returnArr[i]<returnArr[j]){
					temp = returnArr[i];
					returnArr[i] = returnArr[j];
					returnArr[j] = temp;
				}
			}
		}
		return returnArr;
	}
}
