/**
 * 
 */
package com.nixsolutions;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * class implements static methods of exponential converter
 * 
 * @author mixeyes
 *
 */
public class ExponentialConverter {

	public ExponentialConverter() {

	}
/**
 * convert Exponential format to Double format
 * @param exponential Digit
 * @return string in double format */
	public static String convertExpToDouble(String expDigit) {
		Double doubleDigdit = Double.parseDouble(expDigit);

		return String.valueOf(doubleDigdit);
	}

	/**
	 * convert Double format To Exponential format
	 * @param doubleDigitStr digit in double format
	 * @return string in exponential format "0.######E0"*/
	public static String convertDoubleToExp(String doubleDigitStr) {
		Double doubleDigit = Double.parseDouble(doubleDigitStr);
		NumberFormat formatter = new DecimalFormat("0.######E0");
		return formatter.format(doubleDigit);
	}

}
