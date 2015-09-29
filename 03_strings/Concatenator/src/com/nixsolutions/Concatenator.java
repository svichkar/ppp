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
	 * main
	 */
	public static void main(String[] args) {
		String resultString1 = "Java" + " " + "forever";
		System.out.print("\"Java\" + \" \" + \"forever\" - "+"Java" + " " + "forever\n");
		String resultString2 = String.format("Java %s", "forever");
		System.out.print("String.format(\"Java %s\", \"forever\") - "+String.format("Java %s", "forever\n"));
		String resultString3 ="Java ".concat("forever");
		System.out.print("\"Java \".concat(\"forever\") - "+"Java ".concat("forever")+"\n");
		System.out.print("String.join(\" \", \"Java\", \"forever\") - "+String.join(" ", "Java", "forever\n"));
		

	}

}
