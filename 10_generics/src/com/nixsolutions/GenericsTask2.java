package com.nixsolutions;

public class GenericsTask2 {
    public static void main(String[] args) {
	FloatToDoubleConverter fToD = new FloatToDoubleConverter();
	Double resultDouble = fToD.get(2.5f);
	System.out.println(resultDouble);
	IntArrayToStringConverter intArrToString = new IntArrayToStringConverter();
	String resultString = intArrToString.get(new int[] { 1, 2, 3, 4, 5, 6, 7 });
	System.out.println(resultString);
    }
}
