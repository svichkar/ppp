package com.nixsolutions;


public class Arrays {
	
		
	public static int[] sort(int[] arr){
		int[] sortArr;
		sortArr = new int[arr.length];
		System.arraycopy(arr, 0, sortArr, 0, arr.length);
		 for(int i = sortArr.length-1 ; i > 0 ; i--){
	        for(int j = 0 ; j < i ; j++){
	                if( sortArr[j] > sortArr[j+1] ){
	                int tmp = sortArr[j];
	                sortArr[j] = sortArr[j+1];
	                sortArr[j+1] = tmp;
	            }
	        }
	    }
	      
	      return sortArr;
	}
	
	
	
	}
	


