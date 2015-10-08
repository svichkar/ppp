package com.nixsolutions;

public class IntArrayToStringConverter implements Converter<String, int[]> {

    @Override
    public String get(int[] input) {
	String resString = "";
	for (int item : input) {
	    resString = resString + item + " ";
	}
	return resString.trim();
    }

}
