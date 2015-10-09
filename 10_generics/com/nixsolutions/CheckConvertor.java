package com.nixsolutions;

/**
 * Class for testing two implementation of interface Convertor
 * 
 * @author maxb
 * 
 */
public class CheckConvertor {

	public static void main(String[] args) {
		Float f = 12.1234f;
		Integer[] arrayInt = new Integer[] { 12, 10, 1, 5 };
		// create instances of classes
		MyConv1 c1 = new MyConv1();
		MyConv2 c2 = new MyConv2();
		System.out.printf("Float value before %1s and double after %2s%n", f,
				c1.get(f));
		System.out
				.printf("Array integer after processing %s", c2.get(arrayInt));

	}

}
