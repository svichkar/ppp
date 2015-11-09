package com.nixsolutions;

public class Arrays {
	public static int[] sort(int[] array){
		int[] sortArray =  (int[])array.clone();
		int tmp;
		for (int i=0; i<sortArray.length; i++)
			for (int j=0; j<sortArray.length-i-1; j++){
				if (sortArray[j]>sortArray[j+1]){
					tmp=sortArray[j];
					sortArray[j]=sortArray[j+1];
					sortArray[j+1]=tmp;
				}
			}
		return sortArray;
	}

}
