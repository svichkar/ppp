package com.nixsolutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**LabOne_Two class is created to implement the second task for "Basic types" quiz.
 * @author	kulishov */
public class LabOne_Two {

	/**main method is an access point for launching first task.
	 * @author	kulishov
	 * @param	args		defines parameters that are used.
	 * @throws	IOException	throws {@link java.io.IOException} 
	 * 						if there is no console to write to. */
	public static void main(String[] args) throws IOException {
		final String scientificPattern = "^\\d(\\.\\d+)?[eE][+\\-]?\\d{1,2}[df]?(\r\n)?$";
		final String normalPattern = "^\\d+(\\.\\d+)?[df]?(\r\n)?$";
		Pattern sciPattern = Pattern.compile(scientificPattern);
		Pattern normPattern = Pattern.compile(normalPattern);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please input number with floating point.");
		String input = br.readLine().replaceAll(",", ".");
		Matcher sciMatcher = sciPattern.matcher(input);
		Matcher normMatcher = normPattern.matcher(input);
		if (sciMatcher.find()) {
			Float outNumber = Float.parseFloat(input);
			System.out.println(String.format("Input in normal form: %.8f", outNumber)
					.replaceAll("0+$", "").replaceAll("\\.$", ""));
		} else if (normMatcher.find()) {
			Float outNumber = Float.parseFloat(input);
			System.out.println(String.format("Input in scientific form: %e", outNumber));
		} else {
			System.out.println("Input string is not a number.");
		}
	}

}
