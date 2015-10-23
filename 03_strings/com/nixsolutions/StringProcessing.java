package com.nixsolutions;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import java.lang.reflect.*;

/**
 * Class for showing diff ways to concat string
 * 
 * @author maxb
 * 
 */
public class StringProcessing {

	final static String STR1 = "Java";
	final static String STR2 = "forever";

	private static String variant1;
	private static String variant2;
	private static String variant3;
	private static String variant4;
	private static String variant5;

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, InstantiationException {

		// /By "+"
		variant1 = STR1 + " " + STR2;
		variant2 = STR1.concat(" ").concat(STR2);
		variant3 = new StringBuilder().append(STR1).append(" ").append(STR2)
				.toString();
		variant4 = new StringBuffer().append(STR1).append(" ").append(STR2)
				.toString();
		variant5 = String.format("%1s %2s", STR1, STR2);

		for (Field oneField : StringProcessing.class.getDeclaredFields()) {

			if (oneField.getName().startsWith("variant")) {
				System.out.printf("%1s retuns : %2s%n", oneField.getName(),
						oneField.get(oneField.getType().newInstance()));
			}
		}

	}
}
