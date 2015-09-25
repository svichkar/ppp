/**
 * 
 */
package com.nixsolutions;

/**
 * @author mixeyes
 *
 */
public class Concatenator {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String resultString1 = "Java" + " " + "forever";
		System.out.print("\"Java\" + \" \" + \"forever\" - "+"Java" + " " + "forever\n");
		String resultString2 = String.format("Java %s", "forever");
		System.out.print("String.format(\"Java %s\", \"forever\") - "+String.format("Java %s", "forever\n"));
		String resultString3 ="Java ".concat("forever");
		System.out.print("\"Java \".concat(\"forever\") - "+"Java ".concat("forever"));
		// TODO Auto-generated method stub

	}

}
