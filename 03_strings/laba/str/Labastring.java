package laba.str;

import java.util.Arrays;

public class Labastring {

	public static void main(String[] args) {

		String str1 = "Sneguro4ka";
		String str2 = "Ognesru4ka";

		JavaForever();
		Names();
		System.out.println(isAnagram(str1, str2));
	}

	public static void JavaForever() {

		String part1 = "Java";
		String part2 = "forever";
		String Result;

		// way1
		Result = part1 + " " + part2;
		System.out.println(Result);

		// way1.2 "+" use StringBuilder
		Result = part1.concat("  ").concat(part2);
		System.out.println(Result);

		// way2
		Result = new StringBuilder(part1).append("   ").append(part2).toString();
		System.out.println(Result);

		// way3
		Result = new StringBuffer(part1).append("    ").append(part2).toString();
		System.out.println(Result);

		// way4
		Result = String.join("    ", part1, part2);
		System.out.println(Result);
	}

	public static void Names() {

		String allnames = "Cherednyk Kate Yr";
		char temp;

		for (int i = 0; i < allnames.length(); i++) {
			temp = allnames.charAt(i);
			if (Character.isUpperCase(temp)) {
				System.out.print(temp + " ");
			}
		}
		System.out.println("");
	}

	public static boolean isAnagram(String str1, String str2) {

		char[] array1;
		char[] array2;

		str1 = str1.replaceAll("\\W", "").toUpperCase();
		str2 = str2.replaceAll("\\W", "").toUpperCase();

		if (str1.length() == str2.length()) {

			array1 = str1.toCharArray();
			array2 = str2.toCharArray();
			Arrays.sort(array1);
			Arrays.sort(array2);
			return Arrays.equals(array1, array2);

		} else {

			return false;
		}
	}
}
