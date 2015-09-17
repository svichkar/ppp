package com.nixsolutions;

public class Arrays {

	public static int[] sort(int[] a){
		int[] sortedA = a.clone();
		for (int i = 0; i < a.length - 1; i++){
			for(int j = 0; j < a.length - 1 - i; j++){
				if (a[j]>a[j+1]){
					int c = a[j];
					a[j] = a[j+1];
					a[j+1] = c;
				}
			}
		}
		return sortedA;
	}
}
